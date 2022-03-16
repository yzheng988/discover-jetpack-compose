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

## Lesson 2 - Building a User Interface with Jetpack Compose
- Create a composable function named `MainActivityContent` to display a list of Android version details
- Use `AndroidVersionsRepository.data` to provide the list of data
- Use a `Scaffold` to provide the basic container for the screen composable
- Configure the `Scaffold` to include a `TopAppBar` that displays the app title
- Use a `LazyColumn` to display a vertically scrolling list of `Cards` displaying the version info

## Lesson 3 - State Hoisting
- Use `by rembmer { ... }` to create a variable that holds the currently selected `AndroidVersionInfo`
- If an item is selected, update the `TopAppBar` to show a back button, and the display name of the selected info
- If an item is selected, udpate the content of `Scaffold` to show an `AndroidVersionDetails` composable
- Add a `clickable { ... }` `Modifier` to each list `Card` to capture clicks and update the selected item
- Set the `onClick` handler for the back icon so clicking back clears the currently selected item

## Lesson 4 - Material Theme
- Create a custom `HelloComposeTheme` composable that wraps, and customizes, `MaterialTheme`
- Provide both a light mode, and dark mode color palette
- Use the provided `jetbrainsmono_regular.ttf` font to customize the `body1` `TextStyle` of `MaterialTheme.typography`