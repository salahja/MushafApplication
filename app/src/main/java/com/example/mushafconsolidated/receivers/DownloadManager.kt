package com.example.mushafconsolidated.receivers

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.widget.ProgressBar
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.mushafconsolidated.Activity.ShowMushafActivity


import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.receiversimport.AppPreference
import com.example.mushafconsolidated.receiversimport.AudioAppConstants
import com.example.mushafconsolidated.receiversimport.DownloadService
import com.example.mushafconsolidated.receiversimport.Settingsss
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request


import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.Locale
import java.util.concurrent.TimeUnit

class DownloadManager : AsyncTask<String?, Long?, Boolean> {
    val CHANNEL_ID = "DOWNLOAD_SERVICE_CHANNEL_ID"
    val CHANNEL_NAME = "DOWNLOAD_SERVICE_CHANNEL_NAME"
    val CHANNEL_DESCRIPTION = "DOWNLOAD_SERVICE_CHANNEL_DESCRIPTION"
    private var downloadType = -1
    private var downloadProgressBar: ProgressBar? = null
    private var downloadLinks: List<String>? = null
    private var context: Context? = null
    private var downloadInfo: TextView? = null
    private var remoteViews: RemoteViews? = null
    private var notificationManager: NotificationManager? = null
    private var builder: NotificationCompat.Builder? = null
    private var notificationDownloaderFlag: Boolean
    private var notificationDivider = 0
    private var fileExtension: String? = null
    private var fileName: String? = null
    private var filePath: String? = null
    private var notificationPending: PendingIntent? = null
    private var openApplication: Intent? = null
    var stopDownload = false
    private var aboveLollipopFlag = false
    private val mOkHttpClient: OkHttpClient? = null
    var flag = 0
    var TAG = "StopDownloading"

    /**
     * Class constructor. for download manager
     *
     * @param context                    application context to show notification after finish
     * @param notificationDownloaderFlag flag to appear notification progressbar
     */
    constructor(context: Context?, notificationDownloaderFlag: Boolean, downloadType: Int) {
        Log.i("TAFSEER_DOWN_TAG", "start download tafseer")
        this.downloadType = downloadType
        this.context = context
        this.notificationDownloaderFlag = notificationDownloaderFlag
        init()
    }

    /**
     * Class constructor. for download manager
     *
     * @param downloadProgressBar        gui progressbar
     * @param notificationDownloaderFlag flag to appear notification progressbar
     */
    constructor(
        downloadProgressBar: ProgressBar?,
        notificationDownloaderFlag: Boolean,
        downloadType: Int
    ) {
        this.downloadType = downloadType
        this.downloadProgressBar = downloadProgressBar
        this.notificationDownloaderFlag = notificationDownloaderFlag
        init()
    }

    /**
     * Class constructor. for download manager
     *
     * @param context                    application context to show notification after finish
     * @param downloadProgressBar        gui progressbar
     * @param notificationDownloaderFlag flag to appear notification progressbar
     */
    constructor(
        context: Context?,
        downloadProgressBar: ProgressBar?,
        notificationDownloaderFlag: Boolean,
        downloadType: Int
    ) {
        this.downloadType = downloadType
        this.downloadProgressBar = downloadProgressBar
        this.context = context
        this.notificationDownloaderFlag = notificationDownloaderFlag
        init()
    }

    /**
     * Class constructor. for download manager
     *
     * @param context                    application context to show notification after finish
     * @param downloadProgressBar        gui progressbar
     * @param downloadInfo               textview to show download information
     * @param notificationDownloaderFlag flag to appear notification progressbar
     */
    constructor(
        context: Context?,
        downloadProgressBar: ProgressBar?,
        downloadInfo: TextView?,
        notificationDownloaderFlag: Boolean,
        downloadType: Int
    ) {
        this.downloadType = downloadType
        this.context = context
        this.downloadProgressBar = downloadProgressBar
        this.downloadInfo = downloadInfo
        this.notificationDownloaderFlag = notificationDownloaderFlag
        init()
    }

    /**
     * Public constructor for download links
     *
     * @param context                    Application Context
     * @param notificationDownloaderFlag Flag to show notification
     * @param downloadLinks              List of download links
     */
    constructor(
        context: Context?,
        notificationDownloaderFlag: Boolean,
        downloadLinks: List<String>?,
        downloadType: Int
    ) {
        Log.i("TAFSEER_DOWN_TAG", "start download tafseer")
        this.context = context
        this.downloadLinks = downloadLinks
        this.notificationDownloaderFlag = notificationDownloaderFlag
        init()
    }

