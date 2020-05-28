package com.example.weather.screens.currentWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentWeatherViewModel: ViewModel() {

    private val _eventToWeatherHistory = MutableLiveData<Boolean>()
    val eventToWeatherHistory: LiveData<Boolean>
        get() = _eventToWeatherHistory

    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String>
        get() = _temperature

    private val _weatherCondition = MutableLiveData<String>()
    val weatherCondition: LiveData<String>
        get() = _weatherCondition

    private val _location = MutableLiveData<String>()
    val location: LiveData<String>
        get() = _location

    init {
        _temperature.value = "35° C"
        _weatherCondition.value = "Sunny"
        _location.value = "Bangalore, India"
    }

    fun onToWeatherHistory() {
        _eventToWeatherHistory.value = true
    }

    fun onToWeatherHistoryComplete() {
        _eventToWeatherHistory.value = false
    }
}