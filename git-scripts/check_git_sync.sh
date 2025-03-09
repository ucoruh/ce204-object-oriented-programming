#!/bin/bash

# Enable error handling
set -e

# Save the current directory
currentDir=$(pwd)

# Change to the script directory
cd "$(dirname "$0")"

# Change to the parent folder
cd ..

# Remove all desktop.ini files
find . -type f -name "desktop.ini" -exec rm -f {} \;

# Check for uncommitted changes and list them if found
if git status --porcelain > temp_status.txt; then
    echo "Warning: You have uncommitted changes."
    echo "Uncommitted changes:"
    cat temp_status.txt
    rm -f temp_status.txt
else
    rm -f temp_status.txt
fi

# Fetch the latest changes from the remote repository
git fetch

# Get the local branch, remote branch, and their base
local=$(git rev-parse @)
remote=$(git rev-parse @{u})
base=$(git merge-base @ @{u})

if [ "$local" == "$remote" ]; then
    echo "Local branch is up-to-date with the remote branch."
elif [ "$local" == "$base" ]; then
    echo "Local branch is behind the remote branch."
elif [ "$remote" == "$base" ]; then
    echo "Local branch is ahead of the remote branch."
else
    echo "Local and remote branches have diverged."
fi

# Revert to the original directory
cd "$currentDir"
