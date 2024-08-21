package com.example.utility

import android.content.Context
import android.content.SharedPreferences

// quran_arabic_font = sharedPreferences.getString("quran_arabic_font", String.valueOf(Context.MODE_PRIVATE));
//        String arabic_font_selection = sharedPreferences.getString("Arabic_font_selection", String.valueOf(Context.MODE_PRIVATE));
//        String urdu_font_size = sharedPreferences.getString("Urdu_Font_Size", String.valueOf(Context.MODE_PRIVATE));
//        urdu_font_selection = sharedPreferences.getString("Urdu_Font_Selection", String.valueOf(Context.MODE_PRIVATE));
/*

public class PreferenceUtil {
  public static String getArabicFont(Context paramContext) {
    return paramContext.getSharedPreferences("properties", 0).getString("arabicTextFont", Settings.ArabicFont.getDefaultFont().toString());
  }
 */
class PreferenceUtil(var sharedPreferences: SharedPreferences) {
    val arabicTextFonts: String?
        get() = sharedPreferences.getString(
            "Arabic_font_selection",
            Context.MODE_PRIVATE.toString()
        )
    val translation: String?
        get() = sharedPreferences.getString("selecton", Context.MODE_PRIVATE.toString())

    fun getfontsize(): Int {
        return sharedPreferences.getString(
            "pref_font_seek_bar",
            Context.MODE_PRIVATE.toString()
        )!!.toInt()
    }

    val urduTextFontSize: String?
        get() = sharedPreferences.getString("Urdu_Font_Size", Context.MODE_PRIVATE.toString())
    val urduTextFonts: String?
        get() = sharedPreferences.getString("Urdu_Font_Selection", Context.MODE_PRIVATE.toString())

    fun setTranslation(paramContext: Context, paramString: String?) {
        editor = paramContext.getSharedPreferences("properties", 0).edit()
        editor.putString("selection", paramString)
        editor.apply()
    }

    companion object {
        private lateinit var editor: SharedPreferences.Editor
        fun getArabicTextFontSize(paramContext: Context): Int {
            return Integer.valueOf(
                paramContext.getSharedPreferences("properties", 0).getInt("quran_arabic_font", 28)
            )
        }

        fun getUrduTextFontSize(paramContext: Context): Int {
            return Integer.valueOf(
                paramContext.getSharedPreferences("properties", 0).getInt("Urdu_Font_Size", 22)
            )
        }

        fun getThemeMode(paramContext: Context): String? {
            return paramContext.getSharedPreferences("properties", 0)
                .getString("themePref", "default")
            //  return paramContext.getSharedPreferences("themePref", MODE_PRIVATE);
        }

        fun setArabicTextFontSize(paramContext: Context, paramString: String?) {
            editor = paramContext.getSharedPreferences("properties", 0).edit()
            editor.putString("quran_arabic_font", paramString)
            editor.apply()
        }

        fun setUrduTextFontSize(paramContext: Context, paramInteger: Int) {
            val editor = paramContext.getSharedPreferences("properties", 0).edit()
            editor.putInt("Urdu_Font_Size", paramInteger)
            editor.apply()
        }
    }
}