package com.karna.weatherappcompose.ui.weather

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.karna.weatherappcompose.data.ApiState
import com.karna.weatherappcompose.data.currentTemperatureModels.WeatherData
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastDisplayModel
import com.karna.weatherappcompose.data.forecastTemperatureModels.ForecastItem
import com.karna.weatherappcompose.data.forecastTemperatureModels.WeatherForecastResponse
import com.karna.weatherappcompose.utils.CommonUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(private val repo: WeatherInfoRepo) : ViewModel() {

    private val _currentWeatherInfo = MutableSharedFlow<WeatherData>()
    val currentWeatherInfo = _currentWeatherInfo.asSharedFlow()

    private val _nextFourDaysForecast = MutableSharedFlow<List<ForecastDisplayModel>>()
    val nextFourDaysForecast = _nextFourDaysForecast.asSharedFlow()

    private val _loader = MutableSharedFlow<Boolean>()
    val loader = _loader.asSharedFlow()

    private val _showSnackBar = MutableSharedFlow<Boolean>()
    val showSnackBar = _showSnackBar.asSharedFlow()

    init {
        getWeatherInformation()
    }

    fun loaderUpdate(isShow: Boolean) {
        viewModelScope.launch {
            _loader.emit(isShow)
        }
    }

    // Function to get both Current  and Forecast weather info  from server
     fun getWeatherInformation() {

        CoroutineScope(Dispatchers.IO).launch {

            // Helps to get current weather info
            val currentWeatherApiStateDeferred: Deferred<ApiState> =
                async { repo.getCurrentWeather() }
            // Helps to get forecast weather info
            val forecastWeatherApiStateDeferred: Deferred<ApiState> =
                async { repo.getForeCastWeather() }


            val currentWeatherApiState = currentWeatherApiStateDeferred.await()
            val forecastWeatherApiState = forecastWeatherApiStateDeferred.await()

            when (currentWeatherApiState) {
                is ApiState.Failure -> {
                    _showSnackBar.emit(true)
                }

                is ApiState.Success -> {
                    _currentWeatherInfo.emit(currentWeatherApiState.data as WeatherData)
                }
            }

            when (forecastWeatherApiState) {
                is ApiState.Failure -> {
                    _showSnackBar.emit(true)
                }

                is ApiState.Success -> {
                    val forecastWeatherData =
                        forecastWeatherApiState.data as WeatherForecastResponse

                    Log.d(
                        "VM",
                        "Checking ForeCast --------- ${Gson().toJson(forecastWeatherApiState)}"
                    )

                    // Get the current date
                    val currentDate = LocalDate.now().dayOfMonth

                    // Filter the forecast data to include only the next four days' forecast
                    val nextFourDaysForecast =
                        forecastWeatherData.forecastList.filter { forecastItem ->
                            // Extract the date from the forecast item
                            val forecastDate = LocalDateTime.parse(
                                forecastItem.dt_txt,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            ).dayOfMonth
                            Log.d("VM", "Checking ForeCast --------- ")
                            // Check if the forecast date is within the next four days
                            forecastDate in currentDate + 1..currentDate + 4
                        }.distinctBy { forecastItem ->
                            // Use distinctBy to remove duplicate entries based on the date
                            LocalDateTime.parse(
                                forecastItem.dt_txt,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            ).dayOfMonth
                        }


                    Log.d("VM", "Checking ForeCast --------- ${nextFourDaysForecast.size} ---")

                    val datesList = forecastWeatherData.forecastList.distinctBy { it.dt_txt }

                    Log.d("VM", "Checking ForeCast --------- Distint list ${datesList.size} ---")

                    val listForDisplay = ArrayList<ForecastDisplayModel>()
                    for (item in nextFourDaysForecast) {
                        val pair = getDayAndTemperature(item)
                        listForDisplay.add(ForecastDisplayModel(pair.first, pair.second))
                    }
                    Log.d("VM", "Checking ForeCast --------- ${listForDisplay.size}")
                    _nextFourDaysForecast.emit(listForDisplay)

                    _loader.emit(false)
                }
            }

        }

    }

    private fun getDayOfWeek(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(dateString)

        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        return outputFormat.format(date!!)
    }

    private fun getDayAndTemperature(forecastItem: ForecastItem): Pair<String, String> {
        val day = getDayOfWeek(forecastItem.dt_txt)
        val temp =
            "${(CommonUtils.kelvinToCelsius(forecastItem.main.temperature)).toInt()}\u00B0 C"
        return Pair(day, temp)
    }


}