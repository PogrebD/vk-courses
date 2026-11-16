package com.pogreb.vk_courses.presentation

import com.pogreb.vk_courses.domain.entity.App

data class AppListItem(
    val app: App,
    val rating: Float,
)