package com.obiangetfils.weatherapp.openweathermap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "c88998a844508891e08c5f9bb9f98abe"

interface OpenWeatherService {

    @GET("data/2.5/weather?units=metric&lang=fr")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY):Call<WeatherWrapper>
}