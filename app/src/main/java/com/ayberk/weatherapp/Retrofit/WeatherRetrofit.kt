package com.ayberk.weatherapp.Retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.weatherapp.models.MainWeather
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class WeatherRetrofit @Inject constructor(private val retroService: Retrofit) {

    fun getWeather(liveData: MutableLiveData<MainWeather>){
        retroService.getWeather().enqueue(object : retrofit2.Callback<MainWeather>{
            override fun onResponse(call: Call<MainWeather>, response: Response<MainWeather>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MainWeather>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}