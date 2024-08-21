package Utility

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException


object AudioPrefrence {
    private var jsonObject: JsonObject?=null
    private var result: AudioPositionSaved? = null
    private const val MAIN_CONFIG = "application_settings"
    const val LOCATION_INFO = "location_information"
    const val QUIBLA_DEGREE = "quibla_degree"
    const val ALARM = "alarm"
    const val NEXT_PRAY = "next_pray"
    const val WEATHER_INFO = "Weather"
    const val TODAY_WETHER = "today_weather"
    const val WEEK_WETHER = "week_weather"
    const val APP_LANGUAGE = "app_language"
    const val PRAY_NOTIFY = "pray_notify"
    const val ZEKER_NOTIFY = "zeker_notifiy"
    const val ZEKER_NOTIFICATION = "zeker_notification"
    const val SILENT_MOOD = "silent_mood"
    const val LED_MOOD = "led_mood"
    const val WIDGET_MONTH = "widget_month"
    const val VIBRATION = "vibration_mood"
    const val TWENTYFOUR = "twenty_four"
    const val AZKAR_MOOD = "azkar_mood"
    const val COUNTRY_POPUP = "country_popup"
    const val APP_FIRST_OPEN = "application_first_open"
    fun setLastPlayedAudio(
        context: Context,
        audioPlayed: java.util.ArrayList<AudioPlayed>,
        surah: String?
    ) {
        val editor = context.getSharedPreferences(MAIN_CONFIG, Context.MODE_PRIVATE).edit()
        val gson = Gson()
        val json = gson.toJson(audioPlayed)
        //   String json = gson.toJson(new AudioPositionSaved(audioPlayed));
        editor.putString(surah, json)
        editor.apply()
    }

    fun getLastPlayedAudio(
        context: Context,
        surah: String?
    ): JsonObject? {
        val sharedPreferences = context.getSharedPreferences(
            MAIN_CONFIG,
            Context.MODE_PRIVATE
        )
        val gson = Gson()
        val json = sharedPreferences.getString(surah, "")
        try {
            result = gson.fromJson(json, AudioPositionSaved::class.java)
        } catch (e: JsonSyntaxException) {

                val jsonArray: JsonArray = JsonParser()
                    .parse(json)
                    .getAsJsonArray()
                for (i in 0 until jsonArray.size()) {
                    val user: AudioPositionSaved = gson.fromJson(jsonArray[i],AudioPositionSaved::class.java)
                val chapter=     (jsonArray[i] as JsonObject).get("surah")
                    val checked = chapter.toString()


                    if(checked.equals(surah)){
                      //  result = gson.fromJson(jsonArray[i].asJsonObject, AudioPositionSaved::class.java)
                        jsonObject = jsonArray[i] as JsonObject
                        break

                    }
                    println(user)
                }


            return jsonObject

        }



        return jsonObject
    }

}