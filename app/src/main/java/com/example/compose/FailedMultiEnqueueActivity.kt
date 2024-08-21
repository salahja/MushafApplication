package com.example.compose

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.example.mushafconsolidated.Activity.ShowMushafActivity
import com.example.mushafconsolidated.R
import com.tonyodev.fetch2.Fetch
import com.tonyodev.fetch2.Fetch.Impl.getInstance
import com.tonyodev.fetch2.FetchConfiguration
import com.tonyodev.fetch2.NetworkType
import com.tonyodev.fetch2.Priority
import com.tonyodev.fetch2.Request
import com.tonyodev.fetch2core.Downloader.FileDownloaderType
import com.tonyodev.fetch2okhttp.OkHttpDownloader


class FailedMultiEnqueueActivity : AppCompatActivity() {
    private var fetch: Fetch? = null
    private var mainFetch: Fetch? = null
    private var Links: List<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_enqueue)
        Links = createDownloadLinks()
        val mainFetchConfiguration = FetchConfiguration.Builder(this)
            .setDownloadConcurrentLimit(4) // Allows Fetch to download 4 downloads in Parallel.
            .enableLogging(true)
            .build()

        mainFetch = getInstance(mainFetchConfiguration)
        val fetchConfiguration: FetchConfiguration = FetchConfiguration.Builder(this)
            .setDownloadConcurrentLimit(4)
            .setHttpDownloader(OkHttpDownloader(FileDownloaderType.PARALLEL))
            .setNamespace(FETCH_NAMESPACE)
            .build()
        fetch = getInstance(fetchConfiguration)
        fetch!!.setGlobalNetworkType(NetworkType.ALL)
        val readerID = 21
        val filepath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/audio/" + ShowMushafActivity.readerID
        val requestLists: List<Request> = ArrayList()
        val requestList =
            getFileUrlUpdates(this, Links!!, filepath, ShowMushafActivity.readerID.toString())
        val groupId = "MySampleGroup".hashCode()
        for (i in requestList.indices) {
            requestLists[i].groupId = groupId

        }


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
        /*       for (i in 0..9) {
                   val filePath = getSaveDir(context) + "/gameAssets/" + "asset_" + i + ".asset"
                   val request = Request(url, filePath)
                   request.priority = Priority.HIGH
                   requests.add(request)
               }*/
        return requests
    }

    private fun getFilePath(
        url: String,
        context: Context,
        filepath: String,
        readerid: String
    ): String {
        val uri = Uri.parse(url)
        val fileName = uri.lastPathSegment
        val dir = getSaveDirs(context, readerid)
        return "$dir/$fileName"
    }

    private fun getSaveDirs(context: Context, readerid: String): Any {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + "/fetch/" + readerid
    }


    override fun onDestroy() {
        super.onDestroy()
        fetch!!.close()
    }

    companion object {
        const val FETCH_NAMESPACE = "FailedEnqueue"
    }
}