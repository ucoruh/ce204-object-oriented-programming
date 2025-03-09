#!/bin/bash

echo ":::: RUN PLANTUML TO GENERATE SVG DIAGRAMS :::"

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

# Run PlantUML to generate SVG diagrams from .puml files in the parent directory
java -Xmx2048m -Dfile.encoding=UTF-8 -DPLANTUML_LIMIT_SIZE=32768 -jar "$(dirname "$(readlink -f "$0")")/plantuml.jar" -tsvg -v "./**.puml"

# Revert to the original directory
cd "$currentDir"
echo "Reverted to Original Directory: $currentDir"

echo ":::: PLANTUML SVG GENERATION COMPLETED :::"
