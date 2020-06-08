package com.example.weather.screens.weatherHistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.helper.Weather
import com.example.weather.helper.WeatherRepository
import kotlinx.coroutines.*

class WeatherHistoryViewModel(
    private val repository: WeatherRepository,
    application: Application
): AndroidViewModel(application) {

    val weatherData = repository.allWeatherData

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _eventToCurrentWeather = MutableLiveData<Boolean>()
    val eventToCurrentWeather: LiveData<Boolean>
        get() = _eventToCurrentWeather

    fun setTestData() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertWeatherData(
                    Weather(
                        System.currentTimeMillis(),
                        26.00,
                        "Sunny"
                    )
                )
            }
        }
    }

    fun clearTestData() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                repository.clearWeatherData()
            }
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