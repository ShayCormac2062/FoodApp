package com.shaycormac.hammerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val components: List<Component>? = emptyList(),
)
