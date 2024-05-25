package com.example.recipeapp3.network

import com.example.recipeapp3.data.model.CategoriesList
import com.example.recipeapp3.data.model.MealsList
import com.example.recipeapp3.data.model.RecipesByCategoryList
//import com.example.recipeapp3.data.model.recipeList.RecipeList
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("search.php")
    suspend fun searchRecipe(
        @Query("s") searchQuery: String,
    ): MealsList

    @GET("categories.php")
    suspend fun getCategories(
    ): CategoriesList

    @GET("filter.php")
    suspend fun getRecipesByCategories(
        @Query("c") searchQuery: String,
    ): RecipesByCategoryList

    @GET("lookup.php")
    suspend fun getRecipeDetails(
        @Query("i") searchQuery: String,
    ): MealsList
}