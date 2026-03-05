#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.3"
SCRIPT_TITLE="Git Repository Synchronization Check Utility v$SCRIPT_VERSION"

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
COLOR_LIGHT_BLUE="\033[94m"
COLOR_LIGHT_GREEN="\033[92m"
COLOR_GRAY="\033[90m"

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

# Check if .git directory exists
if [ ! -d ".git" ]; then
    echo -e "${COLOR_RED}ERROR: This is not a Git repository. Please run this script from a Git repository.${COLOR_NORMAL}"
    # Return to original directory
    cd "$currentDir"
    exit 1
fi

echo
echo -e "${COLOR_BLUE}==== Cleaning desktop.ini Files ====${COLOR_NORMAL}"
found_files=0

# Delete all desktop.ini files
echo -e "${COLOR_CYAN}Searching for desktop.ini files...${COLOR_NORMAL}"
find . -type f -name "desktop.ini" -print0 2>/dev/null | while IFS= read -r -d '' file; do
    echo -e "  ${COLOR_WHITE}Found: ${file}${COLOR_NORMAL}"
    
    # Try to remove from Git tracking
    git rm --cached --force "$file" >/dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo -e "  ${COLOR_GREEN}REMOVED from Git tracking: ${file}${COLOR_NORMAL}"
    fi
    
    # Delete the file
    rm -f "$file" >/dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo -e "  ${COLOR_GREEN}DELETED: ${file}${COLOR_NORMAL}"
        ((found_files++))
    else
        echo -e "  ${COLOR_RED}ERROR: Failed to delete: ${file}${COLOR_NORMAL}"
    fi
done

echo -e "${COLOR_CYAN}Processed approximately ${found_files} desktop.ini files${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Checking Git Repository Status ====${COLOR_NORMAL}"

# Check for uncommitted changes and list them if found
echo -e "${COLOR_CYAN}Checking for uncommitted changes...${COLOR_NORMAL}"

# Count different types of changes without using temporary files
has_changes=0
modified_count=0
added_count=0
deleted_count=0
untracked_count=0
renamed_count=0

# Create arrays to store filenames by category
declare -a modified_files=()
declare -a added_files=()
declare -a deleted_files=()
declare -a untracked_files=()
declare -a renamed_files=()

# Process git status output directly
while IFS= read -r line; do
    if [[ -n "$line" ]]; then
        has_changes=1
        status="${line:0:2}"
        filename="${line:3}"
        
        # Skip temp_status.txt from counting if it exists
        if [[ "$filename" != "temp_status.txt" ]]; then
            if [[ "${status:0:1}" == "M" || "${status:1:1}" == "M" ]]; then
                ((modified_count++))
                modified_files+=("$filename")
            elif [[ "${status:0:1}" == "A" || "${status:1:1}" == "A" ]]; then
                ((added_count++))
                added_files+=("$filename")
            elif [[ "${status:0:1}" == "D" || "${status:1:1}" == "D" ]]; then
                ((deleted_count++))
                deleted_files+=("$filename")
            elif [[ "$status" == "??" ]]; then
                ((untracked_count++))
                untracked_files+=("$filename")
            elif [[ "${status:0:1}" == "R" ]]; then
                ((renamed_count++))
                renamed_files+=("$filename")
            fi
        fi
    fi
done < <(git status --porcelain 2>/dev/null)

