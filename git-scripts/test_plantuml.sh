#!/bin/bash

echo ":::: TESTING PLANTUML DOT CONFIGURATION :::"

# Save the current directory
currentDir=$(pwd)
echo "Current Directory: $currentDir"

echo "Change the working directory to the script directory"
cd "$(dirname "$(readlink -f "$0")")"

echo "Change the working directory to the parent folder"
cd ..

# Set the Graphviz environment variable if needed (optional, if `dot` is not in PATH)
export GRAPHVIZ_DOT=$(which dot)

if [ -z "$GRAPHVIZ_DOT" ]; then
    echo "Error: Graphviz (dot) is not installed or not in PATH. Please install it."
    exit 1
fi

echo "Using Graphviz at: $GRAPHVIZ_DOT"

# Run PlantUML to test Graphviz dot configuration
java -jar "$(dirname "$(readlink -f "$0")")/plantuml.jar" -v -testdot

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"

echo ":::: PLANTUML DOT CONFIGURATION TEST COMPLETED :::"
