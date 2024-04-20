package com.karna.weatherappcompose.ui.weather

import com.karna.weatherappcompose.data.ApiState

interface WeatherInfoRepo {

    suspend fun getCurrentWeather(): ApiState

    suspend fun getForeCastWeather(): ApiState

}