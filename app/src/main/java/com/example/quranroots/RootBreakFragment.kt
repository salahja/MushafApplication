package com.example.quranroots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.QURAN_VERB_ROOT
import com.example.mushafconsolidated.R

import com.example.mushafconsolidated.Utils

/**
 * A fragment representing a list of Items.
 */
class RootBreakFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    private var root: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            root = requireArguments().getString(QURAN_VERB_ROOT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_root_breakup, container, false)
        val utils = Utils()
        utils.getRootDictionary(root!!)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rootbreakup)
        // Set the adapter
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }

    companion object {
        // TODO: Customize parameter initialization
        fun newInstance(rootword: String?): RootBreakFragment {
            val fragment = RootBreakFragment()
            val args = Bundle()
            args.putString(QURAN_VERB_ROOT, rootword)
            fragment.arguments = args
            return fragment
        }
    }
}