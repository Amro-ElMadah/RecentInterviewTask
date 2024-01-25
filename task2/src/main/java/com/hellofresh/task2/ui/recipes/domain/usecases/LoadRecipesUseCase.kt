package com.hellofresh.task2.ui.recipes.domain.usecases

import com.hellofresh.task2.mapper.RecipesWithDateUiModel
import com.hellofresh.task2.ui.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class LoadRecipesUseCase(private val repository: RecipesRepository) {
    suspend fun build(): Flow<List<RecipesWithDateUiModel>> =
        repository.loadRecipes()
}