    /**
     * Function to init download objects
     */
    private fun init() {
        openApplication = Intent(context, ShowMushafActivity::class.java)
        notificationPending = PendingIntent.getActivity(
            context, 0,
            openApplication, PendingIntent.FLAG_IMMUTABLE
        )
        aboveLollipopFlag = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    /**
     * Start download file from url
     *
     * @param url the download url
     * @return flag download success or not
     */
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg url: String?): Boolean {
        try {
            return if (downloadLinks != null) {
                multiDownload(downloadLinks!!, url[1])
            } else {
                singleDownload(url[0].toString(), url[1])
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(DownloadManager::class.java.simpleName, "e : " + e.localizedMessage)
        }
        return false
    }

    /**
     * Function to single download
     *
     * @param link             Download link
     * @param downloadLocation Download downloadLocation
     * @return Flag or download success or not
     * @throws IOException
     */
    @Throws(IOException::class)
    fun singleDownload(link: String, downloadLocation: String?): Boolean {
        //file name
        val cacheControl: CacheControl? = null
        fileName = link.substring(link.lastIndexOf('/') + 1, link.length)
        filePath = downloadLocation //url[1];
        val splits = fileName!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        fileExtension = fileName!!.substring(fileName!!.lastIndexOf(".") + 1, fileName!!.length)

        //divided update notification
        notificationDivider = 0

        //new http connection
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(context!!.cacheDir, cacheSize.toLong())
        val httpClient: OkHttpClient =
            OkHttpClient.Builder().cache(cache).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES).build()
        //private OkHttpClient mOkHttpClient;
        val request: Request = Request.Builder()
            .url(link)
            .build()
        val call = httpClient.newCall(Request.Builder().url(link).get().build())
        val response = call.execute()
        if (notificationDownloaderFlag) showNotificationDownloader()

        //susses request
        return if (response.code == 200) {
            var inputStream: InputStream? = null
            var output: OutputStream? = null
            try {
                output = FileOutputStream("$filePath/$fileName")
            } catch (e: FileNotFoundException) {
                println(e.message)
            }
            try {
                //path response to input stream
                inputStream = response.body.byteStream()
                val buffer = ByteArray(DOWNLOAD_CHUNK_SIZE)
                var download: Long = 0
                val target = response.body.contentLength()
                val oneBlock = Math.round((target / 100).toFloat())
                val oneLoop = oneBlock / DOWNLOAD_CHUNK_SIZE
                //set progress zero and response length
                publishProgress(0L, target)
                while (true) {
                    val internetStatus: Int = Settingsss.checkInternetStatus(context!!)

                    //flag to stop download
                    if (stopDownload) {
                        Log.e(TAG, "cancel her: ")
                        flag = 1
                        break
                    }
                    notificationDivider++
                    //read buffer
                    val read = inputStream.read(buffer)
                    if (read == -1) break
                    download += read.toLong()
                    if (notificationDivider == oneLoop) {
                        publishProgress(download, target)
                        notificationDivider = 0
                    }
                    if (isCancelled) {
                        flag = 1
                        Log.e(TAG, "Stop: ")
                        //                        break;
                    }
                    output!!.write(buffer, 0, read)
                }
                Log.d(TAG, "Stop: $download")
                download == target
            } catch (e: IOException) {
                Log.d(TAG, "Stop: " + e.message)
                e.printStackTrace()
                false
            } finally {
                inputStream?.close()
            }
        } else {
            false
        }
    }

