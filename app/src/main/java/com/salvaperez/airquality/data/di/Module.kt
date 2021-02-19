package com.salvaperez.airquality.data.di

import android.app.Application
import com.salvaperez.airquality.BuildConfig
import com.salvaperez.airquality.data.api.AirQualityApi
import com.salvaperez.airquality.data.datasource.RemoteAirQualityDataSource
import com.salvaperez.airquality.data.repository.AirQualityDataRepository
import com.salvaperez.airquality.domain.repository.AirQualityRepository
import com.salvaperez.airquality.domain.usecase.GetAirQualityUseCase
import com.salvaperez.airquality.presentation.detail.DetailActivity
import com.salvaperez.airquality.presentation.detail.DetailViewModel
import com.salvaperez.airquality.presentation.main.MainActivity
import com.salvaperez.airquality.presentation.main.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(
            listOf(
                dataModule,
                air
            )
        )
    }
}

val air = module(override = true) {
    scope(named<MainActivity>()) {
        viewModel {
            MainViewModel(
                get()
            )
        }
        scoped { GetAirQualityUseCase(get()) }
    }

    single { RemoteAirQualityDataSource(get()) }
    factory<AirQualityRepository> {
        AirQualityDataRepository(
            remoteAirQualityDataSource = get()
        )
    }

    scope(named<DetailActivity>()) {
        viewModel {
            DetailViewModel()
        }
    }
}

val dataModule = module(override = true) {
    single<CoroutineDispatcher> { Dispatchers.Main }
    single<AirQualityApi> { get<Retrofit>().create() }

    val apiTimeOutInSeconds = 60L

    single {
        Retrofit.Builder()
            .apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BuildConfig.API_URL)
                client(get())
            }
            .build()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .readTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .writeTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    factory {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        return@factory interceptor
    }
}