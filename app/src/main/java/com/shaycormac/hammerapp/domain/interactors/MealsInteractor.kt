package com.shaycormac.hammerapp.domain.interactors

import com.shaycormac.hammerapp.domain.model.Meal

interface MealsInteractor {
    suspend fun getMealsForQuery(
        from: Int = 0,
        size: Int = 25,
        query: String,
    ): Result<List<Meal>>
}
