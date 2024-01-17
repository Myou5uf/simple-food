package com.bignerdranch.android.simplefood.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.simplefood.R
import com.bignerdranch.android.simplefood.databinding.ActivityMealBinding
import com.bignerdranch.android.simplefood.fragments.HomeFragment
import com.bumptech.glide.Glide

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMealInformationFromIntent()

        setInformationInViews()
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title  = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        var intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}