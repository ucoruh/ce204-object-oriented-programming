#!/bin/bash

# ========== SCRIPT CONFIGURATION ==========
SCRIPT_VERSION="1.0"
SCRIPT_TITLE="Scripts Line Ending Fixer Utility v${SCRIPT_VERSION}"

# ANSI color configuration
COLOR_NORMAL="\033[0m"
COLOR_RED="\033[91m"
COLOR_GREEN="\033[92m"
COLOR_YELLOW="\033[93m"
COLOR_BLUE="\033[94m"
COLOR_MAGENTA="\033[95m"
COLOR_CYAN="\033[96m"
COLOR_WHITE="\033[97m"

# ========== MAIN PROCESS ==========
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_MAGENTA}${SCRIPT_TITLE}${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo
echo -e "${COLOR_CYAN}This script converts script files to appropriate line endings:${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}- Windows files (.bat, .cmd, .ps1, .py): CRLF format (Windows)${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}- Shell scripts (.sh): LF format (Unix)${COLOR_NORMAL}"
echo

# Set working directory to parent of repo root to scan the whole repository
SCRIPT_DIR="$(dirname "$0")"
REPO_ROOT="$(dirname "$SCRIPT_DIR")"
PARENT_DIR="$REPO_ROOT"
cd "$PARENT_DIR"

echo -e "${COLOR_CYAN}Starting from parent directory: $(pwd)${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Repository location: $REPO_ROOT${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}==== Checking for Required Tools ====${COLOR_NORMAL}"

# Ensure unix2dos is available by running the download script
echo -e "${COLOR_CYAN}Checking for unix2dos and dos2unix tools...${COLOR_NORMAL}"
bash "$SCRIPT_DIR/download_unix2dos.sh"
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: Failed to ensure unix2dos/dos2unix is available.${COLOR_NORMAL}"
    echo -e "${COLOR_RED}Please check the error messages and try again.${COLOR_NORMAL}"
    exit 1
else
    echo -e "${COLOR_GREEN}Successfully verified availability of unix2dos/dos2unix tools.${COLOR_NORMAL}"
fi

# Initialize command variables
DOS2UNIX_CMD="dos2unix"
UNIX2DOS_CMD="unix2dos"

# Verify tools can be executed
echo -e "${COLOR_CYAN}Verifying tools can be executed...${COLOR_NORMAL}"

# Test unix2dos
"$UNIX2DOS_CMD" --version >/dev/null 2>&1
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: unix2dos tool does not work correctly.${COLOR_NORMAL}"
    exit 1
else
    echo -e "${COLOR_GREEN}unix2dos tool verified successfully.${COLOR_NORMAL}"
fi

# Test dos2unix
"$DOS2UNIX_CMD" --version >/dev/null 2>&1
if [ $? -ne 0 ]; then
    echo -e "${COLOR_RED}ERROR: dos2unix tool does not work correctly.${COLOR_NORMAL}"
    exit 1
else
    echo -e "${COLOR_GREEN}dos2unix tool verified successfully.${COLOR_NORMAL}"
fi

echo
echo -e "${COLOR_BLUE}==== Starting File Conversion ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Starting conversion of files to appropriate format...${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}This will process scripts in and above the repository directory.${COLOR_NORMAL}"
echo

# Initialize counters
windows_count=0
unix_count=0
errors_count=0

# Process Windows batch files (.bat, .cmd) - Convert to Windows format (CRLF)
echo -e "${COLOR_BLUE}==== Processing Windows Batch Files ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Converting .bat and .cmd files to CRLF format...${COLOR_NORMAL}"

while IFS= read -r file; do
    echo -e "${COLOR_WHITE}Converting to Windows format: $file${COLOR_NORMAL}"
    "$UNIX2DOS_CMD" -f "$file" >/dev/null 2>&1
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to convert $file${COLOR_NORMAL}"
        ((errors_count++))
    else
        echo -e "${COLOR_GREEN}  SUCCESS: Converted to CRLF format${COLOR_NORMAL}"
        ((windows_count++))
    fi
done < <(find "$PARENT_DIR" -type f \( -name "*.bat" -o -name "*.cmd" \))

# Process PowerShell scripts (.ps1) - Convert to Windows format (CRLF)
echo
echo -e "${COLOR_BLUE}==== Processing PowerShell Scripts ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Converting .ps1 files to CRLF format...${COLOR_NORMAL}"

while IFS= read -r file; do
    echo -e "${COLOR_WHITE}Converting to Windows format: $file${COLOR_NORMAL}"
    "$UNIX2DOS_CMD" -f "$file" >/dev/null 2>&1
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to convert $file${COLOR_NORMAL}"
        ((errors_count++))
    else
        echo -e "${COLOR_GREEN}  SUCCESS: Converted to CRLF format${COLOR_NORMAL}"
        ((windows_count++))
    fi
done < <(find "$PARENT_DIR" -type f -name "*.ps1")

# Process Python scripts (.py) - Convert to Windows format (CRLF)
echo
echo -e "${COLOR_BLUE}==== Processing Python Scripts ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Converting .py files to CRLF format...${COLOR_NORMAL}"

while IFS= read -r file; do
    echo -e "${COLOR_WHITE}Converting to Windows format: $file${COLOR_NORMAL}"
    "$UNIX2DOS_CMD" -f "$file" >/dev/null 2>&1
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to convert $file${COLOR_NORMAL}"
        ((errors_count++))
    else
        echo -e "${COLOR_GREEN}  SUCCESS: Converted to CRLF format${COLOR_NORMAL}"
        ((windows_count++))
    fi
done < <(find "$PARENT_DIR" -type f -name "*.py")

# Process Shell scripts (.sh) - Convert to Unix format (LF)
echo
echo -e "${COLOR_BLUE}==== Processing Shell Scripts ====${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}Converting .sh files to LF format...${COLOR_NORMAL}"

while IFS= read -r file; do
    echo -e "${COLOR_WHITE}Converting to Unix format: $file${COLOR_NORMAL}"
    "$DOS2UNIX_CMD" -f "$file" >/dev/null 2>&1
    if [ $? -ne 0 ]; then
        echo -e "${COLOR_RED}  ERROR: Failed to convert $file${COLOR_NORMAL}"
        ((errors_count++))
    else
        echo -e "${COLOR_GREEN}  SUCCESS: Converted to LF format${COLOR_NORMAL}"
        ((unix_count++))
    fi
done < <(find "$PARENT_DIR" -type f -name "*.sh")

# Calculate total
total=$((windows_count + unix_count))

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}CONVERSION COMPLETE!${COLOR_NORMAL}"
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
echo
echo -e "${COLOR_CYAN}All script files have been converted to appropriate formats:${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}- Windows scripts (.bat, .cmd, .ps1, .py): CRLF format${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}- Shell scripts (.sh): LF format${COLOR_NORMAL}"
echo
echo -e "${COLOR_GREEN}Conversion summary:${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}- Windows format (CRLF): $windows_count files${COLOR_NORMAL}"
echo -e "${COLOR_GREEN}- Unix format (LF): $unix_count files${COLOR_NORMAL}"
if [ $errors_count -gt 0 ]; then
    echo -e "${COLOR_RED}- Errors: $errors_count files${COLOR_NORMAL}"
fi
echo -e "${COLOR_GREEN}- Total: $total files processed${COLOR_NORMAL}"
echo
echo -e "${COLOR_CYAN}Processed directories:${COLOR_NORMAL}"
echo -e "${COLOR_CYAN}- $PARENT_DIR (and all subdirectories)${COLOR_NORMAL}"

echo
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"
if [ $errors_count -eq 0 ]; then
    echo -e "${COLOR_GREEN}SCRIPT ENCODING FIX COMPLETED SUCCESSFULLY${COLOR_NORMAL}"
else
    echo -e "${COLOR_YELLOW}SCRIPT ENCODING FIX COMPLETED WITH $errors_count ERRORS${COLOR_NORMAL}"
fi
echo -e "${COLOR_BLUE}=======================================================${COLOR_NORMAL}"

echo
echo -e "Press Enter to exit..."
read -r
exit 0 