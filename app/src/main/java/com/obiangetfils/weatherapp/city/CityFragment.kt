package com.obiangetfils.weatherapp.city

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obiangetfils.weatherapp.App
import com.obiangetfils.weatherapp.R
import com.obiangetfils.weatherapp.database.Database

class CityFragment : Fragment(), CityAdapter.CityItemListener {

    interface CityFragmentListener{
        fun onCitySelected(city: City)
        fun onEmptyCities()
    }

    var listener: CityFragmentListener? = null
    private lateinit var database: Database
    private lateinit var cities: MutableList<City>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        cities = mutableListOf<City>()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View? = inflater.inflate(R.layout.fragment_city, container, false)

        recyclerView = view?.findViewById(R.id.cities_recycler_view)!!
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cities = database.getAllCities()
        adapter = CityAdapter(cities, this)
        recyclerView.adapter = adapter

      /*  val count = cities.size - 1
        for (i in 0..count){
            Toast.makeText(context, cities[i].cityName, Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.city_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveCity(city: City) {

        if (database.createCity(city)) {
            cities.add(city)
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(context, "Could not create city", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCitySelected(city: City) {
        listener?.onCitySelected(city)
    }

    override fun onCityDeleted(city: City) {
        showCityDeleted(city)
    }

    private fun showCreateCityDialog() {
        val createCityDialogFragment = CreateCityDialogFragment()
        createCityDialogFragment.listener = object : CreateCityDialogFragment.CreateCityDialogListener {
            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() {}
        }
        fragmentManager?.let { createCityDialogFragment.show(it, "CreateCityDialogFrament") }
    }

    private fun showCityDeleted(city: City) {
        val deleteCityDialogFragment = DeleteCityDialogFragment.newInstance(city.cityName)
        deleteCityDialogFragment.listener = object : DeleteCityDialogFragment.DeleteCityDialogListener {
            override fun onDialogPositiveClick() {
                deleteCity(city)
            }

            override fun onDialogNegativeClick() {
            }
        }
        deleteCityDialogFragment.show(fragmentManager!!, "DeleteCityDialogFragment")
    }

    private fun deleteCity(city: City) {
        if (database.deleteCity(city)){
            cities.remove(city)
            adapter.notifyDataSetChanged()
            selectFirstCity()
            Toast.makeText(context, "City ${city.cityName} has been deleted", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(context, "Could not delete the city: ${city.cityName}", Toast.LENGTH_SHORT).show()
        }
    }

    fun selectFirstCity() {
        when(cities.isEmpty()) {
            true -> listener?.onEmptyCities()
            false -> onCitySelected(cities.first())
        }
    }

}