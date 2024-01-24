package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class UnSelectMultipleRecipesUseCase(private val repository: MenuRepository) {
    fun build(
        recipes: List<Recipe>,
    ) = repository.unSelectMultipleRecipes(
        recipes = recipes,
    )
}