#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="PlantUML Downloader Utility v${SCRIPT_VERSION}"

# Color configuration
COLOR_NORMAL="\033[0m"
COLOR_RED="\033[91m"
COLOR_GREEN="\033[92m"
COLOR_YELLOW="\033[93m"
COLOR_BLUE="\033[94m"
COLOR_MAGENTA="\033[95m"
COLOR_CYAN="\033[96m"
COLOR_WHITE="\033[97m"

# Enable proper error handling
set -o pipefail

# PlantUML version configuration
use_latest=0
# specific_version="tags/v1.2024.4"
# specific_version="tags/v1.2024.6"
specific_version="tags/v1.2025.1"

# ========== MAIN PROCESS ==========
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_MAGENTA}${SCRIPT_TITLE}${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo

# Save the current directory
currentDir=$(pwd)
echo -e "${COLOR_CYAN}Current directory saved: ${currentDir}${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Downloading PlantUML ====${COLOR_NORMAL}"

# Clean previous PlantUML JAR if exists
echo -e "${COLOR_CYAN}Checking for existing PlantUML installation...${COLOR_NORMAL}"
if [ -f "plantuml.jar" ]; then
    echo -e "${COLOR_YELLOW}Found existing plantuml.jar - removing...${COLOR_NORMAL}"
    rm -f "plantuml.jar"
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}ERROR: Failed to delete existing plantuml.jar.${COLOR_NORMAL}"
        exit 1
    else
        echo -e "${COLOR_GREEN}Successfully removed existing plantuml.jar.${COLOR_NORMAL}"
    fi
else
    echo -e "${COLOR_CYAN}No existing plantuml.jar found.${COLOR_NORMAL}"
fi

# Install jq if not installed
echo
echo -e "${COLOR_BLUE}==== Installing JQ JSON Parser ====${COLOR_NORMAL}"
if ! command -v jq &> /dev/null; then
    echo -e "${COLOR_YELLOW}JQ not found. Attempting to install...${COLOR_NORMAL}"
    if command -v apt &> /dev/null; then
        echo -e "${COLOR_CYAN}Using apt package manager...${COLOR_NORMAL}"
        sudo apt update && sudo apt install -y jq
        if [ $? -ne 0 ]; then
            echo -e "${COLOR_RED}ERROR: Failed to install jq using apt.${COLOR_NORMAL}"
            echo -e "${COLOR_RED}Please install jq manually and try again.${COLOR_NORMAL}"
            exit 1
        fi
    elif command -v brew &> /dev/null; then
        echo -e "${COLOR_CYAN}Using Homebrew package manager...${COLOR_NORMAL}"
        brew install jq
        if [ $? -ne 0 ]; then
            echo -e "${COLOR_RED}ERROR: Failed to install jq using Homebrew.${COLOR_NORMAL}"
            echo -e "${COLOR_RED}Please install jq manually and try again.${COLOR_NORMAL}"
            exit 1
        fi
    elif command -v yum &> /dev/null; then
        echo -e "${COLOR_CYAN}Using yum package manager...${COLOR_NORMAL}"
        sudo yum install -y jq
        if [ $? -ne 0 ]; then
            echo -e "${COLOR_RED}ERROR: Failed to install jq using yum.${COLOR_NORMAL}"
            echo -e "${COLOR_RED}Please install jq manually and try again.${COLOR_NORMAL}"
            exit 1
        fi
    else
        echo -e "${COLOR_RED}ERROR: Could not find a suitable package manager to install jq.${COLOR_NORMAL}"
        echo -e "${COLOR_RED}Please install jq manually and try again.${COLOR_NORMAL}"
        exit 1
    fi
    echo -e "${COLOR_GREEN}Successfully installed jq.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}JQ is already installed.${COLOR_NORMAL}"
fi

# Get PlantUML download URL
echo
echo -e "${COLOR_BLUE}==== Retrieving PlantUML Download URL ====${COLOR_NORMAL}"

