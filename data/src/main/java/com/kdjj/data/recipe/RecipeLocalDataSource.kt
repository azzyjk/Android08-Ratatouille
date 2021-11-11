package com.kdjj.data.recipe

import com.kdjj.domain.model.Recipe

interface RecipeLocalDataSource {
	
	suspend fun saveRecipe(recipe: Recipe): Result<Boolean>
	
	suspend fun updateRecipe(recipe: Recipe): Result<Boolean>
}
