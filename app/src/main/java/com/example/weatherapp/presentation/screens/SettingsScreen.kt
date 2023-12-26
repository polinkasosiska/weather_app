package com.example.weatherapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.common.dataStore
import com.jamal.composeprefs3.ui.PrefsScreen
import com.jamal.composeprefs3.ui.prefs.EditTextPref
import com.jamal.composeprefs3.ui.prefs.SwitchPref
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val cityFlow = App.instance.dataStore.data.map {
    it[stringPreferencesKey("city_pref")] ?: ""
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen() {
    val city = cityFlow.collectAsState(initial = "London").value

    PrefsScreen(dataStore = App.instance.dataStore) {
        prefsItem { SwitchPref(key = "use_gps_pref", title = stringResource(R.string.use_current_location)) }


        prefsItem { EditTextPref(
            key = "city_pref",
            title = city.ifEmpty { stringResource(R.string.city) },
            summary = stringResource(R.string.specify_city),
            dialogTitle = stringResource(R.string.city),
            dialogMessage = stringResource(R.string.specify_city),
        ) }
    }
}