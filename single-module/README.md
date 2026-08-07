# AndroidSingleApp

Android single-module starter generated with [Templetry](https://github.com/Templetry): one `app` module, Kotlin + Jetpack Compose (Material 3), version catalog, edge-to-edge activity and adaptive launcher icons.

## Structure

```
app/                  the whole application
  src/main/kotlin/    MainActivity + ui/theme (Compose M3 theme with dynamic color)
  src/main/res/       launcher icons, strings, theme
gradle/               version catalog + wrapper
```

## Build

```sh
./gradlew assembleDebug
```

Requires JDK 17+. When your app grows past one module, the `android/modular-features` form of the same catalog is the step up.
