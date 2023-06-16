package com.shaycormac.hammerapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shaycormac.hammerapp.R

private val SanFrancisco = FontFamily(
    Font(R.font.sf, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Thin),
    Font(R.font.roboto, FontWeight.Bold),
)

val Title: TextStyle = TextStyle(
    fontFamily = SanFrancisco,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 18.75.sp,
)
val Description: TextStyle = TextStyle(
    fontFamily = SanFrancisco,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.71.sp
)
val Menu: TextStyle = TextStyle(
    fontFamily = SanFrancisco,
    fontWeight = FontWeight.Thin,
    fontSize = 12.sp,
    lineHeight = 16.sp,
)
val TabElement: TextStyle = TextStyle(
    fontFamily = SanFrancisco,
    fontWeight = FontWeight.Thin,
    fontSize = 12.sp,
    lineHeight = 16.sp,
)

val Typography = Typography(
    bodyLarge = Title,
    displayMedium = Description,
    headlineSmall = Menu,
    labelSmall = TabElement
)
