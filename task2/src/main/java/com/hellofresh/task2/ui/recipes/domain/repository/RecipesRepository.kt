package com.hellofresh.task2.ui.recipes.domain.repository

import com.hellofresh.task2.mapper.RecipesWithDateUiModel
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun loadRecipes(): Flow<List<RecipesWithDateUiModel>>
}