package com.karna.weatherappcompose.data

import com.karna.weatherappcompose.data.currentTemperatureModels.WeatherData
import com.karna.weatherappcompose.data.forecastTemperatureModels.WeatherForecastResponse
import com.karna.weatherappcompose.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApiService {

    @GET(AppConstants.Apis.CURRENT_WEATHER_API)
    fun getCurrentWeather(): Call<WeatherData>

    @GET(AppConstants.Apis.FORECAST_WEATHER_API)
    fun getForeCastWeather(): Call<WeatherForecastResponse>
}