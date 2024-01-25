package com.hellofresh.task2.ui.recipes.data.remote.datasource

import com.hellofresh.task2.data.remote.network.response.Recipe
import com.hellofresh.task2.data.remote.network.retrofit.RecipesApi

class RecipesRemoteDataSource(private val recipesApi: RecipesApi) {

    suspend fun loadRecipes(): List<Recipe> =
        recipesApi.loadRecipes()
}