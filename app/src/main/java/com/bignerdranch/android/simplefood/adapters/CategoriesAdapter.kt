package com.bignerdranch.android.simplefood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.simplefood.databinding.CategoryItemBinding
import com.bignerdranch.android.simplefood.pojo.Category
import com.bumptech.glide.Glide

class CategoriesAdapter(): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoriesList = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    fun setCategoryList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categoriesList[position]
        Glide
            .with(holder.itemView)
            .load(currentCategory.strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = currentCategory.strCategory

        holder.itemView.setOnClickListener{
            onItemClick!!.invoke(currentCategory)
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}