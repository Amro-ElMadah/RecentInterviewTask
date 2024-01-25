package com.hellofresh.task2.ui.recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellofresh.task2.data.remote.network.response.HelloFreshResponse
import com.hellofresh.task2.data.remote.network.retrofit.getErrorType
import com.hellofresh.task2.mapper.RecipesWithDateUiModel
import com.hellofresh.task2.ui.recipes.domain.usecases.LoadRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val loadRecipesUseCase: LoadRecipesUseCase,
) : ViewModel() {

    private val _recipes =
        MutableStateFlow<HelloFreshResponse<List<RecipesWithDateUiModel>?>>(
            HelloFreshResponse.Loading(
                true
            )
        )
    val recipes get() = _recipes.asStateFlow()

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadRecipesUseCase.build()
                    .onStart {
                        _recipes.emit(HelloFreshResponse.Loading(true))
                    }
                    .catch {
                        _recipes.emit(HelloFreshResponse.Error(it.getErrorType()))
                    }
                    .collect {
                        _recipes.emit(HelloFreshResponse.Success(it))
                    }
            }
        }
    }
}