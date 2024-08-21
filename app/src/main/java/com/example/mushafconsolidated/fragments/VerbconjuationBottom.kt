package com.example.mushafconsolidated.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.layout

import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * ThemeListPrefrence.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class VerbconjuationBottom constructor() : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: VerbconjuationBottom.FontQuranAdapter? = null
    lateinit var frameLayout: RelativeLayout
    var mLocalityList: List<String>? = ArrayList()
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.quran_list_dialog, container, false)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLocalityList = requireArguments().getStringArrayList("list")
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        val details: ArrayList<String> = ArrayList()
        mLocalityList = requireArguments().getStringArrayList("list")
        fontQuranAdapter = FontQuranAdapter()
        recyclerView.adapter = fontQuranAdapter
        fontQuranAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            public override fun onItemClick(v: View?, position: Int) {
                val checkedRadioButtonId: Int = radioGroup!!.checkedRadioButtonId
            }
        })
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            layout.conjugationbottom, parent, false
        )
    ), View.OnClickListener {
        var hua: TextView
        var huma: TextView
        var hum: TextView
        var hia: TextView
        var humaf: TextView
        var hunna: TextView
        var anta: TextView
        var antumam: TextView
        var antum: TextView
        var anti: TextView
        var antumaf: TextView
        var antunna: TextView
        var ana: TextView
        var nahnu: TextView

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            hua = itemView.findViewById(R.id.hua)
            huma = itemView.findViewById(R.id.huma)
            hum = itemView.findViewById(R.id.hum)
            hia = itemView.findViewById(R.id.hia)
            humaf = itemView.findViewById(R.id.humaf)
            hunna = itemView.findViewById(R.id.hunna)
            anta = itemView.findViewById(R.id.anta)
            antumam = itemView.findViewById(R.id.antuma)
            antum = itemView.findViewById(R.id.antum)
            anti = itemView.findViewById(R.id.anti)
            antumaf = itemView.findViewById(R.id.antumaf)
            antunna = itemView.findViewById(R.id.antunna)
            ana = itemView.findViewById(R.id.ana)
            nahnu = itemView.findViewById(R.id.nahnu)
            frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    private inner class FontQuranAdapter constructor() :
        RecyclerView.Adapter<VerbconjuationBottom.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): VerbconjuationBottom.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        public override fun onBindViewHolder(
            holder: VerbconjuationBottom.ViewHolder,
            position: Int
        ) {
            val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val theme: String? = PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("themepref", "dark")
            holder.hua.text = mLocalityList!!.get(0)
            holder.huma.text = mLocalityList!!.get(1)
            holder.hum.text = mLocalityList!!.get(2)
            holder.hia.text = mLocalityList!!.get(3)
            holder.humaf.text = mLocalityList!!.get(4)
            holder.hunna.text = mLocalityList!!.get(5)
            holder.anta.text = mLocalityList!!.get(6)
            holder.antumam.text = mLocalityList!!.get(7)
            holder.antum.text = mLocalityList!!.get(8)
            holder.anti.text = mLocalityList!!.get(9)
            holder.antumaf.text = mLocalityList!!.get(7)
            holder.antunna.text = mLocalityList!!.get(10)
            holder.ana.text = mLocalityList!!.get(11)
            holder.nahnu.text = mLocalityList!!.get(12)
        }

        public override fun getItemCount(): Int {
            return 1
        }

        fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
            this.mItemClickListener = mItemClickListener
        }
    }

    companion object {
        const val TAG: String = "opton"
        private val localityList: List<*>? = null

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(list: ArrayList<*>?): VerbconjuationBottom {
            val fragment: VerbconjuationBottom = VerbconjuationBottom()
            val bundle: Bundle = Bundle()
            bundle.putParcelableArrayList("list", list as java.util.ArrayList<out Parcelable>?)
            fragment.arguments = bundle
            return fragment
        }
    }
}