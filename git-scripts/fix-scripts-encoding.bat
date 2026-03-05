@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=Scripts Line Ending Fixer Utility v%SCRIPT_VERSION%"

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
echo %COLOR_CYAN%This script converts script files to appropriate line endings:%COLOR_NORMAL%
echo %COLOR_CYAN%- Windows files (.bat, .cmd, .ps1, .py): CRLF format (Windows)%COLOR_NORMAL%
echo %COLOR_CYAN%- Shell scripts (.sh): LF format (Unix)%COLOR_NORMAL%
echo.

:: Set working directory to parent of repo root to scan the whole repository
set "SCRIPT_DIR=%~dp0"
set "REPO_ROOT=%SCRIPT_DIR%.."
set "PARENT_DIR=%REPO_ROOT%"
cd /d "%PARENT_DIR%"

echo %COLOR_CYAN%Starting from parent directory: %CD%%COLOR_NORMAL%
echo %COLOR_CYAN%Repository location: %REPO_ROOT%%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Checking for Required Tools ====%COLOR_NORMAL%

:: Ensure unix2dos is available by running the download script
echo %COLOR_CYAN%Checking for unix2dos and dos2unix tools...%COLOR_NORMAL%
call "%SCRIPT_DIR%\download_unix2dos.bat"
if %ERRORLEVEL% neq 0 (
    echo %COLOR_RED%ERROR: Failed to ensure unix2dos/dos2unix is available.%COLOR_NORMAL%
    echo %COLOR_RED%Please check the error messages and try again.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%Successfully verified availability of unix2dos/dos2unix tools.%COLOR_NORMAL%
)

:: Ask user which version to use
echo.
echo %COLOR_BLUE%==== Tool Selection ====%COLOR_NORMAL%
echo %COLOR_YELLOW%Which version of unix2dos/dos2unix would you like to use?%COLOR_NORMAL%
echo   %COLOR_WHITE%1. Use local copy from git-scripts directory (recommended)%COLOR_NORMAL%
echo   %COLOR_WHITE%2. Use system-installed version from PATH%COLOR_NORMAL%
choice /c 12 /n /m "%COLOR_CYAN%Enter your choice (1 or 2): %COLOR_NORMAL%"

:: Set tools based on user choice
set "USE_LOCAL=0"
if errorlevel 2 (
    echo %COLOR_CYAN%You chose to use system-installed tools.%COLOR_NORMAL%
    set "USE_LOCAL=0"
) else if errorlevel 1 (
    echo %COLOR_CYAN%You chose to use local tools from git-scripts directory.%COLOR_NORMAL%
    set "USE_LOCAL=1"
)
echo.

echo %COLOR_BLUE%==== Setting Up Tools ====%COLOR_NORMAL%

:: Determine the commands to use based on user preference
if "%USE_LOCAL%"=="1" (
    :: Use local copy if available
    if exist "%SCRIPT_DIR%\unix2dos.exe" (
        set "UNIX2DOS_CMD=%SCRIPT_DIR%\unix2dos.exe"
        echo %COLOR_GREEN%Using locally installed unix2dos: !UNIX2DOS_CMD!%COLOR_NORMAL%
    ) else (
        echo %COLOR_YELLOW%WARNING: Local unix2dos not found despite preference. Falling back to system.%COLOR_NORMAL%
        set "UNIX2DOS_CMD=unix2dos"
        echo %COLOR_CYAN%Using system-installed unix2dos%COLOR_NORMAL%
    )
    
    if exist "%SCRIPT_DIR%\dos2unix.exe" (
        set "DOS2UNIX_CMD=%SCRIPT_DIR%\dos2unix.exe"
        echo %COLOR_GREEN%Using locally installed dos2unix: !DOS2UNIX_CMD!%COLOR_NORMAL%
    ) else (
        echo %COLOR_YELLOW%WARNING: Local dos2unix not found despite preference. Falling back to system.%COLOR_NORMAL%
        set "DOS2UNIX_CMD=dos2unix"
        echo %COLOR_CYAN%Using system-installed dos2unix%COLOR_NORMAL%
    )
) else (
    :: Use system installation
    where unix2dos >nul 2>&1
    if %ERRORLEVEL% neq 0 (
        echo %COLOR_YELLOW%WARNING: System unix2dos not found. Falling back to local installation.%COLOR_NORMAL%
        if exist "%SCRIPT_DIR%\unix2dos.exe" (
            set "UNIX2DOS_CMD=%SCRIPT_DIR%\unix2dos.exe"
            echo %COLOR_GREEN%Using locally installed unix2dos: !UNIX2DOS_CMD!%COLOR_NORMAL%
        ) else (
            echo %COLOR_RED%ERROR: unix2dos not found in system or locally.%COLOR_NORMAL%
            goto :error
        )
    ) else (
        set "UNIX2DOS_CMD=unix2dos"
        echo %COLOR_GREEN%Using system-installed unix2dos from:%COLOR_NORMAL%
        where unix2dos
    )
    
    where dos2unix >nul 2>&1
    if %ERRORLEVEL% neq 0 (
        echo %COLOR_YELLOW%WARNING: System dos2unix not found. Falling back to local installation.%COLOR_NORMAL%
        if exist "%SCRIPT_DIR%\dos2unix.exe" (
            set "DOS2UNIX_CMD=%SCRIPT_DIR%\dos2unix.exe"
            echo %COLOR_GREEN%Using locally installed dos2unix: !DOS2UNIX_CMD!%COLOR_NORMAL%
        ) else (
            echo %COLOR_RED%ERROR: dos2unix not found in system or locally.%COLOR_NORMAL%
            goto :error
        )
    ) else (
        set "DOS2UNIX_CMD=dos2unix"
        echo %COLOR_GREEN%Using system-installed dos2unix from:%COLOR_NORMAL%
        where dos2unix
    )
)

