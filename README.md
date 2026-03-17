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
