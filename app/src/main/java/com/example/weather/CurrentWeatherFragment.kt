package com.example.weather

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.weather.databinding.FragmentCurrentWeatherBinding

/**
 * A simple [Fragment] subclass.
 */
class CurrentWeatherFragment : Fragment() {

    data class CurrentWeather(val historyButtonText: String)

    private val currentWeather = CurrentWeather("History")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCurrentWeatherBinding>(inflater, R.layout.fragment_current_weather, container, false)
        binding.currentWeather = currentWeather

        binding.currentToHistoryButton.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_currentWeatherFragment_to_weatherHistoryFragment)
        }

        requireActivity().title = getString(R.string.current_weather_title)

        return binding.root
    }

}
