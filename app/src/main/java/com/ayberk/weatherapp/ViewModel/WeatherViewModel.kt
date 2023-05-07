package com.ayberk.weatherapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.weatherapp.Retrofit.WeatherRetrofit
import com.ayberk.weatherapp.models.MainWeather

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel@Inject constructor(private val repo: WeatherRetrofit) : ViewModel() {

    var WeatherList: MutableLiveData<MainWeather>


    init {
        WeatherList = MutableLiveData()
    }

    fun getObserverLiveData(): MutableLiveData<MainWeather>{
        return WeatherList
    }
    fun  loadWeather(){
        repo.getWeather(WeatherList)
    }
}