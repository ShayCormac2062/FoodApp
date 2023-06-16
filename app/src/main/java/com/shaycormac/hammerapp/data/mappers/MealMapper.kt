package com.shaycormac.hammerapp.data.mappers

import com.shaycormac.hammerapp.data.model.Component
import com.shaycormac.hammerapp.data.model.Meal
import com.shaycormac.hammerapp.data.model.Section
import com.shaycormac.hammerapp.domain.model.Meal as MealDomain

object MealMapper {
    fun Meal.toDomain() = MealDomain(
        id = id,
        name = name ?: "",
        thumbLink = thumbnailUrl ?: "",
        description = description ?: "",
        ingredients = sections?.let { mapIngredients(it) } ?: emptyList()
    )

    private fun mapIngredients(sections: List<Section>): List<String> {
        val allComponents = mutableListOf<Component>()
        sections.forEach { section ->
            section.components?.let { components ->
                allComponents.addAll(
                    components
                )
            }
        }
        return allComponents.mapNotNull { it.ingredient?.name }
    }
}
