package com.example.mushafconsolidated.Adapters


import android.annotation.SuppressLint
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.DAO.BookMarksPojo

import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.QuranGrammarApplication
import com.google.android.material.checkbox.MaterialCheckBox

class BookmarkCreateAdapter(collectionC: List<BookMarksPojo>?) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val collection: List<BookMarksPojo>?
    var frameLayout: RelativeLayout? = null
    var mItemClickListener: OnItemClickListener? = null
    var weaknesscolor = 0
    var wazancolor = 0
    var subheaders: ArrayList<String>? = null

    init {
        collection = collectionC
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.create_bookmarks_collection, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val collections: BookMarksPojo? = try {
            collection!![position]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
        val itemholder = holder as ViewHolder
        val shared =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        if (null != collections) {
            itemholder.collectiondetails.text =
                collections.header + (collections.count + ("-") + ("aya's)"))
            itemholder.collectiondetails.visibility = View.VISIBLE
            itemholder.collectionimage.visibility = View.VISIBLE
        } else {
            itemholder.collectiondetails.visibility = View.GONE
            itemholder.collectionimage.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        if (collection == null) {
            return 0
        }
        return if (collection.isEmpty()) {
            //Return 1 here to show nothing
            1
        } else collection.size + 1

        // Add extra view to show the footer view
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return collection!!.size.toLong()
    }

    fun getItem(position: Int): Any {
        return collection!![position]
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        var purple: RadioButton? = null
        var black: RadioButton? = null
        var dark_blue: RadioButton? = null
        var green: RadioButton? = null
        var brown: RadioButton? = null
        var datestamp: TextView? = null
        var suraName: TextView? = null
        var verseno: TextView? = null
        val collectiondetails: MaterialCheckBox
        var surahicon: ImageView? = null
        val collectionimage: ImageView
        var cardView: CardView? = null
        var chapterno: TextView? = null

        init {
            view.tag = this
            collectionimage = view.findViewById(R.id.imgviewcol)
            frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
            collectiondetails = view.findViewById(R.id.checkbox)
            itemView.setOnClickListener(this)
            collectiondetails.setOnClickListener(this) // current clickListerner
            collectiondetails.tag = "ck"
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}