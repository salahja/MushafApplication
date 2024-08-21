package com.example.compose


import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.Constant
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.QuranMorphologyDetails
import com.example.utility.CorpusUtilityorig
import com.example.utility.CorpusUtilityorig.Companion.getSpannableVerses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("SuspiciousIndentation")
class CardsViewModel(
    mApplication: Application,
    verbroot: String,
    nounroot: String,
    isharf: Boolean
) : ViewModel() {
    val builder = AlertDialog.Builder(mApplication)
    var listss: ArrayList<String> = ArrayList<String>()
    val dialog = builder.create()
    var open = MutableLiveData<Boolean>()
    private lateinit var util: Utils

    // var verbroot: String = "حمد"
    private lateinit var shared: SharedPreferences
    lateinit var lemma: String
    private val _cards = MutableStateFlow(listOf<ExpandableCardModelSpannableLists>())


    val cards: StateFlow<List<ExpandableCardModelSpannableLists>> get() = _cards
    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    var counter = 0;
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList
    private var nounCorpusArrayList: ArrayList<NounCorpusBreakup>? = null
    private var verbCorpusArray: ArrayList<VerbCorpusBreakup>? = null
    private var occurances: ArrayList<CorpusNounWbwOccurance>? = null
    val loading = mutableStateOf(true)

    init {
        shared = PreferenceManager.getDefaultSharedPreferences(mApplication)
        var job = Job()
        util = Utils(mApplication)
        if (isharf) {
            getZarf(nounroot, isharf)
        } else
            getNounData(nounroot, verbroot)


    }

    private fun getZarf(nounroot: String, isharf: Boolean) {
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ExpandableCardModelSpannableLists>()
                occurances =
                    util.getnounoccuranceHarfNasbZarf(nounroot) as ArrayList<CorpusNounWbwOccurance>?
                nounCorpusArrayList = nounroot.trim()
                    .let { util.getNounBreakup(it) } as ArrayList<NounCorpusBreakup>?

                for (vers in occurances!!) {
                    //    alist.add("");
                    if (isharf) {
                        lemma = ""
                    }
                    //   lemma = vers.lemma!!
                    val lists: ArrayList<SpannableString> = ArrayList<SpannableString>()
                    var titlspan: String = ""
                    val sb = StringBuilder()
                    val spannableVerses = getSpannableVerses(
                        vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                        vers.qurantext!!
                    )
                    sb.append(vers.surah).append(":").append(vers.ayah).append(":")
                        .append(vers.wordno).append("-").append(vers.en).append("-")
                    val ref = SpannableString(sb.toString())
                    ref.setSpan(
                        Constant.particlespanDark,
                        0,
                        sb.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val which = shared.getString("selecttranslation", "en_sahih")
                    var trans: String? = null
                    when (which) {
                        "en_sahih" -> trans = SpannableString.valueOf(vers.translation).toString()
                        "ur_jalalayn" -> trans =
                            SpannableString.valueOf(vers.ur_jalalayn).toString()

                        "en_jalalayn" -> trans =
                            SpannableString.valueOf(vers.en_jalalayn).toString()
                    }

                    val istimeadverb =
                        vers.tagone.equals("T") || vers.tagtwo.equals("T") || vers.tagthree.equals("T") || vers.tagfour.equals(
                            "T"
                        )
                    val islocation =
                        vers.tagone.equals("LOC") || vers.tagtwo.equals("LOC") || vers.tagthree.equals(
                            "LOC"
                        ) || vers.tagfour.equals(
                            "LOC"
                        )
                    val isaccusaiveparticle =
                        vers.tagone.equals("ACC") || vers.tagtwo.equals("ACC") || vers.tagthree.equals(
                            "ACC"
                        ) || vers.tagfour.equals(
                            "ACC"
                        )



                    if (istimeadverb) {
                        titlspan = "Time Adverb"
                    }
                    if (islocation) {
                        titlspan = " accusative location adverb"
                    }
                    if (isaccusaiveparticle) {

                        titlspan = "accusative particle "
                    }

                    titlspan += vers.araone + vers.aratwo


                    //    if (trans != null) {
                    //      lists.add(trans)
                    //  }

                    val charSequence = TextUtils.concat(ref, "\n ", spannableVerses)
                    val contentText = SpannableString(charSequence)
                    lists.add(contentText)

                    testList += ExpandableCardModelSpannableLists(
                        id = counter++,
                        titlspan,
                        lemma,
                        lists
                    )
                }





                _cards.emit(testList)


            }
            //    closeDialog()
            loading.value = false
        }

    }

    private fun getNounData(nounroot: String, verbroot: String) {
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ExpandableCardModelSpannableLists>()

                nounCorpusArrayList = nounroot.trim()
                    .let { util.getNounBreakup(it) } as ArrayList<NounCorpusBreakup>?
                verbCorpusArray = verbroot.trim()
                    .let { util.getVerbBreakUp(it) } as ArrayList<VerbCorpusBreakup>?


                for (noun in nounCorpusArrayList!!) {
                    var nountitleStrBuilder = StringBuilder()

                    nountitleStrBuilder = stringBuilder(noun, nountitleStrBuilder)
                    lemma = noun.lemma_a.toString()
                    val verses: ArrayList<CorpusNounWbwOccurance> =
                        util.getNounOccuranceBreakVerses(noun.lemma_a!!)
                                as ArrayList<CorpusNounWbwOccurance>
                    val lists: ArrayList<SpannableString> = ArrayList<SpannableString>()

                    for (nounverse in verses) {
                        val nounverseBuilder = StringBuilder()
                        val which = shared.getString(
                            "selecttranslation",
                            "en_sahih"
                        )
                        NounVerseBuilder(nounverse, nounverseBuilder, which, lists)
                        val span = SpannableString(nounverseBuilder)
                        span.setSpan(
                            ForegroundColorSpan(Color.CYAN),
                            0,
                            nounverseBuilder.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )

                        lists.add(span)
                    }
                    testList += ExpandableCardModelSpannableLists(
                        id = counter++,
                        title = nountitleStrBuilder.toString(),
                        lemma,
                        lists
                    )

                }

                for (verbbreakup in verbCorpusArray!!) {
                    var verbtitlesbuilder = StringBuilder()

                    verbtitlesbuilder = stringBuilder1(verbbreakup, verbtitlesbuilder)


                    val verses: List<CorpusVerbWbwOccurance?>? = verbbreakup.lemma_a?.let {
                        util.getVerbOccuranceBreakVerses(
                            it
                        )
                    }

                    val lists: ArrayList<SpannableString> = ArrayList<SpannableString>()
                    lemma = verbbreakup.lemma_a.toString()
                    if (verses != null) {
                        for (ver in verses) {
                            val verbversBuilder = StringBuilder()
                            val which = shared.getString(
                                "selecttranslation",
                                "en_sahih"
                            )


                            VerseVerseBuilder(verbversBuilder, ver, which)




                            lists.add(SpannableString.valueOf(verbversBuilder))


                        }
                    }





                    testList += ExpandableCardModelSpannableLists(
                        id = counter++,
                        title = verbtitlesbuilder.toString(),
                        lemma,
                        lists
                    )

                }





                _cards.emit(testList)


            }
            //    closeDialog()
            loading.value = false
        }

    }


    private fun VerseVerseBuilder(
        verbversBuilder: StringBuilder,
        ver: CorpusVerbWbwOccurance?,
        which: String?,
    ) {
        //     val s = ver?.araone + ver?.aratwo + ver.arathree + ver.arafour + ver.arafive
        val spannableVerses =
            CorpusUtilityorig.getSpannableVerses(
                ver!!.araone + ver.aratwo + ver.arathree + ver.arafour + ver.arafive,
                ver.qurantext!!
            )
        verbversBuilder.append(ver.surah).append(":").append(ver.ayah)
            .append(":").append(ver.wordno).append("-")
            .append(ver.en).append("-").append("\n").append("\n")
        verbversBuilder.append(spannableVerses).append("\n").append("\n")


        if (which.equals("en_sahh")) {
            verbversBuilder.append(ver.translation).append("\n").append("\n")
        } else if (which.equals("ur_jalalayn")) {

            verbversBuilder.append(ver.ur_jalalayn).append("\n").append("\n")
        } else if (which.equals("en_jalalayn")) {

            verbversBuilder.append(ver.en_jalalayn).append("\n").append("\n")
        } else if (which.equals("en_arberry")) {

            verbversBuilder.append(ver.en_arberry).append("\n").append("\n")
        }
    }

    private fun stringBuilder1(
        verbbreakup: VerbCorpusBreakup,
        verbtitlesbuilder: StringBuilder,
    ): StringBuilder {
        var verbtitlesbuilder1 = verbtitlesbuilder
        if (verbbreakup.form == "I") {
            verbtitlesbuilder1 = StringBuilder()
            val mujarrad = java.lang.String.valueOf(
                QuranMorphologyDetails.getThulathiName(verbbreakup.thulathibab)
            )
            verbtitlesbuilder1.append(verbbreakup.count).append("-").append("times").append(" ")
                .append(verbbreakup.lemma_a).append(" ").append("occurs as the")
                .append(" ").append(mujarrad)
            //     expandVerbVerses[sb.toString()] = list
        } else {
            verbtitlesbuilder1 = StringBuilder()
            val s = verbbreakup.tense?.let { QuranMorphologyDetails.expandTags(it) }
            val s1 = verbbreakup.voice?.let { QuranMorphologyDetails.expandTags(it) }
            val mazeed = java.lang.String.valueOf(
                QuranMorphologyDetails.getFormName(verbbreakup.form)
            )
            verbtitlesbuilder1.append(verbbreakup.count).append("-").append("times").append(" ")
                .append(verbbreakup.lemma_a).append(" ").append("occurs as the")
                .append(" ").append(mazeed)
                .append(" ").append(s).append(" ").append(" ").append(s1)
            // expandVerbVerses[sb.toString()] = list
        }
        return verbtitlesbuilder1
    }

    private fun NounVerseBuilder(
        nounverse: CorpusNounWbwOccurance,
        nounverseBuilder: StringBuilder,
        which: String?,
        lists: ArrayList<SpannableString>,
    ) {
        val s =
            nounverse.araone + nounverse.aratwo + nounverse.arathree + nounverse.arafour + nounverse.arafive
        nounverseBuilder.append(nounverse.surah).append(":").append(nounverse.ayah)
            .append(":").append(nounverse.wordno).append("-")

            .append(nounverse.en).append("-").append("\n").append("\n")
        nounverseBuilder.append(nounverse.qurantext).append("\n").append("\n")

        if (which.equals("en_sahh")) {
            nounverseBuilder.append(nounverse.translation).append("\n").append("\n")
        } else if (which.equals("ur_jalalayn")) {

            nounverseBuilder.append(nounverse.ur_jalalayn).append("\n").append("\n")
        } else if (which.equals("en_jalalayn")) {

            nounverseBuilder.append(nounverse.en_jalalayn).append("\n").append("\n")
        } else if (which.equals("en_arberry")) {

            nounverseBuilder.append(nounverse.en_arberry).append("\n").append("\n")
            lists.add(SpannableString.valueOf(nounverseBuilder))

        }
    }

    private fun stringBuilder(
        noun: NounCorpusBreakup,
        nountitleStrBuilder: StringBuilder,
    ): StringBuilder {
        var nountitleStrBuilder1 = nountitleStrBuilder
        if (noun.form == "null") {
            nountitleStrBuilder1 = StringBuilder()
            val nounexpand = QuranMorphologyDetails.expandTags(noun.tag!!)
            var times = ""
            times = if (noun.count == 1) {
                "Once"
            } else {
                val count = noun.count
                val timess = count.toString()
                "$timess-times"
            }
            nountitleStrBuilder1.append(times).append(" ").append(noun.lemma_a).append(" ")
                .append("occurs as the").append(" ").append(nounexpand)

        } else {
            nountitleStrBuilder1 = StringBuilder()
            val s = QuranMorphologyDetails.expandTags(noun.propone + noun.proptwo)
            //  String s1 = QuranMorphologyDetails.expandTags(noun.getProptwo());
            val s2 = QuranMorphologyDetails.expandTags(noun.tag!!)
            var times = ""
            times = if (noun.count == 1) {
                "Once"
            } else {
                val count = noun.count
                val timess = count.toString()
                "$timess-times"
            }
            nountitleStrBuilder1.append(times).append(" ").append(noun.lemma_a).append(" ")
                .append("occurs as the").append(" ").append(noun.form)
                .append(" ")
            if (s != "null") {
                nountitleStrBuilder1.append(s).append(" ").append(" ")
            }
            nountitleStrBuilder1.append(s2)

        }
        return nountitleStrBuilder1
    }


    /*    private fun getFakeData() {
            viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    val testList = arrayListOf<ExpandableCardModel>()
                    for (noun in nounCorpusArrayList!!) {
                        var sb = StringBuilder()
                        sb.append(noun.araword).append("occurs").append(":").append(noun.count)
                            .append("as").append(noun.tag)
                        sb.append(noun.surah).append(":").append(noun.ayah)
                        val verses: ArrayList<CorpusNounWbwOccurance> =
                            util.getNounOccuranceBreakVerses(noun.lemma_a!!)
                             as ArrayList<CorpusNounWbwOccurance>
                        val vlist: ArrayList<String> =
                            ArrayList()

                        for(vers in verses){
                            vlist.add(vers.qurantext!!)

                        }



                        testList += ExpandableCardModel(id = noun.id, title = sb.toString(),vlist)
                        _cards.emit(testList)
                    }

                    *//* repeat(20) { testList += ExpandableCardModel(id = it, title = "Card $it") }
                 _cards.emit(testList)*//*
            }
        }
    }*/

    fun onCardArrowClicked(cardId: Int) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    fun startThread() {
        TODO("Not yet implemented")
    }
}
