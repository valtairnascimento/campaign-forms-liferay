$bundles = Join-Path (Split-Path $PSScriptRoot -Parent) 'bundles'
$serverXml = Join-Path $bundles 'tomcat-9.0.73\conf\server.xml'

$shutdownPort = 8005

if (Test-Path $serverXml) {
    $match = Select-String -Path $serverXml -Pattern '<Server port="(\d+)"' | Select-Object -First 1

    if ($match) {
        $shutdownPort = [int]$match.Matches[0].Groups[1].Value
    }
}

try {
    $client = New-Object Net.Sockets.TcpClient('127.0.0.1', $shutdownPort)
    $bytes = [Text.Encoding]::ASCII.GetBytes('SHUTDOWN')
    $client.GetStream().Write($bytes, 0, $bytes.Length)
    $client.Close()
    Write-Host 'Shutdown sent; waiting for Tomcat to stop...'
}
catch {
    Write-Host "Shutdown port $shutdownPort is not available."
}

$deadline = (Get-Date).AddSeconds(180)

do {
    Start-Sleep 3
    $tomcat = Get-CimInstance Win32_Process -Filter "name='java.exe'" |
        Where-Object { $_.CommandLine -like '*catalina*' -and $_.CommandLine -like "*$bundles*" }
} while ($tomcat -and (Get-Date) -lt $deadline)

if ($tomcat) {
    Write-Host 'Tomcat did not stop within 180 s; killing it.'
    $tomcat | ForEach-Object { Stop-Process -Id $_.ProcessId -Force }
}

Get-CimInstance Win32_Process -Filter "name='java.exe'" |
    Where-Object { $_.CommandLine -like '*elasticsearch-sidecar*' -and $_.CommandLine -like "*$bundles*" } |
    ForEach-Object { Stop-Process -Id $_.ProcessId -Force }

Write-Host 'Liferay stopped.'
