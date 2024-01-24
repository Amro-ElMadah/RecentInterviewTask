package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class SelectMultipleRecipesUseCase(private val repository: MenuRepository) {
    fun build(
        recipes: List<Recipe>,
    ) = repository.selectMultipleRecipes(
        recipes = recipes,
    )
}