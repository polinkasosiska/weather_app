package com.example.weatherapp.domain.usecase

import java.util.regex.Pattern
import javax.inject.Inject

private val simpleCityPattern: Pattern = Pattern.compile("\\p{L}+")
private val dashCityPattern1: Pattern = Pattern.compile(".*-.*\\p{L}+") // например Санкт-Петербург
private val dashCityPattern2: Pattern = Pattern.compile(".*-.*-.*\\p{L}+") // например Ростов-на-Дону

class ValidateCityUseCase @Inject constructor() {
    operator fun invoke(city: String): Boolean =
        simpleCityPattern.matches(city) || dashCityPattern1.matches(city) || dashCityPattern2.matches(city)
}

private fun Pattern.matches(str: String) = this.matcher(str).matches()