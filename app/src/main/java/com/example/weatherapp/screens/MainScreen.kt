package com.example.weatherapp.screens

//import androidx.compose.material3.Card
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Pink41

@Preview (showBackground = true)  //позволяет нам видеть экран до сборки проекта
@Composable // элеиент экрана который отрисуется на экране

fun MainScreen() {
    //фон, который занимает весь экран
    Image(painter = painterResource(id = R.drawable.weather_bg),
        contentDescription = "im1",
    //чтобы картинка заняла весь экран
    modifier = Modifier
        .fillMaxSize()
        .alpha(0.84f), ///alpha для прозрачности
        ///чтобы не было белых полей
        contentScale = ContentScale.Crop
    )

    Column(Modifier.fillMaxSize()
        .padding(5.dp)

    ) {


        ////чтобы карточка занимала всю ширину, а так же настройка цвета карточки
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Pink41,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)

            /* как было
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Pink41,
😍             elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp),
            elevation = 0.dp,
            snape = RoundedCornerShape(10.dp)
            */
        )

        {
            Column( //основной контейнер
                modifier = Modifier.fillMaxWidth(), // ширина по всему экрану
                horizontalAlignment = Alignment.CenterHorizontally // все элементы будут по середине
            ) {
                Column( /// центральный контейнер
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                        // чтобы эдементы были вначале одного края и вначале второго
                        //// тип так
                        //// ляля ................ еще ляля
                    )
                    {
                        Text( /// текст для примера , который слева// :)
                            modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                            text = "20 Jun 2022 13:00",
                            style = TextStyle(fontSize = 15.sp),
                            color = Color.White
                        )

                        AsyncImage( //чтобы была картинка справа
                            model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                            contentDescription = "im2",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(top = 3.dp, end = 8.dp)
                        )
                    }
                }
                Text( /// текст для примера , который слева// :)
                    text = "London",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.White
                )
                Text(
                    text = "23ºC",
                    style = TextStyle(fontSize = 65.sp),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )

                /// нижняя навигация
                Row( ///чтобы они были по серединке и на равном растоянии друг от другап
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {

                        IconButton(
                            onClick = {

                            }
                        ) {
                                Icon( ///картинка значка поиска
                                    painter = painterResource(id = R.drawable.ic_search),
                                    contentDescription = "im3",
                                    tint = Color.White
                                )
                            }
                                Text(
                                    text = "23ºC/12ºC",
                                    style = TextStyle(fontSize = 16.sp),
                                    color = Color.White
                                )
                                    IconButton(
                                        onClick = {

                                        }
                                    ) {
                                        Icon( ///картинка синхронгизации
                                            painter = painterResource(id = R.drawable.ic_sync),
                                            contentDescription = "im4",
                                            tint = Color.White
                                        )
                    }
                }
            }
        }

    }
}