package com.example.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName


data class Current(
    @SerializedName("last_updated") var lastUpdated: String = "",
    @SerializedName("temp_c") var tempC: Double = 0.0,
)