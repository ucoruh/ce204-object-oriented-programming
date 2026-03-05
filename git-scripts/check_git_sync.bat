@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.3"
set "SCRIPT_TITLE=Git Repository Synchronization Check Utility v%SCRIPT_VERSION%"

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
set "COLOR_ORANGE=%ESC%[38;5;208m"
set "COLOR_LIGHT_BLUE=%ESC%[94m"
set "COLOR_LIGHT_GREEN=%ESC%[92m"
set "COLOR_GRAY=%ESC%[90m"

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

echo %COLOR_CYAN%Processed %found_files% desktop.ini files%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Checking Git Repository Status ====%COLOR_NORMAL%

:: Check for uncommitted changes and list them if found
echo %COLOR_CYAN%Checking for uncommitted changes...%COLOR_NORMAL%

:: Count different types of changes - without using a temp file
set "has_changes=0"
set "modified_count=0"
set "added_count=0"
set "deleted_count=0"
set "untracked_count=0"
set "renamed_count=0"

:: Create arrays to store filenames by category
set "modified_files="
set "added_files="
set "deleted_files="
set "untracked_files="
set "renamed_files="

:: Process git status output directly
for /f "tokens=1,2* delims= " %%a in ('git status --porcelain 2^>nul') do (
    set "has_changes=1"
    set "status=%%a"
    set "filename=%%b"
    
    if "!status:~0,1!"=="M" (
        set /a modified_count+=1
        set "modified_files=!modified_files!|!filename!"
    ) else if "!status:~0,1!"=="A" (
        set /a added_count+=1
        set "added_files=!added_files!|!filename!"
    ) else if "!status:~0,1!"=="D" (
        set /a deleted_count+=1
        set "deleted_files=!deleted_files!|!filename!"
    ) else if "!status!"=="??" (
        :: Skip temp_status.txt from counting if it exists
        if not "!filename!"=="temp_status.txt" (
            set /a untracked_count+=1
            set "untracked_files=!untracked_files!|!filename!"
        )
    ) else if "!status:~0,1!"=="R" (
        set /a renamed_count+=1
        set "renamed_files=!renamed_files!|!filename!"
    )
)

