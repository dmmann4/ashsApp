package com.example.perfectfit_kpm

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val orange = Color(0xFFFFC067)
val cyan   = Color(0xFF66F4FF)
val blue   = Color(0xFF66C4FF)
val slate    = Color(0xFF7D99AA)

val cream = Color(0xFFF0EAD6)



val LightColors = lightColorScheme(
    primary = slate,
    onPrimary = Color.Black,
    secondary = cyan,
    onSecondary = Color.Black,
    tertiary = blue,
    onTertiary = Color.Black,
    background = cream,
    onBackground = slate,
    surface = Color.White,
    onSurface = orange
)

val DarkColors = darkColorScheme(
    primary = orange,
    onPrimary = Color.Black,
    secondary = cyan,
    onSecondary = Color.Black,
    tertiary = blue,
    onTertiary = Color.Black,
    background = slate,
    onBackground = Color.White,
    surface = slate,
    onSurface = Color.White
)