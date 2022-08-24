package com.example.news.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news.domain.modals.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityPage(
    onBacked: (Unit) -> Unit = {}
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "News") }, navigationIcon = {
                    IconButton(onClick = { onBacked.invoke(Unit) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Exit App"
                        )
                    }
                }
            )
        }
    ) { values ->
        News(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsBox() {

    val gradient = listOf(
        MaterialTheme.colorScheme.background.copy(alpha = 1f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.1f),
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            NewsCard(
                news = News(
                    title = "Some random title",
                    description = "Hello this is desc",
                    url = "",
                    urlToImage = null,
                    content = ""
                )
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(brush = Brush.verticalGradient(gradient))
            )
        }
    }
}

