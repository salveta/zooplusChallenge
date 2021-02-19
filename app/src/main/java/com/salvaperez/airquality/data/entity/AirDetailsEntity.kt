package com.salvaperez.airquality.data.entity

import com.google.gson.annotations.SerializedName

data class AirDetailsEntity(
    @SerializedName("components") val components: ComponentsEntity,
    @SerializedName("dt") val dt: Int,
    @SerializedName("main") val main: MainEntity
)