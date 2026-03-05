#!/bin/bash
# PlantUML Common Script for Linux/Ubuntu/WSL

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"

# Check if format parameter is provided
if [ -z "$1" ]; then
    echo "ERROR: No output format specified."
    echo "Usage: plantuml_common.sh [format] [format_name]"
    echo "Example: plantuml_common.sh png \"PNG\""
    exit 1
fi

# Set format parameters from command line
FORMAT="$1"
FORMAT_NAME="$2"
SCRIPT_TITLE="PlantUML ${FORMAT_NAME} Generator v${SCRIPT_VERSION}"

# Color configuration with ANSI escape sequences
COLOR_NORMAL="\033[0m"
COLOR_RED="\033[91m"
COLOR_GREEN="\033[92m"
COLOR_YELLOW="\033[93m"
COLOR_BLUE="\033[94m"
COLOR_MAGENTA="\033[95m"
COLOR_CYAN="\033[96m"
COLOR_WHITE="\033[97m"

# ========== MAIN PROCESS ==========
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_MAGENTA}${SCRIPT_TITLE}${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo

# Save current directory
currentDir=$(pwd)
echo -e "${COLOR_CYAN}Current directory saved: ${currentDir}${COLOR_NORMAL}"

# Change to script directory
echo -e "${COLOR_CYAN}Changing to script directory...${COLOR_NORMAL}"
cd "$(dirname "$0")"
echo -e "${COLOR_GREEN}Changed to: $(pwd)${COLOR_NORMAL}"

# Change to parent directory (repository root)
echo -e "${COLOR_CYAN}Changing to repository root directory...${COLOR_NORMAL}"
cd ..
echo -e "${COLOR_GREEN}Changed to: $(pwd)${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Setting Up Graphviz ====${COLOR_NORMAL}"

# Set the path to Graphviz - check Linux/WSL locations
GRAPHVIZ_DOT=""
if command -v dot &> /dev/null; then
    GRAPHVIZ_DOT=$(which dot)
    echo -e "${COLOR_GREEN}Found Graphviz at: ${GRAPHVIZ_DOT}${COLOR_NORMAL}"
else
    echo -e "${COLOR_YELLOW}WARNING: Could not find Graphviz dot in PATH.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Will attempt to use PlantUML's built-in Graphviz.${COLOR_NORMAL}"
fi

# Get script directory for absolute paths
SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"

# Check if PlantUML JAR exists
if [ ! -f "${SCRIPT_DIR}/plantuml.jar" ]; then
    echo -e "${COLOR_RED}ERROR: PlantUML JAR file not found at ${SCRIPT_DIR}/plantuml.jar${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please run download_plantuml.sh first.${COLOR_NORMAL}"
    exit 1
fi

# Set the absolute path to the JAR file
PLANTUML_JAR="${SCRIPT_DIR}/plantuml.jar"
echo -e "${COLOR_CYAN}Using Graphviz at: ${GRAPHVIZ_DOT}${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Using PlantUML at: ${PLANTUML_JAR}${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Generating ${FORMAT_NAME} Diagrams ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Searching for PlantUML files to convert...${COLOR_NORMAL}"

# Create a temp file to store results
temp_file=$(mktemp)

# Search for all .puml files in the current directory and all subdirectories
echo -e "${COLOR_CYAN}Looking for PlantUML files across the entire repository...${COLOR_NORMAL}"

# Find all .puml files in current directory and all subdirectories
find . -name "*.puml" >> "$temp_file" 2>/dev/null

# Count how many files we found
file_count=$(wc -l < "$temp_file")
file_count=$(echo $file_count | tr -d ' ') # Remove whitespace

# If we found no files, tell the user and exit
if [ "$file_count" -eq 0 ]; then
    echo -e "${COLOR_YELLOW}WARNING: No .puml files found in the repository.${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Tips for troubleshooting:${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}1. Check if your PlantUML files have a .puml extension${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}2. Try running the script from the repository root directory${COLOR_NORMAL}"
    rm "$temp_file" 2>/dev/null
    exit 0
fi

# Display found files
echo -e "${COLOR_GREEN}Found ${file_count} PlantUML files to process:${COLOR_NORMAL}"

# Show at most 5 files
display_count=0
while IFS= read -r file; do
    ((display_count++))
    if [ "$display_count" -le 5 ]; then
        echo -e "${COLOR_CYAN}  - $file${COLOR_NORMAL}"
    elif [ "$display_count" -eq 6 ]; then
        echo -e "${COLOR_CYAN}  ... and more files${COLOR_NORMAL}"
        break
    fi
done < "$temp_file"

echo
echo -e "${COLOR_CYAN}Running PlantUML to generate ${FORMAT_NAME} diagrams...${COLOR_NORMAL}"

# Process each file individually for better error handling
success_count=0
error_count=0

while IFS= read -r file; do
    echo -e "${COLOR_WHITE}Processing: $file${COLOR_NORMAL}"
    java -Xmx1024m -jar "$PLANTUML_JAR" -t"$FORMAT" "$file"
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to process $file${COLOR_NORMAL}"
        ((error_count++))
    else
        echo -e "${COLOR_GREEN}  SUCCESS: Generated ${FORMAT_NAME} for $file${COLOR_NORMAL}"
        ((success_count++))
    fi
done < "$temp_file"

# Clean up temporary file
rm "$temp_file" 2>/dev/null

if [ "$error_count" -gt 0 ]; then
    echo -e "${COLOR_RED}ERROR: PlantUML execution failed for $error_count files.${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_RED}An error occurred during the diagram generation process.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please check the error messages above and try again.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}PlantUML ${FORMAT_NAME} diagram generation completed successfully for all $success_count files.${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
    echo -e "${COLOR_GREEN}PLANTUML ${FORMAT_NAME} GENERATION COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
    echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_CYAN}${FORMAT_NAME} diagrams have been generated from your PlantUML files.${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}You can find them in the same directory as your .puml files.${COLOR_NORMAL}"
fi

echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

exit 0 