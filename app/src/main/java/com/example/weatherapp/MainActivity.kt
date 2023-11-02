package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.screens.MainCard
import com.example.weatherapp.screens.TabLayout
import com.example.weatherapp.ui.theme.WeatherAppTheme

const val API_KEY = "74da2efb93fc4c7788f222400232910"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme { /// я сунула сюда ее из ui, так как иначе картинка перекрывает все контейнеры
                //фон, который занимает весь экран

                getData("London", this)

                Image(
                    painter = painterResource(
                        id = R.drawable.weather_bg
                ),
                    contentDescription = "im1",
                    //чтобы картинка заняла весь экран
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.84f), ///alpha для прозрачности
                    ///чтобы не было белых полей
                    contentScale = ContentScale.Crop
                )
                Column {
                    MainCard()
                    TabLayout()
                }

                    ////

            }
        }
    }
}



///функция для передачи названия города
private fun getData(city: String, context: Context) {
    //создание ссылки
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city" +
            "&days=" +
            "5" +
            "&aqi=no&alerts=no"

    ////создание новой очереди очереди
    val queue = Volley.newRequestQueue(context)

    ////создание слушателя с помощью метода get
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response ->
            Log.d("MyLog", "Response: $response")
        },
        {
            Log.d("MyLog", "VolleyError: $it")
        }
    )
    ///добавление запроса в очередь
    queue.add(sRequest)
}
