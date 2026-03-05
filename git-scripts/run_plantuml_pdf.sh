#!/bin/bash
# PlantUML PDF Generator Script

# Get the script directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Call the common PlantUML script with PDF format
"$SCRIPT_DIR/plantuml_common.sh" "pdf" "PDF"
