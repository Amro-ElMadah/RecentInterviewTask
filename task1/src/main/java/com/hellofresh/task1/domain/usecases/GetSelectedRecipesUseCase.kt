package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class GetSelectedRecipesUseCase(private val repository: MenuRepository) {
    fun build(): List<Recipe> =
        repository.getSelectedRecipes()
}