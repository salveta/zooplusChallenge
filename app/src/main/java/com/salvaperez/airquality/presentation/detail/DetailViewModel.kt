package com.salvaperez.airquality.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.presentation.utils.Resource

class DetailViewModel: ViewModel() {

    private val _airComponents = MutableLiveData<Resource<List<ComponentNameModel>>>()
    val airComponents: LiveData<Resource<List<ComponentNameModel>>> get() = _airComponents

    fun onInit(place: ArrayList<ComponentNameModel>?){
        place?.let {
            _airComponents.value = Resource.success(place.toList())
        }?: run {
            _airComponents.value = Resource.error()
        }
    }
}