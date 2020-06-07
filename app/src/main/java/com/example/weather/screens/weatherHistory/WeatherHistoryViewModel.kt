package com.example.weather.screens.weatherHistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.helper.Weather
import com.example.weather.helper.WeatherDao
import kotlinx.coroutines.*

class WeatherHistoryViewModel(
    private val database: WeatherDao,
    application: Application
): AndroidViewModel(application) {

    val weatherData = database.getAllWeatherData()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _eventToCurrentWeather = MutableLiveData<Boolean>()
    val eventToCurrentWeather: LiveData<Boolean>
        get() = _eventToCurrentWeather

    fun setTestData() {
        uiScope.launch {
            add()
        }
    }

    private suspend fun add() {
        withContext(Dispatchers.IO) {
            database.insertWeatherData(
                Weather(
                    System.currentTimeMillis(),
                    26.00,
                    "Sunny"
                )
            )
        }
    }

    fun clearTestData() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clearData()
        }
    }

    fun onToCurrentWeather() {
        _eventToCurrentWeather.value = true
    }

    fun onToCurrentWeatherComplete() {
        _eventToCurrentWeather.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}