#!/bin/bash

echo "Deleting existing PlantUML jar file if it exists..."
if [ -f "plantuml.jar" ]; then
    rm -f "plantuml.jar"
    echo "Deleted plantuml.jar"
fi

# Install jq if not installed
if ! command -v jq &> /dev/null; then
    echo "Installing jq..."
    sudo apt update && sudo apt install -y jq
fi

# Set whether to use the latest version or a specific version
use_latest=0
specific_version="tags/v1.2024.6"

if [ "$use_latest" -eq 1 ]; then
    echo "Extracting download URL for the latest release of PlantUML..."
    download_url=$(curl -s https://api.github.com/repos/plantuml/plantuml/releases/latest | jq -r '.assets[] | select(.name | endswith("plantuml.jar")) | .browser_download_url')
else
    echo "Extracting download URL for the specific release $specific_version of PlantUML..."
    download_url=$(curl -s https://api.github.com/repos/plantuml/plantuml/releases/"$specific_version" | jq -r '.assets[] | select(.name | endswith("plantuml.jar")) | .browser_download_url')
fi

echo "Downloading plantuml.jar..."
curl -sL -o plantuml.jar "$download_url"

echo "Download URL: $download_url"
if [ -f "plantuml.jar" ]; then
    echo "PlantUML downloaded successfully!"
else
    echo "Failed to download PlantUML."
    exit 1
fi

# Download and extract JLatexMath
echo "Downloading JLatexMath..."
curl -sL -o jlatexmath.zip http://beta.plantuml.net/plantuml-jlatexmath.zip

echo "Extracting JLatexMath..."
unzip -o jlatexmath.zip -d .
rm -f jlatexmath.zip

# Download and extract Batik and Fop
echo "Downloading Batik and Fop..."
curl -sL -o batikAndFop.zip http://beta.plantuml.net/batikAndFop.zip

echo "Extracting Batik and Fop..."
unzip -o batikAndFop.zip -d .
rm -f batikAndFop.zip

# Verify that all necessary JAR files are present
echo "Verifying necessary JAR files..."
declare -a jar_files=("batik-all-1.7.jar" "jlatexmath-minimal-1.0.3.jar" "jlm_cyrillic.jar" "jlm_greek.jar" 
                      "avalon-framework-4.2.0.jar" "commons-io-1.3.1.jar" "commons-logging-1.0.4.jar" 
                      "fop.jar" "xml-apis-ext-1.3.04.jar" "xmlgraphics-commons-1.4.jar")

for jar in "${jar_files[@]}"; do
    if [ ! -f "$jar" ]; then
        echo "Missing $jar"
    else
        echo "$jar is present."
    fi
done

echo "All downloads and extractions completed successfully!"
