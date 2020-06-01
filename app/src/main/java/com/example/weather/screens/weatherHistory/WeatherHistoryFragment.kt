package com.example.weather.screens.weatherHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherHistoryBinding

/**
 * A simple [Fragment] subclass.
 */
class WeatherHistoryFragment : Fragment() {

    private lateinit var binding: FragmentWeatherHistoryBinding

    private lateinit var viewModel: WeatherHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_weather_history, container, false)

        viewModel = ViewModelProvider(this).get(WeatherHistoryViewModel::class.java)

        binding.weatherHistoryViewModel = viewModel

        requireActivity().title = getString(R.string.weather_history_title)

        viewModel.eventToCurrentWeather.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(WeatherHistoryFragmentDirections.actionWeatherHistoryFragmentToCurrentWeatherFragment())
                viewModel.onToCurrentWeatherComplete()
            }
        })

        return binding.root
    }

}
