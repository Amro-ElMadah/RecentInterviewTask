package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class UnSelectRecipeUseCase(private val repository: MenuRepository) {
    fun build(
        recipe: Recipe,
    ) = repository.unSelectRecipe(
        recipe = recipe,
    )
}