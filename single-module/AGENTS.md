# AGENTS

Operating contract for AI agents and automation helpers working in this project.

## Mission

- Keep this app simple: one `app` module, Compose Material 3, version catalog. Resist adding modules — when the app genuinely outgrows one module, migrate to the `android/modular-features` form instead.

## Core Rules

- All dependency versions live in `gradle/libs.versions.toml` — never inline versions in build scripts.
- UI is Jetpack Compose only; no XML layouts (XML is reserved for resources: strings, themes, icons).
- Theme changes go in `ui/theme/` (`Color.kt`, `Theme.kt`, `Type.kt`); respect dynamic color on Android 12+.
- Update docs in the same change when behavior or process changes.

## Required Checks Before Finishing

- `./gradlew assembleDebug` compiles clean.
- New screens get a `@Preview` composable.

## Safe Change Workflow

1. Read the affected files fully before editing.
2. Make the smallest change that solves the task.
3. Build, then review the diff with git before committing.
