package com.example.mushafconsolidated.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.layout
import com.example.mushafconsolidated.Utils

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
class TranslationListPrefrence constructor() : BottomSheetDialogFragment() {
    private var chap_id: Int = 0
    private var verse_id: Int = 0
    private var name: String? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            name = requireArguments().getString("name")
            chap_id = requireArguments().getInt("chap_id")
            verse_id = requireArguments().getInt("verse_no")
        }
        val utils: Utils = Utils(activity)
        //  setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Material3_BottomSheetDialog);
    }

    var mItemClickListener: OnItemClickListener? = null
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: TranslationListPrefrence.FontQuranAdapter? = null
    lateinit var frameLayout: RelativeLayout
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*    ContextThemeWrapper contextThemeWrapper= new ContextThemeWrapper(getActivity(),R.style.Theme_DarkBlue);


   return      inflater.cloneInContext(contextThemeWrapper).inflate(R.layout.quran_list_dialog, container, false);*/
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.quran_list_dialog, container, false)
        //  return inflater.inflate(R.layout.selecttranslationselection, container, false);
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val details: ArrayList<String> = ArrayList()
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
            layout.translation_prference_dialog, parent, false
        )
    ), View.OnClickListener {
        var ivCopy: ImageView? = null
        var ivBookmark: ImageView? = null
        var ivShare: ImageView? = null
        var textView: TextView? = null
        var textView2: TextView? = null
        var textView3: TextView? = null
        var en_sahih: RadioButton
        var ur_junagarhi: RadioButton
        var en_jalalayn: RadioButton
        var ur_jalalayn: RadioButton
        var en_erberry: RadioButton

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            en_sahih = itemView.findViewById(R.id.en_sahi)
            ur_junagarhi = itemView.findViewById(R.id.ur_junagarhi)
            en_jalalayn = itemView.findViewById(R.id.en_jalalayn)
            ur_jalalayn = itemView.findViewById(R.id.ur_jalalayn)
            en_erberry = itemView.findViewById(R.id.en_arberry)
            //   frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
            en_sahih.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("selecttranslation", "en_sahih")
                    editor.apply()
                    dismiss()
                }
            })
            ur_junagarhi.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("selecttranslation", "ur_junagarhi")
                    editor.apply()
                    dismiss()
                }
            })
            en_jalalayn.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("selecttranslation", "en_jalalayn")
                    editor.apply()
                    dismiss()
                }
            })
            ur_jalalayn.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("selecttranslation", "ur_jalalayn")
                    editor.apply()
                    dismiss()
                }
            })
            en_erberry.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("selecttranslation", "en_arberry")
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

    private inner class FontQuranAdapter constructor() :
        RecyclerView.Adapter<TranslationListPrefrence.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TranslationListPrefrence.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        public override fun onBindViewHolder(
            holder: TranslationListPrefrence.ViewHolder,
            position: Int
        ) {
            val sharedPreferences: SharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val theme: String? = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("selecttranslation", "en_sahih")
            if ((theme == "en_sahih")) {
                holder.en_sahih.isChecked = true
            } else if ((theme == "ur_jalalayn")) {
                holder.ur_junagarhi.isChecked = true
            } else if ((theme == "ur_junagarhi")) {
                holder.en_jalalayn.isChecked = true
            } else if ((theme == "ur_jalalayn")) {
                holder.ur_jalalayn.isChecked = true
            } else if ((theme == "en_erberry")) {
                holder.en_erberry.isChecked = true
            }


            /*      holder.ivShare.setOnClickListener(convertView -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,
                        holder.duaheader.getText() + "\n\n" +
                                holder.tvDuaArabic.getText() + "\n\n" +
                                holder.tvDuaTranslation.getText() + "\n\n" +
                                holder.tvDuaReference.getText() + "\n\n" +
                                convertView.getResources().getString(R.string.action_share_credit)
                );
                intent.setType("text/plain");
                convertView.context!!.startActivity(
                        Intent.createChooser(
                                intent,
                                convertView.getResources().getString(R.string.action_share_title)
                        )
                );
            });
*/
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

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(): TranslationListPrefrence {
            val fragment: TranslationListPrefrence = TranslationListPrefrence()
            return fragment
        }
    }
}