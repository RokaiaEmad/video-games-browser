package com.example.videogamesbrowser.ui.domain.mapper

import com.example.videogamesbrowser.data.remote.dto.GameDto
import com.example.videogamesbrowser.domain.mapper.toDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test


class DomainGameMapperTest {

    @Test
    fun `toDomain maps all fields correctly`() {
        // Given
        val dto = GameDto(
            id = 1,
            name = "Zelda",
            backgroundImage = "url",
            rating = 4.5
        )

        // When
        val result = dto.toDomain()

        // Then
        assertEquals(1, result.id)
        assertEquals("Zelda", result.name)
        assertEquals("url", result.backgroundImage)
        assertEquals(4.5, result.rating, 0.0)
    }

    @Test
    fun `toDomain uses defaults for null fields`() {
        // Given
        val dto = GameDto(
            id = null,
            name = null,
            backgroundImage = null,
            rating = null
        )

        // When
        val result = dto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertEquals("", result.name)
        assertEquals("", result.backgroundImage)
        assertEquals(0.0, result.rating, 0.0)
    }
}