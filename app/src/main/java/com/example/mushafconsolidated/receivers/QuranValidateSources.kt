package com.example.mushafconsolidated.receiversimport

import android.content.Context
import android.os.Environment
import com.example.mushafconsolidated.R.string
import java.io.File


object QuranValidateSources {
    /**
     * Function to validate aya found or not
     *
     * @param context Application context
     * @param reader  Reader id
     * b
     * @param sura    Sura id
     * @return Flag of found or not
     */
    fun validateAyaAudio(context: Context, reader: Int, aya: Int, sura: Int): Boolean {

        //create file name from aya id and sura id
        val suraLength: Int = sura.toString().trim { it <= ' ' }.length
        var suraID: String = sura.toString() + ""
        val ayaLength: Int = aya.toString().trim { it <= ' ' }.length
        var ayaID: String = aya.toString() + ""
        if (suraLength == 1) suraID = "00$sura" else if (suraLength == 2) suraID = "0$sura"
        if (ayaLength == 1) ayaID = "00$aya" else if (ayaLength == 2) ayaID = "0$aya"

        //Audio file path
        ///storage/emulated/0/Mushafapplication/Audio/1/8.mp3
        val app_folder_path: String =
            (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/audio/" + 20 + "/"
                    + suraID
                    + ayaID + AudioAppConstants.Extensions.Companion.MP3)
        val filePath: String = (Environment
            .getExternalStorageDirectory()
            .absolutePath
                + context.getString(string.app_folder_path)
                + "/Audio/" + reader + "/" + suraID
                + AudioAppConstants.Extensions.Companion.MP3)

        //check file found or not
        val file: File = File(app_folder_path)
        if (!file.exists()) return false
        return true
    }

    fun validateSurahAudio(context: Context, reader: Int, sura: Int): Boolean {

        //create file name from aya id and sura id
        val suraLength: Int = sura.toString().trim { it <= ' ' }.length
        var suraID: String = sura.toString() + ""
        if (suraLength == 1) suraID = "00$sura" else if (suraLength == 2) suraID = "0$sura"
        val dir = getSaveDirs(context, reader)
        val filepath =
            dir.toString() + "/" + suraID +  AudioAppConstants.Extensions.Companion.MP3
        //Audio file path
        ///storage/emulated/0/Mushafapplication/Audio/1/8.mp3
        val app_folder_path: String =
            (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/audio/" + reader + "/"
                    + sura
                    + AudioAppConstants.Extensions.Companion.MP3)
        val filePath: String = (Environment
            .getExternalStorageDirectory()
            .absolutePath
                + context.getString(string.app_folder_path)
                + "/Audio/" + reader + "/" + suraID
                + AudioAppConstants.Extensions.Companion.MP3)

        //check file found or not
        val file: File = File(filepath)
        if (!file.exists()) return false
        return true
    }


    fun validateAyaAudiorx(context: Context, reader: Int, aya: Int, sura: Int): Boolean {

        //create file name from aya id and sura id
        val suraLength: Int = sura.toString().trim { it <= ' ' }.length
        var suraID: String = sura.toString() + ""
        val ayaLength: Int = aya.toString().trim { it <= ' ' }.length
        var ayaID: String = aya.toString() + ""
        if (suraLength == 1) suraID = "00$sura" else if (suraLength == 2) suraID = "0$sura"
        if (ayaLength == 1) ayaID = "00$aya" else if (ayaLength == 2) ayaID = "0$aya"
        val dir = getSaveDirs(context, reader)
        //     return "$dir/$fileName"

        //Audio file path
        ///storage/emulated/0/Misapplication/Audio/1/8.mp3
        val filepath =
            dir.toString() + "/" + suraID + ayaID + AudioAppConstants.Extensions.Companion.MP3
        //Audio file path
        ///storage/emulated/0/Mushafapplication/Audio/1/8.mp3
        val app_folder_path: String =
            (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/audio/" + 20 + "/"
                    + suraID
                    + ayaID + AudioAppConstants.Extensions.Companion.MP3)
        val filePath: String = (Environment
            .getExternalStorageDirectory()
            .absolutePath
                + context.getString(string.app_folder_path)
                + "/Audio/" + reader + "/" + suraID
                + AudioAppConstants.Extensions.Companion.MP3)

        //check file found or not
        //   val file: File = File(app_folder_path)
        val file: File = File(filepath)
        if (file.length() == 0.toLong())
        {
            file.delete()
            return false
        }
        if (!file.exists()) return false
        return true
    }


    fun getSaveDirs(context: Context, reader: Int): Any {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + "/fetch/" + reader
    }
    fun getSaveSurahDirs(context: Context, reader: String): Any {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + "/fetch/" + reader
    }

}