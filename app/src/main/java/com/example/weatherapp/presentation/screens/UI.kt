package com.example.weatherapp.presentation.screens

//import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.ui.theme.Pink41


//@Preview(showBackground = true)
@Composable
fun ListItem(item: WeatherModel) { //на основе его копируются все остальные, item это переменная пррост для обазначения
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp),
        backgroundColor = Pink41,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp) ///закругление карточки
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, //  space between чтобы
            // между всеми элементами было равное ратсонияе
            ///типо так
            ///ляля ..... ляля ....лялял
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 5.dp,
                    bottom = 5.dp
                    )
            ){
                Text(text = item.time, // времеЧко
                    color = Color.White
                )
                Text(
                    text = item.condition,
                    color = Color.White
                )

            }
            Text(
                text = item.currentTemp.ifEmpty {"${item.maxTemp}/${item.minTemp}"}, /// с помощью / показываем сразу два дня
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage(
                model = "https:${item.icon}", ///наша картиночка
                contentDescription = "im5",
                modifier = Modifier
                    .padding(
                        end = 8.dp
                    )
                    .size(35.dp)
            )

        }

    }


}