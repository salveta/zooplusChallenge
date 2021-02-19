package com.salvaperez.airquality.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.espresso.idling.CountingIdlingResource
import com.salvaperez.airquality.domain.model.AirDetailsModel
import com.salvaperez.airquality.domain.usecase.GetAirQualityUseCase
import com.salvaperez.airquality.presentation.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val getAirQualityUseCase: GetAirQualityUseCase): ViewModel() {


    private val _airData = MutableLiveData<Resource<List<AirDetailsModel>>>()
    val airData: LiveData<Resource<List<AirDetailsModel>>> get() = _airData

    fun onInit() {
        _airData.value = Resource.loading(true)
        viewModelScope.launch {
            getAirQualityUseCase(
                onGetAirQualitySuccess = {getAirQuality(it)},
                onGetErrorAirQuality = {getAirQualityError()}
            )
        }
    }

    private fun getAirQuality(data: List<AirDetailsModel>){
        _airData.value = Resource.loading(false)
        _airData.value = Resource.success(data)
    }

    private fun getAirQualityError() {
        _airData.value = Resource.error()
    }
}