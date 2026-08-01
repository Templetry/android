param(
    [Parameter(Mandatory = $true)]
    [ValidateRange(0, 999)]
    [int]$Patch,
    [switch]$Push,
    [switch]$DryRun
)

$scriptPath = Join-Path $PSScriptRoot 'scripts/release/release-tag.ps1'
if (-not (Test-Path $scriptPath)) {
    throw "Could not find organized release script at $scriptPath"
}

$arguments = @{
    Patch = $Patch
}
if ($Push) {
    $arguments.Push = $true
}
if ($DryRun) {
    $arguments.DryRun = $true
}

& $scriptPath @arguments
