package com.example.news.presentation.ui.main.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val ShimmerColorShades = listOf(
    Color.Gray.copy(0.9f),
    Color.Gray.copy(0.7f),
    Color.Gray.copy(0.5f),
    Color.Gray.copy(0.7f),
    Color.Gray.copy(0.9f)
)

@Composable
fun ShimmerAnimation(
) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    ShimmerItem(brush = brush)
}


@Composable
fun ShimmerItem(
    brush: Brush
) {
    Card(modifier = Modifier.padding(5.dp), shape = MaterialTheme.shapes.large) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
                .background(brush = brush)
        )
    }
}