if [ "$use_latest" -eq 1 ]; then
    echo -e "${COLOR_CYAN}Extracting download URL for the latest release of PlantUML...${COLOR_NORMAL}"
    download_url=$(curl -s https://api.github.com/repos/plantuml/plantuml/releases/latest | jq -r '.assets[] | select(.name | endswith("plantuml.jar")) | .browser_download_url')
else
    echo -e "${COLOR_CYAN}Extracting download URL for version ${specific_version} of PlantUML...${COLOR_NORMAL}"
    download_url=$(curl -s "https://api.github.com/repos/plantuml/plantuml/releases/${specific_version}" | jq -r '.assets[] | select(.name | endswith("plantuml.jar")) | .browser_download_url')
fi

# Check if download URL was obtained
if [ -z "$download_url" ]; then
    echo -e "${COLOR_RED}ERROR: Could not determine PlantUML download URL.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Either the API request failed or this version does not exist.${COLOR_NORMAL}"
    exit 1
fi

echo -e "${COLOR_CYAN}Download URL: ${download_url}${COLOR_NORMAL}"

# Download PlantUML JAR
echo
echo -e "${COLOR_BLUE}==== Downloading PlantUML JAR ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Downloading plantuml.jar...${COLOR_NORMAL}"
curl -sL -o plantuml.jar "$download_url"
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to download plantuml.jar.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please check your internet connection or try again later.${COLOR_NORMAL}"
    exit 1
fi

# Verify the download
if [ ! -f "plantuml.jar" ]; then
    echo -e "${COLOR_RED}ERROR: plantuml.jar was not downloaded correctly.${COLOR_NORMAL}"
    exit 1
else
    # Get file size to verify content
    size=$(stat -c%s "plantuml.jar" 2>/dev/null || stat -f%z "plantuml.jar" 2>/dev/null)
    if [ "$size" -lt 1000 ]; then
        echo -e "${COLOR_RED}WARNING: Downloaded plantuml.jar is suspiciously small (${size} bytes).${COLOR_NORMAL}"
        echo -e "${COLOR_RED}It may not have downloaded correctly.${COLOR_NORMAL}"
    else
        echo -e "${COLOR_GREEN}Successfully downloaded plantuml.jar (${size} bytes).${COLOR_NORMAL}"
    fi
fi

# Download additional components
echo
echo -e "${COLOR_BLUE}==== Downloading JLatexMath ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Downloading JLatexMath...${COLOR_NORMAL}"
curl -sL -o jlatexmath.zip http://beta.plantuml.net/plantuml-jlatexmath.zip
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to download JLatexMath.${COLOR_NORMAL}"
    exit 1
else
    echo -e "${COLOR_GREEN}Successfully downloaded JLatexMath.${COLOR_NORMAL}"
fi

echo -e "${COLOR_CYAN}Extracting JLatexMath...${COLOR_NORMAL}"
unzip -o jlatexmath.zip -d . > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to extract JLatexMath.${COLOR_NORMAL}"
    # Continue anyway to cleanup
    echo -e "${COLOR_YELLOW}Continuing without JLatexMath (LaTeX formulas may not work properly).${COLOR_NORMAL}"
fi

echo -e "${COLOR_CYAN}Removing jlatexmath.zip...${COLOR_NORMAL}"
if [ -f "jlatexmath.zip" ]; then
    rm -f jlatexmath.zip
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_YELLOW}WARNING: Could not delete jlatexmath.zip.${COLOR_NORMAL}"
    else
        echo -e "${COLOR_GREEN}Successfully removed jlatexmath.zip.${COLOR_NORMAL}"
    fi
fi

echo
echo -e "${COLOR_BLUE}==== Downloading Batik and Fop ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Downloading Batik and Fop...${COLOR_NORMAL}"
curl -sL -o batikAndFop.zip http://beta.plantuml.net/batikAndFop.zip
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to download Batik and Fop.${COLOR_NORMAL}"
    exit 1
else
    echo -e "${COLOR_GREEN}Successfully downloaded Batik and Fop.${COLOR_NORMAL}"
fi

echo -e "${COLOR_CYAN}Extracting Batik and Fop...${COLOR_NORMAL}"
unzip -o batikAndFop.zip -d . > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to extract Batik and Fop.${COLOR_NORMAL}"
    # Continue anyway to cleanup
    echo -e "${COLOR_YELLOW}Continuing without Batik and Fop (SVG and PDF output may not work properly).${COLOR_NORMAL}"
fi

echo -e "${COLOR_CYAN}Removing batikAndFop.zip...${COLOR_NORMAL}"
if [ -f "batikAndFop.zip" ]; then
    rm -f batikAndFop.zip
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_YELLOW}WARNING: Could not delete batikAndFop.zip.${COLOR_NORMAL}"
    else
        echo -e "${COLOR_GREEN}Successfully removed batikAndFop.zip.${COLOR_NORMAL}"
    fi
fi

# Verify components
echo
echo -e "${COLOR_BLUE}==== Verifying Installation ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Checking for required JAR files...${COLOR_NORMAL}"

missing_files=0
declare -a jar_files=("batik-all-1.7.jar" "jlatexmath-minimal-1.0.3.jar" "jlm_cyrillic.jar" "jlm_greek.jar" 
                      "avalon-framework-4.2.0.jar" "commons-io-1.3.1.jar" "commons-logging-1.0.4.jar" 
                      "fop.jar" "xml-apis-ext-1.3.04.jar" "xmlgraphics-commons-1.4.jar")

for jar in "${jar_files[@]}"; do
    if [ ! -f "$jar" ]; then
        echo -e "${COLOR_RED}Missing: $jar${COLOR_NORMAL}"
        ((missing_files++))
    else
        echo -e "${COLOR_GREEN}Found: $jar${COLOR_NORMAL}"
    fi
done

if [ $missing_files -gt 0 ]; then
    echo
    echo -e "${COLOR_YELLOW}WARNING: $missing_files required JAR files are missing.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}PlantUML may not function properly for all diagram types.${COLOR_NORMAL}"
else
    echo
    echo -e "${COLOR_GREEN}All required JAR files are present.${COLOR_NORMAL}"
fi

# Final completion message
echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}PLANTUML DOWNLOAD COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo
echo -e "${COLOR_CYAN}You can now generate diagrams with the included PlantUML JAR.${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Use the run_plantuml_png.sh or run_plantuml_svg.sh scripts to generate diagrams.${COLOR_NORMAL}"

echo
echo -e "Press Enter key to continue..."
read -r
echo
