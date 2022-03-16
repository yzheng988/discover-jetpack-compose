package dev.goobar.hellocompose.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val lightColorPalette = lightColors(
  primary = colorPrimary,
  primaryVariant = colorPrimaryVariant,
  secondary = colorSecondary,
  secondaryVariant = colorSecondaryVariant,
  background = colorBackground,
  onPrimary = colorOnPrimary,
  onSecondary = colorOnSecondary,
  onBackground = colorOnBackground
)