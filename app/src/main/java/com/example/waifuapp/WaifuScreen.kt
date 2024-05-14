package com.example.waifuapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

sealed class WaifuScreenState {
    data object Loading : WaifuScreenState()

    data class WaifuScreenData(
        val imgWaifu: String
    ) : WaifuScreenState()

    data class Error(
        val err: Throwable
    ) : WaifuScreenState()
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WaifuScreen(
    waifuScreenData: WaifuScreenState
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (waifuScreenData) {
                is WaifuScreenState.Error -> {
                    Text(text = "Ошибка")
                }

                is WaifuScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is WaifuScreenState.WaifuScreenData -> {
                    GlideImage(model = waifuScreenData.imgWaifu, contentDescription = null, modifier = Modifier.fillMaxWidth().padding(8.dp))
                }
            }

        }
    }
}

