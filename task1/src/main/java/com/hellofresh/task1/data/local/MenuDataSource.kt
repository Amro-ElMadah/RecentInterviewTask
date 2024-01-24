package com.hellofresh.task1.data.local

import com.hellofresh.task1.data.FakeData
import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe

class MenuDataSource(private val fakeData: FakeData) {

    private val selectedRecipes = mutableListOf<Recipe>()

    fun getAvailableRecipes(): List<Recipe> = fakeData.getMenu().recipes

    fun selectRecipe(recipe: Recipe, menu: Menu) {
        if (selectedRecipes.size >= 3 && !menu.subscription.isForFamily) {
            throw IllegalArgumentException("Cannot select more than 3 recipes for individual subscription.")
        }

        if (!menu.recipes.contains(recipe)) {
            throw IllegalArgumentException("Recipe not found in the available recipes.")
        }

        if (!selectedRecipes.contains(recipe)) {
            selectedRecipes.add(recipe)
        }
    }

    fun unselectRecipe(recipe: Recipe) {
        selectedRecipes.remove(recipe)
    }

    fun unselectRecipes(recipes: List<Recipe>) {
        selectedRecipes.removeAll(recipes)
    }

    fun getSelectedRecipeCount(): Int {
        return selectedRecipes.size
    }

    fun getSelectedRecipes(): List<Recipe> {
        return selectedRecipes.toList()
    }

    fun getRecipesWithTag(tag: String, availableRecipes: List<Recipe>): List<Recipe> {
        return availableRecipes.filter { tag in it.tags }
    }
}