package org.sj.conjugator.fragments

import android.os.Bundle
import android.view.LayoutInflater
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
import org.sj.conjugator.adapter.VerbSarfKabeerAdapter
import org.sj.conjugator.utilities.GatherAll
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class FragmentVerb : Fragment() {
    var recyclerView: RecyclerView? = null
    private var isAugmented = false
    private var isUnAugmented = false
    private var skabeer = ArrayList<ArrayList<*>>()
    private var augmentedFormula: String? = null
    private lateinit var unaugmentedFormula: String
    private var verbroot: String? = null
    private var verbmood: String? = null
    fun newInstance(): FragmentVerb {
        val f = FragmentVerb()
        val dataBundle = requireArguments()
        dataBundle.getString(QURAN_VERB_ROOT)
        dataBundle.getString(QURAN_VERB_WAZAN) //verb formula depnding upon the verbtype mujjarad or mazeed
        dataBundle.getString(VERBMOOD)
        dataBundle.getString(VERBTYPE)
        f.arguments = dataBundle
        return f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        //   callButton.setVisibility(View.VISIBLE);
        callButton.setOnClickListener {
            val fm = activity
                ?.supportFragmentManager
            if (fm != null) {
                fm.popBackStack()
            }
        }
        assert(dataBundle != null)
        if (dataBundle!!.getString(VERBTYPE) == "mujarrad") {
            isUnAugmented = true
            unaugmentedFormula = dataBundle.getString(QURAN_VERB_WAZAN)!!
        } else {
            augmentedFormula = dataBundle.getString(QURAN_VERB_WAZAN)
            isAugmented = true
        }
        verbroot = dataBundle.getString(QURAN_VERB_ROOT)
        verbmood = dataBundle.getString(VERBMOOD)
        recyclerView = view.findViewById(R.id.sarfrecview)
        skabeer = setUparrays(view)
        return view
    }

    private fun setUparrays(view: View): ArrayList<ArrayList<*>> {
        if (isUnAugmented) {
            ninitThulathiAdapter()
        } else {
            val indictive: ArrayList<ArrayList<*>> =
                GatherAll.instance.getMazeedListing(verbmood, verbroot, augmentedFormula)
            val sk: VerbSarfKabeerAdapter
            if (indictive.isNotEmpty()) {
               // indictive.removeAt(0)
                sk = VerbSarfKabeerAdapter(indictive, requireContext())
                recyclerView!!.adapter = sk
                recyclerView!!.setHasFixedSize(true)
                recyclerView!!.layoutManager = LinearLayoutManager(context)
            }
        }
        recyclerView = view.findViewById(R.id.sarfrecview)
        return skabeer
    }

    private fun ninitThulathiAdapter() {
        val mujarradListing: ArrayList<ArrayList<*>> =
            GatherAll.instance.getMujarradListing(verbmood, verbroot, unaugmentedFormula)
      //  mujarradListing.removeAt(0)
        val ska = VerbSarfKabeerAdapter(mujarradListing, requireContext())
        recyclerView!!.adapter = ska
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.sarfrecview)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
    }

    companion object {
    }
}