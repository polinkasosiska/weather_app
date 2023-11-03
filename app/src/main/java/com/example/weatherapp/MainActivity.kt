package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.screens.MainCard
import com.example.weatherapp.screens.TabLayout
import com.example.weatherapp.ui.theme.WeatherAppTheme

import org.json.JSONObject

const val API_KEY = "74da2efb93fc4c7788f222400232910"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme { /// я сунула сюда ее из ui, так как иначе картинка перекрывает все контейнеры
                //фон, который занимает весь экран

                ///создание сотосния для переданного чпимчка
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }

                getData("London", this, daysList)

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
                    TabLayout(daysList)
                }

                    ////

            }
        }
    }
}



///функция для передачи названия города и днейеййй
private fun getData(city: String, context: Context,
                    daysList: MutableState<List<WeatherModel>>){
    //создание ссылки
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city" +
            "&days=" +
            "14" +
            "&aqi=no&alerts=no"

    ////создание новой очереди очереди
    val queue = Volley.newRequestQueue(context)

    ////создание слушателя с помощью метода get
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response ->
           // Log.d("MyLog", "Response: $response")
            val list = getWeatherByDays(response)
            daysList.value = list
        },
        {
            Log.d("MyLog", "VolleyError: $it")
        }
    )
    ///добавление запроса в очередь
    queue.add(sRequest)
}


///// получение списка
private fun getWeatherByDays(response: String): List<WeatherModel> {

    //проверка на пустоту
    if (response.isEmpty()) return listOf()

    //получение основгого джсон обьекта
    val mainObject = JSONObject(response)

    ///получение названия города
    val city = mainObject.getJSONObject("location").getString("name")

    ///получение спика по дням ( в json же массив по факту в массиве, поэтому указываем путь)
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

    //создание списка для возвращения нужной нам инфы и везер модел
    val list = ArrayList<WeatherModel>()

    // получение информации по дням
    for (i in 0 until days.length()) {
        //превращение в json формат массива
        // item выдает инфу по дням
        val item = days[i] as JSONObject

        //дбавляем в лист собранный везео модел
        list.add(
            WeatherModel(
                city,
                item.getString("date"),
                "", // пустота так как в дейс нам не нужен карент дей
                item.getJSONObject("day").getJSONObject("condition")
                    .getString("text"),
                item.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                item.getJSONObject("day").getString("maxtemp_c"),
                item.getJSONObject("day").getString("mintemp_c"),
                item.getJSONArray("hour").toString() //стринг, так как мы позже будем обрабатывать его
            )
        )
    }
    //презапись первого эалмента при перезапуске, чтоб не показывать погоду за вчера :)
    list[0] = list[0].copy(
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c"),
    )
    //возвращение листа
    return list
}