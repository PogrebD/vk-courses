package com.pogreb.vk_courses.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.pogreb.vk_courses.R
import com.pogreb.vk_courses.domain.entity.App

@Composable
internal fun AppsListScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppsTopBar() }
    ) { innerPadding ->
        AppsListContent(
            apps = apps,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
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
val apps = listOf(
    App(
        id = 1L,
        name = "VK",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 2L,
        name = "OK",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 3L,
        name = "TG",
        description = "Очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание, очень долгое описание",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 4L,
        name = "VK",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 5L,
        name = "OK",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 6L,
        name = "TG",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 7L,
        name = "VK",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 8L,
        name = "OK",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
    App(
        id = 9L,
        name = "TG",
        description = "Популярный мэсенджер",
        category = "Социальные сети",
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
    ),
)