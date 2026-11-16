package com.pogreb.vk_courses.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.vk_courses.domain.usecase.GetAppDetailsUseCase
import com.pogreb.vk_courses.domain.usecase.GetAppsListUseCase
import com.pogreb.vk_courses.presentation.state.AppDetailsState
import com.pogreb.vk_courses.presentation.state.InstallState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.milliseconds

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

    private var installJob: Job?  = null

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
                        similarApps = similarApps,
                        installState = InstallState.NotInstalled
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

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
            _state.update { currentState ->
                if (currentState is AppDetailsState.Content) {
                    currentState.copy(installState = InstallState.NotInstalled)
                } else currentState
            }
        } else {
            installJob = viewModelScope.launch { emulateInstall() }
        }
    }

    private suspend fun emulateInstall() {
        var progress = 0
        
        while (progress <= 100) {
            _state.update { currentState ->
                if (currentState is AppDetailsState.Content) {
                    currentState.copy(installState = InstallState.Installing(progress))
                } else currentState
            }
            delay(150.milliseconds)
            progress+=5
        }

        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(installState = InstallState.Installed)
            } else currentState
        }
    }
}