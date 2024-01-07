package com.example.weatherapp.presentation.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.App
import com.example.weatherapp.common.Constants.API_KEY
import com.example.weatherapp.common.dataStore
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.model.MainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    fun getData() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val city = App.instance.dataStore.data.map {
                it[stringPreferencesKey("city_pref")] ?: "London"
            }.first()
            try {
                val weather = getWeatherUseCase(API_KEY, city, 14, "ru")
                if (weather != null) {
                    _uiState.update {
                        it.copy(
                            daysList = weather.first,
                            hoursList = weather.second,
                            currentDay = weather.third,
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false, isError = true) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }
}