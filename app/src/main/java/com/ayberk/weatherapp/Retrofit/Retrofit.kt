package com.ayberk.weatherapp.Retrofit

import com.ayberk.weatherapp.models.MainWeather
import retrofit2.http.GET

interface Retrofit {

    @GET("data/2.5/weather?q=manisa&APPID=a3d4799805c9498e05474a9547d15ae4")
    fun getWeather():retrofit2.Call<MainWeather>

}