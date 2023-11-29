package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.dto.WeatherResponse
import com.example.weatherapp.domain.models.WeatherModel

class WeatherResponseDTOMapper {

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