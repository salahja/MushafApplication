package com.example.compose

import android.content.Context
import android.net.Uri
import android.os.Environment
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

    private fun getFetchRequests(
        context: Context,
        Links: List<String>,
        filepath: String
    ): List<Request> {
        val requests: MutableList<Request> = ArrayList()
        for (sampleUrl in Links) {
            val request = Request(sampleUrl, getFilePath(sampleUrl, context, filepath))
            //val request = Request(sampleUrl, filepath)
            requests.add(request)
        }
        return requests
    }

    @JvmStatic
    fun getFetchRequestWithGroupId(
        groupId: Int,
        context: Context,
        Links: List<String>,
        filepath: String
    ): List<Request> {
        val requests = getFetchRequests(context, Links, filepath)
        for (request in requests) {
            request.groupId = groupId
        }
        return requests
    }

    private fun getFilePath(url: String, context: Context, filepath: String): String {
        val uri = Uri.parse(url)
        val fileName = uri.lastPathSegment
        val dir = getSaveDir(context)
        return "$dir/$fileName"
    }

    @JvmStatic
    fun getNameFromUrl(url: String?): String {
        return Uri.parse(url).lastPathSegment!!
    }

    @JvmStatic
    fun getGameUpdates(context: Context, Links: List<String>, filepath: String): List<Request> {
        val requests: MutableList<Request> = ArrayList()
        val url = "http://speedtest.ftp.otenet.gr/files/test100k.db"
        for (sampleUrl in Links) {
            val request = Request(sampleUrl, getFilePath(sampleUrl, context, filepath))
            request.priority = Priority.HIGH
            //val request = Request(sampleUrl, filepath)
            requests.add(request)
        }
        /*       for (i in 0..9) {
                   val filePath = getSaveDir(context) + "/gameAssets/" + "asset_" + i + ".asset"
                   val request = Request(url, filePath)
                   request.priority = Priority.HIGH
                   requests.add(request)
               }*/
        return requests
    }

    @JvmStatic
    fun getGameUpdates(context: Context): MutableList<Request> {
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


    @JvmStatic
    fun getSaveDir(context: Context): String {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/fetch"
    }
}