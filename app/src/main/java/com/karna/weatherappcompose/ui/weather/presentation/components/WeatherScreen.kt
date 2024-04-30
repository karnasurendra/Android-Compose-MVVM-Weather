package com.karna.weatherappcompose.ui.weather.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.karna.weatherappcompose.data.currentTemperatureModels.WeatherData
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastDisplayModel
import com.karna.weatherappcompose.ui.theme.screenBgColor
import com.karna.weatherappcompose.ui.weather.WeatherInfoViewModel

@Composable
fun WeatherScreen(viewModel: WeatherInfoViewModel) {

    val currentWeatherInfo by viewModel.currentWeatherInfo.collectAsState(WeatherData())
    val forecastWeatherInfo by viewModel.nextFourDaysForecast.collectAsState(emptyList())
    val loader by viewModel.loader.collectAsState(true)

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(screenBgColor)
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            CurrentWeather(currentWeatherInfo)
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = true, // Set to true to show the animation
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 1000)
                )
            ) {
                ForeCastWeather(
                    forecastWeatherInfo,
                    Modifier
                        .fillMaxWidth()
                )
            }
        }

        if (loader) {
            CircularProgressIndicator(modifier = Modifier
                .size(70.dp)
                .align(Alignment.Center))
        }
    }


}