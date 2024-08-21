package com.example.mushafconsolidated.fragments

import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.layout

import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FontQuranListDialogFragment constructor() : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null
    private var fontQuranAdapter: FontQuranListDialogFragment.FontQuranAdapter? = null
    lateinit var radioGroup: RadioGroup
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quran_list_dialog, container, false)
        //   return inflater.inflate(R.layout.Arabic_Font_Selectionselection, container, false);
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        radioGroup = view.findViewById(R.id.rdgroup)
        val details: ArrayList<String> = ArrayList()
        val sample: String = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
        fontQuranAdapter = FontQuranAdapter(sample)
        recyclerView.adapter = fontQuranAdapter
        fontQuranAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            public override fun onItemClick(v: View?, position: Int) {
                var checkedRadioButtonId: Int = radioGroup.checkedRadioButtonId
                //Toast.makeText(context!!, "", Toast.LENGTH_SHORT).show();
            }
        })
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            layout.quranfonts_bottomsheet, parent, false
        )
    ), View.OnClickListener {
        val text: TextView
        val qalamtext: TextView
        val mequrantext: TextView
        val quranictext: TextView
        var radioGroup: RadioGroup
        var rdqalam: RadioButton
        var rdmequran: RadioButton
        var rdamiri: RadioButton

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            rdqalam = itemView.findViewById(R.id.rdqalam)
            rdmequran = itemView.findViewById(R.id.rdmequran)
            rdamiri = itemView.findViewById(R.id.rdquranic)
            radioGroup = itemView.findViewById(R.id.rdgroup)
            text = itemView.findViewById(R.id.text)
            qalamtext = itemView.findViewById(R.id.qalamtext)
            mequrantext = itemView.findViewById(R.id.mequrantext)
            quranictext = itemView.findViewById(R.id.quranictext)
            itemView.setOnClickListener(this)
            rdqalam.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        android.preference.PreferenceManager.getDefaultSharedPreferences(activity)
                            .edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("Arabic_Font_Selection", "AlQalam.ttf")
                    editor.apply()
                    dismiss()
                }
            })
            rdmequran.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        android.preference.PreferenceManager.getDefaultSharedPreferences(activity)
                            .edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("Arabic_Font_Selection", "me_quran.ttf")
                    editor.apply()
                    dismiss()
                }
            })
            rdamiri.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        android.preference.PreferenceManager.getDefaultSharedPreferences(activity)
                            .edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("Arabic_Font_Selection", "quranicfontregular.ttf")
                    editor.apply()
                    dismiss()
                }
            })
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    private inner class FontQuranAdapter internal constructor(private val mItemCount: String) :
        RecyclerView.Adapter<FontQuranListDialogFragment.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FontQuranListDialogFragment.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        public override fun onBindViewHolder(
            holder: FontQuranListDialogFragment.ViewHolder,
            position: Int
        ) {
            val sample: String = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
            val mequran: Typeface = Typeface.createFromAsset(
                requireContext().assets, "me_quran.ttf"
            )
            val qalam: Typeface = Typeface.createFromAsset(
                requireContext().assets, "AlQalam.ttf"
            )
            val quran: Typeface = Typeface.createFromAsset(
                requireContext().assets, "quranicfontregular.ttf"
            )
            holder.text.text = sample
            holder.qalamtext.text = sample
            holder.mequrantext.text = sample
            holder.qalamtext.typeface = qalam
            holder.mequrantext.typeface = mequran
            holder.quranictext.text = sample
            holder.quranictext.typeface = quran
            val theme: String? = PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("Arabic_Font_Selection", "me_quran.ttf")
            if ((theme == "me_quran.ttf")) {
                holder.rdmequran.isChecked = true
            } else if ((theme == "AlQalam.ttf")) {
                holder.rdqalam.isChecked = true
            } else if ((theme == "quranicfontregular.ttf")) {
                holder.rdamiri.isChecked = true
            }
        }

        public override fun getItemCount(): Int {
            return 1
        }

        fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
            this.mItemClickListener = mItemClickListener
        }
    }

    companion object {
        // TODO: Customize parameters
        fun newInstance(): FontQuranListDialogFragment {
            val fragment: FontQuranListDialogFragment = FontQuranListDialogFragment()
            return fragment
        }
    }
}