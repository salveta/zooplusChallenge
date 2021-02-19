package com.salvaperez.airquality

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.salvaperez.airquality.domain.model.AirDetailsModel
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.domain.usecase.GetAirQualityUseCase
import com.salvaperez.airquality.presentation.detail.DetailViewModel
import com.salvaperez.airquality.presentation.main.MainViewModel
import com.salvaperez.airquality.presentation.utils.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAirQualityApplication: GetAirQualityUseCase

    private lateinit var vm: MainViewModel

    private val uiScope = CoroutineScope(Dispatchers.Main)


    @Before
    fun setUp() {
        vm = MainViewModel(getAirQualityApplication)
    }

    @Test
    fun `get air quality method return success object`() {
        uiScope.launch {
            whenever(getAirQualityApplication.invoke( onGetErrorAirQuality = {
                val resource = vm.airData.value
                assertEquals(resource?.status, Resource.Status.SUCCESS)
            }, onGetAirQualitySuccess = {})).thenReturn(Unit)
        }
    }
}