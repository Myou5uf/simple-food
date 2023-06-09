package com.bignerdranch.android.simplefood.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.simplefood.R
import com.bignerdranch.android.simplefood.adapters.MostPopularAdapter
import com.bignerdranch.android.simplefood.databinding.FragmentHomeBinding
import com.bignerdranch.android.simplefood.pojo.CategoryMeals
import com.bignerdranch.android.simplefood.pojo.Meal
import com.bignerdranch.android.simplefood.pojo.MealList
import com.bignerdranch.android.simplefood.retrofit.RetrofitInstance
import com.bignerdranch.android.simplefood.viewModel.HomeViewModel
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var popularItemsAdapter: MostPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

        popularItemsAdapter = MostPopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparedPopularItemsRecyclerView()

        homeMvvm.getRandomMeal()
        observerRandomMeal()

        homeMvvm.getPopularItems()
        observerPopularItemsLiveData()
    }

    private fun preparedPopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun observerPopularItemsLiveData() {
        homeMvvm.observePopularItemsLiveData()
            .observe(viewLifecycleOwner, object : Observer<List<CategoryMeals>> {
                override fun onChanged(t: List<CategoryMeals>?) {
                    popularItemsAdapter.setMeals(mealsList = t as ArrayList<CategoryMeals>)
                }

            })
    }

    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal> {
            override fun onChanged(t: Meal?) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb)
                    .into(binding.imgRandomMeal);
            }
        })
    }


}