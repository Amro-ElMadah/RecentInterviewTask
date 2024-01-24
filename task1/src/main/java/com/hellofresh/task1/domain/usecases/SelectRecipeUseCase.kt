package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class SelectRecipeUseCase(private val repository: MenuRepository) {
    fun build(
        recipe: Recipe,
    ) = repository.selectRecipe(
        recipe = recipe,
    )
}