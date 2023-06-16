package com.shaycormac.hammerapp.ui.screen.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shaycormac.hammerapp.R
import com.shaycormac.hammerapp.domain.model.Meal
import com.shaycormac.hammerapp.ui.theme.Description
import com.shaycormac.hammerapp.ui.theme.GrayB3
import com.shaycormac.hammerapp.ui.theme.GrayB4
import com.shaycormac.hammerapp.ui.theme.RedB1
import com.shaycormac.hammerapp.ui.theme.RedB2
import com.shaycormac.hammerapp.ui.theme.TabElement
import com.shaycormac.hammerapp.ui.theme.Title

@Composable
fun MenuHeader(
    height: Int,
    onTabClick: (String) -> Unit
) {
    var currentIndex by remember { mutableStateOf(0) }
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CityName()
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qr_code),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = textColor
                )
            }
        }
        Banners(height)
        LazyRow(
            modifier = Modifier
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(queries.size) {
                TabTitle(
                    queries[it],
                    if (it == currentIndex) RedB2 else MaterialTheme.colorScheme.background,
                    if (it == currentIndex) RedB1 else GrayB4,
                ) {
                    currentIndex = it
                    onTabClick.invoke(queries[it])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TabTitle(
    s: String,
    containerColor: Color,
    textColor: Color,
    function: () -> Unit
) {
    Card(
        onClick = { function.invoke() },
        shape = RoundedCornerShape(6.dp),
        backgroundColor = containerColor,
        elevation = 6.dp
    ) {
        Box(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 23.dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = s,
                style = TabElement,
                color = textColor,
            )
        }
    }
}

@Composable
fun Banners(height: Int) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp),
    ) {
        items(2) {
            Image(
                painter = painterResource(id = R.drawable.first_banner),
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(112.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun CityName() {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Москва",
            style = Title,
            color = textColor
        )
        IconButton(
            onClick = {},
            modifier = Modifier.clip(CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.down),
                contentDescription = null,
                modifier = Modifier.width(12.dp),
                tint = textColor
            )
        }
    }
}


@Composable
fun MenuElement(
    meal: Meal
) {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = meal.thumbLink,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(.45f)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxWidth(.9f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = meal.name,
                style = Title,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = getIngredients(meal.ingredients),
                style = Description,
                color = GrayB3,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Box(modifier = Modifier.align(Alignment.End)) {
                Price()
            }
        }
    }
}

private fun getIngredients(ingredients: List<String>): String {
    return ingredients.joinToString(
        separator = ", ",
    )
}

@Composable
fun Price() {
    Box(
        modifier = Modifier
            .border(1.dp, RedB1, RoundedCornerShape(15))
            .padding(vertical = 8.dp, horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "от 345 р",
            style = TabElement,
            color = RedB1,
        )
    }
}
