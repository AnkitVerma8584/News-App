package com.example.news.presentation.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.theme.NewsTheme
import com.example.news.presentation.ui.app.screen_size.CompactScreen
import com.example.news.presentation.ui.app.screen_size.ExpandedScreen
import com.example.news.presentation.ui.app.screen_size.MediumScreen
import com.example.news.presentation.ui.app.screens.BottomBarScreens
import com.example.news.util.WindowInfo
import com.example.news.util.rememberWindowInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                NewsPage()
            }
        }
    }
}

@Composable
private fun NewsPage() {
    val navController = rememberNavController()

    val screens = listOf(
        BottomBarScreens.Home,
        BottomBarScreens.Profile,
        BottomBarScreens.Settings,
    )

    val windowInfo: WindowInfo = rememberWindowInfo()

    val height = windowInfo.screenHeightInfo

    when (windowInfo.screenWidthInfo) {
        is WindowInfo.WindowType.Compact -> CompactScreen(
            navController = navController,
            screens = screens
        )

        is WindowInfo.WindowType.Medium -> MediumScreen(
            screens = screens,
            navController = navController
        )
        is WindowInfo.WindowType.Expanded ->
            if (height == WindowInfo.WindowType.Compact)
                MediumScreen(navController = navController, screens = screens)
            else
                ExpandedScreen(
                    navController = navController,
                    screens = screens
                )
    }
}

