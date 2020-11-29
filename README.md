# Currency-Converter

Welcome 👋 

Currency converter is an android app that perform currency conversion for the different currencies of the world. It fetches the latest currency conversion rates from [here](https://fixer.io)

It is written 100% in Kotlin with both unit and integrated tests.🙂

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [Coroutine](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit](https://github.com/square/retrofit)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
* [Lottie](https://github.com/airbnb/lottie-android)
* [JUnit](https://junit.org/junit4/)
* [Mockito](http://site.mockito.org/)
* [Robolectric](http://robolectric.org/)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android 11 ([API 30](https://developer.android.com/preview/api-overview.html))
* Latest Android SDK Tools and build tools.

## Installation

* To run this code, clone this repository using this command `git clone https://github.com/segunfrancis/Currency-Converter.git`
* Import into android studio
* Get your API key from [here](https://fixer.io)
* Replace instances of `BuildConfig.API_KEY` with a reference to your API key
* Build the project and run on an android device or emulator

## Architecture

The architecture of the project follows the principles of Clean Architecture and MVVM. Here's how the project implements it:

### App layer

This layer makes use of the Android Framework and is used to create all of our UI components to display inside of the Main Activity. This layer contains the views(activities and fragments) and the ViewModel. The ViewModel receives its data from the use cases of the domain layer and then supplies the views.

### Domain layer

The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the app layer.

### Data layer

The Data layer is our access point to external data layers and is used to fetch data from multiple sources (examples are local and network in our case). It contains an implementation of the Local and Remote repositories.

 * ### Local data source

 * The local data source is part of the data layer and it provides a cache of the remote response. This reduces the frequency of network calls and serves as data persistence when the device is offline. The remote response goes through the local layer before being dislayed to the user thereby serving as the single source of truth.

 * ### Remote data source
 
 * The remote data source is part of the data layer and it is responsible for making API calls that returns response data.
 
 ## Screens

<ul>
  <img src="https://github.com/segunfrancis/Currency-Converter/blob/master/screens/Screenshot_20201129-042817_Currency%20Converter.jpg" width="40%" alt="Screen3" hspace="15">
  <img src="https://github.com/segunfrancis/Currency-Converter/blob/master/screens/Screenshot_20201129-042835_Currency%20Converter.jpg" width="40%" alt="Screen1" hspace="15">
  <img src="https://github.com/segunfrancis/Currency-Converter/blob/master/screens/Screenshot_20201129-042758_Currency%20Converter.jpg" width="40%" alt="Screen2" hspace="15">
  <img src="https://github.com/segunfrancis/Currency-Converter/blob/master/screens/Screenshot_20201129-050545_Currency%20Converter.jpg width="40%" alt="Screen4" hspace="15">
  <img src="https://github.com/segunfrancis/Currency-Converter/blob/master/screens/20201129_050959_edited_1.gif" width="40%" alt="Screen5" hspace="15">
</ul>

## Appreciation
* [Klemen Skledar](https://lottiefiles.com/36095-swipe-down) - Lottie Animation

## Author

* [Segun Francis](https://www.linkedin.com/in/segun-francis-302361a1)
