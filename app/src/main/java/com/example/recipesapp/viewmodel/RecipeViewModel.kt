package com.example.recipesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.RecipeDatabase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.repository.RecipeRepository
import com.example.recipesapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Recipe>>
    private  val repository: RecipeRepository

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        readAllData = repository.readAllData

    }

    fun addRecipe(recipe: List<Recipe>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateRecipe(recipe)
        }
    }
}