package com.example.mushafconsolidated.Activity

import Utility.ArabicLiterals.AlifMaksuraString
import Utility.ArabicLiterals.Hamza
import Utility.ArabicLiterals.LALIF
import Utility.ArabicLiterals.Ya
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.view.View
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnChildClickListener
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.example.Constant
import com.example.Constant.AYAHNUMBER
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.Constant.WORDNUMBER
import com.example.Constant.particlespanDark
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.Adapters.NounVerbOccuranceListAdapter
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanelexicon
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.QuranMorphologyDetails
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.utility.CorpusUtilityorig.Companion.getSpannableVerses
import com.example.utility.QuranGrammarApplication
import ru.dimorinny.floatingtextbutton.FloatingTextButton
import java.util.Objects
import java.util.concurrent.Executors
import java.util.regex.Pattern

open class WordOccuranceAct : BaseActivity() {
    private var expandableListView: ExpandableListView? = null
    private var harf = false
    lateinit var expandNounTitles: MutableList<String>
    lateinit var expandVerbTitles: List<String>
    var root: String? = null
    var dialog: AlertDialog? = null

    val expandNounVerses = LinkedHashMap<String, ArrayList<SpannableString>>()
    val expandVerbVerses = LinkedHashMap<String, ArrayList<SpannableString>>()
    lateinit var utils: Utils
    private var firstcolortat = 0
    private var maincolortag = 0
    private var pronouncolortag = 0
    private var fourcolortag = 0
    private var verbCorpusArrayList: ArrayList<VerbCorpusBreakup>? = null
    private var occurances: ArrayList<CorpusNounWbwOccurance>? = null
    private var nounCorpusArrayList: ArrayList<NounCorpusBreakup>? = null
    private lateinit var shared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expand_list)

        utils = Utils(this@WordOccuranceAct)
        // String preferences = SharedPref.themePreferences();
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
        shared = PreferenceManager.getDefaultSharedPreferences(this)
        val whichtranslation = shared.getString("selecttranslation", "en_sahih")
        val showtranslation = shared.getBoolean("prefs_show_translation", true)
        expandableListView = findViewById(R.id.expandableListView)
        val prefs =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        val preferences = prefs.getString("theme", "dark")
        if (preferences == "dark" || preferences == "blue" || preferences == "green") {
            firstcolortat = Constant.BCYAN
            maincolortag = Constant.BYELLOW
            pronouncolortag = Constant.BBLUE
            fourcolortag = Constant.BWHITE
        } else {
            firstcolortat = Constant.WBURNTUMBER
            maincolortag =
                ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.prussianblue)
            pronouncolortag = Constant.WMIDNIHTBLUE
            fourcolortag = Constant.GREENDARK
        }
        val callBackbutton: FloatingTextButton = findViewById(R.id.action_button)

        if (preferences == "dark") {
            val color: Int = resources.getColor(R.color.color_background_overlay)
            callBackbutton.backgroundColor = color
        } else {
            callBackbutton.backgroundColor = resources.getColor(R.color.color_background)
        }
        callBackbutton.setOnClickListener { view: View? ->
            //  Intent quranintnt = new Intent(VerbOccuranceAsynKAct.this, ReadingSurahPartActivity.class);
            finish()
        }
        val bundle: Bundle? = intent.extras
        root = bundle?.getString(Constant.QURAN_VERB_ROOT)
        if (root == "ACC" || root == "LOC" || root == "T") {
            occurances =
                utils.getnounoccuranceHarfNasbZarf(root!!) as ArrayList<CorpusNounWbwOccurance>?
            harf = true
            ExecuteNounOcurrance()
        } else {
            harf = false
            ExecuteNounOcurrance()
        }
    }

    private fun ExecuteNounOcurrance() {
        val ex = Executors.newSingleThreadExecutor()
        ex.execute {
            runOnUiThread { dialog!!.show() }

            val vroot = root!!.indexOf("ء")
            var nounroot: String? = ""
            val verb = root!!.indexOf("ا")
            var verbroot: String? = ""
            nounroot = if (vroot != -1) {
                root!!.replace("ء", "ا")
            } else {
                root
            }
            verbroot = if (verb != -1) {
                root!!.replace("ا", "ء")
            } else {
                root
            }
            nounCorpusArrayList =
                nounroot?.let { utils.getNounBreakup(it) } as ArrayList<NounCorpusBreakup>?
            verbCorpusArrayList =
                verbroot?.let { utils.getVerbBreakUp(it) } as ArrayList<VerbCorpusBreakup>?
            val alist = ArrayList<SpannableString>()
            if (harf) {
                for (vers in occurances!!) {
                    //    alist.add("");
                    val sb = StringBuilder()
                    val spannableVerses = getSpannableVerses(
                        vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                        vers.qurantext!!
                    )
                    sb.append(vers.surah).append(":").append(vers.ayah).append(":")
                        .append(vers.wordno).append("-").append(vers.en).append("-")
                    val ref = SpannableString(sb.toString())
                    ref.setSpan(particlespanDark, 0, sb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    val which = shared.getString("selecttranslation", "en_sahih")
                    var trans: SpannableString? = null
                    when (which) {
                        "en_sahih" -> trans = SpannableString.valueOf(vers.translation)
                        "ur_jalalayn" -> trans = SpannableString.valueOf(vers.ur_jalalayn)
                        "en_jalalayn" -> trans = SpannableString.valueOf(vers.en_jalalayn)
                    }
                    val charSequence = TextUtils.concat(ref, "\n ", spannableVerses)
                    alist.add(SpannableString.valueOf(charSequence))
                    if (trans != null) {
                        alist.add(trans)
                    }
                    expandNounVerses[sb.toString()] = alist
                }
            }
            verbandnouns()
            expandNounTitles = ArrayList(expandNounVerses.keys)
            expandVerbTitles = ArrayList(expandVerbVerses.keys)
            expandNounVerses.putAll(expandVerbVerses)
            expandNounTitles.addAll(expandVerbTitles)
            //post
            runOnUiThread {
                dialog!!.dismiss()
                //   listAdapter = new NounVerbOccuranceListAdapter(WordOccuranceAct.this, expandNounTitles, expandNounVerses);
                var listAdapter: NounVerbOccuranceListAdapter = NounVerbOccuranceListAdapter(
                    this@WordOccuranceAct,
                    expandNounTitles,
                    expandNounVerses,
                    expandVerbVerses,
                    expandVerbTitles
                )


                expandableListView?.setAdapter(listAdapter)
                expandableListView!!.setOnGroupExpandListener { groupPosition ->
                    val split = expandNounTitles[groupPosition].split("\\s".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    if (!harf) {
                        if (expandNounTitles[groupPosition].contains("Hans")) {
                            val ex =
                                Executors.newSingleThreadExecutor()
                            ex.execute(object : Runnable {
                                override fun run() {
                                    runOnUiThread { dialog!!.show() }
                                    var verbroot: String? = ""
                                    val indexOfHamza: Int = root!!.indexOf(Hamza)
                                    val indexofAlifMaksura: Int = root!!.indexOf(Ya)
                                    verbroot = if (indexOfHamza != -1) {
                                        root!!.replace(Hamza.toRegex(), LALIF)
                                    } else {
                                        root
                                    }
                                    if (indexofAlifMaksura != -1) {
                                        verbroot = verbroot?.replace(
                                            Ya.toRegex(),
                                            AlifMaksuraString
                                        )
                                    }
                                    var list: ArrayList<SpannableString> =
                                        ArrayList()
                                    //   ArrayList<CorpusNounWbwOccurance> verses = utils.getNounOccuranceBreakVerses(split[1]);
                                    val lanesDifinition: ArrayList<hanslexicon> =
                                        verbroot?.let { utils.getHansDifinition(it) } as ArrayList<hanslexicon>
                                    for (hans in lanesDifinition) {
                                        //  <p style="margin-left:200px; margin-right:50px;">
                                        list.add(SpannableString.valueOf(hans.definition))
                                        //
                                    }
                                    list = highLightParadigm(list) as ArrayList<SpannableString>
                                    val finalList: ArrayList<SpannableString> =
                                        list
                                    runOnUiThread {
                                        ex.shutdown()
                                        dialog!!.dismiss()
                                        expandNounVerses[expandNounTitles[groupPosition]] =
                                            finalList
                                        listAdapter.notifyDataSetChanged()
                                    }
                                }

                                private fun highLightParadigm(list: List<*>): MutableList<*> {
                                    val lists: MutableList<SpannableString> =
                                        ArrayList()
                                    val REGEX = "aor.([\\s\\S]){3}"
                                    val pattern = Pattern.compile(REGEX)
                                    for (l in list) {
                                        val replaceAll =
                                            l.toString().replace("<br></br>".toRegex(), "")
                                        val m = pattern.matcher(replaceAll)
                                        var sb: SpannableString? = null
                                        var indexof = 0
                                        if (m.find()) {
                                            println("Found value: " + m.group(0))
                                            println("Found value: " + m.group(1))
                                            indexof = l.toString().indexOf(m.group(0))
                                            sb = SpannableString(l.toString())
                                            sb.setSpan(
                                                particlespanDark,
                                                indexof,
                                                Objects.requireNonNull(
                                                    m.group(0)
                                                ).length + indexof,
                                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                            )
                                            lists.add(sb)
                                            //   System.out.println("Found value: " + m.group(2) );
                                        } else {
                                            lists.add(SpannableString.valueOf(replaceAll))
                                        }
                                    }
                                    return lists
                                }
                            })
                        } else if (expandNounTitles[groupPosition].contains("lanes")) {
                            val ex =
                                Executors.newSingleThreadExecutor()
                            ex.execute(object : Runnable {
                                override fun run() {
                                    runOnUiThread { dialog!!.show() }
                                    var list: MutableList<SpannableString?> =
                                        ArrayList()
                                    val lanesDifinition: ArrayList<lanelexicon> =
                                        utils.getLanesDifinition(root!!) as ArrayList<lanelexicon>
                                    for (lanes in lanesDifinition) {
                                        //  <p style="margin-left:200px; margin-right:50px;">
                                        list.add(SpannableString.valueOf(lanes.definition))
                                        //
                                    }
                                    list = highLightParadigm(list) as MutableList<SpannableString?>
                                    val finalList: List<*> = list
                                    runOnUiThread {
                                        ex.shutdown()
                                        dialog!!.dismiss()
                                        expandNounVerses[expandNounTitles[groupPosition]] =
                                            finalList as ArrayList<SpannableString>
                                        listAdapter.notifyDataSetChanged()
                                    }
                                }

                                private fun highLightParadigm(list: List<*>): MutableList<*> {
                                    val lists: MutableList<SpannableString> =
                                        ArrayList()
                                    val REGEX = "aor.([\\s\\S]){3}"
                                    val pattern = Pattern.compile(REGEX)
                                    for (l in list) {
                                        val replaceAll =
                                            l.toString().replace("<br></br>".toRegex(), "")
                                        val m = pattern.matcher(replaceAll)
                                        var sb: SpannableString? = null
                                        var indexof = 0
                                        if (m.find()) {
                                            println("Found value: " + m.group(0))
                                            println("Found value: " + m.group(1))
                                            indexof = l.toString().indexOf(
                                                Objects.requireNonNull(
                                                    m.group(0)
                                                )
                                            )
                                            sb = SpannableString(l.toString())
                                            sb!!.setSpan(
                                                particlespanDark,
                                                indexof,
                                                Objects.requireNonNull(
                                                    m.group(0)
                                                ).length + indexof,
                                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                            )
                                            lists.add(sb!!)
                                            //   System.out.println("Found value: " + m.group(2) );
                                        } else {
                                            lists.add(SpannableString.valueOf(replaceAll))
                                        }
                                    }
                                    return lists
                                }
                            })
                        } else if (expandNounTitles[groupPosition]
                                .contains("Noun") || expandNounTitles[groupPosition]
                                .contains("Adverb") || expandNounTitles[groupPosition]
                                .contains("Adjective")
                        ) {
                            val ex =
                                Executors.newSingleThreadExecutor()
                            val builder =
                                AlertDialog.Builder(this@WordOccuranceAct)
                            builder.setCancelable(false) // if you want user to wait for some process to finish,
                            builder.setView(R.layout.layout_loading_dialog)
                            val dialog = builder.create()
                            ex.execute {
                                runOnUiThread { dialog.show() }
                                val list: ArrayList<SpannableString> =
                                    ArrayList()
                                val verses: ArrayList<CorpusNounWbwOccurance> =
                                    utils.getNounOccuranceBreakVerses(
                                        split[1]
                                    ) as ArrayList<CorpusNounWbwOccurance>
                                for (vers in verses) {
                                    val sb = StringBuilder()
                                    SpannableString(vers.qurantext)
                                    val spannableVerses =
                                        getSpannableVerses(
                                            vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                                            vers.qurantext!!
                                        )
                                    sb.append(vers.surah).append(":").append(vers.ayah)
                                        .append(":").append(vers.wordno).append("-")
                                        .append(vers.en).append("-")
                                    val ref = SpannableString(sb.toString())
                                    ref.setSpan(
                                        particlespanDark,
                                        0,
                                        sb.length,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                    )
                                    val which = shared.getString(
                                        "selecttranslation",
                                        "en_sahih"
                                    )
                                    var trans: SpannableString? = null
                                    when (which) {
                                        "en_sahih" -> trans =
                                            SpannableString.valueOf(vers.translation)

                                        "ur_jalalayn" -> trans =
                                            SpannableString.valueOf(vers.ur_jalalayn)

                                        "en_jalalayn" -> trans =
                                            SpannableString.valueOf(vers.en_jalalayn)

                                        "en_arberry" -> trans =
                                            SpannableString.valueOf(vers.en_arberry)
                                    }
                                    val charSequence =
                                        TextUtils.concat(ref, "\n ", spannableVerses, "\n ", trans)
                                    list.add(SpannableString.valueOf(charSequence))
                                    //  list.add(SpannableString.valueOf(charSequence))
                                    // list.add(trans!!)
                                }
                                runOnUiThread(Runnable {
                                    ex.shutdown()
                                    dialog.dismiss()
                                    expandNounVerses[expandNounTitles[groupPosition]] =
                                        list
                                    listAdapter.notifyDataSetChanged()
                                })
                            }
                        } else {
                            val ex =
                                Executors.newSingleThreadExecutor()
                            ex.execute {
                                runOnUiThread(Runnable { dialog!!.show() })
                                val list: ArrayList<SpannableString> =
                                    ArrayList()
                                val verses: ArrayList<CorpusVerbWbwOccurance> =
                                    utils.getVerbOccuranceBreakVerses(split[1]) as ArrayList<CorpusVerbWbwOccurance>
                                for (vers in verses) {
                                    val sb = StringBuilder()
                                    val spannableVerses =
                                        getSpannableVerses(
                                            vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                                            vers.qurantext!!
                                        )
                                    sb.append(vers.surah).append(":").append(vers.ayah)
                                        .append(":").append(vers.wordno).append("-")
                                        .append(vers.en).append("-")
                                    //       sb.append(vers.getSurah()).append(":").append(vers.getAyah()).append(":").append(vers.getWordno()).append("-");
                                    //   vers.getWordno();
                                    val ref = SpannableString(sb.toString())
                                    ref.setSpan(
                                        maincolortag,
                                        0,
                                        sb.length,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                    )
                                    val which = shared.getString(
                                        "selecttranslation",
                                        "en_sahih"
                                    )
                                    var trans: SpannableString? = null
                                    when (which) {
                                        "en_sahih" -> trans =
                                            SpannableString.valueOf(vers.translation)

                                        "ur_jalalayn" -> trans =
                                            SpannableString.valueOf(vers.ur_jalalayn)

                                        "en_jalalayn" -> trans =
                                            SpannableString.valueOf(vers.en_jalalayn)

                                        "en_arberry" -> trans =
                                            SpannableString.valueOf(vers.en_arberry)
                                    }
                                    val charSequence =
                                        TextUtils.concat(ref, "\n ", spannableVerses, "\n ", trans)
                                    list.add(SpannableString.valueOf(charSequence))
                                    // trans?.let { list.add(it) }
                                }
                                runOnUiThread(Runnable {
                                    ex.shutdown()
                                    dialog!!.dismiss()
                                    expandNounVerses[expandNounTitles[groupPosition]] =
                                        list
                                    listAdapter.notifyDataSetChanged()
                                })
                            }
                        }
                    }
                }
                expandableListView!!.setOnChildClickListener(object : OnChildClickListener {
                    override fun onChildClick(
                        parent: ExpandableListView, v: View,
                        groupPosition: Int, childPosition: Int, id: Long
                    ): Boolean {
                        val child = listAdapter.getChild(
                            groupPosition,
                            childPosition
                        ) as CharSequence
                        val split =
                            child.toString().split("-".toRegex()).dropLastWhile { it.isEmpty() }
                                .toTypedArray()
                        val surahaya = split[0].split(":".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                        return if (surahaya.size > 1) {
                            try {
                                Integer.valueOf(surahaya[2])
                            } catch (e: NumberFormatException) {
                                return false
                            }
                            try {
                                Integer.valueOf(surahaya[1])
                            } catch (e: NumberFormatException) {
                                return false
                            }
                            try {
                                Integer.valueOf(surahaya[0])
                            } catch (e: NumberFormatException) {
                                return false
                            }
                            val wordno = surahaya[2]
                            val ayah = surahaya[1]
                            val surah = surahaya[0]
                            val dataBundle = Bundle()
                            dataBundle.putInt(SURAH_ID, surah.toInt())
                            dataBundle.putInt(AYAHNUMBER, ayah.toInt())
                            dataBundle.putInt(WORDNUMBER, wordno.toInt())
                            dataBundle.putString(SURAH_ARABIC_NAME, "SurahName")
                            LoadItemList(dataBundle, surah, ayah, wordno)
                            true
                        } else {
                            false
                        }
                    }

                    private fun LoadItemList(
                        dataBundle: Bundle,
                        surah: String,
                        ayah: String,
                        wordno: String
                    ) {
                        val item = WordAnalysisBottomSheet()
                        //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                        item.arguments = dataBundle
                        val data = arrayOf<String?>(surah, ayah, "", wordno)
                        WordAnalysisBottomSheet.newInstance(data)
                            .show(supportFragmentManager, WordAnalysisBottomSheet.TAG)
                        //   WordAnalysisBottomSheet.newInstance(data).show(WordOccuranceAsynKAct.this).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);
                    }
                })
            }
        }
    }

    private fun verbandnouns() {
        for (noun in nounCorpusArrayList!!) {
            val list: ArrayList<SpannableString> =
                ArrayList()
            list.add(SpannableString.valueOf(""))
            if (noun.form == "null") {
                val sb = StringBuilder()
                val nounexpand = QuranMorphologyDetails.expandTags(noun.tag!!)
                var times = ""
                times = if (noun.count == 1) {
                    "Once"
                } else {
                    val count = noun.count
                    val timess = count.toString()
                    "$timess-times"
                }
                sb.append(times).append(" ").append(noun.lemma_a).append(" ")
                    .append("occurs as the").append(" ").append(nounexpand)
                expandNounVerses[sb.toString()] = list
            } else {
                val sb = StringBuilder()
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
                sb.append(times).append(" ").append(noun.lemma_a).append(" ")
                    .append("occurs as the").append(" ").append(noun.form)
                    .append(" ")
                if (s != "null") {
                    sb.append(s).append(" ").append(" ")
                }
                sb.append(s2)
                expandNounVerses[sb.toString()] = list
            }
        }
        for (verbCorpusBreakup in verbCorpusArrayList!!) {
            val list = ArrayList<SpannableString>()
            list.add(SpannableString.valueOf(""))
            if (verbCorpusBreakup.form == "I") {
                val sb = StringBuilder()
                val mujarrad = java.lang.String.valueOf(
                    QuranMorphologyDetails.getThulathiName(verbCorpusBreakup.thulathibab)
                )
                sb.append(verbCorpusBreakup.count).append("-").append("times").append(" ")
                    .append(verbCorpusBreakup.lemma_a).append(" ").append("occurs as the")
                    .append(" ").append(mujarrad)
                expandVerbVerses[sb.toString()] = list
            } else {
                val sb = StringBuilder()
                val s = verbCorpusBreakup.tense?.let { QuranMorphologyDetails.expandTags(it) }
                val s1 = verbCorpusBreakup.voice?.let { QuranMorphologyDetails.expandTags(it) }
                val mazeed = java.lang.String.valueOf(
                    QuranMorphologyDetails.getFormName(verbCorpusBreakup.form)
                )
                sb.append(verbCorpusBreakup.count).append("-").append("times").append(" ")
                    .append(verbCorpusBreakup.lemma_a).append(" ").append("occurs as the")
                    .append(" ").append(mazeed)
                    .append(" ").append(s).append(" ").append(" ").append(s1)
                expandVerbVerses[sb.toString()] = list
            }
        }
    }
}