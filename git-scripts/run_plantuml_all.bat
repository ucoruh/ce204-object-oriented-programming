@echo off
:: UTF-8 karakter kodlaması için konsol kod sayfasını ayarla
chcp 65001 > nul
:: Run all PlantUML converters
echo Running PlantUML generation for all formats...

:: Run each format one by one
call "%~dp0run_plantuml_png.bat"
echo.
call "%~dp0run_plantuml_svg.bat"
echo.
call "%~dp0run_plantuml_pdf.bat"
echo.
call "%~dp0run_plantuml_eps.bat"
echo.
call "%~dp0run_plantuml_latex.bat"

echo.
echo All PlantUML conversions completed!
echo. 