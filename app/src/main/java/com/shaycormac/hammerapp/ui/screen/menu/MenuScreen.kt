package com.shaycormac.hammerapp.ui.screen.menu

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.shaycormac.hammerapp.domain.model.Meal
import com.shaycormac.hammerapp.ui.screen.viewmodel.MenuViewModel
import com.shaycormac.hammerapp.ui.theme.Title

val queries = listOf("Pizza", "Pasta", "Dessert", "Fish", "Fruits", "Salad")

@Composable
fun MenuScreen(
    menuViewModel: MenuViewModel
) {
    val menu by menuViewModel.meals.collectAsState()
    var height by remember { mutableStateOf(112) }
    val _height by animateIntAsState(targetValue = height)
    val listState = rememberLazyListState()
    val showBanners by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuHeader(
            height = _height
        ) {
            menuViewModel.getMeals(it)
        }
        menu?.let {
            if (it.isSuccess) {
                MenuList(
                    list = it.getOrNull(),
                    listState = listState,
                )
            } else {
                ErrorMessage(
                    exception = it.exceptionOrNull()
                )
            }
        } ?: ProgressBar()
    }
    LaunchedEffect(key1 = showBanners) {
        height = if (showBanners) 0 else 112
    }
}

@Composable
fun ErrorMessage(exception: Throwable?) {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = exception?.message.toString(),
            style = Title,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(.9f)
        )
    }
}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun MenuList(
    list: List<Meal>?,
    listState: LazyListState
) {
    list?.let {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(it.size) { item ->
                MenuElement(it[item])
            }
        }
    }
}