if %has_changes% equ 1 (
    echo %COLOR_YELLOW%WARNING: You have uncommitted changes%COLOR_NORMAL%
    
    :: Display summary of changes
    echo   %COLOR_WHITE%Summary of Changes:%COLOR_NORMAL%
    if !modified_count! gtr 0 echo   %COLOR_ORANGE%Modified files: !modified_count!%COLOR_NORMAL%
    if !added_count! gtr 0 echo   %COLOR_GREEN%Added files: !added_count!%COLOR_NORMAL%
    if !deleted_count! gtr 0 echo   %COLOR_RED%Deleted files: !deleted_count!%COLOR_NORMAL%
    if !untracked_count! gtr 0 echo   %COLOR_CYAN%Untracked files: !untracked_count!%COLOR_NORMAL%
    if !renamed_count! gtr 0 echo   %COLOR_BLUE%Renamed files: !renamed_count!%COLOR_NORMAL%
    
    echo.
    echo   %COLOR_WHITE%Changes by Category:%COLOR_NORMAL%
    
    :: Display modified files - IMPROVED METHOD
    if !modified_count! gtr 0 (
        echo   %COLOR_ORANGE%Modified Files:%COLOR_NORMAL%
        set "modified_list=!modified_files:~1!"
        
        :: Use a fixed limit for how many files to display
        set "display_count=0"
        set "max_display=15"
        
        :next_modified
        for /f "tokens=1* delims=|" %%a in ("!modified_list!") do (
            set /a display_count+=1
            if !display_count! leq !max_display! (
                echo     %COLOR_ORANGE%- %%a%COLOR_NORMAL%
            )
            set "modified_list=%%b"
        )
        if not "!modified_list!"=="" goto :next_modified
        
        :: Show message if we have more files than the display limit
        if !modified_count! gtr !max_display! (
            set /a remaining=!modified_count!-!max_display!
            echo     %COLOR_ORANGE%... and !remaining! more files%COLOR_NORMAL%
        )
    )
    
    :: Display added files - IMPROVED METHOD
    if !added_count! gtr 0 (
        echo   %COLOR_GREEN%Added Files:%COLOR_NORMAL%
        set "added_list=!added_files:~1!"
        
        :: Use a fixed limit for how many files to display
        set "display_count=0"
        set "max_display=15"
        
        :next_added
        for /f "tokens=1* delims=|" %%a in ("!added_list!") do (
            set /a display_count+=1
            if !display_count! leq !max_display! (
                echo     %COLOR_GREEN%+ %%a%COLOR_NORMAL%
            )
            set "added_list=%%b"
        )
        if not "!added_list!"=="" goto :next_added
        
        :: Show message if we have more files than the display limit
        if !added_count! gtr !max_display! (
            set /a remaining=!added_count!-!max_display!
            echo     %COLOR_GREEN%... and !remaining! more files%COLOR_NORMAL%
        )
    )
    
    :: Display deleted files - IMPROVED METHOD
    if !deleted_count! gtr 0 (
        echo   %COLOR_RED%Deleted Files:%COLOR_NORMAL%
        set "deleted_list=!deleted_files:~1!"
        
        :: Use a fixed limit for how many files to display
        set "display_count=0"
        set "max_display=15"
        
        :next_deleted
        for /f "tokens=1* delims=|" %%a in ("!deleted_list!") do (
            set /a display_count+=1
            if !display_count! leq !max_display! (
                echo     %COLOR_RED%- %%a%COLOR_NORMAL%
            )
            set "deleted_list=%%b"
        )
        if not "!deleted_list!"=="" goto :next_deleted
        
        :: Show message if we have more files than the display limit
        if !deleted_count! gtr !max_display! (
            set /a remaining=!deleted_count!-!max_display!
            echo     %COLOR_RED%... and !remaining! more files%COLOR_NORMAL%
        )
    )
    
    :: Display untracked files - IMPROVED METHOD
    if !untracked_count! gtr 0 (
        echo   %COLOR_CYAN%Untracked Files:%COLOR_NORMAL%
        set "untracked_list=!untracked_files:~1!"
        
        :: Use a fixed limit for how many files to display
        set "display_count=0"
        set "max_display=15"
        
        :next_untracked
        for /f "tokens=1* delims=|" %%a in ("!untracked_list!") do (
            set /a display_count+=1
            if !display_count! leq !max_display! (
                echo     %COLOR_CYAN%? %%a%COLOR_NORMAL%
            )
            set "untracked_list=%%b"
        )
        if not "!untracked_list!"=="" goto :next_untracked
        
        :: Show message if we have more files than the display limit
        if !untracked_count! gtr !max_display! (
            set /a remaining=!untracked_count!-!max_display!
            echo     %COLOR_CYAN%... and !remaining! more files%COLOR_NORMAL%
        )
    )
    
    :: Display renamed files - IMPROVED METHOD
    if !renamed_count! gtr 0 (
        echo   %COLOR_BLUE%Renamed Files:%COLOR_NORMAL%
        set "renamed_list=!renamed_files:~1!"
        
        :: Use a fixed limit for how many files to display
        set "display_count=0"
        set "max_display=15"
        
        :next_renamed
        for /f "tokens=1* delims=|" %%a in ("!renamed_list!") do (
            set /a display_count+=1
            if !display_count! leq !max_display! (
                echo     %COLOR_BLUE%* %%a%COLOR_NORMAL%
            )
            set "renamed_list=%%b"
        )
        if not "!renamed_list!"=="" goto :next_renamed
        
        :: Show message if we have more files than the display limit
        if !renamed_count! gtr !max_display! (
            set /a remaining=!renamed_count!-!max_display!
            echo     %COLOR_BLUE%... and !remaining! more files%COLOR_NORMAL%
        )
    )
    
    echo.
) else (
    echo %COLOR_GREEN%Working directory is clean - no uncommitted changes.%COLOR_NORMAL%
)

:: Fetch the latest changes from the remote repository
echo.
echo %COLOR_CYAN%Fetching updates from remote repository...%COLOR_NORMAL%
git fetch 2>nul
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to fetch from remote. Remote repository may not be configured or accessible.%COLOR_NORMAL%
    goto :sync_complete
)

:: Get the local branch name
for /f "tokens=*" %%i in ('git rev-parse --abbrev-ref HEAD 2^>nul') do set current_branch=%%i
if "%current_branch%" == "" (
    echo %COLOR_RED%ERROR: Failed to determine current branch.%COLOR_NORMAL%
    goto :sync_complete
)

:: Get full commit hashes
for /f %%i in ('git rev-parse HEAD') do set full_local_hash=%%i
for /f %%i in ('git rev-parse @{u} 2^>nul') do set full_remote_hash=%%i
if %errorlevel% neq 0 (
    echo %COLOR_YELLOW%WARNING: No upstream branch set for '%current_branch%'.%COLOR_NORMAL%
    goto :sync_complete
)

