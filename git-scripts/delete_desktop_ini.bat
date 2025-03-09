@echo off

:: Enable necessary extensions
@setlocal enableextensions

echo === DELETE GOOGLE DRIVE desktop.ini FILES ===

:: Save the current directory
set "currentDir=%CD%"
echo Current Directory: %currentDir%

:: Change the current working directory to the script directory
cd /d "%~dp0"
echo Script Directory: %CD%

:: Change the working directory to the parent folder
cd ..
echo Parent Directory: %CD%

:: Delete all desktop.ini files and remove them from Git
echo Searching for desktop.ini files...
for /r %%i in (desktop.ini) do (
    echo Found: %%i
    git rm --cached --force "%%i" >nul 2>&1
    if exist "%%i" (
        del "%%i" /A:H
        echo Deleted: %%i
    ) else (
        echo Could not delete: %%i (file might not exist)
    )
)

:: Completion message
echo === DELETE OPERATION COMPLETED ===

:: Revert to the original directory
cd "%currentDir%"
echo Reverted to Original Directory: %CD%

:: Pause to allow the user to review the output
pause
@endlocal
