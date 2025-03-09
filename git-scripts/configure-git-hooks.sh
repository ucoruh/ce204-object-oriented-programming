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

# Set the path to the .git/hooks directory
HOOKS_DIR=".git/hooks"

# Check if .git/hooks directory exists
if [ ! -d "$HOOKS_DIR" ]; then
    echo "Error: .git/hooks directory not found."
    exit 1
fi

# Backup current pre-commit script if it exists
if [ -f "$HOOKS_DIR/pre-commit" ]; then
    echo "Backing up current pre-commit script..."
    mv "$HOOKS_DIR/pre-commit" "$HOOKS_DIR/pre-commit.backup"
fi

# Copy pre-commit to .git/hooks directory and rename it to pre-commit
cp "git-scripts/pre-commit" "$HOOKS_DIR/pre-commit"
chmod +x "$HOOKS_DIR/pre-commit"

# Backup current pre-push script if it exists
if [ -f "$HOOKS_DIR/pre-push" ]; then
    echo "Backing up current pre-push script..."
    mv "$HOOKS_DIR/pre-push" "$HOOKS_DIR/pre-push.backup"
fi

# Copy pre-push to .git/hooks directory and rename it to pre-push
cp "git-scripts/pre-push" "$HOOKS_DIR/pre-push"
chmod +x "$HOOKS_DIR/pre-push"

echo "Scripts have been copied successfully."

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"