#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="Unix2Dos Downloader and Checker Utility v${SCRIPT_VERSION}"

# ANSI color configuration
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
echo -e "${COLOR_CYAN}This script checks if unix2dos/dos2unix is installed on your system.${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}If not, it will install it using your system's package manager.${COLOR_NORMAL}"
echo

# Set working directory
SCRIPT_DIR="$(dirname "$0")"
ROOT_DIR="$(dirname "$SCRIPT_DIR")"
cd "$ROOT_DIR"
echo -e "${COLOR_CYAN}Changed to repository root directory: $(pwd)${COLOR_NORMAL}"

# Initialize variables
UNIX2DOS_CMD=""
DOS2UNIX_CMD=""
NEED_INSTALL=0

echo
echo -e "${COLOR_BLUE}==== Checking for Existing Installation ====${COLOR_NORMAL}"

# Check if unix2dos is already installed
echo -e "${COLOR_CYAN}Checking if unix2dos is already installed...${COLOR_NORMAL}"
if command -v unix2dos >/dev/null 2>&1; then
    echo -e "${COLOR_GREEN}unix2dos found in system PATH.${COLOR_NORMAL}"
    
    # Test if it works
    unix2dos --version >/dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo -e "${COLOR_GREEN}Verified unix2dos is working properly.${COLOR_NORMAL}"
        UNIX2DOS_CMD="unix2dos"
        
        # Show version info
        VERSION_INFO=$(unix2dos --version 2>&1 | head -n 1)
        echo -e "${COLOR_CYAN}Version: ${VERSION_INFO}${COLOR_NORMAL}"
    else
        echo -e "${COLOR_YELLOW}WARNING: unix2dos command found but may not be working properly.${COLOR_NORMAL}"
        NEED_INSTALL=1
    fi
else
    echo -e "${COLOR_YELLOW}unix2dos is not found in system PATH.${COLOR_NORMAL}"
    NEED_INSTALL=1
fi

# Check if dos2unix is already installed
echo -e "${COLOR_CYAN}Checking if dos2unix is already installed...${COLOR_NORMAL}"
if command -v dos2unix >/dev/null 2>&1; then
    echo -e "${COLOR_GREEN}dos2unix found in system PATH.${COLOR_NORMAL}"
    DOS2UNIX_CMD="dos2unix"
    
    # Show version info
    VERSION_INFO=$(dos2unix --version 2>&1 | head -n 1)
    echo -e "${COLOR_CYAN}Version: ${VERSION_INFO}${COLOR_NORMAL}"
else
    echo -e "${COLOR_YELLOW}dos2unix is not found in system PATH.${COLOR_NORMAL}"
    NEED_INSTALL=1
fi

# Ask user if they want to reinstall even if tools are already available
INSTALL_NOW=0
if [ $NEED_INSTALL -eq 0 ]; then
    echo
    echo -e "${COLOR_BLUE}==== Installation Options ====${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}unix2dos and dos2unix are already available on your system.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Would you like to reinstall anyway? (y/n)${COLOR_NORMAL}"
    read -n 1 -r REPLY
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        echo -e "${COLOR_YELLOW}You chose to reinstall the tools.${COLOR_NORMAL}"
        INSTALL_NOW=1
    else
        echo -e "${COLOR_GREEN}You chose to use the existing tools.${COLOR_NORMAL}"
    fi
fi

