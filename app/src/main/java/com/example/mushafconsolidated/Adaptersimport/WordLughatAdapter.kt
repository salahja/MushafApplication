package com.example.mushafconsolidated.Adaptersimport

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener


class WordLughatAdapter(
    private var worddictorary: ArrayList<lughat>?,
    private var context: Context?,
    var language: String
) : RecyclerView.Adapter<WordLughatAdapter.ItemViewAdapter>() {
    var mItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordLughatAdapter.ItemViewAdapter {
        val view: View
        view = LayoutInflater.from(parent.context!!).inflate(R.layout.lughat_layout, parent, false)
        return ItemViewAdapter(view)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: WordLughatAdapter.ItemViewAdapter, position: Int) {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context!!
        )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val mequran: Typeface = Typeface.createFromAsset(context!!.assets, quranFont)
        //    holder.wordDictionary.setText(worddetails.get("word"));
        if (worddictorary!!.isNotEmpty()) {
            var replace1: String? = ""
            var replace2: String? = ""
            var replace3: String? = ""
            var replace4: String? = ""
            if ((language == "english")) {
                val en_lughat: String = worddictorary!![0].en_lughat
                val meanings: String = worddictorary!![0].meaning
                val rootword: String = worddictorary!![0].rootword
                val arabicword: String = worddictorary!![0].arabicword
                replace1 = en_lughat.replace("\\n", "<br><p>")
                replace2 = rootword.replace("\\n", "<br><p>")
                replace3 = arabicword.replace("\\n", "<br><p>")
                replace4 = meanings.replace("\\n", "<br><p>")
                holder.wordDictionary.text = Html.fromHtml(replace1)
                holder.rootwowrd.text = Html.fromHtml(replace2)
                holder.arabicword.text = Html.fromHtml(replace3)
                holder.meaning.text = Html.fromHtml(replace4)
            } else if ((language == "urdu")) {
                val ur_lughat: String = worddictorary!![0].ur_lughat
                val urdu: String =
                    ur_lughat.replace("\\n", "<br><p>").replace("\\n".toRegex(), "<br><p>")
                val meanings: String = worddictorary!![0].meaning
                val rootword: String = worddictorary!![0].rootword
                val arabicword: String = worddictorary!![0].arabicword
                replace2 = rootword.replace("\\n", "<br><p>")
                replace3 = arabicword.replace("\\n", "<br><p>")
                replace4 = meanings.replace("\\n", "<br><p>")
                holder.wordDictionary.text = Html.fromHtml(urdu)
                holder.rootwowrd.text = Html.fromHtml(replace2)
                holder.arabicword.text = Html.fromHtml(replace3)
                holder.meaning.text = Html.fromHtml(replace4)
            }
            //.replace("\\n", "<br>");
        }
    }

    override fun getItemId(position: Int): Long {
        return worddictorary!![position].surah.toLong()
    }

    override fun getItemCount(): Int {
        return worddictorary!!.size
    }

    fun getItem(position: Int): Any {
        return worddictorary!![position]
    }

    inner class ItemViewAdapter constructor(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val wordDictionary: TextView
        private val wordDictionaryUrdu: TextView
        val meaning: TextView
        val rootwowrd: TextView
        val arabicword: TextView
        private val referenceView: TextView
        var dismissview: ImageView? = null
        var i: Int = ContextCompat.getColor(context!!, R.color.kashmirigreen)

        init {
            rootwowrd = view.findViewById(R.id.rootward)
            arabicword = view.findViewById(R.id.arabicword)
            wordDictionary = view.findViewById(R.id.wordDictionary)
            wordDictionaryUrdu = view.findViewById(R.id.wordDictionaryUrdu)
            meaning = view.findViewById(R.id.meaning)
            referenceView = view.findViewById(R.id.referenceView)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}