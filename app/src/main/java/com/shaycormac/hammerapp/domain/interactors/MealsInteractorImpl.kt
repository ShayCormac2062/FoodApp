package com.shaycormac.hammerapp.domain.interactors

import com.shaycormac.hammerapp.domain.MealsRepository
import com.shaycormac.hammerapp.domain.model.Meal
import javax.inject.Inject

class MealsInteractorImpl @Inject constructor(
    private val mealsRepository: MealsRepository,
) : MealsInteractor {
    override suspend fun getMealsForQuery(from: Int, size: Int, query: String): Result<List<Meal>> =
        mealsRepository.getMealsForQuery(from = from, size = size, query = query)
}
