@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=Git Hooks Configuration Utility v%SCRIPT_VERSION%"

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

:: Set the path to the .git/hooks directory
set "HOOKS_DIR=.git\hooks"
echo %COLOR_CYAN%Hooks directory: %HOOKS_DIR%%COLOR_NORMAL%

:: Check if .git/hooks directory exists
if not exist "%HOOKS_DIR%\" (
    echo %COLOR_YELLOW%WARNING: Hooks directory not found. Creating %HOOKS_DIR% directory...%COLOR_NORMAL%
    mkdir "%HOOKS_DIR%" 2>nul
    if !errorlevel! neq 0 (
        echo %COLOR_RED%ERROR: Failed to create hooks directory.%COLOR_NORMAL%
        goto :cleanup
    ) else (
        echo %COLOR_GREEN%SUCCESS: Created hooks directory.%COLOR_NORMAL%
    )
)

echo.
echo %COLOR_BLUE%==== Configuring Git Hooks ====%COLOR_NORMAL%

:: Backup and install pre-commit hook
echo %COLOR_CYAN%Configuring pre-commit hook...%COLOR_NORMAL%
if exist "%HOOKS_DIR%\pre-commit" (
    echo %COLOR_YELLOW%  Backing up existing pre-commit hook...%COLOR_NORMAL%
    if exist "%HOOKS_DIR%\pre-commit.backup" (
        del /f /q "%HOOKS_DIR%\pre-commit.backup" >nul 2>&1
    )
    rename "%HOOKS_DIR%\pre-commit" "pre-commit.backup" >nul 2>&1
    if !errorlevel! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to backup existing pre-commit hook.%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%  Backed up existing pre-commit hook to pre-commit.backup%COLOR_NORMAL%
    )
)

echo %COLOR_CYAN%  Installing new pre-commit hook...%COLOR_NORMAL%
copy /Y "%CD%\git-scripts\pre-commit" "%HOOKS_DIR%\pre-commit" >nul 2>&1
if %errorlevel% neq 0 (
    echo %COLOR_RED%  ERROR: Failed to install pre-commit hook.%COLOR_NORMAL%
) else (
    echo %COLOR_GREEN%  SUCCESS: Installed pre-commit hook.%COLOR_NORMAL%
)

:: Backup and install pre-push hook
echo.
echo %COLOR_CYAN%Configuring pre-push hook...%COLOR_NORMAL%
if exist "%HOOKS_DIR%\pre-push" (
    echo %COLOR_YELLOW%  Backing up existing pre-push hook...%COLOR_NORMAL%
    if exist "%HOOKS_DIR%\pre-push.backup" (
        del /f /q "%HOOKS_DIR%\pre-push.backup" >nul 2>&1
    )
    rename "%HOOKS_DIR%\pre-push" "pre-push.backup" >nul 2>&1
    if !errorlevel! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to backup existing pre-push hook.%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%  Backed up existing pre-push hook to pre-push.backup%COLOR_NORMAL%
    )
)

echo %COLOR_CYAN%  Installing new pre-push hook...%COLOR_NORMAL%
copy /Y "%CD%\git-scripts\pre-push" "%HOOKS_DIR%\pre-push" >nul 2>&1
if %errorlevel% neq 0 (
    echo %COLOR_RED%  ERROR: Failed to install pre-push hook.%COLOR_NORMAL%
) else (
    echo %COLOR_GREEN%  SUCCESS: Installed pre-push hook.%COLOR_NORMAL%
)

:: Set execute permission on hooks (important for Unix-like systems)
echo.
echo %COLOR_CYAN%Setting executable permissions...%COLOR_NORMAL%
attrib +x "%HOOKS_DIR%\pre-commit" >nul 2>&1
attrib +x "%HOOKS_DIR%\pre-push" >nul 2>&1
echo %COLOR_GREEN%Permissions set.%COLOR_NORMAL%

:: Clean up and return to original directory
:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd /d "%currentDir%"

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%GIT HOOKS CONFIGURATION COMPLETED%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo Press any key to exit...
pause > nul
endlocal
