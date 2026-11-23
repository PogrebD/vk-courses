package com.pogreb.vk_courses.presentation.state

import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.presentation.AppListItem

sealed interface AppsListState {
    data object Loading : AppsListState
    data class Content(
        val apps: List<AppListItem>
    ) : AppsListState
}