package com.hellofresh.task1.data

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.data.model.Subscription
import java.util.Date

class FakeData {

    fun getMenu() = Menu(recipes = getAvailableRecipes(), subscription = getSubscription())

    private fun getAvailableRecipes() = listOf(
        Recipe(0, "Recipe 1", listOf("hot")),
        Recipe(1, "Recipe 2", listOf("quick")),
        Recipe(2, "Recipe 3", listOf("low-calories")),
        Recipe(3, "Recipe 4", listOf("high-calories")),
    )

    private fun getSubscription() =
        Subscription(0, Date(), false)
}