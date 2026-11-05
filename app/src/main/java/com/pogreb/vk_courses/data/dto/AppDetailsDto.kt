package com.pogreb.vk_courses.data.dto

import com.pogreb.vk_courses.domain.entity.Category

data class AppDetailsDto(
    val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>?,
    val description: String,
)
