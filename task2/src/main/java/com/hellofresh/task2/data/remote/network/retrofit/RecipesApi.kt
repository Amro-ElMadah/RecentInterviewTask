package com.hellofresh.task2.data.remote.network.retrofit

import com.hellofresh.task2.data.remote.network.response.Recipe
import retrofit2.http.GET

interface RecipesApi {

    @GET("recipes.json")
    suspend fun loadRecipes(): List<Recipe>
}