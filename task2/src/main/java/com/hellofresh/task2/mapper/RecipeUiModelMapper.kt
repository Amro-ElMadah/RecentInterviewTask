package com.hellofresh.task2.mapper

import com.hellofresh.task2.data.remote.network.response.Recipe

data class RecipeUiModel(
    val image: String?,
    val name: String?,
    val headline: String?
)

fun Recipe.toUiModel(): RecipeUiModel = RecipeUiModel(
    image = this.image,
    name = this.name,
    headline = this.headline
)
