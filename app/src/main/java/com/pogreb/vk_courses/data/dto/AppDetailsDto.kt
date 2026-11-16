package com.pogreb.vk_courses.data.dto

import com.pogreb.vk_courses.domain.entity.Category
import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto(
    val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshots: List<String>? = null,
    val description: String,
)
