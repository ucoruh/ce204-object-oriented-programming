#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="Git Repository Submodule Push Utility v$SCRIPT_VERSION"

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
PUSH_ALL=0
INTERACTIVE=0

function show_help {
    echo
    echo -e "${COLOR_BLUE}Usage: push-submodule-updates.sh [options]${COLOR_NORMAL}"
    echo
    echo -e "${COLOR_WHITE}Options:${COLOR_NORMAL}"
    echo -e "  ${COLOR_CYAN}-a, --all${COLOR_NORMAL}          Push all submodules, not just changed ones"
    echo -e "  ${COLOR_CYAN}-i, --interactive${COLOR_NORMAL}  Interactive mode, prompt for each submodule"
    echo -e "  ${COLOR_CYAN}-h, --help${COLOR_NORMAL}         Show this help message"
    echo
    exit 0
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -a|--all)
            PUSH_ALL=1
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
    echo -e "${COLOR_YELLOW}WARNING: No .gitmodules file found. There are no submodules configured.${COLOR_NORMAL}"
    ERROR_OCCURRED=1
    # Return to original directory
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

# Get list of modified submodules
echo
echo -e "${COLOR_BLUE}==== Checking Modified Submodules ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Looking for submodules with local changes...${COLOR_NORMAL}"

MODIFIED_COUNT=0
modified_modules=""

if [ $PUSH_ALL -eq 1 ]; then
    echo -e "${COLOR_CYAN}--all option specified, will process all submodules${COLOR_NORMAL}"
    modified_modules="all"
else
    # Check git status for modified submodules
    while IFS= read -r line; do
        if [[ -n "$line" ]]; then
            submodule_path=$(echo "$line" | awk '{print $2}')
            
            # Check if this entry in the index has changed
            if git diff --name-only --cached "$submodule_path" &>/dev/null; then
                echo -e "  ${COLOR_WHITE}Found modified submodule: $submodule_path${COLOR_NORMAL}"
                ((MODIFIED_COUNT++))
                modified_modules="$modified_modules $submodule_path"
            # Check if working tree has changes
            elif git diff --name-only "$submodule_path" &>/dev/null; then
                echo -e "  ${COLOR_WHITE}Found modified submodule: $submodule_path${COLOR_NORMAL}"
                ((MODIFIED_COUNT++))
                modified_modules="$modified_modules $submodule_path"
            fi
        fi
    done < <(git config --file .gitmodules --get-regexp path | sed 's/^submodule\.\(.*\)\.path \(.*\)/\2/')
fi

if [ "$modified_modules" != "all" ]; then
    if [ $MODIFIED_COUNT -eq 0 ]; then
        echo -e "${COLOR_YELLOW}No modified submodules found.${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}If you know there are changes, use --all to push all submodules.${COLOR_NORMAL}"
        cd "$currentDir"
        exit 0
    fi
fi

echo
echo -e "${COLOR_BLUE}==== Pushing Submodule Changes ====${COLOR_NORMAL}"

# Process each submodule for push
PUSHED_COUNT=0
FAILED_COUNT=0

while IFS= read -r line; do
    if [[ -n "$line" ]]; then
        submodule_path=$(echo "$line" | awk '{print $2}')
        submodule_name=$(echo "$line" | sed 's/^submodule\.\(.*\)\.path .*/\1/')
        
        process_this=0
        
        # Determine if we should process this submodule
        if [ "$modified_modules" = "all" ]; then
            process_this=1
        else
            if [[ "$modified_modules" == *"$submodule_path"* ]]; then
                process_this=1
            fi
        fi
        
        if [ $process_this -eq 1 ]; then
            echo -e "${COLOR_CYAN}Processing submodule: $submodule_path${COLOR_NORMAL}"
            
            # Check if submodule directory exists
            if [ -d "$submodule_path/.git" ] || [ -f "$submodule_path/.git" ]; then
                # Check if we should prompt user in interactive mode
                should_push=1
                if [ $INTERACTIVE -eq 1 ]; then
                    echo -e "  ${COLOR_WHITE}Submodule has changes. Push to remote?${COLOR_NORMAL}"
                    read -p "$(echo -e ${COLOR_CYAN}"  Push $submodule_path changes? (Y/N): "${COLOR_NORMAL})" choice
                    
                    if [[ "$choice" != "Y" && "$choice" != "y" ]]; then
                        should_push=0
                        echo -e "  ${COLOR_YELLOW}  Skipping $submodule_path as requested${COLOR_NORMAL}"
                    fi
                fi
                
                if [ $should_push -eq 1 ]; then
                    # Change to submodule directory
                    pushd "$submodule_path" > /dev/null
                    
                    # First, make sure we have all changes committed in the submodule
                    if ! git status | grep -q "nothing to commit"; then
                        echo -e "  ${COLOR_YELLOW}  WARNING: Uncommitted changes in $submodule_path${COLOR_NORMAL}"
                        echo -e "  ${COLOR_YELLOW}  Please commit your changes first before pushing${COLOR_NORMAL}"
                        ((FAILED_COUNT++))
                    else
                        # Get current branch
                        current_branch=$(git rev-parse --abbrev-ref HEAD)
                        echo -e "  ${COLOR_WHITE}  Current branch: $current_branch${COLOR_NORMAL}"
                        
                        # Push the changes
                        echo -e "  ${COLOR_CYAN}  Pushing submodule changes to remote...${COLOR_NORMAL}"
                        git push origin $current_branch
                        if [ $? -ne 0 ]; then
                            echo -e "  ${COLOR_RED}  ERROR: Failed to push $submodule_path${COLOR_NORMAL}"
                            ((FAILED_COUNT++))
                        else
                            echo -e "  ${COLOR_GREEN}  Successfully pushed $submodule_path${COLOR_NORMAL}"
                            ((PUSHED_COUNT++))
                        fi
                    fi
                    
                    # Return to parent directory
                    popd > /dev/null
                fi
            else
                echo -e "  ${COLOR_YELLOW}  Submodule directory does not exist or is not initialized${COLOR_NORMAL}"
                echo -e "  ${COLOR_YELLOW}  Run init-submodules.sh first${COLOR_NORMAL}"
                ((FAILED_COUNT++))
            fi
            echo
        fi
    fi
