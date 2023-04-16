# Game Search App

The Game Search App is a simple Android application that allows users to search for video games using the GiantBomb API.
 - https://www.giantbomb.com/api/documentation/

## Features

- App loads initally list of games
- Search for games by characters to words. 
- View details of a selected game, including the game's name, image, and description

## Technologies Used

- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection
- [Retrofit2](https://github.com/square/retrofit) - construct the REST APIs and getting network data.
- [Moshi](https://github.com/square/moshi) - for fast JSON parsing
- JetPack
    - Compose - A modern toolkit for building native Android UI.
    - Lifecycle - dispose observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
- Architecture
    - MVVM Architecture (View - ViewModel - Model)

## Getting Started

To get started with the Game Search App, follow these steps:

1. Clone the repository to your local machine using `git clone https://github.com/<username>/game-search-app.git`.
2. Open the project in Android Studio.
3. Build and run the project using an emulator or physical device.

## Wanted Changes

- Remove hard coded API key and place into server side
- Stop loading list onBack and put pull to referesh
- Cache games and hit API at some interval such as once a day.

## Support

If you encounter any issues or have questions about the app, please contact [Adam](https://www.adamgardner.dev/contact).

## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[LICENSE](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
