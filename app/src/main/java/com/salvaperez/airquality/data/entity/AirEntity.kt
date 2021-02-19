package com.salvaperez.airquality.data.entity

import com.google.gson.annotations.SerializedName

data class AirEntity(
    @SerializedName("coord") val coord: LatLongEntity,
    @SerializedName("list") val list: List<AirDetailsEntity>
)