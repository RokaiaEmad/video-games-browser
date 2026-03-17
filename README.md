# Video Games Browser

## Project Description
An Android application that allows users to browse video games by genre 
using the RAWG API. The app displays a list of games with their images, 
ratings, and release dates, and allows users to view detailed information 
for each game. Built with modern Android development practices including 
Jetpack Compose, MVVM architecture, and Dagger Hilt.

## Features
- Browse video games by Action genre
- View game details including name, image, release date, rating, and description
- Paginate the game list with lazy loading
- Filter games locally based on search query (without additional API calls)
- Handle loading, error, and empty states gracefully

## Tech Stack
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Async:** Kotlin Coroutines + Flow
- **Networking:** Retrofit 2 + Gson
- **Dependency Injection:** Dagger Hilt
- **Pagination:** Jetpack Paging 3
- **Image Loading:** Coil
- **Navigation:** Jetpack Navigation Compose

## Architecture
- **Pattern:** MVVM
- **Rationale:** MVVM separates UI logic from business logic, making the 
  codebase more maintainable and testable. The ViewModel manages UI state 
  while the Model layer handles business logic and data fetching.

## Unit Testing
### Tests Implemented
- **DomainGameMapper** — tests null defaults and correct field mapping
- **GetGameDetailsUseCase** — tests success and error states

### Libraries Used
- JUnit
- MockK
- Kotlin Coroutines Test
- Turbine

## Assumptions/Shortcuts
- **Offline Caching:** Not implemented due to time constraints. 
  Planned using Room database.
- **Genre Selection:** Currently hardcoded to "Action". A future version 
  will allow users to select genres dynamically from the RAWG API.

## Future Improvements
### Offline Caching with Room + Paging 3
- Cache game data locally for offline browsing
- Use RemoteMediator to sync network and local data
- Integrate Room with Paging 3 PagingSource

### Complete Unit Testing
- ViewModel tests for GamesViewModel
- Repository tests for GameRepositoryImpl
- UseCase tests for GetGameUseCase
- Integration tests for RemoteMediator

## Setup Instructions
1. Clone the repository:
   git clone <repository-url>
2. Open in Android Studio
3. Add your API key in local.properties:
   API_KEY=your_rawg_api_key_here
4. Get your free API key from https://rawg.io/apidocs
5. Build and run the project

## Screenshots
<img width="429" height="776" alt="image" src="https://github.com/user-attachments/assets/b6d48a45-2fc6-4ff0-947d-f7c762a1d457" />
<img width="441" height="798" alt="image" src="https://github.com/user-attachments/assets/45a493e2-3a3d-4911-9549-d592ec6bbc4f" />
