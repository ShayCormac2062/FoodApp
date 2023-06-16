package com.shaycormac.hammerapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val description: String? = "",
    val id: Int,
    val name: String? = "",
    val sections: List<Section>? = emptyList(),
    @SerialName("thumbnail_url") val thumbnailUrl: String? = ""
)
