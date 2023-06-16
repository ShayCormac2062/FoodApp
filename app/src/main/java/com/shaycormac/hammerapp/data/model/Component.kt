package com.shaycormac.hammerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val ingredient: Ingredient? = Ingredient(),
)
