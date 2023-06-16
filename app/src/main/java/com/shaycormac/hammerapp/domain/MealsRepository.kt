package com.shaycormac.hammerapp.domain

import com.shaycormac.hammerapp.domain.model.Meal

interface MealsRepository {

    suspend fun getMealsForQuery(
        from: Int,
        size: Int,
        query: String,
    ): Result<List<Meal>>

}
