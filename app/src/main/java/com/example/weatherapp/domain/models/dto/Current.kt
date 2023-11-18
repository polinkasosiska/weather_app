package com.example.weatherapp.domain.models.dto

import com.google.gson.annotations.SerializedName


data class Current(
    @SerializedName("last_updated") var lastUpdated: String = "",
    @SerializedName("temp_c") var tempC: Double = 0.0,
)