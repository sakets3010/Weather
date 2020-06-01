package com.example.weather.helper

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    val allWeatherData: LiveData<List<Weather>> = weatherDao.getAllWeatherData()

    suspend fun insertWeatherData(weather: Weather) {
        weatherDao.insertWeatherData(weather)
    }
}