package com.example.news.presentation.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.presentation.ui.main.MainViewModel
import com.example.news.util.WindowInfo
import com.example.news.util.rememberWindowInfo

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

                val windowInfo: WindowInfo = rememberWindowInfo()

                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
                    LazyColumn(contentPadding = PaddingValues(top = 24.dp)) {
                        items(items = state.news) { name ->
                            NewsCard(news = name)
                        }
                    }
                else LazyVerticalGrid(
                    columns = GridCells.Adaptive(300.dp),
                    contentPadding = PaddingValues(top = 24.dp),
                    content = {
                        items(items = state.news) { name ->
                            NewsCard(news = name)
                        }
                    })

            }
            if (state.isLoading) {

                val windowInfo: WindowInfo = rememberWindowInfo()
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
                    LazyColumn {
                        repeat(4) {
                            item {
                                ShimmerAnimation()
                            }
                        }
                    }
                else LazyVerticalGrid(columns = GridCells.Adaptive(300.dp), content = {
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
                    modifier = Modifier.fillMaxWidth(0.8f),
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