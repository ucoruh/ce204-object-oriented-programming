@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=Unix2Dos Downloader and Checker Utility v%SCRIPT_VERSION%"

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

:: Download configurations
set "SF_URL=https://sourceforge.net/projects/dos2unix/files/dos2unix/7.5.2/dos2unix-7.5.2-win64.zip/download"
set use_latest=0
set specific_version=7.5.2

:: ========== MAIN PROCESS ==========
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_MAGENTA%%SCRIPT_TITLE%%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo %COLOR_CYAN%This script checks if unix2dos is installed on your system.%COLOR_NORMAL%
echo %COLOR_CYAN%If not, it will download the latest version from SourceForge.%COLOR_NORMAL%
echo.

:: Set working directory
set "SCRIPT_DIR=%~dp0"
set "ROOT_DIR=%SCRIPT_DIR%.."
cd /d "%ROOT_DIR%"
echo %COLOR_CYAN%Changed to repository root directory: %CD%%COLOR_NORMAL%

:: Initialize variables
set "UNIX2DOS_CMD="
set "DOS2UNIX_CMD="
set "NEED_DOWNLOAD=0"
set "FORCE_DOWNLOAD=0"

echo.
echo %COLOR_BLUE%==== Checking for Existing Installation ====%COLOR_NORMAL%

:: Check if unix2dos is already installed in system path
echo %COLOR_CYAN%Checking if unix2dos is already installed in system PATH...%COLOR_NORMAL%
where unix2dos >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo %COLOR_GREEN%unix2dos found in system PATH.%COLOR_NORMAL%
    
    :: Test if it works
    unix2dos --version >nul 2>&1
    if %ERRORLEVEL% equ 0 (
        echo %COLOR_GREEN%Verified unix2dos is working properly.%COLOR_NORMAL%
        set "UNIX2DOS_CMD=unix2dos"
        
        :: Show version info
        for /f "tokens=*" %%v in ('unix2dos --version 2^>^&1') do (
            echo %COLOR_CYAN%Version: %%v%COLOR_NORMAL%
        )
    ) else (
        echo %COLOR_YELLOW%WARNING: unix2dos command found but may not be working properly.%COLOR_NORMAL%
        set "NEED_DOWNLOAD=1"
    )
) else (
    echo %COLOR_YELLOW%unix2dos is not found in system PATH.%COLOR_NORMAL%
    set "NEED_DOWNLOAD=1"
)

:: Check for local copy in git-scripts directory
echo %COLOR_CYAN%Checking for local copy in git-scripts directory...%COLOR_NORMAL%
if exist "%SCRIPT_DIR%\unix2dos.exe" (
    echo %COLOR_GREEN%Local copy of unix2dos found in git-scripts directory.%COLOR_NORMAL%
    
    :: Test if it works
    "%SCRIPT_DIR%\unix2dos.exe" --version >nul 2>&1
    if %ERRORLEVEL% equ 0 (
        echo %COLOR_GREEN%Verified local unix2dos is working properly.%COLOR_NORMAL%
        set "UNIX2DOS_CMD=%SCRIPT_DIR%\unix2dos.exe"
        set "NEED_DOWNLOAD=0"
        
        :: Show version info
        for /f "tokens=*" %%v in ('"%SCRIPT_DIR%\unix2dos.exe" --version 2^>^&1') do (
            echo %COLOR_CYAN%Version: %%v%COLOR_NORMAL%
        )
    ) else (
        echo %COLOR_YELLOW%WARNING: Local unix2dos found but may not be working properly.%COLOR_NORMAL%
        set "NEED_DOWNLOAD=1"
    )
)

:: Check for dos2unix in system path
echo %COLOR_CYAN%Checking if dos2unix is installed in system PATH...%COLOR_NORMAL%
where dos2unix >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo %COLOR_GREEN%dos2unix found in system PATH.%COLOR_NORMAL%
    set "DOS2UNIX_CMD=dos2unix"
    
    :: Show version info
    for /f "tokens=*" %%v in ('dos2unix --version 2^>^&1') do (
        echo %COLOR_CYAN%dos2unix version: %%v%COLOR_NORMAL%
    )
) else (
    :: Check for local copy
    if exist "%SCRIPT_DIR%\dos2unix.exe" (
        echo %COLOR_GREEN%Local copy of dos2unix found in git-scripts directory.%COLOR_NORMAL%
        set "DOS2UNIX_CMD=%SCRIPT_DIR%\dos2unix.exe"
        
        :: Show version info
        for /f "tokens=*" %%v in ('"%SCRIPT_DIR%\dos2unix.exe" --version 2^>^&1') do (
            echo %COLOR_CYAN%dos2unix version: %%v%COLOR_NORMAL%
        )
    ) else (
        echo %COLOR_YELLOW%dos2unix not found, will download if needed.%COLOR_NORMAL%
        set "NEED_DOWNLOAD=1"
    )
)

