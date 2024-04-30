package com.karna.weatherappcompose.ui.weather.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karna.weatherappcompose.R
import com.karna.weatherappcompose.data.currentTemperatureModels.WeatherData
import com.karna.weatherappcompose.utils.CommonUtils

@Composable
fun CurrentWeather(currentWeatherInfo: WeatherData) {

    Column(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = "${CommonUtils.kelvinToCelsius(currentWeatherInfo.mainInfo.temperature).toInt()}${
                stringResource(
                    id = R.string.degree_indicator
                )
            }",
            style = TextStyle(
                fontSize = 96.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.bangalore),
            style = TextStyle(
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        )
    }


}

