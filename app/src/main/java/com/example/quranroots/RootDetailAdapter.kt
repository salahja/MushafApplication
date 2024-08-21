package com.example.quranroots

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R

import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.QuranGrammarApplication
import com.google.android.material.chip.Chip

class RootDetailAdapter(

    private var catTwoArrayList: ArrayList<String>,
    private val context: Context
) :
    RecyclerView.Adapter<RootDetailAdapter.ViewHolder>() {

    fun updateList(newList: List<String>) {
        catTwoArrayList = newList as ArrayList<String>
        notifyDataSetChanged()
    }

    lateinit var mItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_arabicroot_detail, parent, false)
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgs = context.resources.obtainTypedArray(R.array.cat_img)
        imgs.recycle()
        val catOne = catTwoArrayList[position]
        //  Drawable icon = imgs.getDrawable(catOne.getId() - 1);
        //  imgs.recycle();
        //  CatTwo catOne= catTwoArrayList.get(position);
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val prefs =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        val sb = StringBuilder()
        holder.arabicroot_detail.text = catOne
        // sb.append(catOne.getId());
        //    holder.rulenumber.setTextSize(arabicFontsize);
        //   holder.title.setText(catOne.getTitle());
        //  holder.title.setTextSize(18);
        //  holder.id.setCompoundDrawablesWithIntrinsicBounds( icon, null, null, null);
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return catTwoArrayList.size.toLong()
    }

    fun getItem(position: Int): Any {
        return catTwoArrayList[position]
    }

    override fun getItemCount(): Int {
        return catTwoArrayList.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        if (mItemClickListener != null) {
            this.mItemClickListener = mItemClickListener
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        // public final ImageView id;
        val arabicroot_detail: Chip

        init {
            view.tag = this
            itemView.setOnClickListener(this)
            //  id = view.findViewById(R.id.imgview);
            arabicroot_detail = view.findViewById(R.id.arabicroot_detail)
            arabicroot_detail.tag = "root"
            arabicroot_detail.setOnClickListener(this)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, layoutPosition)
            }
        }
    }
}