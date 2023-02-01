package com.example.little_lemon_android_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.little_lemon_android_app.ui.theme.Little_lemon_android_appTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Little_lemon_android_appTheme {

                val databaseMenuItems =
                    database.menuItemDao().getAll().observeAsState(listOf()).value

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var navController = rememberNavController()
                    Navigation(navController, sharedPreferences, databaseMenuItems)

                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                // add code here
                val menu = fetchMenu()
                saveMenuToDatabase(menu)
            }

        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val response: List<MenuItemNetwork> =
            client.get("https://android-capstone-713d5-default-rtdb.firebaseio.com/menu.json")
                .body()
        Log.d("Whats the Response", "$response")
        return response
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}


