package com.example.mushafconsolidated.receiversimport

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.mushafconsolidated.R.id

import com.example.utility.QuranGrammarApplication


object AppPreference {
    /**
     * Open configuration file
     *
     * @return SharedPreferences object
     */
    private fun OpenConfigPreferences(): SharedPreferences? {
        return try {
            QuranGrammarApplication.instance?.getSharedPreferences(
                AudioAppConstants.Preferences.Companion.CONFIG,
                Context.MODE_PRIVATE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Function to set download statue
     *
     * @param flag in download or not
     */
    fun Downloading(flag: Boolean) {
        val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
        editor.putBoolean(AudioAppConstants.Preferences.Companion.DOWNLOAD_STATUS, flag)
        editor.apply()
    }

    /**
     * Function to set after download statue
     *
     * @param statues Download statue
     */
    fun DownloadStatues(statues: Int) {
        val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
        editor.putInt(AudioAppConstants.Preferences.Companion.DOWNLOAD_STATUS_TEXT, statues)
        editor.apply()
    }

    /**
     * Function to set application language arabic
     *
     * @param isArabic flag of arabic or not
     */
    fun setApplicationLanguage(isArabic: Boolean) {
        val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
        editor.putBoolean(AudioAppConstants.Preferences.Companion.LANGUAGE, isArabic)
    }

    /**
     * Function to get download states
     *
     * @return Download running or not
     */
    val isDownloading: Boolean
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val isDownloading: Boolean = preferences!!.getBoolean(
                AudioAppConstants.Preferences.Companion.DOWNLOAD_STATUS,
                false
            )
            return isDownloading
        }

    /**
     * Function get after download statues
     *
     * @return After download statue
     */
    val downloadStatues: Int
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val downloadStatues: Int =
                preferences!!.getInt(
                    AudioAppConstants.Preferences.Companion.DOWNLOAD_STATUS_TEXT,
                    -1
                )
            return downloadStatues
        }
    /**
     * Function to get last page read number
     *
     * @return Last page read number
     */
    /**
     * Function to save last read page
     *
     * @param pageNumber Last Page read number
     */
    var lastPageRead: Int
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val pageNumber: Int =
                preferences!!.getInt(AudioAppConstants.Preferences.Companion.LAST_PAGE_NUMBER, -1)
            return pageNumber
        }
        set(pageNumber) {
            val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
            editor.putInt(AudioAppConstants.Preferences.Companion.LAST_PAGE_NUMBER, pageNumber)
            editor.apply()
        }
    /**
     * Function to get screen resolution
     *
     * @return
     */
    /**
     * Function to save screen resolution
     *
     * @param resolution Screen max resolution
     */
    var screenResolution: Int
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val resolution: Int =
                preferences!!.getInt(AudioAppConstants.Preferences.Companion.SCREEN_RESOLUTION, -1)
            return resolution
        }
        set(resolution) {
            val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
            editor.putInt(AudioAppConstants.Preferences.Companion.SCREEN_RESOLUTION, resolution)
            editor.apply()
        }
    /**
     * Function to get download type of download
     *
     * @return download type tafseer , voice , images or voiceDatabases
     */
    /**
     * Function to set witch type of download you do
     *
     * @param type integer refer to download type
     */
    var downloadType: Int
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val type: Int =
                preferences!!.getInt(AudioAppConstants.Preferences.Companion.DOWNLOAD_TYPE, -1)
            return type
        }
        set(type) {
            val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
            editor.putInt(AudioAppConstants.Preferences.Companion.DOWNLOAD_TYPE, type)
            editor.apply()
        }
    /**
     * Function to get download id
     *
     * @return download id
     */
    /**
     * Function to set latest download id
     *
     * @param id ID of object you download
     */
    var downloadID: Int
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val id: Int =
                preferences!!.getInt(AudioAppConstants.Preferences.Companion.DOWNLOAD_ID, -1)
            return id
        }
        set(id) {
            val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
            editor.putInt(AudioAppConstants.Preferences.Companion.DOWNLOAD_ID, id)
            editor.apply()
        }
    /**
     * Function to get default tafseer book id
     *
     * @return Tafseer book id
     */
    /**
     * Function to set default tafseer
     *
     * @param tafseerID tafseer book id
     */
    var defaultTafseer: Int
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            val type: Int =
                preferences!!.getInt(
                    AudioAppConstants.Preferences.Companion.DEFAULT_EXPLANATION,
                    -1
                )
            return type
        }
        set(tafseerID) {
            val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
            editor.putInt(AudioAppConstants.Preferences.Companion.DEFAULT_EXPLANATION, tafseerID)
            editor.commit()
        }

    /**
     * Function to get volume key navigation
     *
     * @return Statues of volume key navigation
     */
    val isVolumeKeyNavigation: Boolean
        get() {
            val preferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
            return preferences.getBoolean(
                AudioAppConstants.Preferences.Companion.VOLUME_NAVIGATION,
                false
            )
        }

    /**
     * Function to get if screen rotation allowed or not
     *
     * @return statue of rotation disable or enable
     */
    val isScreeRotationDisabled: Boolean
        get() {
            val preferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
            return preferences.getBoolean(
                AudioAppConstants.Preferences.Companion.ORIENTATION,
                false
            )
        }

    /**
     * Function to get app in public mood or not
     *
     * @return Statue of arabic mood
     */
    fun isArabicMood(context: Context?): Boolean {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(AudioAppConstants.Preferences.Companion.ARABIC_MOOD, false)
    }

    /**
     * Function to get appear aya or not in translations
     *
     * @return statues of appear aya or not
     */
    val isAyaAppear: Boolean
        get() {
            val preferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
            return preferences.getBoolean(AudioAppConstants.Preferences.Companion.AYA_APPEAR, true)
        }
    /**
     * Function to get translation text size
     *
     * @return Translation text size
     */
    /**
     * Function to set translation text size
     */
    var translationTextSize: String?
        get() {
            val preferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
            return preferences.getString(
                AudioAppConstants.Preferences.Companion.TRANSLATION_SIZE,
                "large"
            )
        }
        set(size) {
            val preferences: SharedPreferences.Editor =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
                    .edit()
            preferences.putString(AudioAppConstants.Preferences.Companion.TRANSLATION_SIZE, size)
            preferences.commit()
        }

    /**
     * Function to check stream or download mood
     *
     * @return Flag of stream or not
     */
    val isStreamMood: Boolean
        get() {
            val preferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
            return preferences.getBoolean(AudioAppConstants.Preferences.Companion.STREAM, false)
        }

    /**
     * Function to check NightMood
     *
     * @return Flag of NightMood
     */
    fun isNightMood(context: Context?): Boolean {
        val preferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.instance)
        return preferences.getBoolean(AudioAppConstants.Preferences.Companion.NIGHT_MOOD, false)
    }
    /**
     * Function to get selected aya
     *
     * @return Selected aya object
     */
    /**
     * Function to set selection verse
     */
    @get:Synchronized
    @set:Synchronized
    var selectionVerse: String?
        get() {
            val preferences: SharedPreferences? = AppPreference.OpenConfigPreferences()
            return preferences!!.getString(AudioAppConstants.Preferences.Companion.SELECT_VERSE, "")
        }
        set(info) {
            val editor: SharedPreferences.Editor = AppPreference.OpenConfigPreferences()!!.edit()
            editor.putString(AudioAppConstants.Preferences.Companion.SELECT_VERSE, info)
            editor.commit()
        }
}