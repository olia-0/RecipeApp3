package com.example.recipeapp3.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipeapp3.RecipeApplication
import com.example.recipeapp3.data.model.RecipeSearchDTO
import com.example.recipeapp3.db.SavedRecipeRepository
import com.example.recipeapp3.db.SavedRecipesRepository
import com.example.recipeapp3.db.model.SavedRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface SavedRecipeUiState {
    data class Success(val recipeSaved: Flow<List<SavedRecipe>>): SavedRecipeUiState
    object Error : SavedRecipeUiState
    object Loading : SavedRecipeUiState
}

class SavedRecipeViewModel(
    private val savedRecipesRepository: SavedRecipeRepository
) : ViewModel() {
    var recipeSavedUiState: SavedRecipeUiState by mutableStateOf(SavedRecipeUiState.Loading)
        private set
    private val _savedRecipes = MutableStateFlow<List<SavedRecipe>>(emptyList())
    val savedRecipes: StateFlow<List<SavedRecipe>> = _savedRecipes
    init {
        getSavedRecipes()
        Log.d("SAVED", savedRecipes.toString())
    }

    fun getSavedRecipes(){
        viewModelScope.launch {
            recipeSavedUiState = SavedRecipeUiState.Loading
            recipeSavedUiState = try {
                SavedRecipeUiState.Success(savedRecipesRepository.getRecipe())
            }catch (e: IOException){
                SavedRecipeUiState.Error
            }

        }
    }


    fun insertRecipe(savedRecipe: SavedRecipe) {
        viewModelScope.launch {
            savedRecipesRepository.insertRecipe(savedRecipe)
        }
    }


    fun updateRecipe(savedRecipe: SavedRecipe) {
        viewModelScope.launch {
            savedRecipesRepository.updateRecipe(savedRecipe)
        }
    }


    fun deleteRecipe(savedRecipe: SavedRecipe) {
        viewModelScope.launch {
            savedRecipesRepository.deleteRecipe(savedRecipe)
        }
    }

    // Функція для видалення збереженого рецепту за його ідентифікатором
    fun deleteRecipeById(recipeid: String) {
        viewModelScope.launch {
            savedRecipesRepository.deleteRecipeById(recipeid)
        }
    }

    // Функція для отримання збереженого рецепту за його ідентифікатором
    suspend fun getSavedRecipeById(recipeid: String): SavedRecipe? {
        return savedRecipesRepository.getSavedRecipeById(recipeid).firstOrNull()
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RecipeApplication)
                val savedRecipesRepository = application.container.savedRecipesRepository
                SavedRecipeViewModel(savedRecipesRepository = savedRecipesRepository)
            }
        }
    }
}
