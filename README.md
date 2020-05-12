# gish
Simple Repository / Issue reader with Kotlin / Architecture Components

## Purpose
Gish ( _G_ -itHub _Iss_ -ues) is a straight forward consumer of the [GitHub API](https://developer.github.com/v3/). In its current form it loads repositories from [Google's organization](https://api.github.com/orgs/google/repos) but was build to be repurposed for any organization's repositories.

### Components
Gish uses the following native Android components in implementing its functionality.

* [Kotlin](https://kotlinlang.org/)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* Fragments
* RecylerView
* [DataBinding](https://developer.android.com/topic/libraries/data-binding)
* [Paging Library](https://developer.android.com/topic/libraries/architecture/paging)
* [Moshi](https://github.com/square/moshi/)
* [Retrofit](https://square.github.io/retrofit/)
* [Kurlet](https://github.com/C06A/KUrlet)

### API
Gish uses GitHub's [REST API v3](https://developer.github.com/v3/) to retrieve a truncated form of the Repository and Issue model and displays a simple stateful (open or closed) list of Issues for each of repository.

## Future Enhancements
The codebase in its current form is "barely functional" in that it assumes proper conditions and expects that data will be where it needs to be. The shouldn't crash, but there are several shortcomings with this implementation

### API Loading
Currently the application is naively accessing GitHub's API as an unauthenticated User for each state change of the UI. Even though Retrofit has been configured to utilize cache hits, the queries being performed will rarely, if ever, result in a cached load. 

This is bad for several reasons including almost guaranteeing hitting the API's rate limit of 60 per hour as well as increased data usage fetching items that have already been previously loaded.

Enhancing this feature would include taking an offline-first approach that focused on local data storage, content-diffing, and a reasonable caching strategy that balances content loading vs data usage and API restrictions.

### UI State
The UI of the application currently exposes a bare minimum of functionality which consists entirely of a "loaded" state after data from the API has been retrieved. This would not fly for a production application.

Enhancing this feature would be to implement error states with informative messaging (like how long until your rate limit will refresh) as well as loading indication to inform the user that data is inbound from the remote API.

### Testing
There are currently no automated tests for this project, nor has there been an effort to create infrastructure that would allow for automated testing to be performed without accessing the live GitHub API.

Enhancing this feature would include the creation of Stubbed/Mocked API behaviors that can be utilized for automated tests as well as creating Espresso and Unit tests to cover the major behaviors of the application.

### Linting / Git Hooks
This code was generated "fast and loose" with a focus on feature implementation rather than perfect code. Setting up `ktlint` and `git hooks` for the project would allow for the workflow itself to catch glaring issues before they can make their way into the code base.
