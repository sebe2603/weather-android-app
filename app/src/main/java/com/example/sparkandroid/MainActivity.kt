package com.example.sparkandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlin.math.roundToInt

object Client {
    private const val url = "https://api.openweathermap.org/data/2.5/"

    val instance: API by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(API::class.java)
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var tempResponseText: TextView
    private lateinit var infoResponseText: TextView
    private lateinit var descriptionResponseText: TextView
    private lateinit var cityText: TextView
    private lateinit var cityTextH1: TextView
    private lateinit var cityTextH2: TextView
    private lateinit var cityTextH3: TextView
    private lateinit var historyError: TextView
    private lateinit var searchButton: Button
    private lateinit var cityInput: EditText
    private val tempHistory = mutableListOf<String>()
    private val cityHistory = mutableListOf<String>()
    private val iconHistory = mutableListOf<String>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempResponseText = findViewById(R.id.tempResponse)
        infoResponseText = findViewById(R.id.info)
        descriptionResponseText = findViewById(R.id.description)
        cityText = findViewById(R.id.cityName)
        cityTextH1 = findViewById(R.id.cityNameH1)
        cityTextH2 = findViewById(R.id.cityNameH2)
        cityTextH3 = findViewById(R.id.cityNameH3)
        historyError = findViewById(R.id.historyError)
        searchButton = findViewById(R.id.Button)
        cityInput = findViewById(R.id.Box)
        historyError.text = "Your history will be displayed here"
        searchButton.setOnClickListener {
            val city = cityInput.text.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                if (city.isNotEmpty()) {
                    fetchWeather(city)
                } else {
                    tempResponseText.text = "Please enter a city"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun fetchWeather(city: String) {
        val apiKey = "f9de28d61ccf88791cc6b13e8d694b70"

        try {
            val responseAPI = Client.instance.getWeather(city, apiKey, "metric")
            val temp = responseAPI.main.temp
            val tempInt = temp.roundToInt()
            val temperatureData = "$tempInt°C"
            val pressure = responseAPI.main.pressure
            val humidity = responseAPI.main.humidity
            val clouds = responseAPI.clouds.all
            val country = responseAPI.sys.country
            val cityName = responseAPI.name
            val icon = responseAPI.weather[0].icon
            cityHistory.add(cityName)
            tempHistory.add(temperatureData)
            iconHistory.add(icon)
            val iconUrl = "https://openweathermap.org/img/wn/$icon@2x.png"
            lifecycleScope.launch(Dispatchers.Main) {
                Picasso.get().load(iconUrl).into(findViewById<ImageView>(R.id.weatherIcon))
            }
            val weatherDescription = responseAPI.weather[0].main
            val info = "Humidity: $humidity% \t Pressure: $pressure hPa \t Clouds: $clouds%"
            cityText.text = "$cityName, $country"
            descriptionResponseText.text = weatherDescription
            tempResponseText.text = temperatureData
            infoResponseText.text = info
        } catch (e: Exception) {
            cityText.text = "Error"
        }
        historyError.text = ""
        try {
            val iconUrlH1 =
                "https://openweathermap.org/img/wn/${iconHistory[iconHistory.size - 1]}@2x.png"
            lifecycleScope.launch(Dispatchers.Main) {
                Picasso.get().load(iconUrlH1).into(findViewById<ImageView>(R.id.weatherIconH1))
            }
            cityTextH1.text =
                "${cityHistory[cityHistory.size - 1]}, ${tempHistory[tempHistory.size - 1]}"
        } catch (_: Exception) {
        }
        try {
            val iconUrlH2 =
                "https://openweathermap.org/img/wn/${iconHistory[iconHistory.size - 2]}@2x.png"
            lifecycleScope.launch(Dispatchers.Main) {
                Picasso.get().load(iconUrlH2).into(findViewById<ImageView>(R.id.weatherIconH2))
            }
            cityTextH2.text =
                "${cityHistory[cityHistory.size - 2]}, ${tempHistory[tempHistory.size - 2]}"
        } catch (_: Exception) {
        }
        try {
            val iconUrlH3 =
                "https://openweathermap.org/img/wn/${iconHistory[iconHistory.size - 3]}@2x.png"
            lifecycleScope.launch(Dispatchers.Main) {
                Picasso.get().load(iconUrlH3).into(findViewById<ImageView>(R.id.weatherIconH3))
            }
            cityTextH3.text =
                "${cityHistory[cityHistory.size - 3]}, ${tempHistory[tempHistory.size - 3]}"
        } catch (_: Exception) {
        }
    }
}


