package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.model.dto.WeatherResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) :
    WeatherRepository {
    override suspend fun getWeather(
        key: String,
        query: String,
        days: Int
    ): Response<WeatherResponse> = withContext(Dispatchers.IO) {
        weatherApi.getWeather(key, query, days)
    }
}