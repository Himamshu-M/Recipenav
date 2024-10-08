package com.example.myrecipeapp

import com.example.myrecipeapp.Screen.RecipeScreen.route

sealed class Screen(val route:String) {
    object RecipeScreen:Screen("recipescreen")
    object DetailScreen:Screen("detailscreen")
}