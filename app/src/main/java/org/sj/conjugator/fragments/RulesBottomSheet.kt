package org.sj.conjugator.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.VERBMOOD
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import database.VerbDatabaseUtils
import database.entity.kov
import database.verbrepo.VerbModel
import org.sj.conjugator.adapter.rulesbottomsheetadapter
import org.sj.conjugator.interfaces.OnItemClickListener

class RulesBottomSheet : BottomSheetDialogFragment() {
    var rulesadapter: rulesbottomsheetadapter? = null
    private var verbtype: Array<String>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mujarrad_bottom_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recview)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
        val bundle = this.arguments
        verbtype = bundle!!.getStringArray(ARG_OPTIONS_DATA)
        val mLayoutManager = GridLayoutManager(activity, 2)
        val db = VerbDatabaseUtils(QuranGrammarApplication.context!!)
     //   kovArrayList = db.kov as ArrayList<kov>?
        rulesadapter=rulesbottomsheetadapter()
        val viewmodel: VerbModel by viewModels()
        viewmodel.getKov().observe(this, {
            rulesadapter!!.setmutable(it!! as ArrayList<kov>)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = mLayoutManager
            recyclerView.adapter = rulesadapter

        })
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rulesadapter!!.SetOnItemClickListener(object : OnItemClickListener {


            override fun onItemClick(v: View?, position: Int) {
                dismiss()
            //    val entity = kovArrayList!![position]
                val entity = rulesadapter!!.getItem(position) as kov
                val dataBundle = Bundle()
                dataBundle.putString(QURAN_VERB_WAZAN, entity.kov)
                dataBundle.putString(VERBMOOD, "Indicative")
                if (verbtype!![0] == "mujarrad") {
                    val rulesFragment = RulesMujarradVerbList(context)
                    rulesFragment.arguments = dataBundle
                    val transactions = requireActivity().supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transactions.replace(R.id.frame_container, rulesFragment).addToBackStack("mujarrad")
                    transactions.commit()
                } else {
                    val rulesFragment = RulesMazeedVerbList(context)
                    rulesFragment.arguments = dataBundle
                    val transactions = requireActivity().supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transactions.replace(R.id.frame_container, rulesFragment).addToBackStack("mujarrad")
                    transactions.commit()
                }
            }
        })
    }

    companion object {
        const val TAG = "bottom"
        private const val ARG_OPTIONS_DATA = "options_data"
        fun newInstance(data: Array<String?>?): RulesBottomSheet {
            val fragment = RulesBottomSheet()
            val args = Bundle()
            args.putStringArray(ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}