package com.example.recipesapp.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter(private val context: Context): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var recipeList = emptyList<Recipe>()
    private lateinit var clickItem:((item: Recipe)->Unit)

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipeList[position]

        Picasso.with(context)
            .load(currentItem.thumb)
            .into(holder.itemView.imageView)
        holder.itemView.name_txt.text = currentItem.name
        holder.itemView.description_txt.text = "Calories : "+if (currentItem.calories.isEmpty())"0 kcal" else currentItem.calories
        holder.itemView.rowItem_layout.setOnClickListener {
            this.clickItem(currentItem)
        }
        if (currentItem.favorite){
            holder.itemView.imageView3.visibility = View.VISIBLE
        }else{
            holder.itemView.imageView3.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun setData(recipe: List<Recipe>){
        this.recipeList = recipe
        notifyDataSetChanged()
    }

    fun setOnItemClick(clickItem:((item: Recipe)->Unit)){
        this.clickItem = clickItem
    }
}