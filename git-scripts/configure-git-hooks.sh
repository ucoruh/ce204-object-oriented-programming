#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="Git Hooks Configuration Utility v${SCRIPT_VERSION}"

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

# Save current directory
CURRENT_DIR=$(pwd)
echo -e "${COLOR_CYAN}Current directory saved: ${CURRENT_DIR}${COLOR_NORMAL}"

# Change to script directory and then parent folder
echo -e "${COLOR_CYAN}Changing to repository root directory...${COLOR_NORMAL}"
cd "$(dirname "$0")"
cd ..

# Check if .git directory exists
if [ ! -d ".git" ]; then
    echo -e "${COLOR_RED}ERROR: This is not a Git repository. Please run this script from a Git repository.${COLOR_NORMAL}"
    cd "$CURRENT_DIR"
    exit 1
fi

# Set the path to the .git/hooks directory
HOOKS_DIR=".git/hooks"
echo -e "${COLOR_CYAN}Hooks directory: ${HOOKS_DIR}${COLOR_NORMAL}"

# Check if .git/hooks directory exists
if [ ! -d "$HOOKS_DIR" ]; then
    echo -e "${COLOR_YELLOW}WARNING: Hooks directory not found. Creating ${HOOKS_DIR} directory...${COLOR_NORMAL}"
    mkdir -p "$HOOKS_DIR" 2>/dev/null
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}ERROR: Failed to create hooks directory.${COLOR_NORMAL}"
        cd "$CURRENT_DIR"
        exit 1
    else
        echo -e "${COLOR_GREEN}SUCCESS: Created hooks directory.${COLOR_NORMAL}"
    fi
fi

echo
echo -e "${COLOR_BLUE}==== Configuring Git Hooks ====${COLOR_NORMAL}"

# Backup and install pre-commit hook
echo -e "${COLOR_CYAN}Configuring pre-commit hook...${COLOR_NORMAL}"
if [ -f "${HOOKS_DIR}/pre-commit" ]; then
    echo -e "${COLOR_YELLOW}  Backing up existing pre-commit hook...${COLOR_NORMAL}"
    if [ -f "${HOOKS_DIR}/pre-commit.backup" ]; then
        rm -f "${HOOKS_DIR}/pre-commit.backup" 2>/dev/null
    fi
    mv "${HOOKS_DIR}/pre-commit" "${HOOKS_DIR}/pre-commit.backup" 2>/dev/null
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to backup existing pre-commit hook.${COLOR_NORMAL}"
    else
        echo -e "${COLOR_GREEN}  Backed up existing pre-commit hook to pre-commit.backup${COLOR_NORMAL}"
    fi
fi

echo -e "${COLOR_CYAN}  Installing new pre-commit hook...${COLOR_NORMAL}"
cp -f "$(pwd)/git-scripts/pre-commit" "${HOOKS_DIR}/pre-commit" 2>/dev/null
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}  ERROR: Failed to install pre-commit hook.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}  SUCCESS: Installed pre-commit hook.${COLOR_NORMAL}"
fi

# Backup and install pre-push hook
echo
echo -e "${COLOR_CYAN}Configuring pre-push hook...${COLOR_NORMAL}"
if [ -f "${HOOKS_DIR}/pre-push" ]; then
    echo -e "${COLOR_YELLOW}  Backing up existing pre-push hook...${COLOR_NORMAL}"
    if [ -f "${HOOKS_DIR}/pre-push.backup" ]; then
        rm -f "${HOOKS_DIR}/pre-push.backup" 2>/dev/null
    fi
    mv "${HOOKS_DIR}/pre-push" "${HOOKS_DIR}/pre-push.backup" 2>/dev/null
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to backup existing pre-push hook.${COLOR_NORMAL}"
    else
        echo -e "${COLOR_GREEN}  Backed up existing pre-push hook to pre-push.backup${COLOR_NORMAL}"
    fi
fi

echo -e "${COLOR_CYAN}  Installing new pre-push hook...${COLOR_NORMAL}"
cp -f "$(pwd)/git-scripts/pre-push" "${HOOKS_DIR}/pre-push" 2>/dev/null
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}  ERROR: Failed to install pre-push hook.${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}  SUCCESS: Installed pre-push hook.${COLOR_NORMAL}"
fi

# Set execute permission on hooks
echo
echo -e "${COLOR_CYAN}Setting executable permissions...${COLOR_NORMAL}"
chmod +x "${HOOKS_DIR}/pre-commit" 2>/dev/null
chmod +x "${HOOKS_DIR}/pre-push" 2>/dev/null
echo -e "${COLOR_GREEN}Permissions set.${COLOR_NORMAL}"

# Clean up and return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${CURRENT_DIR}${COLOR_NORMAL}"
cd "$CURRENT_DIR"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}GIT HOOKS CONFIGURATION COMPLETED${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo