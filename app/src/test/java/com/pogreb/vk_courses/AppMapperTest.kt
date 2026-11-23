package com.pogreb.vk_courses

import com.pogreb.vk_courses.data.dto.AppDto
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.domain.entity.App
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppMapperTest {

    private val mapper = AppMapper()

    private fun createDto(
        id: String = "1",
        name: String = "VK",
        description: String = "social",
        category: String = "Social",
        iconUrl: String = "url"
    ) = AppDto(id, name, description, category, iconUrl)

    @Test
    fun `map preserves id`() {
        val dto = createDto(id = "42")
        assertEquals("42", mapper.map(dto).id)
    }

    @Test
    fun `map preserves name`() {
        val dto = createDto(name = "Telegram")
        assertEquals("Telegram", mapper.map(dto).name)
    }

    @Test
    fun `map preserves description`() {
        val dto = createDto(description = "messenger")
        assertEquals("messenger", mapper.map(dto).description)
    }

    @Test
    fun `map preserves category`() {
        val dto = createDto(category = "Communication")
        assertEquals("Communication", mapper.map(dto).category)
    }

    @Test
    fun `map preserves iconUrl`() {
        val dto = createDto(iconUrl = "https://icon")
        assertEquals("https://icon", mapper.map(dto).iconUrl)
    }

    @Test
    fun `map returns App instance`() {
        val result = mapper.map(createDto())
        assertTrue(result is App)
    }
}