package com.hellofresh.task1.domain.repository

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe

interface MenuRepository {
    fun getMenu(): Menu

    fun selectRecipe(recipe: Recipe)

    fun selectMultipleRecipes(recipes: List<Recipe>)

    fun unSelectRecipe(recipe: Recipe)

    fun unSelectMultipleRecipes(recipes: List<Recipe>)

    fun getSelectedRecipesCount(): Int

    fun getSelectedRecipes(): List<Recipe>

    fun getRecipesWithTag(tag: String): List<Recipe>
}