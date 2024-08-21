package com.example.mushafconsolidated.Activity

import ArabicrootListFragment
import FileUtility
import VerbrootListFragment
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.format.DateFormat
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.Constant.SURAHFRAGTAG
import com.example.mushafconsolidated.BottomOptionDialog
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.SurahSummary
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.ajroomiya.NewAjroomiyaDetailHostActivity
import com.example.mushafconsolidated.databinding.NewFragmentReadingBinding
import com.example.mushafconsolidated.fragments.BookMarkCreateFrag
import com.example.mushafconsolidated.fragments.BookmarkFragment
import com.example.mushafconsolidated.fragments.NewSurahDisplayFrag
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.mushafconsolidated.fragments.newFlowAyahWordAdapter
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.model.CorpusAyahWord
import com.example.mushafconsolidated.model.CorpusWbwWord
import com.example.mushafconsolidated.model.NewQuranCorpusWbw
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.mushafconsolidated.model.VerseModel
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.mushafconsolidated.settingsimport.Constants
import com.example.mushafconsolidatedimport.ParticleColorScheme
import com.example.sentenceanalysis.SentenceGrammarAnalysis
import com.example.utility.CorpusUtilityorig
import com.example.utility.CorpusUtilityorig.Companion.HightLightKeyWord
import com.example.utility.QuranGrammarApplication
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.refactoring.FlowAyahWordAdapter
import com.refactoring.NewCorpusUtilityorig
import com.refactoring.WbwSurah
import database.NamesGridImageAct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mufradat.MufradatPagerActivity
import org.sj.conjugator.activity.BaseActivity
import org.sj.conjugator.activity.ConjugatorAct
import sj.hisnul.compose.HisnulComposeAct
import sj.hisnul.fragments.NamesDetail
import wheel.OnWheelChangedListener
import wheel.WheelView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.reflect.Type
import java.util.Date


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class QuranGrammarAct : BaseActivity(), OnItemClickListenerOnLong {
    private var bundle: Intent? = null
    private var bundles: Bundle? = null
    private lateinit var mainViewModel: QuranVIewModel
    private var corpusSurahWord: List<QuranCorpusWbw>? = null
    private var corpusayahWordArrayList: ArrayList<CorpusAyahWord>? = null
    private val passage = LinkedHashMap<Int, ArrayList<CorpusWbwWord>>()

    // private var newnewadapterlist = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
    var newnewadapterlist: LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>> = LinkedHashMap()
    private lateinit var newflowAyahWordAdapter: newFlowAyahWordAdapter
    lateinit var binding: NewFragmentReadingBinding
    private lateinit var surahWheelDisplayData: Array<String>
    private lateinit var ayahWheelDisplayData: Array<String>
    private lateinit var btnBottomSheet: FloatingActionButton
    lateinit var surahArabicName: String
    private var jumptostatus = false
    var surah_id = 0
    var verseNumber = 0
    var suraNumber = 0
    private var rukucount = 0
    var surahname: String? = null

    private var shartagainstback = 0
    private var surahorpart = 0

    // TextView tvsurah, tvayah, tvrukus;
    private var currentSelectSurah = 0

    // --Commented out by Inspection (13/08/23, 4:31 pm):RelativeLayout layoutBottomSheet;
    var versescount = 0
    private var chapterorpart = false

    // --Commented out by Inspection (14/08/21, 7:26 PM):ChipNavigationBar chipNavigationBar;
    private var verseNo = 0
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView

    // ChipNavigationBar chipNavigationBar;
    private lateinit var materialToolbar: Toolbar

    //goes with extracttwothree
    //   private lateinit var refflowAyahWordAdapter: refFlowAyahWordAdapter
    // private lateinit var flowAyahWordAdapterpassage: FlowAyahWordAdapterPassage
    // private UpdateMafoolFlowAyahWordAdapter flowAyahWordAdapter;
    private var mausoof = false
    private var mudhaf = false
    private var harfnasb = false
    private var shart = false
    private lateinit var soraList: ArrayList<ChaptersAnaEntity>
    private var kana = false
    private var allofQuran: List<QuranEntity>? = null
    private lateinit var shared: SharedPreferences

    //  private OnClickListener onClickListener;
    //  private val newadapterlist = LinkedHashMap<Int, ArrayList<NewCorpusAyahWord>>()
    private var mafoolbihiwords: List<MafoolBihi?> = emptyList()
    private var Jumlahaliya: List<HalEnt?>? = emptyList()
    private var Tammezent: List<TameezEnt?>? = emptyList()
    private var Mutlaqent: List<MafoolMutlaqEnt?>? = emptyList()
    private var Liajlihient: List<LiajlihiEnt?>? = emptyList()
    private var BadalErabNotesEnt: List<BadalErabNotesEnt?>? = emptyList()
    private var isMakkiMadani = 0
    var chapterno = 0
    private lateinit var parentRecyclerView: RecyclerView
    private var mushafview = false

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val myFragment: NewSurahDisplayFrag? =
            supportFragmentManager.findFragmentByTag(SURAHFRAGTAG) as NewSurahDisplayFrag?


        val mngr = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val taskList = mngr.getRunningTasks(10)
        val baseActivity = taskList.get(0).baseActivity
        val contains = taskList.get(0).baseActivity!!.className.contains("QuranGrammar")
        if (myFragment != null && myFragment.isVisible() && contains) {
            finish()
        }


        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dua_group, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView?
        searchView!!.queryHint = "Type something…"
        val sear = ContextCompat.getDrawable(this, R.drawable.custom_search_box)
        searchView.clipToOutline = true
        searchView.setBackgroundDrawable(sear)
        searchView.gravity = View.TEXT_ALIGNMENT_CENTER
        searchView.maxWidth = Int.MAX_VALUE
        return true
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.jumpto -> {
                SurahAyahPicker()
            }

            R.id.settings -> {
                val settingint = Intent(this, ActivitySettings::class.java)
                startActivity(settingint)
                navigationView.setCheckedItem(R.id.Names)
            }

            R.id.mushafview -> {
                val settingint = Intent(this, WordbywordMushafAct::class.java)
                startActivity(settingint)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        this.shared = PreferenceManager.getDefaultSharedPreferences(this@QuranGrammarAct)
        val preferences = shared.getString("themepref", "dark")
        super.onCreate(savedInstanceState)
        binding = NewFragmentReadingBinding.inflate(layoutInflater)

        mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
  val      versmodel = ViewModelProvider(this)[QuranVIewModel::class.java]
        val view = binding.root
        setContentView(view)
        // Get a reference to the ViewModel scoped to this Fragment
        // Get a reference to the ViewModel scoped to its Activity
        //    setContentView(R.layout.new_fragment_reading)
        materialToolbar = binding.toolbarmain




        setSupportActionBar(materialToolbar)
        val prefs =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)

        if (isFirstTime) {
            val intents = Intent(this@QuranGrammarAct, ActivitySettings::class.java)
            startActivity(intents)
        }
        android.preference.PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        mausoof = shared.getBoolean("mausoof", true)
        mudhaf = shared.getBoolean("mudhaf", true)
        harfnasb = shared.getBoolean("harfnasb", true)
        shart = shared.getBoolean("shart", true)
        kana = shared.getBoolean("kana", true)
        getpreferences()
        bundle = intent
        bundles = intent.extras
        if (bundles != null) {
            bundles = intent.extras

            val chapter = bundles!!.getInt(Constant.SURAH_ID, 1)
            mushafview = bundles!!.getBoolean("passages", false)
            val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
            val list = mainViewModel.loadListschapter().value

            initView()
            initnavigation()
            chapterno = chapter

            surahArabicName = list!![chapter - 1].abjadname
            //   setChapterno( bundle.etIntExtra(SURAH_ID,2));
            //  verseNo = bundle!!.getIntExtra(Constant.AYAH_ID, 1)
            versescount = list[chapter - 1].versescount
            isMakkiMadani = list[chapter - 1].ismakki
            rukucount = list[chapter - 1].rukucount
            bundles = null
            bundle = null
            SetTranslation()

        } else {
            initView()
            initnavigation()
            val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
            val list = mainViewModel.loadListschapter().value
            //    final boolean chapterorpartb = bundle.getBooleanExtra(CHAPTERORPART, true);
            initView()
            initnavigation()
            versescount = list!![chapterno - 1].versescount
            isMakkiMadani = list[chapterno - 1].ismakki
            rukucount = list[chapterno - 1].rukucount
            surahArabicName = surahname.toString()


            supportFragmentManager.commit {
                replace<NewSurahDisplayFrag>(R.id.frame_container_qurangrammar, SURAHFRAGTAG)
                setReorderingAllowed(true)
                addToBackStack(null)
                setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                supportFragmentManager.saveBackStack("replacement")
            }
        }
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

    private fun initnavigation() {
        btnBottomSheet = binding.fab
        btnBottomSheet = binding.fab
        drawerLayout = binding.drawer
        navigationView = binding.navigationView
        bottomNavigationView = binding.bottomNavView
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            materialToolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        btnBottomSheet.setOnClickListener { v: View? -> toggleBottomSheets() }
        //  bottomNavigationView.setOnItemSelectedListener { new  }OnItemReselectedListener
        bottomNavigationView.setOnItemReselectedListener { item: MenuItem ->
            if (item.itemId == R.id.surahnav) {
                materialToolbar.title = "Surah"
                val fragmentManager = supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                val newCustomFragment = NewSurahDisplayFrag.newInstance()
                transaction.replace(R.id.frame_container_qurangrammar, newCustomFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                navigationView.setCheckedItem(R.id.surahnav)
            }
            if (item.itemId == R.id.mushafview) {
                val settingints = Intent(this@QuranGrammarAct, ShowMushafActivity::class.java)

                startActivity(settingints)
            }
            if (item.itemId == R.id.conjugationnav) {
                materialToolbar.title = "Conjugator"
                val conjugatorintent = Intent(this@QuranGrammarAct, ConjugatorAct::class.java)
                startActivity(conjugatorintent)
            }
            if (item.itemId == R.id.dua) {
                materialToolbar.title = "Hisnul Muslim-Dua;s"
                //  val searchintent = Intent(this@QuranGrammarAct, ComposeAct::class.java)
                val searchintent = Intent(this@QuranGrammarAct, HisnulComposeAct::class.java)
                startActivity(searchintent)
            }
            if (item.itemId == R.id.names) {
                materialToolbar.title = "Quran Audio"
                val settingint = Intent(this@QuranGrammarAct, NamesGridImageAct::class.java)
                settingint.putExtra(Constants.SURAH_INDEX, chapterno)
                startActivity(settingint)
            }
            /* if (item.itemId == R.id.mushafview) {
                 materialToolbar.title = "Mushaf"
                 val settingints = Intent(this@QuranGrammarAct, QuranGrammarAct::class.java)
                 //      settingints.putExtra(Constants.SURAH_INDEX, getChapterno());
                 startActivity(settingints)
             }*/
        }
        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId == R.id.bookmark) {
                drawerLayout.closeDrawers()
                val bookmarkFragment = BookmarkFragment()
                //  TameezDisplayFrag bookmarkFragment=new TameezDisplayFrag();
                val transactions = supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transactions.add(R.id.frame_container_qurangrammar, bookmarkFragment)
                    .addToBackStack("mujarrad")
                transactions.commit()
            }
            if (item.itemId == R.id.rootdetails) {
                drawerLayout.closeDrawers()
                /*       val bundle = Bundle()
                       val roots = Intent(this, arabicrootDetailHostActivity::class.java)
                       bundle.putString(Constant.WORDDETAILS, "word")
                       roots.putExtras(bundle)

                       startActivity(roots)*/
                val fragmentManager: FragmentManager = supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                val newCustomFragment = ArabicrootListFragment.newInstance("word")
                //    newCustomFragment.arguments = bundle
                transaction.replace(
                    R.id.frame_container_qurangrammar,
                    newCustomFragment,
                    "rootfragment"
                )
                transaction.addToBackStack(null)
                transaction.commit()
            }
            if (item.itemId == R.id.verbdetails) {
                /*                drawerLayout.closeDrawers()
                                   drawerLayout.closeDrawers()
                                    val verbdetails = Intent(this, QuranVerbRootDetailHostActivity::class.java)
                                    verbdetails.putExtra(Constant.WORDDETAILS, "verb")
                                    startActivity(verbdetails)*/
                drawerLayout.closeDrawers()
                val fragmentManager: FragmentManager = supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                val verbrootfragment = VerbrootListFragment.newInstance("VERB")
                //  QuranVerbRootListFragment

                //    newCustomFragment.arguments = bundle
                transaction.replace(
                    R.id.frame_container_qurangrammar,
                    verbrootfragment,
                    "verbfragment"
                )
                transaction.addToBackStack(null)
                transaction.commit()
            }
            if (item.itemId == R.id.ajroomiya_detail) {
                drawerLayout.closeDrawers()
                val grammar = Intent(this, NewAjroomiyaDetailHostActivity::class.java)
                startActivity(grammar)
            }
            if (item.itemId == R.id.phrases) {

                drawerLayout.closeDrawers()
                materialToolbar.title = "Topics"


               // val conjugatorintent = Intent(this@QuranGrammarAct, PhrasesGrammarAct::class.java)
                val conjugatorintent = Intent(this@QuranGrammarAct, MufradatPagerActivity::class.java)
                startActivity(conjugatorintent)

            }

            if (item.itemId == R.id.settings) {
                drawerLayout.closeDrawers()
                materialToolbar.title = "Settings"
                val settingint = Intent(this, ActivitySettings::class.java)
                startActivity(settingint)


            }

            if (item.itemId == R.id.searchtopic) {
                drawerLayout.closeDrawers()
                materialToolbar.title = "Topics"
                val searchs = Intent(this, QuranTopicSearchActivity::class.java)
                startActivity(searchs)
            }
            false
        }
    }// first time

    ////////////////
    private val isFirstTime: Boolean
        get() {
            val preferences = getPreferences(MODE_PRIVATE)
            val ranBefore = preferences.getBoolean("RanBefore", false)
            if (!ranBefore) {
                // first time
                val editor = preferences.edit()
                editor.putBoolean("RanBefore", true)
                editor.apply()
            }
            return !ranBefore
        }

    private fun getpreferences() {
        val pref = applicationContext.getSharedPreferences("lastread", MODE_PRIVATE)
        chapterno = pref.getInt(Constant.SURAH_ID, 1)
        verseNo = pref.getInt(Constant.AYAH_ID, 1)
        surahname = pref.getString(Constant.SURAH_ARABIC_NAME, "")
        surahArabicName = surahname.toString()
    }

    fun initDialogComponents(readposition: Int) {
        val quranEntity = allofQuran!![readposition - 1]
        val suraNames: Spinner
        val verses: Spinner
        val ok: Button
        val sorasShow: MutableList<String?>
        //   jumpDialog = new Dialog(this,R.style.Base_Theme_AppCompat_Dialog);
        val jumpDialog = Dialog(this)
        jumpDialog.setContentView(R.layout.backupjumb_to_popup)
        suraNames = jumpDialog.findViewById(R.id.suras)
        verses = jumpDialog.findViewById(R.id.verses)



        jumpDialog.show()

        ok = jumpDialog.findViewById(R.id.ok)
        val currentsurah = quranEntity.surah
        verseNumber = quranEntity.ayah
        sorasShow = java.util.ArrayList()
        soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>
        for ((count, entity) in soraList.withIndex()) {
            //  sorasShow.add(((++count) + " - " + (Locale.getDefault().getDisplayLanguage().equals("العربية") ? entity.getNamearabic() : entity.getAbjadname()).replace("$$$", "'")));
            val english = entity.nameenglish
            val abjad = entity.abjadname
            sorasShow.add((count + 1).toString() + " - " + english + "-" + abjad)
        }
        val show = sorasShow.toTypedArray()
        val verseAdapter: ArrayAdapter<String?>
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this,
            R.layout.myspinner, show
        )
        verseAdapter = ArrayAdapter<String?>(
            this,
            R.layout.spinner_layout_larg, verseNo
        )
        suraNames.adapter = adapter
        suraNames.setSelection(currentsurah - 1)
        surahArabicName = show[currentSelectSurah].toString()
        //  setSurahArabicName(show[getCurrentSelectSurah()])
        verses.adapter = verseAdapter
        //   verses.setSelection(verseNumber);
        suraNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long,
            ) {
                suraNumber = position + 1
                val sora: ChaptersAnaEntity = soraList[position]
                //   surahIndex.inputType = suraNumber
                val verseAdapter1: ArrayAdapter<String?>
                val versesNumbers = if (suraNumber == 1) sora.versescount + 1 else sora.versescount
                val numbers = arrayOfNulls<String>(versesNumbers)
                for (i in 1..versesNumbers) {
                    numbers[i - 1] = i.toString() + ""
                }
                verseAdapter1 = ArrayAdapter<String?>(
                    this@QuranGrammarAct,
                    R.layout.spinner_layout_larg, numbers
                )
                verses.adapter = verseAdapter1
                if (verseNumber <= numbers.size) {
                    verses.setSelection(verseNumber - 1)
                } else {
                    verses.setSelection(numbers.size - 1)
                }
                //    surahIndex.hint = suraNumber.toString() + ""
                //   sora.getNamearabic();
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        verses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long,
            ) {
                verseNumber = position + 1
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        ok.setOnClickListener {
            jumpDialog.dismiss()

            verseNo = verseNumber
            //    soraList.get(suraNumber).getAbjadname();
            surahArabicName =
                (suraNumber.toString() + "-" + soraList[suraNumber - 1].nameenglish + "-" + soraList[suraNumber - 1].abjadname)
            surahArabicName = (soraList[suraNumber - 1].abjadname)
            //  ayahIndex.getInputType();
            //   val text = ayahIndex.text
            versescount = (soraList[suraNumber - 1].versescount)
            isMakkiMadani = (soraList[suraNumber - 1].ismakki)
            rukucount = (soraList[suraNumber - 1].rukucount)
            currentSelectSurah = suraNumber
            //  setVerse_no(verseNumber);
            //      chapterno = suraNumber

            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView)








            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView)
            if (currentSelectSurah == this.chapterno) {
                parentRecyclerView.post {
                    parentRecyclerView.scrollToPosition(
                        verseNo
                    )
                }
            } else {
                jumptostatus = true


                surahorpart = currentSelectSurah
                surah_id = currentSelectSurah
                this.chapterno = currentSelectSurah





                ExecuteSurahWordByWord()
                //     asyncTaskcorpus = new refactoringcurrentSurahSyncWordByWord().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    private fun SurahAyahPicker() {
        val mTextView: TextView
        val chapterWheel: WheelView
        val verseWheel: WheelView

        surahWheelDisplayData = arrayOf("")
        ayahWheelDisplayData = arrayOf("")
        val current = ArrayList<String>()
        val chapterno = IntArray(1)
        val verseno = IntArray(1)
        val surahArrays = resources.getStringArray(R.array.surahdetails)
        val versearrays = resources.getStringArray(R.array.versescounts)
        val intarrays = resources.getIntArray(R.array.versescount)
        val dialogPicker = AlertDialog.Builder(this@QuranGrammarAct)
        val soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>
        val inflater = this@QuranGrammarAct.layoutInflater
        val view = inflater.inflate(R.layout.activity_wheel_t, null)
        //  View view = inflater.inflate(R.layout.activity_wheel, null);
        dialogPicker.setView(view)
        mTextView = view.findViewById(R.id.textView2)
        chapterWheel = view.findViewById(R.id.wv_year)
        verseWheel = view.findViewById(R.id.wv_month)
        chapterWheel.setEntries(*surahArrays)
        //  chapterWheel.setCurrentIndex(getSurahselected() - 1);
        chapterWheel.currentIndex = this.chapterno - 1
        //set wheel initial state
        val initial = true
        if (initial) {
            val text = chapterWheel.getItem(this.chapterno - 1) as String
            surahWheelDisplayData[0] = text
            val chapno = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            chapterno[0] = chapno[0].toInt()
            verseno[0] = 1
            val intarray: Int = if (this.chapterno != 0) {
                intarrays[this.chapterno - 1]
            } else {
                7
            }
            for (i in 1..intarray) {
                current.add(i.toString())
            }
            verseWheel.setEntriesv(current)
            val texts = surahWheelDisplayData[0] + "/" + ayahWheelDisplayData[0]
            //   = mYear[0]+ mMonth[0];
            mTextView.text = texts
        }

        versearrays[this.chapterno - 1].toInt()

        verseWheel.setEntriesv(current)
        verseWheel.currentIndex = this.verseNo
        dialogPicker.setPositiveButton("Done") { dialogInterface: DialogInterface?, i: Int ->
            try {
                surahArabicName =
                    suraNumber.toString() + "-" + soraList[chapterno[0] - 1].nameenglish + "-" + soraList[chapterno[0] - 1].abjadname
                surahArabicName = soraList[chapterno[0] - 1].abjadname
                verseNo = verseno[0]
                versescount = soraList[chapterno[0] - 1].versescount
                isMakkiMadani = soraList[chapterno[0] - 1].ismakki
                rukucount = soraList[chapterno[0] - 1].rukucount
                currentSelectSurah = soraList[chapterno[0] - 1].chapterid
            } catch (e: ArrayIndexOutOfBoundsException) {
                surahArabicName =
                    suraNumber.toString() + "-" + soraList[chapterno[0]].nameenglish + "-" + soraList[chapterno[0]].abjadname
                surahArabicName = soraList[chapterno[0]].abjadname
                verseNo = 1
                versescount = soraList[chapterno[0]].versescount
                isMakkiMadani = soraList[chapterno[0]].ismakki
                rukucount = soraList[chapterno[0]].rukucount
                currentSelectSurah = soraList[chapterno[0]].chapterid
                this.chapterno = soraList[chapterno[0]].chapterid
                //   println(soraList[chapterno[0]].chapterid)
                println("chapterno" + this.chapterno)
                println(this.chapterno)
            }
            parentRecyclerView = binding.overlayViewRecyclerView
            val myFragment: NewSurahDisplayFrag? =
                supportFragmentManager.findFragmentByTag(SURAHFRAGTAG) as NewSurahDisplayFrag?


            val mngr = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val taskList = mngr.getRunningTasks(10)
            val baseActivity = taskList.get(0).baseActivity
            val contains = taskList.get(0).baseActivity!!.className.contains("QuranGrammar")
            if (myFragment != null && myFragment.isVisible()) {
                val settingint = Intent(this, QuranGrammarAct::class.java)
                settingint.putExtra(Constant.SURAH_ID, this.chapterno)

                settingint.putExtra(Constant.RUKUCOUNT, rukucount)
                settingint.putExtra(Constant.ISMAKKI, isMakkiMadani)
                settingint.putExtra(Constant.VERSESCOUNT, versescount)
                settingint.putExtra(Constant.SURAHNAME, surahArabicName)
                startActivity(settingint)
            } else

                if (currentSelectSurah == this.chapterno) {
                    parentRecyclerView.post { parentRecyclerView.scrollToPosition(verseNo) }
                } else {
                    jumptostatus = true
                    surahorpart = currentSelectSurah
                    surah_id = currentSelectSurah
                    this.chapterno = currentSelectSurah
//                println(soraList[chapterno[0]].chapterid)
                    println("chapterno" + this.chapterno)
                    println(this.chapterno)
                    ExecuteSurahWordByWord()
                    //     asyncTaskcorpus = new refactoringcurrentSurahSyncWordByWord().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            val sura: String = soraList[chapterno[0] - 1].chapterid.toString()
            this.chapterno = soraList[chapterno[0] - 1].chapterid
            surahArabicName = soraList[chapterno[0] - 1].nameenglish
            surahArabicName = soraList[chapterno[0] - 1].namearabic
            val pref = getSharedPreferences("lastreadmushaf", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putInt(Constant.CHAPTER, sura.toInt())
            //  editor.putInt("page", page.getAyahItemsquran().get(0).getPage());
            editor.putString(
                Constant.SURAH_ARABIC_NAME,
                soraList[chapterno[0] - 1].namearabic
            )
            editor.apply()
        }
        dialogPicker.setNegativeButton("Cancel") { dialogInterface: DialogInterface?, i: Int -> }
        val alertDialog = dialogPicker.create()
        val preferences = shared.getString("themepref", "dark")
        when (preferences) {
            "light" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.md_theme_dark_onSecondary)
            "brown" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.background_color_light_brown)
            "blue" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.prussianblue)
            "green" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.mdgreen_theme_dark_onPrimary)
        }
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        //   alertDialog.show();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.show()
        val buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonPositive.setTextColor(ContextCompat.getColor(this@QuranGrammarAct, R.color.green))
        val buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        buttonNegative.setTextColor(ContextCompat.getColor(this@QuranGrammarAct, R.color.red))
        when (preferences) {
            "light", "brown" -> {
                buttonPositive.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.colorMuslimMate
                    )
                )
                buttonNegative.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.red
                    )
                )
            }

            "blue" -> {
                buttonPositive.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.yellow
                    )
                )
                buttonNegative.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.Goldenrod
                    )
                )
            }

            "green" -> {
                buttonPositive.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.yellow
                    )
                )
                buttonNegative.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.cyan_light
                    )
                )
            }
        }
        //  wmlp.gravity = Gravity.TOP | Gravity.CENTER;
        alertDialog.window!!.attributes = lp
        alertDialog.window!!.setGravity(Gravity.TOP)
        chapterWheel.onWheelChangedListener = object : OnWheelChangedListener {
            override fun onChanged(wheel: WheelView, oldIndex: Int, newIndex: Int) {
                val text = chapterWheel.getItem(newIndex) as String
                surahWheelDisplayData[0] = text
                val chapno = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                chapterno[0] = chapno[0].toInt()
                verseno[0] = 1
                updateVerses(newIndex)
                updateTextView()
                //    updateTextView();
            }

            private fun updateVerses(newIndex: Int) {
                val intarray: Int = if (newIndex != 0) {
                    intarrays[newIndex]
                } else {
                    7
                }
                for (i in 1..intarray) {
                    current.add(i.toString())
                }
                verseWheel.setEntriesv(current)
                updateTextView()
            }

            private fun updateTextView() {
                val text = surahWheelDisplayData[0] + "/" + ayahWheelDisplayData[0]
                //   = mYear[0]+ mMonth[0];
                mTextView.text = text
            }
        }
        verseWheel.onWheelChangedListener =
            OnWheelChangedListener { wheel, oldIndex, newIndex ->
                val text = verseWheel.getItem(newIndex) as String
                ayahWheelDisplayData[0] = text
                verseno[0] = text.toInt()
            }
    }

    private fun SetTranslation() {
        shared.getBoolean("prefs_show_erab", true)
        ExecuteSurahWordByWord()
    }

    private fun ExecuteSurahWordByWord() {
        val builder = AlertDialog.Builder(
            this,
            com.google.android.material.R.style.ThemeOverlay_Material3_Dialog
        )
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        val dialog = builder.create()
        val listener: OnItemClickListenerOnLong = this
        lateinit var scope: CoroutineScope
        val corpus = CorpusUtilityorig(this)
        scope = CoroutineScope(Dispatchers.Main)
        /*      if(chapterno==1 || chapterno==2) {
                  newbysurah(dialog)
              }else {
                  bysurah(dialog, scope, corpus, listener)
              }*/

        bysurah(dialog, scope, corpus, listener)
        /*
                val ex = Executors.newSingleThreadExecutor()
                ex.execute {
                    //do inbackground
                    bysurah(dialog, ex)
                }*/
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun newbysurah(dialog: AlertDialog) {
        // runOnUiThread { dialog.show() }
        val utils = Utils(this)
        val surahJson = utils.getSurahJson(chapterno)
        //    val surahJson1 = utils.getJsonstr(chapterno)
        val jasonstr = surahJson!!.get(0)!!.jasonstr
        /*
                mafoolbihiwords = mainViewModel.getMafoolSurah(chapterno).value
                Jumlahaliya = mainViewModel.getHalsurah(chapterno).value
                Tammezent = mainViewModel.getTameezsurah(chapterno).value
                Liajlihient = mainViewModel.getLiajlihiSurah(chapterno).value
                Mutlaqent = mainViewModel.getMutlaqSurah(chapterno).value
                BadalErabNotesEnt = mainViewModel.getbadalSurah(chapterno).value
        */


        val wbwSurah = WbwSurah(this@QuranGrammarAct, chapterno, corpusayahWordArrayList, passage)
        wbwSurah.wordbyword
        val corpusayahWordArrayList1 = wbwSurah.corpusayahWordArrayList
        val corpuss = NewCorpusUtilityorig(this)
        //     corpus.highLightVerbs(corpusayahWordArrayList,surah_id);
        if (kana) {
            corpusayahWordArrayList1?.let { corpuss.setKana(it, chapterno) }
        }
        if (shart) {
            corpusayahWordArrayList1?.let { corpuss.setShart(it, chapterno) }
        }
        if (mudhaf) {
            corpusayahWordArrayList1?.let { corpuss.setMudhafFromDB(it, chapterno) }
        }
        if (mausoof) {
            corpusayahWordArrayList1?.let { corpuss.SetMousufSifaDB(it, chapterno) }
            //  corpus.NewMAOUSOOFSIFA(corpusayahWordArrayList);
        }
        if (harfnasb) {
            corpusayahWordArrayList1?.let { corpuss.newnewHarfNasbDb(it, chapterno) }
        }
        val gson = Gson()
        val json = gson.toJson(corpusayahWordArrayList1)
        val filename = "${surahArabicName}!!.json"
        // val createFileInAppDirectory = FileUtility.createFileInAppDirectory(this, filename, json)
        //  var newnewadapterlists = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
        val objectMapper = ObjectMapper()
        val jacksonData = objectMapper.writeValueAsString(corpusayahWordArrayList1)
        val createFileInAppDirectory = FileUtility.createFileInAppDirectory(this, filename, json)

        println(jacksonData)
        println(json)


        val type: Type = object : TypeToken<ArrayList<CorpusAyahWord?>?>() {}.type
        val contactList: ArrayList<CorpusAyahWord> = gson.fromJson(jasonstr, type)


        //   extracted(utils)


        //


        /*       val filename = "$surahArabicName.json"
               // val createFileInAppDirectory = FileUtility.createFileInAppDirectory(this, filename, json)
               //  var newnewadapterlists = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
               val objectMapper = ObjectMapper()
               val jacksonData = objectMapper.writeValueAsString(corpusayahWordArrayList1)
               val createFileInAppDirectory = FileUtility.createFileInAppDirectory(this, filename, jacksonData)

               val type: Type = object : TypeToken<List<CorpusAyahWord?>?>() {}.type
               val contactList: List<CorpusAyahWord> = gson.fromJson(json, type)
               for (contact in contactList) {
                   Log.i(
                       "Contact Details",
                       (contact.ar_irab_two+ "-" + contact.word).toString() + "-" + contact.spannableverse
                   )
               }
       */


        //     corpus.highLightVerbs(corpusayahWordArrayList,surah_id);
        //post
        runOnUiThread {
            // dialog.dismiss()
            // ex.shutdown()
            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView)
            allofQuran = utils?.getQuranbySurah(chapterno)
            if (jumptostatus) {
                surahorpart = chapterno
            }
            val listener: OnItemClickListenerOnLong = this
            val header = ArrayList<String?>()
            header.add(rukucount.toString())
            header.add(versescount.toString())
            header.add(chapterno.toString())
            header.add(surahArabicName)
            //  HightLightKeyWord()
            if (!mushafview) {
                var flowAyahWordAdapter = FlowAyahWordAdapter(
                    false,
                    passage,
                    Mutlaqent,
                    Tammezent,
                    BadalErabNotesEnt,
                    Liajlihient,
                    Jumlahaliya,
                    mafoolbihiwords,
                    header,
                    allofQuran,
                    contactList,
                    this@QuranGrammarAct,
                    surah_id.toLong(),
                    surahArabicName,
                    isMakkiMadani,
                    listener
                )
                flowAyahWordAdapter!!.addContext(this@QuranGrammarAct)
                parentRecyclerView.setHasFixedSize(true)
                parentRecyclerView.setAdapter(flowAyahWordAdapter)
                flowAyahWordAdapter!!.notifyDataSetChanged()
                parentRecyclerView.post(Runnable { parentRecyclerView.scrollToPosition(1) })
            }
        }
    }

    private fun extracted(utils: Utils) {
        val allAnaChapters = utils.getAllAnaChapters();
        if (allAnaChapters != null) {
            for (chap in allAnaChapters) {
                val wbwSurah =
                    WbwSurah(this@QuranGrammarAct, chapterno, corpusayahWordArrayList, passage)
                wbwSurah.wordbyword
                val corpusayahWordArrayList1 = wbwSurah.corpusayahWordArrayList
                val corpuss = NewCorpusUtilityorig(this)
                //     corpus.highLightVerbs(corpusayahWordArrayList,surah_id);
                if (kana) {
                    corpusayahWordArrayList1?.let { corpuss.setKana(it, chapterno) }
                }
                if (shart) {
                    corpusayahWordArrayList1?.let { corpuss.setShart(it, chapterno) }
                }
                if (mudhaf) {
                    corpusayahWordArrayList1?.let { corpuss.setMudhafFromDB(it, chapterno) }
                }
                if (mausoof) {
                    corpusayahWordArrayList1?.let { corpuss.SetMousufSifaDB(it, chapterno) }
                    //  corpus.NewMAOUSOOFSIFA(corpusayahWordArrayList);
                }
                if (harfnasb) {
                    corpusayahWordArrayList1?.let { corpuss.newnewHarfNasbDb(it, chapterno) }
                }
                val gson = Gson()
                val json = gson.toJson(corpusayahWordArrayList1)


                val filename = "${chap!!.chapterid},${chap.nameenglish}.json"
                // val createFileInAppDirectory = FileUtility.createFileInAppDirectory(this, filename, json)
                //  var newnewadapterlists = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
                val objectMapper = ObjectMapper()
                val jacksonData = objectMapper.writeValueAsString(corpusayahWordArrayList1)
                val createFileInAppDirectory =
                    FileUtility.createFileInAppDirectory(this, filename, jacksonData)

            }


        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun bysurah(
        dialog: AlertDialog,
        ex: CoroutineScope,
        corpus: CorpusUtilityorig,
        listener: OnItemClickListenerOnLong,
    ) {
        runOnUiThread { dialog.show() }
      /**/  val    verseModel = ViewModelProviders.of(
            this,
            newViewModelFactory(chapterno)
        ).get(VerseModel::class.java)
        ex.launch {
                 mafoolbihiwords = mainViewModel.getMafoolSurah(chapterno).value!!
                  Jumlahaliya = mainViewModel.getHalsurah(chapterno).value
                  Tammezent = mainViewModel.getTameezsurah(chapterno).value
                  Liajlihient = mainViewModel.getLiajlihiSurah(chapterno).value
                  Mutlaqent = mainViewModel.getMutlaqSurah(chapterno).value
                  BadalErabNotesEnt = mainViewModel.getbadalSurah(chapterno).value
            val startTime = System.nanoTime()
          // val cardss: List<QuranArrays> by verseModel.uiStates.collectAsState()

          //  val cardss by verseModel.cards.collectAsStateWithLifecycle()


            allofQuran = mainViewModel.getquranbySUrah(chapterno).value




            corpusSurahWord = mainViewModel.getQuranCorpusWbwbysurah(chapterno).value

          newnewadapterlist = CorpusUtilityorig.composeWBWCollection(allofQuran, corpusSurahWord)


            Log.e("Measure", "TASK took : " + ((System.nanoTime() - startTime) / 1000000) + "mS\n")

            if (kana) {
                newnewadapterlist.let { corpus.setKana(it, chapterno) }
            }
            if (shart) {
                newnewadapterlist.let { corpus.setShart(it, chapterno) }
            }
            if (mudhaf) {
                newnewadapterlist.let {
                    corpus.setMudhafFromDB(
                        it,
                        chapterno
                    )
                }
                //   corpusayahWordArrayList?.get(0)?.let { corpus.setMudhafFromDB(it, chapterno) }
            }
            if (mausoof) {
                newnewadapterlist.let {
                    corpus.SetMousufSifaDB(
                        it,
                        chapterno
                    )
                }
                //  corpus.NewMAOUSOOFSIFA(corpusayahWordArrayList);
            }
            if (harfnasb) {
                newnewadapterlist.let {
                    corpus.newnewHarfNasbDb(
                        it,
                        chapterno
                    )
                }
            }
            val gson = Gson()
            val json = gson.toJson(newnewadapterlist)
            println(json)
            //


            Log.e("Measure", "TASK took : " + ((System.nanoTime() - startTime) / 1000000) + "mS\n")







            parentRecyclerView = binding.overlayViewRecyclerView

            if (jumptostatus) {
                surahorpart = chapterno
            }
            val header = ArrayList<String>()
            header.add(rukucount.toString())
            header.add(versescount.toString())
            header.add(chapterno.toString())
            header.add(surahArabicName)
            HightLightKeyWord(allofQuran)
            runOnUiThread {
                dialog.dismiss()
            }


            val viewmodel: QuranVIewModel by viewModels()
            if (!mushafview) {
                // viewmodel.getVersesBySurahLive(chapterno).observe(this, {
                //    allofQuran=it
                newflowAyahWordAdapter = newFlowAyahWordAdapter(
                    false,
                    Mutlaqent,
                    Tammezent,
                    BadalErabNotesEnt,
                    Liajlihient,
                    Jumlahaliya,
                    mafoolbihiwords,
                    header,
                    allofQuran,
                    newnewadapterlist,
                    this@QuranGrammarAct,
                    surahArabicName,
                    isMakkiMadani,
                    listener
                )

                newflowAyahWordAdapter.addContext(this@QuranGrammarAct)
                parentRecyclerView.setHasFixedSize(true)
                parentRecyclerView.adapter = newflowAyahWordAdapter
                //   flowAyahWordAdapter.notifyDataSetChanged()
                parentRecyclerView.post { parentRecyclerView.scrollToPosition(verseNo) }
                //   })
            }

        }

    }

    class newViewModelFactory(private val dbname: Int) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
            VerseModel(dbname,) as T
    }
    class ViewModelFactory(private val dbname: Int) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VerseModel::class.java)) {
                return VerseModel(dbname) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }

    private fun LoadItemList(dataBundle: Bundle, word: QuranEntity) {
        /*     val builder = AlertDialog.Builder(this)
             builder.setCancelable(false) // if you want user to wait for some process to finish,
             builder.setView(R.layout.layout_loading_dialog)
             val item = GrammerFragmentsBottomSheet()
             val fragmentManager = supportFragmentManager
             item.arguments = dataBundle

             fragmentManager.beginTransaction()
                 .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out).show(item)
             // transactions.show(item);
             val data =
                 arrayOf(word.surah.toString(), word.ayah.toString(), word.translation, 1.toString())
             GrammerFragmentsBottomSheet.newInstance(data)
                 .show(supportFragmentManager, WordAnalysisBottomSheet.TAG)*/


        val homeactivity = Intent(this@QuranGrammarAct, SentenceGrammarAnalysis::class.java)
        homeactivity.putExtras(dataBundle!!)
        //  val homeactivity = Intent(this@MainActivity, DownloadListActivity::class.java)

        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(homeactivity)
        //    finish();

    }

    override fun onItemClick(v: View, position: Int) {
        qurangrammarmenu(v, position)
        //  popupWindow(view);
    }

    @SuppressLint("RestrictedApi", "InflateParams")
    fun qurangrammarmenu(view: View, position: Int) {
        val tag = view.tag
        //  val quranEntity: QuranEntity = flowAyahWordAdapter.getItem(position) as QuranEntity
        val quranEntity: QuranEntity = try {
            allofQuran!![position]
        } catch (e: IndexOutOfBoundsException) {
            allofQuran!![position - 1]
        }
        val colorsentence = view.findViewById<SwitchCompat>(R.id.colorized)
        val colortag = shared.getBoolean("colortag", true)
        if (tag == "bookmarfb") {
            bookMarkSelected(position)
        } else if (tag == "collection") {
            val dataBundle = Bundle()
            val chapterno = corpusSurahWord!![position - 1].corpus.surah
            val verse = corpusSurahWord!![position - 1].corpus.ayah


            dataBundle.putInt(Constant.SURAH_ID, chapterno)
            dataBundle.putInt(Constant.AYAHNUMBER, verse)
            dataBundle.putString(Constant.SURAH_ARABIC_NAME, surahArabicName)
            val item = BookMarkCreateFrag()
            item.arguments = dataBundle
            val data = arrayOf(chapterno.toString(), verse.toString(), surahArabicName)
            //    item.setdata(root!!WordMeanings,wbwRootwords,grammarRootsCombined);
            //   transactions.show(item);
            BookMarkCreateFrag.newInstance(data)
                .show(this@QuranGrammarAct.supportFragmentManager, WordAnalysisBottomSheet.TAG)


        }
        if (tag == "arrowforward") {
            val currentsurah = quranEntity.surah
            val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
            if (currentsurah != 114) {
                soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>

                verseNumber = quranEntity.ayah
                val intent = intent.putExtra("chapter", soraList[0].chapterid)
                    .putExtra("chapterorpart", chapterorpart).putExtra(
                        Constant.SURAH_ARABIC_NAME, soraList[0].abjadname
                    )
                    .putExtra(Constant.VERSESCOUNT, soraList[0].versescount)
                    .putExtra(Constant.RUKUCOUNT, soraList[0].rukucount).putExtra(
                        Constant.MAKKI_MADANI, soraList[0].ismakki
                    )
                overridePendingTransition(0, 0)
                startActivity(intent)
                finish()
                overridePendingTransition(
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left
                )
            }
        } else if (tag == "arrowback") {
            val currentsurah = quranEntity.surah
            if (currentsurah != 1) {
                val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]

                soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>


                verseNumber = quranEntity.ayah
                val intent = intent.putExtra("chapter", soraList[0].chapterid)
                    .putExtra("chapterorpart", chapterorpart).putExtra(
                        Constant.SURAH_ARABIC_NAME, soraList[0].abjadname
                    )
                    .putExtra(Constant.VERSESCOUNT, soraList[0].versescount)
                    .putExtra(Constant.RUKUCOUNT, soraList[0].rukucount).putExtra(
                        Constant.MAKKI_MADANI, soraList[0].ismakki
                    )
                overridePendingTransition(0, 0)
                startActivity(intent)
                finish()
                overridePendingTransition(
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left
                )
            }
        } else if (tag == "colorize") {
            if (colortag) {
                val editor =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(this@QuranGrammarAct)
                        .edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", false)
                editor.apply()
                ReloadActivity(colorsentence)
            } else {
                val editor =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(this@QuranGrammarAct)
                        .edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", true)
                editor.apply()
                ReloadActivity(colorsentence)
            }
        } else if (tag == "overflowbottom") {
            val chapterno = corpusSurahWord!![position - 1].corpus.surah
            val verse = corpusSurahWord!![position - 1].corpus.ayah
            val name = surahArabicName
            val data = arrayOf(chapterno.toString(), verse.toString(), name)
            BottomOptionDialog.newInstance(data)
                .show(this@QuranGrammarAct.supportFragmentManager, WordAnalysisBottomSheet.TAG)
        } else if (tag == "jumptofb") {
            initDialogComponents(position)
        } else if (tag == "sharefb") {
            takeScreenShot(window.decorView)
        } else if (tag == "helpfb") {
            val chapterno = corpusSurahWord!![position - 1].corpus.surah
            val dataBundle = Bundle()
            dataBundle.putInt(Constant.SURAH_ID, chapterno)
            val item = SurahSummary()
            item.arguments = dataBundle
            SurahSummary.newInstance(chapterno).show(supportFragmentManager, NamesDetail.TAG)
        } else if (tag == "overflow_img") {
            @SuppressLint("RestrictedApi") val menuBuilder = MenuBuilder(this)
            val inflater = MenuInflater(this)
            inflater.inflate(R.menu.popup_menu, menuBuilder)
            @SuppressLint("RestrictedApi") val optionsMenu =
                MenuPopupHelper(this, menuBuilder, view)
            optionsMenu.setForceShowIcon(true)
// Set Item Click Listener
            menuBuilder.setCallback(object : MenuBuilder.Callback {
                override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
                    if (item.itemId == R.id.actionTafsir) { // Handle option1 Click
                        val readingintent =
                            Intent(this@QuranGrammarAct, TafsirFullscreenActivity::class.java)
                        val chapterno = corpusSurahWord!![position - 1].corpus.surah
                        val verse = corpusSurahWord!![position - 1].corpus.ayah
                        readingintent.putExtra(Constant.SURAH_ID, chapterno)
                        readingintent.putExtra(Constant.AYAH_ID, verse)
                        readingintent.putExtra(Constant.SURAH_ARABIC_NAME, surahArabicName)
                        startActivity(readingintent)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.bookmark) { // Handle option2 Click
                        bookMarkSelected(position)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.jumpto) { // Handle option2 Click
                        //  SurahAyahPicker();
                        initDialogComponents(position)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.action_share) {
                        takeScreenShot(window.decorView)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.ivHelp) { // Handle option2 Click
                        ParticleColorScheme.newInstance().show(
                            this@QuranGrammarAct.supportFragmentManager,
                            WordAnalysisBottomSheet.TAG
                        )
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.colorized) { // Handle option2 Click
                        if (colortag) {
                            val editor =
                                android.preference.PreferenceManager.getDefaultSharedPreferences(
                                    this@QuranGrammarAct
                                )
                                    .edit()
                            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                            editor.putBoolean("colortag", false)
                            editor.apply()
                            ReloadActivity()
                        } else {
                            val editor =
                                android.preference.PreferenceManager.getDefaultSharedPreferences(
                                    this@QuranGrammarAct
                                )
                                    .edit()
                            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                            editor.putBoolean("colortag", true)
                            editor.apply()
                            ReloadActivity()
                        }
                        optionsMenu.dismiss()
                        return true
                    }
                    return false
                }

                override fun onMenuModeChange(menu: MenuBuilder) {}
            })
            optionsMenu.show()
        } else if (tag == "help_img") {
            println("check")
            ParticleColorScheme.newInstance()
                .show(this@QuranGrammarAct.supportFragmentManager, WordAnalysisBottomSheet.TAG)
        } else if (tag == "qurantext") {
            val word: QuranEntity = if (position != 0) {
                allofQuran!![position - 1]
            } else {
                allofQuran!![position]
            }
            val dataBundle = Bundle()
            dataBundle.putInt(Constant.SURAH_ID, word.surah)
            dataBundle.putInt(Constant.AYAH_ID, Math.toIntExact(word.ayah.toLong()))
            LoadItemList(dataBundle, word)
        }
    }

    private fun takeScreenShot(view: View) {
        val date = Date()
        val format = DateFormat.format("MM-dd-yyyy_hh:mm:ss", date)
        try {
            val mainDir = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES), "FilShare"
            )
            val path = "$mainDir/Mushafapplication-$format.jpeg"
            //    File zipfile = new File(getExternalFilesDir(null).getAbsolutePath() + getString(R.string.app_folder_path) + File.separator + DATABASEZIP);
            view.isDrawingCacheEnabled = true
            val color = Color.RED
            val bitmap = getBitmapFromView(view, color)
            val imageFile = File(path)
            val fileOutputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            shareScreenShot(imageFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getBitmapFromView(view: View, defaultColor: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width, view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        canvas.drawColor(defaultColor)
        view.draw(canvas)
        return bitmap
    }

    private fun shareScreenShot(imageFile: File) {
        val uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            imageFile
        )
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_TEXT, "Download Application from Instagram")
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        val resInfoList =
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        //  startActivity(Intent.createChooser(intent, "Share PDF using.."));
        try {
            this.startActivity(Intent.createChooser(intent, "Share With"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "No App Available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ReloadActivity() {
        Log.e(TAG, "onClick called")
        val intent =
            intent.putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(
                Constant.SURAH_ARABIC_NAME, surahArabicName
            )
                .putExtra("passages", mushafview)
                .putExtra(Constant.VERSESCOUNT, versescount).putExtra(Constant.RUKUCOUNT, rukucount)
                .putExtra(
                    Constant.MAKKI_MADANI, isMakkiMadani
                )
        overridePendingTransition(0, 0)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
    }

    private fun bookMarkSelected(position: Int) {
        //  position = flowAyahWordAdapter.getAdapterposition();
        val chapterno = corpusSurahWord!![position - 1].corpus.surah
        val verse = corpusSurahWord!![position - 1].corpus.ayah
        val en = BookMarks()
        en.header = "pins"
        en.chapterno = chapterno.toString()
        en.verseno = verse.toString()
        en.surahname = surahArabicName
        val viewmodel: QuranVIewModel by viewModels()

        viewmodel.Insertbookmark(en)
        val view = findViewById<View>(R.id.bookmark)
        val snackbar = Snackbar
            .make(view, "BookMark Created", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setTextColor(Color.CYAN)
        snackbar.setBackgroundTint(Color.BLACK)
        snackbar.show()
    }

    private fun ReloadActivity(colorsentence: SwitchCompat) {
        Log.e(TAG, "onClick called")
        val intent =
            intent.putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(
                Constant.SURAH_ARABIC_NAME, surahArabicName
            )
                .putExtra(Constant.VERSESCOUNT, versescount).putExtra(Constant.RUKUCOUNT, rukucount)
                .putExtra(
                    Constant.MAKKI_MADANI, isMakkiMadani
                )
        overridePendingTransition(0, 0)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
    }

    override fun onItemLongClick(position: Int, v: View) {
        //    Toast.makeText(this, "longclick", Toast.LENGTH_SHORT).show();
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        btnBottomSheet = binding.fab
        val verlayViewRecyclerView = findViewById<RecyclerView>(R.id.overlayViewRecyclerView)
        verlayViewRecyclerView.layoutManager = linearLayoutManager
        // bookmarkchip.setOnClickListener(v -> CheckStringLENGTHS());
    }

    private fun toggleBottomSheets() {
        if (bottomNavigationView.visibility == View.VISIBLE) {
            bottomNavigationView.visibility = View.GONE
            //    btnBottomSheet.setText("Close sheet");
        } else {
            bottomNavigationView.visibility = View.VISIBLE
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    /*    override fun ondatarecevied(
            chapterno: Int,
            partname: String?,
            totalverses: Int,
            rukucount: Int,
            makkimadani: Int,
                                   ) {
            Log.e(TAG, "onClick called")
            val intent =
                intent.putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(
                    Constant.SURAH_ARABIC_NAME, partname
                                                                                                       )
                    .putExtra(Constant.VERSESCOUNT, totalverses).putExtra(Constant.RUKUCOUNT, rukucount)
                    .putExtra(
                        Constant.MAKKI_MADANI, makkimadani
                             )
            overridePendingTransition(0, 0)
            startActivity(intent)
            finish()
            overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
        }*/
    companion object {
        private const val TAG = "fragment"
    }
}


/*       if (item.itemId == R.id.search) {
            drawerLayout.closeDrawers()
            materialToolbar.title = "Root Word Search"
            val search = Intent(this, VoiceRecognitionActivity::class.java)
            startActivity(search)
        }
        if (item.itemId == R.id.surahcompose) {
            drawerLayout.closeDrawers()
            materialToolbar.title = "Topics"
            val searchs = Intent(this, SurahComposeAct::class.java)
            startActivity(searchs)
        }*/
/*
            if (item.itemId == R.id.bottomcompose) {
        */
/*        drawerLayout.closeDrawers()
                materialToolbar.title = "Topics"
                val searchs = Intent(this, BottomCompose::class.java)
                startActivity(searchs)*//*


                val conjugatorintent = Intent(this@QuranGrammarAct, PhrasesGrammarAct::class.java)

                startActivity(conjugatorintent)

            }
*/
