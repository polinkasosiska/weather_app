package com.example.weatherapp.presentation.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.App
import com.example.weatherapp.common.Constants.API_KEY
import com.example.weatherapp.common.dataStore
import com.example.weatherapp.data.mapper.WeatherResponseDTOMapper
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.ValidateCityUseCase
import com.example.weatherapp.presentation.model.MainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private  val useCase: ValidateCityUseCase) : ViewModel() {

    val cityFlow = App.instance.dataStore.data.map {
        it[stringPreferencesKey("city_pref")] ?: ""
    }

    fun validateCity(city: String) = useCase(city)
}