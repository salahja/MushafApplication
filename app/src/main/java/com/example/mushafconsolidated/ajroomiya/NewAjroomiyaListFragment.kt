package com.example.mushafconsolidated.ajroomiya

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.OnUnhandledKeyEventListenerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.ajroomiya.placeholder.AjroomiyaRulecontents
import com.example.mushafconsolidated.databinding.GrammarruleListContentBinding
import com.example.mushafconsolidated.databinding.NewFragmentAjroomiyaListBinding
import com.example.utility.QuranGrammarApplication
import com.example.utility.QuranGrammarApplication.Companion.context

/**
 * A fragment representing a list of GrammarRules. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,

 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class NewAjroomiyaListFragment : Fragment() {
    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    private var unhandledKeyEventListenerCompat =
        OnUnhandledKeyEventListenerCompat { v: View, event: KeyEvent ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                return@OnUnhandledKeyEventListenerCompat true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                return@OnUnhandledKeyEventListenerCompat true
            }
            false
        }
    private var binding: NewFragmentAjroomiyaListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = NewFragmentAjroomiyaListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)
        val recyclerView: RecyclerView = binding!!.ajroomiyaList
        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        //  View itemDetailFragmentContainer = view.findViewById(R.id.grammarrule_detail_nav_container);


        /* Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView: View ->
            val item = itemView.tag as GrammarRules
            val bundle1 = Bundle()
            bundle1.putString(NewAjroomiyaDetailFragment.ARG_ITEM_ID, item.id.toString())
            //     if (itemDetailFragmentContainer != null) {
            //      Navigation.findNavController(itemDetailFragmentContainer)
            //            .navigate(R.id.fragment_grammarrule_detail, arguments);
            //   } else {
            //  findNavController(itemView).navigate(R.id.ajroomiya_detail_fragment, arguments)


            val fragvsi = NewAjroomiyaDetailFragment()
            fragvsi.arguments = bundle1
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.replace(R.id.frame_container, fragvsi, "items")
            //     transaction.addToBackStack("setting");
            transaction.addToBackStack("items")
            transaction.commit()


        }

        /*
         * Context click listener to handle Right click events
         * from mice and trackpad input to provide a more native
         * experience on larger screen devices
         */
        val onContextClickListener = View.OnContextClickListener { itemView: View ->
            val item =
                itemView.tag as AjroomiyaRulecontents.PlaceholderItem
            Toast.makeText(
                itemView.context,
                "Context click of item " + item.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
        setupRecyclerView(recyclerView, onClickListener, onContextClickListener)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        onContextClickListener: View.OnContextClickListener,
    ) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            AjroomiyaRulecontents.ITEMS,
            onClickListener,
            onContextClickListener
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private class SimpleItemRecyclerViewAdapter(
        private val mValues: List<GrammarRules?>,
        private val mOnClickListener: View.OnClickListener,
        private val mOnContextClickListener: View.OnContextClickListener,
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding: GrammarruleListContentBinding = GrammarruleListContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val id = mValues[position]!!.id
            val s = id.toString()
            val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
            val theme = sharedPreferences.getString("themepref", "dark")
            // holder.mIdView.text = s
            //   holder.mContentView.setText(mValues.get(position).getHarf());
            val str = mValues[position]!!.worddetails!!.replace("\n", " ")
            holder.mContentView.text = str
            //    holder.mContentView.setText(HtmlCompat.fromHtml(mValues.get(position).getWorddetails() ,0));
            holder.itemView.tag = mValues[position]
            holder.itemView.setOnClickListener(mOnClickListener)
            holder.itemView.setOnContextClickListener(mOnContextClickListener)
            if (theme == "green") {
                holder.cardview.setCardBackgroundColor(context!!.resources.getColor(R.color.mdgreen_theme_dark_onPrimary))
            } else if (theme == "blue") {
                holder.cardview.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.bg_surface_dark_blue
                    )
                )
            }

        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(binding: GrammarruleListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val cardview: CardView

            //  val mIdView: TextView
            val mContentView: TextView
            //     val cardView: CardView

            init {
                // mIdView = binding.idText
                mContentView = binding.content
                cardview = binding.cardview

            }
        }
    }

    companion object {
        fun newInstance(): NewAjroomiyaListFragment {
            return NewAjroomiyaListFragment()

        }

    }
}