package com.example.weatherapp.domain.models.dto

import com.google.gson.annotations.SerializedName


data class Forecast(

    @SerializedName("forecastday") var forecastday: ArrayList<Forecastday> = arrayListOf()

)