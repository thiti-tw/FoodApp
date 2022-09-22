package com.example.recipesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recipesapp.model.Recipe

@Dao
interface RecipeDao {//contain methods used for access database

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: List<Recipe>)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe_table")
    fun readAllData(): LiveData<List<Recipe>>
}