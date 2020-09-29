package com.obiangetfils.weatherapp.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.obiangetfils.weatherapp.App
import com.obiangetfils.weatherapp.R
import com.obiangetfils.weatherapp.openweathermap.WeatherWrapper
import com.obiangetfils.weatherapp.openweathermap.mapOPenWeatherDataToWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {

    lateinit var cityName: String
    private val TAG = WeatherFragment::class.java.simpleName

    lateinit var city: TextView
    lateinit var weatherIcon: ImageView
    lateinit var weatherDescription: TextView
    lateinit var temprature: TextView
    lateinit var humidity: TextView
    lateinit var pressure: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        city = view.findViewById(R.id.city)
        weatherIcon = view.findViewById(R.id.weather_icon)
        weatherDescription = view.findViewById(R.id.weather_description)
        temprature = view.findViewById(R.id.temperature)
        humidity = view.findViewById(R.id.humidity)
        pressure = view.findViewById(R.id.pressure)
        return view
    }

    companion object {
        val EXTRA_CITY_NAME = "com.kotlin.weather.extras.EXTRA_CITY_NAME"
        fun newInstance() = WeatherFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity?.intent!!.hasExtra(EXTRA_CITY_NAME)) {

            updateWeatherForCity(activity!!.intent.getStringExtra(EXTRA_CITY_NAME))
        }
    }

    private fun updateWeatherForCity(cityName: String) {
        this.cityName = cityName
        val call = App.weatherService.getWeather("$cityName,fr")
        call.enqueue(object : Callback<WeatherWrapper> {
            override fun onResponse(
                call: Call<WeatherWrapper>?,
                response: Response<WeatherWrapper>?
            ) {

                response?.body()?.let {
                    val weather = mapOPenWeatherDataToWeather(it)
                    upDateUi(weather)
                    Log.i(TAG, "OpenweatherMap response: $weather")
                }
            }

            override fun onFailure(call: Call<WeatherWrapper>?, t: Throwable?) {
                Log.e(TAG, getString(R.string.msg_error_could_not_load_city), t)
                Toast.makeText(
                    activity, getString(R.string.msg_error_could_not_load_city), Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun upDateUi(weather: Weather) {
        weatherDescription.text = weather.description
        temprature.text = getString(R.string.weather_temperature_value, weather.temperature.toInt())
        humidity.text = getString(R.string.weather_humidity_value, weather.humidity)
        pressure.text = getString(R.string.weather_pressure_value, weather.pressure)
    }
}