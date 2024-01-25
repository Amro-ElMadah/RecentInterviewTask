package com.hellofresh.task2.ui.recipes.presentation.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import com.hellofresh.task2.R
import com.hellofresh.task2.data.remote.network.response.HelloFreshResponse
import com.hellofresh.task2.mapper.RecipeUiModel
import com.hellofresh.task2.mapper.RecipesWithDateUiModel
import com.hellofresh.task2.ui.recipes.presentation.viewmodel.RecipesViewModel

@Composable
fun RecipesScreen() {
    val viewModel: RecipesViewModel = viewModel<RecipesViewModel>()

    val response by viewModel.recipes.collectAsState(initial = HelloFreshResponse.Loading(false))
    var snackBarVisible by remember { mutableStateOf(false) }
    var snackBarMessage by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        when (response) {
            is HelloFreshResponse.Loading -> {
                snackBarVisible = false
                if (response.loading == true) {
                    LoadingScreen()
                }
            }

            is HelloFreshResponse.Success ->
                if (response.data?.isNotEmpty() == true) {
                    ListScreen(
                        recipes = response.data.orEmpty(),
                    )
                } else {
                    EmptyListScreen(
                        response.data.orEmpty()[0].fetchDate.orEmpty(),
                    )
                }

            else -> if (response.errorType != null) {
                snackBarVisible = true
                snackBarMessage = stringResource(id = response.errorType!!.errorMessageId)
                ErrorScreen {
                    viewModel.loadRecipes()
                }
            }
        }

        if (snackBarVisible) {
            SnackBarView(snackBarMessage = snackBarMessage)
        }
    }
}

@Composable
fun ListScreen(
    recipes: List<RecipesWithDateUiModel>,
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        itemsIndexed(recipes) { index, item ->
            if (index == 0) {
                DateText(
                    date = item.fetchDate.orEmpty(),
                )
            } else {
                RecipeItem(
                    item.recipe!!,
                )
            }
        }
    }
}

@Composable
fun RecipeItem(
    recipe: RecipeUiModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            val painter =
                rememberAsyncImagePainter(
                    Builder(LocalContext.current)
                        .data(data = recipe.image)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .build()
                )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            16.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.surface),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(8.dp))

            recipe.name?.let { name ->
                Text(
                    text = name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                )
            }
            recipe.headline?.let { headline ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = headline,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                )
            }
        }
    }
}

@Composable
fun DateText(
    modifier: Modifier = Modifier,
    date: String,
) {
    Text(
        modifier = modifier,
        text = date,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.inverseSurface,
    )
}