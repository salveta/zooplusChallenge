package com.salvaperez.airquality.data.api

import com.salvaperez.airquality.BuildConfig
import com.salvaperez.airquality.data.entity.AirEntity
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AirQualityApi {

    @Headers("Accept: application/json")
    @GET("forecast")
    suspend fun getAirQuality(
        @Query("lat") lat: String = "48.13743",
        @Query("lon") lon: String = "11.57549",
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): AirEntity
}


