package com.karna.weatherappcompose.data

sealed class ApiState {

    class Success(val data: Any) : ApiState()

    class Failure(val throwable: Throwable) : ApiState()

}