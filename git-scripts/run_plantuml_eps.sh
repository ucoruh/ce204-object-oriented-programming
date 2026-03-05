#!/bin/bash
# PlantUML EPS Generator Script

# Get the script directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Call the common PlantUML script with EPS format
"$SCRIPT_DIR/plantuml_common.sh" "eps" "EPS" 