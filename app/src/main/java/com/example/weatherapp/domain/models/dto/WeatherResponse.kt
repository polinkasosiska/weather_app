package com.example.weatherapp.domain.models.dto

import com.google.gson.annotations.SerializedName


data class WeatherResponse(

    @SerializedName("location") var location: Location = Location(),
    @SerializedName("current") var current: Current = Current(),
    @SerializedName("forecast") var forecast: Forecast = Forecast()

)