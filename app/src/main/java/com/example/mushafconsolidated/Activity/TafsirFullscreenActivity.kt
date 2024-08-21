package com.example.mushafconsolidated.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import com.example.Constant.AYAH_ID
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.google.android.material.appbar.MaterialToolbar

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class TafsirFullscreenActivity : BaseActivity() {
    private val mContentView: View? = null

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    //  private ActivityTafsirFullscreenBinding binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tafsir_fullscreen)
        val bundle: Bundle? = intent.extras
        val sura = bundle?.getInt(SURAH_ID)
        val ayah = bundle?.getInt(AYAH_ID)
        val surahname = bundle?.getString(SURAH_ARABIC_NAME)
        val utils = Utils(this)
        val materialToolbar: MaterialToolbar = findViewById(R.id.toolbarmain)
        setSupportActionBar(materialToolbar)
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (materialToolbar != null) {
            setSupportActionBar(materialToolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        val viewmodel: QuranVIewModel by viewModels()

        viewmodel.getsurahayahVerses(sura!!, ayah!!).observe(this) {
            //   val list: List<QuranEntity?>? = Utils.getsurahayahVerses(sura!!, ayah!!)
            val actionBar: ActionBar? = supportActionBar
            val sourcelable: TextView = findViewById(R.id.tvSourceLabel)
            val tafsir: TextView = findViewById(R.id.tvTafsir)
            val translation: TextView = findViewById(R.id.tvTranslation)
            val tvaryah: TextView = findViewById(R.id.tvData)
            val button: Button = findViewById(R.id.detailsbutton)
            button.text = "$sura:$ayah $surahname"
            sourcelable.text = "Arabic Ayah"
            val tafsir_kathir = it?.get(0)?.tafsir_kathir
            val tafsir_kathirs = tafsir_kathir?.replace("<b>", "")
            val tafsir_kathissr = tafsir_kathirs?.replace("</b>", "")
            tafsir.text = tafsir_kathissr
            translation.text = it!![0].translation
            tvaryah.text = it[0].qurantext
        }
    }

    override fun onBackPressed() {
        // code here to show dialog
        super.onBackPressed() // optional depending on your needs
        this.finish()
    }

    companion object {
    }
}