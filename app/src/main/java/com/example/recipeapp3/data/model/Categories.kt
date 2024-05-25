package com.example.recipeapp3.data.model

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String
)
data class CategoriesList(
    @SerializedName("categories") val categories: List<Categories>
)
