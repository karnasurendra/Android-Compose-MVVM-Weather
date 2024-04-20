package com.karna.weatherappcompose.data.forecastTemperatureModels

import com.google.gson.annotations.SerializedName
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastItem

data class WeatherForecastResponse(
    @SerializedName("list") val forecastList: List<ForecastItem> = emptyList()
)
