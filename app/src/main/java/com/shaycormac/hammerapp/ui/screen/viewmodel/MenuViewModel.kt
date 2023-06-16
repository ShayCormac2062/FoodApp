package com.shaycormac.hammerapp.ui.screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaycormac.hammerapp.domain.interactors.MealsInteractor
import com.shaycormac.hammerapp.domain.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val mealsInteractor: MealsInteractor
) : ViewModel() {

    private var _meals: MutableStateFlow<Result<List<Meal>>?> = MutableStateFlow(null)
    val meals: StateFlow<Result<List<Meal>>?> = _meals

    init {
        getMeals("Pizza")
    }

    fun getMeals(query: String) = viewModelScope.launch {
        _meals.value = null
        mealsInteractor
            .getMealsForQuery(query = query)
            .fold(
                onSuccess = { meals ->
                    _meals.value = Result.success(meals)
                    Log.e("RESULT", _meals.value.toString())
                },
                onFailure = {
                    _meals.value = Result.failure(it)
                }
            )
    }
}
