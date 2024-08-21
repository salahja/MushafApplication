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
package com.example.mushafconsolidated.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SeekBarPreference
import com.example.mushafconsolidated.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val selectionPreference = findPreference<ListPreference>("selecttranslation")
        if (selectionPreference != null) {
            selectionPreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
                    val selectionOption = newValue as String?
                    val editor =
                        requireContext().getSharedPreferences("properties", 0).edit()
                    editor.putString("selecttranslation", selectionOption)
                    editor.apply()
                    true
                }
        }
        val fetchBar = findPreference<Preference>("pref_font_seekbar_key") as SeekBarPreference?
        if (fetchBar != null) {
            fetchBar.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference: Preference, newValue: Any ->
                    if (newValue is Int) {
                        val newValueInt: Int
                        newValueInt = try {
                            newValue
                        } catch (nfe: NumberFormatException) {
                            Log.e(
                                TAG,
                                "SeekBarPreference is a Integer, but it caused a NumberFormatException"
                            )
                            return@OnPreferenceChangeListener false
                        }
                        preference.context
                        val editor = preference.context
                            .getSharedPreferences(
                                SHARED_PREFERENCE_EDITOR_NAME,
                                Context.MODE_PRIVATE
                            ).edit()
                        editor.putInt(
                            SHARED_PREFERENCE_SEEKBAR_VALUE,
                            newValueInt
                        )
                        editor.apply()
                        return@OnPreferenceChangeListener true
                    } else {
                        val objType = newValue.javaClass.name
                        Log.e(
                            TAG,
                            "SeekBarPreference is not a Integer, it is $objType"
                        )
                        return@OnPreferenceChangeListener false
                    }
                }
        }
        val themePreference = findPreference<ListPreference>("themePref")
        if (themePreference != null) {
            themePreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
                    val themeOption = newValue as String?
                    true
                }
        }
    }

    companion object {
        const val TAG = "SettingsFragmentTag"
        const val SHARED_PREFERENCE_EDITOR_NAME = "properties"
        const val SHARED_PREFERENCE_SEEKBAR_VALUE = "pref_font_seekbar_key"

        // --Commented out by Inspection (16/08/23, 8:04 pm):private Drawable mDivider;
        fun setArabicTextFontSize(paramContext: Context, paramString: String?) {
            val editor = paramContext.getSharedPreferences("properties", 0).edit()
            editor.putString("quran_arabic_font", paramString)
            editor.apply()
        }
    }
}
