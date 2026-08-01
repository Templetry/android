# Templetry parent: android

Android native templates for [Templetry](https://github.com/Templetry). One **parent repo**, multiple **forms** — each form is a subdirectory that compiles on its own and carries its own `template.yml` ([ADR-0011](https://github.com/Templetry/wiki/blob/main/adr/0011-template-forms.md)).

| Form | What it is | Status |
|---|---|---|
| [`modular-features/`](modular-features/) | Multi-module production base — `core/data/domain/feature` layers, convention plugins, Compose | ✅ ready |
| `single-module/` | Single-module starter | 🏗️ planned |

## Usage

```sh
git clone https://github.com/Templetry/android
templetry render --template ./android/modular-features --out ./my-app \
  --set "project_name=My App" --set "base_package=com.me.myapp"
```

Forms are **chosen**, not combined. Inside a form, the manifest's features are freely combinable.
