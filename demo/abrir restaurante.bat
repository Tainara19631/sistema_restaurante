@echo off
title Sistema Restaurante

cd /d "%~dp0"

java -jar target/demo-1.0-SNAPSHOT.jar

echo.
echo Sistema Restaurante encerrado.
pause