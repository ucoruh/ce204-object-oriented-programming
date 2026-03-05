@echo off
setlocal enableextensions enabledelayedexpansion

:: ========== SCRIPT CONFIGURATION ==========
set "SCRIPT_VERSION=1.0"
set "SCRIPT_TITLE=PlantUML Downloader Utility v%SCRIPT_VERSION%"

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

:: PlantUML version configuration
set use_latest=0
:: set specific_version=tags/v1.2024.4
:: set specific_version=tags/v1.2024.6
set specific_version=tags/v1.2025.1

:: ========== MAIN PROCESS ==========
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_MAGENTA%%SCRIPT_TITLE%%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.

:: Save current directory
set "currentDir=%CD%"
echo %COLOR_CYAN%Current directory saved: %currentDir%%COLOR_NORMAL%

echo.
echo %COLOR_BLUE%==== Downloading PlantUML ====%COLOR_NORMAL%

:: Clean previous PlantUML JAR if exists
echo %COLOR_CYAN%Checking for existing PlantUML installation...%COLOR_NORMAL%
if exist plantuml.jar (
    echo %COLOR_YELLOW%Found existing plantuml.jar - removing...%COLOR_NORMAL%
    del plantuml.jar
    if !errorlevel! neq 0 (
        echo %COLOR_RED%ERROR: Failed to delete existing plantuml.jar.%COLOR_NORMAL%
        goto :error
    ) else (
        echo %COLOR_GREEN%Successfully removed existing plantuml.jar.%COLOR_NORMAL%
    )
) else (
    echo %COLOR_CYAN%No existing plantuml.jar found.%COLOR_NORMAL%
)

:: Download and install JQ
echo.
echo %COLOR_BLUE%==== Installing JQ JSON Parser ====%COLOR_NORMAL%
echo %COLOR_CYAN%Downloading jq.exe...%COLOR_NORMAL%
curl -sL -o jq.exe https://github.com/stedolan/jq/releases/download/jq-1.6/jq-win64.exe
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to download jq.exe.%COLOR_NORMAL%
    echo %COLOR_RED%Please check your internet connection or try again later.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%Successfully downloaded jq.exe.%COLOR_NORMAL%
)

:: Get PlantUML download URL
echo.
echo %COLOR_BLUE%==== Retrieving PlantUML Download URL ====%COLOR_NORMAL%

if %use_latest%==1 (
    echo %COLOR_CYAN%Extracting download URL for the latest release of PlantUML...%COLOR_NORMAL%
    for /f "delims=" %%a in ('curl -s https://api.github.com/repos/plantuml/plantuml/releases/latest ^| jq -r ".assets[] | select(.name | endswith(\"plantuml.jar\")) | .browser_download_url"') do (
        set download_url=%%a
    )
) else (
    echo %COLOR_CYAN%Extracting download URL for version %specific_version% of PlantUML...%COLOR_NORMAL%
    for /f "delims=" %%a in ('curl -s https://api.github.com/repos/plantuml/plantuml/releases/%specific_version% ^| jq -r ".assets[] | select(.name | endswith(\"plantuml.jar\")) | .browser_download_url"') do (
        set download_url=%%a
    )
)

:: Check if download URL was obtained
if not defined download_url (
    echo %COLOR_RED%ERROR: Could not determine PlantUML download URL.%COLOR_NORMAL%
    echo %COLOR_RED%Either the API request failed or this version does not exist.%COLOR_NORMAL%
    goto :cleanup_jq
)

echo %COLOR_CYAN%Download URL: %download_url%%COLOR_NORMAL%

:: Download PlantUML JAR
echo.
echo %COLOR_BLUE%==== Downloading PlantUML JAR ====%COLOR_NORMAL%
echo %COLOR_CYAN%Downloading plantuml.jar...%COLOR_NORMAL%
curl -sL -o plantuml.jar "%download_url%"
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to download plantuml.jar.%COLOR_NORMAL%
    echo %COLOR_RED%Please check your internet connection or try again later.%COLOR_NORMAL%
    goto :cleanup_jq
) else (
    if not exist plantuml.jar (
        echo %COLOR_RED%ERROR: plantuml.jar was not downloaded correctly.%COLOR_NORMAL%
        goto :cleanup_jq
    ) else (
        echo %COLOR_GREEN%Successfully downloaded plantuml.jar.%COLOR_NORMAL%
    )
)

:: Clean up JQ
:cleanup_jq
echo.
echo %COLOR_BLUE%==== Cleanup Temporary Files ====%COLOR_NORMAL%
echo %COLOR_CYAN%Removing jq.exe...%COLOR_NORMAL%
if exist jq.exe (
    del jq.exe
    if !errorlevel! neq 0 (
        echo %COLOR_YELLOW%WARNING: Could not delete jq.exe.%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%Successfully removed jq.exe.%COLOR_NORMAL%
    )
)

