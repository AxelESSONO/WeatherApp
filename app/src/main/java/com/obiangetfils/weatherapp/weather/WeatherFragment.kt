package com.obiangetfils.weatherapp.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.obiangetfils.weatherapp.App
import com.obiangetfils.weatherapp.R
import com.obiangetfils.weatherapp.openweathermap.WeatherWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {

    lateinit var cityName: String
    private val TAG = WeatherFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
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
            override fun onResponse(call: Call<WeatherWrapper>?, response: Response<WeatherWrapper>?) {
                Log.d(TAG, "OpenweatherMap response: ${response?.body()}")
            }

            override fun onFailure(call: Call<WeatherWrapper>?, t: Throwable?) {
                Log.d(TAG, getString(R.string.msg_error_could_not_load_city), t)
                Toast.makeText(activity, getString(R.string.msg_error_could_not_load_city), Toast.LENGTH_SHORT).show()
            }
        })
    }
}