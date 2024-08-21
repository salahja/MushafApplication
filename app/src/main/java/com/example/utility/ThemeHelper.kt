/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.utility

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.mushafconsolidated.R

object ThemeHelper {
    private const val LIGHT_THEME = "light"
    private const val DARK_THEME = "dark"

    // private static int sCurrentTheme = LIGHT_THEME;
    private const val DARK_BLUE = "blue"
    private const val GREEN_MODE = "green"
    private const val BROWN_MODE = "brown"

    //   public static final String LIGHT_MODE = "light";
    //  public static final String DARK_MODE = "dark";
    //  public static final String DARK_BLUE = "blue";
    // public static final String GREEN_MODE = "green";
    // public static final String DEFAULT_MODE = "default";
    // public static final String BROWN_MODE="greem";
    fun applyTheme(themePref: String) {
        when (themePref) {
            LIGHT_THEME -> {

                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                QuranGrammarApplication.context!!.setTheme(R.style.AppTheme)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            DARK_THEME -> {

                // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                QuranGrammarApplication.context!!.setTheme(R.style.Theme_Black)
            }

            DARK_BLUE -> {
                QuranGrammarApplication.context!!.setTheme(R.style.AppTheme_materialdarkblue)
            }

            BROWN_MODE -> {
                QuranGrammarApplication.context!!.setTheme(R.style.AppTheme_DarkGreen)
            }

            GREEN_MODE -> {
                QuranGrammarApplication.context!!.setTheme(R.style.Theme_Browns)
            }

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
}