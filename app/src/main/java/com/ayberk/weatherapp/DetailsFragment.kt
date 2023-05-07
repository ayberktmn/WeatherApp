package com.ayberk.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ayberk.weatherapp.ViewModel.WeatherViewModel
import com.ayberk.weatherapp.databinding.FragmentDetailsBinding
import com.ayberk.weatherapp.databinding.FragmentHomeBinding
import com.ayberk.weatherapp.models.MainWeather
import com.ayberk.weatherapp.models.Weather
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var resultList: com.ayberk.weatherapp.models.Weather
    private val viewModel : WeatherViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        val view = binding.root
        fetchWeather()
        arguments?.let {
            viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<MainWeather> {
                override fun onChanged(t: MainWeather?) {
                    if(t != null){
                        val position = DetailsFragmentArgs.fromBundle(it).positionDetail
                        resultList = t.weather.get(position)
                        binding.txtCity.text= t.name
                        binding.txtTemp.text = String.format("%.2f °C", t.main.temp - 273.15)
                        binding.txtFeels.text = String.format("%.2f °C", t.main.feels_like - 273.15)
                        binding.txtDescrition.text = resultList.description

                        if("15" > String.format("%.2f °C", t.main.temp - 273.15)){
                            binding.imageView.visibility = View.VISIBLE
                            binding.imageView2.visibility = View.INVISIBLE
                        }
                        else{
                            binding.imageView.visibility = View.VISIBLE
                            binding.imageView2.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
        return view
    }
    fun fetchWeather(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.loadWeather()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}