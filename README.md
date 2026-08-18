# Templetry parent: android

Android native templates for [Templetry](https://github.com/Templetry). One **parent repo**, multiple **forms** — each form is a subdirectory that compiles on its own and carries its own `template.yml` ([ADR-0011](https://github.com/Templetry/wiki/blob/main/adr/0011-template-forms.md)).

| Form | What it is | Status |
|---|---|---|
| [`modular-features/`](modular-features/) | Multi-module production base — `core/data/domain/feature` layers, convention plugins, Compose | ✅ ready |
| [`single-module/`](single-module/) | Single-module starter — one `app` module, Kotlin + Compose Material 3, selectable `min_sdk` | ✅ ready |

## Usage

```sh
git clone https://github.com/Templetry/android
templetry render --template ./android/modular-features --out ./my-app \
  --set "project_name=My App" --set "base_package=com.me.myapp"
```

Forms are **chosen**, not combined. Inside a form, the manifest's features are freely combinable.

## Environment profiles

Both forms ship `development` / `staging` / `production` as Android product flavors — the ecosystem's own mechanism (ADR-0018) — each with its own `BuildConfig.ENVIRONMENT`, `API_BASE_URL` and `VERBOSE_LOGGING`. Unlike the profile feature in most other catalog forms, this is **not togglable**: the flavor dimension is load-bearing infrastructure (`modular-features`' convention plugins wire it in unconditionally), not an add-on, so `assembleDebug` already builds all three flavors' debug variants.
