package com.example.news.presentation.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.news.domain.modals.News
import kotlinx.coroutines.launch

@Composable
internal fun NewsCard(
    modifier: Modifier = Modifier,
    news: News
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(5.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.large)
    ) {
        CardContent(news = news)
    }
}

@Composable
private fun CardContent(news: News) {
    val scaffoldState = rememberScaffoldState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable {
                expanded = !expanded
            },
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(
                model = news.urlToImage
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
        )
        Text(
            text = news.title ?: "No Title",
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = news.description ?: "No description available",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        if (expanded) {
            Text(
                text = news.content ?: "No content available",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
            val context = LocalContext.current
            val color =
                if (isSystemInDarkTheme())
                    Color(53, 104, 255, 255)
                else Color(0, 43, 170, 255)
            val scope = rememberCoroutineScope()
            Text(
                text = "View full story",
                color = color,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(16.dp, 5.dp, 16.dp, 16.dp)
                    .clickable {
                        try {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(news.url)
                                )
                            )
                        } catch (e: Exception) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = e.message ?: "Unable to open url",
                                    actionLabel = "Ok"
                                )
                            }
                        }
                    })
        }
    }
}