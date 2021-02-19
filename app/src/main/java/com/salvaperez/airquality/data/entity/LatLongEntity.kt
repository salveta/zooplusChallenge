package com.salvaperez.airquality.data.entity

import com.google.gson.annotations.SerializedName

data class LatLongEntity(
    @SerializedName("coord") val coord: CoordEntity
)