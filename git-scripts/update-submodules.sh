#!/bin/bash

echo ":::: UPDATE SUBMODULES BEGIN :::"

# Save the current directory
currentDir=$(pwd)
echo "Current Directory: $currentDir"

echo "Change the working directory to the script directory"
cd "$(dirname "$(readlink -f "$0")")"

echo "Change the working directory to the parent folder"
cd ..

# Delete all desktop.ini files
echo "Deleting all desktop.ini files..."
find . -name 'desktop.ini' -exec git rm --cached --force {} \; -exec rm {} \;

# Update Git submodules
echo "Updating Git submodules..."
git submodule update --remote --merge

echo ":::: UPDATE SUBMODULES COMPLETED :::"

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"
