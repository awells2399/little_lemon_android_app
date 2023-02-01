package com.example.little_lemon_android_app

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    var id: Int,

    @SerialName("title")
    var title: String,

    @SerialName("description")
    var description: String,

    @SerialName("price")
    var price: Double,

    @SerialName("image")
    var image: String,

    @SerialName("category")
    var category: String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id, title, price, description, image, category
    )
}