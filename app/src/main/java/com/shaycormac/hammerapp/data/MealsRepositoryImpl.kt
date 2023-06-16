package com.shaycormac.hammerapp.data

import com.shaycormac.hammerapp.domain.MealsRepository
import com.shaycormac.hammerapp.domain.model.Meal
import com.shaycormac.hammerapp.data.api.MealsApi
import com.shaycormac.hammerapp.data.mappers.MealMapper.toDomain
import com.shaycormac.hammerapp.di.qualifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val api: MealsApi,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : MealsRepository {

    override suspend fun getMealsForQuery(
        from: Int,
        size: Int,
        query: String
    ): Result<List<Meal>> =
        withContext(coroutineDispatcher) {
            try {
                val response = api.getMeals(from = from, size = size, query = query)
                response.body()?.results?.map { it.toDomain() }
                    ?.let { Result.success(it) }
                    ?: Result.failure(Error("Sorry, check your internet connection and try again"))
            } catch (ex: Exception) {
                Result.failure(Error(ex.message))
            }
        }
}
