package com.example.recipeapp3.data.model

import com.google.gson.annotations.SerializedName

data class RecipeByCategory(
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    @SerializedName("idMeal") val idMeal: String,
)

data class RecipesByCategoryList(
    @SerializedName("meals") val meals: List<RecipeByCategory>
)
