#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.3"
SCRIPT_TITLE="Desktop.ini File Cleaner Utility v$SCRIPT_VERSION"

# Color configuration
COLOR_NORMAL="\033[0m"
COLOR_RED="\033[91m"
COLOR_GREEN="\033[92m"
COLOR_YELLOW="\033[93m"
COLOR_BLUE="\033[94m"
COLOR_MAGENTA="\033[95m"
COLOR_CYAN="\033[96m"
COLOR_WHITE="\033[97m"
COLOR_ORANGE="\033[38;5;208m"

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

# Check if .git directory exists
if [ ! -d ".git" ]; then
    echo -e "${COLOR_YELLOW}WARNING: This is not a Git repository. Will still remove desktop.ini files but not from Git tracking.${COLOR_NORMAL}"
fi

echo
echo -e "${COLOR_BLUE}==== Removing desktop.ini Files ====${COLOR_NORMAL}"

# Find and delete desktop.ini files, and remove them from Git index
echo -e "${COLOR_CYAN}Searching for desktop.ini files...${COLOR_NORMAL}"
found_files=0
deleted_files=0
removed_from_git=0
failed_to_delete=0

# Create a temporary file to store the list of desktop.ini files
temp_file=$(mktemp)

# Use find to search for desktop.ini files
find . -type f -name "desktop.ini" -print > "$temp_file" 2>/dev/null

# Count how many files we found
file_count=$(wc -l < "$temp_file")
file_count=$(echo "$file_count" | tr -d ' ') # Remove whitespace

if [ "$file_count" -eq 0 ]; then
    echo -e "${COLOR_CYAN}  No desktop.ini files detected in this repository.${COLOR_NORMAL}"
else
    echo -e "${COLOR_YELLOW}Found $file_count desktop.ini files to process.${COLOR_NORMAL}"
    
    # Process each desktop.ini file
    while IFS= read -r file; do
        # Check if file actually exists
        if [ -f "$file" ]; then
            ((found_files++))
            echo -e "  ${COLOR_WHITE}Found: $file${COLOR_NORMAL}"
            
            # Try to remove from Git if .git directory exists
            if [ -d ".git" ]; then
                if git ls-files --error-unmatch "$file" > /dev/null 2>&1; then
                    if git rm --cached --force "$file" 2>/dev/null; then
                        echo -e "  ${COLOR_GREEN}REMOVED from Git tracking: $file${COLOR_NORMAL}"
                        ((removed_from_git++))
                    else
                        echo -e "  ${COLOR_YELLOW}WARNING: Failed to remove from Git tracking: $file${COLOR_NORMAL}"
                    fi
                else
                    echo -e "  ${COLOR_YELLOW}NOTE: File not tracked by Git: $file${COLOR_NORMAL}"
                fi
            fi
            
            # Delete the file
            if rm -f "$file" 2>/dev/null; then
                echo -e "  ${COLOR_GREEN}DELETED: $file${COLOR_NORMAL}"
                ((deleted_files++))
            else
                echo -e "  ${COLOR_RED}ERROR: Failed to delete: $file${COLOR_NORMAL}"
                ((failed_to_delete++))
            fi
            echo
        fi
    done < "$temp_file"
fi

# Clean up temporary file
rm -f "$temp_file" 2>/dev/null

# Add desktop.ini to .gitignore if it exists
if [ -d ".git" ]; then
    echo
    echo -e "${COLOR_BLUE}==== Updating .gitignore ====${COLOR_NORMAL}"
    
    if [ -f ".gitignore" ]; then
        if grep -q "desktop.ini" .gitignore; then
            echo -e "${COLOR_GREEN}desktop.ini already in .gitignore${COLOR_NORMAL}"
        else
            echo -e "${COLOR_CYAN}Adding desktop.ini to .gitignore...${COLOR_NORMAL}"
            echo "# Windows Desktop.ini files" >> .gitignore
            echo "desktop.ini" >> .gitignore
            echo -e "${COLOR_GREEN}Added desktop.ini to .gitignore${COLOR_NORMAL}"
        fi
    else
        echo -e "${COLOR_CYAN}Creating .gitignore with desktop.ini entry...${COLOR_NORMAL}"
        echo "# Windows Desktop.ini files" > .gitignore
        echo "desktop.ini" >> .gitignore
        echo -e "${COLOR_GREEN}Created .gitignore with desktop.ini entry${COLOR_NORMAL}"
    fi
fi

# Display detailed summary
echo
echo -e "${COLOR_BLUE}==== Cleanup Summary ====${COLOR_NORMAL}"

if [ "$found_files" -eq 0 ]; then
    echo -e "${COLOR_YELLOW}No desktop.ini files were found in this repository.${COLOR_NORMAL}"
else
    echo -e "${COLOR_WHITE}Files Found:      ${COLOR_YELLOW}$found_files${COLOR_NORMAL}"
    echo -e "${COLOR_WHITE}Files Deleted:    ${COLOR_GREEN}$deleted_files${COLOR_NORMAL}"
    
    if [ -d ".git" ]; then
        echo -e "${COLOR_WHITE}Removed from Git: ${COLOR_CYAN}$removed_from_git${COLOR_NORMAL}"
    fi
    
    if [ "$failed_to_delete" -gt 0 ]; then
        echo -e "${COLOR_WHITE}Failed to Delete: ${COLOR_RED}$failed_to_delete${COLOR_NORMAL}"
    fi
    
    if [ "$deleted_files" -eq "$found_files" ]; then
        echo -e "${COLOR_GREEN}✓ All desktop.ini files successfully removed!${COLOR_NORMAL}"
    elif [ "$deleted_files" -eq 0 ]; then
        echo -e "${COLOR_RED}✗ Failed to delete any desktop.ini files.${COLOR_NORMAL}"
    else
        echo -e "${COLOR_YELLOW}⚠ Some desktop.ini files could not be deleted.${COLOR_NORMAL}"
    fi
fi

# Return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}DESKTOP.INI CLEANUP COMPLETED${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"

# Wait for user input before exiting
echo -e "Press any key to continue..."
read -n1 -s
echo
