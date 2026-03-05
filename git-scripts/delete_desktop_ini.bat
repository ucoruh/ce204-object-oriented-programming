@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=Desktop.ini File Cleaner Utility v%SCRIPT_VERSION%"

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
    echo %COLOR_YELLOW%WARNING: This is not a Git repository. Will still remove desktop.ini files but not from Git tracking.%COLOR_NORMAL%
)

echo.
echo %COLOR_BLUE%==== Removing desktop.ini Files ====%COLOR_NORMAL%
set "found_files=0"
set "deleted_files=0"

:: Find and process desktop.ini files
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
            set /a found_files+=1
            echo   %COLOR_WHITE%Found: %%i%COLOR_NORMAL%
            
            :: Try to remove from Git if .git directory exists
            if exist ".git\" (
                git rm --cached --force "%%i" >nul 2>&1
                if !errorlevel! equ 0 (
                    echo   %COLOR_GREEN%REMOVED from Git tracking: %%i%COLOR_NORMAL%
                ) else (
                    echo   %COLOR_YELLOW%NOTE: File not in Git repository or already untracked: %%i%COLOR_NORMAL%
                )
            )
            
            :: Delete the file
            attrib -h "%%i" >nul 2>&1
            del "%%i" >nul 2>&1
            if !errorlevel! equ 0 (
                echo   %COLOR_GREEN%DELETED: %%i%COLOR_NORMAL%
                set /a deleted_files+=1
            ) else (
                echo   %COLOR_RED%ERROR: Failed to delete: %%i%COLOR_NORMAL%
            )
        )
    )
)

:: Clean up temporary file
if exist "%temp_file%" del "%temp_file%" >nul 2>&1

:: Display summary
echo.
if %found_files% equ 0 (
    echo %COLOR_YELLOW%No desktop.ini files were found.%COLOR_NORMAL%
) else if %deleted_files% equ 0 (
    echo %COLOR_YELLOW%Found %found_files% desktop.ini files, but none could be deleted.%COLOR_NORMAL%
) else if %deleted_files% equ 1 (
    echo %COLOR_GREEN%Successfully deleted 1 desktop.ini file.%COLOR_NORMAL%
) else (
    echo %COLOR_GREEN%Successfully deleted %deleted_files% desktop.ini files.%COLOR_NORMAL%
)

:: Clean up and return to original directory
:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd /d "%currentDir%"

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%DESKTOP.INI CLEANUP COMPLETED%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo Press any key to exit...
pause > nul
endlocal
