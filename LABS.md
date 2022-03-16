# Labs

## Lesson 1 - Integrate Jetpack Compose Into an Existing Project
- Open the sample project in Android Studio
- Add the necessary core Compose dependencies
- Uncomment the code within `MainActivity.kt`
- Build the project
- Deploy the project to an emulator or physical device

```
// add to app/build.gradle
implementation "androidx.compose.ui:ui:$compose_version"
implementation "androidx.compose.material:material:$compose_version"
implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
```