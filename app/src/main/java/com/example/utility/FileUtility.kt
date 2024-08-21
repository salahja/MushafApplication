import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mushafconsolidated.Activity.Data
import com.example.mushafconsolidated.receiversimport.AudioAppConstants
import com.example.mushafconsolidated.receiversimport.QuranValidateSources
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException

class FileUtility(var context: Context) {
    //to be delted
    fun writetofile(
        filename: String?,
        surahId: Int,
        verseId: Int,
        wordno: Int,
        wordsAr: String
    ): Int {
        val ammended = ArrayList<String>()
        ammended.add("$surahId|$verseId|$wordno|$wordsAr|")
        val state = Environment.getExternalStorageState()
        var status = 0
        if (Environment.MEDIA_MOUNTED == state) {
            var myWriter: FileWriter
            var s: String
            if (checkPermission()) {
                val sdcard = Environment.getExternalStorageDirectory()
                val dir = File(sdcard.absolutePath + "/text/")
                dir.mkdir()
                val file = File(dir, filename)
                val os: FileOutputStream
                try {
                    os = FileOutputStream(file, true)
                    //      os.write(harfNasbIndexArrayList.toString().getBytes());
                    for (str in ammended) {
                        os.write(str.toByteArray())
                        val newline = "\n"
                        os.write(newline.toByteArray())
                    }
                    os.close()
                    status = 1
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                requestPermission() // Code for permission
            }
        }
        return status
    }

    fun writetofileJason(
        filename: String?,
        surahId: Int,

        jsonString: String
    ): Int {

        var filePathJson =
            ""
        val state = Environment.getExternalStorageState()
        var status = 0
        if (Environment.MEDIA_MOUNTED == state) {
            var myWriter: FileWriter
            var s: String
            val dir = QuranValidateSources.getSaveDirs(context, surahId)
            val filepath =
                dir.toString() + "/" + surahId +  AudioAppConstants.Extensions.Companion.JSON
                val app_folder_path =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                        .toString() + "/surah/" + surahId+".json"
                val f = File(app_folder_path)
                val path = f.absolutePath
                val file = File(app_folder_path)
                if (!file.exists()) file.mkdirs()
                filePathJson =
                    Data.getFilePathJson(context, app_folder_path, surahId.toString(), jsonString)


                // val filepath =
                //    dirs.toString() + "/" + surahId +  AudioAppConstants.Extensions.Companion.JSON
                //   val file: File = File(filepath)
                //   val sdcard = Environment.getExternalStorageDirectory()
                //   val dir = File(sdcard.absolutePath + "/text/")
                //   dirs.mkdir()

                //  val file = File(dir, filename)
                val os: FileOutputStream
                try {
                    os = FileOutputStream(filePathJson, true)
                    //      os.write(harfNasbIndexArrayList.toString().getBytes());

                    os.write(jsonString.toByteArray())
                    val newline = "\n"
                    os.write(newline.toByteArray())

                    os.close()
                    status = 1
                } catch (e: IOException) {
                    e.printStackTrace()
                }

        }
        return status
    }


    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                (context as Activity),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                context as Activity,
                "Write External Storage permission allows us to create files. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                (context as Activity),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            (context as Activity),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        @JvmStatic
        fun createFileInAppDirectory(context: Context, fileName: String, json: String): File? {
            val appDirectory = createAppDirectoryInDownloads(context)
            if (appDirectory != null) {
                val file = File(appDirectory, fileName)
                try {
                    if (!file.exists()) {
                        val fileCreated = file.createNewFile()
                        if (!fileCreated) {
                            // Failed to create the file
                            return null
                        }
                    }
                    val os: FileOutputStream
                    try {
                        os = FileOutputStream(file, true)
                        //      os.write(harfNasbIndexArrayList.toString().getBytes());

                        os.write(json.toByteArray())
                        val newline = "\n"
                        os.write(newline.toByteArray())

                        os.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return file
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        fun createAppDirectoryInDownloads(context: Context): File? {
            val downloadsDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val appDirectory = File(downloadsDirectory, "qurangrammar")

            if (!appDirectory.exists()) {
                val directoryCreated = appDirectory.mkdir()
                if (!directoryCreated) {
                    // Failed to create the directory
                    return null
                }
            }

            return appDirectory
        }
    }
}