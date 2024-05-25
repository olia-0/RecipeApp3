package com.example.recipeapp3.data

import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.data.model.RecipeByCategory
import com.example.recipeapp3.data.model.RecipeByCategoryDTO
import com.example.recipeapp3.data.model.RecipeDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
//import com.example.recipeapp3.data.model.recipeList.Recipe
import com.example.recipeapp3.network.RecipeService

interface RecipeRepository {
    suspend fun getRecipe(quire: String): List<RecipeSearchDTO>
    suspend fun getCategories(): List<CategoriesDTO>
    suspend fun getRecipeByCategories(quire: String): List<RecipeByCategoryDTO>
    suspend fun getRecipeDetails(quire: String): List<RecipeDTO>
}

class RecipesRepository(
    private val recipeService: RecipeService
):RecipeRepository{
    override suspend fun getRecipe(
        quire: String
    ): List<RecipeSearchDTO> = recipeService.searchRecipe(quire).meals.map {items ->
        RecipeSearchDTO(
            idRecipe = items.idMeal,
            label = items.strMeal,
            image = items.strMealThumb
        )
    }
    override suspend fun getCategories(
    ): List<CategoriesDTO> = recipeService.getCategories().categories.map { items ->
        CategoriesDTO(
            idCategory = items.idCategory,
            strCategory = items.strCategory,
            strCategoryThumb = items.strCategoryThumb,
            strCategoryDescription = items.strCategoryDescription
        )
    }
    override suspend fun getRecipeByCategories(
        quire: String
    ): List<RecipeByCategoryDTO> = recipeService.getRecipesByCategories(quire).meals.map { items ->
        RecipeByCategoryDTO(
            nameRecipe = items.strMeal,
            photoRecipe = items.strMealThumb,
            idRecipe = items.idMeal
        )
    }
    override suspend fun getRecipeDetails(
        quire: String
    ): List<RecipeDTO> = recipeService.getRecipeDetails(quire).meals.map { items ->
        RecipeDTO(
            idRecipe = items.idMeal,
            nameRecipe = items.strMeal,
            photoRecipe = items.strMealThumb,
            categoryRecipe = items.strCategory,
            areaRecipe = items.strArea,
            instructionsRecipe = items.strInstructions,
            tagsRecipe = items.strTags,
            youtubeRecipe = items.strYoutube,
            //strIngredients = items.ingredientsRecipe
            strIngredient1 = items.strIngredient1,
            strIngredient2 = items.strIngredient2,
            strIngredient3 = items.strIngredient3,
            strIngredient4 = items.strIngredient4,
            strIngredient5 = items.strIngredient5,
            strIngredient6 = items.strIngredient6,
            strIngredient7 = items.strIngredient7,
            strIngredient8 = items.strIngredient8,
            strIngredient9 = items.strIngredient9,
            strIngredient10 = items.strIngredient10,
            strIngredient11 = items.strIngredient11,
            strIngredient12 = items.strIngredient12,
            strIngredient13 = items.strIngredient13,
            strIngredient14 = items.strIngredient14,
            strIngredient15 = items.strIngredient15,
            strIngredient16 = items.strIngredient16,
            strIngredient17 = items.strIngredient17,
            strIngredient18 = items.strIngredient18,
            strIngredient19 = items.strIngredient19,
            strIngredient20 = items.strIngredient20,
            strMeasure1 = items.strMeasure1,
            strMeasure2 = items.strMeasure2,
            strMeasure3 = items.strMeasure3,
            strMeasure4 = items.strMeasure4,
            strMeasure5 = items.strMeasure5,
            strMeasure6 = items.strMeasure6,
            strMeasure7 = items.strMeasure7,
            strMeasure8 = items.strMeasure8,
            strMeasure9 = items.strMeasure9,
            strMeasure10 = items.strMeasure10,
            strMeasure11 = items.strMeasure11,
            strMeasure12 = items.strMeasure12,
            strMeasure13 = items.strMeasure13,
            strMeasure14 = items.strMeasure14,
            strMeasure15 = items.strMeasure15,
            strMeasure16 = items.strMeasure16,
            strMeasure17 = items.strMeasure17,
            strMeasure18 = items.strMeasure18,
            strMeasure19 = items.strMeasure19,
            strMeasure20 = items.strMeasure20
        )
    }


}