package com.salvaperez.airquality.data.datasource

import com.salvaperez.airquality.data.api.AirQualityApi
import com.salvaperez.airquality.data.api.AirQualityResult
import com.salvaperez.airquality.data.api.ApiCall
import com.salvaperez.airquality.data.entity.AirDetailsEntity
import com.salvaperez.airquality.data.entity.ErrorEntity

class RemoteAirQualityDataSource(private val api: AirQualityApi): AirQualityDataSource {

    override suspend fun getAirQuality(): AirQualityResult<ErrorEntity, List<AirDetailsEntity>> {
        return ApiCall.safeApiCall {
            api.getAirQuality().list
        }
    }

}