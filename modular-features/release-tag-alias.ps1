$scriptPath = Join-Path $PSScriptRoot 'scripts/release/release-tag-alias.ps1'
if (-not (Test-Path $scriptPath)) {
    throw "Could not find organized release alias script at $scriptPath"
}

. $scriptPath
