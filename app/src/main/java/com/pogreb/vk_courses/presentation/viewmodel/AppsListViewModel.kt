package com.pogreb.vk_courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.data.repository.AppRepositoryImpl
import com.pogreb.vk_courses.domain.usecase.GetAppsListUseCase
import com.pogreb.vk_courses.presentation.state.AppsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppsListViewModel @Inject constructor(
    private val getAppsListUseCase: GetAppsListUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<AppsListState>(AppsListState.Loading)
    val state = _state.asStateFlow()

    fun load() {
        val apps = getAppsListUseCase()

        _state.update {
            AppsListState.Content(apps = apps)
        }
    }
}