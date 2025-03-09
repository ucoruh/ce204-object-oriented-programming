#!/bin/bash

echo ":::: RUN PLANTUML TO GENERATE PDF DIAGRAMS :::"

# Save the current directory
currentDir=$(pwd)
echo "Current Directory: $currentDir"

echo "Change the working directory to the script directory"
cd "$(dirname "$(readlink -f "$0")")"

echo "Change the working directory to the parent folder"
cd ..

# Set the path to Graphviz if necessary (ensure dot command is in the PATH)
export GRAPHVIZ_DOT="/usr/bin/dot"

# Verify if Graphviz is installed
if ! command -v dot &>/dev/null; then
    echo "Error: Graphviz 'dot' command not found. Please install Graphviz."
    exit 1
fi

# Check if PlantUML JAR file exists
PLANTUML_JAR="./plantuml.jar"
if [ ! -f "$PLANTUML_JAR" ]; then
    echo "Error: PlantUML JAR file not found at $PLANTUML_JAR. Please ensure it is present."
    exit 1
fi

# Run PlantUML to generate PDF diagrams from .puml files
java -Xmx2048m -Dfile.encoding=UTF-8 -DPLANTUML_LIMIT_SIZE=32768 -jar "$PLANTUML_JAR" -tpdf -v "./**.puml"
if [ $? -eq 0 ]; then
    echo "PlantUML diagrams successfully generated in PDF format."
else
    echo "Error occurred while generating PlantUML diagrams in PDF format."
    exit 1
fi

echo "Reverting to Original Directory: $currentDir"
cd "$currentDir"
