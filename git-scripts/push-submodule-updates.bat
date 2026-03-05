@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=Git Repository Submodule Push Utility v%SCRIPT_VERSION%"

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
set "PUSH_ALL=0"
set "INTERACTIVE=0"

:parse_args
if "%~1"=="" goto start_main
if /i "%~1"=="--all" set "PUSH_ALL=1" & goto next_arg
if /i "%~1"=="--help" goto show_help
if /i "%~1"=="-a" set "PUSH_ALL=1" & goto next_arg
if /i "%~1"=="-h" goto show_help
if /i "%~1"=="-i" set "INTERACTIVE=1" & goto next_arg
if /i "%~1"=="--interactive" set "INTERACTIVE=1" & goto next_arg

:next_arg
shift
goto parse_args

:show_help
echo.
echo %COLOR_BLUE%Usage: push-submodule-updates.bat [options]%COLOR_NORMAL%
echo.
echo %COLOR_WHITE%Options:%COLOR_NORMAL%
echo   %COLOR_CYAN%-a, --all%COLOR_NORMAL%          Push all submodules, not just changed ones
echo   %COLOR_CYAN%-i, --interactive%COLOR_NORMAL%  Interactive mode, prompt for each submodule
echo   %COLOR_CYAN%-h, --help%COLOR_NORMAL%         Show this help message
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
    echo %COLOR_YELLOW%WARNING: No .gitmodules file found. There are no submodules configured.%COLOR_NORMAL%
    set "ERROR_OCCURRED=1"
    goto :cleanup
)

:: Count submodules
for /f "tokens=1" %%i in ('git config --file .gitmodules --get-regexp path ^| wc -l 2^>nul') do set "SUBMODULE_COUNT=%%i"
if "%SUBMODULE_COUNT%"=="" set "SUBMODULE_COUNT=0"

if %SUBMODULE_COUNT% equ 0 (
    echo %COLOR_YELLOW%WARNING: No submodules defined in .gitmodules%COLOR_NORMAL%
    set "ERROR_OCCURRED=1"
    goto :cleanup
) else (
    echo %COLOR_CYAN%Found %SUBMODULE_COUNT% submodule(s) configured in this repository%COLOR_NORMAL%
)

:: Get list of modified submodules
echo.
echo %COLOR_BLUE%==== Checking Modified Submodules ====%COLOR_NORMAL%
echo %COLOR_CYAN%Looking for submodules with local changes...%COLOR_NORMAL%

set "MODIFIED_COUNT=0"
set "modified_modules="

if %PUSH_ALL% equ 1 (
    echo %COLOR_CYAN%--all option specified, will process all submodules%COLOR_NORMAL%
    set "modified_modules=all"
) else (
    :: Check git status for modified submodules
    for /f "tokens=2" %%s in ('git config --file .gitmodules --get-regexp path') do (
        set "submodule_path=%%s"
        
        :: Check if this entry in the index has changed
        git diff --name-only --cached "!submodule_path!" >nul 2>&1
        if !errorlevel! equ 0 (
            echo   %COLOR_WHITE%Found modified submodule: !submodule_path!%COLOR_NORMAL%
            set /a MODIFIED_COUNT+=1
            set "modified_modules=!modified_modules! !submodule_path!"
        ) else (
            :: Check if working tree has changes
            git diff --name-only "!submodule_path!" >nul 2>&1
            if !errorlevel! equ 0 (
                echo   %COLOR_WHITE%Found modified submodule: !submodule_path!%COLOR_NORMAL%
                set /a MODIFIED_COUNT+=1
                set "modified_modules=!modified_modules! !submodule_path!"
            )
        )
    )
)

if not "%modified_modules%"=="all" (
    if %MODIFIED_COUNT% equ 0 (
        echo %COLOR_YELLOW%No modified submodules found.%COLOR_NORMAL%
        echo %COLOR_YELLOW%If you know there are changes, use --all to push all submodules.%COLOR_NORMAL%
        goto :cleanup
    )
)

echo.
echo %COLOR_BLUE%==== Pushing Submodule Changes ====%COLOR_NORMAL%

:: Process each submodule for push
set "PUSHED_COUNT=0"
set "FAILED_COUNT=0"

