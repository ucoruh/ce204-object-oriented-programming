#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="Git Repository Submodule Initialization Utility v$SCRIPT_VERSION"

# Color configuration
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
cd "$(dirname "$(readlink -f "$0")")"
cd ..

# Check if .git directory exists
if [ ! -d ".git" ]; then
    echo -e "${COLOR_RED}ERROR: This is not a Git repository. Please run this script from a Git repository.${COLOR_NORMAL}"
    # Return to original directory
    cd "$currentDir"
    exit 1
fi

# Check if any submodules are configured
if [ ! -f ".gitmodules" ]; then
    echo -e "${COLOR_YELLOW}WARNING: No .gitmodules file found. There may be no submodules configured.${COLOR_NORMAL}"
fi

echo
echo -e "${COLOR_BLUE}==== Cleaning desktop.ini Files ====${COLOR_NORMAL}"
found_files=0

# Delete all desktop.ini files and remove them from Git index
echo -e "${COLOR_CYAN}Searching for desktop.ini files...${COLOR_NORMAL}"

find . -name 'desktop.ini' -print0 2>/dev/null | while IFS= read -r -d '' file; do
    if [ -f "$file" ]; then
        echo -e "  ${COLOR_WHITE}Found: ${file}${COLOR_NORMAL}"
        
        # Remove from Git tracking
        git rm --cached --force "$file" >/dev/null 2>&1
        if [ $? -ne 0 ]; then
            echo -e "  ${COLOR_RED}ERROR: Failed to remove from Git: ${file}${COLOR_NORMAL}"
        else
            echo -e "  ${COLOR_GREEN}REMOVED from Git tracking: ${file}${COLOR_NORMAL}"
        fi
        
        # Delete the file
        rm -f "$file" >/dev/null 2>&1
        if [ $? -ne 0 ]; then
            echo -e "  ${COLOR_RED}ERROR: Failed to delete: ${file}${COLOR_NORMAL}"
        else
            echo -e "  ${COLOR_GREEN}DELETED: ${file}${COLOR_NORMAL}"
            ((found_files++))
        fi
    fi
done

echo
echo -e "${COLOR_CYAN}Processed approximately ${found_files} desktop.ini files${COLOR_NORMAL}"

# Initialize and update Git submodules
echo
echo -e "${COLOR_BLUE}==== Initializing Git Submodules ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Running git submodule update --init --recursive${COLOR_NORMAL}"
git submodule update --init --recursive
if [ $? -eq 0 ]; then
    echo -e "${COLOR_GREEN}SUCCESS: Submodules successfully initialized and updated.${COLOR_NORMAL}"
else
    echo -e "${COLOR_RED}ERROR: Failed to initialize submodules. Please check your Git configuration.${COLOR_NORMAL}"
    # Return to original directory
    cd "$currentDir"
    exit 1
fi

# Return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}OPERATION COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
