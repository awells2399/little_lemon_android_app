package com.example.little_lemon_android_app

import android.content.SharedPreferences
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController, userPrefs: SharedPreferences) {

    var startDestination = if (userPrefs.contains("First Name")) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Home.route) {
            Home(navController, userPrefs)
        }
        composable(Profile.route) {
            Profile(navController = navController, userPrefs = userPrefs)
        }
        composable(Onboarding.route) {
            Onboarding(navController, userPrefs)
        }
    }
}