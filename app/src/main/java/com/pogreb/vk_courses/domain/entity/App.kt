package com.pogreb.vk_courses.domain.entity

data class App(
    val id: Long,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)
