# SparkWeather - Android Weather App

SparkWeather is a simple and intuitive weather application built for Android using Kotlin. It allows users to check the current weather in any city by fetching real-time data from the [OpenWeatherMap API](https://openweathermap.org/).

## 📱 Features

- 🔍 Search for current weather in any city
- 🌡 Displays temperature, humidity, pressure, cloudiness, and general weather condition
- 🌍 Shows country and city name
- 🕘 Maintains a basic history of recent weather searches (up to 3 entries)
- 🌤 Displays weather icons using OpenWeatherMap's image resources
- ⚡ Built with Retrofit, Kotlin Coroutines, and Picasso for efficient networking and image loading

## 🛠 Technologies Used

- Kotlin
- Retrofit2
- Picasso
- OpenWeatherMap API

## 📦 Project Structure
app/src/main  
├── java/com/example/sparkandroid  
│ │── MainActivity.kt # Main UI logic and weather fetching  
│ │── API.kt # Retrofit API interface  
│ └── Response.kt # Data models for parsing API response  
└── res/layout/ # UI layout files (e.g., activity_main.xml)

## 🔧 Setup Instructions

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/sparkweather.git

2. Open the project in Android Studio.

3. Replace the API key in `MainActivity.kt` with your own from OpenWeatherMap:
      ```bash
   val apiKey = "YOUR_API_KEY_HERE"
 
4. Make sure you have internet permissions in your `AndroidManifest.xml`:
      ```bash
   <uses-permission android:name="android.permission.INTERNET"/>

5. Build and run the app on an Android device or emulator.

## 🔍 How It Works

1. **User Input**  
   The user enters the name of a city into the input field and presses the **Search** button.

2. **API Request**  
   The app uses **Retrofit** to send an HTTP GET request to the OpenWeatherMap API:
      ```bash
   https://api.openweathermap.org/data/2.5/weather?q={city}&appid={API_KEY}&units=metric

4. **Data Parsing**  
The JSON response is automatically parsed into Kotlin data classes (`Response`, `Main`, `Weather`, `Clouds`, and `Sys`) using the **GsonConverterFactory**.

5. **UI Update**  
- Temperature, weather description, pressure, humidity, and cloudiness are extracted from the response.
- The data is displayed in appropriate `TextView` elements on the screen.
- The app uses the **Picasso** library to load and display the weather icon.

5. **Search History**  
- The last three successful searches (city name, temperature, and weather icon) are stored in memory.
- The UI updates to display this mini-history for quick reference.

6. **Error Handling**  
- If the user enters an invalid city name or there’s a network error, the app shows an error message.

![111](https://github.com/user-attachments/assets/fccc49f7-6f5d-4aeb-a880-66f6b4e074ec)
![222](https://github.com/user-attachments/assets/a200573c-a71d-4fcc-a92d-a2aab7bde07f)
