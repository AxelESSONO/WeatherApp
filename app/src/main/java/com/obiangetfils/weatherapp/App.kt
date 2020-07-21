package com.obiangetfils.weatherapp

import android.app.Application
import com.obiangetfils.weatherapp.database.Database

class App : Application() {

    companion object {
        lateinit var instance: App
        val database: Database by lazy {
            Database(instance)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}