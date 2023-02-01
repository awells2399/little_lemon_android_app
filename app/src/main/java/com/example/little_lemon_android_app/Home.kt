package com.example.little_lemon_android_app


import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController, userPrefs: SharedPreferences, items: List<MenuItemRoom>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        Hero()
        Categories()
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = items,
                itemContent = { item ->

                    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            RestaurantDetails(
                                item.title,
                                item.description,
                                item.price,
                                Modifier.weight(0.7f)
                            )
                            ItemImage(item.image, Modifier.weight(0.15f))
                        }
                    }
                }
            )
        }
    }


}

@Composable
private fun ItemImage(icon: String, modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "placeHolder",
        modifier = modifier
            .padding(8.dp)

    )
}

@Composable
private fun RestaurantDetails(
    title: String,
    description: String,
    price: Double,
    modifier: Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primaryVariant
        )
        CompositionLocalProvider(
            LocalContentAlpha provides
                    ContentAlpha.medium
        ) {
            Text(text = description, style = MaterialTheme.typography.body1)
            Text(text = "$price", style = MaterialTheme.typography.body2)
        }

    }
}

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
                .padding(top = 5.dp)
                .align(Alignment.CenterVertically)
        )
    }

}

@Composable
fun Categories() {
    Column(modifier = Modifier
        .fillMaxWidth(0.9f)
        .padding()
        .drawBehind {
            val borderSize = 2.dp.toPx()
            drawLine(
                color = Color.LightGray,
                start = Offset(0f, size.height + 70f),
                end = Offset(size.width, size.height + 70f),
                strokeWidth = borderSize
            )
        }) {

        Text(text = "ORDER FOR DELIVERY!", modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Starters"
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Mains"
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Desserts"
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Drinks"
                )
            }
        }
    }


}