package com.pogreb.vk_courses.presentation.state

import com.pogreb.vk_courses.domain.entity.App

sealed interface AppsListState {
    data object Loading : AppsListState
    data class Content(
        val apps: List<App>
    ) : AppsListState
}