/*
package com

import com.example.compose.ActionListener
import com.example.compose.Data
import com.example.compose.FileAdapter


import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Activity.ShowMushafActivity
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.receiversimport.AudioAppConstants
import com.example.mushafconsolidated.receiversimport.QuranValidateSources
import com.example.utility.QuranGrammarApplication
import com.google.android.material.snackbar.Snackbar
import com.tonyodev.fetch2.AbstractFetchListener
import com.tonyodev.fetch2.Download
import com.tonyodev.fetch2.Error
import com.tonyodev.fetch2.Fetch
import com.tonyodev.fetch2.Fetch.Impl.getInstance
import com.tonyodev.fetch2.FetchConfiguration
import com.tonyodev.fetch2.FetchListener
import com.tonyodev.fetch2.NetworkType
import com.tonyodev.fetch2.Request
import com.tonyodev.fetch2core.Downloader.FileDownloaderType
import com.tonyodev.fetch2okhttp.OkHttpDownloader


import java.io.File
import java.util.Collections

class DownloadListActivityOrig : AppCompatActivity(), ActionListener {
    private var mainView: View? = null
    private var fileAdapter: FileAdapter? = null
    private var fetch: Fetch? = null
    private lateinit var Links:List<String>
    val filepath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/audio/" + 20


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_list)
        val f = File(filepath)
        val path = f.absolutePath
        val file = File(path)
        if (!file.exists()) file.mkdirs()
        Links = createDownloadLinks()
        setUpViews()
        val fetchConfiguration: FetchConfiguration = FetchConfiguration.Builder(this)
            .setDownloadConcurrentLimit(1)
            .setHttpDownloader(OkHttpDownloader(FileDownloaderType.PARALLEL))
            .setNamespace(FETCH_NAMESPACE)
            .build()
        fetch = getInstance(fetchConfiguration)
        checkStoragePermissions()

    }

    private fun setUpViews() {
        val networkSwitch = findViewById<SwitchCompat>(R.id.networkSwitch)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        mainView = findViewById(R.id.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        networkSwitch.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                fetch!!.setGlobalNetworkType(NetworkType.WIFI_ONLY)
            } else {
                fetch!!.setGlobalNetworkType(NetworkType.ALL)
            }
        }
        fileAdapter = FileAdapter(this)
        recyclerView.adapter = fileAdapter
    }

    override fun onResume() {
        super.onResume()
        fetch!!.getDownloadsInGroup(GROUP_ID) { downloads: List<Download>? ->
            val list = ArrayList(downloads)
            Collections.sort(list) { first: Download, second: Download ->
                java.lang.Long.compare(
                    first.created,
                    second.created
                )
            }
            for (download in list) {
                fileAdapter!!.addDownload(download!!)
            }
        }.addListener(fetchListener)
    }

    override fun onPause() {
        super.onPause()
        fetch!!.removeListener(fetchListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        fetch!!.close()
    }

    private val fetchListener: FetchListener = object : AbstractFetchListener() {
        override fun onAdded(download: Download) {
            fileAdapter!!.addDownload(download)
        }

        override fun onQueued(download: Download, waitingOnNetwork: Boolean) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onCompleted(download: Download) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        */
/*       @Override
        public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {
            super.onError(download, error, throwable);
            fileAdapter.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }
*//*

        override fun onProgress(
            download: Download,
            etaInMilliseconds: Long,
            downloadedBytesPerSecond: Long
        ) {
            fileAdapter!!.update(download, etaInMilliseconds, downloadedBytesPerSecond)
        }

        override fun onPaused(download: Download) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onResumed(download: Download) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onCancelled(download: Download) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onRemoved(download: Download) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onDeleted(download: Download) {
            fileAdapter!!.update(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }
    }

    private fun checkStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            enqueueDownloads()
            //      requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        } else {
            enqueueDownloads()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enqueueDownloads()
        } else {
            Snackbar.make(mainView!!, R.string.permission_not_enabled, Snackbar.LENGTH_INDEFINITE)
                .show()
        }
    }
    */
