package org.sj.conjugator.utilities

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager

class VerbApplication : Application() {
    // --Commented out by Inspection START (20/08/23, 8:37 am):
    //    public static Context getContext() {
    //        return appContext;
    //    }
    // --Commented out by Inspection STOP (20/08/23, 8:37 am)
    override fun onCreate() {
        super.onCreate()
        if (appContext == null) {
            appContext = this
        }
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        //  String theme = sharedPreferences.getString("theme", 1);
        val themePref = sharedPreferences.getString("theme", "white")
        ThemeHelper.applyTheme(themePref!!)
    }

    companion object {
        private var appContext: Context? = null
    }
}