package com.obiangetfils.weatherapp.openweathermap

import com.google.gson.annotations.SerializedName

data class WeatherWrapper(
    val weather: Array<WeatherData>,
    val main: MainData
)

data class WeatherData(
    val description: String,
    val icon: String
)

data class MainData(
    @SerializedName("temp") val temperature: String,
    val pressure: Int,
    val humidity: Int
)
