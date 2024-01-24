package com.hellofresh.task1.domain.repository

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe

interface MenuRepository {
    fun getAvailableRecipes(): List<Recipe>

    fun selectRecipe(recipe: Recipe, menu: Menu)

    fun unSelectRecipe(recipe: Recipe)

    fun unSelectRecipes(recipes: List<Recipe>)

    fun getSelectedRecipesCount(): Int

    fun getSelectedRecipes(): List<Recipe>

    fun getRecipesWithTag(tag: String): List<Recipe>
}