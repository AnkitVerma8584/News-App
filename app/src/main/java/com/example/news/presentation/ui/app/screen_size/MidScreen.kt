package com.example.news.presentation.ui.app.screen_size

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.news.R
import com.example.news.presentation.ui.app.screens.BottomBarScreens
import com.example.news.presentation.ui.app.screens.BottomNavGraph

@Composable
fun MediumScreen(
    navController: NavHostController,
    screens: List<BottomBarScreens>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            header = {
                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.new_icon),
                        contentDescription = null
                    )
                }
            }
        ) {
            screens.forEach { item ->
                NavigationRailItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route == item.route
                    } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }, icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "Navigation Icon"
                        )
                    }, label = {
                        Text(text = item.title)
                    })
            }
        }
        BottomNavGraph(navController = navController)
    }
}