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
import com.example.mushafconsolidated.databinding.ArabicrootListContentBinding
import com.example.mushafconsolidated.databinding.FragmentArabicrootListBinding
import com.example.quranroots.ArabicrootDetailFragment
import com.example.quranroots.placeholder.PlaceholderContent


/**
 * A fragment representing a list of arabicrootitems. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a [ArabicrootDetailFragment] representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ArabicrootListFragment : Fragment() {
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
    private var binding: FragmentArabicrootListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArabicrootListBinding.inflate(inflater, container, false)
        val bundle = this.arguments
        iswordorverb = bundle!!.getString(WORDDETAILS)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)
        val recyclerView = binding!!.arabicrootList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer =
            view.findViewById<View>(R.id.arabicroot_detail_nav_container)
        requireArguments().getString(WORDDETAILS)
        setupRecyclerView(recyclerView, itemDetailFragmentContainer)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View
    ) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            PlaceholderContent.ITEMS,
            itemDetailFragmentContainer, iswordorverb
        )
    }

    private class SimpleItemRecyclerViewAdapter(
        private val mValues: List<PlaceholderContent.PlaceholderItem>,
        private val mItemDetailFragmentContainer: View?, private val iswordorverb: String?
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ArabicrootListContentBinding.inflate(
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
                    itemView.tag as PlaceholderContent.PlaceholderItem
                val arguments = Bundle()
                arguments.putString(ArabicrootDetailFragment.ARG_ITEM_ID, item.id)
                arguments.putString(WORDDETAILS, iswordorverb)
                if (mItemDetailFragmentContainer != null) {
                    findNavController(mItemDetailFragmentContainer)
                        .navigate(R.id.fragment_arabicroot_detail, arguments)
                } else {
                    findNavController(itemView).navigate(R.id.show_arabicroot_detail, arguments)
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

        inner class ViewHolder(binding: ArabicrootListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val mIdView: TextView
            val mContentView: TextView

            init {
                mIdView = binding.idText
                mContentView = binding.content
            }
        }
    }

    companion object {
        fun newInstance(wordorverb: String?): ArabicrootListFragment {
            val fragment = ArabicrootListFragment()
            val args = Bundle()
            args.putString(WORDDETAILS, wordorverb)
            fragment.arguments = args
            return fragment
        }
    }
}