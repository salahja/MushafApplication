package org.sj.conjugator.adapter

import android.graphics.Typeface
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import database.entity.kov
import org.sj.conjugator.interfaces.OnItemClickListener

/*
class Person(val name: String) {
    val children: MutableList<Person> = mutableListOf()
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
 */
/*
class CustomRecyclerAdapter constructor(
    val context: Context,
    val topics: Array<String> = emptyArray(),
    private var alist: ArrayList<String>
 ) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    constructor(context: Context, sss: ArrayList<String>) : this(context, alist = sss)

    // ...
}
 */
class rulesbottomsheetadapter :
    RecyclerView.Adapter<rulesbottomsheetadapter.ViewHolder>() {
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null
    private var kovArrayList = ArrayList<kov>()



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //      View view = LayoutInflater.from(parent.context!!).inflate(R.layout.sarfkabeercolumn, parent, false);


        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rules, parent, false)

        //    View view = LayoutInflater.from(parent.context!!).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val toArray = kovArrayList[position]

        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val fonts = prefs.getString("Arabic_Font_Size", "25")

        val mequran = Typeface.createFromAsset(
            QuranGrammarApplication.context!!.assets,
            "me_quran.ttf"
        )
        val arabicFontsize = Integer.valueOf(fonts)

        holder.rulenumber.text =
            StringBuilder().append(toArray.rulename).append("(").append(toArray.example).append(")")
                .toString()


        holder.rulenumber.textSize = arabicFontsize.toFloat()
        holder.rulenumber.typeface = mequran
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return kovArrayList.size.toLong()
    }

    fun getItem(position: Int): Any {
        return kovArrayList[position]
    }

    override fun getItemCount(): Int {
        return kovArrayList.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val rulenumber: TextView
        private val rulename: TextView

        init {
            //    itemView.setTag(this);
            //     itemView.setOnClickListener(onItemClickListener);
            rulenumber = view.findViewById(R.id.tvKovNumber)
            rulename = view.findViewById(R.id.tvKovName)
            view.setOnClickListener(this)
            rulename.setOnClickListener(this)
            //  mumaroof.setOnClickListener(this);//R.id.mumaroof);
            //  mamaroof.setOnClickListener(this);//R.id.mamaroof);
            //   ismmafool.setOnClickListener(this);//R.id.ismmafool);
            //   mumajhool.setOnClickListener(this);//R.id.mumajhool);
            //   mamajhool.setOnClickListener(this);//R.id.mamajhool);
            //   amr.setOnClickListener(this);//R.id.amr);
            //   nahiamr.setOnClickListener(this);//R.id.nahiamr);
            //    ismala.setOnClickListener(this);//R.id.ismaalatable);
            //    ismzarf.setOnClickListener(this);//R.id.zarftable);
            //      rootword.setOnClickListener(this);//R.id.weaknesstype);
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
    fun setmutable(kovs: ArrayList<kov>) {
        if (kovs != null) {
            kovArrayList=kovs
        }
    }


}