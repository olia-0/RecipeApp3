package com.example.recipeapp3.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_recipe")
data class SavedRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "recipe_id")
    val recipeid: String,
    @ColumnInfo(name = "recipe_name")
    val recipename: String,
    @ColumnInfo(name = "recipe_photo")
    val recipephoto: String?,
//    @ColumnInfo(name = "recipe_status")
//    val recipe_status: Boolean
) {

}
