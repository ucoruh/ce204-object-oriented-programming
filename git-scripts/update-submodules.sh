#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.1"
SCRIPT_TITLE="Git Repository Submodule Update Utility v$SCRIPT_VERSION"

# Color configuration
COLOR_NORMAL="\033[0m"
COLOR_RED="\033[91m"
COLOR_GREEN="\033[92m"
COLOR_YELLOW="\033[93m"
COLOR_BLUE="\033[94m"
COLOR_MAGENTA="\033[95m"
COLOR_CYAN="\033[96m"
COLOR_WHITE="\033[97m"

# Initialize error flag
ERROR_OCCURRED=0

# Process command line arguments
FORCE_MODE=0
INTERACTIVE=0

function show_help {
    echo
    echo -e "${COLOR_BLUE}Usage: update-submodules.sh [options]${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_WHITE}Options:${COLOR_NORMAL}"
    echo -e "  ${COLOR_CYAN}-f, --force${COLOR_NORMAL}         Force update with safe method (no unrelated histories merge)"
    echo -e "  ${COLOR_CYAN}-i, --interactive${COLOR_NORMAL}   Interactive mode, prompt for options during operation"
    echo -e "  ${COLOR_CYAN}-h, --help${COLOR_NORMAL}          Show this help message"
    echo
    exit 0
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -f|--force)
            FORCE_MODE=1
            shift
            ;;
        -i|--interactive)
            INTERACTIVE=1
            shift
            ;;
        -h|--help)
            show_help
            ;;
        *)
            echo -e "${COLOR_RED}Unknown option: $1${COLOR_NORMAL}"
            show_help
            ;;
    esac
done

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
    ERROR_OCCURRED=1
    # Return to original directory
    cd "$currentDir"
    exit 1
fi

# Check if any submodules are configured
if [ ! -f ".gitmodules" ]; then
    echo -e "${COLOR_YELLOW}WARNING: No .gitmodules file found. There may be no submodules configured.${COLOR_NORMAL}"
    ERROR_OCCURRED=1
    cd "$currentDir"
    exit 1
fi

# Count submodules
SUBMODULE_COUNT=$(git config --file .gitmodules --get-regexp path 2>/dev/null | wc -l)
SUBMODULE_COUNT=${SUBMODULE_COUNT:-0}

if [ "$SUBMODULE_COUNT" -eq 0 ]; then
    echo -e "${COLOR_YELLOW}WARNING: No submodules defined in .gitmodules${COLOR_NORMAL}"
    ERROR_OCCURRED=1
    cd "$currentDir"
    exit 1
else
    echo -e "${COLOR_CYAN}Found ${SUBMODULE_COUNT} submodule(s) configured in this repository${COLOR_NORMAL}"
fi

# Find and delete desktop.ini files
echo
echo -e "${COLOR_BLUE}==== Cleaning desktop.ini Files ====${COLOR_NORMAL}"
found_files=0
deleted_files=0

# Find and delete desktop.ini files
echo -e "${COLOR_CYAN}Searching for desktop.ini files...${COLOR_NORMAL}"

# Use -type f -name to ensure we only find actual files
desktop_files=$(find . -type f -name 'desktop.ini' 2>/dev/null)

if [ -z "$desktop_files" ]; then
    echo -e "${COLOR_CYAN}  No desktop.ini files detected in this repository.${COLOR_NORMAL}"
else
    while IFS= read -r file; do
        # Check if file actually exists
        if [ -f "$file" ]; then
            ((found_files++))
            echo -e "  ${COLOR_WHITE}Found: $file${COLOR_NORMAL}"
            
            # Try to remove from Git if .git directory exists
            if [ -d ".git" ]; then
                if git rm --cached --force "$file" 2>/dev/null; then
                    echo -e "  ${COLOR_GREEN}REMOVED from Git tracking: $file${COLOR_NORMAL}"
                else
                    echo -e "  ${COLOR_YELLOW}NOTE: File not in Git repository or already untracked: $file${COLOR_NORMAL}"
                fi
            fi
            
            # Delete the file
            if rm -f "$file" 2>/dev/null; then
                echo -e "  ${COLOR_GREEN}DELETED: $file${COLOR_NORMAL}"
                ((deleted_files++))
            else
                echo -e "  ${COLOR_RED}ERROR: Failed to delete: $file${COLOR_NORMAL}"
            fi
        fi
    done <<< "$desktop_files"
fi

echo
echo -e "${COLOR_CYAN}Processed ${deleted_files} desktop.ini files${COLOR_NORMAL}"

# Update Git submodules
echo
echo -e "${COLOR_BLUE}==== Updating Git Submodules ====${COLOR_NORMAL}"

# Initialize submodules if not already done
echo -e "${COLOR_CYAN}Checking if submodules need to be initialized...${COLOR_NORMAL}"
if git submodule status 2>&1 | grep -q "No submodule mapping found"; then
    echo -e "${COLOR_YELLOW}Submodules not initialized. Running git submodule init...${COLOR_NORMAL}"
    git submodule init
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}ERROR: Failed to initialize submodules.${COLOR_NORMAL}"
        ERROR_OCCURRED=1
        cd "$currentDir"
        exit 1
    fi
fi

