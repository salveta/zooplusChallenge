package com.salvaperez.airquality.data.api

import com.salvaperez.airquality.data.entity.ErrorEntity
import com.salvaperez.airquality.data.entity.toErrorEntity
import retrofit2.HttpException

object ApiCall {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): AirQualityResult<ErrorEntity, T> {
        return try {
            val result = apiCall()
            AirQualityResult.Success(result)
        } catch (throwable: Throwable) {
            if (throwable is HttpException) {
                val errorEntity = throwable.toErrorEntity()
                AirQualityResult.Failure(errorEntity)
            } else {
                AirQualityResult.Failure(ErrorEntity.unknownEntity)
            }
        }
    }
}