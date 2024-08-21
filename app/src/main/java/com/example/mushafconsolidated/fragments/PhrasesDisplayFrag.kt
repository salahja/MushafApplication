package com.example.mushafconsolidated.fragments


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.Constant.ISMAKKI
import com.example.Constant.RUKUCOUNT
import com.example.Constant.SURAHNAME
import com.example.Constant.SURAH_ID
import com.example.Constant.VERSESCOUNT
import com.example.mushafconsolidated.Activity.PhrasesGrammarAct
import com.example.mushafconsolidated.Activity.ShowMushafActivity
import com.example.mushafconsolidated.Adaptersimport.JuzSurahDisplayAdapter
import com.example.mushafconsolidated.Adaptersimport.NewSurahDisplayAdapter
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.drawable.custom_search_box
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.databinding.PhrasesListSurahJuzBinding
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.intrfaceimport.PassdataInterface
import com.example.mushafconsolidated.model.Juz
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import database.NamesGridImageAct
import org.sj.conjugator.activity.ConjugatorAct
import sj.hisnul.activity.HisnulBottomACT
import java.util.Objects


/**
 * A simple [Fragment] subclass.
 * Use the [PhrasesDisplayFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhrasesDisplayFrag : Fragment(), SearchView.OnQueryTextListener {
    //   implements FragmentCommunicator {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //   private static final String ARG_PARAM1 = "param1";
    //   private static final String ARG_PARAM2 = "param2";
    private lateinit var parentRecyclerView: RecyclerView

    //   private RecyclerView.Adapter ParentAdapter;
    private lateinit var ParentAdapter: NewSurahDisplayAdapter
    private lateinit var juzSurahDisplayAdapter: JuzSurahDisplayAdapter
    lateinit var binding: PhrasesListSurahJuzBinding
    //  SurahDisplayAdapter ParentAdapter;
    private lateinit var mItemClickListener: OnItemClickListener
    private lateinit var btnBottomSheet: FloatingActionButton
    lateinit var datapasser: PassdataInterface
    private var lastreadchapterno = 0
    private var lastreadverseno = 0
    private lateinit var allAnaChapters: ArrayList<ChaptersAnaEntity>
    private lateinit var parts: List<Juz>
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private lateinit var searchint: TextView
    private lateinit var bottomNavigationView: NavigationBarView
    lateinit var innard: RadioButton
    lateinit var kanard: RadioButton
    lateinit var shartrd: RadioButton
    lateinit var mudhafrd: RadioButton
    lateinit var mousufrd: RadioButton
    lateinit var phraserg:RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PhrasesListSurahJuzBinding.inflate(layoutInflater)
    }

    private fun setToolbarMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                var searchView = menu.findItem(R.id.search).actionView as SearchView?
                val searchItem = menu.findItem(R.id.search)
                val searchManager = Objects.requireNonNull(requireActivity()).getSystemService(
                    Context.SEARCH_SERVICE
                ) as SearchManager
                if (searchItem != null) {
                    searchView = searchItem.actionView as SearchView?
                    val sear = ContextCompat.getDrawable(
                        Objects.requireNonNull(requireActivity()),
                        custom_search_box
                    )
                    searchView!!.clipToOutline = true
                    searchView.setBackgroundDrawable(sear)
                    searchView.gravity = View.TEXT_ALIGNMENT_CENTER
                    searchView.maxWidth = Int.MAX_VALUE
                }
                if (searchView != null) {
                    searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
                    queryTextListener = object : SearchView.OnQueryTextListener {
                        override fun onQueryTextChange(newText: String): Boolean {
                            //   Log.i("onQueryTextChange", newText);
                            ParentAdapter.filter.filter(newText)
                            return true
                        }

                        override fun onQueryTextSubmit(query: String): Boolean {
                            //    Log.i("onQueryTextSubmit", query);
                            ParentAdapter.filter.filter(query)
                            return false
                        }
                    }
                    searchView.setOnQueryTextListener(queryTextListener)
                }
                //   super@MenuProvider.onPrepareMenu(menu)
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_search, menu)
                var searchView = menu.findItem(R.id.search).actionView as SearchView?
                val searchItem = menu.findItem(R.id.search)
                val searchManager =
                    activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
                if (searchItem != null) {
                    searchView = searchItem.actionView as SearchView?
                    val sear = ContextCompat.getDrawable(activity!!, custom_search_box)
                    searchView!!.clipToOutline = true
                    searchView!!.setBackgroundDrawable(sear)
                    searchView!!.gravity = View.TEXT_ALIGNMENT_CENTER
                    searchView!!.maxWidth = Int.MAX_VALUE
                }
                if (searchView != null) {
                    searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
                    queryTextListener = object : SearchView.OnQueryTextListener {
                        override fun onQueryTextChange(newText: String): Boolean {
                            //   Log.i("onQueryTextChange", newText);
                            ParentAdapter.filter.filter(newText)
                            return true
                        }

                        override fun onQueryTextSubmit(query: String): Boolean {
                            //    Log.i("onQueryTextSubmit", query);
                            ParentAdapter.filter.filter(query)
                            return false
                        }
                    }
                    searchView!!.setOnQueryTextListener(queryTextListener)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.search) {
                    searchint.visibility = View.VISIBLE
                }
                return false
            }

            override fun onMenuClosed(menu: Menu) {
                // super@MenuProvider.onMenuClosed(menu)
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        run {}
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        container?.removeAllViews()
       // binding = PhrasesListSurahJuzBinding.inflate(layoutInflater)
        //     View view = inflater.inflate(R.layout.list_surah_juz, container, false);
        val view = inflater.inflate(R.layout.phrases_list_surah_juz, container, false)
        initnavagation(view)
  phraserg=      view.findViewById(R.id.phrase)
    //    phraserg = findViewById(R.id.verbcase)
        //phraserg=binding.phrase
        kanard=  binding.kana
        innard=binding.inna
        shartrd=binding.shart
        mudhafrd=binding.mudhaf
        mousufrd=binding.maousuf

      //  searchint = view.findViewById(R.id.searchint)
    //    val juz = view.findViewById<MaterialTextView>(R.id.tiJuz)
     //   val surahtv = view.findViewById<MaterialTextView>(R.id.tvSura)

        //   setToolbarFragment();
        setToolbarMenu()
        val utils = Utils(context)
        //  allAnaChapters = utils.getAllAnaChapters() as ArrayList<ChaptersAnaEntity>
        parts = utils.juz

        //  TypedArray imgs = context!!.getResources().obtainTypedArray(R.array.sura_imgs);
        val mLayoutManager = GridLayoutManager(activity, 2)
        // parentRecyclerView = view.findViewById(R.id.juzRecyclerView);
        parentRecyclerView = view.findViewById(R.id.wordByWordRecyclerView)
      //  val lastread = view.findViewById<MaterialButton>(R.id.lastread)
      //  val kahaf = view.findViewById<TextView>(R.id.kahaf)
       // val ayakursi = view.findViewById<TextView>(R.id.ayatkursi)
        val pref = Objects.requireNonNull(requireContext())
            .getSharedPreferences("lastread", Context.MODE_PRIVATE)
        lastreadchapterno = pref.getInt(Constant.SURAH_ID, 1)
        lastreadverseno = pref.getInt(Constant.AYAH_ID, 1)
        val sbss = StringBuilder()
        sbss.append("Last read").append(":").append("Surah").append(lastreadchapterno).append(" ")
            .append("Ayah").append(lastreadverseno)
       // lastread.text = sbss.toString()
        //lastread.setText("Last read" + ":" + "Surah:" + lastreadchapterno + " " + "Ayah:" + lastreadverseno);
   /*     juz.setOnClickListener { v: View? ->
            parentRecyclerView.layoutManager = mLayoutManager
            parentRecyclerView.setHasFixedSize(true)
            parentRecyclerView.layoutManager = mLayoutManager
            juzSurahDisplayAdapter = JuzSurahDisplayAdapter(context, parts)
            parentRecyclerView.adapter = juzSurahDisplayAdapter
        }
        surahtv.setOnClickListener { v: View? ->
            parentRecyclerView.layoutManager = mLayoutManager
            parentRecyclerView.setHasFixedSize(true)
            ParentAdapter = NewSurahDisplayAdapter(context, allAnaChapters)
            ParentAdapter.setUp(allAnaChapters)
            parentRecyclerView.adapter = ParentAdapter
        }
        kahaf.setText(R.string.linkkahaf)*/
        innard.setOnClickListener { v: View? ->
//
             innard.isChecked=true


        }

        kanard.setOnClickListener { v: View? ->

        }
        mousufrd.setOnClickListener { v: View? ->

        }
        val viewmodel: QuranVIewModel by viewModels()
        ParentAdapter = NewSurahDisplayAdapter(context)
        viewmodel.getAllChapters().observe(viewLifecycleOwner) {
            parentRecyclerView.layoutManager = mLayoutManager
            parentRecyclerView.setHasFixedSize(true)
            parentRecyclerView.layoutManager = mLayoutManager
            allAnaChapters = it as ArrayList<ChaptersAnaEntity>
            ParentAdapter.setUp(it)
            ParentAdapter.setmutable(it)
            parentRecyclerView.adapter = ParentAdapter
        }




        return view
    }

    private fun initnavagation(view: View) {
        bottomNavigationView = view.findViewById(R.id.bottomNavView)
        btnBottomSheet = view.findViewById(R.id.fab)
        btnBottomSheet.setOnClickListener { v: View? -> toggleBottomSheets() }
        bottomNavigationView.setOnItemReselectedListener { item: MenuItem ->
            if (item.itemId == R.id.surahnav) {
                val fragmentManager =
                    Objects.requireNonNull(requireActivity())
                        .supportFragmentManager
                val transaction =
                    fragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                val newCustomFragment =
                    newInstance()
                transaction.replace(R.id.frame_container_qurangrammar, newCustomFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            if (item.itemId == R.id.conjugationnav) {
                val conjugatorintent = Intent(activity, ConjugatorAct::class.java)
                startActivity(conjugatorintent)
            }
            if (item.itemId == R.id.dua) {
                val hisnulBottomACT = Intent(activity, HisnulBottomACT::class.java)
                startActivity(hisnulBottomACT)
            }
            if (item.itemId == R.id.names) {
                val settingint = Intent(activity, NamesGridImageAct::class.java)

                startActivity(settingint)
            }
            if (item.itemId == R.id.mushafview) {
                val settingints = Intent(activity, ShowMushafActivity::class.java)

                startActivity(settingints)
            }
        }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ParentAdapter.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val item: ChaptersAnaEntity = ParentAdapter.getItem(position) as ChaptersAnaEntity
                val selectedRadioButton: RadioButton =view.findViewById(phraserg.getCheckedRadioButtonId())
                val selected: String = selectedRadioButton.text.toString()

                val settingint = Intent(requireActivity(), PhrasesGrammarAct::class.java)
                when (selected) {
                    "Inna" -> settingint.putExtra(Constant.HARF, "inna")
                    "Kana" -> settingint.putExtra(Constant.HARF, "kana")
                    "Mudhaf" -> settingint.putExtra(Constant.HARF, "mudhaf")
                    "Shart" -> settingint.putExtra(Constant.HARF, "shart")
                    "Mausouf" -> settingint.putExtra(Constant.HARF, "mausuf")
                }

                settingint.putExtra(SURAH_ID, item.chapterid)

                settingint.putExtra(RUKUCOUNT, item.rukucount)
                settingint.putExtra(ISMAKKI, item.ismakki)
                settingint.putExtra(VERSESCOUNT, item.versescount)
                settingint.putExtra(SURAHNAME, item.abjadname)
                startActivity(settingint)
            }


        })

    }



    override fun onQueryTextSubmit(query: String): Boolean {
        ParentAdapter.filter.filter(query)
        //  Utils.LogDebug("Submitted: "+query);
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        //    Utils.LogDebug("Changed: "+newText);
        return false
    }

    companion object {
        fun newInstance(): PhrasesDisplayFrag {
            val fragment = PhrasesDisplayFrag()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}