@echo off
:: UTF-8 karakter kodlaması için konsol kod sayfasını ayarla
chcp 65001 > nul
:: SVG Formatter for PlantUML
:: Just calls the common script with the correct format
call "%~dp0plantuml_common.bat" svg "SVG"
