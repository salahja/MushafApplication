package com.example.quranroots


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.view.View
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.Constant.ARABICWORD
import com.example.Constant.AYAHNUMBER
import com.example.Constant.AYAH_ID
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.SARFKABEER
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.Constant.WORDDETAILS
import com.example.Constant.WORDMEANING
import com.example.Constant.WORDNUMBER
import com.example.Constant.particlespanDark
import com.example.mushafconsolidated.Activity.LughatWordDetailsAct
import com.example.mushafconsolidated.Adapters.NounVerbOccuranceListAdapter
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.RootVerbDetails
import com.example.mushafconsolidated.Entities.RootWordDetails
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbWazan
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanelexicon
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.databinding.ActivityRootBreakupBinding
import com.example.mushafconsolidated.fragments.QuranMorphologyDetails
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.utility.CorpusUtilityorig
import com.example.utility.CorpusUtilityorig.Companion.getSpannableVerses
import com.example.utility.QuranGrammarApplication
import com.google.android.material.chip.Chip
import org.sj.conjugator.activity.BaseActivity
import org.sj.conjugator.activity.ConjugatorTabsActivity
import org.sj.conjugator.interfaces.OnItemClickListener
import org.sj.conjugator.utilities.ArabicLiterals
import java.util.concurrent.Executors
import java.util.regex.Pattern


class RootBreakupAct : BaseActivity(), OnItemClickListener, View.OnClickListener {
    private lateinit var allChapters: LiveData<List<ChaptersAnaEntity>>
    private lateinit var binding: ActivityRootBreakupBinding
    lateinit var shared: SharedPreferences
    private var firstcolortat = 0
    private var maincolortag = 0
    private var pronouncolortag = 0
    private var fourcolortag = 0
    private lateinit var root: String
    private lateinit var wordorverb: String
    private lateinit var verbCorpusArrayList: ArrayList<VerbCorpusBreakup>
    private var occurances: ArrayList<CorpusNounWbwOccurance>? = null
    private lateinit var nounCorpusArrayList: ArrayList<NounCorpusBreakup>
    private lateinit var expandableListView: ExpandableListView
    private var harf = false
    lateinit var expandNounTitles: MutableList<String>
    private lateinit var expandVerbTitles: List<String>
    var counter = 0
    lateinit var dialog: AlertDialog

    var expandNounVerses: LinkedHashMap<String, ArrayList<SpannableString>> =
        LinkedHashMap()
    private var expandVerbVerses: LinkedHashMap<String, ArrayList<SpannableString>> =
        LinkedHashMap()
    private lateinit var utils: Utils
    private lateinit var lanes: Chip
    private var rootdetails: ArrayList<RootWordDetails>? = null

    private var verbdetails: ArrayList<RootVerbDetails>? = null

    private var corpusSurahWord: List<QuranCorpusWbw>? = null
    private var allofQuran: List<QuranEntity>? = null
    private lateinit var mainViewModel: QuranVIewModel
    var isverb = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBreakupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        val preferences = prefs.getString("theme", "dark")
        expandableListView = findViewById(R.id.expandableListView)
        lanes = findViewById(R.id.lanelexicon)
        val rootoccurance: TextView = binding.rootoccurance

        root = bundle.getStringExtra(QURAN_VERB_ROOT)!!
        isverb = bundle.getBooleanExtra("isverb", false)
        rootoccurance.text = root

        lanes.setOnClickListener(this)
        lanes.setOnClickListener {
            val bundle = Bundle()
            //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);
            val intent = Intent(this@RootBreakupAct, LughatWordDetailsAct::class.java)
            //   getTypedValues();
            bundle.putString(QURAN_VERB_ROOT, root)
            bundle.putBoolean("dictionary", true)
            intent.putExtras(bundle)
            //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
            startActivity(intent)
        }
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
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
        val lanelexicon: TextView = binding.lanelexicon
        val recyclerView: RecyclerView = binding.rootbreakup
        if (bundle.extras != null) {
            val bundles = intent.extras

            root = bundles!!.getString(QURAN_VERB_ROOT)!!
            wordorverb = bundles.getString(WORDDETAILS)!!
        }
        mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
        utils = Utils(this)
        val indexOf = root.indexOf("ء")
        if (indexOf != -1) {
            root = root.replace("ء", "ا")
        }
        //   rootdetails = utils.getRootDetails(root) as ArrayList<RootWordDetails>?
        val corpus = CorpusUtilityorig(this)

