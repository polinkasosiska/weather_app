package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.dto.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("days") days: Int
    ): Response<WeatherResponse>
}