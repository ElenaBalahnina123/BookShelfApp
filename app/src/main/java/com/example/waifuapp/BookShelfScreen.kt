package com.example.waifuapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import kotlinx.coroutines.flow.Flow

sealed class BookShelfScreenState {
    data object Initial : BookShelfScreenState()
    data object Loading : BookShelfScreenState()

    data class WaifuScreenData(
        val imgWaifu: String,
        val title: String
    ) : BookShelfScreenState()

    data class Error(
        val err: Throwable
    ) : BookShelfScreenState()
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookShelfScreen(
    bookShelfScreenStateFlow: Flow<BookShelfScreenState>
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val bookShelf by bookShelfScreenStateFlow.collectAsState("")

            when (bookShelf) {
                is BookShelfScreenState.Error -> {
                    Text(text = "Ошибка")
                    Log.d("OLOLO", (bookShelf as BookShelfScreenState.Error).err.toString())
                }

                is BookShelfScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is BookShelfScreenState.WaifuScreenData -> {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        GlideImage(
                            model = (bookShelf as BookShelfScreenState.WaifuScreenData).imgWaifu, contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(0.4f)

                        )
                        Text(text = (bookShelf as BookShelfScreenState.WaifuScreenData).title,modifier = Modifier
                            .padding(8.dp))
                    }
                }

                BookShelfScreenState.Initial -> Text(text = "")
            }

        }
    }
}

