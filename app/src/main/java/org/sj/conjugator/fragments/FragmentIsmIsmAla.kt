package org.sj.conjugator.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.MUJARRADVERBTAG
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.R
import org.sj.conjugator.adapter.IsmAlaSarfKabeerAdapter
import org.sj.conjugator.utilities.GatherAll
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class FragmentIsmIsmAla : Fragment() {
    private var isAugmented = false
    private var isUnAugmented = false
    var recyclerView: RecyclerView? = null
    private var unaugmentedFormula: String? = null
    private var verbroot: String? = null
    private var skabeer = ArrayList<ArrayList<*>>()
    fun newInstance(): FragmentIsmIsmAla {
        val f = FragmentIsmIsmAla()
        val dataBundle = requireArguments()
        if (null != dataBundle) {
            dataBundle.getString(QURAN_VERB_ROOT)
            dataBundle.getString(QURAN_VERB_WAZAN) //verb formula depnding upon the verbtype mujjarad or mazeed
            dataBundle.getString(VERBMOOD)
            dataBundle.getString(VERBTYPE)
        }
        f.arguments = dataBundle
        return f
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Log.d(TAG, "ONCREATE OPTION MENU verse ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.sarfkabeerheader, container, false)
        val callButton = view.findViewById<FloatingTextButton>(R.id.action_buttons)
        val dataBundle = arguments
        if (dataBundle != null) {
            val callingfragment = dataBundle.getString(MUJARRADVERBTAG)
            if (callingfragment != null) {
                if (callingfragment == "tverblist") {
                    callButton.visibility = View.VISIBLE
                } else {
                    callButton.visibility = View.GONE
                }
            } else {
                callButton.visibility = View.GONE
            }
        }
        if (dataBundle!!.getString(VERBTYPE) == "mujarrad") {
            isUnAugmented = true
            unaugmentedFormula = dataBundle.getString(QURAN_VERB_WAZAN)
        } else {
            val augmentedFormula = dataBundle.getString(QURAN_VERB_WAZAN)
            isAugmented = true
        }
        verbroot = dataBundle.getString(QURAN_VERB_ROOT)
        recyclerView = view.findViewById(R.id.sarfrecview)
        skabeer = setUparrays(view)
        callButton.setOnClickListener {
            val fm = activity
                ?.supportFragmentManager
            if (fm != null) {
                fm.popBackStack()
            }
        }
        return view
    }

    private fun setUparrays(view: View): ArrayList<ArrayList<*>> {
        if (isUnAugmented) {
            ninitThulathiAdapter()
        } else {
            initMazeedAdapterNew()
        }
        recyclerView = view.findViewById(R.id.sarfrecview)
        return skabeer
    }

    private fun initMazeedAdapterNew() {}
    private fun ninitThulathiAdapter() {
        //   OldThulathi();
        val mujarradListing: ArrayList<ArrayList<*>> =
            GatherAll.instance.getMujarradIsmAla(verbroot, unaugmentedFormula)
        if (mujarradListing.isNotEmpty()) {
            val ska = IsmAlaSarfKabeerAdapter(mujarradListing, requireContext())
            recyclerView!!.adapter = ska
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        private const val TAG = "PermissionDemo"
    }
}