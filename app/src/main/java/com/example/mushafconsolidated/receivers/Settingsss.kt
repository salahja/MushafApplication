package com.example.mushafconsolidated.receiversimport

import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.DisplayMetrics
import com.example.utility.QuranGrammarApplication


object Settingsss {
    /**
     * Function to check service running of not
     *
     * @param context      Application context
     * @param serviceClass Service class
     * @return Service running or not
     */
    fun isMyServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val manager: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service: ActivityManager.RunningServiceInfo in manager
            .getRunningServices(Int.MAX_VALUE)) {
            if ((serviceClass.getName() == service.service.getClassName())) {
                return true
            }
        }
        return false
    }

    /**
     * Function to check mobile connected to internet or not and connection type
     *
     * @param context Application context
     * @return Connection type
     */
    fun checkInternetStatus(context: Context): Int {
        val connMgr: ConnectivityManager = context.getApplicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi: NetworkInfo? = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile: NetworkInfo? = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifi!!.isConnectedOrConnecting()) {
            return 1
        } else if (mobile!!.isConnectedOrConnecting()) {
            return 2
        } else {
            return 1
        }
    }

    /**
     * Function to convert english numbers to arabic
     * if mobile language arabic
     *
     * @param number Number to convert
     * @return Converted number
     */
    fun ChangeNumbers(context: Context, number: String): String {
        try {
            if ((context.getResources()
                    .getConfiguration().locale.getDisplayLanguage() == "العربية")
            ) return number.replace("0".toRegex(), "٠").replace("1".toRegex(), "١")
                .replace("2".toRegex(), "٢").replace("3".toRegex(), "٣")
                .replace("4".toRegex(), "٤").replace("5".toRegex(), "٥")
                .replace("6".toRegex(), "٦").replace("7".toRegex(), "٧")
                .replace("8".toRegex(), "٨").replace("9".toRegex(), "٩")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return number
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics =
            QuranGrammarApplication.instance?.getResources()!!.getDisplayMetrics()
        val px: Int = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        return px
    }
}