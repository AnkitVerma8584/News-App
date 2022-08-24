package com.example.news.presentation.ui.app.screens.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val fraction = remember { Animatable(0f) }
    var reveal by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        while (homeViewModel.isLoading.value) {
            fraction.animateTo(1f, tween(2000))
            fraction.snapTo(0f)
        }
        reveal = true
        fraction.animateTo(1f, tween(1000))
    }
    if (!reveal) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    } else {
        val configuration = LocalConfiguration.current
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = configuration.screenWidthDp.toString(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = configuration.screenHeightDp.toString(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }


}
