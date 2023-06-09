package com.bignerdranch.android.simplefood.retrofit

import com.bignerdranch.android.simplefood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}