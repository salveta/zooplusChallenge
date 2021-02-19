package com.salvaperez.airquality.data.repository

import com.salvaperez.airquality.data.api.AirQualityError
import com.salvaperez.airquality.data.api.AirQualityResult
import com.salvaperez.airquality.data.api.fold
import com.salvaperez.airquality.data.datasource.RemoteAirQualityDataSource
import com.salvaperez.airquality.domain.model.AirDetailsModel
import com.salvaperez.airquality.domain.model.toChallengeError
import com.salvaperez.airquality.domain.model.toModel
import com.salvaperez.airquality.domain.repository.AirQualityRepository

class AirQualityDataRepository(private val remoteAirQualityDataSource: RemoteAirQualityDataSource): AirQualityRepository {

    override suspend fun getAirQuality(): AirQualityResult<AirQualityError, List<AirDetailsModel>> {
        val result = remoteAirQualityDataSource.getAirQuality()
        return result.fold(
            { errorEntity -> AirQualityResult.Failure(errorEntity.toChallengeError()) },
            { dataEntity ->
                AirQualityResult.Success(dataEntity.map { it.toModel() }) }
        )
    }
}