package com.example.recipesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.model.Post
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

// provide data to the UI
class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<List<Recipe>>> = MutableLiveData()

    fun getRecipe() {
        viewModelScope.launch {
            //val response : Response<Post> = repository.getPost()
            val response = repository.getRecipe()
            myResponse.value = response
        }
    }
}