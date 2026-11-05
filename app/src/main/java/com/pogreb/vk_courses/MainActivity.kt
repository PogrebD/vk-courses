package com.pogreb.vk_courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pogreb.vk_courses.navigation.AppNavHost
import com.pogreb.vk_courses.presentation.ui.applist.AppsListScreen
import com.pogreb.vk_courses.ui.theme.VkcoursesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkcoursesTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }
}