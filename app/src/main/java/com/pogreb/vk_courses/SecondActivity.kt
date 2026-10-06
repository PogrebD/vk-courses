package com.pogreb.vk_courses

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pogreb.vk_courses.ui.theme.VkcoursesTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val value = intent.getStringExtra("key").orEmpty()

        enableEdgeToEdge()
        setContent {
            VkcoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondScreen(
                        modifier = Modifier.padding(innerPadding),
                        value = value
                    )
                }
            }
        }
    }
}

@Composable
fun SecondScreen(modifier: Modifier = Modifier, value: String) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Получено: $value"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    VkcoursesTheme {
        SecondScreen(value = "")
    }
}