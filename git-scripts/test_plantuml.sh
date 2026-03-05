#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="PlantUML Test Utility v${SCRIPT_VERSION}"

# ========== COLOR CONFIGURATION ==========
# ANSI color codes
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

# Save the current directory
currentDir=$(pwd)
echo -e "${COLOR_CYAN}Current directory saved: ${currentDir}${COLOR_NORMAL}"

# Change to script directory and then parent folder
echo -e "${COLOR_CYAN}Changing to repository root directory...${COLOR_NORMAL}"
cd "$(dirname "$0")"
cd ..

# Set the Graphviz environment variable
echo -e "${COLOR_BLUE}==== Checking Graphviz Installation ====${COLOR_NORMAL}"
export GRAPHVIZ_DOT=$(which dot 2>/dev/null)

if [ -z "$GRAPHVIZ_DOT" ]; then
    echo -e "${COLOR_YELLOW}Warning: Graphviz (dot) is not found in PATH.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Some PlantUML diagrams requiring dot may not render correctly.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Consider installing Graphviz using your package manager.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}Found Graphviz at: ${GRAPHVIZ_DOT}${COLOR_NORMAL}"
fi

# Check if PlantUML JAR exists
echo
echo -e "${COLOR_BLUE}==== Checking PlantUML Installation ====${COLOR_NORMAL}"
if [ ! -f "plantuml.jar" ]; then
    echo -e "${COLOR_RED}Error: plantuml.jar not found in the current directory.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please run the download_plantuml.sh script first.${COLOR_NORMAL}"
    cd "$currentDir"
    exit 1
else
    echo -e "${COLOR_GREEN}Found PlantUML JAR file.${COLOR_NORMAL}"
fi

# Run PlantUML to test dot configuration
echo
echo -e "${COLOR_BLUE}==== Testing PlantUML Configuration ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Running PlantUML test...${COLOR_NORMAL}"
java -jar "plantuml.jar" -v -testdot

# Check if test command was successful
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}Error: PlantUML test failed.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please check the error messages above.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}PlantUML test completed successfully.${COLOR_NORMAL}"
fi

# Revert to the original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}PLANTUML TEST COMPLETED${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo

echo -e "Press Enter to continue..."
read -r
