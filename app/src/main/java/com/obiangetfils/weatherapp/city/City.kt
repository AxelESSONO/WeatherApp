package com.obiangetfils.weatherapp.city

data class City(val cityId : Long, val cityName : String) {

    constructor(cityName: String) : this(-1, cityName)

}