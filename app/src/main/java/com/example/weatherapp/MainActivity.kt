package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.model.Screen
import com.example.weatherapp.presentation.screens.SettingsScreen
import com.example.weatherapp.presentation.screens.WeatherScreen
import com.example.weatherapp.ui.theme.Purple60
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val items = listOf(Screen.Weather, Screen.Settings)
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    bottomBar = {
                        BottomNavigation(backgroundColor = Color.Black) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                val selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            tint = if (selected) Purple60 else Color.White,
                                            contentDescription = null
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = stringResource(screen.resourceId),
                                            color = if (selected) Purple60 else Color.White,
                                        )
                                    },
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { contentPadding ->
                    Image(
                        painter = painterResource(
                            id = R.drawable.weather_bg
                        ),
                        contentDescription = "im1",
                        //чтобы картинка заняла весь экран
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.84f)
                            .padding(contentPadding), ///alpha для прозрачности
                        ///чтобы не было белых полей
                        contentScale = ContentScale.Crop
                    )
                    NavHost(
                        navController,
                        startDestination = Screen.Weather.route,
                        Modifier.padding(contentPadding)
                    ) {
                        composable(Screen.Weather.route) { WeatherScreen(snackbarHostState) }
                        composable(Screen.Settings.route) { SettingsScreen() }
                    }
                }
            }
        }
    }
}