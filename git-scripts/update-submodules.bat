@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.1"
set "SCRIPT_TITLE=Git Repository Submodule Update Utility v%SCRIPT_VERSION%"

:: Enable ANSI colors - using Windows 10+ compatible method
for /F "tokens=4-5 delims=. " %%i in ('ver') do set VERSION=%%i.%%j
if "%version%" == "10.0" (
    :: For Windows 10/11 - enable VT sequences
    reg add HKCU\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f > nul 2>&1
)

:: Prepare escape character for ANSI colors
for /F %%a in ('echo prompt $E^| cmd') do set "ESC=%%a"

:: Color configuration with proper escape sequences
set "COLOR_NORMAL=%ESC%[0m"
set "COLOR_RED=%ESC%[91m"
set "COLOR_GREEN=%ESC%[92m"
set "COLOR_YELLOW=%ESC%[93m"
set "COLOR_BLUE=%ESC%[94m"
set "COLOR_MAGENTA=%ESC%[95m"
set "COLOR_CYAN=%ESC%[96m"
set "COLOR_WHITE=%ESC%[97m"

:: Initialize error flag
set "ERROR_OCCURRED=0"

:: Process command line arguments
set "FORCE_MODE=0"
set "INTERACTIVE=0"

:parse_args
if "%~1"=="" goto start_main
if /i "%~1"=="--force" set "FORCE_MODE=1" & goto next_arg
if /i "%~1"=="--help" goto show_help
if /i "%~1"=="-f" set "FORCE_MODE=1" & goto next_arg
if /i "%~1"=="-h" goto show_help
if /i "%~1"=="-i" set "INTERACTIVE=1" & goto next_arg
if /i "%~1"=="--interactive" set "INTERACTIVE=1" & goto next_arg

:next_arg
shift
goto parse_args

:show_help
echo.
echo %COLOR_BLUE%Usage: update-submodules.bat [options]%COLOR_NORMAL%
echo.
echo %COLOR_WHITE%Options:%COLOR_NORMAL%
echo   %COLOR_CYAN%-f, --force%COLOR_NORMAL%         Force update with --allow-unrelated-histories
echo   %COLOR_CYAN%-i, --interactive%COLOR_NORMAL%   Interactive mode, prompt for options during operation
echo   %COLOR_CYAN%-h, --help%COLOR_NORMAL%          Show this help message
echo.
goto :eof

:: ========== MAIN PROCESS ==========
:start_main
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_MAGENTA%%SCRIPT_TITLE%%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.

:: Save current directory
set "currentDir=%CD%"
echo %COLOR_CYAN%Current directory saved: %currentDir%%COLOR_NORMAL%

:: Change to script directory and then parent folder
echo %COLOR_CYAN%Changing to repository root directory...%COLOR_NORMAL%
cd /d "%~dp0"
cd ..

:: Check if .git directory exists
if not exist ".git\" (
    echo %COLOR_RED%ERROR: This is not a Git repository. Please run this script from a Git repository.%COLOR_NORMAL%
    set "ERROR_OCCURRED=1"
    goto :cleanup
)

:: Check if any submodules are configured
if not exist ".gitmodules" (
    echo %COLOR_YELLOW%WARNING: No .gitmodules file found. There may be no submodules configured.%COLOR_NORMAL%
)

:: Count submodules
for /f "tokens=1" %%i in ('git config --file .gitmodules --get-regexp path ^| wc -l 2^>nul') do set "SUBMODULE_COUNT=%%i"
if "%SUBMODULE_COUNT%"=="" set "SUBMODULE_COUNT=0"

if %SUBMODULE_COUNT% equ 0 (
    echo %COLOR_YELLOW%WARNING: No submodules defined in .gitmodules%COLOR_NORMAL%
) else (
    echo %COLOR_CYAN%Found %SUBMODULE_COUNT% submodule(s) configured in this repository%COLOR_NORMAL%
)

echo.
echo %COLOR_BLUE%==== Cleaning desktop.ini Files ====%COLOR_NORMAL%
set "found_files=0"

