package com.salvaperez.airquality.data.entity

import com.google.gson.annotations.SerializedName

data class ComponentsEntity(
    @SerializedName("co") val co: Double,
    @SerializedName("nh3")  val nh3: Double,
    @SerializedName("no") val no: Double,
    @SerializedName("no2") val no2: Double,
    @SerializedName("o3") val o3: Double,
    @SerializedName("pm10") val pm10: Double,
    @SerializedName("pm2_5") val pm2_5: Double,
    @SerializedName("so2") val so2: Double
)