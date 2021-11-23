package com.kdjj.domain.repository

import com.kdjj.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

interface RecipeRepository {

    suspend fun saveLocalRecipe(
        recipe: Recipe
    ): Result<Boolean>

    suspend fun updateLocalRecipe(
        recipe: Recipe
    ): Result<Boolean>

    suspend fun deleteLocalRecipe(
        recipe: Recipe
    ): Result<Boolean>

    suspend fun uploadRecipe(
        recipe: Recipe
    ): Result<Unit>

    suspend fun increaseRemoteRecipeViewCount(
        recipe: Recipe
    ): Result<Unit>

    suspend fun deleteRemoteRecipe(
        recipe: Recipe
    ): Result<Unit>

    fun getLocalRecipeFlow(
        recipeId: String
    ): Flow<Recipe>

    suspend fun fetchRemoteRecipe(
        recipeId: String
    ): Result<Recipe>

    fun getRecipeUpdateFlow(): Flow<Int>

    suspend fun getLocalRecipe(
        recipeId: String
    ): Result<Recipe>

    suspend fun updateLocalRecipe(
        recipe: Recipe,
        originImgPathList: List<String>
    ): Result<Unit>
}