    //    /**
    //     * Function to multi download
    //     *
    //     * @param links            List of links
    //     * @param downloadLocation Download destination
    //     * @return Flag of download success or not
    //     * @throws IOException
    //     */
    @Synchronized
    @Throws(IOException::class)
    fun multiDownload(links: List<String>, downloadLocation: String?): Boolean {
        if (notificationDownloaderFlag) showNotificationDownloader()
        var counter = 0
        publishProgress(0L, java.lang.Long.valueOf(links.size.toLong()))
        //foreach for the all links
        for (linkItem in links) {

            //flag to stop download
            if (stopDownload) break

            //update progress
            publishProgress(
                java.lang.Long.valueOf(counter++.toLong()),
                java.lang.Long.valueOf(links.size.toLong())
            )

            //file name
            fileName = linkItem.substring(linkItem.lastIndexOf('/') + 1, linkItem.length)
            filePath = downloadLocation
            val splits = fileName!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            fileExtension = fileName!!.substring(fileName!!.lastIndexOf(".") + 1, fileName!!.length)

            //divided update notification
            notificationDivider = 0

            //new http connection
            val httpClient: OkHttpClient =
                OkHttpClient.Builder().connectTimeout(15, TimeUnit.MINUTES)
                    .readTimeout(15, TimeUnit.MINUTES)
                    .build()
            val call = httpClient.newCall(Request.Builder().url(linkItem).get().build())
            val response = call.execute()


            //susses request
            // if (response.code() == 200) {
            var inputStream: InputStream? = null
            var output: OutputStream? = null
            try {
                //path response to input stream
                inputStream = response.body.byteStream()
                output = FileOutputStream("$filePath/$fileName")
                val buffer = ByteArray(DOWNLOAD_CHUNK_SIZE)
                //set progress zero and response length
                while (true) {

                    //flag to stop download
                    if (stopDownload) {
                        Log.e(TAG, "cancel her: ")
                        flag = 1
                        break
                    }
                    notificationDivider++
                    //read buffer
                    val read = inputStream.read(buffer)
                    if (read == -1) break
                    if (isCancelled) {
                        flag = 1

//                            break;
                    }
                    output.write(buffer, 0, read)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            } finally {
                inputStream?.close()
            }
        }
        return true
    }

    /**
     * Function show download information
     *
     * @param values download information values
     */
    @Deprecated("Deprecated in Java")
    override fun onProgressUpdate(vararg values: Long?) {
        if (downloadProgressBar != null) {
            downloadProgressBar!!.max = values[1]!!.toInt()
            downloadProgressBar!!.progress = values[0]!!.toInt()
        }
        if (downloadInfo != null) {
            downloadInfo!!.text =
                (values[1]!! / 1000000).toString() + "/" + values[0]!! / 1000000 + " (" + (values[0]!! / 1000000 * 100 / (values[1]!! / 1000000)).toInt() + "% )"
        }
        if (notificationDownloaderFlag) {
            //remoteViews.setProgressBar(R.id.progressBar2, , , false);
            builder!!.setProgress(values[1]!!.toInt(), values[0]!!.toInt(), false)
            notificationManager!!.notify(0, builder!!.build())
        }
        if (context != null) {
            if (context is Service) {
                val i = Intent(AudioAppConstants.Download.INTENT)
                i.putExtra(AudioAppConstants.Download.NUMBER, values[0])
                i.putExtra(AudioAppConstants.Download.MAX, values[1])
                i.putExtra(AudioAppConstants.Download.TYPE, downloadType)
                i.putExtra(
                    AudioAppConstants.Download.DOWNLOAD,
                    AudioAppConstants.Download.IN_DOWNLOAD
                )
                LocalBroadcastManager.getInstance(context as Service).sendBroadcast(i)
            }
        }
    }

    override fun onCancelled() {
        super.onCancelled()
    }

    /**
     * Action after download finished of not
     *
     * @param result flag download success or not
     */
    override fun onPostExecute(result: Boolean) {

        //notify download complete
        //notify download cancel
        if (flag == 1) {
            Toast.makeText(
                context,
                context!!.getString(R.string.success_download_canceled), Toast.LENGTH_LONG
            ).show()
            File("$filePath/$fileName").delete()
            showcancelNotification()
            //            downloadProgressBar.setProgress(0);
            notificationManager!!.cancel(0)
        } else {
            //notify download cancel
            if (context != null) if (flag == 1) {
                Toast.makeText(
                    context,
                    context!!.getString(R.string.success_download_canceled), Toast.LENGTH_LONG
                ).show()
                showcancelNotification()
            } else {
                Toast.makeText(
                    context,
                    if (result) context!!.getString(R.string.download_complete) else "Download failed due to the connection is lost",
                    Toast.LENGTH_LONG
                ).show()
            }
            //pass the download statue to notification
            if (notificationDownloaderFlag) {
                try {
                    notificationManager!!.cancel(0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if (result) {
                    showCompleteNotification()
                    AppPreference.DownloadStatues(AudioAppConstants.Preferences.DOWNLOAD_SUCCESS)
                } else {
                    showFailedNotification()
                    AppPreference.DownloadStatues(AudioAppConstants.Preferences.DOWNLOAD_FAILED)
                }
            }
        }
        //run extraction service if zip file or stop service
        if (fileExtension!!.lowercase(Locale.getDefault()) == "zip" && result) {
            Log.i("CHECK_INTERNET_LOST", "in zipping")
            //   new UnZipping(context , downloadType).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, filePath, fileName);
        } else {
            Log.i("CHECK_INTERNET_LOST", "send broad cast result : $result")
            //send broadcast of success or failed
            LocalBroadcastManager.getInstance(context!!).sendBroadcast(
                Intent(AudioAppConstants.Download.INTENT)
                    .putExtra(
                        AudioAppConstants.Download.DOWNLOAD,
                        if (result) AudioAppConstants.Download.SUCCESS else AudioAppConstants.Download.FAILED
                    )
                    .putExtra(AudioAppConstants.Download.TYPE, downloadType)
            )
            if (context is Service) {
                (context as Service).stopService(Intent(context, DownloadService::class.java))
                //  context.stopService(new Intent(context , DownloadTafseerService.class));
            }
        }
    }

    /**
     * Init notification channels for android 8.0 and higher
     */
    private fun createNotificationChannel(context: Context?): String {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        channel.description = CHANNEL_DESCRIPTION
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.setShowBadge(true)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager = context!!.getSystemService(
            NotificationManager::class.java
        )
        notificationManager.createNotificationChannel(channel)
        return CHANNEL_ID
    }

    /**
     * Initialize and show notification of download statue
     */
    fun showNotificationDownloader() {
        remoteViews = RemoteViews(context!!.packageName, R.layout.notification_download_progress)
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = createNotificationChannel(context)
            NotificationCompat.Builder(context!!, channelID)
        } else {
            NotificationCompat.Builder(context!!)
        }
        builder!!.setSmallIcon(if (aboveLollipopFlag) R.drawable.ic_quran_trans else R.drawable.logo)
            .setColor(Color.parseColor("#3E686A"))
            .setProgress(100, 0, false)
            .setContentTitle(context!!.getString(R.string.app_name))
            .setContentText(context!!.getString(R.string.download) + "")
            .setOngoing(true)
        builder!!.setContentIntent(notificationPending)
        notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager!!.notify(0, builder!!.build())
    }

    /**
     * Initialize and show notification of download completes
     */
    @SuppressLint("RemoteViewLayout")
    fun showCompleteNotification() {
        remoteViews = RemoteViews(context!!.packageName, R.layout.notification_download_finished)
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = createNotificationChannel(context)
            NotificationCompat.Builder(context!!, channelID)
        } else {
            NotificationCompat.Builder(context!!)
        }
        builder!!.setSmallIcon(if (aboveLollipopFlag) R.drawable.ic_quran_trans else R.drawable.logo)
            .setContentTitle(context!!.getString(R.string.app_name))
            .setContentText(context!!.getString(R.string.download_complete)).color =
            Color.parseColor("#3E686A")
        builder!!.setContentIntent(notificationPending)
        notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager!!.notify(0, builder!!.build())
    }

    /**
     * Initialize and show notification of download failed
     */
    @SuppressLint("RemoteViewLayout")
    fun showFailedNotification() {
        remoteViews = RemoteViews(context!!.packageName, R.layout.notification_download_failed)
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = createNotificationChannel(context)
            NotificationCompat.Builder(context!!, channelID)
        } else {
            NotificationCompat.Builder(context!!)
        }
        builder!!.setSmallIcon(if (aboveLollipopFlag) R.drawable.ic_quran_trans else R.drawable.logo)
            .setContentTitle(context!!.getString(R.string.app_name))
            .setContentText(context!!.getString(R.string.download_failed)).color =
            Color.parseColor("#3E686A")
        notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager!!.notify(0, builder!!.build())
    }

    /**
     * Initialize and show notification of download canceled
     */
    @SuppressLint("RemoteViewLayout")
    fun showcancelNotification() {
        remoteViews = RemoteViews(context!!.packageName, R.layout.notification_download_failed)
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = createNotificationChannel(context)
            NotificationCompat.Builder(context!!, channelID)
        } else {
            NotificationCompat.Builder(context!!)
        }
        builder!!.setSmallIcon(if (aboveLollipopFlag) R.drawable.ic_quran_trans else R.drawable.logo)
            .setContentTitle(context!!.getString(R.string.app_name))
            .setContentText("canceled").color = Color.parseColor("#3E686A")
        notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager!!.notify(0, builder!!.build())
    }

    companion object {
        const val DOWNLOAD_CHUNK_SIZE = 1024 * 3
    }
}