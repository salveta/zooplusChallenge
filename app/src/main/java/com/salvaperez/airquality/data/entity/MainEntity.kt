package com.salvaperez.airquality.data.entity

import com.google.gson.annotations.SerializedName

data class MainEntity(
    @SerializedName("aqi") val aqi: Int
)