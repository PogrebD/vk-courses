package com.pogreb.vk_courses.presentation.ui.appdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pogreb.vk_courses.R
import com.pogreb.vk_courses.presentation.state.InstallState
import com.pogreb.vk_courses.ui.theme.VkcoursesTheme

@Composable
fun InstallButton(
    onClick: () -> Unit,
    installState: InstallState,
    modifier: Modifier = Modifier,
) {
    when (installState) {
        is InstallState.NotInstalled -> {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = modifier,
            ) {
                Text(text = stringResource(R.string.install_button_label))
            }
        }

        is InstallState.Installing -> {
            OutlinedButton(
                onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = modifier,
            ) {
                CircularProgressIndicator(
                    progress = { installState.progress / 100f },
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                )
                Spacer(Modifier.width(8.dp))
                Text(text = stringResource(R.string.cancel_button_label))
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "${installState.progress}%",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }

        is InstallState.Installed -> {
            Button(
                onClick = {},
                enabled = false,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = modifier,
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(text = stringResource(R.string.installed_button_label))
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkcoursesTheme {
        InstallButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            installState = InstallState.NotInstalled,
        )
    }
}