done < <(git config --file .gitmodules --get-regexp path)

# Now check if we need to update the main repository with new submodule references
if [ $PUSHED_COUNT -gt 0 ]; then
    echo -e "${COLOR_BLUE}==== Updating Main Repository ====${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Checking if main repository needs updates...${COLOR_NORMAL}"
    
    # Get status of the submodules in the main repo
    main_repo_changes=0
    if git status -s | grep -q "^M "; then
        main_repo_changes=1
    fi
    
    if [ $main_repo_changes -eq 1 ]; then
        echo -e "${COLOR_CYAN}Main repository has updated submodule references${COLOR_NORMAL}"
        
        should_commit=1
        if [ $INTERACTIVE -eq 1 ]; then
            read -p "$(echo -e ${COLOR_CYAN}"Commit and push updated submodule references? (Y/N): "${COLOR_NORMAL})" choice
            
            if [[ "$choice" != "Y" && "$choice" != "y" ]]; then
                should_commit=0
                echo -e "${COLOR_YELLOW}Skipping main repository update as requested${COLOR_NORMAL}"
            fi
        fi
        
        if [ $should_commit -eq 1 ]; then
            echo -e "${COLOR_CYAN}Committing submodule reference updates...${COLOR_NORMAL}"
            git add -u
            git commit -m "Update submodule references"
            if [ $? -ne 0 ]; then
                echo -e "${COLOR_RED}ERROR: Failed to commit submodule reference updates${COLOR_NORMAL}"
                ERROR_OCCURRED=1
            else
                echo -e "${COLOR_GREEN}Successfully committed submodule reference updates${COLOR_NORMAL}"
                
                echo -e "${COLOR_CYAN}Pushing updates to remote...${COLOR_NORMAL}"
                git push
                if [ $? -ne 0 ]; then
                    echo -e "${COLOR_RED}ERROR: Failed to push main repository updates${COLOR_NORMAL}"
                    ERROR_OCCURRED=1
                else
                    echo -e "${COLOR_GREEN}Successfully pushed main repository updates${COLOR_NORMAL}"
                fi
            fi
        fi
    else
        echo -e "${COLOR_YELLOW}No submodule reference changes detected in main repository${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}If you just pushed submodule changes, you might need to:${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}1. git add <submodule-path>${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}2. git commit -m \"Update submodule references\"${COLOR_NORMAL}"
        echo -e "${COLOR_YELLOW}3. git push${COLOR_NORMAL}"
    fi
fi

# Display summary
echo
echo -e "${COLOR_BLUE}==== Summary ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Submodules processed: $MODIFIED_COUNT${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}Successfully pushed: $PUSHED_COUNT${COLOR_NORMAL}"
if [ $FAILED_COUNT -gt 0 ]; then
    echo -e "${COLOR_RED}Failed to push: $FAILED_COUNT${COLOR_NORMAL}"
    ERROR_OCCURRED=1
fi

# Return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
if [ $ERROR_OCCURRED -eq 1 ]; then
    echo -e "${COLOR_RED}OPERATION COMPLETED WITH WARNINGS OR ERRORS${COLOR_NORMAL}"
else
    echo -e "${COLOR_GREEN}OPERATION COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
fi
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}" 