# If tools not found or reinstall requested, install them
if [ $NEED_INSTALL -eq 1 ] || [ $INSTALL_NOW -eq 1 ]; then
    echo
    echo -e "${COLOR_BLUE}==== Installing Tools ====${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Setting up installation parameters...${COLOR_NORMAL}"
    
    # Detect package manager and install
    INSTALL_STATUS=1
    
    if command -v apt-get >/dev/null 2>&1; then
        echo -e "${COLOR_CYAN}Using apt-get to install dos2unix package...${COLOR_NORMAL}"
        sudo apt-get update && sudo apt-get install -y dos2unix
        INSTALL_STATUS=$?
    elif command -v yum >/dev/null 2>&1; then
        echo -e "${COLOR_CYAN}Using yum to install dos2unix package...${COLOR_NORMAL}"
        sudo yum install -y dos2unix
        INSTALL_STATUS=$?
    elif command -v dnf >/dev/null 2>&1; then
        echo -e "${COLOR_CYAN}Using dnf to install dos2unix package...${COLOR_NORMAL}"
        sudo dnf install -y dos2unix
        INSTALL_STATUS=$?
    elif command -v pacman >/dev/null 2>&1; then
        echo -e "${COLOR_CYAN}Using pacman to install dos2unix package...${COLOR_NORMAL}"
        sudo pacman -S --noconfirm dos2unix
        INSTALL_STATUS=$?
    elif command -v brew >/dev/null 2>&1; then
        echo -e "${COLOR_CYAN}Using Homebrew to install dos2unix package...${COLOR_NORMAL}"
        brew install dos2unix
        INSTALL_STATUS=$?
    else
        echo -e "${COLOR_RED}ERROR: Could not determine package manager to install tools.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}Please install dos2unix tools manually with your package manager.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}For example: sudo apt install dos2unix${COLOR_NORMAL}"
        exit 1
    fi
    
    # Check if installation was successful
    if [ $INSTALL_STATUS -ne 0 ]; then
        echo -e "${COLOR_RED}ERROR: Failed to install dos2unix package.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}You may need to run this script with sudo privileges or manually install:${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}sudo apt install dos2unix${COLOR_NORMAL}"
        exit 1
    else
        echo -e "${COLOR_GREEN}Successfully installed dos2unix package.${COLOR_NORMAL}"
    fi
    
    # Verify tools are now available
    echo -e "${COLOR_CYAN}Verifying tools are now available...${COLOR_NORMAL}"
    
    # Check dos2unix
    if command -v dos2unix >/dev/null 2>&1; then
        echo -e "${COLOR_GREEN}dos2unix is now installed and available.${COLOR_NORMAL}"
        DOS2UNIX_CMD="dos2unix"
        
        # Show version
        VERSION_INFO=$(dos2unix --version 2>&1 | head -n 1)
        echo -e "${COLOR_CYAN}Version: ${VERSION_INFO}${COLOR_NORMAL}"
    else
        echo -e "${COLOR_RED}ERROR: dos2unix still not available after installation.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}You may need to restart your terminal or update your PATH.${COLOR_NORMAL}"
        exit 1
    fi
    
    # Check unix2dos
    if command -v unix2dos >/dev/null 2>&1; then
        echo -e "${COLOR_GREEN}unix2dos is now installed and available.${COLOR_NORMAL}"
        UNIX2DOS_CMD="unix2dos"
        
        # Show version
        VERSION_INFO=$(unix2dos --version 2>&1 | head -n 1)
        echo -e "${COLOR_CYAN}Version: ${VERSION_INFO}${COLOR_NORMAL}"
    else
        echo -e "${COLOR_RED}ERROR: unix2dos still not available after installation.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}You may need to restart your terminal or update your PATH.${COLOR_NORMAL}"
        exit 1
    fi
fi

echo
echo -e "${COLOR_BLUE}==== Status Summary ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Tool locations:${COLOR_NORMAL}"

if [ -n "$UNIX2DOS_CMD" ]; then
    UNIX2DOS_PATH=$(which unix2dos)
    echo -e "${COLOR_GREEN}- unix2dos: ${UNIX2DOS_PATH}${COLOR_NORMAL}"
else
    echo -e "${COLOR_RED}- unix2dos: Not found${COLOR_NORMAL}"
fi

if [ -n "$DOS2UNIX_CMD" ]; then
    DOS2UNIX_PATH=$(which dos2unix)
    echo -e "${COLOR_GREEN}- dos2unix: ${DOS2UNIX_PATH}${COLOR_NORMAL}"
else
    echo -e "${COLOR_RED}- dos2unix: Not found${COLOR_NORMAL}"
fi

# Return success/failure based on tool availability
if [ -n "$UNIX2DOS_CMD" ] && [ -n "$DOS2UNIX_CMD" ]; then
    echo
    echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
    echo -e "${COLOR_GREEN}UNIX2DOS/DOS2UNIX SETUP COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
    echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_CYAN}You can now convert text file line endings between Unix and Windows formats.${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}- Use unix2dos to convert from Unix to Windows format${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}- Use dos2unix to convert from Windows to Unix format${COLOR_NORMAL}"
    
    echo
    echo -e "Press Enter to continue..."
    read -r
    
    exit 0
else
    echo
    echo -e "${COLOR_RED}ERROR: Could not ensure all required tools are available.${COLOR_NORMAL}"
    exit 1
fi 