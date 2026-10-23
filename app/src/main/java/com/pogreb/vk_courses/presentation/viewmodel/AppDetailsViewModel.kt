package com.pogreb.vk_courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.vk_courses.data.repository.AppDetailsRepository
import com.pogreb.vk_courses.data.repository.AppsListRepository
import com.pogreb.vk_courses.presentation.state.AppDetailsState
import com.pogreb.vk_courses.presentation.state.AppsListState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppDetailsViewModel() : ViewModel() {
    val repository: AppDetailsRepository = AppDetailsRepository()
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()


    private val _snackBarChannel = Channel<String>(Channel.BUFFERED)
    val snackBarMessages = _snackBarChannel.receiveAsFlow()

    init {
        load()
    }

    fun load() {
        val appDetails = repository.getAppDetails()
        val similarApps = repository.getSimilarApps()

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