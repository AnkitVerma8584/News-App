package com.example.news.presentation.ui.app.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.presentation.ui.app.screens.home.HomeScreen
import com.example.news.presentation.ui.app.screens.profile.ProfileScreen
import com.example.news.presentation.ui.app.screens.settings.SettingsScreen

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.route
    ) {
        composable(route = BottomBarScreens.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(route = BottomBarScreens.Profile.route) {
            ProfileScreen(modifier = modifier)
        }
        composable(route = BottomBarScreens.Settings.route) {
            SettingsScreen(modifier = modifier)
        }
    }
}
