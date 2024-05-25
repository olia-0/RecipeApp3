package com.example.recipeapp3.navigation

import com.example.recipeapp3.R

enum class AppRoute(val route: String, val title: String, val iconId : Int) {
    HomeScreen("home", "Home", R.drawable.home),
    SearchScreen("search", "Search", R.drawable.search),
    SavedScreen("saved", "Saved", R.drawable.saved),
    ProfileScreen("profile", "Profile", R.drawable.profile),
    LoginScreen("login", "Login", R.drawable.login),
    RegistrationScreen("registration","Registration", 0),
    CategoryScreen("category","Category",0),
    RecipeScreen("recipe","Recipe",0),
    StartScreen("start","Start",0)
}