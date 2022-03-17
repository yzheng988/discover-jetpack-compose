package dev.goobar.hellocompose.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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

private val darkColorPalette = darkColors(
  primary = colorPrimaryDark,
  primaryVariant = colorPrimaryVariantDark,
  secondary = colorSecondaryDark,
  secondaryVariant = colorSecondaryVariantDark,
  background = colorBackgroundDark,
  onPrimary = colorOnPrimaryDark,
  onSecondary = colorOnSecondaryDark,
  onBackground = colorOnBackgroundDark
)

private val helloComposeTypography = Typography(
  h3 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 48.sp,
    letterSpacing = 0.sp
  ),
  h4 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 34.sp,
    letterSpacing = 0.25.sp
  ),
  body1 = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    letterSpacing = 0.15.sp
  ),
)

private val helloComposeShapes = Shapes(
  small = helloComposeShapeSmall,
  medium = helloComposeShapeMedium,
  large = helloComposeShapeLarge
)

@Composable
fun HelloComposeTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  MaterialTheme(
    colors = if(isDarkTheme) darkColorPalette else lightColorPalette,
    typography = helloComposeTypography,
    shapes = helloComposeShapes,
    content = content
  )
}