if [ "$has_changes" -eq 1 ]; then
    echo -e "${COLOR_YELLOW}WARNING: You have uncommitted changes${COLOR_NORMAL}"
    
    # Display summary of changes
    echo -e "  ${COLOR_WHITE}Summary of Changes:${COLOR_NORMAL}"
    [ $modified_count -gt 0 ] && echo -e "  ${COLOR_ORANGE}Modified files: $modified_count${COLOR_NORMAL}"
    [ $added_count -gt 0 ] && echo -e "  ${COLOR_GREEN}Added files: $added_count${COLOR_NORMAL}"
    [ $deleted_count -gt 0 ] && echo -e "  ${COLOR_RED}Deleted files: $deleted_count${COLOR_NORMAL}"
    [ $untracked_count -gt 0 ] && echo -e "  ${COLOR_CYAN}Untracked files: $untracked_count${COLOR_NORMAL}"
    [ $renamed_count -gt 0 ] && echo -e "  ${COLOR_BLUE}Renamed files: $renamed_count${COLOR_NORMAL}"
    
    echo
    echo -e "  ${COLOR_WHITE}Changes by Category:${COLOR_NORMAL}"
    
    # Display modified files - IMPROVED METHOD
    if [ $modified_count -gt 0 ]; then
        echo -e "  ${COLOR_ORANGE}Modified Files:${COLOR_NORMAL}"
        
        # Use a fixed limit for how many files to display
        max_display=15
        display_count=0
        
        for file in "${modified_files[@]}"; do
            ((display_count++))
            if [ $display_count -le $max_display ]; then
                echo -e "    ${COLOR_ORANGE}- $file${COLOR_NORMAL}"
            else
                break
            fi
        done
        
        # Show message if we have more files than the display limit
        if [ $modified_count -gt $max_display ]; then
            remaining=$((modified_count - max_display))
            echo -e "    ${COLOR_ORANGE}... and $remaining more files${COLOR_NORMAL}"
        fi
    fi
    
    # Display added files - IMPROVED METHOD
    if [ $added_count -gt 0 ]; then
        echo -e "  ${COLOR_GREEN}Added Files:${COLOR_NORMAL}"
        
        # Use a fixed limit for how many files to display
        max_display=15
        display_count=0
        
        for file in "${added_files[@]}"; do
            ((display_count++))
            if [ $display_count -le $max_display ]; then
                echo -e "    ${COLOR_GREEN}+ $file${COLOR_NORMAL}"
            else
                break
            fi
        done
        
        # Show message if we have more files than the display limit
        if [ $added_count -gt $max_display ]; then
            remaining=$((added_count - max_display))
            echo -e "    ${COLOR_GREEN}... and $remaining more files${COLOR_NORMAL}"
        fi
    fi
    
    # Display deleted files - IMPROVED METHOD
    if [ $deleted_count -gt 0 ]; then
        echo -e "  ${COLOR_RED}Deleted Files:${COLOR_NORMAL}"
        
        # Use a fixed limit for how many files to display
        max_display=15
        display_count=0
        
        for file in "${deleted_files[@]}"; do
            ((display_count++))
            if [ $display_count -le $max_display ]; then
                echo -e "    ${COLOR_RED}- $file${COLOR_NORMAL}"
            else
                break
            fi
        done
        
        # Show message if we have more files than the display limit
        if [ $deleted_count -gt $max_display ]; then
            remaining=$((deleted_count - max_display))
            echo -e "    ${COLOR_RED}... and $remaining more files${COLOR_NORMAL}"
        fi
    fi
    
    # Display untracked files - IMPROVED METHOD
    if [ $untracked_count -gt 0 ]; then
        echo -e "  ${COLOR_CYAN}Untracked Files:${COLOR_NORMAL}"
        
        # Use a fixed limit for how many files to display
        max_display=15
        display_count=0
        
        for file in "${untracked_files[@]}"; do
            ((display_count++))
            if [ $display_count -le $max_display ]; then
                echo -e "    ${COLOR_CYAN}? $file${COLOR_NORMAL}"
            else
                break
            fi
        done
        
        # Show message if we have more files than the display limit
        if [ $untracked_count -gt $max_display ]; then
            remaining=$((untracked_count - max_display))
            echo -e "    ${COLOR_CYAN}... and $remaining more files${COLOR_NORMAL}"
        fi
    fi
    
    # Display renamed files - IMPROVED METHOD
    if [ $renamed_count -gt 0 ]; then
        echo -e "  ${COLOR_BLUE}Renamed Files:${COLOR_NORMAL}"
        
        # Use a fixed limit for how many files to display
        max_display=15
        display_count=0
        
        for file in "${renamed_files[@]}"; do
            ((display_count++))
            if [ $display_count -le $max_display ]; then
                echo -e "    ${COLOR_BLUE}* $file${COLOR_NORMAL}"
            else
                break
            fi
        done
        
        # Show message if we have more files than the display limit
        if [ $renamed_count -gt $max_display ]; then
            remaining=$((renamed_count - max_display))
            echo -e "    ${COLOR_BLUE}... and $remaining more files${COLOR_NORMAL}"
        fi
    fi
    
    echo
else
    echo -e "${COLOR_GREEN}Working directory is clean - no uncommitted changes.${COLOR_NORMAL}"
fi

# Fetch the latest changes from the remote repository
echo
echo -e "${COLOR_CYAN}Fetching updates from remote repository...${COLOR_NORMAL}"
if ! git fetch 2>/dev/null; then
    echo -e "${COLOR_RED}ERROR: Failed to fetch from remote. Remote repository may not be configured or accessible.${COLOR_NORMAL}"
    cd "$currentDir"
    exit 1
