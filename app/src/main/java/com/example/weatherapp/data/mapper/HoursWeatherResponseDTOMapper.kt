package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.dto.WeatherResponse
import com.example.weatherapp.domain.models.HourWeatherModel
import com.example.weatherapp.domain.models.WeatherModel

class HoursWeatherResponseDTOMapper {
    fun map(input: WeatherResponse): List<HourWeatherModel> {
        val list = ArrayList<HourWeatherModel>()
        for (hour in input.forecast.forecastday[0].hour) {
            list.add(
                HourWeatherModel(
                    time = hour.time,
                    currentTemp = hour.tempC.toString(),
                    condition = hour.condition.text,
                    icon = hour.condition.icon,
                )
            )
        }
        return list
    }
}