:: Ask user if they want to download even if tools are already available
set "DOWNLOAD_NOW=0"
if "%NEED_DOWNLOAD%"=="0" (
    echo.
    echo %COLOR_BLUE%==== Download Options ====%COLOR_NORMAL%
    echo %COLOR_CYAN%unix2dos and dos2unix are already available on your system.%COLOR_NORMAL%
    echo %COLOR_YELLOW%Would you like to download the latest version anyway?%COLOR_NORMAL%
    echo   %COLOR_WHITE%1. Yes, download and use the latest version from SourceForge%COLOR_NORMAL%
    echo   %COLOR_WHITE%2. No, use the existing tools%COLOR_NORMAL%
    
    rem Get user choice directly with simple handling
    choice /c 12 /n /m "%COLOR_CYAN%Enter your choice (1 or 2): %COLOR_NORMAL%"
    if errorlevel 2 (
        echo %COLOR_GREEN%You chose to use the existing tools.%COLOR_NORMAL%
    ) else if errorlevel 1 (
        echo %COLOR_YELLOW%You chose to download the latest version.%COLOR_NORMAL%
        set "DOWNLOAD_NOW=1"
    )
    echo.
)

:: Download if needed or requested
if "%NEED_DOWNLOAD%"=="1" (
    echo %COLOR_YELLOW%Need to download unix2dos/dos2unix tools...%COLOR_NORMAL%
    set "DOWNLOAD_NOW=1"
)

