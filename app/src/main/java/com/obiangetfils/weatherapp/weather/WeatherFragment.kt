package com.obiangetfils.weatherapp.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.obiangetfils.weatherapp.R

class WeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        return view
    }

    companion object {
        val EXTRA_CITY_NAME = "com.obiangetfils.weatherapp.weather.EXTRA_CITY_NAME"
        fun newInstance() = WeatherFragment()
    }
}