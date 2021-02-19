package com.salvaperez.airquality.domain.usecase

import com.salvaperez.airquality.data.api.AirQualityError
import com.salvaperez.airquality.data.api.fold
import com.salvaperez.airquality.domain.model.AirDetailsModel
import com.salvaperez.airquality.domain.repository.AirQualityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAirQualityUseCase(private val airQualityRepository: AirQualityRepository) {

    suspend operator fun invoke(
        onGetAirQualitySuccess: (data: List<AirDetailsModel>) -> Unit,
        onGetErrorAirQuality: (data: AirQualityError) -> Unit
    ) {

        val result = withContext(Dispatchers.IO){
            airQualityRepository.getAirQuality()
        }

        result.fold(
            failure = { error -> onGetErrorAirQuality(error) },
            success = { data -> onGetAirQualitySuccess(data) }
        )
    }
}