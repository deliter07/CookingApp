package com.deliter.cookingapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class TypeOfDish {
    Soup,
    SecondDish,
    Desert,
    Drink
}

data class Dish(
    val dishId: Int,
    @StringRes val dishName: Int,
    @DrawableRes val dishImage: Int,
    val dishRecipe: String,
    val typeOfDish: TypeOfDish,
)
