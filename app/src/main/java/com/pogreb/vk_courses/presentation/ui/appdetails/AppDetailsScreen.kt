package com.pogreb.vk_courses.presentation.ui.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pogreb.vk_courses.presentation.state.AppDetailsState
import com.pogreb.vk_courses.presentation.viewmodel.AppDetailsViewModel

@Composable
fun AppDetailsScreen(
    onShowcaseAppClick: () -> Unit = {}
) {
    val viewModel = viewModel<AppDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.snackBarMessages.collect { message ->
            snackBarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { contentPadding ->

        when(val currentState = state) {
            is AppDetailsState.Content -> {
                AppDetailsContent(
                    appDetails = currentState.appDetails,
                    similarApps = currentState.similarApps,
                    onBackClick = {
                        // TODO: Открыть предыдущий экран через Jetpack Navigation
                    },
                    onShareClick = { viewModel.onShareClicked() },
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding),
                )
            }
            AppDetailsState.Loading -> {
                CircularProgressIndicator()
            }
        }


    }
}