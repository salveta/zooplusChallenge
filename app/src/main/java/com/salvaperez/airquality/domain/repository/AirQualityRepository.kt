package com.salvaperez.airquality.domain.repository

import com.salvaperez.airquality.data.api.AirQualityError
import com.salvaperez.airquality.data.api.AirQualityResult
import com.salvaperez.airquality.domain.model.AirDetailsModel

interface AirQualityRepository {

    suspend fun getAirQuality(): AirQualityResult<AirQualityError, List<AirDetailsModel>>

}