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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.karna.weatherappcompose.data.currentTemperatureModels.WeatherData
import com.karna.weatherappcompose.ui.theme.screenBgColor
import com.karna.weatherappcompose.ui.weather.WeatherInfoViewModel

@Composable
fun WeatherScreen(viewModel: WeatherInfoViewModel) {

    val currentWeatherInfo by viewModel.currentWeatherInfo.collectAsState(WeatherData())
    val forecastWeatherInfo by viewModel.nextFourDaysForecast.collectAsState(emptyList())
    val loader by viewModel.loader.collectAsState(true)
    val showSnackBar by viewModel.showSnackBar.collectAsState(false)

    val snackBarHostState by remember { mutableStateOf(SnackbarHostState()) }

    LaunchedEffect(showSnackBar) {
        if (showSnackBar) {

            viewModel.loaderUpdate(false)

            val result = snackBarHostState.showSnackbar(
                message = "Failed to Fetch Weather.",
                actionLabel = "Try again",
                duration = SnackbarDuration.Indefinite
            )

            when (result) {
                SnackbarResult.Dismissed -> {
                    // Nothing to do
                }

                SnackbarResult.ActionPerformed -> {
                    snackBarHostState.currentSnackbarData?.dismiss()
                    viewModel.loaderUpdate(true)
                    viewModel.getWeatherInformation()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            ) { snackBarData ->
                Snackbar(
                    snackbarData = snackBarData,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
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
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }


}