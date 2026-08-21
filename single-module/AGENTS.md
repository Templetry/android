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

```sh templetry:checks
./gradlew assembleDevDebug
./gradlew testDevDebugUnitTest
```

## Safe Change Workflow

1. Read the affected files fully before editing.
2. Make the smallest change that solves the task.
3. Build, then review the diff with git before committing.

## This project came from a template

Four facts you cannot infer from the code in front of you:

- **Never hand-edit `.templetry-answers.yml`.** It records what generated this project. Editing it makes the next update merge against a state that never existed.
- **Before writing a capability by hand, run `templetry pieces`.** Auth, RBAC, audit trails, API keys and whole CRUD resources may already exist as pieces for this template. Adopting one is `templetry add <name>`, and it brings its own tests.
- **`templetry update` pulls improvements from the template** through a three-way merge that keeps your edits. Use it instead of copying files from the template by hand.
- **Directives like `tpl:if` belong to the template, not here.** If you find one in this project, it is a rendering bug worth reporting — do not try to interpret it.