        corpusSurahWord = mainViewModel.getQuranCorpusWbwbyroot(root).value


        val sb = StringBuilder()
        sb.append(root).append(" ").append("Ocurrance").append(" ").append(corpusSurahWord!!.size)
        rootoccurance.text = sb.toString()

        allChapters = mainViewModel.getAllChapters()
        if (root == "ACC" || root == "LOC" || root == "T") {
            occurances =
                utils.getnounoccuranceHarfNasbZarf(root) as ArrayList<CorpusNounWbwOccurance>?
            harf = true
            ExecuteNounOcurrance()
        } else {
            harf = false
            ExecuteNounOcurrance()
        }
        lanelexicon.text = "Lanes"
        var myRootBreakRecyclerViewAdapter: MyRootBreakRecyclerViewAdapter? = null
        var verbDetailsRecAdapter: VerbDetailsRecAdapter? = null
        if (wordorverb == "word") {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            myRootBreakRecyclerViewAdapter =
                MyRootBreakRecyclerViewAdapter(corpusSurahWord, allChapters)
            recyclerView.adapter = myRootBreakRecyclerViewAdapter
            myRootBreakRecyclerViewAdapter.SetOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(v: View?, position: Int) {
                    val wordDetails = corpusSurahWord!![position]

                    val datas = HashMap<String, String>()
                    val newbundle = Bundle()
                    newbundle.putInt(SURAH_ID, wordDetails.corpus.surah)
                    newbundle.putInt(AYAH_ID, wordDetails.corpus.ayah)

                    newbundle.putString(
                        SURAH_ARABIC_NAME,
                        allChapters.value!!.get(wordDetails.corpus.surah).namearabic
                    )



                    newbundle.putString(
                        ARABICWORD, wordDetails.corpus.araone + wordDetails.corpus.aratwo +
                                wordDetails.corpus.arathree + wordDetails.corpus.arafour + wordDetails.corpus.arafive
                    )
                    newbundle.putString(WORDMEANING, wordDetails.wbw.en)
                    newbundle.putSerializable("map", datas)
                    val intents = Intent(this@RootBreakupAct, TopicDetailAct::class.java)
                    intents.putExtras(newbundle)
                    startActivity(intents)

                    //    dataBundle.putSerializable("map", datas)

                    /*   val fragmentManagers = supportFragmentManager
                       val transactions = fragmentManagers.beginTransaction()
                       transactions.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                       val fragvsi = TopicDetailsFrag.newInstance(newbundle)
                       fragvsi.arguments = newbundle
                       transactions.add(R.id.frame_container, fragvsi)
                       transactions.addToBackStack(null)
                       transactions.commit()*/


                    /*       val item = TopicDetailsFrag()
                           val fragmentManager = supportFragmentManager
                           item.arguments = newbundle

                           fragmentManager.beginTransaction()
                               .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out).show(item)
       */


                }
            })
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 1)
            //  recyclerView.adapter = myRootBreakRecyclerViewAdapter
            verbdetails = utils.getRootVerbDetails(root) as ArrayList<RootVerbDetails>?
            verbDetailsRecAdapter = VerbDetailsRecAdapter(verbdetails!!)
            recyclerView.adapter = verbDetailsRecAdapter
            verbDetailsRecAdapter.SetOnItemClickListener(object : OnItemClickListener {

                override fun onItemClick(v: View?, position: Int) {
                    val dataBundle = Bundle()
                    val newbundle = Bundle()
                    val wordDetails = verbdetails!![position]
                    val datas = HashMap<String, String>()
                    if (v != null) {
                        if (v.tag == "conjugate") {
                            newbundle.putInt(SURAH_ID, wordDetails.surah)
                            newbundle.putInt(AYAH_ID, wordDetails.ayah)
                            newbundle.putString(SURAH_ARABIC_NAME, wordDetails.namearabic)
                            newbundle.putString(ARABICWORD, wordDetails.arabic)
                            newbundle.putString(WORDMEANING, wordDetails.en)
                            newbundle.putString(
                                VERBMOOD,
                                VerbWazan.getVerbMood(wordDetails.mood_kananumbers)
                            )
                            if (wordDetails.thulathibab!!.isEmpty()) {
                                newbundle.putString(
                                    QURAN_VERB_WAZAN,
                                    VerbWazan.getMazeedWazan(wordDetails.form)
                                )
                                newbundle.putString(VERBTYPE, "mazeed")
                            } else {
                                newbundle.putString(QURAN_VERB_WAZAN, wordDetails.thulathibab)
                                newbundle.putString(VERBTYPE, "mujarrad")
                            }
                            newbundle.putString(QURAN_VERB_ROOT, wordDetails.rootarabic)
                            //       dataBundle.putString(VERBTYPE, verbtype);
                            newbundle.putBoolean(SARFKABEER, true)
                            val intent =
                                Intent(this@RootBreakupAct, ConjugatorTabsActivity::class.java)
                            intent.putExtras(newbundle)
                            startActivity(intent)
                        } else {
                            newbundle.putInt(SURAH_ID, wordDetails.surah)
                            newbundle.putInt(AYAH_ID, wordDetails.ayah)
                            newbundle.putString(SURAH_ARABIC_NAME, wordDetails.namearabic)
                            newbundle.putString(ARABICWORD, wordDetails.arabic)
                            newbundle.putString(WORDMEANING, wordDetails.en)
                            newbundle.putSerializable("map", datas)
                            val intents = Intent(this@RootBreakupAct, TopicDetailAct::class.java)
                            intents.putExtras(newbundle)
                            startActivity(intents)


                            /*
                                                        val fragmentManagers = supportFragmentManager
                                                        val transactions = fragmentManagers.beginTransaction()
                                                        transactions.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                                                        val fragvsi = TopicDetailsFrag.newInstance(newbundle)
                                                        fragvsi.arguments = newbundle
                                                        transactions.add(R.id.frame_container, fragvsi)
                                                        transactions.addToBackStack(null)
                                                        transactions.commit()*/


                            /*    val topfrag: TopicDetailsFrag = TopicDetailsFrag.newInstance(newbundle)
                                topfrag.show(
                                    supportFragmentManager,
                                    TopicDetailsFrag.TAG
                                )
    */


                        }
                    }
                }


            })
        }
        //    rootDictionary.get(0).getHansweir();
    }


    private fun ExecuteNounOcurrance() {
        shared = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        val ex = Executors.newSingleThreadExecutor()
        ex.execute {
            runOnUiThread { dialog.show() }
            //  occurances = utils.getNounOccuranceBreakVerses(root);
            val vroot = root.indexOf("ء")
            var nounroot = ""
            val verb = root.indexOf("ا")
            var verbroot = ""
            nounroot = if (vroot != -1) {
                root.replace("ء", "ا")
            } else {
                root
            }
            verbroot = if (verb != -1) {
                root.replace("ا", "ء")
            } else {
                root
            }
            nounCorpusArrayList = utils.getNounBreakup(nounroot) as ArrayList<NounCorpusBreakup>
            verbCorpusArrayList = utils.getVerbBreakUp(verbroot) as ArrayList<VerbCorpusBreakup>
            var Lemma = ""
            val incexofgroup = 0
            val alist: ArrayList<SpannableString> = ArrayList()
            if (harf) {
                for (vers in occurances!!) {
                    //    alist.add("");
                    val sb = java.lang.StringBuilder()
                    val spanDark = SpannableString(vers.qurantext)
                    val spannableVerses = getSpannableVerses(
                        vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                        vers.qurantext!!
                    )
                    sb.append(vers.surah).append(":").append(vers.ayah).append(":")
                        .append(vers.wordno)
                        .append("-").append(vers.en).append("-")
                    val ref = SpannableString(sb.toString())
                    ref.setSpan(particlespanDark, 0, sb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    val which = shared.getString("selecttranslation", "en_sahih")
                    var trans: SpannableString? = null
                    when (which) {
                        "en_sahih" -> {
                            trans = SpannableString.valueOf(vers.translation)
                        }

                        "ur_jalalayn" -> {
                            trans = SpannableString.valueOf(vers.ur_jalalayn)
                        }

                        "en_jalalayn" -> {
                            trans = SpannableString.valueOf(vers.en_jalalayn)
                        }
                    }
                    val charSequence = TextUtils.concat(ref, "\n ", spannableVerses)
                    // alist.add(charSequence as SpannableString)
                    alist.add(SpannableString.valueOf(charSequence))

                    if (trans != null) {
                        alist.add(trans)
                    }
                    expandNounVerses[sb.toString()] = alist
                }
            }


            for (noun in nounCorpusArrayList) {
                val list: ArrayList<SpannableString> = ArrayList()
                list.add(SpannableString.valueOf(""))
                Lemma = noun.lemma_a!!
                if (noun.form.equals("null")) {
                    val sb = java.lang.StringBuilder()
                    val nounexpand = noun.tag?.let { QuranMorphologyDetails.expandTags(it) }
                    var times = ""
                    times = if (noun.count === 1) {
                        "Once"
                    } else {
                        val count: Int = noun.count
                        val timess = count.toString()
                        "$timess-times"
                    }
                    sb.append(times).append(" ").append(noun.lemma_a).append(" ")
                        .append("occurs as the").append(" ").append(nounexpand)
                    expandNounVerses[sb.toString()] = list
                } else {
                    val sb = java.lang.StringBuilder()
                    val s = QuranMorphologyDetails.expandTags(noun.propone + noun.proptwo)
                    //  String s1 = QuranMorphologyDetails.expandTags(noun.getProptwo());
                    val s2 = QuranMorphologyDetails.expandTags(noun.tag!!)
                    var times = ""
                    times = if (noun.count === 1) {
                        "Once"
                    } else {
                        val count: Int = noun.count
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

            for (verbCorpusBreakup in verbCorpusArrayList) {

                val list: ArrayList<SpannableString> = ArrayList()

                list.add(SpannableString.valueOf("yes"))
                Lemma = verbCorpusBreakup.lemma_a!!
                if (verbCorpusBreakup.form == "I") {
                    val sb = java.lang.StringBuilder()
                    val mujarrad = java.lang.String.valueOf(
                        QuranMorphologyDetails.getThulathiName(verbCorpusBreakup.thulathibab)
                    )
                    sb.append(verbCorpusBreakup.count).append("-").append("times").append(" ")
                        .append(verbCorpusBreakup.lemma_a).append(" ").append("occurs as the")
                        .append(" ").append(mujarrad)
                    expandVerbVerses[sb.toString()] = list
                } else {
                    val sb = java.lang.StringBuilder()
                    val s = QuranMorphologyDetails.expandTags(verbCorpusBreakup.tense!!)
                    val s1 = QuranMorphologyDetails.expandTags(verbCorpusBreakup.voice!!)
                    //  String s1 = QuranMorphologyDetails.expandTags(noun.getProptwo());
                    //   String s2 = QuranMorphologyDetails.expandTags(noun.get);
                    val mazeed =
                        java.lang.String.valueOf(
                            QuranMorphologyDetails.getFormName(
                                verbCorpusBreakup.form
                            )
                        )
                    sb.append(verbCorpusBreakup.count).append("-").append("times").append(" ")
                        .append(verbCorpusBreakup.lemma_a).append(" ").append("occurs as the")
                        .append(" ").append(mazeed)
                        .append(" ").append(s).append(" ").append(" ").append(s1)
                    expandVerbVerses[sb.toString()] = list
                }
            }
            expandNounTitles = java.util.ArrayList(expandNounVerses.keys)
            expandVerbTitles = java.util.ArrayList(expandVerbVerses.keys)
            expandNounVerses.putAll(expandVerbVerses)
            expandNounTitles.addAll(expandVerbTitles)
            //post
            runOnUiThread {
                dialog.dismiss()
                // Intent intent = new Intent();
                // intent.putExtra("result", 1);
                //  setResult(RESULT_OK, intent);
                val listAdapter = NounVerbOccuranceListAdapter(
                    this@RootBreakupAct,
                    expandNounTitles,
                    expandNounVerses,
                    expandVerbVerses,
                    expandVerbTitles
                )
                expandableListView.setAdapter(listAdapter)
                expandableListView.setOnGroupExpandListener { groupPosition ->
                    val split = expandNounTitles[groupPosition].split("\\s".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    if (!harf) {
                        if (expandNounTitles[groupPosition].contains("Hans")) {
                            val ex = Executors.newSingleThreadExecutor()
                            ex.execute(object : Runnable {
                                override fun run() {
                                    runOnUiThread { dialog.show() }
                                    var verbroot = ""
                                    val indexOfHamza =
                                        root.indexOf(ArabicLiterals.Hamza)
                                    val indexofAlifMaksura =
                                        root.indexOf(ArabicLiterals.Ya)
                                    verbroot = if (indexOfHamza != -1) {
                                        root.replace(
                                            ArabicLiterals.Hamza.toRegex(),
                                            ArabicLiterals.LALIF
                                        )
                                    } else {
                                        root
                                    }
                                    if (indexofAlifMaksura != -1) {
                                        verbroot = verbroot.replace(
                                            ArabicLiterals.Ya.toRegex(),
                                            ArabicLiterals.AlifMaksuraString
                                        )
                                    }
                                    var list: ArrayList<SpannableString> =
                                        ArrayList()

                                    //   ArrayList<CorpusNounWbwOccurance> verses = utils.getNounOccuranceBreakVerses(split[1]);
                                    val lanesDifinition: java.util.ArrayList<hanslexicon?>? =
                                        utils.getHansDifinition(verbroot) as java.util.ArrayList<hanslexicon?>?
                                    //    ArrayList<SpannableString> lanesdifinition;
                                    //   StringBuilder lanessb = new StringBuilder();
                                    for (hans in lanesDifinition!!) {
                                        //  <p style="margin-left:200px; margin-right:50px;">
                                        //    list.add("<p style=\"margin-left:200px; margin-right:50px;\">");
                                        //  list.add("<p style=\"margin-left:200px; margin-right:50px;\">");
                                        list.add(SpannableString.valueOf(hans!!.definition))
                                        //
                                    }
                                    list = highLightParadigm(list) as ArrayList<SpannableString>
                                    val finalList: ArrayList<SpannableString> =
                                        ArrayList()
                                    //   val finalList: List<*> = list
                                    runOnUiThread {
                                        ex.shutdown()
                                        dialog.dismiss()
                                        expandNounVerses[expandNounTitles[groupPosition]] =
                                            finalList
                                        listAdapter.notifyDataSetChanged()
                                    }
                                }

                                private fun highLightParadigm(list: ArrayList<*>): MutableList<*> {
                                    val lists: ArrayList<SpannableString> =
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
                                                m.group(0).length + indexof,
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
                            val ex = Executors.newSingleThreadExecutor()
                            ex.execute(object : Runnable {
                                override fun run() {
                                    runOnUiThread { dialog.show() }
                                    var list: ArrayList<SpannableString> =
                                        ArrayList()
                                    val lanesDifinition: java.util.ArrayList<lanelexicon?>? =
                                        utils.getLanesDifinition(root) as java.util.ArrayList<lanelexicon?>?
                                    for (lanes in lanesDifinition!!) {
                                        //  <p style="margin-left:200px; margin-right:50px;">
                                        //    list.add("<p style=\"margin-left:200px; margin-right:50px;\">");
                                        //  list.add("<p style=\"margin-left:200px; margin-right:50px;\">");
                                        list.add(SpannableString.valueOf(lanes!!.definition))
                                        //
                                    }
                                    list = highLightParadigm(list) as ArrayList<SpannableString>
                                    val finalList: List<*> = list
                                    runOnUiThread {
                                        ex.shutdown()
                                        dialog.dismiss()
                                        expandNounVerses[expandNounTitles[groupPosition]] =
                                            finalList as java.util.ArrayList<SpannableString>
                                        listAdapter.notifyDataSetChanged()
                                    }
                                }

                                private fun highLightParadigm(list: List<*>): MutableList<*> {
                                    val lists: ArrayList<SpannableString> =
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
                                            sb!!.setSpan(
                                                particlespanDark,
                                                indexof,
                                                m.group(0).length + indexof,
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
                        } else if (expandNounTitles[groupPosition].contains("Noun") || expandNounTitles[groupPosition].contains(
                                "Adverb"
                            ) || expandNounTitles[groupPosition].contains("Adjective")
                        ) {
                            val ex = Executors.newSingleThreadExecutor()
                            val builder =
                                AlertDialog.Builder(this@RootBreakupAct)
                            builder.setCancelable(false) // if you want user to wait for some process to finish,
                            builder.setView(R.layout.layout_loading_dialog)
                            val dialog = builder.create()
                            ex.execute {
                                runOnUiThread { dialog.show() }
                                val list: ArrayList<SpannableString> = ArrayList()
                                val verses: java.util.ArrayList<CorpusNounWbwOccurance?>? =
                                    utils.getNounOccuranceBreakVerses(
                                        split[1]
                                    ) as java.util.ArrayList<CorpusNounWbwOccurance?>?
                                for (vers in verses!!) {
                                    val sb = java.lang.StringBuilder()
                                    val spanDark = SpannableString(
                                        vers!!.qurantext
                                    )
                                    val spannableVerses =
                                        getSpannableVerses(
                                            vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                                            vers.qurantext!!
                                        )
                                    sb.append(vers.surah).append(":").append(vers.ayah)
                                        .append(":").append(
                                            vers.wordno
                                        ).append("-").append(vers.en).append("-")
                                    val ref = SpannableString(sb.toString())
                                    ref.setSpan(
                                        particlespanDark,
                                        0,
                                        sb.length,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                    )
                                    val which =
                                        shared.getString("selecttranslation", "en_sahih")
                                    var trans: SpannableString? = null
                                    if (which == "en_sahih") {
                                        trans = SpannableString.valueOf(vers.translation)
                                    } else if (which == "ur_jalalayn") {
                                        trans = SpannableString.valueOf(vers.ur_jalalayn)
                                    } else if (which == "en_jalalayn") {
                                        trans = SpannableString.valueOf(vers.en_jalalayn)
                                    } else if (which == "en_arberry") {
                                        trans = SpannableString.valueOf(vers.en_arberry)
                                    }
                                    val charSequence =
                                        TextUtils.concat(ref, "\n ", spannableVerses)
                                    //   list.add(charSequence as SpannableString)
                                    list.add(SpannableString.valueOf(charSequence))
                                    list.add(SpannableString.valueOf(trans))
                                }
                                runOnUiThread {
                                    ex.shutdown()
                                    dialog.dismiss()
                                    expandNounVerses[expandNounTitles[groupPosition]] =
                                        list
                                    listAdapter.notifyDataSetChanged()
                                }
                            }
                        } else {
                            val ex = Executors.newSingleThreadExecutor()
                            ex.execute {
                                runOnUiThread { dialog.show() }
                                val list: ArrayList<SpannableString> = ArrayList()
                                val verses: java.util.ArrayList<CorpusVerbWbwOccurance?>? =
                                    utils.getVerbOccuranceBreakVerses(split[1]) as java.util.ArrayList<CorpusVerbWbwOccurance?>?
                                for (vers in verses!!) {
                                    val sb = java.lang.StringBuilder()
                                    val spanDark = SpannableString(
                                        vers!!.qurantext
                                    )
                                    val spannableVerses =
                                        getSpannableVerses(
                                            vers.araone + vers.aratwo + vers.arathree + vers.arafour + vers.arafive,
                                            vers.qurantext!!
                                        )
                                    //  SpannableString spannableString = CorpusUtilityorig.SetWordSpanNew(vers.getTagone(), vers.getTagtwo(), vers.getTagthree(), vers.getTagfour(), vers.getTagfive(),
                                    //     vers.getAraone(), vers.getAratwo(), vers.getArathree(), vers.getArafour(), vers.getArafive());
                                    sb.append(vers.surah).append(":").append(vers.ayah)
                                        .append(":").append(
                                            vers.wordno
                                        ).append("-").append(vers.en).append("-")
                                    //       sb.append(vers.getSurah()).append(":").append(vers.getAyah()).append(":").append(vers.getWordno()).append("-");
                                    vers.wordno
                                    val ref = SpannableString(sb.toString())
                                    ref.setSpan(
                                        maincolortag,
                                        0,
                                        sb.length,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                    )
                                    val which =
                                        shared.getString("selecttranslation", "en_sahih")
                                    var trans: SpannableString? = null
                                    if (which == "en_sahih") {
                                        trans = SpannableString.valueOf(vers.translation)
                                    } else if (which == "ur_jalalayn") {
                                        trans = SpannableString.valueOf(vers.ur_jalalayn)
                                    } else if (which == "en_jalalayn") {
                                        trans = SpannableString.valueOf(vers.en_jalalayn)
                                    } else if (which == "en_arberry") {
                                        trans = SpannableString.valueOf(vers.en_arberry)
                                    }
                                    val charSequence =
                                        TextUtils.concat(ref, "\n ", spannableVerses)
                                    list.add(SpannableString.valueOf(charSequence))
                                    list.add(SpannableString.valueOf(trans))
                                }
                                runOnUiThread {
                                    ex.shutdown()
                                    dialog.dismiss()
                                    expandNounVerses[expandNounTitles[groupPosition]] =
                                        list
                                    listAdapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
                expandableListView.setOnChildClickListener(object : OnChildClickListener {
                    override fun onChildClick(
                        parent: ExpandableListView, v: View,
                        groupPosition: Int, childPosition: Int, id: Long,
                    ): Boolean {
                        //   final String selected = String.valueOf((SpannableString) listAdapter.getChild(
                        //      groupPosition, childPosition));
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
                        wordno: String,
                    ) {
                        val item = WordAnalysisBottomSheet()
                        //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                        val fragmentManager =
                            supportFragmentManager
                        item.arguments = dataBundle
                        val data = arrayOf<String?>(surah, ayah, "", wordno)
                        //    FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                        //     transactions.show(item);
                        WordAnalysisBottomSheet.newInstance(data)
                            .show(supportFragmentManager, WordAnalysisBottomSheet.TAG)
                        //   WordAnalysisBottomSheet.newInstance(data).show(WordOccuranceAsynKAct.this).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);
                    }
                })
            }
        }
        //  ExpandableRecAdapter expandableRecAdapter=new ExpandableRecAdapter(WordOccuranceAsynKAct.this,expandNounVerses,expandNounTitles);
        //  recview.setAdapter(expandableRecAdapter);
    }

    override fun onItemClick(v: View?, position: Int) {
        Toast.makeText(this, "itemclicked", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        Toast.makeText(this, "itemclicked", Toast.LENGTH_SHORT).show()
    }
}