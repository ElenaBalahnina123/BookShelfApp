package com.example.bookshelf.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
 fun GradientButton(
    gradientColors: List<Color>,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 17.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.W400,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            )
            .padding(
                all = 16.dp
            ),
        textAlign = TextAlign.Center
    )
}