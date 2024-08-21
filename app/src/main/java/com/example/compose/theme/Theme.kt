package com.skyyo.expandablelist.theme


import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.codelab.basics.ui.theme.brownmd_theme_light_background
import com.codelab.basics.ui.theme.brownmd_theme_light_error
import com.codelab.basics.ui.theme.brownmd_theme_light_errorContainer
import com.codelab.basics.ui.theme.brownmd_theme_light_inverseOnSurface
import com.codelab.basics.ui.theme.brownmd_theme_light_inversePrimary
import com.codelab.basics.ui.theme.brownmd_theme_light_inverseSurface
import com.codelab.basics.ui.theme.brownmd_theme_light_onBackground
import com.codelab.basics.ui.theme.brownmd_theme_light_onError
import com.codelab.basics.ui.theme.brownmd_theme_light_onErrorContainer
import com.codelab.basics.ui.theme.brownmd_theme_light_onPrimary
import com.codelab.basics.ui.theme.brownmd_theme_light_onPrimaryContainer
import com.codelab.basics.ui.theme.brownmd_theme_light_onSecondary
import com.codelab.basics.ui.theme.brownmd_theme_light_onSecondaryContainer
import com.codelab.basics.ui.theme.brownmd_theme_light_onSurface
import com.codelab.basics.ui.theme.brownmd_theme_light_onSurfaceVariant
import com.codelab.basics.ui.theme.brownmd_theme_light_onTertiary
import com.codelab.basics.ui.theme.brownmd_theme_light_onTertiaryContainer
import com.codelab.basics.ui.theme.brownmd_theme_light_outline
import com.codelab.basics.ui.theme.brownmd_theme_light_outlineVariant
import com.codelab.basics.ui.theme.brownmd_theme_light_primary
import com.codelab.basics.ui.theme.brownmd_theme_light_scrim
import com.codelab.basics.ui.theme.brownmd_theme_light_secondary
import com.codelab.basics.ui.theme.brownmd_theme_light_secondaryContainer
import com.codelab.basics.ui.theme.brownmd_theme_light_surface
import com.codelab.basics.ui.theme.brownmd_theme_light_surfaceTint
import com.codelab.basics.ui.theme.brownmd_theme_light_surfaceVariant
import com.codelab.basics.ui.theme.brownmd_theme_light_tertiary
import com.codelab.basics.ui.theme.brownmd_theme_light_tertiaryContainer
import com.codelab.basics.ui.theme.md_theme_dark_background
import com.codelab.basics.ui.theme.md_theme_dark_error
import com.codelab.basics.ui.theme.md_theme_dark_errorContainer
import com.codelab.basics.ui.theme.md_theme_dark_inverseOnSurface
import com.codelab.basics.ui.theme.md_theme_dark_inversePrimary
import com.codelab.basics.ui.theme.md_theme_dark_inverseSurface
import com.codelab.basics.ui.theme.md_theme_dark_onBackground
import com.codelab.basics.ui.theme.md_theme_dark_onError
import com.codelab.basics.ui.theme.md_theme_dark_onErrorContainer
import com.codelab.basics.ui.theme.md_theme_dark_onPrimary
import com.codelab.basics.ui.theme.md_theme_dark_onPrimaryContainer
import com.codelab.basics.ui.theme.md_theme_dark_onSecondary
import com.codelab.basics.ui.theme.md_theme_dark_onSecondaryContainer
import com.codelab.basics.ui.theme.md_theme_dark_onSurface
import com.codelab.basics.ui.theme.md_theme_dark_onSurfaceVariant
import com.codelab.basics.ui.theme.md_theme_dark_onTertiary
import com.codelab.basics.ui.theme.md_theme_dark_onTertiaryContainer
import com.codelab.basics.ui.theme.md_theme_dark_outline
import com.codelab.basics.ui.theme.md_theme_dark_outlineVariant
import com.codelab.basics.ui.theme.md_theme_dark_primary
import com.codelab.basics.ui.theme.md_theme_dark_primaryContainer
import com.codelab.basics.ui.theme.md_theme_dark_scrim
import com.codelab.basics.ui.theme.md_theme_dark_secondary
import com.codelab.basics.ui.theme.md_theme_dark_secondaryContainer
import com.codelab.basics.ui.theme.md_theme_dark_surface
import com.codelab.basics.ui.theme.md_theme_dark_surfaceTint
import com.codelab.basics.ui.theme.md_theme_dark_surfaceVariant
import com.codelab.basics.ui.theme.md_theme_dark_tertiary
import com.codelab.basics.ui.theme.md_theme_dark_tertiaryContainer
import com.codelab.basics.ui.theme.md_theme_light_background
import com.codelab.basics.ui.theme.md_theme_light_error
import com.codelab.basics.ui.theme.md_theme_light_errorContainer
import com.codelab.basics.ui.theme.md_theme_light_inverseOnSurface
import com.codelab.basics.ui.theme.md_theme_light_inversePrimary
import com.codelab.basics.ui.theme.md_theme_light_inverseSurface
import com.codelab.basics.ui.theme.md_theme_light_onBackground
import com.codelab.basics.ui.theme.md_theme_light_onError
import com.codelab.basics.ui.theme.md_theme_light_onErrorContainer
import com.codelab.basics.ui.theme.md_theme_light_onPrimary
import com.codelab.basics.ui.theme.md_theme_light_onPrimaryContainer
import com.codelab.basics.ui.theme.md_theme_light_onSecondary
import com.codelab.basics.ui.theme.md_theme_light_onSecondaryContainer
import com.codelab.basics.ui.theme.md_theme_light_onSurface
import com.codelab.basics.ui.theme.md_theme_light_onSurfaceVariant
import com.codelab.basics.ui.theme.md_theme_light_onTertiary
import com.codelab.basics.ui.theme.md_theme_light_onTertiaryContainer
import com.codelab.basics.ui.theme.md_theme_light_outline
import com.codelab.basics.ui.theme.md_theme_light_outlineVariant
import com.codelab.basics.ui.theme.md_theme_light_primary
import com.codelab.basics.ui.theme.md_theme_light_primaryContainer
import com.codelab.basics.ui.theme.md_theme_light_scrim
import com.codelab.basics.ui.theme.md_theme_light_secondary
import com.codelab.basics.ui.theme.md_theme_light_secondaryContainer
import com.codelab.basics.ui.theme.md_theme_light_surface
import com.codelab.basics.ui.theme.md_theme_light_surfaceTint
import com.codelab.basics.ui.theme.md_theme_light_surfaceVariant
import com.codelab.basics.ui.theme.md_theme_light_tertiary
import com.codelab.basics.ui.theme.md_theme_light_tertiaryContainer


