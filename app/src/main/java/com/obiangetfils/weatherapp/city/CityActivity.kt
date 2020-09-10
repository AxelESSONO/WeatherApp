package com.obiangetfils.weatherapp.city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.obiangetfils.weatherapp.R
import com.obiangetfils.weatherapp.weather.WeatherActivity
import com.obiangetfils.weatherapp.weather.WeatherFragment

class CityActivity : AppCompatActivity(), CityFragment.CityFragmentListener {

    private lateinit var cityFragment: CityFragment
    private var currentCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        cityFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as CityFragment
        cityFragment.listener = this

    }

    override fun onCitySelected(city: City) {
        currentCity = city
        startWeatherCity(city)
    }

    private fun startWeatherCity(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME, city.cityName)
        startActivity(intent)
    }
}