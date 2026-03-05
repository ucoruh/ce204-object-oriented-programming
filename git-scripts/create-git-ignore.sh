#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.1"
SCRIPT_TITLE="Git Ignore File Generator Utility v$SCRIPT_VERSION"

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
echo -e "${COLOR_GREEN}Changed to: $(pwd)${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Generating .gitignore File ====${COLOR_NORMAL}"

# Create a temporary file for download
TEMP_FILE=$(mktemp)

# API URL for GitHub Gitignore templates 
# Note: URL is correctly escaped for shell
API_URL="https://www.toptal.com/developers/gitignore/api/c,csharp,visualstudio,visualstudiocode,java,maven,c++,cmake,eclipse,netbeans"

# Set the output file name
OUTPUT_FILE=".gitignore"

echo -e "${COLOR_CYAN}Using Toptal GitIgnore API to generate file.${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Template includes: C, C#, Visual Studio, VS Code, Java, Maven, C++, CMake, Eclipse, NetBeans${COLOR_NORMAL}"

# Check if .gitignore already exists
if [ -f "$OUTPUT_FILE" ]; then
    echo -e "${COLOR_YELLOW}WARNING: .gitignore file already exists.${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Do you want to:${COLOR_NORMAL}"
    echo -e "  ${COLOR_WHITE}1. Overwrite the existing file${COLOR_NORMAL}"
    echo -e "  ${COLOR_WHITE}2. Skip generation and keep existing file${COLOR_NORMAL}"
    
    read -p "$(echo -e ${COLOR_CYAN}"Enter your choice (1-2): "${COLOR_NORMAL})" choice
    if [ "$choice" != "1" ]; then
        echo -e "${COLOR_YELLOW}Keeping existing .gitignore file.${COLOR_NORMAL}"
        # Clean up
        rm -f "$TEMP_FILE"
        
        # Return to original directory
        echo
        echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
        cd "$currentDir"
        
        echo
        echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
        echo -e "${COLOR_GREEN}GITIGNORE SETUP COMPLETED (NO CHANGES)${COLOR_NORMAL}"
        echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
        exit 0
    else
        echo -e "${COLOR_CYAN}Overwriting existing .gitignore file...${COLOR_NORMAL}"
    fi
fi

echo -e "${COLOR_CYAN}Downloading .gitignore template from API...${COLOR_NORMAL}"

# Download the API results using curl to a temporary file with proper error handling
if ! curl -s -o "$TEMP_FILE" "$API_URL"; then
    echo -e "${COLOR_RED}ERROR: Failed to download .gitignore file from API.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please check your internet connection or try again later.${COLOR_NORMAL}"
    rm -f "$TEMP_FILE"
    cd "$currentDir"
    exit 1
fi

# Verify the temp file was created successfully
if [ ! -f "$TEMP_FILE" ] || [ ! -s "$TEMP_FILE" ]; then
    echo -e "${COLOR_RED}ERROR: Failed to download to temporary file.${COLOR_NORMAL}"
    rm -f "$TEMP_FILE"
    cd "$currentDir"
    exit 1
fi

# Get file size to verify content
if command -v stat &> /dev/null; then
    if stat --version &> /dev/null; then
        # GNU stat
        size=$(stat -c%s "$TEMP_FILE" 2>/dev/null)
    else
        # BSD stat (macOS)
        size=$(stat -f%z "$TEMP_FILE" 2>/dev/null)
    fi
else
    # Fallback if stat is not available
    size=$(wc -c < "$TEMP_FILE" 2>/dev/null)
fi

# Create the output file with header
echo "# Generated with Git Repository Preparer" > "$OUTPUT_FILE"
echo "# Template includes C, C#, Visual Studio, VS Code, Java, Maven, C++, CMake, Eclipse, NetBeans" >> "$OUTPUT_FILE"
echo "# Generated on: $(date)" >> "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"

# Append the downloaded content
cat "$TEMP_FILE" >> "$OUTPUT_FILE"

# Add desktop.ini entry at the end
echo "" >> "$OUTPUT_FILE"
echo "# Windows desktop.ini files" >> "$OUTPUT_FILE"
echo "**/desktop.ini" >> "$OUTPUT_FILE"
echo "desktop.ini" >> "$OUTPUT_FILE"

# Clean up the temporary file
rm -f "$TEMP_FILE" 2>/dev/null

# Check final result size
if command -v stat &> /dev/null; then
    if stat --version &> /dev/null; then
        # GNU stat
        final_size=$(stat -c%s "$OUTPUT_FILE" 2>/dev/null)
    else
        # BSD stat (macOS)
        final_size=$(stat -f%z "$OUTPUT_FILE" 2>/dev/null)
    fi
else
    # Fallback if stat is not available
    final_size=$(wc -c < "$OUTPUT_FILE" 2>/dev/null)
fi

if [ "$final_size" -lt 100 ]; then
    echo -e "${COLOR_RED}WARNING: Generated .gitignore file is suspiciously small (${final_size} bytes).${COLOR_NORMAL}"
    echo -e "${COLOR_RED}It may not have been created correctly.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}SUCCESS: Generated .gitignore file (${final_size} bytes).${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Generated .gitignore file at: $(pwd)/${OUTPUT_FILE}${COLOR_NORMAL}"
fi

# Return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}GITIGNORE SETUP COMPLETED${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
