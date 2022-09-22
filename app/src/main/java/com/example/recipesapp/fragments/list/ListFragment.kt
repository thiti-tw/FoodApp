package com.example.recipesapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.R
import com.example.recipesapp.viewmodel.MainViewModel
import com.example.recipesapp.viewmodel.RecipeViewModel


class ListFragment : Fragment() {

    private lateinit var mRecipeViewModel: RecipeViewModel
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        return view
    }

}