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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherHistoryBinding
import com.example.weather.helper.WeatherDatabase
import com.example.weather.helper.WeatherListAdapter

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

        binding.lifecycleOwner = this

        binding.weatherList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val adapter = WeatherListAdapter()
        binding.weatherList.adapter = adapter

        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application).weatherDao
        val viewModelFactory = WeatherHistoryViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherHistoryViewModel::class.java)
        binding.weatherHistoryViewModel = viewModel

        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                binding.weatherList.smoothScrollToPosition(0)
            }
        })

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
