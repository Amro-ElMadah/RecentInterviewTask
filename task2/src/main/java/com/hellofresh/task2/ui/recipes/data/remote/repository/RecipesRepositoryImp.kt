package com.hellofresh.task2.ui.recipes.data.remote.repository

import com.hellofresh.task2.mapper.RecipesWithDateUiModel
import com.hellofresh.task2.mapper.toUiModelList
import com.hellofresh.task2.ui.recipes.data.remote.datasource.RecipesRemoteDataSource
import com.hellofresh.task2.ui.recipes.domain.repository.RecipesRepository
import com.hellofresh.task2.ui.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipesRepositoryImp(
    private val remoteDataSource: RecipesRemoteDataSource,
) : RecipesRepository {
    override suspend fun loadRecipes(): Flow<List<RecipesWithDateUiModel>> {

        return flow {
            emit(remoteDataSource.loadRecipes().toUiModelList(date = DateUtils.getCurrentDateInDdMmmFormat()))
        }
    }
}