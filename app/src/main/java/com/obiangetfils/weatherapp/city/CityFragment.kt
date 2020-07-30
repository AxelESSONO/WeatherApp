package com.obiangetfils.weatherapp.city

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.obiangetfils.weatherapp.App
import com.obiangetfils.weatherapp.R
import com.obiangetfils.weatherapp.database.Database

class CityFragment : Fragment() {

    lateinit var database: Database
    lateinit var cities : MutableList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        cities = mutableListOf()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_city, container, false)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.city_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId) {
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCreateCityDialog() {
        val createCityDialogFragment = CreateCityDialogFragment()
        createCityDialogFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener{
            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() {}
        }
        fragmentManager?.let { createCityDialogFragment.show(it, "CreateCityDialogFrament") }
    }

    private fun saveCity(city: City) {

        if (database.createCity(city)){
            cities.add(city)
        } else{
            Toast.makeText(context, "Could not create city", Toast.LENGTH_SHORT).show()
        }
    }
}

