package com.example.mushafconsolidated.receiversimport

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder


class DownloadService : Service() {
    private var downloadManager: com.example.mushafconsolidated.receivers.DownloadManager? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        AppPreference.Downloading(true)
        val extras = intent.extras
        val downloadURL = extras!!.getString(AudioAppConstants.Download.DOWNLOAD_URL)
        val downloadLocation = extras.getString(AudioAppConstants.Download.DOWNLOAD_LOCATION)
        val type = extras.getInt(AudioAppConstants.Download.TYPE, -1)
        val downloadLinks: List<String>? =
            extras.getStringArrayList(AudioAppConstants.Download.DOWNLOAD_LINKS)
        if (downloadLinks == null) {
            downloadManager =
                com.example.mushafconsolidated.receivers.DownloadManager(this, true, type)
            downloadManager!!.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR,
                downloadURL,
                downloadLocation
            )
        } else {
            downloadManager = com.example.mushafconsolidated.receivers.DownloadManager(
                this,
                true,
                downloadLinks,
                type
            )
            downloadManager!!.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR,
                "",
                downloadLocation
            )
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (downloadManager != null) {
            downloadManager!!.stopDownload = true
        }
        AppPreference.Downloading(false)
    }
}
