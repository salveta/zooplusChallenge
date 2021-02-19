package com.salvaperez.airquality.domain.model

import com.salvaperez.airquality.data.api.AirQualityError
import com.salvaperez.airquality.data.entity.ErrorEntity

fun ErrorEntity.toChallengeError(): AirQualityError {
    return when (code) {
        500 -> AirQualityError.ServerError()
        else -> AirQualityError.Unknown()
    }
}