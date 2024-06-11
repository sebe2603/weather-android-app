package com.example.sparkandroid

data class Response(
    val weather: List<Weather>,
    val main: Main,
    val clouds: Clouds,
    val sys: Sys,
    val name: String,
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    val humidity: Int,
    val pressure: Int,
)

data class Clouds(
    val all: Int,
)

data class Sys(
    val country: String,
)
