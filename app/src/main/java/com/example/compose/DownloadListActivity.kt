package com.example.compose


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
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
import com.example.utility.QuranGrammarApplication.Companion.context
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
import com.tonyodev.fetch2.Status
import com.tonyodev.fetch2core.Downloader.FileDownloaderType
import com.tonyodev.fetch2okhttp.OkHttpDownloader
import java.io.File
import java.util.Collections


class DownloadListActivity : AppCompatActivity(), ActionListener {
    val titleTextView: TextView? = null
    val statusTextView: TextView? = null
    val progressBar: ProgressBar? = null
    val progressTextView: TextView? = null
    val actionButton: Button? = null
    val timeRemainingTextView: TextView? = null
    val downloadedBytesPerSecondTextView: TextView? = null
    var downloads: MutableList<DownloadDatass> = java.util.ArrayList()
    private var mainView: View? = null
    private var fileAdapter: FileAdapter? = null
    private var fetch: Fetch? = null
    private lateinit var Links: List<String>
    private val actionListener: ActionListener? = null
    val filepath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            .toString() + "/audio/" + 20


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_download_list)
        setContentView(R.layout.download_item)
        val f = File(filepath)
        val path = f.absolutePath
        val file = File(path)
        if (!file.exists()) file.mkdirs()
        Links = createDownloadLinks()
        //    setUpViews()
        val fetchConfiguration: FetchConfiguration = FetchConfiguration.Builder(this)
            .setDownloadConcurrentLimit(1)
            .setHttpDownloader(OkHttpDownloader(FileDownloaderType.PARALLEL))
            .setNamespace(FETCH_NAMESPACE)
            .build()
        fetch = getInstance(fetchConfiguration)
        fetch!!.setGlobalNetworkType(NetworkType.ALL)
        checkStoragePermissions()
        fetch!!.getDownloadsInGroup(GROUP_ID) { downloads: List<Download>? ->
            val list = ArrayList(downloads)
            Collections.sort(list) { first: Download, second: Download ->
                java.lang.Long.compare(
                    first.created,
                    second.created
                )
            }
            for (download in list) {
                addDownload((download))
            }


            /*    for (download in list) {
                    fileAdapter!!.addDownload(download!!)
                }*/
        }.addListener(fetchListener)
        for (download in downloads) {
            var url = ""
            if (download != null) {
                url = download.download!!.url
            }
            val uri = Uri.parse(url)
            val status: Status = download.download!!.status
            // val context: Context = itemView.getContext()

            titleTextView!!.setText(uri.lastPathSegment)
            statusTextView!!.setText(getStatusString(status))

            var progress: Int = download.download!!.progress
            if (progress == -1) { // Download progress is undermined at the moment.
                progress = 0
            }
            progressBar!!.setProgress(progress)
            progressTextView!!.setText(getString(R.string.percent_progress, progress))



            if (download.eta == -1L) {
                timeRemainingTextView!!.setText("")
            } else {
                timeRemainingTextView!!.setText(
                    FUtils.getETAString(
                        this,
                        download.eta
                    )
                )
            }


            if (download.eta == -1L) {
                timeRemainingTextView.setText("")
            } else {
                timeRemainingTextView.setText(
                    FUtils.getETAString(
                        this,
                        download.eta
                    )
                )
            }

            if (download.downloadedBytesPerSecond == 0L) {
                downloadedBytesPerSecondTextView!!.setText("")
            } else {
                downloadedBytesPerSecondTextView!!.setText(
                    FUtils.getDownloadSpeedString(
                        this,
                        download.downloadedBytesPerSecond
                    )
                )
            }

            when (status) {
                Status.COMPLETED -> {
                    actionButton!!.setText(R.string.view)

                    actionButton!!.setOnClickListener(View.OnClickListener { view: View? ->
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Toast.makeText(
                                context,
                                "Downloaded Path:" + download.download!!.file,
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        val file: File = File(download.download!!.file)
                        val uri1 = Uri.fromFile(file)
                        val share = Intent(Intent.ACTION_VIEW)
                        share.setDataAndType(uri1, FUtils.getMimeType(this, uri1))
                        context!!.startActivity(share)
                    })
                }

                Status.FAILED -> {
                    actionButton!!.setText(R.string.retry)
                    actionButton!!.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onRetryDownload(download.download!!.id)
                    })
                }

                Status.PAUSED -> {
                    actionButton!!.setText(R.string.resume)
                    actionButton.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onResumeDownload(download.download!!.id)
                    })
                }

                Status.DOWNLOADING, Status.QUEUED -> {
                    actionButton!!.setText(R.string.pause)
                    actionButton.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onPauseDownload(download.download!!.id)
                    })
                }

                Status.ADDED -> {
                    actionButton!!.setText(R.string.download)
                    actionButton!!.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onResumeDownload(download.id)
                    })
                }

                else -> {}
            }

        }

    }

    private fun getStatusString(status: Status): String? {
        return when (status) {
            Status.COMPLETED -> "Done"
            Status.DOWNLOADING -> "Downloading"
            Status.FAILED -> "Error"
            Status.PAUSED -> "Paused"
            Status.QUEUED -> "Waiting in Queue"
            Status.REMOVED -> "Removed"
            Status.NONE -> "Not Queued"
            else -> "Unknown"
        }
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


    override fun onStart() {
        super.onStart()
        fetch!!.getDownloadsInGroup(GROUP_ID) { downloads: List<Download>? ->
            val list = ArrayList(downloads)
            Collections.sort(list) { first: Download, second: Download ->
                java.lang.Long.compare(
                    first.created,
                    second.created
                )
            }
            for (download in list) {
                addDownload((download))
            }


            /*    for (download in list) {
                    fileAdapter!!.addDownload(download!!)
                }*/
        }.addListener(fetchListener)
        for (download in downloads) {
            var url = ""
            if (download != null) {
                url = download.download!!.url
            }
            val uri = Uri.parse(url)
            val status: Status = download.download!!.status
            // val context: Context = itemView.getContext()

            titleTextView!!.setText(uri.lastPathSegment)
            statusTextView!!.setText(getStatusString(status))

            var progress: Int = download.download!!.progress
            if (progress == -1) { // Download progress is undermined at the moment.
                progress = 0
            }
            progressBar!!.setProgress(progress)
            progressTextView!!.setText(getString(R.string.percent_progress, progress))



            if (download.eta == -1L) {
                timeRemainingTextView!!.setText("")
            } else {
                timeRemainingTextView!!.setText(
                    FUtils.getETAString(
                        this,
                        download.eta
                    )
                )
            }


            if (download.eta == -1L) {
                timeRemainingTextView.setText("")
            } else {
                timeRemainingTextView.setText(
                    FUtils.getETAString(
                        this,
                        download.eta
                    )
                )
            }

            if (download.downloadedBytesPerSecond == 0L) {
                downloadedBytesPerSecondTextView!!.setText("")
            } else {
                downloadedBytesPerSecondTextView!!.setText(
                    FUtils.getDownloadSpeedString(
                        this,
                        download.downloadedBytesPerSecond
                    )
                )
            }

            when (status) {
                Status.COMPLETED -> {
                    actionButton!!.setText(R.string.view)

                    actionButton!!.setOnClickListener(View.OnClickListener { view: View? ->
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Toast.makeText(
                                context,
                                "Downloaded Path:" + download.download!!.file,
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        val file: File = File(download.download!!.file)
                        val uri1 = Uri.fromFile(file)
                        val share = Intent(Intent.ACTION_VIEW)
                        share.setDataAndType(uri1, FUtils.getMimeType(this, uri1))
                        context!!.startActivity(share)
                    })
                }

                Status.FAILED -> {
                    actionButton!!.setText(R.string.retry)
                    actionButton!!.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onRetryDownload(download.download!!.id)
                    })
                }

                Status.PAUSED -> {
                    actionButton!!.setText(R.string.resume)
                    actionButton.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onResumeDownload(download.download!!.id)
                    })
                }

                Status.DOWNLOADING, Status.QUEUED -> {
                    actionButton!!.setText(R.string.pause)
                    actionButton.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onPauseDownload(download.download!!.id)
                    })
                }

                Status.ADDED -> {
                    actionButton!!.setText(R.string.download)
                    actionButton!!.setOnClickListener(View.OnClickListener { view: View? ->
                        actionButton.setEnabled(false)
                        actionListener!!.onResumeDownload(download.id)
                    })
                }

                else -> {}
            }

        }

    }


    fun addDownload(download: Download) {
        var found = false
        var data: DownloadDatass? = null
        var dataPosition = -1
        for (i in downloads.indices) {
            val downloadData = downloads[i]
            if (downloadData.id == download.id) {
                data = DownloadDatass()
                dataPosition = i
                found = true
                break
            }
        }
        if (!found) {
            val downloadData = DownloadDatass()
            downloadData.id = download.id
            downloadData.download = download

            downloads.add(downloadData)
            //  notifyItemInserted(downloads.size - 1)
        } else {
            data!!.download = download
            //     notifyItemChanged(dataPosition)
        }
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
            updates(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onCompleted(download: Download) {
            updates(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        /*       @Override
        public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {
            super.onError(download, error, throwable);
            fileAdapter.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }
*/
        override fun onProgress(
            download: Download,
            etaInMilliseconds: Long,
            downloadedBytesPerSecond: Long
        ) {
            updates(download, etaInMilliseconds, downloadedBytesPerSecond)
        }

        private fun updatess(
            download: Download,
            etaInMilliseconds: Long,
            downloadedBytesPerSecond: Long
        ) {
            TODO("Not yet implemented")
        }

        fun updates(download: Download, eta: Long, downloadedBytesPerSecond: Long) {
            for (position in downloads.indices) {
                val downloadData = downloads[position]
                if (downloadData.id == download.id) {
                    when (download.status) {
                        Status.REMOVED, Status.DELETED -> {
                            downloads.removeAt(position)
                            //   notifyItemRemoved(position)
                        }

                        else -> {
                            downloadData.download = download
                            downloadData.eta = eta
                            downloadData.downloadedBytesPerSecond = downloadedBytesPerSecond
                            //   notifyItemChanged(position)
                        }
                    }
                    return
                }
            }
        }


        override fun onPaused(download: Download) {
            updates(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onResumed(download: Download) {
            updates(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onCancelled(download: Download) {
            updates(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onRemoved(download: Download) {
            updates(
                download,
                UNKNOWN_REMAINING_TIME,
                UNKNOWN_DOWNLOADED_BYTES_PER_SECOND
            )
        }

        override fun onDeleted(download: Download) {
            updates(
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
    /*
      private void enqueueDownloads() {
            final List<Request> requests = Data.getFetchRequestWithGroupId(GROUP_ID, this);
            fetch.enqueue(requests, updatedRequests -> {

            });

        }
     */

    private fun enqueueDownloads() {
        val requests = Data.getFetchRequestWithGroupId(GROUP_ID, this, Links, filepath)
        fetch!!.enqueue(requests, { updatedRequests: List<Pair<Request?, Error?>?>? -> })


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

class DownloadDatass {
    var id = 0
    var download: Download? = null
    var eta: Long = -1
    var downloadedBytesPerSecond: Long = 0
    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return if (download == null) {
            ""
        } else download.toString()
    }

    override fun equals(obj: Any?): Boolean {
        return obj === this || obj is com.example.compose.DownloadDatas && obj.id == id
    }

}

fun createDownloadLinks(): List<String> {
    val repository = Utils(QuranGrammarApplication.context)
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
    val readersList = repository.qaris
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
}

class DownloadDatas {
    var id = 0
    var download: Download? = null
    var eta: Long = -1
    var downloadedBytesPerSecond: Long = 0
    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return if (download == null) {
            ""
        } else download.toString()
    }

    override fun equals(obj: Any?): Boolean {
        return obj === this || obj is com.example.compose.DownloadDatas && obj.id == id
    }

    companion object
}


/*
fun update(download: Download, eta: Long, downloadedBytesPerSecond: Long) {
    for (position in downloads.indices) {
        val downloadData: DownloadData = downloads.get(position)
        if (downloadData.id == download.id) {
            when (download.status) {
                Status.REMOVED, Status.DELETED -> {
                    downloads.removeAt(position)
                    notifyItemRemoved(position)
                }

                else -> {
                    downloadData.download = download
                    downloadData.eta = eta
                    downloadData.downloadedBytesPerSecond = downloadedBytesPerSecond
                    notifyItemChanged(position)
                }
            }
            return
        }
    }
}*/
