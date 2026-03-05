@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=Git Repository Submodule Initialization Utility v%SCRIPT_VERSION%"

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

:: ========== MAIN PROCESS ==========
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
    goto :cleanup
)

:: Check if any submodules are configured
if not exist ".gitmodules" (
    echo %COLOR_YELLOW%WARNING: No .gitmodules file found. There may be no submodules configured.%COLOR_NORMAL%
)

echo.
echo %COLOR_BLUE%==== Cleaning desktop.ini Files ====%COLOR_NORMAL%
set "found_files=0"

:: Delete hidden desktop.ini files
echo %COLOR_CYAN%Searching for desktop.ini files...%COLOR_NORMAL%
for /f "delims=" %%i in ('dir /a:h /s /b desktop.ini 2^>nul') do (
    echo   %COLOR_WHITE%Found: %%i%COLOR_NORMAL%
    del /f /q "%%i" >nul 2>&1
    if !errorlevel! neq 0 (
        echo   %COLOR_RED%ERROR: Failed to delete: %%i%COLOR_NORMAL%
    ) else (
        echo   %COLOR_GREEN%DELETED: %%i%COLOR_NORMAL%
        set /a found_files+=1
    )
)

:: Remove desktop.ini files from Git tracking
echo.
echo %COLOR_CYAN%Removing desktop.ini files from Git tracking...%COLOR_NORMAL%
for /f "delims=" %%i in ('git ls-files --full-name *desktop.ini 2^>nul') do (
    echo   %COLOR_WHITE%Removing from Git: %%i%COLOR_NORMAL%
    git rm --cached --force "%%i" >nul 2>&1
    if !errorlevel! neq 0 (
        echo   %COLOR_RED%ERROR: Failed to remove from Git: %%i%COLOR_NORMAL%
    ) else (
        echo   %COLOR_GREEN%REMOVED from Git tracking: %%i%COLOR_NORMAL%
    )
)

echo.
echo %COLOR_CYAN%Processed %found_files% desktop.ini files%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Initializing Git Submodules ====%COLOR_NORMAL%
echo %COLOR_CYAN%Running git submodule update --init --recursive%COLOR_NORMAL%
git submodule update --init --recursive
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to initialize submodules. Please check your Git configuration.%COLOR_NORMAL%
) else (
    echo %COLOR_GREEN%SUCCESS: Submodules successfully initialized and updated.%COLOR_NORMAL%
)

:: Clean up and return to original directory
:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd "%currentDir%"

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%OPERATION COMPLETED SUCCESSFULLY%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo Press any key to exit...
pause > nul
endlocal
