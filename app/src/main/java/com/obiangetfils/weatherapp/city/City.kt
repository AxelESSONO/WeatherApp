package com.obiangetfils.weatherapp.city

data class City(var cityId : Long, var cityName : String) {

    constructor(cityName: String) : this(-1, cityName)

}