package com.salvaperez.airquality.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComponentNameModel(
    val name: String,
    val value: String
): Parcelable