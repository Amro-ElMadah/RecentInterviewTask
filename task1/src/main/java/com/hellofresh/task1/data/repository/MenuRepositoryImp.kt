package com.hellofresh.task1.data.repository

import com.hellofresh.task1.data.local.MenuDataSource
import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class MenuRepositoryImp(
    private val menuDataSource: MenuDataSource,
) : MenuRepository {
    override fun getAvailableRecipes(): List<Recipe> =
        menuDataSource.getAvailableRecipes()

    override fun selectRecipe(
        recipe: Recipe,
        menu: Menu,
    ) {
        menuDataSource.selectRecipe(
            recipe = recipe,
            menu = menu,
        )
    }

    override fun unSelectRecipe(recipe: Recipe) {
        menuDataSource.unselectRecipe(recipe = recipe)
    }

    override fun unSelectRecipes(recipes: List<Recipe>) {
        menuDataSource.unselectRecipes(recipes = recipes)
    }

    override fun getSelectedRecipesCount(): Int =
        menuDataSource.getSelectedRecipeCount()

    override fun getSelectedRecipes(): List<Recipe> = menuDataSource.getSelectedRecipes()

    override fun getRecipesWithTag(tag: String): List<Recipe> =
        getRecipesWithTag(tag = tag)
}