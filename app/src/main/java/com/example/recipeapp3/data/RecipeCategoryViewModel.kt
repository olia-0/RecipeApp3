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
import com.example.recipeapp3.R
import com.example.recipeapp3.RecipeApplication
import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException




sealed interface RecipeCategoryUiState {
    data class Success(val recipeCategoriesDTO: List<CategoriesDTO>): RecipeCategoryUiState
    object Error : RecipeCategoryUiState
    object Loading : RecipeCategoryUiState
}

class RecipeCategoryViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    var recipeCategoryUiState: RecipeCategoryUiState by mutableStateOf(RecipeCategoryUiState.Loading)
        private set

    init {
        getCategories()
    }

    fun getCategories(){
        viewModelScope.launch {
            recipeCategoryUiState = RecipeCategoryUiState.Loading
            recipeCategoryUiState = try {
                RecipeCategoryUiState.Success(recipeRepository.getCategories())
            }catch (e: IOException){
                RecipeCategoryUiState.Error
            }

        }
    }
    var currentRecipe by mutableStateOf<CategoriesDTO?>(null)
    //var currentRecipe by mutableStateOf<String?>(null)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RecipeApplication)
                val recipeRepository = application.container.recipeRepository
                RecipeCategoryViewModel(recipeRepository = recipeRepository)
            }
        }
    }



//    private val _recipeList = mutableStateOf(recipeList)
//    val recipeListCategory by _recipeList
//
//
//    init {
//        _recipeList.value = listOf(
//            Recipe("Паста", R.drawable.pasta2),
//            Recipe("Піца", R.drawable.pizza2),
//            Recipe("Салат", R.drawable.salad2),
//            Recipe("Суп", R.drawable.soup2),
//            Recipe("Омлет", R.drawable.omelette2),
//            Recipe("Десерт", R.drawable.dessert2)
//        )
//    }
//    var currentRecipe by mutableStateOf<Recipe?>(null)
}
