package com.ayberk.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.weatherapp.Adapter.WeatherAdapter
import com.ayberk.weatherapp.ViewModel.WeatherViewModel
import com.ayberk.weatherapp.databinding.FragmentHomeBinding
import com.ayberk.weatherapp.models.MainWeather
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: WeatherAdapter
    private val viewModel : WeatherViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.rcylerAgends.layoutManager = LinearLayoutManager(requireContext())
        InitRecycler()
        fetchWeather()


        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<MainWeather> {
            override fun onChanged(t: MainWeather?) {
                if(t != null){
                    adapter.setLists(listOf(t))
                    println(t)
                }
            }
        })

        return view
    }

    fun InitRecycler(){

        adapter = WeatherAdapter()
        binding.rcylerAgends.adapter = adapter

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