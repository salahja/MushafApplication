package com.example.mushafconsolidatedimport

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log


class Config constructor() {
    // public String lang;
    var rtl: Boolean = false
    var showTranslation: Boolean = false
    var wordByWord: Boolean = false
    var fullWidth: Boolean = false
    var keepScreenOn: Boolean = false
    var enableAnalytics: Boolean = false
    private var fontArabic: String? = null
    private var fontSizeArabic: String? = null
    var fontSizeTranslation: Int = 0
    var showErab: Boolean = false
    fun load(context: Context?) {
        Log.d("Config", "Load")
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        try {
            loadDefault()
            fontArabic =
                sp.getString(Config.Companion.ARABIC_FONT, Config.Companion.defaultArabicFont)
            fontSizeArabic = sp.getString(
                Config.Companion.FONT_SIZE_ARABIC,
                Config.Companion.defaultFontSizeArabic
            )
            Log.d("Config", "Loading Custom")
        } catch (e: Exception) {
            loadDefault()
            Log.d("Config", "Exception Loading Defaults")
        }
    }

    private fun loadDefault() {
        fontArabic = Config.Companion.defaultArabicFont
        fontSizeArabic = Config.Companion.defaultFontSizeArabic
    }

    /*public void save(Context context) {
        Log.d("Config","Save");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.putString(LANG, lang);
        ed.putBoolean(SHOW_TRANSLATION, showTranslation);
        ed.putBoolean(WORD_BY_WORD, wordByWord);
        ed.putBoolean(KEEP_SCREEN_ON, keepScreenOn);
        ed.putString(FONT_SIZE_ARABIC, "" + fontSizeArabic);
        ed.putString(FONT_SIZE_TRANSLATION, "" + fontSizeTranslation);
        ed.commit();
    }*/
    private fun getStringInt(sp: SharedPreferences, key: String, defValue: Int): Int {
        return sp.getString(key, defValue.toString())!!.toInt()
    } /*  public boolean loadFont() {
      if (loadedFont != Config.fontArabic) {
          String name;
          switch (config.fontArabic) {
              case Config.FONT_NASKH:
                  name = "naskh.otf";
                  break;
              case Config.FONT_NOOREHUDA:
                  name = "noorehuda.ttf";
                  break;
              case Config.FONT_ME_QURAN:
                  name = "me_quran.ttf";
                  break;
              default:
                  name = "qalam.ttf";
          }
          try {
              NativeRenderer.loadFont(getAssets().open(name));
              loadedFont = config.fontArabic;
          } catch (IOException e) {
              e.printStackTrace();
              loadedFont = -1;
              return false;
          }
      }
      return true;
  }*/

    companion object {
        const val FONT_QALAM_MAJEED: Int = 0
        const val FONT_HAFS: Int = 1
        const val FONT_NOOREHUDA: Int = 2
        const val FONT_ME_QURAN: Int = 3
        const val FONT_MAX: Int = 3
        const val LANG: String = "lang"
        const val LANG_BN: String = "bn"
        const val LANG_EN: String = "en"
        const val LANG_INDO: String = "indo"
        const val SHOW_TRANSLATION: String = "showTranslation"
        const val SHOW_Erab: String = "showErab"
        const val WORD_BY_WORD: String = "wordByWord"
        const val KEEP_SCREEN_ON: String = "keepScreenOn"
        const val ARABIC_FONT: String = "arabicFont"
        const val FONT_SIZE_ARABIC: String = "fontSizeArabic"
        const val FONT_SIZE_TRANSLATION: String = "fontSizeTranslation"
        const val FONT_SIZE_ERAB: String = "fontSizeErab"
        const val FIRST_RUN: String = "firstRun"
        const val DATABASE_VERSION: String = "dbVersion"
        const val defaultLang: String = "en"
        const val defaultShowTranslation: Boolean = true
        const val defaultShowErab: Boolean = true
        const val defaultWordByWord: Boolean = true
        const val defaultKeepScreenOn: Boolean = true
        const val defaultArabicFont: String = "Uthmani.oft"
        const val defaultFontSizeArabic: String = "20"
        const val defaultFontSizeTranslation: String = "14"
        const val defaultFontSizeErab: String = "14"
    }
}