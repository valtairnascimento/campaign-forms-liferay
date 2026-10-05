$ErrorActionPreference = 'Stop'
$bundles = Join-Path (Split-Path $PSScriptRoot -Parent) 'bundles'
$bin = Join-Path $bundles 'tomcat-9.0.73\bin'
$log = Join-Path $bundles 'logs\liferay-console.log'

if (Get-NetTCPConnection -LocalPort 8080 -State Listen -ErrorAction SilentlyContinue) {
    Write-Host 'Something is already listening on port 8080. Run scripts\stop-liferay.ps1 first.'
    exit 1
}

if (Test-Path $log) {
    Move-Item $log (Join-Path $bundles 'logs\liferay-console.prev.log') -Force
}

$cmd = "Set-Location '$bin'; & '$bin\catalina.bat' run *> '$log'"
Start-Process -WindowStyle Hidden powershell.exe -ArgumentList '-NoProfile', '-Command', $cmd

Write-Host "Liferay is starting. Follow the log with: Get-Content '$log' -Wait -Tail 20"
Write-Host 'It is ready when "Server startup in" shows up -> http://localhost:8080'
