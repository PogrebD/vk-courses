package com.pogreb.vk_courses

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkcoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
    ) {
        TextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue
            },
            label = { Text("Введите что-нибудь") }
        )
        TextButton(
            onClick = {
                context.startActivity(
                    Intent(context, SecondActivity::class.java).apply {
                        putExtra("key", text)
                    })
            }
        ) {
            Text(
                text = "Открыть вторую Activity"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VkcoursesTheme {
        MainScreen()
    }
}