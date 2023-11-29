package com.example.weatherapp.presentation.model

import com.example.weatherapp.domain.models.WeatherModel

data class MainUIState(
    val daysList: List<WeatherModel> = emptyList(),
    val currentDay: WeatherModel = WeatherModel(),
    val isLoading: Boolean = false
)