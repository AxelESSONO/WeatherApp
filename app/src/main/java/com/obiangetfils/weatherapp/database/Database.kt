package com.obiangetfils.weatherapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.obiangetfils.weatherapp.city.City

private const val DATABASE_NAME = "weather.db"
private const val DATABASE_VERSION = 1

private const val CITY_TABLE_NAME = "city"
private const val CITY_KEY_ID = "id"
private const val CITY_KEY_NAME = "name"

// This Constant will be used to create Sqlite database
private const val CITY_TABLE_CREATE = """
     CREATE TABLE $CITY_TABLE_NAME (
     $CITY_KEY_ID INTEGER PRIMARY KEY,
     $CITY_KEY_NAME TEXT
)
"""

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CITY_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun createCity(city: City): Boolean {
        val values = ContentValues()
        values.put(CITY_KEY_ID, CITY_KEY_NAME)
        val insert = writableDatabase.insert(CITY_TABLE_NAME, null, values)
        city.cityId = insert

        return insert > 0
    }

}