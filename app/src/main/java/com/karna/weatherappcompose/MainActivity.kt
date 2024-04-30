package com.karna.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.karna.weatherappcompose.ui.theme.WeatherAppComposeTheme
import com.karna.weatherappcompose.ui.weather.WeatherInfoViewModel
import com.karna.weatherappcompose.ui.weather.presentation.components.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppComposeTheme {
                Weather()
            }
        }
    }
}

@Composable
fun Weather() {
    val viewModel: WeatherInfoViewModel = hiltViewModel()
    WeatherScreen(viewModel)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppComposeTheme {
        Weather()
    }
}