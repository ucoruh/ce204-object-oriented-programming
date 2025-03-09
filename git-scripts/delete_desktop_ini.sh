#!/bin/bash

echo ":::: DELETE GOOGLE DRIVE desktop.ini FILES :::"

# Save the current directory
currentDir=$(pwd)
echo "Current Directory: $currentDir"

# Change the working directory to the script directory
cd "$(dirname "$(readlink -f "$0")")"
echo "Script Directory: $(pwd)"

# Change the working directory to the parent folder
cd ..
echo "Parent Directory: $(pwd)"

# Find and delete desktop.ini files, and remove them from Git index
echo "Searching for desktop.ini files..."
found_files=false
find . -name 'desktop.ini' -print0 | while IFS= read -r -d '' file; do
    found_files=true
    if [ -f "$file" ]; then
        if git rm --cached --force "$file" 2>/dev/null; then
            echo "Removed from Git index: $file"
        else
            echo "Failed to remove from Git index (may not be tracked): $file"
        fi
        rm "$file"
        echo "Removed: $file"
    fi
done

if ! $found_files; then
    echo "No desktop.ini files found."
fi

echo ":::: DELETE OPERATION COMPLETED :::"

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"

# Wait for user input before exiting
echo "Press any key to continue..."
read -n1 -s
echo
