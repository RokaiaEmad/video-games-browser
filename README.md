# Tech Stack

- **#Language:** Kotlin
- **#UI:** Jetpack Compose
- **#Async:** Kotlin Coroutines + Flow
- **#Networking:** Retrofit 2 + Gson
- **#DependencyInjection:** Dagger Hilt
- **#Pagination:** Jetpack Paging 3
- **#ImageLoading:** Coil
- **#Navigation:** Jetpack Navigation Compose

# Architecture

- **#Architecture:** MVVM
  - **Rationale:** MVVM is used to separate UI-related logic from business logic, making the codebase more maintainable and testable. The **ViewModel** is responsible for preparing and managing UI-related data, while the **Model** layer handles business logic and data fetching. This separation allows for easier unit testing and clear separation of concerns in the application.
 
 # Assumptions/Shortcuts

- **Offline Caching:** Due to time constraints, offline caching (such as using Room for local storage) was not implemented.
- **Genre Selection:** The genre list (e.g., "Action") is dynamically retrieved from the RAWG API, and the genre selection UI is simplified for this implementation.
