package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.API_KEY
import com.example.weatherapp.data.ResponseToUIMapper
import com.example.weatherapp.domain.models.dto.WeatherResponse
import com.example.weatherapp.presentation.model.WeatherModel
import com.example.weatherapp.presentation.model.MainUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(val application: Application) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    fun getData(city: String){
        _uiState.update { it.copy(isLoading = true) }
        //создание ссылки
        val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
                "&q=$city" +
                "&days=" +
                "14" +
                "&aqi=no&alerts=no"

        Log.d("MyLog", url)

        viewModelScope.launch {
            ////создание новой очереди очереди
            val queue = Volley.newRequestQueue(application)

            ////создание слушателя с помощью метода get
            val sRequest = StringRequest(
                Request.Method.GET,
                url,
                {
                        response ->
                    // Log.d("MyLog", "Response: $response")
                    val list = ResponseToUIMapper().map(Gson().fromJson(response, WeatherResponse::class.java))
                    _uiState.update { it.copy(daysList = list, currentDay = list[0], isLoading = false) }
                    Log.d("MyLog", response)
                },
                {
                    Log.d("MyLog", "VolleyError: $it")
                }
            )
            ///добавление запроса в очередь
            queue.add(sRequest)
        }
    }


    // использовать потом для прокидывания репозитория
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return MainViewModel(application) as T
            }
        }
    }
}