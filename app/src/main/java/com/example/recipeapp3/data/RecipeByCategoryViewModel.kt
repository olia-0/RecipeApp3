package com.example.recipeapp3.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipeapp3.RecipeApplication
import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.data.model.RecipeByCategoryDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface RecipeByCategoryUiState {
    data class Success(val recipeByCategoryDTO: List<RecipeByCategoryDTO>): RecipeByCategoryUiState
    object Error : RecipeByCategoryUiState
    object Loading : RecipeByCategoryUiState
}

class RecipeByCategoryViewModel (
    private val recipeRepository: RecipeRepository
): ViewModel(){

    var recipeByCategoryUiState: RecipeByCategoryUiState by mutableStateOf(RecipeByCategoryUiState.Loading)
        private set

    var currentRecipeByCategory by mutableStateOf<RecipeByCategoryDTO?>(null)

    init {
        getRecipeByCategory("Breakfast")
    }

    fun getRecipeByCategory(query: String){
        viewModelScope.launch {
            recipeByCategoryUiState = RecipeByCategoryUiState.Loading
            recipeByCategoryUiState = try {
                RecipeByCategoryUiState.Success(recipeRepository.getRecipeByCategories(query))
            }catch (e: IOException){
                RecipeByCategoryUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RecipeApplication)
                val recipeRepository = application.container.recipeRepository
                RecipeByCategoryViewModel(recipeRepository = recipeRepository)
            }
        }
    }


}