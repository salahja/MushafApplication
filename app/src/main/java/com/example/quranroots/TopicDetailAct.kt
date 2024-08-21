package com.example.quranroots


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.AYAHNUMBER
import com.example.Constant.AYAH_ID
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.mushafconsolidated.Activity.TafsirFullscreenActivity
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.Adapters.NewTopicFlowAyahWordAdapter
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.GrammerFragmentsBottomSheet
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.model.CorpusAyahWord
import com.example.mushafconsolidated.model.CorpusWbwWord
import com.example.mushafconsolidated.model.NewQuranCorpusWbw
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.utility.CorpusUtilityorig
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class TopicDetailAct : BaseActivity(), OnItemClickListenerOnLong {
    private lateinit var flowAyahWordAdapter: NewTopicFlowAyahWordAdapter

    // --Commented out by Inspection (24/10/22, 10:04 PM):int mudhafColoragainstBlack, // --Commented out by Inspection (24/10/22, 10:04 PM):mausofColoragainstBlack, sifatColoragainstBlack, // --Commented out by Inspection (24/10/22, 10:03 PM):brokenPlurarColoragainstBlack, shartagainstback;
    // --Commented out by Inspection (24/10/22, 10:04 PM):private NavigationView navigationView;
    // --Commented out by Inspection (24/10/22, 10:04 PM):private MaterialToolbar materialToolbar;
    // --Commented out by Inspection (24/10/22, 10:03 PM):private FlowAyahWordAdapterPassage flowAyahWordAdapterpassage;
    private lateinit var corpusayahWordArrayList: ArrayList<CorpusAyahWord>
    private var corpusSurahWord: List<QuranCorpusWbw>? = null
    private var newnewadapterlist = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
    private var allofQuran: ArrayList<QuranEntity>? = null
    private lateinit var mainViewModel: QuranVIewModel

    private lateinit var newcorpusayahWordArrayList: ArrayList<QuranCorpusWbw>
    private lateinit var arrayofquran: ArrayList<ArrayList<QuranEntity>>
    private lateinit var arrayofadapterlist: ArrayList<LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>>

    // --Commented out by Inspection (24/10/22, 10:04 PM):private int chapterno;
    // --Commented out by Inspection START (24/10/22, 10:03 PM):
    //    public int getVersescount() {
    //        return versescount;
    //    }
    // --Commented out by Inspection STOP (24/10/22, 10:03 PM)
    // --Commented out by Inspection START (24/10/22, 10:03 PM):
    //    public int getChapterno() {
    //        return chapterno;
    //    }
    // --Commented out by Inspection STOP (24/10/22, 10:03 PM)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dua_group, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        // --Commented out by Inspection (24/10/22, 10:04 PM):private boolean kana;
        val shared = PreferenceManager.getDefaultSharedPreferences(this@TopicDetailAct)
        when (shared.getString("themePref", "dark")) {
            "light" -> switchTheme("light")
            "dark" -> switchTheme("dark")
            "blue" -> switchTheme("blue")
            "green" -> switchTheme("light")
            "brown" -> switchTheme("brown")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_search_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //     Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        android.preference.PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        arrayofadapterlist = ArrayList()
        arrayofquran = ArrayList()
        val bundle: Intent = intent
        if (bundle.extras != null) {
            val bundles: Bundle = intent.extras!!
            val map = bundle.getSerializableExtra("map") as HashMap<String, String>?
            var surahname = ""
            val surahArrays = resources.getStringArray(R.array.surahdetails)

            if (map!!.size != 0) {
                bundles.getSerializable("map")
                //  LinkedHashMap map = (LinkedHashMap) bundles.get("map");
                val keys: Set<String> = map.keys
                corpusayahWordArrayList = ArrayList()
                arrayofquran = ArrayList()
                arrayofadapterlist = ArrayList()
                val scope: CoroutineScope
                val builder = AlertDialog.Builder(
                    this,
                    com.google.android.material.R.style.ThemeOverlay_Material3_Dialog
                )
                builder.setCancelable(false) // if you want user to wait for some process to finish,
                builder.setView(R.layout.layout_loading_dialog)
                val dialog = builder.create()
                runOnUiThread { dialog.show() }
                val listener: OnItemClickListenerOnLong = this
                scope = CoroutineScope(Dispatchers.Main)
                scope.launch {
                    for (key: String in keys) {
                        val splits = map[key]
                        assert(splits != null)
                        val split = splits!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                        for (s: String in split) {
                            getwbwy(s, key)
                        }
                    }
                    runOnUiThread {
                        dialog.dismiss()
                        val linearLayoutManager = LinearLayoutManager(applicationContext)

                        /*       val flowAyahWordAdapter =
                                   TopicFlowAyahWordAdapter(corpusayahWordArrayList, listener, surahname)*/


                        flowAyahWordAdapter =
                            NewTopicFlowAyahWordAdapter(
                                arrayofadapterlist,
                                listener,
                                arrayofquran,
                                surahArrays
                            )

                        val parentRecyclerView: RecyclerView = findViewById(R.id.recycler_view)
                        parentRecyclerView.layoutManager = linearLayoutManager
                        flowAyahWordAdapter.addContext(this@TopicDetailAct)
                        parentRecyclerView.setHasFixedSize(true)
                        parentRecyclerView.adapter = flowAyahWordAdapter
                        flowAyahWordAdapter.notifyDataSetChanged()
                    }

                }

            } else {
                val surah = bundle.extras!!.getInt(SURAH_ID)
                val ayah = bundle.extras!!.getInt(AYAH_ID)
                corpusayahWordArrayList = ArrayList()

                mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
                allofQuran =
                    mainViewModel.getsurahayahVerses(surah, ayah).value as ArrayList<QuranEntity>?
                corpusSurahWord = mainViewModel.getQuranCorpusWbwbysurahAyah(surah, ayah).value
                val corpus = CorpusUtilityorig(this)
                newnewadapterlist =
                    CorpusUtilityorig.composeWBWCollection(allofQuran, corpusSurahWord)
                allofQuran?.let { arrayofquran.add(it) }
                corpus.setKana(newnewadapterlist, surah, ayah, newnewadapterlist.size)

                corpus.setMudhafFromDB(newnewadapterlist, surah, ayah, newnewadapterlist.size)
                corpus.SetMousufSifaDB(newnewadapterlist, surah, ayah, newnewadapterlist.size)
                corpus.setKana(newnewadapterlist, surah, ayah, newnewadapterlist.size)
                corpus.setShart(newnewadapterlist, surah, ayah, newnewadapterlist.size)
                corpus.newnewHarfNasbDb(newnewadapterlist, surah, ayah, newnewadapterlist.size)

                arrayofadapterlist.add(newnewadapterlist)
                //    final Object o6 = wbwa.get(verseglobal).get(0);
                val sb = StringBuilder()

                val listener: OnItemClickListenerOnLong = this
                flowAyahWordAdapter =
                    NewTopicFlowAyahWordAdapter(
                        arrayofadapterlist,
                        listener,
                        arrayofquran,
                        surahArrays
                    )
                val linearLayoutManager = LinearLayoutManager(this)
                val parentRecyclerView: RecyclerView = findViewById(R.id.recycler_view)
                parentRecyclerView.layoutManager = linearLayoutManager
                flowAyahWordAdapter.addContext(this)
                parentRecyclerView.setHasFixedSize(true)
                parentRecyclerView.adapter = flowAyahWordAdapter
                flowAyahWordAdapter.notifyDataSetChanged()

            }
            //  getwbwy(aref);

        }
    }


    private fun getwbwy(str: String, header: String) {
        val ss = str.split(":".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val suraid = ss[0].trim { it <= ' ' }.toInt()
        val ayah = ss[1].trim { it <= ' ' }.toInt()
        //    preparewbwarray(header, suraid, ayah)
        newpreparewbwarray(header, suraid, ayah)
    }

    private fun newpreparewbwarray(header: String?, suraid: Int, ayah: Int) {
        val utils = Utils(this@TopicDetailAct)
        //  CorpusWbwWord word = new CorpusWbwWord();
        val ayahWord = CorpusAyahWord()
        val wordArrayList = ArrayList<CorpusWbwWord>()
        //   val wbw: List<CorpusExpandWbwPOJO> = utils.getCorpusWbwBySurahAyahtopic(suraid, ayah)


        mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
        allofQuran = mainViewModel.getsurahayahVerses(suraid, ayah).value as ArrayList<QuranEntity>?
        corpusSurahWord = mainViewModel.getQuranCorpusWbwbysurahAyah(suraid, ayah).value
        val corpus = CorpusUtilityorig(this)
        newnewadapterlist = CorpusUtilityorig.composeWBWCollection(allofQuran, corpusSurahWord)
        allofQuran?.let { arrayofquran.add(it) }
        corpus.setKana(newnewadapterlist, suraid, ayah, newnewadapterlist.size)

        corpus.setMudhafFromDB(newnewadapterlist, suraid, ayah, newnewadapterlist.size)
        corpus.SetMousufSifaDB(newnewadapterlist, suraid, ayah, newnewadapterlist.size)
        corpus.setKana(newnewadapterlist, suraid, ayah, newnewadapterlist.size)
        corpus.setShart(newnewadapterlist, suraid, ayah, newnewadapterlist.size)
        corpus.newnewHarfNasbDb(newnewadapterlist, suraid, ayah, newnewadapterlist.size)

        arrayofadapterlist.add(newnewadapterlist)
        //    final Object o6 = wbwa.get(verseglobal).get(0);
        val sb = StringBuilder()





        println("check")
    }


    private fun LoadItemList(dataBundle: Bundle, word: QuranEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        val item = GrammerFragmentsBottomSheet()
        val fragmentManager: FragmentManager = supportFragmentManager
        item.arguments = dataBundle
        val data = arrayOf(
            word.surah.toString(),
            word.ayah.toString(),
            word.translation,
            (1).toString()

        )
        @SuppressLint("CommitTransaction") val transactions = fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
        transactions.show(item)
        GrammerFragmentsBottomSheet.newInstance(data)
            .show(supportFragmentManager, WordAnalysisBottomSheet.TAG)
    }

    override fun onItemClick(view: View, position: Int) {
        qurangrammarmenu(view, position)
    }

    override fun onItemLongClick(position: Int, v: View) {
        TODO("Not yet implemented")
    }

    @SuppressLint("InflateParams")
    fun qurangrammarmenu(view: View, position: Int) {
        var view = view
        val tag = view.tag
        var bookmarkview: View? = null
        val overflow = view.findViewById<View>(R.id.ivActionOverflow)
        if ((tag == "bookmark")) {
            bookMarkSelected(position, bookmarkview)
            //  SurahAyahPicker();
        } else if ((tag == "overflow_img")) {
            val builder = AlertDialog.Builder(this@TopicDetailAct)
            val factory = LayoutInflater.from(this@TopicDetailAct)
            view = factory.inflate(R.layout.topic_popup_layout, null)
            val tafsirtag = view.findViewById<View>(R.id.tafsir)
            bookmarkview = view.findViewById(R.id.bookmark)
            builder.setView(view)
            val location = IntArray(2)
            overflow.getLocationOnScreen(location)
            val dialog = builder.create()
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val wmlp = dialog.window!!.attributes
            wmlp.gravity = Gravity.TOP or Gravity.START
            wmlp.x = location[0] //x position
            wmlp.y = location[1] //y position
            dialog.window!!.attributes.windowAnimations =
                R.style.WindowAnimationTransition //style id
            dialog.show()
            tafsirtag.setOnClickListener { view12: View? ->
                val readingintent: Intent = Intent(
                    this@TopicDetailAct,
                    TafsirFullscreenActivity::class.java
                )
                //  flowAyahWordAdapter.getItem(position);
                val chapter_no: Int =
                    corpusayahWordArrayList[position].word[0].surahId
                val verse: Int =
                    corpusayahWordArrayList[position].word[0].verseId
                val surahArrays: Array<String> =
                    resources.getStringArray(R.array.suraharabic)
                val surahname: String = surahArrays[chapter_no - 1]
                readingintent.putExtra(SURAH_ID, chapter_no)
                readingintent.putExtra(AYAH_ID, verse)
                readingintent.putExtra(SURAH_ARABIC_NAME, surahname)
                startActivity(readingintent)
                dialog.dismiss()
            }
            val finalBookmarkview = bookmarkview
            bookmarkview.setOnClickListener(View.OnClickListener { view1: View? ->
                bookMarkSelected(position, finalBookmarkview)
                dialog.dismiss()
            })
            println("check")
        } else if ((tag == "qurantext")) {
            val word: QuranEntity
            val ayah = flowAyahWordAdapter.ayahWord.corpus!!.ayah
            val surah = flowAyahWordAdapter.ayahWord.corpus!!.surah
            word = if (position != 0) {
                arrayofquran[position].get(0)
            } else {
                arrayofquran[position].get(0)
            }
            val dataBundle = Bundle()
            dataBundle.putInt(SURAH_ID, arrayofquran[position].get(0).surah)
            dataBundle.putInt(AYAHNUMBER, arrayofquran[position].get(0).ayah)
            LoadItemList(dataBundle, word)
        }
    }

    private fun bookMarkSelected(position: Int, bookmarkview: View?) {
        val chapter_no = corpusayahWordArrayList[position].word[0].surahId
        val verse = corpusayahWordArrayList[position].word[0].verseId
        val surahArrays: Array<String> = resources.getStringArray(R.array.suraharabic)
        val surahname = surahArrays[chapter_no - 1]
        val en = BookMarks()
        en.chapterno = chapter_no.toString()
        en.verseno = verse.toString()
        en.surahname = surahname
        //     Utils utils = new Utils(ReadingSurahPartActivity.this);
        //  val utils = Utils(this)
        val vm: QuranVIewModel by viewModels()
        vm.Insertbookmark(en)
        // utils.insertBookMark(en)
        val snackbar = Snackbar
            .make((bookmarkview)!!, "BookMark Created", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setTextColor(Color.CYAN)
        snackbar.setBackgroundTint(Color.BLACK)
        snackbar.show()
    }


}