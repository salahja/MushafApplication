package org.sj.conjugator.utilities

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPref(var sharedContext: Context) {
    init {
        //  this.sharedPreferences = sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            sharedContext
        )
    }

    companion object {
        lateinit var sharedPreferences: SharedPreferences
        fun arabicFontsize(): Int {
            return sharedPreferences.getString(
                "Arabic_Font_Size",
                Context.MODE_PRIVATE.toString()
            )!!.toInt()
        }

        fun arabicFontSelection(): String? {
            return sharedPreferences.getString(
                "arabic_font_category",
                Context.MODE_PRIVATE.toString()
            )
        }

        val language: String?
            get() = sharedPreferences.getString("lang", Context.MODE_PRIVATE.toString())

        fun themePreferences(): String? {
            return sharedPreferences.getString("themePref", Context.MODE_PRIVATE.toString())
        }

        fun engishFontsize(): Int {
            return sharedPreferences.getString(
                "English_Font_Size",
                Context.MODE_PRIVATE.toString()
            )!!.toInt()
        }

        fun englishFontSelection(): String? {
            return sharedPreferences.getString(
                "English_Font_Selection",
                Context.MODE_PRIVATE.toString()
            )
        }

        fun quranText(): String? {
            return sharedPreferences.getString("qurantext", Context.MODE_PRIVATE.toString())
        }

        fun showTranslation(): Boolean {
            return sharedPreferences.getBoolean("showTranslationKey", true)
        }

        fun AutoComplete(): Boolean {
            return sharedPreferences.getBoolean("autocomplete", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun Nominative(): Boolean {
            return sharedPreferences.getBoolean("Accusative", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun Accusative(): Boolean {
            return sharedPreferences.getBoolean("Nominative", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun Jussive(): Boolean {
            return sharedPreferences.getBoolean("Jussive", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun Comparative(): Boolean {
            return sharedPreferences.getBoolean("All", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun Empathetic(): Boolean {
            return sharedPreferences.getBoolean("Empathetic", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun GetSarfKabeerVerb(): Boolean {
            return sharedPreferences.getBoolean("sarfkabeer_format_verb", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun SarfKabeerOthers(): Boolean {
            return sharedPreferences.getBoolean("sarfkabeer_format_participles", false)
            //  boolean isChecked = sharedPreferences.getBoolean("switch", false);
        }

        fun showErab(): Boolean {
            return sharedPreferences.getBoolean("showErabKey", true)
        }
    }
}