val LightColors = lightColorScheme(
    primary = brownmd_theme_light_primary,
    onPrimary = brownmd_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = brownmd_theme_light_onPrimaryContainer,
    secondary = brownmd_theme_light_secondary,
    onSecondary = brownmd_theme_light_onSecondary,
    secondaryContainer = brownmd_theme_light_secondaryContainer,
    onSecondaryContainer = brownmd_theme_light_onSecondaryContainer,
    tertiary = brownmd_theme_light_tertiary,
    onTertiary = brownmd_theme_light_onTertiary,
    tertiaryContainer = brownmd_theme_light_tertiaryContainer,
    onTertiaryContainer = brownmd_theme_light_onTertiaryContainer,
    error = brownmd_theme_light_error,
    errorContainer = brownmd_theme_light_errorContainer,
    onError = brownmd_theme_light_onError,
    onErrorContainer = brownmd_theme_light_onErrorContainer,
    background = brownmd_theme_light_background,
    onBackground = brownmd_theme_light_onBackground,
    surface = brownmd_theme_light_surface,
    onSurface = brownmd_theme_light_onSurface,
    surfaceVariant = brownmd_theme_light_surfaceVariant,
    onSurfaceVariant = brownmd_theme_light_onSurfaceVariant,
    outline = brownmd_theme_light_outline,
    inverseOnSurface = brownmd_theme_light_inverseOnSurface,
    inverseSurface = brownmd_theme_light_inverseSurface,
    inversePrimary = brownmd_theme_light_inversePrimary,
    surfaceTint = brownmd_theme_light_surfaceTint,
    outlineVariant = brownmd_theme_light_outlineVariant,
    scrim = brownmd_theme_light_scrim,
)
/*

val DarkColors = darkColorScheme(
    primary = brownmd_theme_dark_primary,
    onPrimary = brownmd_theme_dark_onPrimary,
    primaryContainer = brownmd_theme_dark_primaryContainer,
    onPrimaryContainer = brownmd_theme_dark_onPrimaryContainer,
    secondary = brownmd_theme_dark_secondary,
    onSecondary = brownmd_theme_dark_onSecondary,
    secondaryContainer = brownmd_theme_dark_secondaryContainer,
    onSecondaryContainer = brownmd_theme_dark_onSecondaryContainer,
    tertiary = brownmd_theme_dark_tertiary,
    onTertiary = brownmd_theme_dark_onTertiary,
    tertiaryContainer = brownmd_theme_dark_tertiaryContainer,
    onTertiaryContainer = brownmd_theme_dark_onTertiaryContainer,
    error = brownmd_theme_dark_error,
    errorContainer = brownmd_theme_dark_errorContainer,
    onError = brownmd_theme_dark_onError,
    onErrorContainer = brownmd_theme_dark_onErrorContainer,
    background = brownmd_theme_dark_background,
    onBackground = brownmd_theme_dark_onBackground,
    surface = brownmd_theme_dark_surface,
    onSurface = brownmd_theme_dark_onSurface,
    surfaceVariant = brownmd_theme_dark_surfaceVariant,
    onSurfaceVariant = brownmd_theme_dark_onSurfaceVariant,
    outline = brownmd_theme_dark_outline,
    inverseOnSurface = brownmd_theme_dark_inverseOnSurface,
    inverseSurface = brownmd_theme_dark_inverseSurface,
    inversePrimary = brownmd_theme_dark_inversePrimary,
    surfaceTint = brownmd_theme_dark_surfaceTint,
    outlineVariant = brownmd_theme_dark_outlineVariant,
    scrim = brownmd_theme_dark_scrim,
)



val LightThemeColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


val DarkThemeColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)


 val LightColorsNew = lightColorScheme(
    primary = mdnew_theme_light_primary,
    onPrimary = mdnew_theme_light_onPrimary,
    primaryContainer = mdnew_theme_light_primaryContainer,
    onPrimaryContainer = mdnew_theme_light_onPrimaryContainer,
    secondary = mdnew_theme_light_secondary,
    onSecondary = mdnew_theme_light_onSecondary,
    secondaryContainer = mdnew_theme_light_secondaryContainer,
    onSecondaryContainer = mdnew_theme_light_onSecondaryContainer,
    tertiary = mdnew_theme_light_tertiary,
    onTertiary = mdnew_theme_light_onTertiary,
    tertiaryContainer = mdnew_theme_light_tertiaryContainer,
    onTertiaryContainer = mdnew_theme_light_onTertiaryContainer,
    error = mdnew_theme_light_error,
    errorContainer = mdnew_theme_light_errorContainer,
    onError = mdnew_theme_light_onError,
    onErrorContainer = mdnew_theme_light_onErrorContainer,
    background = mdnew_theme_light_background,
    onBackground = mdnew_theme_light_onBackground,
    surface = mdnew_theme_light_surface,
    onSurface = mdnew_theme_light_onSurface,
    surfaceVariant = mdnew_theme_light_surfaceVariant,
    onSurfaceVariant = mdnew_theme_light_onSurfaceVariant,
    outline = mdnew_theme_light_outline,
    inverseOnSurface = mdnew_theme_light_inverseOnSurface,
    inverseSurface = mdnew_theme_light_inverseSurface,
    inversePrimary = mdnew_theme_light_inversePrimary,
    surfaceTint = mdnew_theme_light_surfaceTint,
    outlineVariant = mdnew_theme_light_outlineVariant,
    scrim = mdnew_theme_light_scrim,
)


 val DarkColorsNew = darkColorScheme(
    primary = mdnew_theme_dark_primary,
    onPrimary = mdnew_theme_dark_onPrimary,
    primaryContainer = mdnew_theme_dark_primaryContainer,
    onPrimaryContainer = mdnew_theme_dark_onPrimaryContainer,
    secondary = mdnew_theme_dark_secondary,
    onSecondary = mdnew_theme_dark_onSecondary,
    secondaryContainer = mdnew_theme_dark_secondaryContainer,
    onSecondaryContainer = mdnew_theme_dark_onSecondaryContainer,
    tertiary = mdnew_theme_dark_tertiary,
    onTertiary = mdnew_theme_dark_onTertiary,
    tertiaryContainer = mdnew_theme_dark_tertiaryContainer,
    onTertiaryContainer = mdnew_theme_dark_onTertiaryContainer,
    error = mdnew_theme_dark_error,
    errorContainer = mdnew_theme_dark_errorContainer,
    onError = mdnew_theme_dark_onError,
    onErrorContainer = mdnew_theme_dark_onErrorContainer,
    background = mdnew_theme_dark_background,
    onBackground = mdnew_theme_dark_onBackground,
    surface = mdnew_theme_dark_surface,
    onSurface = mdnew_theme_dark_onSurface,
    surfaceVariant = mdnew_theme_dark_surfaceVariant,
    onSurfaceVariant = mdnew_theme_dark_onSurfaceVariant,
    outline = mdnew_theme_dark_outline,
    inverseOnSurface = mdnew_theme_dark_inverseOnSurface,
    inverseSurface = mdnew_theme_dark_inverseSurface,
    inversePrimary = mdnew_theme_dark_inversePrimary,
    surfaceTint = mdnew_theme_dark_surfaceTint,
    outlineVariant = mdnew_theme_dark_outlineVariant,
    scrim = mdnew_theme_dark_scrim,
)


*/


