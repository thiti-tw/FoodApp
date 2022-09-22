package com.example.recipesapp.api

import com.example.recipesapp.model.Post
import com.example.recipesapp.model.Recipe
import retrofit2.Response
import retrofit2.http.GET


interface SimpleApi {
    //@GET(value = "android-test/recipes.json")
    @GET("/android-test/recipes.json")


    //suspend fun getPost(): Response<Post>
    suspend fun getRecipe(): Response<List<Recipe>>
}