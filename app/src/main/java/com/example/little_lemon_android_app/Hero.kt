package com.example.little_lemon_android_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun Hero(searchPhrase: MutableState<String>) {
    val restaurantName = stringResource(id = R.string.restaurant_name)
    val restaurantDescription = stringResource(id = R.string.restaurant_description)
    val restaurantCity = stringResource(id = R.string.restaurant_city)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {

        Column(modifier = Modifier.fillMaxWidth(.9f)) {
            Text(
                text = restaurantName,
                color = MaterialTheme.colors.onPrimary,
                fontSize = MaterialTheme.typography.h1.fontSize
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = restaurantCity,
                        color = Color.White,
                        fontSize = MaterialTheme.typography.h2.fontSize,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = restaurantDescription,
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        color = Color.White,
                        modifier = Modifier

                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Food Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .width(140.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(top = 5.dp)
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(73.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                value = searchPhrase.value,
                placeholder = { Text(text = "Enter Search Phrase") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = { searchPhrase.value = it },
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