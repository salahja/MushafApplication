package com.example.mushafconsolidated.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SeekBarPreference
import com.example.Constant
import com.example.Constant.SURAHFRAGTAG
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.BottomOptionDialog
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.fragments.FontQuranListDialogFragment
import com.example.mushafconsolidated.fragments.ThemeListPrefrence
import com.example.mushafconsolidated.fragments.TranslationListPrefrence
import com.example.mushafconsolidated.fragments.WbwTranslationListPrefrence
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet


class ActivitySettings : BaseActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    // RelativeLayout layoutBottomSheet;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        //    backa=findViewById(R.id.back);
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, HeaderFragment())
                .commit()
        } else {
            title = savedInstanceState.getCharSequence(TITLE_TAG)
        }
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, title)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments: MutableList<android.app.Fragment>? = fragmentManager.fragments
        val activeFragment = fragments!!?.get(fragments.size - 1)

        val lastOrNull = this.supportFragmentManager.fragments.lastOrNull()

        if (lastOrNull != null) {
            val findFragmentByTag = lastOrNull.childFragmentManager.findFragmentByTag(SURAHFRAGTAG)
            println(findFragmentByTag)
        }

        val readingintent = intent
        finish()
        startActivity(readingintent)
    }

    override fun getIntent(): Intent {
        val pref = applicationContext.getSharedPreferences("lastread", MODE_PRIVATE)

        var surahname = pref.getString(Constant.SURAH_ARABIC_NAME, "")
        //  var surahArabicName = surahname.toString()


        val readingintent = Intent(this, QuranGrammarAct::class.java)

        readingintent.putExtra(Constant.SURAH_ID, pref.getInt(Constant.SURAH_ID, 1))
        readingintent.putExtra(Constant.AYAH_ID, pref.getInt(Constant.AYAH_ID, 1))
        readingintent.putExtra(Constant.SURAH_ARABIC_NAME, surahname)
        return readingintent
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (supportFragmentManager.popBackStackImmediate()) {
            true
        } else super.onSupportNavigateUp()
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        if (pref.key == "wbw") {
            val item = WbwTranslationListPrefrence()
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            val fragmentManager = this@ActivitySettings.supportFragmentManager
            @SuppressLint("CommitTransaction") val transactions = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
            transactions.show(item)
            WbwTranslationListPrefrence.newInstance()
                .show(this@ActivitySettings.supportFragmentManager, WordAnalysisBottomSheet.TAG)
            title = pref.title
        }
        if (pref.key == "selecttranslation") {
            val item = TranslationListPrefrence()
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            val fragmentManager = this@ActivitySettings.supportFragmentManager
            @SuppressLint("CommitTransaction") val transactions = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
            transactions.show(item)
            TranslationListPrefrence.newInstance()
                .show(this@ActivitySettings.supportFragmentManager, WordAnalysisBottomSheet.TAG)
            title = pref.title
        } else if (pref.key == "themepref") {
            val item = ThemeListPrefrence()
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            val fragmentManager = this@ActivitySettings.supportFragmentManager
            @SuppressLint("CommitTransaction") val transactions = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
            transactions.show(item)
            ThemeListPrefrence.newInstance()
                .show(this@ActivitySettings.supportFragmentManager, WordAnalysisBottomSheet.TAG)
            title = pref.title
        } else if (pref.key == "Arabic_Font_Selection") {
            // Instantiate the new Fragment
            val item = FontQuranListDialogFragment()
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            val fragmentManager = this@ActivitySettings.supportFragmentManager
            @SuppressLint("CommitTransaction") val transactions = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
            transactions.show(item)
            FontQuranListDialogFragment.newInstance()
                .show(this@ActivitySettings.supportFragmentManager, WordAnalysisBottomSheet.TAG)
            title = pref.title
        } else if (pref.key == "Exit") {
            val readingintent = intent
            finish()
            startActivity(readingintent)
        }
        return true
    }

    class HeaderFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
            val button = preferenceManager.findPreference("exitlink") as Preference?
            if (button != null) {
                button.onPreferenceClickListener =
                    Preference.OnPreferenceClickListener { arg0: Preference? ->
                        val intents = Intent(
                            activity,
                            QuranGrammarAct::class.java
                        )
                        //  finish();
                        startActivity(intents)
                        true
                    }
            }
            val fetchBar = findPreference<Preference>("pref_seekbar_key") as SeekBarPreference?
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
                            val editor = preference.context.getSharedPreferences(
                                SHARED_PREFERENCE_EDITOR_NAME,
                                MODE_PRIVATE
                            ).edit()
                            editor.putInt(SHARED_PREFERENCE_SEEKBAR_VALUE, newValueInt)
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
            val themePreference = findPreference<ListPreference>("theme")
            if (themePreference != null) {
                themePreference.onPreferenceChangeListener =
                    Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? -> true }
            }
        }
    }

    inner class SelectQuranFronts : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.website_preferences, rootKey)
            val item = BottomOptionDialog()
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            val fragmentManager = this@ActivitySettings.supportFragmentManager
            @SuppressLint("CommitTransaction") val transactions = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
            transactions.show(item)
            //     WordAnalysisBottomSheet.newInstance(data).show(ActivitySettings.this.getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);
        }
    }

    companion object {
        const val SHARED_PREFERENCE_EDITOR_NAME = "shared_pref_name"
        const val SHARED_PREFERENCE_SEEKBAR_VALUE = "seek_value"
        private val TAG = ActivitySettings::class.java.simpleName
        private const val TITLE_TAG = "settingsActivityTitle"
    }
}
