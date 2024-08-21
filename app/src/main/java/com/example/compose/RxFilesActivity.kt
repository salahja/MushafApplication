package com.example.compose


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.ArrayMap
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.compose.Data.getGameUpdates
import com.example.mushafconsolidated.R
import com.google.android.material.snackbar.Snackbar
import com.tonyodev.fetch2.AbstractFetchListener
import com.tonyodev.fetch2.Download
import com.tonyodev.fetch2.Error
import com.tonyodev.fetch2.FetchConfiguration
import com.tonyodev.fetch2.FetchListener
import com.tonyodev.fetch2.Request
import com.tonyodev.fetch2.getErrorFromThrowable
import com.tonyodev.fetch2rx.RxFetch
import com.tonyodev.fetch2rx.RxFetch.Impl.getDefaultRxInstance
import com.tonyodev.fetch2rx.RxFetch.Impl.setDefaultRxInstanceConfiguration
import io.reactivex.disposables.Disposable
import timber.log.Timber


class RxFilesActivity : AppCompatActivity() {
    private var mainView: View? = null
    private var progressTextView: TextView? = null
    private var progressBar: ProgressBar? = null
    private var startButton: Button? = null
    private var labelTextView: TextView? = null
    private val fileProgressMap = ArrayMap<Int, Int>()
    private var rxFetch: RxFetch? = null
    private var enqueueDisposable: Disposable? = null
    private var resumeDisposable: Disposable? = null
    private lateinit var Links: List<String>
    val filepath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            .toString() + "/audio/" + 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rxfetch_progress)

        val fetchConfiguration: FetchConfiguration = FetchConfiguration.Builder(this).build()
        //    rxFetch = RxFetch.Impl.getInstance(fetchConfiguration);
        setDefaultRxInstanceConfiguration(fetchConfiguration)
        //  rxFetch.Impl.setDefaultFetchConfiguration(config);
        rxFetch = getDefaultRxInstance()
        Links = createDownloadLinks()
        setUpViews()
        reset()
    }

    private fun setUpViews() {
        progressTextView = findViewById(R.id.progressTextView)
        progressBar = findViewById(R.id.progressBar)
        startButton = findViewById(R.id.startButton)
        labelTextView = findViewById(R.id.labelTextView)
        mainView = findViewById(R.id.activity_loading)
        labelTextView!!.setText(R.string.fetch_started)

        startButton!!.setOnClickListener(View.OnClickListener { v: View? ->
            val label = startButton!!.getText() as String
            val context: Context = this@RxFilesActivity
            if (label == context.getString(R.string.reset)) {
                rxFetch!!.deleteAll()
                reset()
            } else {
                startButton!!.setVisibility(View.GONE)
                labelTextView!!.setText(R.string.fetch_started)
                checkStoragePermission()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        rxFetch!!.addListener(fetchListener)
        resumeDisposable = rxFetch!!.getDownloadsInGroup(groupId).flowable.subscribe(
            { downloads: List<Download> ->
                for (download in downloads) {
                    if (fileProgressMap.containsKey(download.id)) {
                        fileProgressMap[download.id] = download.progress
                        updateUIWithProgress()
                    }
                }
            }
        ) { throwable: Throwable? ->
            val error = getErrorFromThrowable(
                throwable!!
            )
            Timber.d("GamesFilesActivity Error: %1\$s", error)
        }
    }

    override fun onPause() {
        super.onPause()
        rxFetch!!.removeListener(fetchListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        rxFetch!!.deleteAll()
        rxFetch!!.close()
        if (enqueueDisposable != null && !enqueueDisposable!!.isDisposed) {
            enqueueDisposable!!.dispose()
        }
        if (resumeDisposable != null && !resumeDisposable!!.isDisposed) {
            resumeDisposable!!.dispose()
        }
    }

    private fun checkStoragePermission() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
        enqueueFiles()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE || grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enqueueFiles()
        } else {
            enqueueFiles()
            Toast.makeText(this, R.string.permission_not_enabled, Toast.LENGTH_SHORT).show()
            reset()
        }
    }

    private fun updateUIWithProgress() {
        val totalFiles = fileProgressMap.size
        val completedFiles = completedFileCount
        progressTextView!!.text =
            resources.getString(R.string.complete_over, completedFiles, totalFiles)
        val progress = downloadProgress
        progressBar!!.progress = progress
        if (completedFiles == totalFiles) {
            labelTextView!!.text = getString(R.string.fetch_done)
            startButton!!.setText(R.string.reset)
            startButton!!.visibility = View.VISIBLE
        }
    }

    private val downloadProgress: Int
        private get() {
            var currentProgress = 0
            val totalProgress = fileProgressMap.size * 100
            val ids: Set<Int> = fileProgressMap.keys
            for (id in ids) {
                currentProgress += fileProgressMap[id]!!
            }
            currentProgress = (currentProgress.toDouble() / totalProgress.toDouble() * 100).toInt()
            return currentProgress
        }
    private val completedFileCount: Int
        private get() {
            var count = 0
            val ids: Set<Int> = fileProgressMap.keys
            for (id in ids) {
                val progress = fileProgressMap[id]!!
                if (progress == 100) {
                    count++
                }
            }
            return count
        }

    private fun reset() {
        rxFetch!!.deleteAll()
        fileProgressMap.clear()
        progressBar!!.progress = 0
        progressTextView!!.text = ""
        labelTextView!!.setText(R.string.start_fetching)
        startButton!!.setText(R.string.start)
        startButton!!.visibility = View.VISIBLE
    }

    private fun enqueueFiles() {
        val requestList = getGameUpdates(this, Links, filepath)
        for (request in requestList) {
            request.groupId = groupId
        }
        enqueueDisposable =
            rxFetch!!.enqueue(requestList).flowable.subscribe({ updatedRequests: List<Pair<Request, Error?>> ->
                for ((first) in updatedRequests) {
                    fileProgressMap[first.id] = 0
                    updateUIWithProgress()
                }
            }) { throwable: Throwable? ->
                val error = getErrorFromThrowable(
                    throwable!!
                )
                Timber.d("GamesFilesActivity Error: %1\$s", error)
            }
    }

    private val fetchListener: FetchListener = object : AbstractFetchListener() {
        override fun onCompleted(download: Download) {
            fileProgressMap[download.id] = download.progress
            updateUIWithProgress()
        }

        override fun onError(download: Download, error: Error, throwable: Throwable?) {
            super.onError(download, error, throwable)
            reset()
            Snackbar.make(mainView!!, R.string.game_download_error, Snackbar.LENGTH_INDEFINITE)
                .show()
        }

        override fun onProgress(
            download: Download,
            etaInMilliseconds: Long,
            downloadedBytesPerSecond: Long
        ) {
            super.onProgress(download, etaInMilliseconds, downloadedBytesPerSecond)
            fileProgressMap[download.id] = download.progress
            updateUIWithProgress()
        }
    }

    companion object {
        const val STORAGE_PERMISSION_CODE = 400
        const val groupId = 12
    }
}
