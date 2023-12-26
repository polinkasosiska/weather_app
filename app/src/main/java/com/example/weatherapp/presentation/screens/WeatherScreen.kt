package com.example.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.presentation.components.LoadingIndicator
import com.example.weatherapp.presentation.viewmodel.MainViewModel

@Composable
fun WeatherScreen(snackbarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<MainViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    viewModel.getData()
    Column {
        MainCard(uiState.currentDay)
        TabLayout(uiState.daysList, uiState.currentDay)
    }

    ////

    if (uiState.isLoading) LoadingIndicator()

    LaunchedEffect(uiState.isError) {
        if (uiState.isError) snackbarHostState.showSnackbar("Ошибка!")
    }
}