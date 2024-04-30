package com.karna.weatherappcompose.data.forecastTemperatureModels

import com.google.gson.annotations.SerializedName

data class ForecastItem(
    @SerializedName("dt") val dt: Long = 0,
    @SerializedName("main") val main: ForecastMainInfo = ForecastMainInfo(),
    @SerializedName("weather") val weather: List<WeatherInfo> = emptyList(),
    @SerializedName("dt_txt") val dt_txt: String = String()
)
