package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.presentation.components.LoadingIndicator
import com.example.weatherapp.presentation.screens.MainCard
import com.example.weatherapp.presentation.screens.TabLayout
import com.example.weatherapp.presentation.viewmodel.MainViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme { /// я сунула сюда ее из ui, так как иначе картинка перекрывает все контейнеры
                //фон, который занимает весь экран
                val viewModel = hiltViewModel<MainViewModel>()
                val uiState by viewModel.uiState.collectAsState()
                viewModel.getData("London")

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
                    MainCard(uiState.currentDay)
                    TabLayout(uiState.daysList, uiState.currentDay)
                }

                    ////

                if (uiState.isLoading) LoadingIndicator()

            }
        }
    }
}