package com.example.mushafconsolidated.receiversimport

import android.content.Context
import android.os.Environment
import java.io.File


object FileManager {
    fun createAyaAudioLinkLocation(context: Context?, reader: Int, aya: Int, sura: Int): String {
        //create file name from aya id and sura id
        val suraLength: Int = sura.toString().trim({ it <= ' ' }).length
        var suraID: String = sura.toString() + ""
        val ayaLength: Int = aya.toString().trim({ it <= ' ' }).length
        var ayaID: String = aya.toString() + ""
        if (suraLength == 1) suraID = "00" + sura else if (suraLength == 2) suraID = "0" + sura
        if (ayaLength == 1) ayaID = "00" + aya else if (ayaLength == 2) ayaID = "0" + aya
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            .toString() + "/audio/" + reader + "/" + suraID + ayaID + AudioAppConstants.Extensions.Companion.MP3
        //Audio file path
        /*       return Environment
                .getExternalStorageDirectory()
                .getAbsolutePath()
                + context.getString(R.string.app_folder_path)
                + "/Audio/" + reader + "/" + suraID
                + ayaID + AudioAppConstants.Extensions.MP3;*/
    }

    fun createAyaAudioLinkLocationrx(
        context: Context?,
        reader: Int,
        aya: Int,
        sura: Int,
        dir: String,
        audioType: Int
    ): String {
        //create file name from aya id and sura id
        val suraLength: Int = sura.toString().trim({ it <= ' ' }).length
        var suraID: String = sura.toString() + ""
        val ayaLength: Int = aya.toString().trim({ it <= ' ' }).length
        var ayaID: String = aya.toString() + ""
        if (suraLength == 1) suraID = "00" + sura else if (suraLength == 2) suraID = "0" + sura
        if (ayaLength == 1) ayaID = "00" + aya else if (ayaLength == 2) ayaID = "0" + aya
        var filepath = ""
        if (audioType == 0) {
            filepath = dir + "/" + suraID + ayaID + AudioAppConstants.Extensions.Companion.MP3
        } else if (audioType == 2) {

            filepath = dir + "/" + suraID + AudioAppConstants.Extensions.Companion.MP3
        }
        val file: File = File(filepath)
        if (file.length() == 0.toLong()) {

            return ""
        }else {
            return filepath
        }



        /*  return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
              .toString() + "/audio/" + reader + "/" + suraID + ayaID + AudioAppConstants.Extensions.Companion.MP3*/

        //Audio file path
        /*       return Environment
                .getExternalStorageDirectory()
                .getAbsolutePath()
                + context.getString(R.string.app_folder_path)
                + "/Audio/" + reader + "/" + suraID
                + ayaID + AudioAppConstants.Extensions.MP3;*/
    }

    fun createAyaSurahLinkLocation(context: Context?, reader: Int, sura: Int): String {
        //create file name from aya id and sura id
        val suraLength: Int = sura.toString().trim({ it <= ' ' }).length
        var suraID: String? = sura.toString() + ""
        if (suraLength == 1) suraID = "00" + sura else if (suraLength == 2) suraID = "0" + sura
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            .toString() + "/audio/" + reader + "/" + sura + AudioAppConstants.Extensions.Companion.MP3
        //Audio file path
        /*       return Environment
                .getExternalStorageDirectory()
                .getAbsolutePath()
                + context.getString(R.string.app_folder_path)
                + "/Audio/" + reader + "/" + suraID
                + ayaID + AudioAppConstants.Extensions.MP3;*/
    }
}