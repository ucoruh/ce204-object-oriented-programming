#!/bin/bash

# Enable error handling
set -e

# Get the current directory
currentDir=$(pwd)
echo "Current Directory: $currentDir"

# Change the current working directory to the script directory
cd "$(dirname "$0")"
echo "Script Directory: $(pwd)"

# Change the working directory to the parent folder
cd ..
echo "Parent Directory: $(pwd)"

# API URL for GitHub Gitignore templates
API_URL="https://www.toptal.com/developers/gitignore/api/c,csharp,vs,visualstudio,visualstudiocode,java,maven,c++,cmake,eclipse,netbeans"
# Set the output file name
OUTPUT_FILE=".gitignore"

# Check if .gitignore already exists
if [ -f "$OUTPUT_FILE" ]; then
    echo ".gitignore already exists, skipping generation."
else
    # Download the API results using curl
    if curl -s -o "$OUTPUT_FILE" "$API_URL"; then
        echo "Downloaded .gitignore file from $API_URL and saved as $OUTPUT_FILE"

        # Append '**/desktop.ini' to .gitignore
        echo "**/desktop.ini" >> "$OUTPUT_FILE"
        echo "Appended '**/desktop.ini' to $OUTPUT_FILE"
    else
        echo "Failed to download .gitignore file from $API_URL"
        exit 1
    fi
fi

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"
