package sj.hisnul.adapter

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.PreferenceUtil
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.entity.hduanamesEnt

/**
 * RecyclerView.Adapter<BookmarksShowAdapter.ViewHolder>
 * Adapter class for the bookmarks list view
</BookmarksShowAdapter.ViewHolder> */
class NewHisnulBookmarksShowAdapter :
    RecyclerView.Adapter<NewHisnulBookmarksShowAdapter.ViewHolder?> {
    var mItemClickListener: OnItemClickListener? = null
    private var BookmarksShowAdapterContext: Context? = null
    var bookmarkpostion = 0
    var bookMarkArrayList: ArrayList<hduanamesEnt>? = null
    var pref: PreferenceUtil? = null
    private var holderposition = 0
    var bookmarid = 0
    val bookChapterno = 0

    constructor(context: Context?) {
        BookmarksShowAdapterContext = context
    }

   /* fun setHolderposition(holderposition: Int) {
        this.holderposition = holderposition
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookmark, parent, false)
        //   sendVerseClick=(SendVerseClick) getActivity();
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return bookMarkArrayList!!.size
    }
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catOne: hduanamesEnt = bookMarkArrayList!![position]
        holderposition=position

        val shared: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)

        //   final Integer arabicFontsize = Integer.valueOf(fonts);
        val empty: Boolean = catOne.chapname!!.isEmpty()
        if (!empty) {
            val sb = StringBuilder()
            sb.append(catOne.ID)
            holder.id.text = sb
            holder.duaname.text = catOne.chapname
            holder.duaname.textSize = 18f
        } else {
            holder.duaname.visibility = View.GONE
            holder.id.visibility = View.GONE
            holder.ivdelete.visibility = View.GONE
        }
    }

    /*val itemCount: Int
        get() = bookMarkArrayList!!.size*/
    fun removeItem(position: Int) {
        bookMarkArrayList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): Any {
        return bookMarkArrayList!![position]
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val id: TextView
        val chapter: TextView
        val duaname: TextView
        val ivdelete: ImageView

        init {
            view.tag = this
            view.setOnClickListener(this)
            //    itemView.setTag(this);
            //     itemView.setOnClickListener(onItemClickListener);
            id = view.findViewById(R.id.id)
            chapter = view.findViewById(R.id.chapter)
            duaname = view.findViewById(R.id.duaname)
            ivdelete = view.findViewById(R.id.ivdelete)
            chapter.setOnClickListener(this)
            id.setOnClickListener(this)
            ivdelete.setOnClickListener(this)

            id.tag = "id"
            ivdelete.tag = "delete"
            duaname.tag = "id"
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    companion object {
    }
}