for /f "tokens=2" %%s in ('git config --file .gitmodules --get-regexp path') do (
    set "submodule_path=%%s"
    set "process_this=0"
    
    :: Determine if we should process this submodule
    if "%modified_modules%"=="all" (
        set "process_this=1"
    ) else (
        echo !modified_modules! | findstr /C:"!submodule_path!" >nul
        if !errorlevel! equ 0 (
            set "process_this=1"
        )
    )
    
    if !process_this! equ 1 (
        echo %COLOR_CYAN%Processing submodule: !submodule_path!%COLOR_NORMAL%
        
        :: Check if submodule directory exists
        if exist "!submodule_path!\.git" (
            :: Check if we should prompt user in interactive mode
            set "should_push=1"
            if %INTERACTIVE% equ 1 (
                echo   %COLOR_WHITE%Submodule has changes. Push to remote?%COLOR_NORMAL%
                choice /c YN /n /m "%COLOR_CYAN%  Push !submodule_path! changes? (Y/N): %COLOR_NORMAL%"
                if !errorlevel! equ 2 (
                    set "should_push=0"
                    echo   %COLOR_YELLOW%  Skipping !submodule_path! as requested%COLOR_NORMAL%
                )
            )
            
            if !should_push! equ 1 (
                :: Change to submodule directory
                pushd "!submodule_path!"
                
                :: First, make sure we have all changes committed in the submodule
                git status | findstr /C:"nothing to commit" >nul
                if !errorlevel! neq 0 (
                    echo   %COLOR_YELLOW%  WARNING: Uncommitted changes in !submodule_path!%COLOR_NORMAL%
                    echo   %COLOR_YELLOW%  Please commit your changes first before pushing%COLOR_NORMAL%
                    set /a FAILED_COUNT+=1
                ) else (
                    :: Get current branch
                    for /f "tokens=*" %%b in ('git rev-parse --abbrev-ref HEAD') do set "current_branch=%%b"
                    echo   %COLOR_WHITE%  Current branch: !current_branch!%COLOR_NORMAL%
                    
                    :: Push the changes
                    echo   %COLOR_CYAN%  Pushing submodule changes to remote...%COLOR_NORMAL%
                    git push origin !current_branch!
                    if !errorlevel! neq 0 (
                        echo   %COLOR_RED%  ERROR: Failed to push !submodule_path!%COLOR_NORMAL%
                        set /a FAILED_COUNT+=1
                    ) else (
                        echo   %COLOR_GREEN%  Successfully pushed !submodule_path!%COLOR_NORMAL%
                        set /a PUSHED_COUNT+=1
                    )
                )
                
                :: Return to parent directory
                popd
            )
        ) else (
            echo   %COLOR_YELLOW%  Submodule directory does not exist or is not initialized%COLOR_NORMAL%
            echo   %COLOR_YELLOW%  Run init-submodules.bat first%COLOR_NORMAL%
            set /a FAILED_COUNT+=1
        )
        echo.
    )
)

:: Now check if we need to update the main repository with new submodule references
if %PUSHED_COUNT% gtr 0 (
    echo %COLOR_BLUE%==== Updating Main Repository ====%COLOR_NORMAL%
    echo %COLOR_CYAN%Checking if main repository needs updates...%COLOR_NORMAL%
    
    :: Get status of the submodules in the main repo
    git status -s | findstr /C:"M " > "%TEMP%\git_status_%RANDOM%.txt"
    set "main_repo_changes=0"
    
    :: Check if there are modified submodule references
    for /f "tokens=*" %%a in (%TEMP%\git_status_%RANDOM%.txt) do (
        set "line=%%a"
        echo "!line!" | findstr /C:" " >nul 2>&1
        if !errorlevel! equ 0 (
            set "main_repo_changes=1"
        )
    )
    
    if !main_repo_changes! equ 1 (
        echo %COLOR_CYAN%Main repository has updated submodule references%COLOR_NORMAL%
        
        set "should_commit=1"
        if %INTERACTIVE% equ 1 (
            choice /c YN /n /m "%COLOR_CYAN%Commit and push updated submodule references? (Y/N): %COLOR_NORMAL%"
            if !errorlevel! equ 2 (
                set "should_commit=0"
                echo %COLOR_YELLOW%Skipping main repository update as requested%COLOR_NORMAL%
            )
        )
        
        if !should_commit! equ 1 (
            echo %COLOR_CYAN%Committing submodule reference updates...%COLOR_NORMAL%
            git add -u
            git commit -m "Update submodule references"
            if !errorlevel! neq 0 (
                echo %COLOR_RED%ERROR: Failed to commit submodule reference updates%COLOR_NORMAL%
                set "ERROR_OCCURRED=1"
            ) else (
                echo %COLOR_GREEN%Successfully committed submodule reference updates%COLOR_NORMAL%
                
                echo %COLOR_CYAN%Pushing updates to remote...%COLOR_NORMAL%
                git push
                if !errorlevel! neq 0 (
                    echo %COLOR_RED%ERROR: Failed to push main repository updates%COLOR_NORMAL%
                    set "ERROR_OCCURRED=1"
                ) else (
                    echo %COLOR_GREEN%Successfully pushed main repository updates%COLOR_NORMAL%
                )
            )
        )
    ) else (
        echo %COLOR_YELLOW%No submodule reference changes detected in main repository%COLOR_NORMAL%
        echo %COLOR_YELLOW%If you just pushed submodule changes, you might need to:%COLOR_NORMAL%
        echo %COLOR_YELLOW%1. git add <submodule-path>%COLOR_NORMAL%
        echo %COLOR_YELLOW%2. git commit -m "Update submodule references"%COLOR_NORMAL%
        echo %COLOR_YELLOW%3. git push%COLOR_NORMAL%
    )
)

:: Display summary
echo.
echo %COLOR_BLUE%==== Summary ====%COLOR_NORMAL%
echo %COLOR_CYAN%Submodules processed: %MODIFIED_COUNT%%COLOR_NORMAL%
echo %COLOR_GREEN%Successfully pushed: %PUSHED_COUNT%%COLOR_NORMAL%
if %FAILED_COUNT% gtr 0 (
    echo %COLOR_RED%Failed to push: %FAILED_COUNT%%COLOR_NORMAL%
    set "ERROR_OCCURRED=1"
)

:: Clean up and return to original directory
:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd "%currentDir%"

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
if %ERROR_OCCURRED% equ 1 (
    echo %COLOR_RED%OPERATION COMPLETED WITH WARNINGS OR ERRORS%COLOR_NORMAL%
) else (
    echo %COLOR_GREEN%OPERATION COMPLETED SUCCESSFULLY%COLOR_NORMAL%
)
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo Press any key to exit...
pause > nul
endlocal 