:: Download additional components
echo.
echo %COLOR_BLUE%==== Downloading JLatexMath ====%COLOR_NORMAL%
echo %COLOR_CYAN%Downloading JLatexMath...%COLOR_NORMAL%
curl -sL -o jlatexmath.zip http://beta.plantuml.net/plantuml-jlatexmath.zip
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to download JLatexMath.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%Successfully downloaded JLatexMath.%COLOR_NORMAL%
)

echo %COLOR_CYAN%Extracting JLatexMath...%COLOR_NORMAL%
powershell -Command "Expand-Archive -Path jlatexmath.zip -DestinationPath . -Force"
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to extract JLatexMath.%COLOR_NORMAL%
    goto :cleanup_jlatexmath
) else (
    echo %COLOR_GREEN%Successfully extracted JLatexMath.%COLOR_NORMAL%
)

:cleanup_jlatexmath
echo %COLOR_CYAN%Removing jlatexmath.zip...%COLOR_NORMAL%
if exist jlatexmath.zip (
    del jlatexmath.zip
    if !errorlevel! neq 0 (
        echo %COLOR_YELLOW%WARNING: Could not delete jlatexmath.zip.%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%Successfully removed jlatexmath.zip.%COLOR_NORMAL%
    )
)

echo.
echo %COLOR_BLUE%==== Downloading Batik and Fop ====%COLOR_NORMAL%
echo %COLOR_CYAN%Downloading Batik and Fop...%COLOR_NORMAL%
curl -sL -o batikAndFop.zip http://beta.plantuml.net/batikAndFop.zip
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to download Batik and Fop.%COLOR_NORMAL%
    goto :error
) else (
    echo %COLOR_GREEN%Successfully downloaded Batik and Fop.%COLOR_NORMAL%
)

echo %COLOR_CYAN%Extracting Batik and Fop...%COLOR_NORMAL%
powershell -Command "Expand-Archive -Path batikAndFop.zip -DestinationPath . -Force"
if %errorlevel% neq 0 (
    echo %COLOR_RED%ERROR: Failed to extract Batik and Fop.%COLOR_NORMAL%
    goto :cleanup_batik
) else (
    echo %COLOR_GREEN%Successfully extracted Batik and Fop.%COLOR_NORMAL%
)

:cleanup_batik
echo %COLOR_CYAN%Removing batikAndFop.zip...%COLOR_NORMAL%
if exist batikAndFop.zip (
    del batikAndFop.zip
    if !errorlevel! neq 0 (
        echo %COLOR_YELLOW%WARNING: Could not delete batikAndFop.zip.%COLOR_NORMAL%
    ) else (
        echo %COLOR_GREEN%Successfully removed batikAndFop.zip.%COLOR_NORMAL%
    )
)

:: Verify components
echo.
echo %COLOR_BLUE%==== Verifying Installation ====%COLOR_NORMAL%
echo %COLOR_CYAN%Checking for required JAR files...%COLOR_NORMAL%

set missing_files=0
set "jar_files=batik-all-1.7.jar jlatexmath-minimal-1.0.3.jar jlm_cyrillic.jar jlm_greek.jar avalon-framework-4.2.0.jar commons-io-1.3.1.jar commons-logging-1.0.4.jar fop.jar xml-apis-ext-1.3.04.jar xmlgraphics-commons-1.4.jar"

for %%j in (%jar_files%) do (
    if not exist "%%j" (
        echo %COLOR_RED%Missing: %%j%COLOR_NORMAL%
        set /a missing_files+=1
    ) else (
        echo %COLOR_GREEN%Found: %%j%COLOR_NORMAL%
    )
)

if %missing_files% gtr 0 (
    echo.
    echo %COLOR_YELLOW%WARNING: %missing_files% required JAR files are missing.%COLOR_NORMAL%
    echo %COLOR_YELLOW%PlantUML may not function properly for all diagram types.%COLOR_NORMAL%
) else (
    echo.
    echo %COLOR_GREEN%All required JAR files are present.%COLOR_NORMAL%
)

goto :success

:error
echo.
echo %COLOR_RED%An error occurred during the download or extraction process.%COLOR_NORMAL%
echo %COLOR_RED%Please check the error messages above and try again.%COLOR_NORMAL%
goto :cleanup

:success
echo.
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo %COLOR_GREEN%PLANTUML DOWNLOAD COMPLETED SUCCESSFULLY%COLOR_NORMAL%
echo %COLOR_BLUE%=======================================================%COLOR_NORMAL%
echo.
echo %COLOR_CYAN%You can now generate diagrams with the included PlantUML JAR.%COLOR_NORMAL%
echo %COLOR_CYAN%Use the run_plantuml_png.bat or run_plantuml_svg.bat scripts to generate diagrams.%COLOR_NORMAL%

:cleanup
echo.
echo %COLOR_CYAN%Returning to original directory: %currentDir%%COLOR_NORMAL%
cd /d "%currentDir%"

echo.
echo Press any key to exit...
pause > nul
endlocal
