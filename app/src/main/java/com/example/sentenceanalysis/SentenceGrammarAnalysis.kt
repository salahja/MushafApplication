package com.example.sentenceanalysis

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.preference.PreferenceManager
import com.example.Constant
import com.example.compose.CardsViewModel
import com.skyyo.expandablelist.theme.AppTheme


class SentenceGrammarAnalysis : AppCompatActivity() {

    override fun onBackPressed() {
        super.onBackPressed()
    }

    // val darkTheme = themeViewModel.darkTheme.observeAsState(initial = true)
    private val cardsViewModel by viewModels<CardsViewModel>()
    private lateinit var shared: SharedPreferences
    var root: String? = null
    var thememode = false

    //   private val viewModel by viewModels<ExpandableListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bundle: Bundle? = intent.extras
            val sura = bundle?.getInt(Constant.SURAH_ID)
            val ayah = bundle?.getInt(Constant.AYAH_ID)
            this.shared =
                PreferenceManager.getDefaultSharedPreferences(this@SentenceGrammarAnalysis)
            val preferences = shared.getString("themepref", "dark")

            var systemInDarkTheme = isSystemInDarkTheme()
            if (preferences == "dark" || preferences == "blue" || preferences == "green") {
                thememode = true
                systemInDarkTheme = true
            } else {
                thememode = false
                systemInDarkTheme = false
            }

            val versemodel: ExpandableVerseViewModel = viewModel(factory = sura?.let {
                VerseAnalysisFctory(
                    it, ayah!!, thememode, 0
                )
            })
            //   VerseAnalysisScreen(versemodel,navController,  chapterid, verseid,)


            AppTheme(systemInDarkTheme) {
                Surface(color = MaterialTheme.colors.background) {


                    NewVerseAnalysisCardsScreen(versemodel)


                }
            }


        }


    }


}

class VerseAnalysisFctory(
    private val chapterid: Int,
    private val verseid: Int,
    private val thememode: Boolean,
    private val wbwchoice: Int


) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        ExpandableVerseViewModel(chapterid, verseid, thememode, wbwchoice) as T
}

