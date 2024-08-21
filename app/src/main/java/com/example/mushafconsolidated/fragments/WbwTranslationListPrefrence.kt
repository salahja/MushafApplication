package com.example.mushafconsolidated.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
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
class WbwTranslationListPrefrence : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var mItemClickListener: OnItemClickListener? = null
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: WbwTranslationListPrefrence.FontQuranAdapter? = null

    //   lateinit var frameLayout: RelativeLayout
    fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layout.quran_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val details: ArrayList<String> = ArrayList()
        fontQuranAdapter = FontQuranAdapter()
        recyclerView.adapter = fontQuranAdapter
        fontQuranAdapter!!.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val checkedRadioButtonId: Int = radioGroup!!.checkedRadioButtonId
            }
        })
    }

    private inner class ViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            layout.wbw_prference_dialog, parent, false
        )
    ), View.OnClickListener {
        var wbwen: RadioButton = itemView.findViewById(R.id.wbwen)
        var wbwbangla: RadioButton
        var wbwindonesia: RadioButton
        var wbwurdu: RadioButton

        init {
            wbwbangla = itemView.findViewById(R.id.wbwbangla)
            wbwurdu = itemView.findViewById(R.id.wbwurdu)
            wbwindonesia = itemView.findViewById(R.id.wbwindonesia)
            //frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
            wbwen.setOnClickListener {
                val editor: SharedPreferences.Editor =
                    PreferenceManager.getDefaultSharedPreferences(activity).edit()
                editor.putString("wbw", "en")
                editor.apply()
                dismiss()
            }
            wbwbangla.setOnClickListener {
                val editor: SharedPreferences.Editor =
                    PreferenceManager.getDefaultSharedPreferences(activity).edit()
                editor.putString("wbw", "bn")
                editor.apply()
                dismiss()
            }
            wbwindonesia.setOnClickListener {
                val editor: SharedPreferences.Editor =
                    PreferenceManager.getDefaultSharedPreferences(activity).edit()
                editor.putString("wbw", "in")
                editor.apply()
                dismiss()
            }
            wbwurdu.setOnClickListener {
                val editor: SharedPreferences.Editor =
                    PreferenceManager.getDefaultSharedPreferences(activity).edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putString("wbw", "ur")
                editor.apply()
                dismiss()
            }
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    private inner class FontQuranAdapter :
        RecyclerView.Adapter<WbwTranslationListPrefrence.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WbwTranslationListPrefrence.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        override fun onBindViewHolder(
            holder: WbwTranslationListPrefrence.ViewHolder,
            position: Int
        ) {
            val sharedPreferences: SharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val theme: String? = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("wbw", "en")
            if ((theme == "en")) {
                holder.wbwen.isChecked = true
            } else if ((theme == "bn")) {
                holder.wbwbangla.isChecked = true
            } else if ((theme == "ur")) {
                holder.wbwurdu.isChecked = true
            } else if ((theme == "in")) {
                holder.wbwindonesia.isChecked = true
            }
        }

        override fun getItemCount(): Int {
            return 1
        }

        fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
            this.mItemClickListener = mItemClickListener
        }
    }

    companion object {
        const val TAG: String = "opton"

        // TODO: Customize parameters
        fun newInstance(): WbwTranslationListPrefrence {
            return WbwTranslationListPrefrence()
        }
    }
}