package com.example.weather.helper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_data ORDER BY date DESC")
    fun getAllWeatherData(): LiveData<List<Weather>>

    @Insert
    fun insertWeatherData(weather: Weather)
}