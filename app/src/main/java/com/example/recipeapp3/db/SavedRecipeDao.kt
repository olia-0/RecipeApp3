package com.example.recipeapp3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.recipeapp3.db.model.SavedRecipe
import com.example.recipeapp3.db.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedRecipeDao {
    @Query("SELECT * FROM saved_recipe")
    fun getAllSavedRecipe(): Flow<List<SavedRecipe>>

    @Query("SELECT * FROM saved_recipe WHERE recipe_id = :recipeid")
    fun getSavedRecipeById(recipeid: String): Flow<SavedRecipe?>

//    @Query("SELECT * FROM saved_recipe WHERE recipe_status = :recipestatus AND recipe_id = :recipeid")
//    fun getSavedRecipeByIdAndStatus(recipeid: String,recipestatus: Boolean): Flow<SavedRecipe?>

    @Insert
    suspend fun insertRecipe(saved_recipe: SavedRecipe)

    @Update
    suspend fun updateRecipe(saved_recipe: SavedRecipe)

    @Delete
    suspend fun deleteRecipe(saved_recipe: SavedRecipe)

    @Query("DELETE FROM saved_recipe WHERE recipe_id = :recipeid")
    suspend fun deleteRecipeById(recipeid: String)

}