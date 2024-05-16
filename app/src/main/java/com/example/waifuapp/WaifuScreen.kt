package com.example.waifuapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.flow.StateFlow

sealed class WaifuScreenState {
    data object Initial : WaifuScreenState()
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
    waifuScreenData: StateFlow<WaifuScreenState>
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val waifu by waifuScreenData.collectAsState()

            when (waifu) {
                is WaifuScreenState.Error -> {
                    Text(text = "Ошибка")
                    Log.d("OLOLO", (waifu as WaifuScreenState.Error).err.toString())
                }

                is WaifuScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is WaifuScreenState.WaifuScreenData -> {
                    GlideImage(model = (waifu as WaifuScreenState.WaifuScreenData).imgWaifu, contentDescription = null, modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                }

                WaifuScreenState.Initial -> TODO()
            }

        }
    }
}

