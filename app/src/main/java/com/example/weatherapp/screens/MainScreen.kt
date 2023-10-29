package com.example.weatherapp.screens

//import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.ui.theme.Pink41
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview (showBackground = true)  //позволяет нам видеть экран до сборки проекта
@Composable // элеиент экрана который отрисуется на экране

fun MainCard()
{


    Column(
        modifier = Modifier
            ///.fillMaxSize()
            .padding(5.dp)

    ) {


        ////чтобы карточка занимала всю ширину, а так же настройка цвета карточки
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Pink41,
            elevation = 0.dp,
          ///  shape = RoundedCornerShape(10.dp)

            /* как было
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Pink41,
            elevation = CardDefaults.cardElevation(
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                        // чтобы эдементы были вначале одного края и вначале второго
                        //// тип так
                        //// ляля ................ еще ляля
                    )
                    {
                        Text( /// текст для примера , который слева// :)
                            text = "20 Jun 2022 13:00",
                            modifier = Modifier.padding(
                                top = 8.dp,
                                start = 8.dp
                            ),
                            style = TextStyle(fontSize = 15.sp),
                            color = Color.White )

                        AsyncImage( //чтобы была картинка справа
                            model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                            contentDescription = "im2",
                            modifier = Modifier
                                .padding(
                                    top = 8.dp,
                                    end = 8.dp
                                )
                                .size(35.dp)
                        )
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


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(){
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState() //состояние
    val tabIndex = pagerState.currentPage //состояние для пенреключения страницы, грубо говоря индекс, которой ща октрыт
    val coroutineScope = rememberCoroutineScope() //состояние

    Column( ///конйтнер для выбора дней или часов потом
    modifier = Modifier
        .padding(start = 5.dp, end = 5.dp)
        .clip(RoundedCornerShape(5.dp))

    ) {
        TabRow(selectedTabIndex = tabIndex,
            indicator = {pos ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, pos)
                        )
            },
            backgroundColor = Pink41,
            contentColor = Color.White
            ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false, ///не один из жлементов сейчас не выбран, поэтому фолс
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text ={
                        Text(text = text)
                    }
                )

            }
        }
        HorizontalPager(
            count = tabList.size, //чтобы знать сколько страниц
            state = pagerState, //сохранение состояния
            modifier = Modifier.weight(1.0f)

        ) {
            index -> ///какая страница сейчас открытиа передаем
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                itemsIndexed(
                    listOf(
                        WeatherModel(
                            "London", ///для часов
                            "10:00",
                            "25ºC",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/176.png",
                            "",
                            "",
                            ""
                        ),
                        WeatherModel( ///для дней
                            "London",
                            "26/07/2022",
                            "",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/176.png",
                            "26º",
                            "12º",
                            "xdfghxdfthxfghxdft"
                        )
                    )
                ) { _, item ->
                    ListItem(item)
                }
            }
        }
    }
}