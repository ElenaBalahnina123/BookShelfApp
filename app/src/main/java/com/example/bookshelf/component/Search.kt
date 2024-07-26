package com.example.bookshelf.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.bookshelf.R
import kotlinx.coroutines.flow.Flow


@Composable
fun Search(
    flow: Flow<TextFieldValue>,
    onSearchChanged: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorBlue = colorResource(id = R.color.red)
    val colorPink = colorResource(id = R.color.light_yellow)
    val searchText by flow.collectAsState(initial = TextFieldValue())
    TextField(
        value = searchText,
        onValueChange = onSearchChanged,
      modifier = modifier.fillMaxWidth()
            .drawBehind {
                drawLine(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorBlue,
                            colorPink
                        ),
                    ),
                    Offset(0f, size.height),
                    Offset(size.width, size.height),
                    strokeWidth = 3f
                )
            },
        singleLine = true,
        placeholder = {
            Text(text = "Поиск...")
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}