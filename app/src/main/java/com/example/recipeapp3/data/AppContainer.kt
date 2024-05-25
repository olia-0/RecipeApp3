package com.example.recipeapp3.data

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.recipeapp3.db.SavedRecipeDatabase
import com.example.recipeapp3.db.SavedRecipesRepository
import com.example.recipeapp3.network.RecipeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer{
    val recipeRepository: RecipeRepository
    val savedRecipesRepository: SavedRecipesRepository
}
class DefaultAppContainer(private val context: Context): AppContainer{
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val  retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: RecipeService by lazy {
        retrofit.create(RecipeService::class.java)
    }
    override val recipeRepository: RecipeRepository by lazy {
        RecipesRepository(retrofitService)
    }
    override val savedRecipesRepository: SavedRecipesRepository by lazy {
        SavedRecipesRepository(
            recipeSavedDB = SavedRecipeDatabase.getDatabase(context).savedRecipeDao()
        )
    }
}
