package com.pogreb.vk_courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.data.repository.AppRepositoryImpl
import com.pogreb.vk_courses.domain.usecase.GetAppDetailsUseCase
import com.pogreb.vk_courses.domain.usecase.GetAppsListUseCase
import com.pogreb.vk_courses.presentation.state.AppDetailsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppDetailsViewModel() : ViewModel() {
    private val getAppDetailsUseCase = GetAppDetailsUseCase(
        repository = AppRepositoryImpl(
            api = AppApi(),
            appMapper = AppMapper(),
            appDetailsMapper = AppDetailsMapper()
        )
    )

    private val getAppsListUseCase = GetAppsListUseCase(
        repository = AppRepositoryImpl(
            api = AppApi(),
            appMapper = AppMapper(),
            appDetailsMapper = AppDetailsMapper()
        )
    )
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()


    private val _snackBarChannel = Channel<String>(Channel.BUFFERED)
    val snackBarMessages = _snackBarChannel.receiveAsFlow()

    init {
        load()
    }

    fun load() {
        val appDetails = getAppDetailsUseCase()
        val similarApps = getAppsListUseCase()

        _state.update {
            AppDetailsState.Content(
                appDetails = appDetails,
                similarApps = similarApps
            )
        }
    }

    fun onShareClicked() {
        viewModelScope.launch {
            _snackBarChannel.send("В разработке")
        }
    }
}