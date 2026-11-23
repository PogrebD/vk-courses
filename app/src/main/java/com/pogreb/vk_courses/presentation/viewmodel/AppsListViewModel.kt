package com.pogreb.vk_courses.presentation.viewmodel

import android.util.Log
import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.data.repository.AppRepositoryImpl
import com.pogreb.vk_courses.domain.usecase.GetAppsListUseCase
import com.pogreb.vk_courses.domain.usecase.LoadRatingsInParallelUseCase
import com.pogreb.vk_courses.domain.usecase.LoadRatingsSequentiallyUseCase
import com.pogreb.vk_courses.presentation.AppListItem
import com.pogreb.vk_courses.presentation.state.AppsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppsListViewModel @Inject constructor(
    private val getAppsListUseCase: GetAppsListUseCase,
    private val loadRatingsInParallelUseCase: LoadRatingsInParallelUseCase,
    private val loadRatingsSequentiallyUseCase: LoadRatingsSequentiallyUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<AppsListState>(AppsListState.Loading)
    val state = _state.asStateFlow()

    private var loadingJob: Job? = null

    fun load() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            try {
                val apps = getAppsListUseCase()
                val ids  = apps.map { it.id }

                var seqRatings: Map<String, Float> = emptyMap()
                val seqMs = measureTimeMillis {
                    seqRatings = loadRatingsSequentiallyUseCase(ids)
                }
                Log.d("Ratings", "Sequential: ${seqMs} ms")

                var parRatings: Map<String, Float> = emptyMap()
                val parMs = measureTimeMillis {
                    parRatings = loadRatingsInParallelUseCase(ids)
                }
                Log.d("Ratings", "Parallel:   ${parMs} ms")

                val items = apps.map { app ->
                    AppListItem(app = app, rating = parRatings[app.id] ?: 0f)
                }

                _state.update {
                    AppsListState.Content(apps = items)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("AppsListViewModel", "Failed to load apps", e)
            }
        }
    }
}