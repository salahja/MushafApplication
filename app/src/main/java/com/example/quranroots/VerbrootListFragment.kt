import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.OnUnhandledKeyEventListenerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.WORDDETAILS
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.databinding.FragmentVerbrootListBinding
import com.example.mushafconsolidated.databinding.VerbrootListContentBinding
import com.example.quranroots.ArabicrootDetailFragment
import com.example.quranroots.placeholder.PlaceholderContent
import com.example.quranroots.placeholder.VerbHolderContent

class VerbrootListFragment : Fragment() {
    private var iswordorverb: String? = null

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
    private var binding: FragmentVerbrootListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerbrootListBinding.inflate(inflater, container, false)
        val bundle = this.arguments
        iswordorverb = bundle!!.getString(WORDDETAILS)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)
        val recyclerView = binding?.arabicrootList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer =
            view.findViewById<View>(R.id.verbroot_detail_nav_container)
        requireArguments().getString(WORDDETAILS)
        if (recyclerView != null) {
            setupRecyclerView(recyclerView, itemDetailFragmentContainer)
        }
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View
    ) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            VerbHolderContent.ITEMS,
            itemDetailFragmentContainer, iswordorverb
        )
    }

    private class SimpleItemRecyclerViewAdapter(
        private val mValues: List<VerbHolderContent.ItemHolder>,
        private val mItemDetailFragmentContainer: View?, private val iswordorverb: String?
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = VerbrootListContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //  holder.mIdView.setText(mValues.get(position).id);
            //   holder.mContentView.setText(mValues.get(position).content);
            //   StringBuilder sb=new StringBuilder();
            //   sb.append(mValues.get(position).id).append("-").append(mValues.get(position).content);
            holder.mIdView.text = mValues[position].id
            holder.mContentView.text = mValues[position].content
            //  holder.mIdView.setText(mValues.get(position).content);
            holder.itemView.tag = mValues[position]
            holder.itemView.setOnClickListener { itemView: View ->
                val item =
                    itemView.tag as VerbHolderContent.ItemHolder
                val arguments = Bundle()
                arguments.putString(ArabicrootDetailFragment.ARG_ITEM_ID, item.id)
                arguments.putString(WORDDETAILS, iswordorverb)
                if (mItemDetailFragmentContainer != null) {
                    findNavController(mItemDetailFragmentContainer)
                        .navigate(R.id.fragment_verbroot_detail, arguments)
                } else {
                    findNavController(itemView).navigate(R.id.show_verbroot_detail, arguments)
                }
            }
            /*
 * Context click listener to handle Right click events
 * from mice and trackpad input to provide a more native
 * experience on larger screen devices
 */holder.itemView.setOnContextClickListener { v: View? ->
                val item =
                    holder.itemView.tag as PlaceholderContent.PlaceholderItem
                Toast.makeText(
                    holder.itemView.context,
                    "Context click of item " + item.id,
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            holder.itemView.setOnLongClickListener { v: View ->
                // Setting the item id as the clip data so that the drop target is able to
                // identify the id of the content
                val clipItem = ClipData.Item(mValues[position].id)
                val dragData = ClipData(
                    (v.tag as PlaceholderContent.PlaceholderItem).content,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    clipItem
                )
                v.startDragAndDrop(
                    dragData,
                    DragShadowBuilder(v),
                    null,
                    0
                )
                true
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(binding: VerbrootListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val mIdView: TextView
            val mContentView: TextView

            init {
                mIdView = binding.idText
                mContentView = binding.content
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(wordorverb: String?): VerbrootListFragment {
            val fragment = VerbrootListFragment()
            val args = Bundle()
            args.putString(WORDDETAILS, wordorverb)
            fragment.arguments = args
            return fragment
        }
    }
}