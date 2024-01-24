package com.hellofresh.task1.domain.usecases

import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class GetRecipesWithTagUseCase(private val repository: MenuRepository) {
    fun build(tag: String): List<Recipe> = repository.getRecipesWithTag(tag)
}