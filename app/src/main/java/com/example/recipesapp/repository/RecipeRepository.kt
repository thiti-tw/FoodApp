package com.example.recipesapp.repository

import androidx.lifecycle.LiveData
import com.example.recipesapp.data.RecipeDao
import com.example.recipesapp.model.Recipe

class RecipeRepository(private val recipeDao: RecipeDao) {

    val readAllData: LiveData<List<Recipe>> = recipeDao.readAllData()

    suspend fun addRecipe(recipe: List<Recipe>){
        recipeDao.addRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe){
        recipeDao.updateRecipe(recipe)
    }

}