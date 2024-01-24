package com.hellofresh.task1.data.local

import com.hellofresh.task1.data.FakeData
import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe

class MenuDataSource(private val fakeData: FakeData) {

    private val selectedRecipes = mutableListOf<Recipe>()

    fun getMenu(): Menu = fakeData.getMenu()

    fun selectRecipe(recipe: Recipe, menu: Menu) {
        if (selectedRecipes.size >= SELECTED_RECIPES_SINGLE_LIMIT && !menu.subscription.isForFamily) {
            throw IllegalArgumentException("Cannot select more than 3 recipes for individual subscription.")
        } else if (selectedRecipes.size >= SELECTED_RECIPES_FAMILY_LIMIT && menu.subscription.isForFamily) {
            throw IllegalArgumentException("Cannot select more than 5 recipes for individual subscription.")
        }

        if (!menu.recipes.contains(recipe)) {
            throw IllegalArgumentException("Recipe not found in the available recipes.")
        }

        if (!selectedRecipes.contains(recipe)) {
            selectedRecipes.add(recipe)
        }
    }

    fun selectMultipleRecipes(recipes: List<Recipe>, menu: Menu) {
        if (selectedRecipes.size >= SELECTED_RECIPES_SINGLE_LIMIT && !menu.subscription.isForFamily) {
            throw IllegalArgumentException("Cannot select more than 3 recipes for individual subscription.")
        } else if (selectedRecipes.size + recipes.size > SELECTED_RECIPES_FAMILY_LIMIT && menu.subscription.isForFamily) {
            throw IllegalArgumentException("Cannot select more than 5 recipes for individual subscription.")
        }

        if (!menu.recipes.containsAll(recipes)) {
            throw IllegalArgumentException("Some of the selected recipes not found in the available recipes.")
        }

        if (!selectedRecipes.containsAll(recipes)) {
            selectedRecipes.addAll(recipes)
        }
    }

    fun unselectRecipe(recipe: Recipe) {
        selectedRecipes.remove(recipe)
    }

    fun unselectMultipleRecipes(recipes: List<Recipe>) {
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

    companion object {
        const val SELECTED_RECIPES_SINGLE_LIMIT = 3
        const val SELECTED_RECIPES_FAMILY_LIMIT = 5
    }
}