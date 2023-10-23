package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.screens.MainCard
import com.example.weatherapp.screens.TabLayout
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme { /// я сунула сюда ее из ui, так как иначе картинка перекрывает все контейнеры
                //фон, который занимает весь экран
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

