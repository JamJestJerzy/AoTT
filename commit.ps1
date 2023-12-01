param (
    [string]$text
)

function Get-Version {
    $filePath = "build.gradle"
    $content = Get-Content -Path $filePath -Raw

    $versionPattern = 'version = "(.+?)"'
    $versionMatch = [regex]::Match($content, $versionPattern)

    if ($versionMatch.Success) {
        return $versionMatch.Groups[1].Value
    } else {
        Write-Host "Error: Version not found in build.gradle. Please check the file."
        exit 1
    }
}

if (-not $text) {
    Write-Host "Error: Please provide a commit message."
    exit 1
}

$version = Get-Version

git add --all
git commit -m ("[{0}] {1}" -f $version, $text)
git push origin main