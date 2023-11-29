package com.example.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName


data class Forecast(
    @SerializedName("forecastday") var forecastday: ArrayList<Forecastday> = arrayListOf()
)