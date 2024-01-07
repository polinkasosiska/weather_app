package com.example.weatherapp.domain.models

data class HourWeatherModel(
    var time: String = "", //
    val currentTemp: String = "", // время паоследней ласт темпы
    val condition: String = "", // сдождь или ветер, чо  будет
    val icon: String = "", // фоточка
)
