package com.example.weather.screens.weatherHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherHistoryViewModel: ViewModel() {
    private val _eventToCurrentWeather = MutableLiveData<Boolean>()
    val eventToCurrentWeather: LiveData<Boolean>
        get() = _eventToCurrentWeather

    fun onToCurrentWeather() {
        _eventToCurrentWeather.value = true
    }

    fun onToCurrentWeatherComplete() {
        _eventToCurrentWeather.value = false
    }
}