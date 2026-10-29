package com.pogreb.vk_courses.presentation.state

import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails

sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val similarApps: List<App>,
    ) : AppDetailsState
}