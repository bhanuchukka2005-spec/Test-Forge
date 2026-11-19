@echo off
setlocal
set "MVN_VERSION=3.10.1"
set "MVN_ROOT=%LOCALAPPDATA%\TestForge\apache-maven-%MVN_VERSION%"
if exist "%MVN_ROOT%\bin\mvn.cmd" goto run_maven
set "BOOTSTRAP_DIR=%LOCALAPPDATA%\TestForge"
if not exist "%BOOTSTRAP_DIR%" mkdir "%BOOTSTRAP_DIR%"
echo Downloading Apache Maven %MVN_VERSION% into "%BOOTSTRAP_DIR%" (no administrator access required)...
powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference='Stop'; $zip=Join-Path $env:LOCALAPPDATA 'TestForge\maven.zip'; Invoke-WebRequest -Uri 'https://dlcdn.apache.org/maven/maven-3/%MVN_VERSION%/binaries/apache-maven-%MVN_VERSION%-bin.zip' -OutFile $zip; Expand-Archive -LiteralPath $zip -DestinationPath (Join-Path $env:LOCALAPPDATA 'TestForge') -Force; Remove-Item $zip -Force"
if errorlevel 1 (
  echo Maven download failed. Check internet access or install Maven manually: https://maven.apache.org/download.cgi
  exit /b 1
)
:run_maven
call "%MVN_ROOT%\bin\mvn.cmd" %*
exit /b %errorlevel%
