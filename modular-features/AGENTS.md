# AGENTS

Operating contract for AI agents and automation helpers working in this project.

## Mission

- Keep the template stable, maintainable, and well-documented.
- Prefer incremental changes with verifiable outputs.

## Core Rules

- Respect module boundaries (`app`, `core`, `data`, `domain`, `feature`).
- Prefer convention plugins and version catalog over ad-hoc Gradle configuration.
- Keep debug-only tooling in debug variants.
- Update docs in the same change when behavior or process changes.

## Required Checks Before Finishing

- Compile affected modules.
- Run detekt on affected modules.
- Run lint for affected Android modules (for example, `:app:lint`).
- If dependencies or build logic changed, run `dependencyUpdates --no-parallel`.

```sh templetry:checks
./gradlew assembleDevDebug
./gradlew testDevDebugUnitTest
```

## Safe Change Workflow

1. Read context from `AI_INDEX.md` and the relevant docs.
2. Apply the minimum necessary edits.
3. Validate with targeted Gradle tasks.
4. Summarize what changed, what was verified, and any known constraints.

## Architecture and Flow Replication Docs

- Playbook: `docs/ai/AGENT_PLAYBOOK.md`
- Guardrails: `docs/ai/ARCHITECTURE_GUARDRAILS.md`
- Existing flows: `docs/ai/FLOW_CATALOG.md`
- Recipes: `docs/ai/PATTERN_RECIPES.md`

## Do Not

- Do not commit secrets or credentials.
- Do not change release/governance rules silently.
- Do not move files between repository root and module directories (`app/`, `core/`, `data/`, `domain/`, `feature/`) without explicit intent and docs updates.

## This project came from a template

Four facts you cannot infer from the code in front of you:

- **Never hand-edit `.templetry-answers.yml`.** It records what generated this project. Editing it makes the next update merge against a state that never existed.
- **Before writing a capability by hand, run `templetry pieces`.** Auth, RBAC, audit trails, API keys and whole CRUD resources may already exist as pieces for this template. Adopting one is `templetry add <name>`, and it brings its own tests.
- **`templetry update` pulls improvements from the template** through a three-way merge that keeps your edits. Use it instead of copying files from the template by hand.
- **Directives like `tpl:if` belong to the template, not here.** If you find one in this project, it is a rendering bug worth reporting — do not try to interpret it.
