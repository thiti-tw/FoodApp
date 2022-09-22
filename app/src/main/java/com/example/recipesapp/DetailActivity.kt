package com.example.recipesapp

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.viewmodel.RecipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row.view.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mRecipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        //receive data
        val extra = intent.extras
        if (extra != null){
            val data = extra.get("data") as Recipe
            findViewById<TextView>(R.id.detailName_txt).text = data.name
            findViewById<TextView>(R.id.detailTime_txt).text = data.time
            findViewById<TextView>(R.id.detailCalorie_txt).text = "Calorie : "+data.calories
            findViewById<TextView>(R.id.detailProtein_txt).text = "Protein : "+data.proteins
            findViewById<TextView>(R.id.detailCarbo_txt).text = "Carbohydrate : "+data.carbos
            findViewById<TextView>(R.id.detailFat_txt).text = "Fat : "+data.fats
            findViewById<TextView>(R.id.detailDescip_txt).text = data.description
            Picasso.with(this)
                .load(data.thumb)
                .into(findViewById<ImageView>(R.id.imageView2))
            val favoriteButton: Button = findViewById<Button>(R.id.btn_Favorite)
            val buttonDrawables: Drawable? = favoriteButton.background
            val buttonDrawable = DrawableCompat.wrap(buttonDrawables!!)

            favoriteButton.setOnClickListener {
                data.favorite = !data.favorite
                mRecipeViewModel.updateRecipe(data)
                if(data.favorite){
                    DrawableCompat.setTint(buttonDrawable, Color.RED)
                }else{
                    DrawableCompat.setTint(buttonDrawable, Color.GRAY)
                }
            }

            if(data.favorite){
                DrawableCompat.setTint(buttonDrawable, Color.RED)
            }else{
                DrawableCompat.setTint(buttonDrawable, Color.GRAY)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}