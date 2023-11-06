package com.deliter.cookingapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

//Меню для Drawer

data class MenuItem(
    val id: Int,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector,
)

object MenuItems {
    val menuList = listOf(
        MenuItem(
            id = 1,
            title = "Супы",
            contentDescription = "Супы",
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = 2,
            title = "Второе блюдо",
            contentDescription = "Второе блюдо",
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = 3,
            title = "Десерты",
            contentDescription = "Десерты",
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = 4,
            title = "Напитки",
            contentDescription = "Напитки",
            icon = Icons.Default.Favorite
        ),
        MenuItem(
            id = 5,
            title = "Избранное",
            contentDescription = "Избранное",
            icon = Icons.Default.Favorite
        )
    )
}
