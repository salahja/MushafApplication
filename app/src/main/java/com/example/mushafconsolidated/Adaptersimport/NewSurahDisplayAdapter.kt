package com.example.mushafconsolidated.Adaptersimport


import SharedPref
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.QuranGrammarApplication
import java.util.Locale


//public class VerseDisplayAdapter extends RecyclerView.Adapter<VerseDisplayAdapter.ItemViewAdapter> {
//public class SurahPartAdap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
class NewSurahDisplayAdapter(
    private val context: Context?,

    ) :
    RecyclerView.Adapter<NewSurahDisplayAdapter.ItemViewAdapter>(), Filterable {
    constructor(context: Context?, allAnaChapters: java.util.ArrayList<ChaptersAnaEntity>) : this(
        context
    ) {
        listonearray = allAnaChapters
        chapterfilered = allAnaChapters
    }


    var mItemClickListener: OnItemClickListener? = null
    private lateinit var listonearray: List<ChaptersAnaEntity>
    private lateinit var chapterfilered: List<ChaptersAnaEntity>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        //    view = LayoutInflater.from(parent.context!!).inflate(R.layout.surarowlinear, parent, false);
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.orignalsurarowlinear, parent, false)
        return ItemViewAdapter(view, viewType)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        val surah = chapterfilered[position]
        val context = QuranGrammarApplication.context!!
        val pref = context.getSharedPreferences("lastread", Context.MODE_PRIVATE)
        val imgs = this.context?.resources?.obtainTypedArray(R.array.sura_imgs)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val theme = sharedPreferences.getString("themepref", "dark")
        val defaultfont = sharedPreferences.getBoolean("default_font", true)
        val surahIsmakki: Int = surah.ismakki
        val cno: Int = surah.chapterid
        val te = cno.toString() + " " + surah.nameenglish;

        holder.tvsurahleft.text = te
        if (!defaultfont) {
            holder.tvsurahleft.textSize = SharedPref.SeekarabicFontsize().toFloat()
        }
        //
        val drawable = imgs?.getDrawable(cno - 1)
    //    imgs?.recycle()
        holder.ivsurahicon.setImageDrawable(drawable)
        if (surahIsmakki == 1) {
            holder.makkimadaniIcon.setImageResource(R.drawable.ic_makkah_foreground)
        } else {
            holder.makkimadaniIcon.setImageResource(R.drawable.ic_madinah_foreground)
        }
        if (theme == "green") {
            holder.surahcardview.setCardBackgroundColor(context.resources.getColor(R.color.mdgreen_theme_dark_onPrimary))
        } else if (theme == "blue") {
            holder.surahcardview.setCardBackgroundColor(context.resources.getColor(R.color.colorPrimaryDarkBlueLight))
        }
        if (theme == "dark" || theme == "blue" || theme == "green") {
            holder.makkimadaniIcon.setColorFilter(Color.CYAN)
            holder.ivsurahicon.setColorFilter(Color.CYAN)
        } else {
            holder.makkimadaniIcon.setColorFilter(Color.BLUE)
            holder.ivsurahicon.setColorFilter(Color.BLACK)
        }
        //   holder.tvsurahleft.setTextSize(SharedPref.SeekarabicFontsize());
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return chapterfilered.size.toLong()
    }

    fun getItem(position: Int): Any {
        return chapterfilered[position]
    }

    override fun getItemCount(): Int {
        return chapterfilered.size
    }

    fun setUp(listone: List<ChaptersAnaEntity>, listtwo: List<ChaptersAnaEntity?>?) {
        listonearray = listone
    }

    fun setUp(allAnaChapters: ArrayList<ChaptersAnaEntity>) {
        listonearray = allAnaChapters

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                var bool = false
                val charArray = charString.toCharArray()
                if (charString.isNotEmpty()) {
                    bool = Character.isDigit(charArray[0])
                }
                if (charString.isEmpty()) {
                    chapterfilered = listonearray
                } else {
                    if (!bool) {
                        val filteredList: MutableList<ChaptersAnaEntity> = ArrayList()
                        for (details in listonearray) {
                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (details.nameenglish.lowercase(Locale.ROOT).contains(
                                    charString.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            ) {
                                filteredList.add(details)
                            }
                        }
                        chapterfilered = filteredList
                    } else {
                        val filteredList: MutableList<ChaptersAnaEntity> = ArrayList()
                        for (details in listonearray) {
                            val str = charString.toInt()
                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (details.chapterid === str) {
                                filteredList.add(details)
                            }
                            chapterfilered = filteredList
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = chapterfilered
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                chapterfilered = filterResults.values as ArrayList<ChaptersAnaEntity>
                notifyDataSetChanged()
            }
        }
    }

    fun setmutable(allAnaChapters: java.util.ArrayList<ChaptersAnaEntity>) {
        listonearray = allAnaChapters
        chapterfilered = allAnaChapters
    }

    inner class ItemViewAdapter internal constructor(layout: View, viewType: Int) :
        RecyclerView.ViewHolder(layout), View.OnClickListener // current clickListerner
    {
        val tvsurahleft: TextView

        val makkimadaniIcon: ImageView
        val ivsurahicon: ImageView
        val surahcardview: CardView = itemView.findViewById(R.id.surahcardview)

        init {

            tvsurahleft = itemView.findViewById(R.id.tvArabic)
            makkimadaniIcon = itemView.findViewById(R.id.makkimadaniicon)

            ivsurahicon = itemView.findViewById(R.id.surahicon)
            layout.setOnClickListener(this) // current clickListerner
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    companion object {
        private const val TAG = "SurahPartAdap "
    }
}