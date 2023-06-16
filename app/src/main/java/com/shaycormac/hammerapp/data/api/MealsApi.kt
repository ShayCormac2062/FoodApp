package com.shaycormac.hammerapp.data.api

import com.shaycormac.hammerapp.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("/recipes/list?")
    suspend fun getMeals(
        @Query("from") from: Int,
        @Query("size") size: Int,
        @Query("q") query:String
    ): Response<ApiResponse>
}