:: Delete hidden desktop.ini files
echo %COLOR_CYAN%Searching for desktop.ini files...%COLOR_NORMAL%

:: First build a temporary file with the list of desktop.ini files that actually exist
set "temp_file=%TEMP%\desktop_ini_list_%RANDOM%.txt"
dir /b /s "desktop.ini" 2>nul > "%temp_file%"

:: Check if we found any files
for /f %%F in ('type "%temp_file%" ^| find /c /v ""') do set file_count=%%F

if %file_count% equ 0 (
    echo %COLOR_CYAN%  No desktop.ini files detected in this repository.%COLOR_NORMAL%
) else (
    :: Process each desktop.ini file
    for /f "usebackq delims=" %%i in ("%temp_file%") do (
        if exist "%%i" (
            echo   %COLOR_WHITE%Found: %%i%COLOR_NORMAL%
            attrib -h "%%i" >nul 2>&1
            del "%%i" >nul 2>&1
            if !errorlevel! equ 0 (
                echo   %COLOR_GREEN%DELETED: %%i%COLOR_NORMAL%
                set /a found_files+=1
            ) else (
                echo   %COLOR_RED%ERROR: Failed to delete: %%i%COLOR_NORMAL%
            )
        )
    )
)

:: Clean up temporary file
if exist "%temp_file%" del "%temp_file%" >nul 2>&1

echo.
echo %COLOR_CYAN%Processed %found_files% desktop.ini files%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Updating Git Submodules ====%COLOR_NORMAL%

:: Initialize submodules if not already done
echo %COLOR_CYAN%Checking if submodules need to be initialized...%COLOR_NORMAL%
git submodule status | findstr /C:"No submodule mapping found" >nul 2>&1
if %errorlevel% equ 0 (
    echo %COLOR_YELLOW%Submodules not initialized. Running git submodule init...%COLOR_NORMAL%
    git submodule init
    if !errorlevel! neq 0 (
        echo %COLOR_RED%ERROR: Failed to initialize submodules.%COLOR_NORMAL%
        set "ERROR_OCCURRED=1"
        goto :cleanup
    )
)

:: Enable interactive mode if requested
if %INTERACTIVE% equ 1 (
    echo.
    echo %COLOR_CYAN%Interactive Mode: Choose update option:%COLOR_NORMAL%
    echo   %COLOR_WHITE%1. Standard update (--remote --merge)%COLOR_NORMAL%
    echo   %COLOR_WHITE%2. Safe update without merging unrelated histories (--init --recursive)%COLOR_NORMAL%
    echo   %COLOR_WHITE%3. Reset and clean update (removes local changes)%COLOR_NORMAL%
    echo   %COLOR_WHITE%4. Cancel operation%COLOR_NORMAL%
    echo.
    echo %COLOR_YELLOW%WARNING: Merging unrelated histories can cause conflicts and code issues%COLOR_NORMAL%
    
    choice /c 1234 /n /m "%COLOR_CYAN%Enter your choice (1-4): %COLOR_NORMAL%"
    
    if errorlevel 4 (
        echo %COLOR_YELLOW%Operation canceled by user.%COLOR_NORMAL%
        goto :cleanup
    ) else if errorlevel 3 (
        set "UPDATE_MODE=3"
    ) else if errorlevel 2 (
        set "UPDATE_MODE=2"
    ) else (
        set "UPDATE_MODE=1"
    )
) else if %FORCE_MODE% equ 1 (
    set "UPDATE_MODE=2"
    echo %COLOR_YELLOW%Force mode enabled - Using safe update method%COLOR_NORMAL%
) else (
    set "UPDATE_MODE=1"
)

