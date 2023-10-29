package com.example.weatherapp.data

data class WeatherModel(
    val city: String, //
    val time: String, //
    val currentTemp: String, // время паоследней ласт темпы
    val condition: String, // сдождь или ветер, чо  будет
    val icon: String, // фоточка
    val maxTemp: String, //
    val minTemp: String, //
    val hours: String //
)
