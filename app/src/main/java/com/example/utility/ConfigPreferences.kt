package com.example.utility


import AudioPlayed
import android.content.Context
import com.google.gson.Gson


/**
 * Class to save application configurations
 */
object ConfigPreferences {
    private const val MAIN_CONFIG = "application_settings"



    fun setLastPlayedAudio(
        context: Context,
        audioPlayed: ArrayList<AudioPlayed?>?,
        surah: String?,
    ) {
        val editor = context.getSharedPreferences(MAIN_CONFIG, Context.MODE_PRIVATE).edit()
        val gson = Gson()
        //  String json = gson.toJson(audioPlayed);
        val json = gson.toJson(AudioPositionSaved(audioPlayed))
        editor.putString(surah, json)
        editor.apply()
    }

    fun getLastPlayedAudio(
        context: Context,
        surah: String?,
    ): AudioPositionSaved {
        val sharedPreferences = context.getSharedPreferences(
            MAIN_CONFIG,
            Context.MODE_PRIVATE
        )
        val gson = Gson()
        val json = sharedPreferences.getString(surah, "")
        return gson.fromJson(json, AudioPositionSaved::class.java)
    }




}

