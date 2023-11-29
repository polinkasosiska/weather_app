package com.example.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName


data class Location(

    @SerializedName("name") var name: String = "",
    @SerializedName("region") var region: String = "",
    @SerializedName("country") var country: String = "",
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("tz_id") var tzId: String = "",
    @SerializedName("localtime_epoch") var localtimeEpoch: Int? = null,
    @SerializedName("localtime") var localtime: String = ""

)