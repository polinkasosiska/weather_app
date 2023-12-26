package com.example.weatherapp.domain.usecase

import java.util.regex.Pattern

private val pattern: Pattern = Pattern.compile("\\p{L}+")

class ValidateCityUseCase {
    operator fun invoke(city: String): Boolean = pattern.matcher(city).matches()
}