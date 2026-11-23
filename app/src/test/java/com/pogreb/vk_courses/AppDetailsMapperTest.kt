package com.pogreb.vk_courses

import com.pogreb.vk_courses.data.dto.AppDetailsDto
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.domain.entity.Category
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDetailsMapperTest {

    private val mapper = AppDetailsMapper()

    private fun createDto(
        id: String = "1",
        name: String = "VK",
        developer: String = "VK Team",
        category: Category = Category.SOCIAL,
        ageRating: Int = 12,
        size: Float= 50f,
        iconUrl: String = "icon",
        screenshots: List<String> = listOf("s1", "s2"),
        description: String = "desc"
    ) = AppDetailsDto(id, name, developer, category, ageRating, size, iconUrl, screenshots, description)

    @Test
    fun `map preserves all scalar fields`() {
        val dto = createDto(id = "7", name = "App", developer = "Dev")
        val result = mapper.map(dto)
        assertEquals("7", result.id)
        assertEquals("App", result.name)
        assertEquals("Dev", result.developer)
    }

    @Test
    fun `map preserves ageRating and size`() {
        val dto = createDto(ageRating = 18, size = 120f)
        val result = mapper.map(dto)
        assertEquals(18, result.ageRating)
        assertEquals(120f, result.size)
    }

    @Test
    fun `map maps screenshots into screenshotUrlList`() {
        val dto = createDto(screenshots = listOf("a", "b", "c"))
        assertEquals(listOf("a", "b", "c"), mapper.map(dto).screenshotUrlList)
    }

    @Test
    fun `map preserves screenshots order`() {
        val dto = createDto(screenshots = listOf("3", "1", "2"))
        assertEquals(listOf("3", "1", "2"), mapper.map(dto).screenshotUrlList)
    }

    @Test
    fun `map handles empty screenshots list`() {
        val dto = createDto(screenshots = emptyList())
        assertEquals(emptyList<String>(), mapper.map(dto).screenshotUrlList)
    }

    @Test
    fun `map preserves description and iconUrl`() {
        val dto = createDto(description = "long text", iconUrl = "https://x")
        val result = mapper.map(dto)
        assertEquals("long text", result.description)
        assertEquals("https://x", result.iconUrl)
    }
}