package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.weather.databinding.FragmentWeatherHistoryBinding

/**
 * A simple [Fragment] subclass.
 */
class WeatherHistoryFragment : Fragment() {

    data class HistoryWeather(val currentButtonText: String)

    private val historyWeather = HistoryWeather("Current Weather")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWeatherHistoryBinding>(inflater, R.layout.fragment_weather_history, container, false)
        binding.historyWeather = historyWeather

        binding.historyToCurrentButton.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_weatherHistoryFragment_to_currentWeatherFragment)
        }

        requireActivity().title = getString(R.string.weather_history_title)

        return binding.root
    }

}
