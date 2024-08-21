package com.example.mushafconsolidated.Activityimport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.mushafconsolidated.R
import com.google.android.material.color.DynamicColors

open class BaseActivity : AppCompatActivity() {
    private var currenttheme: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        currenttheme =
            PreferenceManager.getDefaultSharedPreferences(this).getString("themepref", "dark")
        super.onCreate(savedInstanceState)
        DynamicColors.applyToActivitiesIfAvailable(this@BaseActivity.application)
        switchTheme(currenttheme)
    }

    //  protected  void setTheme(){
    //  switchTheme(currenttheme);
    // }
    protected fun switchTheme(currenttheme: String?) {
        when (currenttheme) {
            BaseActivity.Companion.LIGHT_THEME -> setTheme(R.style.AppThemebrow)
            BaseActivity.Companion.DARK_THEME -> setTheme(R.style.Theme_Black)
            BaseActivity.Companion.DARK_BLUE -> setTheme(R.style.AppTheme_materialdarkblue)
            BaseActivity.Companion.DARK_GREEN -> setTheme(R.style.AppTheme_DarkGreen)
            BaseActivity.Companion.BROWN_MODE -> setTheme(R.style.Theme_Browns)
            else -> setTheme(R.style.Theme_Black)
        }
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("theme", currenttheme)
            .apply()
    }

    companion object {
        const val LIGHT_THEME = "light"
        const val DARK_THEME = "dark"
        const val DARK_BLUE = "blue"
        const val DARK_GREEN = "green"
        const val BROWN_MODE = "brown"
    }
}