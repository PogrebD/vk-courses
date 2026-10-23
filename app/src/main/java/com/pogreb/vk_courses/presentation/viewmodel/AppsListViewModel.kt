package com.pogreb.vk_courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.pogreb.vk_courses.data.repository.AppsListRepository
import com.pogreb.vk_courses.presentation.state.AppsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppsListViewModel() : ViewModel() {
    val repository: AppsListRepository = AppsListRepository()
    private val _state = MutableStateFlow<AppsListState>(AppsListState.Loading)
    val state = _state.asStateFlow()

    fun load() {
        val apps = repository.getApps()

        _state.update {
            AppsListState.Content(apps = apps)
        }
    }
}