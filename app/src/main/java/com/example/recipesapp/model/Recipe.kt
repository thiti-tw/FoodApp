package com.example.recipesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "recipe_table")
data class Recipe ( //Recipe Model

    val calories: String,
    val carbos: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,

    @PrimaryKey
        val id: String,

    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String,

    var favorite: Boolean = false
        ):Parcelable

