package com.example.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName


data class Condition(
    @SerializedName("text") var text: String = "",
    @SerializedName("icon") var icon: String = "",
    @SerializedName("code") var code: Int? = null
)