:: Verify tools existence and execution
echo %COLOR_CYAN%Verifying tools can be executed...%COLOR_NORMAL%

:: Test unix2dos
"!UNIX2DOS_CMD!" --version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo %COLOR_RED%ERROR: unix2dos tool does not work correctly.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%unix2dos tool verified successfully.%COLOR_NORMAL%
)

:: Test dos2unix
"!DOS2UNIX_CMD!" --version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo %COLOR_RED%ERROR: dos2unix tool does not work correctly.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%dos2unix tool verified successfully.%COLOR_NORMAL%
)

echo.
echo %COLOR_BLUE%==== Starting File Conversion ====%COLOR_NORMAL%
echo %COLOR_CYAN%Starting conversion of files to appropriate format...%COLOR_NORMAL%
echo %COLOR_CYAN%This will process scripts in and above the repository directory.%COLOR_NORMAL%
echo.

:: Create a list of directories to process
echo %COLOR_CYAN%Finding all script files...%COLOR_NORMAL%
set "DIRS_TO_SCAN=%PARENT_DIR%"
set "windows_count=0"
set "unix_count=0"

:: Process Windows batch files (.bat, .cmd) - Convert to Windows format (CRLF)
echo %COLOR_BLUE%==== Processing Windows Batch Files ====%COLOR_NORMAL%
echo %COLOR_CYAN%Converting .bat and .cmd files to CRLF format...%COLOR_NORMAL%

for /r "%PARENT_DIR%" %%F in (*.bat *.cmd) do (
    echo %COLOR_WHITE%Converting to Windows format: %%F%COLOR_NORMAL%
    "!UNIX2DOS_CMD!" -f "%%F" >nul 2>&1
    if !errorlevel! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to convert %%F%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%  SUCCESS: Converted to CRLF format%COLOR_NORMAL%
        set /a windows_count+=1
    )
)

:: Process PowerShell scripts (.ps1) - Convert to Windows format (CRLF)
echo.
echo %COLOR_BLUE%==== Processing PowerShell Scripts ====%COLOR_NORMAL%
echo %COLOR_CYAN%Converting .ps1 files to CRLF format...%COLOR_NORMAL%

for /r "%PARENT_DIR%" %%F in (*.ps1) do (
    echo %COLOR_WHITE%Converting to Windows format: %%F%COLOR_NORMAL%
    "!UNIX2DOS_CMD!" -f "%%F" >nul 2>&1
    if !errorlevel! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to convert %%F%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%  SUCCESS: Converted to CRLF format%COLOR_NORMAL%
        set /a windows_count+=1
    )
)

:: Process Python scripts (.py) - Convert to Windows format (CRLF)
echo.
echo %COLOR_BLUE%==== Processing Python Scripts ====%COLOR_NORMAL%
echo %COLOR_CYAN%Converting .py files to CRLF format...%COLOR_NORMAL%

for /r "%PARENT_DIR%" %%F in (*.py) do (
    echo %COLOR_WHITE%Converting to Windows format: %%F%COLOR_NORMAL%
    "!UNIX2DOS_CMD!" -f "%%F" >nul 2>&1
    if !errorlevel! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to convert %%F%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%  SUCCESS: Converted to CRLF format%COLOR_NORMAL%
        set /a windows_count+=1
    )
)

:: Process Shell scripts (.sh) - Convert to Unix format (LF)
echo.
echo %COLOR_BLUE%==== Processing Shell Scripts ====%COLOR_NORMAL%
echo %COLOR_CYAN%Converting .sh files to LF format...%COLOR_NORMAL%

for /r "%PARENT_DIR%" %%F in (*.sh) do (
    echo %COLOR_WHITE%Converting to Unix format: %%F%COLOR_NORMAL%
    "!DOS2UNIX_CMD!" -f "%%F" >nul 2>&1
    if !errorlevel! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to convert %%F%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%  SUCCESS: Converted to LF format%COLOR_NORMAL%
        set /a unix_count+=1
    )
)

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%CONVERSION COMPLETE!%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo %COLOR_CYAN%All script files have been converted to appropriate formats:%COLOR_NORMAL%
echo %COLOR_CYAN%- Windows scripts (.bat, .cmd, .ps1, .py): CRLF format%COLOR_NORMAL%
echo %COLOR_CYAN%- Shell scripts (.sh): LF format%COLOR_NORMAL%
echo.
echo %COLOR_GREEN%Conversion summary:%COLOR_NORMAL%
echo %COLOR_GREEN%- Windows format (CRLF): %windows_count% files%COLOR_NORMAL%
echo %COLOR_GREEN%- Unix format (LF): %unix_count% files%COLOR_NORMAL%
set /a total_count=windows_count+unix_count
echo %COLOR_GREEN%- Total: %windows_count% + %unix_count% = !total_count! files%COLOR_NORMAL%
echo.
echo %COLOR_CYAN%Processed directories:%COLOR_NORMAL%
echo %COLOR_CYAN%- %PARENT_DIR% (and all subdirectories)%COLOR_NORMAL%

goto :success

:error
echo.
echo %COLOR_RED%An error occurred during the process.%COLOR_NORMAL%
echo %COLOR_RED%Please check the error messages above and try again.%COLOR_NORMAL%
exit /b 1

:success
echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%SCRIPT ENCODING FIX COMPLETED SUCCESSFULLY%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%

echo.
echo Press any key to exit...
pause > nul
exit /b 0 