package com.salvaperez.airquality.data.datasource

import com.salvaperez.airquality.data.api.AirQualityResult
import com.salvaperez.airquality.data.entity.AirDetailsEntity
import com.salvaperez.airquality.data.entity.ErrorEntity

interface AirQualityDataSource {

    suspend fun getAirQuality(): AirQualityResult<ErrorEntity, List<AirDetailsEntity>>

}