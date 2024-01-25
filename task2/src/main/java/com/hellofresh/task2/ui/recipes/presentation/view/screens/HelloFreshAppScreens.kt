package com.hellofresh.task2.ui.recipes.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hellofresh.task2.ui.theme.HelloFreshTheme

@Composable
fun HelloFreshAppScreens() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    var darkTheme by remember { mutableStateOf(isSystemInDarkTheme) }

    HelloFreshTheme(darkTheme) {
        Column(
            Modifier.background(
                MaterialTheme.colorScheme.surface
            )
        ) {
            ThemeSwitch(darkTheme) {
                darkTheme = it
            }
            RecipesScreen()
        }
    }
}