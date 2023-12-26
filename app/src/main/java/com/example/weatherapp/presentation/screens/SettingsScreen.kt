package com.example.weatherapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.common.dataStore
import com.jamal.composeprefs3.ui.PrefsScreen
import com.jamal.composeprefs3.ui.prefs.EditTextPref
import com.jamal.composeprefs3.ui.prefs.SwitchPref

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen() {
    PrefsScreen(dataStore = LocalContext.current.dataStore) {
        prefsItem { SwitchPref(key = "use_gps_pref", title = stringResource(R.string.use_current_location)) }

        prefsItem { EditTextPref(
            key = "city_pref",
            title = stringResource(R.string.city),
            summary = stringResource(R.string.specify_city),
            dialogTitle = stringResource(R.string.city),
            dialogMessage = stringResource(R.string.specify_city)
        ) }
    }
}