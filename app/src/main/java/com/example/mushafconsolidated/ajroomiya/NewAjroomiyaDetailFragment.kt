package com.example.mushafconsolidated.ajroomiya

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnDragListener
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.ajroomiya.placeholder.AjroomiyaRulecontents
import com.example.mushafconsolidated.databinding.NewFragmentAjroomiyaListBinding
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * A fragment representing a single GrammarRule detail screen.

 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class NewAjroomiyaDetailFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    /**
     * The placeholder content this fragment is presenting.
     */
    private var mItem: GrammarRules? = null
    private var mToolbarLayout: CollapsingToolbarLayout? = null
    private lateinit var mTextView: WebView
    private val dragListener = OnDragListener { v: View?, event: DragEvent ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem = event.clipData.getItemAt(0)
            mItem = AjroomiyaRulecontents.ITEM_MAP[clipDataItem.text.toString()]
            updateContent()
        }
        true
    }
    private var binding: NewFragmentAjroomiyaListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(ARG_ITEM_ID)) {
            // Load the placeholder content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = AjroomiyaRulecontents.ITEM_MAP[requireArguments().getString(ARG_ITEM_ID)]
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView: View =
            inflater.inflate(R.layout.new_fragment_ajroomiya_detail, container, false)
        //   binding = FragmentGrammarruleDetailBinding.inflate(inflater, container, false);
        //  View rootView = binding.getRoot();
        mToolbarLayout = rootView.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        mTextView = rootView.findViewById<WebView>(R.id.ajroomiya_detail)
        val webSettings = mTextView.settings
        webSettings.javaScriptEnabled = true
        //  mTextView.setInitialScale(1);
        //   mTextView.getSettings().setLoadWithOverviewMode(true);
        //    mTextView.getSettings().setUseWideViewPort(true);

        //      mTextView.getSettings().setBuiltInZoomControls(true);
        // Show the placeholder content as text in a TextView & in the toolbar if available.
        updateContent()
        rootView.setOnDragListener(dragListener)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun updateContent() {
        if (mItem != null) {
            mTextView.loadDataWithBaseURL(null, mItem!!.detailsrules, "text/html", "utf-8", null)
            //   mTextView.setText(mItem.getDetailsrules());
            if (mToolbarLayout != null) {
                mToolbarLayout!!.title = mItem!!.worddetails
            }
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }


}