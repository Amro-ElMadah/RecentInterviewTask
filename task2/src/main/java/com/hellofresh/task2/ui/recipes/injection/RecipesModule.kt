package com.hellofresh.task2.ui.recipes.injection

import com.hellofresh.task2.data.remote.network.retrofit.RecipesApi
import com.hellofresh.task2.ui.recipes.data.remote.datasource.RecipesRemoteDataSource
import com.hellofresh.task2.ui.recipes.data.remote.repository.RecipesRepositoryImp
import com.hellofresh.task2.ui.recipes.domain.repository.RecipesRepository
import com.hellofresh.task2.ui.recipes.domain.usecases.LoadRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
open class RecipesModule {

    @Provides
    fun provideRecipesRemoteDataSource(recipesApi: RecipesApi) =
        RecipesRemoteDataSource(recipesApi = recipesApi)

    @Provides
    fun provideRecipesRepository(
        remoteDataSource: RecipesRemoteDataSource,
    ): RecipesRepository =
        RecipesRepositoryImp(remoteDataSource)

    @Provides
    fun provideLoadRecipesUseCase(repository: RecipesRepository) =
        LoadRecipesUseCase(repository)
}