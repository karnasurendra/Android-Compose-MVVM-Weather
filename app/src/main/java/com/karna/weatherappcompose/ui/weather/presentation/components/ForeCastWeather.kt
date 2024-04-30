package com.karna.weatherappcompose.ui.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karna.weatherappcompose.R
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastDisplayModel
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastItem
import com.karna.weatherappcompose.ui.theme.screenBgColor

@Composable
fun ForeCastWeather(forecastWeatherInfo: List<ForecastDisplayModel>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier.background(Color.White)
    ) {
        items(forecastWeatherInfo) { forecastItem ->
            ForecastItemRow(forecastItem)
        }
    }
}

@Composable
fun ForecastItemRow(forecastItem: ForecastDisplayModel) {

    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 30.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = forecastItem.day,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = forecastItem.temperate,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )
        }
        Divider(
            Modifier
                .padding(start = 15.dp, end = 15.dp)
                .height(1.dp), color = screenBgColor)
    }

}

