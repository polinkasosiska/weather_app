package com.example.weatherapp.presentation.model

data class MainUIState(
    val daysList: List<WeatherModel> = emptyList(),
    val currentDay: WeatherModel = WeatherModel(),
    val isLoading: Boolean = false
)