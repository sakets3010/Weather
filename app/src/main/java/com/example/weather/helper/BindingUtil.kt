package com.example.weather.helper

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weather.R
import java.text.SimpleDateFormat

@BindingAdapter("weatherDate")
fun TextView.setWeatherDate(weather: Weather) {
    text = convertLongToDateString(weather.date)
}

@BindingAdapter("weatherDesc")
fun TextView.setWeatherDesc(weather: Weather) {
    text = weather.description
}

@BindingAdapter("weatherTemperature")
fun TextView.setWeatherTemperature(weather: Weather) {
    text = context.resources.getString(R.string.temperature, weather.temperature.toInt())
}

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE") //EEEE MMM-dd-yyyy' Time: 'HH:mm"
        .format(systemTime).toString()
}