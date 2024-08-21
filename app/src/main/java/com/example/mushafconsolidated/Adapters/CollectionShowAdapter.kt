package com.example.mushafconsolidated.Adapters


import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.PreferenceUtil
import com.example.utility.QuranGrammarApplication


/**
 * RecyclerView.Adapter<BookmarksShowAdapter.ViewHolder>
 * Adapter class for the bookmarks list view
</BookmarksShowAdapter.ViewHolder> */
class CollectionShowAdapter : RecyclerView.Adapter<CollectionShowAdapter.ViewHolder> {
    var mItemClickListener: OnItemClickListener? = null
    private var BookmarksShowAdapterContext: Context? = null
    var bookmarkpostion: Int = 0
    var pref: PreferenceUtil? = null
    private var holderposition: Int = 0
    var bookmarid: Int = 0
    val surahName: String? = null
    val bookChapterno: Int = 0
    val bookVerseno: Int = 0
    var bookMarkArrayList: List<BookMarks>? = null

    constructor() {}
    constructor(context: Context?) {
        BookmarksShowAdapterContext = context
    }


    public override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionShowAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_bookmar_two, parent, false)
        //   sendVerseClick=(SendVerseClick) getActivity();
        return ViewHolder(view)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onBindViewHolder(holder: CollectionShowAdapter.ViewHolder, position: Int) {

        val bookMark: BookMarks? = bookMarkArrayList!![position]
        holderposition = position
        if (bookMark != null) {
            bookmarid = bookMark.id
        }
        val imgs: TypedArray =
            QuranGrammarApplication.context?.resources!!.obtainTypedArray(R.array.sura_imgs)
        val shared: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val isNightmode: String? = shared.getString("theme", "dark")
        val chapterno: String = bookMark!!.chapterno.toString()
        if (chapterno.isNotEmpty()) {
            val drawable: Drawable? = imgs.getDrawable((chapterno.toInt() - 1))
            holder.pinicon.setImageDrawable(drawable)
            //     holder.surahicon.setImageDrawable(drawable);
        }
        //   final Drawable drawable = imgs.getDrawable((Integer.parseInt(chapterno) - 1));
        if ((isNightmode == "dark") || (isNightmode == "blue")) {
            holder.pinicon.setColorFilter(Color.CYAN)
        }
        holder.pinicon.visibility = View.GONE
        holder.collectionicon.visibility = View.VISIBLE
        if (bookMark.header != null) {
            holder.header.text = bookMark.header + "(" + bookMark.count + " aya;s" + ")"
            //   holder.header.setText(bookMark.getHeader());
        }
        // holder.datestamp.setText(bookMark.getDatetime())
        holder.suraName.text = bookMark.surahname
        holder.chapterno.text = bookMark.chapterno.toString()
        holder.verseno.text = bookMark.verseno + ""
        val arabicFontSize: Int = shared.getInt("pref_font_arabic_key", 18)
        holder.datestamp.textSize = arabicFontSize.toFloat()
        holder.suraName.textSize = arabicFontSize.toFloat()
        holder.verseno.textSize = arabicFontSize.toFloat()
        holder.chapterno.textSize = arabicFontSize.toFloat()
        /*     if (isNightmode.equals("dark")) {
            ContextCompat.getColor(QuranGrammarApplication.getContext(), R.color.color_background_overlay);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(QuranGrammarApplication.getContext(), R.color.color_background_overlay));

        } else if (isNightmode.equals("blue")) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(QuranGrammarApplication.getContext(), R.color.solarizedBase02));

        }
*/
    }

    public override fun getItemCount(): Int {
        return bookMarkArrayList!!.size
    }

    fun removeItem(position: Int) {
        //  bookMarkArrayList.removeAt(position)

        notifyItemRemoved(position)
    }

    fun getItem(position: Int): BookMarks? {
        return bookMarkArrayList!![position]
    }

    // public void restoreItem(ArrayList<BookMarks> item, int position) {
    //      data.add(position, item);
    //     notifyItemInserted(position);
    //  }
    inner class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val datestamp: TextView
        val suraName: TextView
        val verseno: TextView
        val pinicon: ImageView
        val collectionicon: ImageView

        //val cardView: CardView
        var chapterno: TextView
        var header: TextView

        init {
            collectionicon = view.findViewById(R.id.bookmark)
            header = view.findViewById(R.id.header)
            pinicon = view.findViewById(R.id.imgview)
//            cardView = view.findViewById(R.id.cardview)
            datestamp = view.findViewById(R.id.date)
            chapterno = view.findViewById(R.id.chapterno)
            suraName = view.findViewById<View>(R.id.surahname) as TextView
            verseno = view.findViewById(R.id.verseno)
            chapterno = view.findViewById(R.id.chapterno)
            view.setOnClickListener(this) // current clickListerner
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    companion object {
        private const val TAG: String = "BookmarksShowAdapter"
    }
}