fi

# Get the current branch name
current_branch=$(git rev-parse --abbrev-ref HEAD 2>/dev/null)
if [ -z "$current_branch" ]; then
    echo -e "${COLOR_RED}ERROR: Failed to determine current branch.${COLOR_NORMAL}"
    cd "$currentDir"
    exit 1
fi

# Try to get the remote branch information
if ! git rev-parse --verify @{u} >/dev/null 2>&1; then
    echo -e "${COLOR_YELLOW}WARNING: No upstream branch set for '${current_branch}'.${COLOR_NORMAL}"
    cd "$currentDir"
    echo
    echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
    echo -e "${COLOR_GREEN}GIT SYNC CHECK COMPLETED${COLOR_NORMAL}"
    echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
    exit 0
fi

# Get full commit hashes
full_local_hash=$(git rev-parse HEAD)
full_remote_hash=$(git rev-parse @{u})

# Get short commit hashes (7 characters)
local=$(git rev-parse --short HEAD)
remote=$(git rev-parse --short @{u})
base=$(git merge-base @ @{u})

# Get timestamps and authors for the commits
local_date=$(git log -1 --format=%cd --date=short HEAD)
local_author=$(git log -1 --format=%an HEAD)

remote_date=$(git log -1 --format=%cd --date=short @{u})
remote_author=$(git log -1 --format=%an @{u})

echo -e "${COLOR_CYAN}Current branch: ${COLOR_WHITE}${current_branch}${COLOR_NORMAL}"
echo -e "${COLOR_GRAY}Local commit:  ${local} (${local_date} by ${local_author})${COLOR_NORMAL}"
echo -e "${COLOR_GRAY}Remote commit: ${remote} (${remote_date} by ${remote_author})${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Synchronization Status ====${COLOR_NORMAL}"

if [ "$full_local_hash" = "$full_remote_hash" ]; then
    echo -e "${COLOR_GREEN}SUCCESS: Local branch '${current_branch}' is up-to-date with the remote branch.${COLOR_NORMAL}"
    echo -e "${COLOR_GREEN}Both local and remote are at commit ${local}${COLOR_NORMAL}"
    echo -e "${COLOR_GREEN}Commit date: ${local_date} by ${local_author}${COLOR_NORMAL}"
elif [ "$local" = "$base" ]; then
    behind_count=$(git rev-list --count HEAD..@{u})
    echo -e "${COLOR_YELLOW}WARNING: Local branch '${current_branch}' is ${behind_count} commits behind the remote branch.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Local is at:  ${local}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Remote is at: ${remote}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Local commit date: ${local_date} by ${local_author}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Remote commit date: ${remote_date} by ${remote_author}${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Recommendation: Run 'git pull' to update your local branch.${COLOR_NORMAL}"
elif [ "$remote" = "$base" ]; then
    ahead_count=$(git rev-list --count @{u}..HEAD)
    echo -e "${COLOR_YELLOW}WARNING: Local branch '${current_branch}' is ${ahead_count} commits ahead of the remote branch.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Local is at:  ${local}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Remote is at: ${remote}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Local commit date: ${local_date} by ${local_author}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Remote commit date: ${remote_date} by ${remote_author}${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Recommendation: Run 'git push' to update the remote branch.${COLOR_NORMAL}"
else
    ahead_count=$(git rev-list --count @{u}..HEAD)
    behind_count=$(git rev-list --count HEAD..@{u})
    echo -e "${COLOR_RED}ALERT: Local and remote branches have diverged.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Local is ${ahead_count} commits ahead and ${behind_count} commits behind the remote.${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Local is at:  ${local}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Remote is at: ${remote}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Local commit date: ${local_date} by ${local_author}${COLOR_NORMAL}"
    echo -e "${COLOR_YELLOW}Remote commit date: ${remote_date} by ${remote_author}${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}Recommendation: You may need to merge or rebase to reconcile the differences.${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}  - To merge remote changes: git pull${COLOR_NORMAL}"
    echo -e "${COLOR_CYAN}  - To rebase onto remote: git pull --rebase${COLOR_NORMAL}"
fi

# Return to original directory
echo
echo -e "${COLOR_CYAN}Returning to original directory: ${currentDir}${COLOR_NORMAL}"
cd "$currentDir"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}GIT SYNC CHECK COMPLETED${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
