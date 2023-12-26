package com.example.weatherapp.presentation.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    data object Weather : Screen("weather", R.string.weather, Icons.Filled.Home)
    data object Settings : Screen("settings", R.string.settings, Icons.Filled.Settings)
}