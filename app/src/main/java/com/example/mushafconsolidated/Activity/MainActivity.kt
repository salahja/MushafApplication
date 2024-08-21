package com.example.mushafconsolidated.Activity


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.window.layout.WindowMetricsCalculator
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.settingsimport.Constants.Companion.DATABASENAME
import com.example.mushafconsolidated.settingsimport.Constants.Companion.DATABASEZIP
import com.example.mushafconsolidated.settingsimport.Constants.Companion.FILEPATH
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.Executors
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class MainActivity : BaseActivity() {
    private var newquran: File? = null
    var recview: RecyclerView? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        switchTheme("brown")
        super.onCreate(savedInstanceState)
        val hasPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        ) == PackageManager.PERMISSION_GRANTED
        computeWindowSizeClasses()
        //  setContentView(R.layout.fragment_reading);
        setContentView(R.layout.main_activity)
        recview = findViewById(R.id.recycler_views)
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val SPL = 1
        if (sp.getInt("spl", 0) != SPL) {
            PreferenceManager.setDefaultValues(this, R.xml.preferences, true)
            //  PreferenceManager.setDefaultValues(this, R.xml.prefs2, true);
            sp.edit().putInt("spl", SPL).apply()
        }
        newquran = File("$FILEPATH/$DATABASENAME")
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S) {
            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_WRITE_STORAGE
                )
            } else {
                try {
                    validateFilesAndDownload()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else {
            try {
                validateFilesAndDownload()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        //  PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    private fun computeWindowSizeClasses() {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val editor = PreferenceManager.getDefaultSharedPreferences(this@MainActivity).edit()
        val widthDp = metrics.bounds.width() / resources.displayMetrics.density
        if (widthDp < 600f) {
            editor.putString("width", "compactWidth")
            editor.apply()
        } else if (widthDp < 840f) {
            editor.putString("width", "mediumWidth")
            editor.apply()
        } else {
            // widthWindowSizeClass = WindowSizeClass.EXPANDED;
            editor.putString("width", "expandedWidth")
            editor.apply()
        }
        val heightDp = metrics.bounds.height() / resources.displayMetrics.density
        val heightWindowSizeClass: WindowSizeClass
        heightWindowSizeClass = if (heightDp < 480f) {
            WindowSizeClass.COMPACT
        } else if (heightDp < 900f) {
            WindowSizeClass.MEDIUM
        } else {
            WindowSizeClass.EXPANDED
        }
        // Use widthWindowSizeClass and heightWindowSizeClass
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //check if permission had taken or not
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //valid to download or not
                try {
                    validateFilesAndDownload()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, getString(R.string.permission), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    @Throws(IOException::class)
    private fun validateFilesAndDownload() {
        if (!newquran!!.exists()) {
            // first install copy newquran.db.zip and unzip
            //   new CopyDatabase().execute();
            CopyDatbases()
        } else {
            /*      LinearLayoutManager ln=new LinearLayoutManager(this);
            recview.setLayoutManager(ln);
                List<Page> pages = new ArrayList<>();
                Page page;
                Utils utils=new Utils(this);
                List<QuranEntity> ayahItems;
                for (int i = 1; i <= 604; i++) {
                    ayahItems = utils.getAyahsByPage(i);
                    if (ayahItems.size() > 0) {
                        page = new Page();
                        page.setAyahItems(ayahItems);
                        page.setPageNum(i);
                        page.setJuz(ayahItems.get(0).getJuz());
                        pages.add(page);
                    }
                }

                fullQuranPages = new ArrayList<>(pages);
            PageAdapter pageAdapter=new PageAdapter(pages,this);
            recview.setAdapter(pageAdapter);*/
            val homeactivity = Intent(this@MainActivity, QuranGrammarAct::class.java)
            startActivity(homeactivity)
            //   MainActivity.this.finish();
            //   initnavigation();
        }
    }

    private fun CopyDatbases() {
        val ex = Executors.newSingleThreadExecutor()
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        val dialog = builder.create()
        ex.execute(object : Runnable {
            override fun run() {
                runOnUiThread { dialog.show() }
                val canWrie = canWriteInSDCard()
                if (canWrie) {
                    try {
                        val databaseDirectory = File(FILEPATH)
                        if (!databaseDirectory.exists()) {
                            val cr = databaseDirectory.mkdirs()
                            println(cr)
                        }
                        val databaseFile = File(databaseDirectory, DATABASEZIP)
                        databaseFile.parentFile
                        if (!databaseFile.exists()) {
                            databaseFile.createNewFile()
                        }
                        //    InputStream inputStream = getApplicationContext().getAssets().open("newquran.db");
                        val inputStream = applicationContext.assets.open(DATABASEZIP)
                        val outputStream = FileOutputStream(databaseFile)
                        //   publishProgress(0, fileSize);
                        var copylength = 0
                        val buffer = ByteArray(1024)
                        while (true) {
                            val read = inputStream.read(buffer)
                            if (read == -1) break
                            copylength += read
                            //   publishProgress(copylength, fileSize);
                            outputStream.write(buffer, 0, read)
                        }
                        outputStream.flush()
                        outputStream.close()
                        inputStream.close()
                    } catch (e1: IOException) {
                        e1.printStackTrace()
                    }
                }
                runOnUiThread {
                    val zipfile =
                        File(getExternalFilesDir(null)!!.absolutePath + getString(R.string.app_folder_path) + File.separator + DATABASEZIP)
                    val targetDirectory = File(FILEPATH)
                    val mainDatabasesZIP = File(zipfile.toString())
                    var zis: ZipInputStream? = null
                    var progress = 1
                    try {
                        zis = ZipInputStream(
                            BufferedInputStream(
                                FileInputStream(mainDatabasesZIP)
                            )
                        )
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                        val dialog1 =
                            AlertDialog.Builder(this@MainActivity)
                        dialog1.setMessage(e.cause.toString())
                        val alertDialog = dialog1.create()
                        alertDialog.show()
                    }
                    try {
                        var ze: ZipEntry
                        var count: Int
                        val buffer = ByteArray(8192)
                        try {
                            while (zis!!.nextEntry.also { ze = it } != null) {
                                val file = File(targetDirectory, ze.name)
                                val dir =
                                    if (ze.isDirectory) file else file.parentFile
                                if (!dir.isDirectory && !dir.mkdirs()) throw FileNotFoundException(
                                    "Failed to ensure directory: " + dir.absolutePath
                                )
                                if (ze.isDirectory) continue
                                FileOutputStream(file).use { fout ->
                                    progress += 1
                                    while (zis.read(buffer).also { count = it } != -1) {
                                        fout.write(buffer, 0, count)
                                        progress += 1
                                        //   progressBarDD.setProgress(progress);
                                    }
                                }
                            }
                        } catch (ex: NullPointerException) {
                            try {
                                zis!!.close()
                                mainDatabasesZIP.delete()
                                //     progressBarDD.dismiss();
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        try {
                            zis!!.close()
                            mainDatabasesZIP.delete()
                            //     progressBarDD.dismiss();
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                    ex.shutdown()
                    dialog.dismiss()
                    val zipintent = Intent(this@MainActivity, QuranGrammarAct::class.java)
                    startActivity(zipintent)
                    finish()
                }
            }

            private fun canWriteInSDCard(): Boolean {
                val state = Environment.getExternalStorageState()
                return Environment.MEDIA_MOUNTED == state
            }
        })
    }

    val defaultSaveRootPath: Boolean
        get() {
            var useExternalStorage = false
            val mounted = Environment.getExternalStorageState() == "mounted"
            val freeSpace = Environment.getExternalStorageDirectory().freeSpace
            if (mounted) {
                if (freeSpace > 0) {
                    useExternalStorage = true
                }
            }
            return useExternalStorage
        }

    enum class WindowSizeClass {
        COMPACT, MEDIUM, EXPANDED
    }

    companion object {
        private const val REQUEST_WRITE_STORAGE = 112
    }
}


