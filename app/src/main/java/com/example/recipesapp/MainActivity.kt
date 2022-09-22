package com.example.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.fragments.list.ListAdapter
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.repository.Repository
import com.example.recipesapp.viewmodel.MainViewModel
import com.example.recipesapp.viewmodel.MainViewModelFactory
import com.example.recipesapp.viewmodel.RecipeViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mRecipeViewModel: RecipeViewModel
    private val timer = Timer()
    private var i = 0
    private lateinit var listData: List<Recipe>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init database
        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        //read data
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        //recyclerview
        recyclerView = findViewById(R.id.recyclerview)
        val adapter = ListAdapter(this)
        adapter.setOnItemClick {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        //get data from database
        mRecipeViewModel.readAllData.observe(this, Observer { response ->
            if(response.isEmpty()){
                viewModel.getRecipe()
                viewModel.myResponse.observe(this, Observer {
                    if (it.isSuccessful) {
                        val items = it.body()
                        if (items != null) {
                            mRecipeViewModel.addRecipe(items)
                        }
                    }
                })
            }
            listData = response
            adapter.setData(response)
        })
        repeatGetData()

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onStop() {
        mRecipeViewModel.addRecipe(listData)
        super.onStop()
    }

    override fun onDestroy() {
        mRecipeViewModel.addRecipe(listData)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sortByName){
            listData = listData.sortedBy { it.name }
        }else if(item.itemId == R.id.sortByCalories){
            listData = listData.sortedBy { it.calories }
        }
        mRecipeViewModel.addRecipe(listData)
        return super.onOptionsItemSelected(item)
    }

    fun repeatGetData() {

        timer.scheduleAtFixedRate(
            object : TimerTask() {

                override fun run() {
                    try {
                        viewModel.getRecipe()
                        mRecipeViewModel.addRecipe(viewModel.myResponse.value!!.body() as List<Recipe>)
                        Log.d("Repeat", "insert data success")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }, 0, 300000
        )   // 1000 Millisecond  = 1 second
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),0){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val startPosition = viewHolder.adapterPosition
            val endPosition = target.adapterPosition

            Collections.swap(listData, startPosition, endPosition)
                recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
                return true

        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }

    }

}