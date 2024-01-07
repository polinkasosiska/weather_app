package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.dto.WeatherResponse
import com.example.weatherapp.domain.models.WeatherModel
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeather(
        key: String,
        query: String,
        days: Int,
        lang: String
    ): Response<WeatherResponse>
}