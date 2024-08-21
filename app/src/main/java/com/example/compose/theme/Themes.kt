package com.example.compose.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.skyyo.expandablelist.theme.DarkColors
import com.skyyo.expandablelist.theme.LightColors

@Composable
fun ComposeSettingsTheme(
    darkThemePreference: Boolean,
    dynamicThemePreference: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (Build.VERSION.SDK_INT >= 31 && dynamicThemePreference) {
            if (darkThemePreference) {
                dynamicDarkColorScheme(LocalContext.current)
            } else {
                dynamicLightColorScheme(LocalContext.current)
            }
        } else {
            if (darkThemePreference) {
                //   DarkColorsNew
                DarkColors
            } else {
                //   LightColorsNew
                LightColors
            }
        },
        content = content,
    )
}
