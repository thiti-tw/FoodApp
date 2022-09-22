package com.example.recipesapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Post (

    val calories: String,
    val carbos: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val id: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String
)