# Interactive mode
if [ $INTERACTIVE -eq 1 ]; then
    echo
    echo -e "${COLOR_CYAN}Interactive Mode: Choose update option:${COLOR_NORMAL}"
    echo -e "  ${COLOR_WHITE}1. Standard update (--remote --merge)${COLOR_NORMAL}"
    echo -e "  ${COLOR_WHITE}2. Safe update without merging unrelated histories (--init --recursive)${COLOR_NORMAL}"
    echo -e "  ${COLOR_WHITE}3. Reset and clean update (removes local changes)${COLOR_NORMAL}"
    echo -e "  ${COLOR_WHITE}4. Cancel operation${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_YELLOW}WARNING: Merging unrelated histories can cause conflicts and code issues${COLOR_NORMAL}"
    
    read -p "$(echo -e ${COLOR_CYAN}"Enter your choice (1-4): "${COLOR_NORMAL})" choice
    
    case $choice in
        1) UPDATE_MODE=1 ;;
        2) UPDATE_MODE=2 ;;
        3) UPDATE_MODE=3 ;;
        4) 
            echo -e "${COLOR_YELLOW}Operation canceled by user.${COLOR_NORMAL}"
            cd "$currentDir"
            exit 0
            ;;
        *)
            echo -e "${COLOR_RED}Invalid choice. Defaulting to safe update.${COLOR_NORMAL}"
            UPDATE_MODE=2
            ;;
    esac
elif [ $FORCE_MODE -eq 1 ]; then
    UPDATE_MODE=2
    echo -e "${COLOR_YELLOW}Force mode enabled - Using safe update method${COLOR_NORMAL}"
else
    UPDATE_MODE=1
fi

# Perform the update based on selected mode
UPDATE_ERROR=0

if [ $UPDATE_MODE -eq 1 ]; then
    echo -e "${COLOR_CYAN}Running standard update: git submodule update --remote --merge${COLOR_NORMAL}"
    git submodule update --remote --merge
    UPDATE_ERROR=$?
    
    if [ $UPDATE_ERROR -ne 0 ]; then
        echo -e "${COLOR_YELLOW}Standard update failed. Detected possible unrelated histories issue.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}Trying safer approach with --init --recursive...${COLOR_NORMAL}"
        git submodule update --init --recursive
        UPDATE_ERROR=$?
    fi
elif [ $UPDATE_MODE -eq 2 ]; then
    echo -e "${COLOR_CYAN}Running safe update: git submodule update --init --recursive${COLOR_NORMAL}"
    git submodule update --init --recursive
    UPDATE_ERROR=$?
elif [ $UPDATE_MODE -eq 3 ]; then
    echo -e "${COLOR_CYAN}Running reset and clean update (local changes will be lost)${COLOR_NORMAL}"
    
    # Need to handle each submodule individually for reset mode
    while IFS= read -r line; do
        if [[ -n "$line" ]]; then
            submodule_path=$(echo "$line" | awk '{print $2}')
            submodule_name=$(echo "$line" | sed 's/^submodule\.\(.*\)\.path .*/\1/')
            
            echo -e "${COLOR_WHITE}Resetting submodule: $submodule_name${COLOR_NORMAL}"
            echo -e "${COLOR_CYAN}  Submodule path: $submodule_path${COLOR_NORMAL}"
            
            # Check if the submodule directory exists
            if [ -d "$submodule_path" ]; then
                # Go into the submodule directory
                pushd "$submodule_path" > /dev/null
                
                if [ -d ".git" ] || [ -f ".git" ]; then
                    echo -e "${COLOR_CYAN}  Resetting submodule content...${COLOR_NORMAL}"
                    git fetch
                    git reset --hard origin/HEAD
                    git clean -fd
                    
                    if [ $? -ne 0 ]; then
                        echo -e "${COLOR_RED}  ERROR: Failed to reset submodule $submodule_name${COLOR_NORMAL}"
                        UPDATE_ERROR=1
                    else
                        echo -e "${COLOR_GREEN}  Successfully reset submodule $submodule_name${COLOR_NORMAL}"
                    fi
                else
                    echo -e "${COLOR_YELLOW}  Submodule .git not found, reinitializing...${COLOR_NORMAL}"
                    popd > /dev/null
                    git submodule deinit -f "$submodule_path"
                    git submodule update --init "$submodule_path"
                    
                    if [ $? -ne 0 ]; then
                        echo -e "${COLOR_RED}  ERROR: Failed to reinitialize submodule $submodule_path${COLOR_NORMAL}"
                        UPDATE_ERROR=1
                    fi
                    continue
                fi
                
                # Return to parent directory
                popd > /dev/null
            else
                echo -e "${COLOR_YELLOW}  Submodule directory not found, initializing...${COLOR_NORMAL}"
                git submodule update --init "$submodule_path"
                
                if [ $? -ne 0 ]; then
                    echo -e "${COLOR_RED}  ERROR: Failed to initialize submodule $submodule_path${COLOR_NORMAL}"
                    UPDATE_ERROR=1
                fi
            fi
            echo
        fi
    done < <(git config --file .gitmodules --get-regexp path)
fi

# Check for update errors
if [ $UPDATE_ERROR -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to update submodules. Error code: $UPDATE_ERROR${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}TIP: Try using interactive mode with reset option${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}     Run: ./update-submodules.sh -i${COLOR_NORMAL}"
    ERROR_OCCURRED=1
else
    echo -e "${COLOR_GREEN}SUCCESS: Submodules successfully updated.${COLOR_NORMAL}"
fi

# Return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
if [ $ERROR_OCCURRED -eq 1 ]; then
    echo -e "${COLOR_RED}OPERATION COMPLETED WITH ERRORS${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}OPERATION COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
fi
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
