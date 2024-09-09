# Jetsnack sample

Jetsnack is a sample snack ordering app built with [Jetpack Compose][compose].

To try out this sample app, use the latest stable version
of [Android Studio](https://developer.android.com/studio).
You can clone this repository or import the
project from Android Studio following the steps
[here](https://developer.android.com/jetpack/compose/setup#sample).

This sample showcases:

* Show Consent Banner
* Show Consent Preference Center

## Consent Code

Kotlin code to show the consent banner and preference center

**File: app/src/main/java/com/example/jetsnack/SecuritiBanner.kt**
```kotlin
val options = CmpSDKOptions(
    appURL = "https://<API_HOST>",
    cdnURL = "https://<CDN_HOST>/consent",
    tenantID = "b9153366-8d40-43b0-8009-e7c5f7342b6c",
    appID = "e6c35be9-0159-4f5e-ad95-d7476ce39943",
    testingMode = true,
    loggerLevel = CmpSDKLoggerLevel.DEBUG,
    backgroundTaskIntervalTime = 1
)
```

## Screenshots

<img src="screenshots/s1.png"/>
<img src="screenshots/s2.png"/>
<img src="screenshots/s3.png"/>

### Status: 🚧 In progress 🚧

Jetsnack is still under development and some screens are not yet implemented.

## License

```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[compose]: https://developer.android.com/jetpack/compose
[coil]: https://coil-kt.github.io/coil/
