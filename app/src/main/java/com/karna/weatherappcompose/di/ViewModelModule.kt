package com.karna.weatherappcompose.di

import com.karna.weatherappcompose.data.WeatherApiService
import com.karna.weatherappcompose.ui.weather.WeatherInfoRepo
import com.karna.weatherappcompose.ui.weather.WeatherInfoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherRepo(weatherApiService: WeatherApiService): WeatherInfoRepo {
        return WeatherInfoRepoImpl(weatherApiService)
    }

}