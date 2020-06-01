package com.example.weather.screens.currentWeather

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
import com.example.weather.databinding.FragmentCurrentWeatherBinding

/**
 * A simple [Fragment] subclass.
 */
class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_weather,
            container,
            false)

        requireActivity().title = getString(R.string.current_weather_title)

        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        binding.currentWeatherViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventToWeatherHistory.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToWeatherHistoryFragment())
                viewModel.onToWeatherHistoryComplete()
            }
        })

        return binding.root
    }

}

