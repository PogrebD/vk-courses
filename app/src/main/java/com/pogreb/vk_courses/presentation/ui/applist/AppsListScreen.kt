package com.pogreb.vk_courses.presentation.ui.applist

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.pogreb.vk_courses.R
import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.presentation.state.AppsListState
import com.pogreb.vk_courses.presentation.viewmodel.AppsListViewModel

@Composable
internal fun AppsListScreen(
    onAppClick: (Int) -> Unit = {}
) {
    val viewModel = viewModel<AppsListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppsTopBar() }
    ) { innerPadding ->

        when(val currentState = state) {
            is AppsListState.Content -> {
                AppsListContent(
                    apps = currentState.apps,
                    onAppClick = onAppClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
            is AppsListState.Loading -> {
                CircularProgressIndicator()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppsTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.title_apps),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            AsyncImage(
                model = R.mipmap.ic_launcher,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}