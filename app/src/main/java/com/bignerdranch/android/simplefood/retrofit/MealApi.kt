package com.bignerdranch.android.simplefood.retrofit

import com.bignerdranch.android.simplefood.pojo.CategoryList
import com.bignerdranch.android.simplefood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<CategoryList>
}