:: Execute the download process if required
if "%DOWNLOAD_NOW%"=="1" (
    echo.
    echo %COLOR_BLUE%==== Downloading Tools ====%COLOR_NORMAL%
    echo %COLOR_CYAN%Setting up download parameters...%COLOR_NORMAL%
    
    :: Simple direct paths without complex operations
    set "MY_ZIP_FILE=%SCRIPT_DIR%dos2unix.zip"
    set "MY_EXTRACT_DIR=%SCRIPT_DIR%dos2unix_temp"
    
    echo %COLOR_CYAN%Download URL: %SF_URL%%COLOR_NORMAL%
    echo %COLOR_CYAN%Target location: %MY_ZIP_FILE%%COLOR_NORMAL%
    echo %COLOR_CYAN%Extraction directory: %MY_EXTRACT_DIR%%COLOR_NORMAL%
    
    :: Check if the zip file already exists and delete it first
    if exist "!MY_ZIP_FILE!" (
        echo %COLOR_YELLOW%Found existing zip file. Deleting it before download...%COLOR_NORMAL%
        del /f /q "!MY_ZIP_FILE!"
        if !errorlevel! neq 0 (
            echo %COLOR_RED%ERROR: Failed to delete existing zip file.%COLOR_NORMAL%
            goto :error
        ) else (
            echo %COLOR_GREEN%Successfully removed existing zip file.%COLOR_NORMAL%
        )
    )
    
    echo %COLOR_CYAN%Downloading file to script directory...%COLOR_NORMAL%
    
    :: Simple curl command with explicit delayed expansion
    curl -L "!SF_URL!" -o "!MY_ZIP_FILE!" --progress-bar
    if !errorlevel! neq 0 (
        echo %COLOR_RED%ERROR: Failed to download using primary URL.%COLOR_NORMAL%
        echo %COLOR_YELLOW%Attempting fallback download...%COLOR_NORMAL%
        
        :: Fallback if the above command fails
        curl -L "https://sourceforge.net/projects/dos2unix/files/dos2unix/7.5.2/dos2unix-7.5.2-win64.zip/download" -o "!MY_ZIP_FILE!" --progress-bar
        if !errorlevel! neq 0 (
            echo %COLOR_RED%ERROR: Fallback download also failed.%COLOR_NORMAL%
            goto :error
        )
    )
    
    :: Check if file exists after download
    if exist "!MY_ZIP_FILE!" (
        echo %COLOR_GREEN%File downloaded successfully.%COLOR_NORMAL%
        
        :: Check file size
        for %%A in ("!MY_ZIP_FILE!") do set "FILE_SIZE=%%~zA"
        echo %COLOR_CYAN%Downloaded file size: !FILE_SIZE! bytes%COLOR_NORMAL%
        
        if !FILE_SIZE! LSS 10000 (
            echo %COLOR_RED%ERROR: Downloaded file is suspiciously small. May be corrupted.%COLOR_NORMAL%
            goto :error
        )
        
        :: Remove existing extract directory if it exists
        if exist "!MY_EXTRACT_DIR!" (
            echo %COLOR_YELLOW%Removing existing extract directory...%COLOR_NORMAL%
            rd /s /q "!MY_EXTRACT_DIR!"
            if !errorlevel! neq 0 (
                echo %COLOR_RED%ERROR: Failed to remove existing extract directory.%COLOR_NORMAL%
                goto :error
            ) else (
                echo %COLOR_GREEN%Successfully removed existing extract directory.%COLOR_NORMAL%
            )
        )
        
        :: Create extract directory
        mkdir "!MY_EXTRACT_DIR!"
        if !errorlevel! neq 0 (
            echo %COLOR_RED%ERROR: Failed to create extract directory.%COLOR_NORMAL%
            goto :error
        )
        
        :: Extract the zip file
        echo %COLOR_CYAN%Extracting zip file contents...%COLOR_NORMAL%
        powershell -Command "Expand-Archive -Path '!MY_ZIP_FILE!' -DestinationPath '!MY_EXTRACT_DIR!' -Force"
        if !errorlevel! neq 0 (
            echo %COLOR_RED%ERROR: Failed to extract zip file contents.%COLOR_NORMAL%
            goto :error
        ) else (
            echo %COLOR_GREEN%Successfully extracted zip file.%COLOR_NORMAL%
        )
        
        :: Check if extract worked
        if exist "!MY_EXTRACT_DIR!" (
            echo %COLOR_GREEN%Extraction completed successfully.%COLOR_NORMAL%
            
            :: Check various paths for unix2dos.exe
            set "UNIX2DOS_FOUND=0"
            
            echo %COLOR_CYAN%Searching for executables in extracted files...%COLOR_NORMAL%
            if exist "!MY_EXTRACT_DIR!\bin\unix2dos.exe" (
                set "SRC_UNIX2DOS=!MY_EXTRACT_DIR!\bin\unix2dos.exe"
                set "SRC_DOS2UNIX=!MY_EXTRACT_DIR!\bin\dos2unix.exe"
                set "UNIX2DOS_FOUND=1"
                echo %COLOR_GREEN%Found executables in bin directory.%COLOR_NORMAL%
            ) else if exist "!MY_EXTRACT_DIR!\dos2unix-7.5.2-win64\bin\unix2dos.exe" (
                set "SRC_UNIX2DOS=!MY_EXTRACT_DIR!\dos2unix-7.5.2-win64\bin\unix2dos.exe"
                set "SRC_DOS2UNIX=!MY_EXTRACT_DIR!\dos2unix-7.5.2-win64\bin\dos2unix.exe"
                set "UNIX2DOS_FOUND=1"
                echo %COLOR_GREEN%Found executables in version-specific directory.%COLOR_NORMAL%
            ) else (
                echo %COLOR_YELLOW%Searching recursively for executables...%COLOR_NORMAL%
                for /r "!MY_EXTRACT_DIR!" %%F in (unix2dos.exe) do (
                    set "SRC_UNIX2DOS=%%F"
                    set "UNIX2DOS_FOUND=1"
                    echo %COLOR_GREEN%Found unix2dos.exe at: %%F%COLOR_NORMAL%
                )
                
                for /r "!MY_EXTRACT_DIR!" %%F in (dos2unix.exe) do (
                    set "SRC_DOS2UNIX=%%F"
                    echo %COLOR_GREEN%Found dos2unix.exe at: %%F%COLOR_NORMAL%
                )
            )
            
            if "!UNIX2DOS_FOUND!"=="0" (
                echo %COLOR_RED%ERROR: Could not find unix2dos.exe in extracted files.%COLOR_NORMAL%
                echo %COLOR_YELLOW%Please try installing it manually from https://dos2unix.sourceforge.io/%COLOR_NORMAL%
                goto :error
            )
            
            :: Copy executables to git-scripts directory
            echo %COLOR_CYAN%Copying executables to git-scripts directory...%COLOR_NORMAL%
            copy "!SRC_UNIX2DOS!" "!SCRIPT_DIR!" /Y
            if !errorlevel! neq 0 (
                echo %COLOR_RED%ERROR: Failed to copy unix2dos.exe to script directory.%COLOR_NORMAL%
                goto :error
            ) else (
                echo %COLOR_GREEN%Successfully copied unix2dos.exe to script directory.%COLOR_NORMAL%
            )
            
            copy "!SRC_DOS2UNIX!" "!SCRIPT_DIR!" /Y
            if !errorlevel! neq 0 (
                echo %COLOR_RED%ERROR: Failed to copy dos2unix.exe to script directory.%COLOR_NORMAL%
                goto :error
            ) else (
                echo %COLOR_GREEN%Successfully copied dos2unix.exe to script directory.%COLOR_NORMAL%
            )
            
            :: Cleanup
            echo %COLOR_CYAN%Keeping the zip file for future use at: !MY_ZIP_FILE!%COLOR_NORMAL%
            if exist "!MY_EXTRACT_DIR!" (
                echo %COLOR_CYAN%Cleaning up temporary extraction directory...%COLOR_NORMAL%
                rd /s /q "!MY_EXTRACT_DIR!"
                if !errorlevel! neq 0 (
                    echo %COLOR_YELLOW%WARNING: Failed to remove temporary extraction directory.%COLOR_NORMAL%
                ) else (
                    echo %COLOR_GREEN%Successfully removed temporary extraction directory.%COLOR_NORMAL%
                )
            )
            
            :: Update command variables
            set "UNIX2DOS_CMD=!SCRIPT_DIR!unix2dos.exe"
            set "DOS2UNIX_CMD=!SCRIPT_DIR!dos2unix.exe"
            
            echo %COLOR_GREEN%Tools successfully installed to script directory.%COLOR_NORMAL%
        ) else (
            echo %COLOR_RED%ERROR: Extraction failed - extraction directory not found.%COLOR_NORMAL%
            goto :error
        )
    ) else (
        echo %COLOR_RED%ERROR: Download failed - zip file not found.%COLOR_NORMAL%
        goto :error
    )
)

