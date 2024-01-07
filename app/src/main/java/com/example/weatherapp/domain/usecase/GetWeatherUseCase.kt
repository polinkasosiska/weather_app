package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.mapper.HoursWeatherResponseDTOMapper
import com.example.weatherapp.data.mapper.WeatherResponseDTOMapper
import com.example.weatherapp.domain.models.HourWeatherModel
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    private val dateFormatIn = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val dateFormatOut = SimpleDateFormat("dd MMMM", Locale.getDefault())
    private val timeWithoutMinFormat = SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault())
    private val timeFormatIn = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    private val timeFormatOut = SimpleDateFormat("HH:mm", Locale.getDefault())

    suspend operator fun invoke(
        key: String,
        query: String,
        days: Int,
        lang: String
    ): Triple<List<WeatherModel>, List<HourWeatherModel>, WeatherModel>? {
        val response = repository.getWeather(key, query, days, lang)
        if (response.isSuccessful && response.body() != null) {
            var list = WeatherResponseDTOMapper().map(response.body()!!)
            val currentDay = list[0]
            list = list.filter {
                val currentDateWithoutHoursStr = dateFormatIn.format(Date())
                val currentDateWithoutHours = dateFormatIn.parse(currentDateWithoutHoursStr)
                val itemDate = dateFormatIn.parse(it.date)
                itemDate?.equals(currentDateWithoutHours) == false
            }
            list = list.map {
                val itemDate = dateFormatIn.parse(it.date)
                it.date = dateFormatOut.format(itemDate ?: Date())
                it
            }

            var hoursList = HoursWeatherResponseDTOMapper().map(response.body()!!)
            hoursList = hoursList.filter {
                val itemTime = timeWithoutMinFormat.parse(it.time)
                val currentHourStr = timeWithoutMinFormat.format(Date())
                val currentHour = timeWithoutMinFormat.parse(currentHourStr)
                itemTime?.after(Date()) == true || itemTime?.equals(currentHour) == true
            }
            hoursList = hoursList.map {
                val itemTime = timeFormatIn.parse(it.time)
                it.time = timeFormatOut.format(itemTime ?: Date())
                it
            }
            return Triple(list, hoursList, currentDay)
        } else return null
    }
}