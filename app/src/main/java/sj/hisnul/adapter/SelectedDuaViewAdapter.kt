package sj.hisnul.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import sj.hisnul.entity.hduadetailsEnt

class SelectedDuaViewAdapter(
    private val duadetailsitems: ArrayList<ArrayList<hduadetailsEnt>>,
    private var subheaders: ArrayList<String>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.hfrag_duaitems, parent, false)
            ViewHolder(view)
        } else (if (viewType == TYPE_HEADER) {
            //Inflating header view
            val view = LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
            HeaderViewHolder(view)
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.duareferncefooter, parent, false)
            FooterViewHolder(itemView)
        } else null)!!
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int,
    ) {
        if (holder is HeaderViewHolder) {
            val items = duadetailsitems[position]
            holder.headerTitle.text = "Header View"
            if (!items[0].top?.isEmpty()!!) {
                holder.headerTitle.text = items[0].bottom
                holder.headerTitle.visibility = View.VISIBLE
            } else {
                holder.headerTitle.visibility = View.GONE
            }
            holder.headerTitle.setOnClickListener { view: View? -> }
        } else if (holder is FooterViewHolder) {
            holder.footerText.text = "footer"
            holder.footerText.setOnClickListener { view: View? -> }
        } else if (holder is ViewHolder) {
            //   try {
            val items = duadetailsitems[position - 1]
            val str = subheaders[position - 1]
            //   final Integer arabicFontsize = Integer.valueOf(fonts);
            val sb = StringBuilder()
            sb.append(items[0].ID)
            holder.duaheader.text = str
            //    holder.rulenumbe.r.setTextSize(arabicFontsize);
            holder.tvDuaNumber.text = sb
            //  holder.title.setText(catOne.getTitle_en());
            //  holder.title.setTextSize(18);
            holder.tvDuaNumber.textSize = 18f
            holder.duaheader.textSize = 24f
            if (!items[0].top?.isEmpty()!!) {
                holder.top.text = items[0].top
                holder.top.textSize = 24f
                holder.top.visibility = View.VISIBLE
            } else {
                holder.top.visibility = View.GONE
            }
            if (items[0].arabic!!.isNotEmpty()) {
                holder.tvDuaArabic.text = items[0].arabic
                holder.tvDuaArabic.textSize = 24f
                holder.tvDuaArabic.visibility = View.VISIBLE
            } else {
                holder.tvDuaArabic.visibility = View.GONE
            }
            if (items[0].arabic!!.isNotEmpty()) {
                holder.tvDuaTranslation.text = items[0].translations
                holder.tvDuaTranslation.textSize = 24f
                holder.tvDuaTranslation.visibility = View.VISIBLE
            } else {
                holder.tvDuaTranslation.visibility = View.GONE
            }
            if (items[0].transliteration!!.isNotEmpty()) {
                holder.tvliteration.text = Html.fromHtml(items[0].transliteration)
                holder.tvliteration.textSize = 24f
                holder.tvliteration.visibility = View.VISIBLE
            } else {
                holder.tvliteration.visibility = View.GONE
            }
            if (items[0].bottom!!.isNotEmpty()) {
                holder.tvbottom.text = items[0].bottom
                holder.tvbottom.visibility = View.VISIBLE
                holder.tvbottom.textSize = 24f
            } else {
                holder.tvbottom.visibility = View.GONE
            }
            holder.tvDuaReference.text = items[0].reference
            holder.sharebtn.setOnClickListener { convertView: View ->
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    """
                    ${holder.duaheader.text}
                    
                    ${holder.tvDuaArabic.text}
                    
                    ${holder.tvDuaTranslation.text}
                    
                    ${holder.tvDuaReference.text}
                    
                    ${convertView.resources.getString(R.string.action_share_credit)}
                    """.trimIndent()
                               )
                intent.type = "text/plain"
                convertView.context.startActivity(
                    Intent.createChooser(
                        intent,
                        convertView.resources.getString(R.string.action_share_title)
                                        )
                                                 )
            }
        }
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return duadetailsitems.size.toLong()
    }

    fun getItem(position: Int): Any {
        return duadetailsitems[position]
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        } else if (position == duadetailsitems.size + 1) {
            return TYPE_FOOTER
        }
        return TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return duadetailsitems.size + 2
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int)
    }

    internal class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTitle: TextView

        init {
            headerTitle = view.findViewById<View>(R.id.header_text) as TextView
        }
    }

    private class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val footerText: TextView

        init {
            footerText = view.findViewById(R.id.txtDuaReference)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val tvDuaNumber: TextView
        val tvDuaArabic: TextView
        val tvDuaReference: TextView
        val tvDuaTranslation: TextView
        val tvliteration: TextView
        val tvbottom: TextView
        val duaheader: TextView
        val sharebtn: TextView
        val top: TextView

        init {
            view.tag = this
            top = view.findViewById(R.id.top)
            sharebtn = view.findViewById(R.id.sharebtn)
            duaheader = view.findViewById(R.id.duaheader)
            tvDuaNumber = view.findViewById(R.id.txtDuaNumber)
            tvDuaArabic = view.findViewById<View>(R.id.txtDuaArabic) as TextView
            tvDuaTranslation = view.findViewById<View>(R.id.txtDuaTranslation) as TextView
            tvDuaReference = view.findViewById<View>(R.id.txtDuaReference) as TextView
            tvliteration = view.findViewById(R.id.transliteration)
            tvbottom = view.findViewById(R.id.txtbottom)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_FOOTER = 1
        private const val TYPE_ITEM = 2
    }
}