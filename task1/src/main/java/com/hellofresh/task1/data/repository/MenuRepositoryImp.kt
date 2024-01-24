package com.hellofresh.task1.data.repository

import com.hellofresh.task1.data.local.MenuDataSource
import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.repository.MenuRepository

class MenuRepositoryImp(
    private val menuDataSource: MenuDataSource,
) : MenuRepository {
    override fun getMenu(): Menu =
        menuDataSource.getMenu()

    override fun selectRecipe(
        recipe: Recipe,
    ) {
        menuDataSource.selectRecipe(
            recipe = recipe,
            menu = getMenu(),
        )
    }

    override fun selectMultipleRecipes(
        recipes: List<Recipe>,
    ) {
        menuDataSource.selectMultipleRecipes(
            recipes = recipes,
            menu = getMenu(),
        )
    }

    override fun unSelectRecipe(recipe: Recipe) {
        menuDataSource.unselectRecipe(recipe = recipe)
    }

    override fun unSelectMultipleRecipes(recipes: List<Recipe>) {
        menuDataSource.unselectMultipleRecipes(recipes = recipes)
    }

    override fun getSelectedRecipesCount(): Int =
        menuDataSource.getSelectedRecipeCount()

    override fun getSelectedRecipes(): List<Recipe> = menuDataSource.getSelectedRecipes()

    override fun getRecipesWithTag(tag: String): List<Recipe> =
        menuDataSource.getRecipesWithTag(tag = tag, availableRecipes =  menuDataSource.getMenu().recipes)
}