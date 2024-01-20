package com.bignerdranch.android.simplefood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.simplefood.databinding.MealItemBinding
import com.bignerdranch.android.simplefood.pojo.MealsByCategory
import com.bumptech.glide.Glide

class CategoryMealsAdapter: RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>() {
    lateinit var onItemClick: ((MealsByCategory) -> Unit)
    private var mealsList = ArrayList<MealsByCategory>()

    inner class CategoryMealsViewModel(val binding: MealItemBinding):RecyclerView.ViewHolder(binding.root)

    fun setMealsList(mealsList: List<MealsByCategory>) {
        this.mealsList = mealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
        return CategoryMealsViewModel(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        val currentMeal = mealsList[position]
        Glide.with(holder.itemView)
            .load(currentMeal.strMealThumb)
            .into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = currentMeal.strMeal

        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentMeal)
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}