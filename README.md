# Video Games Browser

## Project Description

This is an Android application that allows users to browse video games by genre using the RAWG API. The app displays a list of games with their images, ratings, and release dates, and allows users to view detailed information for each game. The app is built with modern Android development practices, including Jetpack Compose for UI, MVVM architecture, and Dagger Hilt for dependency injection.

## Features

- Browse video games by genre (e.g., Action, Adventure, etc.).
- View game details, including name, image, release date, rating, and description.
- Dynamically fetch genres from the RAWG API.
- Paginate the game list with lazy loading.
- Filter games locally based on the search query (without additional API calls).
- Handle loading, error, and empty states gracefully.

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Async:** Kotlin Coroutines + Flow
- **Networking:** Retrofit 2 + Gson
- **DependencyInjection:** Dagger Hilt
- **Pagination:** Jetpack Paging 3
- **ImageLoading:** Coil
- **Navigation:** Jetpack Navigation Compose

## Architecture

- **Architecture:** MVVM
  - **Rationale:** MVVM is used to separate UI-related logic from business logic, making the codebase more maintainable and testable. The **ViewModel** is responsible for preparing and managing UI-related data, while the **Model** layer handles business logic and data fetching. This separation allows for easier unit testing and clear separation of concerns in the application.
 
## Unit Testing

### Unit Tests for Mapper and GetGameDetailsUseCase

### Libraries Used for Unit Testing


- **JUnit**
- **MockK**
- **Kotlin Coroutines Test**
- **Turbine**

## Assumptions/Shortcuts

- **Offline Caching:** Due to time constraints, offline caching (such as using Room for local storage) was not implemented.
- **Genre Selection:** The genre is currently "Action". 
  In a future version, users will be able to select genres dynamically 
  from a list fetched from the RAWG API.

## Future Improvements

### Offline Caching with Room + Paging 3
The next planned improvement is implementing offline caching using Room database 
integrated with Paging 3 via RemoteMediator. This will allow the app to:
- Cache game data locally so users can browse without an internet connection
- Use RemoteMediator to coordinate between the network and local database

**Planned Tech:**
- Room Database for local storage
- RemoteMediator to sync network and local data
- Paging 3 integrated with Room PagingSource

### Complete Unit Testing
Currently the project includes unit tests for the Mapper and GetGameDetailsUseCase. 

## Screenshots
<img width="429" height="776" alt="image" src="https://github.com/user-attachments/assets/b6d48a45-2fc6-4ff0-947d-f7c762a1d457" />
<img width="441" height="798" alt="image" src="https://github.com/user-attachments/assets/45a493e2-3a3d-4911-9549-d592ec6bbc4f" />


## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>


   
