package com.karna.weatherappcompose.utils

import java.text.DecimalFormat

object CommonUtils {

    fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

}