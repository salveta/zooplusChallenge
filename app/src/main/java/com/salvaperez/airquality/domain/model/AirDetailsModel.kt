package com.salvaperez.airquality.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AirDetailsModel(
    val components: List<ComponentNameModel>,
    val dataTime: String,
    val airQualityId: Int,
    val airQualityName: String,
    val airQualityDate: String
): Parcelable