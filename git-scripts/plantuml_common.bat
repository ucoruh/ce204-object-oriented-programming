@echo off
:: UTF-8 karakter kodlaması için konsol kod sayfasını ayarla
chcp 65001 >nul
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"

:: Check if format parameter is provided
if "%~1"=="" (
    echo ERROR: No output format specified.
    echo Usage: plantuml_common.bat [format] [format_name]
    echo Example: plantuml_common.bat png "PNG"
    exit /b 1
)

:: Set format parameters from command line
set "FORMAT=%~1"
set "FORMAT_NAME=%~2"
set "SCRIPT_TITLE=PlantUML %FORMAT_NAME% Generator v%SCRIPT_VERSION%"

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

:: Change to script directory
echo %COLOR_CYAN%Changing to script directory...%COLOR_NORMAL%
cd /d "%~dp0"
echo %COLOR_GREEN%Changed to: %CD%%COLOR_NORMAL%

:: Change to parent directory (repository root)
echo %COLOR_CYAN%Changing to repository root directory...%COLOR_NORMAL%
cd ..
echo %COLOR_GREEN%Changed to: %CD%%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Setting Up Graphviz ====%COLOR_NORMAL%

:: Set the path to Graphviz - check both locations
if exist "C:\ProgramData\chocolatey\bin\dot.exe" (
    SET GRAPHVIZ_DOT="C:\ProgramData\chocolatey\bin\dot.exe"
    echo %COLOR_GREEN%Found Graphviz at Chocolatey location.%COLOR_NORMAL%
) else if exist "C:\Program Files (x86)\Graphviz2.38\bin\dot.exe" (
    SET GRAPHVIZ_DOT="C:\Program Files (x86)\Graphviz2.38\bin\dot.exe"
    echo %COLOR_GREEN%Found Graphviz at standard installation location.%COLOR_NORMAL%
) else (
    echo %COLOR_YELLOW%WARNING: Could not find Graphviz dot.exe in standard locations.%COLOR_NORMAL%
    echo %COLOR_YELLOW%Will attempt to use PlantUML's built-in Graphviz.%COLOR_NORMAL%
)

:: Check if PlantUML JAR exists
if not exist "%~dp0plantuml.jar" (
    echo %COLOR_RED%ERROR: PlantUML JAR file not found at %~dp0plantuml.jar%COLOR_NORMAL%
    echo %COLOR_RED%Please run download_plantuml.bat first.%COLOR_NORMAL%
    goto :error
)

:: Set the absolute path to the JAR file
set "PLANTUML_JAR=%~dp0plantuml.jar"
echo %COLOR_CYAN%Using Graphviz at: %GRAPHVIZ_DOT%%COLOR_NORMAL%
echo %COLOR_CYAN%Using PlantUML at: %PLANTUML_JAR%%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Generating %FORMAT_NAME% Diagrams ====%COLOR_NORMAL%
echo %COLOR_CYAN%Searching for PlantUML files to convert...%COLOR_NORMAL%

:: Create a temp file to store results - SIMPLIFIED VERSION
set "temp_file=%TEMP%\puml_files_%RANDOM%.txt"
type nul > "%temp_file%"

:: Search for all .puml files in the current directory and all subdirectories
echo %COLOR_CYAN%Looking for PlantUML files across the entire repository...%COLOR_NORMAL%

:: Search all subdirectories for .puml files
for /r %%F in (*.puml) do (
    echo %%F >> "%temp_file%"
)

:: Count how many files we found (silently check if file is empty)
set "file_count=0"
for /f %%A in ('type "%temp_file%" ^| find /v /c ""') do (
    set "file_count=%%A"
)

:: If we found no files, tell the user and exit
if %file_count% equ 0 (
    echo %COLOR_YELLOW%WARNING: No .puml files found in the repository.%COLOR_NORMAL%
    echo %COLOR_CYAN%Tips for troubleshooting:%COLOR_NORMAL%
    echo %COLOR_CYAN%1. Check if your PlantUML files have a .puml extension%COLOR_NORMAL%
    echo %COLOR_CYAN%2. Try running the script from the repository root directory%COLOR_NORMAL%
    del "%temp_file%" 2>nul
    goto :no_files
)

