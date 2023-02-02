package com.example.little_lemon_android_app


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>) {
    val searchPhrase = remember {
        mutableStateOf("")
    }

    val category = remember {
        mutableStateOf("")
    }
    var itemsList = items


    if (searchPhrase.value.isNotEmpty()) {
        itemsList = items.filter { it.title.contains(searchPhrase.value, ignoreCase = true) }
    }

    if (category.value.isNotEmpty()) {
        itemsList = itemsList.filter { it.category == category.value }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item { Header(navController) }
        item { Hero(searchPhrase) }
        item { Categories(category, searchPhrase) }



        items(
            items = itemsList,
            itemContent = { item ->

                Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        DishDetails(
                            item.title,
                            item.description,
                            item.price,
                            Modifier.weight(0.7f)
                        )
                        ItemImage(item.image, item.title)
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ItemImage(imageUrl: String, title: String) {

    GlideImage(
        model = imageUrl,
        contentDescription = title,
        modifier = Modifier.fillMaxSize(0.3f),
    )

}

@Composable
private fun DishDetails(
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
            Text(
                text = "$$price",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

    }
}

@Composable
fun Header(navController: NavController) {
    Box(
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
                .align(Alignment.Center)

        )

        Image(
            painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterEnd)
                .padding(top = 5.dp, end = 10.dp)
                .clickable { navController.navigate("Profile") }


        )
    }

}

@Composable
fun Categories(cat: MutableState<String>, phrase: MutableState<String>) {

    var activeCategory by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxWidth(0.9f)
        .height(120.dp)
        .padding(bottom = 30.dp)
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
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (activeCategory !== Category.STARTER.cat) Color.LightGray else MaterialTheme.colors.onPrimary),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    if (activeCategory == Category.STARTER.cat) {
                        activeCategory = ""
                        cat.value = ""
                    } else {
                        activeCategory = Category.STARTER.cat
                        cat.value = Category.STARTER.cat
                        phrase.value = ""
                    }


                }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Starters"
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (activeCategory !== Category.MAINS.cat) Color.LightGray else MaterialTheme.colors.onPrimary),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    if (activeCategory == Category.MAINS.cat) {
                        activeCategory = ""
                        cat.value = ""
                    } else {
                        activeCategory = Category.MAINS.cat
                        cat.value = Category.MAINS.cat
                        phrase.value = ""
                    }
                }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Mains"
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (activeCategory !== Category.DESSERTS.cat) Color.LightGray else MaterialTheme.colors.onPrimary),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    if (activeCategory == Category.DESSERTS.cat) {
                        activeCategory = ""
                        cat.value = ""
                    } else {
                        activeCategory = Category.DESSERTS.cat
                        cat.value = Category.DESSERTS.cat
                        phrase.value = ""
                    }
                }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Desserts"
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (activeCategory !== Category.DRINKS.cat) Color.LightGray else MaterialTheme.colors.onPrimary),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    if (activeCategory == Category.DRINKS.cat) {
                        activeCategory = ""
                        cat.value = ""
                    } else {
                        activeCategory = Category.DRINKS.cat
                        cat.value = Category.DRINKS.cat
                        phrase.value = ""
                    }

                }) {
                Text(
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    text = "Drinks"
                )
            }
        }
    }


}