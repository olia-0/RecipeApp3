package com.example.recipeapp3.data


import android.annotation.SuppressLint
import android.text.Spannable.Factory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipeapp3.R
import com.example.recipeapp3.RecipeApplication
import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.http.Query


sealed interface RecipeFullUiState {
    data class Success(val recipeSearchDTO: List<RecipeSearchDTO>): RecipeFullUiState
    object Error : RecipeFullUiState
    object Loading : RecipeFullUiState
}

class RecipeFullViewModel (
    private val recipeRepository: RecipeRepository
): ViewModel(){

    var recipeFullUiState: RecipeFullUiState by mutableStateOf(RecipeFullUiState.Loading)
        private set

    init {
        getRecipe("A")
    }
    var currentRecipe by mutableStateOf<RecipeSearchDTO?>(null)

    fun getRecipe(query: String){
        viewModelScope.launch {
            recipeFullUiState = RecipeFullUiState.Loading
            recipeFullUiState = try {
                RecipeFullUiState.Success(recipeRepository.getRecipe(query))
            }catch (e:IOException){
                RecipeFullUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecipeApplication)
                val recipeRepository = application.container.recipeRepository
                RecipeFullViewModel(recipeRepository = recipeRepository)
            }
        }
    }


}