/*
      private void enqueueDownloads() {
            final List<Request> requests = Data.getFetchRequestWithGroupId(GROUP_ID, this);
            fetch.enqueue(requests, updatedRequests -> {

            });

        }
     *//*


    private fun enqueueDownloads() {
        val requests = Data.getFetchRequestWithGroupId(GROUP_ID, this,Links,filepath)
        fetch!!.enqueue(requests,  { updatedRequests: List<Pair<Request?, Error?>?>? -> })





    }
    ;



    override fun onPauseDownload(id: Int) {
        fetch!!.pause(id)
    }

    override fun onResumeDownload(id: Int) {
        fetch!!.resume(id)
    }

    override fun onRemoveDownload(id: Int) {
        fetch!!.remove(id)
    }

    override fun onRetryDownload(id: Int) {
        fetch!!.retry(id)
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 200
        private const val UNKNOWN_REMAINING_TIME: Long = -1
        private const val UNKNOWN_DOWNLOADED_BYTES_PER_SECOND: Long = 0
        private val GROUP_ID = "listGroup".hashCode()
        const val FETCH_NAMESPACE = "DownloadListActivity"
    }
}
fun createDownloadLinks(): List<String> {
    val repository= Utils(QuranGrammarApplication.context)
    val chap = repository.getSingleChapter(13)
    val quranbySurah: List<QuranEntity?>? = repository.getQuranbySurah(13)
    //   surahselected = surah
    //   int ayaID=0;
    var counter = 0
    //   quranbySurah.add(0, new QuranEntity(1, 1, 1));
    val downloadLinks: MutableList<String> = java.util.ArrayList()
    //   ayaList.add(0, new Aya(1, 1, 1));
    //loop for all page ayat
//check if readerID is 0
    val  readersList = repository.qaris
    var readerID = 0
    lateinit var downloadLink: String
    lateinit var readerName: String
    if (ShowMushafActivity.readerID == 0) {
        for (qari in readersList) {
            if (qari.name_english == "Mishary Rashed Al-Afasy") {
                ShowMushafActivity.readerID = qari.id
                ShowMushafActivity.downloadLink = qari.url
                break
            }
        }
    }
    if (quranbySurah != null) {
        for (ayaItem in quranbySurah) {
            //validate if aya download or not
            if (!QuranValidateSources.validateAyaAudio(
                    QuranGrammarApplication.context!!,
                    readerID,
                    ayaItem!!.ayah,
                    ayaItem.surah
                )
            ) {

                //create aya link


                //create aya link
                val suraLength: Int =
                    chap!![0]!!.chapterid.toString().trim { it <= ' ' }.length
                var suraID: String = chap[0]!!.chapterid.toString() + ""


                val ayaLength = ayaItem.ayah.toString().trim { it <= ' ' }.length
                //   int ayaLength = String.valueOf(ayaItem.ayaID).trim().length();
                //   int ayaLength = String.valueOf(ayaItem.ayaID).trim().length();
                var ayaID = java.lang.StringBuilder(
                    java.lang.StringBuilder().append(ayaItem.ayah).append("").toString()
                )
                if (suraLength == 1) suraID =
                    "00" + ayaItem.surah else if (suraLength == 2) suraID = "0" + ayaItem.surah

                if (ayaLength == 1) {
                    ayaID = java.lang.StringBuilder("00" + ayaItem.ayah)
                } else if (ayaLength == 2) {
                    ayaID = java.lang.StringBuilder("0" + ayaItem.ayah)
                }
                counter++
                //add aya link to list
                //chec
                downloadLinks.add(ShowMushafActivity.downloadLink + suraID + ayaID + AudioAppConstants.Extensions.MP3)
                Log.d(
                    "DownloadLinks",
                    ShowMushafActivity.downloadLink + suraID + ayaID + AudioAppConstants.Extensions.MP3
                )
            }
        }
    }
    return downloadLinks
}*/
