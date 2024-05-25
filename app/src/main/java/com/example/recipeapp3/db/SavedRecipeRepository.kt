package com.example.recipeapp3.db

import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.data.model.RecipeByCategoryDTO
import com.example.recipeapp3.data.model.RecipeDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
import com.example.recipeapp3.db.model.SavedRecipe
import com.example.recipeapp3.network.RecipeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SavedRecipeRepository {
    suspend fun getRecipe(): Flow<List<SavedRecipe>>
    suspend fun insertRecipe(savedRecipe: SavedRecipe)
    suspend fun updateRecipe(savedRecipe: SavedRecipe)
    suspend fun deleteRecipe(savedRecipe: SavedRecipe)
    suspend fun deleteRecipeById(recipeid: String)
    suspend fun getSavedRecipeById(recipeid: String): Flow<SavedRecipe?>
//    suspend fun getSavedRecipeByIdAndStatus(recipeid: String, recipestatus: Boolean): Flow<SavedRecipe?>
}

class SavedRecipesRepository(
    private val recipeSavedDB: SavedRecipeDao
):SavedRecipeRepository {
    override suspend fun getRecipe(): Flow<List<SavedRecipe>> =
        recipeSavedDB.getAllSavedRecipe()
    override suspend fun insertRecipe(savedRecipe: SavedRecipe) {
        recipeSavedDB.insertRecipe(savedRecipe)
    }

    override suspend fun updateRecipe(savedRecipe: SavedRecipe) {
        recipeSavedDB.updateRecipe(savedRecipe)
    }

    override suspend fun deleteRecipe(savedRecipe: SavedRecipe) {
        recipeSavedDB.deleteRecipe(savedRecipe)
    }

    override suspend fun deleteRecipeById(recipeid: String) {
        recipeSavedDB.deleteRecipeById(recipeid)
    }
    override suspend fun getSavedRecipeById(recipeid: String): Flow<SavedRecipe?> {
        return recipeSavedDB.getSavedRecipeById(recipeid)
    }

//    override suspend fun getSavedRecipeByIdAndStatus(recipeid: String, recipestatus: Boolean): Flow<SavedRecipe?> {
//        return recipeSavedDB.getSavedRecipeByIdAndStatus(recipeid, recipestatus)
//    }

}