/*
@Composable
fun JetRedditTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (JetRedditThemeSettings.isInDarkTheme.value) DarkThemeColors else LightThemeColors,
        content = content
    )
}


object JetRedditThemeSettings {
    var isInDarkTheme: MutableState<Boolean> = mutableStateOf(false)
}

*/


/*
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val isDarkThemeEnabled = isSystemInDarkTheme() || AppThemeSettings.isDarkThemeEnabled
    val colors = if (isDarkThemeEnabled) DarkThemeColors else LightThemeColors

    MaterialTheme(colorScheme = colors, content = content)
}

*/
/**
 * Allows changing between light and a dark theme from the app's settings.
 *//*

object AppThemeSettings {
    var isDarkThemeEnabled by mutableStateOf(false)
}

*/


val LightColorss = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)


@Composable
fun AppThemes(content: @Composable () -> Unit) {
    val isDarkThemeEnabled = isSystemInDarkTheme() || AppThemeSettings.isDarkThemeEnabled
    val colors = if (isDarkThemeEnabled) DarkColors else LightColors

    MaterialTheme(colorScheme = colors, content = content)
}

/**
 * Allows changing between light and a dark theme from the app's settings.
 */
object AppThemeSettings {
    var isDarkThemeEnabled by mutableStateOf(true)
}


@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (useDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        useDarkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
