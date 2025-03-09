#!/bin/bash

echo ":::: INIT SUBMODULES BEGIN :::"

# Save the current directory
currentDir=$(pwd)
echo "Current Directory: $currentDir"

echo "Change the working directory to the script directory"
cd "$(dirname "$(readlink -f "$0")")"

echo "Change the working directory to the parent folder"
cd ..

# Delete all desktop.ini files and remove them from Git index
echo "Deleting desktop.ini files..."
find . -name 'desktop.ini' -print0 | while IFS= read -r -d '' file; do
    if [ -f "$file" ]; then
        git rm --cached --force "$file"
        rm "$file"
        echo "Removed: $file"
    fi
done

# Initialize and update Git submodules
echo "Initializing and updating submodules..."
git submodule update --init --recursive
if [ $? -eq 0 ]; then
    echo "Git submodules initialized and updated successfully."
else
    echo "Failed to initialize or update Git submodules."
    exit 1
fi

echo ":::: INIT SUBMODULES COMPLETED :::"

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"
