package com.example.mushafconsolidated.Activity

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.mushafconsolidated.receiversimport.AudioAppConstants
import com.example.mushafconsolidated.receiversimport.QuranValidateSources.getSaveSurahDirs
import com.tonyodev.fetch2.Priority
import com.tonyodev.fetch2.Request

object Data {
    val sampleUrls = arrayOf(
        "http://speedtest.ftp.otenet.gr/files/test100Mb.db",
        "https://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_stereo.avi",
        "http://media.mongodb.org/zips.json",
        "http://www.exampletonyotest/some/unknown/123/Errorlink.txt",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Android_logo_2019.svg/687px-Android_logo_2019.svg.png",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    )

    fun getFetchRequests(context: Context): List<Request> {
        val requests: MutableList<Request> = ArrayList()
        for (sampleUrl in sampleUrls) {
            val request = Request(sampleUrl, getFilePath(sampleUrl, context))
            requests.add(request)
        }
        return requests
    }

    fun getFetchRequestWithGroupId(groupId: Int, context: Context): List<Request> {
        val requests = getFetchRequests(context)
        for (request in requests) {
            request.groupId = groupId
        }
        return requests
    }

    private fun getFilePath(url: String, context: Context): String {
        val uri = Uri.parse(url)
        val fileName = uri.lastPathSegment
        val dir = getSaveDir(context)
        return "$dir/DownloadList/$fileName"
    }

    fun getNameFromUrl(url: String?): String {
        return Uri.parse(url).lastPathSegment!!
    }

    fun getGameUpdates(context: Context): List<Request> {
        val requests: MutableList<Request> = ArrayList()
        val url = "http://speedtest.ftp.otenet.gr/files/test100k.db"
        for (i in 0..9) {
            val filePath = getSaveDir(context) + "/gameAssets/" + "asset_" + i + ".asset"
            val request = Request(url, filePath)
            request.priority = Priority.HIGH
            requests.add(request)
        }
        return requests
    }

    fun getSaveDir(context: Context): String {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/fetch"
    }

    fun getFilePath(url: String, context: Context, filepath: String, readerid: String): String {
        val uri = Uri.parse(url)
        val fileName = uri.lastPathSegment
        val dir = getSaveDirs(context, readerid)
        return "$dir/$fileName"
    }
    fun getFilePathJson(context: Context, filepath: String, readerid: String, jsonString: String): String {

        val fileName = readerid+ AudioAppConstants.Extensions.Companion.JSON
        val dir = getSaveSurahDirs(context, readerid)
        return "$dir/$fileName"
    }

    fun getSaveDirs(context: Context, readerid: String): Any {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + "/fetch/" + readerid
    }

    fun getFileUrlUpdates(
        context: Context,
        Links: List<String>,
        filepath: String,
        readerid: String
    ): List<Request> {
        val requests: MutableList<Request> = ArrayList()
        val url = "http://speedtest.ftp.otenet.gr/files/test100k.db"
        for (sampleUrl in Links) {
            val request = Request(sampleUrl, getFilePath(sampleUrl, context, filepath, readerid))
            request.priority = Priority.HIGH
            //val request = Request(sampleUrl, filepath)
            requests.add(request)
        }

        return requests
    }

}