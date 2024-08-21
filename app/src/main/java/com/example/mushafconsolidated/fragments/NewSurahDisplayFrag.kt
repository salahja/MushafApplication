package com.example.mushafconsolidated.fragments


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
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.Activity.ShowMushafActivity
import com.example.mushafconsolidated.Adaptersimport.JuzSurahDisplayAdapter
import com.example.mushafconsolidated.Adaptersimport.NewSurahDisplayAdapter
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.drawable.custom_search_box
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.intrfaceimport.PassdataInterface
import com.example.mushafconsolidated.model.Juz
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.utility.QuranGrammarApplication
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.textview.MaterialTextView
import database.NamesGridImageAct
import org.sj.conjugator.activity.ConjugatorAct
import sj.hisnul.activity.HisnulBottomACT
import java.util.Objects


/**
 * A simple [Fragment] subclass.
 * Use the [NewSurahDisplayFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewSurahDisplayFrag : Fragment(), SearchView.OnQueryTextListener {
    //   implements FragmentCommunicator {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //   private static final String ARG_PARAM1 = "param1";
    //   private static final String ARG_PARAM2 = "param2";
    private lateinit var parentRecyclerView: RecyclerView

    //   private RecyclerView.Adapter ParentAdapter;
    private lateinit var ParentAdapter: NewSurahDisplayAdapter
    private lateinit var juzSurahDisplayAdapter: JuzSurahDisplayAdapter

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

    /*


     */
    /*
        override fun onAttach(context: Context) {
            super.onAttach(context)
            datapasser = context as PassdataInterface
        }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    /*    override fun onDestroy() {
            super.onDestroy()
            val fragment =
                Objects.requireNonNull(requireActivity()).supportFragmentManager.findFragmentByTag("group")
            if (fragment != null) {
                Objects.requireNonNull(requireActivity()).supportFragmentManager.beginTransaction()
                    .remove(fragment).commit()
            }
        }*/
    /*

        override fun onDetach() {
            super.onDetach()
            val fragment =
                Objects.requireNonNull(requireActivity()).supportFragmentManager.findFragmentByTag("group")
            if (fragment != null) {
                Objects.requireNonNull(requireActivity()).supportFragmentManager.beginTransaction()
                    .remove(fragment).commit()
            }
        }
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        container?.removeAllViews()

        //     View view = inflater.inflate(R.layout.list_surah_juz, container, false);
        val view = inflater.inflate(R.layout.list_surah_juz, container, false)
        initnavagation(view)
        searchint = view.findViewById(R.id.searchint)
        val juz = view.findViewById<MaterialTextView>(R.id.tiJuz)
        val surahtv = view.findViewById<MaterialTextView>(R.id.tvSura)

        //   setToolbarFragment();
        setToolbarMenu()
        val utils = Utils(context)
        //  allAnaChapters = utils.getAllAnaChapters() as ArrayList<ChaptersAnaEntity>
        parts = utils.juz

        //  TypedArray imgs = context!!.getResources().obtainTypedArray(R.array.sura_imgs);
        val mLayoutManager = GridLayoutManager(activity, 2)
        // parentRecyclerView = view.findViewById(R.id.juzRecyclerView);
        parentRecyclerView = view.findViewById(R.id.wordByWordRecyclerView)
        val lastread = view.findViewById<MaterialButton>(R.id.lastread)
        val kahaf = view.findViewById<TextView>(R.id.kahaf)
        val ayakursi = view.findViewById<TextView>(R.id.ayatkursi)
        val pref = Objects.requireNonNull(requireContext())
            .getSharedPreferences("lastread", Context.MODE_PRIVATE)
        lastreadchapterno = pref.getInt(Constant.SURAH_ID, 1)
        lastreadverseno = pref.getInt(Constant.AYAH_ID, 1)
        val sbss = StringBuilder()
        sbss.append("Last read").append(":").append("Surah").append(lastreadchapterno).append(" ")
            .append("Ayah").append(lastreadverseno)
        lastread.text = sbss.toString()
        //lastread.setText("Last read" + ":" + "Surah:" + lastreadchapterno + " " + "Ayah:" + lastreadverseno);
        juz.setOnClickListener { v: View? ->
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
        kahaf.setText(R.string.linkkahaf)
        lastread.setOnClickListener { v: View? ->
//
            val intent =
                Intent(QuranGrammarApplication.context!!, QuranGrammarAct::class.java)
            //  Intent intent = new Intent(DarkThemeApplication.context!!, ReadingSurahPartActivity.class);
            intent.putExtra(SURAH_ID, lastreadchapterno)
            intent.putExtra("chapterorpart", true)
            intent.putExtra("partname", allAnaChapters[lastreadchapterno - 1].abjadname)
            intent.putExtra(Constant.AYAH_ID, lastreadverseno)
            intent.putExtra(Constant.AYAHNUMBER, lastreadverseno)
            startActivity(intent)


        }
        kahaf.setOnClickListener { v: View? ->
            val intent = Intent(
                QuranGrammarApplication.context!!,
                QuranGrammarAct::class.java
            )
            //  Intent intent = new Intent(DarkThemeApplication.context!!, ReadingSurahPartActivity.class);
            intent.putExtra("chapter", 18)
            intent.putExtra("chapterorpart", true)
            intent.putExtra("partname", allAnaChapters[18].abjadname)
            intent.putExtra("verseno", 1)
            intent.putExtra(Constant.AYAH_ID, 1)
            startActivity(intent)
        }
        ayakursi.setOnClickListener { v: View? ->
            val intent = Intent(
                QuranGrammarApplication.context!!,
                QuranGrammarAct::class.java
            )
            //  Intent intent = new Intent(DarkThemeApplication.context!!, ReadingSurahPartActivity.class);
            intent.putExtra("chapter", 2)
            intent.putExtra("chapterorpart", true)
            intent.putExtra("partname", allAnaChapters[2].abjadname)
            intent.putExtra("verseno", 255)
            intent.putExtra(Constant.AYAH_ID, 255)
            startActivity(intent)
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

                val settingint = Intent(requireActivity(), QuranGrammarAct::class.java)
                settingint.putExtra(SURAH_ID, item.chapterid)

                settingint.putExtra(RUKUCOUNT, item.rukucount)
                settingint.putExtra(ISMAKKI, item.ismakki)
                settingint.putExtra(VERSESCOUNT, item.versescount)
                settingint.putExtra(SURAHNAME, item.abjadname)
                startActivity(settingint)
            }


        })

        /*

  private void passData(int chapter_no,String partname,int versescount,int rukucount) {
    getDatapasser().ondatarecevied(chapter_no,true,partname);
  }

 */
    }


    /*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)
          ParentAdapter.SetOnItemClickListener(object : OnItemClickListener {
              override fun onItemClick(v: View?, position: Int) {
                  val item: ChaptersAnaEntity = ParentAdapter.getItem(position) as ChaptersAnaEntity
                  passData(
                      item.chapterid,
                      item.abjadname,
                      item.versescount,
                      item.rukucount,
                      item.ismakki
                          )
              }

              private fun passData(
                  chapterid: Int,
                  abjadname: String,
                  versescount: Int,
                  rukucount: Int,
                  ismakki: Int,
                                  ) {
                  datapasser.ondatarecevied(chapterid, abjadname, versescount, rukucount, ismakki)
              }
          })

          *//*

  private void passData(int chapter_no,String partname,int versescount,int rukucount) {
    getDatapasser().ondatarecevied(chapter_no,true,partname);
  }

 *//*
    }  */
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
        fun newInstance(): NewSurahDisplayFrag {
            val fragment = NewSurahDisplayFrag()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}