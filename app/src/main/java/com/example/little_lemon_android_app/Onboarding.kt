package com.example.little_lemon_android_app

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.little_lemon_android_app.ui.theme.Little_lemon_android_appTheme

@Composable
fun Onboarding(navController: NavController, userPrefs: SharedPreferences) {
    var firstName = rememberSaveable { mutableStateOf("") }
    var lastName = rememberSaveable { mutableStateOf("") }
    var email = rememberSaveable { mutableStateOf("") }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.height(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(250.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Let's get to know you",
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            text = "Personal information",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .padding(top = 40.dp, bottom = 40.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .fillMaxHeight(.50f)
                .padding(bottom = 40.dp)
        ) {
            TextField(
                value = firstName.value,
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { firstName.value = it })
            TextField(
                value = lastName.value,
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { lastName.value = it })
            TextField(
                value = email.value,
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = Email),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email.value = it })

        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
            modifier = Modifier.fillMaxWidth(0.90f),
            onClick = {
                context.validateRegistration(
                    firstName.value,
                    lastName.value,
                    email.value,
                    userPrefs, navController
                )
            }) {
            Text("Register")
        }

    }

}

fun Context.validateRegistration(
    firstName: String,
    lastName: String,
    email: String,
    userPrefs: SharedPreferences,
    navController: NavController
) {
    //simple validation for now TODO: Create form composable and validators
    if (firstName.isNullOrBlank() || lastName.isBlank() || email.isBlank()) {
        Toast.makeText(
            this,
            "Registration unsuccessful. Please enter all data.",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        userPrefs.edit().putString("First Name", firstName).apply()
        userPrefs.edit().putString("Last Name", lastName).apply()
        userPrefs.edit().putString("Email", email).apply()

        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
        navController.navigate(Home.route)
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun OnboardingPreview() {
//    Little_lemon_android_appTheme {
//        Onboarding()
//    }
//}