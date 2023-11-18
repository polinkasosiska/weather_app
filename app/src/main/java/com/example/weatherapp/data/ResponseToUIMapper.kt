package com.example.weatherapp.data

import com.example.weatherapp.domain.models.dto.WeatherResponse
import com.example.weatherapp.presentation.model.WeatherModel

class ResponseToUIMapper {

    fun map(input: WeatherResponse): List<WeatherModel> {
        val list = ArrayList<WeatherModel>()
        for (forecastDay in input.forecast.forecastday) {
            list.add(
                WeatherModel(
                    city = input.location.name,
                    time = forecastDay.date,
                    currentTemp = "",
                    condition = forecastDay.day.condition.text,
                    icon = forecastDay.day.condition.icon,
                    maxTemp = forecastDay.day.maxtempC.toString(),
                    minTemp = forecastDay.day.mintempC.toString(),
                    hours = forecastDay.hour.toString()
                )
            )
        }
        list[0] = list[0].copy(
            time = input.current.lastUpdated,
            currentTemp = input.current.tempC.toString(),
        )
        return list
    }
}