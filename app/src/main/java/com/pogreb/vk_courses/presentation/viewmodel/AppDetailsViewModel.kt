package com.pogreb.vk_courses.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.vk_courses.domain.usecase.GetAppDetailsUseCase
import com.pogreb.vk_courses.domain.usecase.GetAppsListUseCase
import com.pogreb.vk_courses.presentation.state.AppDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val getAppsListUseCase: GetAppsListUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()


    private val _snackBarChannel = Channel<String>(Channel.BUFFERED)
    val snackBarMessages = _snackBarChannel.receiveAsFlow()

    private var loadingJob: Job? = null

    private val id: String = checkNotNull(savedStateHandle["appId"])

    init {
        load()
    }

    fun load() {
        loadingJob?.cancel()
        viewModelScope.launch {

            try {
                val appDetails = getAppDetailsUseCase(id)
                val similarApps = getAppsListUseCase()

                _state.update {
                    AppDetailsState.Content(
                        appDetails = appDetails,
                        similarApps = similarApps
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("AppDetailsViewModel", "Failed to load app details", e)
            }

        }
    }

    fun onShareClicked() {
        viewModelScope.launch {
            _snackBarChannel.send("В разработке")
        }
    }
}