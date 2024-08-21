package com.example.mushafconsolidated.Adaptersimport

import SharedPref
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.model.Juz
import com.example.utility.QuranGrammarApplication


//public class VerseDisplayAdapter extends RecyclerView.Adapter<VerseDisplayAdapter.ItemViewAdapter> {
//public class SurahPartAdap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
class JuzSurahDisplayAdapter(private val context: Context?, private val JuzArray: List<Juz>) :
    RecyclerView.Adapter<JuzSurahDisplayAdapter.ItemViewAdapter>() {


    var mItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        //    view = LayoutInflater.from(parent.context!!).inflate(R.layout.surarowlinear, parent, false);
        view = LayoutInflater.from(parent.context).inflate(R.layout.juz_hizb, parent, false)
        return ItemViewAdapter(view, viewType)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        val juzitem = JuzArray[position]
        val context = QuranGrammarApplication.context!!
        val pref = context.getSharedPreferences("lastread", Context.MODE_PRIVATE)
        val imgs = this.context?.resources?.obtainTypedArray(R.array.sura_imgs)
        imgs?.recycle()
        val array = imgs
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val juzdetail = "Juz" + " " + java.lang.String.valueOf(juzitem.juz)


        val surahdetails = java.lang.StringBuilder()
        surahdetails.append(juzitem.namearabic).append(" ").append(juzitem.chapterid).append(":")
            .append(juzitem.ayah)


        val text: String = juzitem.qurantext
        val split = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val defaultfont = sharedPreferences.getBoolean("default_font", true)
        holder.tvnumber.text = juzdetail
        holder.tvJuz.text = surahdetails
        if (split.size > 1) {
            holder.tvArabic.text = split[0] + " " + split[1]
        } else if (split.size == 1) {
            holder.tvArabic.text = split[0]
        }
        holder.tvnumber.textSize = SharedPref.SeekarabicFontsize().toFloat()
        holder.tvJuz.textSize = SharedPref.SeekarabicFontsize().toFloat()
        holder.tvArabic.textSize = SharedPref.SeekarabicFontsize().toFloat()
        if (!defaultfont) {
            holder.tvArabic.textSize = SharedPref.SeekarabicFontsize().toFloat()
        }
    }

    fun getItem(position: Int): Any {
        return JuzArray[position].chapterid - 1
    }

    override fun getItemCount(): Int {
        return JuzArray.size
    }

    inner class ItemViewAdapter internal constructor(layout: View, viewType: Int) :
        RecyclerView.ViewHolder(layout), View.OnClickListener // current clickListerner
    {
        var tvnumber: TextView
        var tvJuz: TextView
        var tvArabic: TextView
        var part: TextView? = null
        private var surahcardview: CardView

        init {
            surahcardview = itemView.findViewById(R.id.surahcardview)
            tvnumber = itemView.findViewById(R.id.tvNumber)
            tvJuz = itemView.findViewById(R.id.tvJuz)
            tvArabic = itemView.findViewById(R.id.tvArabic)
            layout.setOnClickListener(this) // current clickListerner
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    companion object {
        //implements Filterable
        private const val TAG = "SurahPartAdap "
    }
}