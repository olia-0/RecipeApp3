package com.example.recipeapp3

import android.app.Application
import com.example.recipeapp3.data.AppContainer
import com.example.recipeapp3.data.DefaultAppContainer
//import com.example.recipeapp3.data.User
import com.example.recipeapp3.db.UsersDatabase

const val APPLICATION_KEY = "com.example.recipeapp3.RecipeApplication"

class RecipeApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)

    }
}