package com.example.weatherapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.common.dataStore
import com.example.weatherapp.domain.usecase.ValidateCityUseCase
import com.example.weatherapp.presentation.components.EditTextPref
import com.example.weatherapp.presentation.viewmodel.SettingsViewModel
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import com.jamal.composeprefs3.ui.PrefsScreen
import com.jamal.composeprefs3.ui.prefs.SwitchPref
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val city = viewModel.cityFlow.collectAsState(initial = "London").value

    PrefsScreen(dataStore = App.instance.dataStore) {
        prefsItem { SwitchPref(key = "use_gps_pref", title = stringResource(R.string.use_current_location)) }

        prefsItem {
            var isError by remember { mutableStateOf(false) }
            EditTextPref(
            key = "city_pref",
            title = city.ifEmpty { stringResource(R.string.city) },
            summary = stringResource(R.string.specify_city),
            dialogTitle = stringResource(R.string.city),
            dialogMessage = stringResource(R.string.specify_city),
            onValueChange = {
                isError = !viewModel.validateCity(it)
            },
            isError = isError
        ) }
    }
}