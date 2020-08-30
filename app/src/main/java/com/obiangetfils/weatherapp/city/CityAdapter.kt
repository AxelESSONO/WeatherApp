package com.obiangetfils.weatherapp.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.obiangetfils.weatherapp.R

class CityAdapter(private val cities : List<City>,
                  private val cityListener : CityAdapter.CityItemListener)
    : RecyclerView.Adapter<CityAdapter.ViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewItem : View = LayoutInflater.from(parent?.context).inflate(R.layout.item_city, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        with(holder){
            cardView.tag = city
            cityNameView.text = city.cityName
            cardView.setOnClickListener(this@CityAdapter)
        }
    }

    override fun getItemCount(): Int = cities.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.card_view)
        val cityImageView : ImageView = itemView.findViewById(R.id.icon)
        val cityNameView : TextView = itemView.findViewById(R.id.name)
    }

    interface CityItemListener {
        fun onCitySelected(city : City)
        fun onCityDeleted(city: City)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.card_view -> cityListener.onCitySelected(view.tag as City)
        }
    }
}