package com.ayberk.weatherapp.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.weatherapp.HomeFragmentDirections
import com.ayberk.weatherapp.R
import com.ayberk.weatherapp.databinding.WeatherItemBinding
import com.ayberk.weatherapp.models.Main
import com.ayberk.weatherapp.models.MainWeather

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyWeather>() {

    var liveData : List<MainWeather>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWeather {
        val binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyWeather(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyWeather, position: Int) {
        holder.bind(liveData!![position])
        holder.itemView.setOnClickListener {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(position)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }else{
            return liveData!!.size
        }
    }

    inner class MyWeather(private val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MainWeather) {

            binding.txtWeather.text = data.name
            binding.txtTemp.text = String.format("%.2f °C", data.main.temp - 273.15)

        }
    }
    fun setLists(liveData: List<MainWeather>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }
}