package com.example.sparkandroid

import retrofit2.http.Query
import retrofit2.http.GET

interface API {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response

}