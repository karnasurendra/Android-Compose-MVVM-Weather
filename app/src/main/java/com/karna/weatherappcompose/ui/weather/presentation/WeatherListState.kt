package com.karna.weatherappcompose.ui.weather.presentation

import com.karna.weatherappcompose.data.currentTemperatureModels.WeatherData
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastItem

data class WeatherListState(
    val currentWeather:List<WeatherData> = emptyList(),
    val foreCastWeather:List<ForecastItem> = emptyList()
)