:: Display found files
echo %COLOR_GREEN%Found %file_count% PlantUML files to process:%COLOR_NORMAL%

:: Show at most 5 files
set "display_count=0"
for /f "usebackq delims=" %%f in ("%temp_file%") do (
    set /a "display_count+=1"
    if !display_count! leq 5 (
        echo %COLOR_CYAN%  - %%f%COLOR_NORMAL%
    ) else if !display_count! equ 6 (
        echo %COLOR_CYAN%  ... and more files%COLOR_NORMAL%
        goto :file_list_done
    )
)
:file_list_done

echo.
echo %COLOR_CYAN%Running PlantUML to generate %FORMAT_NAME% diagrams...%COLOR_NORMAL%

:: Process each file individually for better error handling
set "success_count=0"
set "error_count=0"

for /f "usebackq delims=" %%f in ("%temp_file%") do (
    echo %COLOR_WHITE%Processing: %%f%COLOR_NORMAL%
    :: UTF-8 karakter desteği için -Dfile.encoding=UTF-8 eklendi
    java -Dfile.encoding=UTF-8 -Xmx1024m -jar "%PLANTUML_JAR%" -charset UTF-8 -t%FORMAT% "%%f"
    if !ERRORLEVEL! neq 0 (
        echo %COLOR_RED%  ERROR: Failed to process %%f%COLOR_NORMAL%
        set /a "error_count+=1"
    ) else (
        echo %COLOR_GREEN%  SUCCESS: Generated %FORMAT_NAME% for %%f%COLOR_NORMAL%
        set /a "success_count+=1"

        :: Post-processing: Fix filename if PlantUML created "file.puml .ext" instead of "file.ext"
        set "input_file=%%f"
        set "dir_path=%%~dpf"
        set "base_name=%%~nf"
        set "wrong_output=!dir_path!!base_name!.puml .%FORMAT%"
        set "correct_output=!dir_path!!base_name!.%FORMAT%"

        :: Check if wrong filename exists and rename it
        if exist "!wrong_output!" (
            echo %COLOR_YELLOW%  Fixing filename: !base_name!.puml .%FORMAT% -^> !base_name!.%FORMAT%%COLOR_NORMAL%
            move /Y "!wrong_output!" "!correct_output!" >nul 2>&1
            if !ERRORLEVEL! equ 0 (
                echo %COLOR_GREEN%  Filename corrected successfully%COLOR_NORMAL%
            ) else (
                echo %COLOR_RED%  WARNING: Failed to rename file%COLOR_NORMAL%
            )
        )
    )
)

:: Clean up temporary file
del "%temp_file%" 2>nul

if %error_count% gtr 0 (
    echo %COLOR_RED%ERROR: PlantUML execution failed for %error_count% files.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%PlantUML %FORMAT_NAME% diagram generation completed successfully for all %success_count% files.%COLOR_NORMAL%
)

goto :success

:no_files
echo %COLOR_YELLOW%No PlantUML files to process. Please create .puml files first.%COLOR_NORMAL%
goto :cleanup

:error
echo.
echo %COLOR_RED%An error occurred during the diagram generation process.%COLOR_NORMAL%
echo %COLOR_RED%Please check the error messages above and try again.%COLOR_NORMAL%
goto :cleanup

:success
echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%PLANTUML %FORMAT_NAME% GENERATION COMPLETED SUCCESSFULLY%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo %COLOR_CYAN%%FORMAT_NAME% diagrams have been generated from your PlantUML files.%COLOR_NORMAL%
echo %COLOR_CYAN%You can find them in the same directory as your .puml files.%COLOR_NORMAL%

:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd /d "%currentDir%"

echo.
echo Press any key to exit...
pause > nul
exit /b 0 