:: Perform the update based on selected mode
if %UPDATE_MODE% equ 1 (
    echo %COLOR_CYAN%Running standard update: git submodule update --remote --merge%COLOR_NORMAL%
    git submodule update --remote --merge
    set "UPDATE_ERROR=!errorlevel!"
    if !UPDATE_ERROR! neq 0 (
        echo %COLOR_YELLOW%Standard update failed. Detected possible unrelated histories issue.%COLOR_NORMAL%
        echo %COLOR_YELLOW%Trying safer approach with --init --recursive...%COLOR_NORMAL%
        git submodule update --init --recursive
        set "UPDATE_ERROR=!errorlevel!"
    )
) else if %UPDATE_MODE% equ 2 (
    echo %COLOR_CYAN%Running safe update: git submodule update --init --recursive%COLOR_NORMAL%
    git submodule update --init --recursive
    set "UPDATE_ERROR=!errorlevel!"
) else if %UPDATE_MODE% equ 3 (
    echo %COLOR_CYAN%Running reset and clean update (local changes will be lost)%COLOR_NORMAL%
    
    :: Need to handle each submodule individually for reset mode
    for /f "tokens=2" %%s in ('git config --file .gitmodules --get-regexp path ^| sed "s/^submodule\.\(.*\)\.path.*/\1/"') do (
        set "submodule_name=%%s"
        echo %COLOR_WHITE%Resetting submodule: !submodule_name!%COLOR_NORMAL%
        
        :: Get the path of the submodule
        for /f "tokens=2" %%p in ('git config --file .gitmodules --get submodule.!submodule_name!.path') do (
            set "submodule_path=%%p"
            echo %COLOR_CYAN%  Submodule path: !submodule_path!%COLOR_NORMAL%
            
            :: Check if the submodule directory exists
            if exist "!submodule_path!" (
                cd "!submodule_path!"
                
                :: Check if .git exists in the submodule directory
                if exist ".git" (
                    echo %COLOR_CYAN%  Resetting submodule content...%COLOR_NORMAL%
                    git fetch
                    git reset --hard origin/HEAD
                    git clean -fd
                    if !errorlevel! neq 0 (
                        echo %COLOR_RED%  ERROR: Failed to reset submodule !submodule_name!%COLOR_NORMAL%
                        set "UPDATE_ERROR=1"
                    ) else (
                        echo %COLOR_GREEN%  Successfully reset submodule !submodule_name!%COLOR_NORMAL%
                    )
                ) else (
                    echo %COLOR_YELLOW%  Submodule .git not found, reinitializing...%COLOR_NORMAL%
                    cd ..
                    git submodule deinit -f "!submodule_path!"
                    git submodule update --init "!submodule_path!"
                    if !errorlevel! neq 0 (
                        echo %COLOR_RED%  ERROR: Failed to reinitialize submodule !submodule_path!%COLOR_NORMAL%
                        set "UPDATE_ERROR=1"
                    )
                )
                
                cd ..
            ) else (
                echo %COLOR_YELLOW%  Submodule directory not found, initializing...%COLOR_NORMAL%
                git submodule update --init "!submodule_path!"
                if !errorlevel! neq 0 (
                    echo %COLOR_RED%  ERROR: Failed to initialize submodule !submodule_path!%COLOR_NORMAL%
                    set "UPDATE_ERROR=1"
                )
            )
        )
    )
)

:: Check for update errors
if defined UPDATE_ERROR (
    if !UPDATE_ERROR! neq 0 (
        echo %COLOR_RED%ERROR: Failed to update submodules. Error code: !UPDATE_ERROR!%COLOR_NORMAL%
        echo %COLOR_YELLOW%TIP: Try using interactive mode with reset option%COLOR_NORMAL%
        echo %COLOR_YELLOW%     Run: update-submodules.bat -i%COLOR_NORMAL%
        set "ERROR_OCCURRED=1"
    ) else (
        echo %COLOR_GREEN%SUCCESS: Submodules successfully updated.%COLOR_NORMAL%
    )
)

:: Clean up and return to original directory
:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd "%currentDir%"

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
if %ERROR_OCCURRED% equ 1 (
    echo %COLOR_RED%OPERATION COMPLETED WITH ERRORS%COLOR_NORMAL%
) else (
    echo %COLOR_GREEN%OPERATION COMPLETED SUCCESSFULLY%COLOR_NORMAL%
)
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo Press any key to exit...
pause > nul
endlocal
