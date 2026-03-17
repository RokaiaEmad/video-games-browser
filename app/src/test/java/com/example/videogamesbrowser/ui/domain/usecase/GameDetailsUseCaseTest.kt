package com.example.videogamesbrowser.ui.domain.usecase

import app.cash.turbine.test
import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.domain.model.DomainGameDetails
import com.example.videogamesbrowser.domain.repository.GameRepository
import com.example.videogamesbrowser.domain.usecase.GetGameDetailsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetGameDetailsUseCaseTest {

    private val gameRepository: GameRepository = mockk()
    private val useCase = GetGameDetailsUseCase(gameRepository)

    private val fakeDto = GameDetailsResponseDto(
        id = 1,
        name = "Zelda",
        description = "A great game",
        imageUrl = "https://image.url",
        rating = 4.5,
        released = "2023-01-01"
    )

    private val expectedDomain = DomainGameDetails(
        id = 1,
        name = "Zelda",
        description = "A great game",
        imageUrl = "https://image.url",
        rating = 4.5,
        released = "2023-01-01"
    )

    private val fakeException = Exception("Network error")

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `test invoke with success response then return domain model`() {
        runTest {
            coEvery { gameRepository.getGameDetails(any()) } returns fakeDto

            useCase(1).test {
                assertEquals(expectedDomain, awaitItem())
                awaitComplete()
            }

            coVerify(exactly = 1) { gameRepository.getGameDetails(1) }
        }
    }

    @Test
    fun `test invoke with failure response then return error`() {
        runTest {
            coEvery { gameRepository.getGameDetails(any()) } throws fakeException

            useCase(1).test {
                assertEquals(fakeException.message, awaitError().message)
            }

            coVerify(exactly = 1) { gameRepository.getGameDetails(1) }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}