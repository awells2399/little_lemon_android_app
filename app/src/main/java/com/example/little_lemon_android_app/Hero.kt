package com.example.little_lemon_android_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun Hero() {
    val restaurantName = stringResource(id = R.string.restaurant_name)
    val restaurantDescription = stringResource(id = R.string.restaurant_description)
    val restaurantCity = stringResource(id = R.string.restaurant_city)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {
        Column() {
            Text(text = "$restaurantName", color = MaterialTheme.colors.onPrimary)
            Text(text = "$restaurantCity", color = Color.White)
            Row() {
                Text(
                    text = "$restaurantDescription",
                    color = Color.White,
                    modifier = Modifier
                        .weight(0.5f)
                        .align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Food Picture",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(150.dp)
                        .width(60.dp)
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                value = "Enter Search Phrase",
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null
                    )
                },
            )
        }
    }
}