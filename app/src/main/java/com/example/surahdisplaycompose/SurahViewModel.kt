package com.example.surahdisplaycompose


import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.compose.SurahModelList
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("SuspiciousIndentation")
class SurahViewModel(
    mApplication: Application,

    ) : ViewModel() {
    val builder = AlertDialog.Builder(mApplication)
    var listss: ArrayList<String> = ArrayList<String>()
    val dialog = builder.create()
    var open = MutableLiveData<Boolean>()
    private lateinit var util: Utils

    // var verbroot: String = "حمد"
    private lateinit var shared: SharedPreferences
    lateinit var lemma: String
    private val _cards = MutableStateFlow(listOf<SurahModelList>())


    val cards: StateFlow<List<SurahModelList>> get() = _cards

    //  private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    var counter = 0;

    //   val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList
    private var nounCorpusArrayList: ArrayList<NounCorpusBreakup>? = null
    private var verbCorpusArray: ArrayList<VerbCorpusBreakup>? = null
    private var occurances: ArrayList<CorpusNounWbwOccurance>? = null
    private var surahlist: ArrayList<ChaptersAnaEntity>? = null
    val loading = mutableStateOf(true)

    init {
        shared = PreferenceManager.getDefaultSharedPreferences(mApplication)
        var job = Job()
        util = Utils(mApplication)

        surahlist()


    }

    private fun surahlist() {
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<SurahModelList>()
                surahlist = util.getAllAnaChapters() as ArrayList<ChaptersAnaEntity>?


            }
            //    closeDialog()

        }

    }


    fun startThread() {
        TODO("Not yet implemented")
    }
}
