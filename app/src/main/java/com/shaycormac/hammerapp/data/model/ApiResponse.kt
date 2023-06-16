package com.shaycormac.hammerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val results: List<Meal> = emptyList()
)
