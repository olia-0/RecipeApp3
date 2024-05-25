package com.example.recipeapp3

import com.example.recipeapp3.data.RecipeFull
import com.example.recipeapp3.data.RecipeFullViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

/*class RecipeFullViewModelTest {
    private lateinit var recipeViewModel: RecipeFullViewModel

    @Before
    fun setUp() {
        recipeViewModel = RecipeFullViewModel()
    }

    @Test
    fun testFilterRecipesByName() {
        val filteredName = "паста"
        recipeViewModel.filterRecipesByName(filteredName)
        assertTrue(recipeViewModel.recipeListFull.all { it.name.contains(filteredName, ignoreCase = true) })
    }

    @Test
    fun testSortRecipesByName() {
        recipeViewModel.sortRecipesByName()
        val sortedNames = recipeViewModel.recipeListFull.map { it.name }
        val expectedSortedNames = recipeViewModel.recipeListFull.map { it.name }.sorted()
        assertEquals(expectedSortedNames, sortedNames)
    }


    @Test
    fun testAddRecipe() {
        val listSize = recipeViewModel.recipeListFull.size
        val newRecipe = RecipeFull("Новий рецепт пасти", R.drawable.pasta, listOf("Паста", "Томати"))
        recipeViewModel.addRecipe(newRecipe)
        assertEquals(listSize + 1, recipeViewModel.recipeListFull.size)
        assertTrue(recipeViewModel.recipeListFull.contains(newRecipe))
    }

    @Test
    fun testDeleteRecipeByName() {
        val recipeToDelete = recipeViewModel.recipeListFull.first()
        val initialSize = recipeViewModel.recipeListFull.size
        recipeViewModel.deleteRecipeByName(recipeToDelete.name)
        assertEquals(initialSize - 1, recipeViewModel.recipeListFull.size)
        assertFalse(recipeViewModel.recipeListFull.contains(recipeToDelete))
    }
}*/