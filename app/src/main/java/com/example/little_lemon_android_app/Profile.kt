package com.example.little_lemon_android_app

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Profile(navController: NavController, userPrefs: SharedPreferences) {

    val firstName: String = userPrefs.getString("First Name", "").toString()
    val lastName: String = userPrefs.getString("Last Name", "").toString()
    val email: String = userPrefs.getString("Email", "").toString()



    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.height(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(250.dp)
                    .clickable { navController.navigate("Home") }

            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 10.dp)


        ) {
            TextField(
                value = firstName,
                label = { Text("First Name") },
                onValueChange = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            TextField(
                value = lastName,
                label = { Text("Last Name") },
                onValueChange = {/*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            TextField(
                value = email,
                label = { Text("Email") },
                onValueChange = {/*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )

//            if (changingProfile.value) Button(onClick = { /*TODO*/ }) {
//                Text("Save Changes")
//            } else ""
        }


        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 50.dp),
            onClick = { logout(userPrefs, navController) }) {
            Text("Logout")
        }
    }
}

private fun logout(userPrefs: SharedPreferences, navController: NavController) {
    userPrefs.edit().clear().apply()
    navController.navigate(Onboarding.route)

}

//private fun changeProfile(input: String, userPrefs: SharedPreferences, key: String) {
//    userPrefs.edit().putString(key, input)
//}

