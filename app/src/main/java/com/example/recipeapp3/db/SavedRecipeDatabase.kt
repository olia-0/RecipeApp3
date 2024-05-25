package com.example.recipeapp3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp3.db.model.SavedRecipe
import com.example.recipeapp3.db.model.User

@Database(entities = [SavedRecipe::class], version = 1)
abstract class SavedRecipeDatabase : RoomDatabase(){
    abstract fun savedRecipeDao(): SavedRecipeDao
    companion object {
        @Volatile
        private var Instance: SavedRecipeDatabase? = null
        fun getDatabase(context: Context): SavedRecipeDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SavedRecipeDatabase::class.java,
                    "SavedRecipeDB"
                ).build()
            }
        }
    }
}