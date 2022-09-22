package com.example.recipesapp.repository

import androidx.lifecycle.LiveData
import com.example.recipesapp.api.Retrofitinstance
import com.example.recipesapp.data.RecipeDao
import com.example.recipesapp.model.Post
import com.example.recipesapp.model.Recipe
import retrofit2.Response

class Repository { // access to multiple data sources

    /*val readAllData: LiveData<List<Recipe>> = recipeDao.readAllData()

    suspend fun addRecipe(recipe: Recipe){
        recipeDao.addRecipe(recipe)
    }*/

    suspend fun getRecipe(): Response<List<Recipe>>{
        //return Retrofitinstance.api.getPost()
        return Retrofitinstance.api.getRecipe()
    }
}