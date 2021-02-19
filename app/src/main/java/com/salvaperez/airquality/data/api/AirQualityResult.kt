package com.salvaperez.airquality.data.api

sealed class AirQualityResult<out Failure, out Success> {

    data class Failure<out Failure>(val failure: Failure) : AirQualityResult<Failure, Nothing>()

    data class Success<out Success>(val success: Success) : AirQualityResult<Nothing, Success>()

    val isSuccess get() = this is AirQualityResult.Success<Success>
    val isFailure get() = this is AirQualityResult.Failure<Failure>

}

fun <Failure, Success, T> AirQualityResult<Failure, Success>.fold(failure: (Failure) -> T, success: (Success) -> T): T =
    when (this) {
        is AirQualityResult.Failure -> failure(this.failure)
        is AirQualityResult.Success -> success(this.success)
    }