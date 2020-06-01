package com.example.weather.helper

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class Weather(

    @PrimaryKey
    val date: Long = System.currentTimeMillis(),

    val temperature: Double,

    val description: String
)