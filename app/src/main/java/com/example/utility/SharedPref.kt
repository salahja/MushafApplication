import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPref(private val sharedContext: Context) {
    init {
        //  this.sharedPreferences = sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            sharedContext
        )
    }

    companion object {
        var sharedPreferences: SharedPreferences? = null
        fun SeekarabicFontsize(): Int {
            // return Integer.(sharedPreferences.getInt("pref_font_seekbar_key", (Context.MODE_PRIVATE)));
            return sharedPreferences?.getInt("pref_font_arabic_key", Context.MODE_PRIVATE) ?: 20
        }

        val language: String?
            get() = sharedPreferences?.getString("lang", Context.MODE_PRIVATE.toString())
        val wbwLanguage: String?
            get() = sharedPreferences?.getString("wbw", Context.MODE_PRIVATE.toString())

        fun themePreferences(): String? {
            return sharedPreferences?.getString("themePref", Context.MODE_PRIVATE.toString())
        }

        val translation: String?
            get() = sharedPreferences?.getString(
                "selecttranslation",
                Context.MODE_PRIVATE.toString()
            )

        fun showTranslation(): Boolean {
            return sharedPreferences?.getBoolean("showTranslationKey", true) ?: true
        }

        fun showWordByword(): Boolean {
            return sharedPreferences?.getBoolean("wordByWord", false) ?: false
        }

        fun showErab(): Boolean {
            return sharedPreferences?.getBoolean("showErabKey", true) ?: false
        }
    }
}