:: Get short commit hashes (7 characters)
for /f %%i in ('git rev-parse --short HEAD') do set local=%%i
for /f %%i in ('git rev-parse --short @{u} 2^>nul') do set remote=%%i
for /f %%i in ('git merge-base @ @{u} 2^>nul') do set base=%%i

:: Get timestamps and authors for the commits - FIXED FORMAT STRINGS
:: Get local commit date (simpler format)
for /f "tokens=*" %%i in ('git log -1 --format^=%%cd --date^=short HEAD') do set local_date=%%i
:: Get local commit author
for /f "tokens=*" %%i in ('git log -1 --format^=%%an HEAD') do set local_author=%%i

:: Get remote commit date
for /f "tokens=*" %%i in ('git log -1 --format^=%%cd --date^=short @{u} 2^>nul') do set remote_date=%%i
:: Get remote commit author
for /f "tokens=*" %%i in ('git log -1 --format^=%%an @{u} 2^>nul') do set remote_author=%%i

echo %COLOR_CYAN%Current branch: %COLOR_WHITE%%current_branch%%COLOR_NORMAL%
echo %COLOR_GRAY%Local commit:  %local% (%local_date% by %local_author%)%COLOR_NORMAL%
echo %COLOR_GRAY%Remote commit: %remote% (%remote_date% by %remote_author%)%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Synchronization Status ====%COLOR_NORMAL%

if "%full_local_hash%" == "%full_remote_hash%" (
    echo %COLOR_GREEN%SUCCESS: Local branch '%current_branch%' is up-to-date with the remote branch.%COLOR_NORMAL%
    echo %COLOR_GREEN%Both local and remote are at commit %local%%COLOR_NORMAL%
    echo %COLOR_GREEN%Commit date: %local_date% by %local_author%%COLOR_NORMAL%
) else if "%local%" == "%base%" (
    set "behind_count=0"
    for /f %%i in ('git rev-list --count HEAD..@{u} 2^>nul') do set behind_count=%%i
    echo %COLOR_YELLOW%WARNING: Local branch '%current_branch%' is %behind_count% commits behind the remote branch.%COLOR_NORMAL%
    echo %COLOR_YELLOW%Local is at:  %local%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Remote is at: %remote%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Local commit date: %local_date% by %local_author%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Remote commit date: %remote_date% by %remote_author%%COLOR_NORMAL%
    echo %COLOR_CYAN%Recommendation: Run 'git pull' to update your local branch.%COLOR_NORMAL%
) else if "%remote%" == "%base%" (
    set "ahead_count=0"
    for /f %%i in ('git rev-list --count @{u}..HEAD 2^>nul') do set ahead_count=%%i
    echo %COLOR_YELLOW%WARNING: Local branch '%current_branch%' is %ahead_count% commits ahead of the remote branch.%COLOR_NORMAL%
    echo %COLOR_YELLOW%Local is at:  %local%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Remote is at: %remote%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Local commit date: %local_date% by %local_author%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Remote commit date: %remote_date% by %remote_author%%COLOR_NORMAL%
    echo %COLOR_CYAN%Recommendation: Run 'git push' to update the remote branch.%COLOR_NORMAL%
) else (
    set "ahead_count=0"
    set "behind_count=0"
    for /f %%i in ('git rev-list --count @{u}..HEAD 2^>nul') do set ahead_count=%%i
    for /f %%i in ('git rev-list --count HEAD..@{u} 2^>nul') do set behind_count=%%i
    echo %COLOR_RED%ALERT: Local and remote branches have diverged.%COLOR_NORMAL%
    echo %COLOR_RED%Local is %ahead_count% commits ahead and %behind_count% commits behind the remote.%COLOR_NORMAL%
    echo %COLOR_YELLOW%Local is at:  %local%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Remote is at: %remote%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Local commit date: %local_date% by %local_author%%COLOR_NORMAL%
    echo %COLOR_YELLOW%Remote commit date: %remote_date% by %remote_author%%COLOR_NORMAL%
    echo %COLOR_CYAN%Recommendation: You may need to merge or rebase to reconcile the differences.%COLOR_NORMAL%
    echo %COLOR_CYAN%  - To merge remote changes: git pull%COLOR_NORMAL%
    echo %COLOR_CYAN%  - To rebase onto remote: git pull --rebase%COLOR_NORMAL%
)

:sync_complete
:: Clean up and return to original directory
:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd "%currentDir%"

echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%GIT SYNC CHECK COMPLETED%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo Press any key to exit...
pause > nul
endlocal
