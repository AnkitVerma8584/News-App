package com.example.news.presentation.ui.screens

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.presentation.ui.main.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun News(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {

    val gradient = listOf(
        MaterialTheme.colorScheme.background.copy(alpha = 1f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.4f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.1f),
    )

    Column(modifier = modifier) {
        SearchBar(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 5.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            val state = viewModel.newsState

            if (state.news != null && state.news.isNotEmpty()) {
                val listState = rememberLazyGridState()
                LazyVerticalGrid(
                    state = listState,
                    columns = GridCells.Adaptive(300.dp),
                    contentPadding = PaddingValues(top = 24.dp),
                    content = {
                        items(items = state.news) { name ->
                            NewsCard(news = name)
                        }
                    })

                val shouldShowButton = remember {
                    derivedStateOf { listState.firstVisibleItemIndex > 0 }
                }
                val coroutineScope = rememberCoroutineScope()

                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(5.dp),
                    visible = shouldShowButton.value,
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut(),

                    content = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index = 0)
                                }
                            }, modifier = Modifier
                                .padding(16.dp)
                                .clip(CircleShape)
                                .background(color = MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = null
                            )
                        }
                    })

            }
            if (state.isLoading) {

                LazyVerticalGrid(columns = GridCells.Adaptive(300.dp), content = {
                    repeat(4) {
                        item {
                            ShimmerAnimation()
                        }
                    }
                })
            }
            if (state.error != null) {
                Text(
                    text = state.error.asString(),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(brush = Brush.verticalGradient(gradient))
            )
        }
    }
}