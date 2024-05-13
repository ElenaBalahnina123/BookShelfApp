package com.example.waifuapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlin.reflect.KSuspendFunction0

data class WaifuScreenData(
    val imgWaifu: String
)


@OptIn(ExperimentalGlideComposeApi::class)
//@Preview
@Composable
fun WaifuScreen(
    waifuScreenData: WaifuScreenData
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(model = waifuScreenData.imgWaifu, contentDescription = null) {
                it.error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_foreground)
            }

        }
    }
}

