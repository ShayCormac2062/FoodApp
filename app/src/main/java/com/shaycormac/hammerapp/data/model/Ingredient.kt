package com.shaycormac.hammerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val name: String? = "",
)