:: After download, prefer using local copy if it was downloaded
if exist "!SCRIPT_DIR!unix2dos.exe" (
    set "UNIX2DOS_CMD=!SCRIPT_DIR!unix2dos.exe"
)

if exist "!SCRIPT_DIR!dos2unix.exe" (
    set "DOS2UNIX_CMD=!SCRIPT_DIR!dos2unix.exe"
)

echo.
echo %COLOR_BLUE%==== Status Summary ====%COLOR_NORMAL%
echo %COLOR_CYAN%The executables are located at:%COLOR_NORMAL%

if /i "!UNIX2DOS_CMD!"=="unix2dos" (
    echo %COLOR_GREEN%- unix2dos: System PATH%COLOR_NORMAL%
) else if not "!UNIX2DOS_CMD!"=="" (
    echo %COLOR_GREEN%- unix2dos: !UNIX2DOS_CMD!%COLOR_NORMAL%
) else (
    echo %COLOR_RED%- unix2dos: Not found%COLOR_NORMAL%
)

if /i "!DOS2UNIX_CMD!"=="dos2unix" (
    echo %COLOR_GREEN%- dos2unix: System PATH%COLOR_NORMAL%
) else if not "!DOS2UNIX_CMD!"=="" (
    echo %COLOR_GREEN%- dos2unix: !DOS2UNIX_CMD!%COLOR_NORMAL%
) else (
    echo %COLOR_RED%- dos2unix: Not found%COLOR_NORMAL%
)

:: Export variables
set "UNIX2DOS_PATH=!UNIX2DOS_CMD!"
set "DOS2UNIX_PATH=!DOS2UNIX_CMD!"

echo.
echo %COLOR_CYAN%unix2dos path: !UNIX2DOS_PATH!%COLOR_NORMAL%
if defined DOS2UNIX_PATH echo %COLOR_CYAN%dos2unix path: !DOS2UNIX_PATH!%COLOR_NORMAL%

goto :success

:error
echo.
echo %COLOR_RED%An error occurred during the process.%COLOR_NORMAL%
echo %COLOR_RED%Please check the error messages above and try again.%COLOR_NORMAL%
exit /b 1

:success
echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%UNIX2DOS/DOS2UNIX SETUP COMPLETED SUCCESSFULLY%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo %COLOR_CYAN%All tools are ready to use.%COLOR_NORMAL%

exit /b 0 