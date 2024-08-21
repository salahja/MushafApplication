package org.sj.conjugator.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.utilities.ThemeHelper.applyTheme

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
 */   class SettingsFragmentVerb : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.verbpreferences, rootKey)
        val themePreference = findPreference<ListPreference>("themePref")
        if (themePreference != null) {
            themePreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    val themeOption = newValue as String
                    applyTheme(themeOption)
                    true
                }
        }
        val selectionPreference = findPreference<Preference>("Accusative")
        if (selectionPreference != null) {
            selectionPreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    val selectionOption = newValue as Boolean
                    val editor =
                        PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
                            .edit()
                    //   SharedPreferences.Editor editor =  getContext().getSharedPreferences("properties", 0).edit();
                    editor.putBoolean("Accusative", selectionOption)
                    editor.putBoolean("Nominative", false)
                    editor.apply()
                    true
                }
        }
    }

    companion object {
        const val TAG = "SettingsFragmentTag"
    }
}