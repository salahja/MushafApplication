package sj.hisnul.fragments


import android.app.ActionBar
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import sj.hisnul.adapter.SelectedDuaViewAdapter
import sj.hisnul.entity.hduadetailsEnt
import sj.hisnul.entity.hduanamesEnt
import sj.hisnul.newepository.NewDuaModel

class HDuaNamesfrag : Fragment() {
    private val subheaders = ArrayList<String>()
    private val duacoll: ArrayList<ArrayList<hduadetailsEnt>> = ArrayList()
    private var sadapter: SelectedDuaViewAdapter? = null

    //called by allduarag and  catwofrag retrival by the chaptername in hdunames
    lateinit var recyclerView: RecyclerView
    private var name: String? = null
    private var fromcatwo = false
    private var chap_id = 0
    private lateinit var coordinatorLayout: CoordinatorLayout
    val viewmodel: NewDuaModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments != null) {
            name = requireArguments().getString("name")
            chap_id = requireArguments().getInt("chap_id")
            fromcatwo = requireArguments().getBoolean("cattwp")
        }
       // val utils = Utils(activity)
        if (chap_id != -1) {
            viewmodel.Duadetailsbychapter(chap_id).observe(this) { it ->
                // val dd: ArrayList<hduanames> = utils.getdualistbychapter(chap_id) as ArrayList<hduanames>
                for (hduanames in it) {
                    viewmodel.DuaItembyId(hduanames.ID).observe(this) {
                        val duaItems: ArrayList<hduadetailsEnt> = it as ArrayList<hduadetailsEnt>
                        duacoll.add(duaItems)
                        subheaders.add(hduanames.duaname)
                        sadapter = SelectedDuaViewAdapter(duacoll, subheaders)
                        recyclerView.adapter = sadapter

                    }


                }


            }

        } else {
            Toast.makeText(activity, "Chapter Id not Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        val view = arrayOf<View>(inflater.inflate(R.layout.dunamefragview, container, false))
        setHasOptionsMenu(true)
        val toolbar: MaterialToolbar = view[0].findViewById(R.id.toolbarmain)
        val actionBa: ActionBar? = (requireActivity() as AppCompatActivity).actionBar
        coordinatorLayout = view[0].findViewById<View>(R.id.coordinatorLayout) as CoordinatorLayout
        val shared: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)

        actionBa?.setDisplayHomeAsUpEnabled(true)
        recyclerView = view[0].findViewById(R.id.dunamerec)
      //  val utils = Utils(context)
        toolbar.title = name
        toolbar.inflateMenu(R.menu.menu_bookmark)
        toolbar.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.bookmark -> {
                    Toast.makeText(context, "First book item", Toast.LENGTH_SHORT).show()
                    var dunamesbyid: java.util.ArrayList<hduanamesEnt> = ArrayList()

                    viewmodel.Duadetailsbychapter(chap_id).observe(viewLifecycleOwner) {
                        dunamesbyid = it as java.util.ArrayList<hduanamesEnt>
                        val gookstat = dunamesbyid[0].fav
                        if (gookstat == 0) {
                            viewmodel.update(1, chap_id)
                        } else {
                            viewmodel.update(0, chap_id)
                        }
                    }
//sadapter.duadetailsitems
                    //sadapter.duadetailsitems
                    //  val up = utils.updateFav(1, chap_id)!!
                    //   val upd = utils.updateFav(0, chap_id)!!
                    val snackbar =
                        Snackbar.make(coordinatorLayout, "BookMarked", Snackbar.LENGTH_LONG)
                    view[0] = snackbar.view
                    val params = view[0].layoutParams as CoordinatorLayout.LayoutParams
                    params.gravity = Gravity.TOP
                    view[0].layoutParams = params
                    snackbar.show()
                    //  return true
                }

                R.id.backup -> {
                    val count = requireActivity().supportFragmentManager.backStackEntryCount
                    (activity as AppCompatActivity?)!!.supportActionBar!!.show()
                    if (count == 0) {
                        val supportActionBar = (activity as AppCompatActivity?)!!.supportActionBar
                        //additional code
                    } else {
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                    //  return true
                }

                R.id.action_share -> {
                    Toast.makeText(context, "First share item", Toast.LENGTH_SHORT).show()
                    //       setuptoolbar();
                    //       setuptoolbar();
                    //  return true
                }
            }
            Toast.makeText(activity, "tool", Toast.LENGTH_SHORT).show()
            false
        }
        //AconSarfSagheerAdapter sk=new AconSarfSagheerAdapter(ar, MainActivity.this);
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        //  recyclerView.setLayoutManager(new LinearLayoutManager(context!!));
        return view[0]
    }

    companion object {
        fun newInstance(): HDuaNamesfrag {
            return HDuaNamesfrag()
        }
    }
}