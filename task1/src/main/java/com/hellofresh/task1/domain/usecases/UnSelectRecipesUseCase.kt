package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class UnSelectRecipesUseCase(private val repository: MenuRepository) {
    fun build(
        recipes: List<Recipe>,
    ) = repository.unSelectRecipes(
        recipes = recipes,
    )
}