package com.example.recipeapp3.data.model

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    val idRecipe: String,
    val nameRecipe: String,
    val photoRecipe: String?,
    val categoryRecipe: String?,
    val areaRecipe: String?,
    val instructionsRecipe: String?,
    val tagsRecipe: String?,
    val youtubeRecipe: String?,
    //val strIngredients: List<Pair<String, String>>?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?,

    ){
//    val ingredientsRecipe: List<Pair<String, String>> by lazy {
//        strIngredients?.let { extractIngredientsAndMeasures() } ?: emptyList()
//    }
//
//    private fun extractIngredientsAndMeasures(): List<Pair<String, String>> {
//        val ingredientsList = mutableListOf<Pair<String, String>>()
//        for (i in 1..20) {
//            val ingredientName = javaClass.getDeclaredField("strIngredient$i").get(this) as? String
//            val ingredientMeasure = javaClass.getDeclaredField("strMeasure$i").get(this) as? String
//            if (!ingredientName.isNullOrBlank() && !ingredientMeasure.isNullOrBlank()) {
//                ingredientsList.add(Pair(ingredientName, ingredientMeasure))
//            }
//        }
//        return ingredientsList
//    }

}
