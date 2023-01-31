package com.example.little_lemon_android_app

import android.content.SharedPreferences
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController, userPrefs: SharedPreferences) {
    Text(text = "Home Screen")
    val b: String = userPrefs.getString("First Name", "No name").toString()
    Text(text = b, modifier = Modifier.padding(top = 40.dp))
    Button(
        modifier = Modifier.padding(top = 80.dp),
        onClick = { navController.navigate("Profile") }) {

    }
}