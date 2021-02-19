package com.salvaperez.airquality.data.api

import java.lang.Exception

sealed class AirQualityError: Exception() {

    class ServerError: AirQualityError()
    class Unknown: AirQualityError()

}