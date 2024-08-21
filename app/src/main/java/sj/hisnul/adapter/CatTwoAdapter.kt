package sj.hisnul.adapter

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.entity.hcategoryEnt

class CatTwoAdapter(

    private val context: Context
) :
    RecyclerView.Adapter<CatTwoAdapter.ViewHolder?>() {
  //  private val catTwoArrayList: ArrayList<hcategory>

   private var mylist:   List<hcategoryEnt> =ArrayList()

    var mItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.frag_catwo_drawble, parent, false)
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgs: TypedArray = context.resources.obtainTypedArray(R.array.cat_img)
        val catOne: hcategoryEnt = mylist[position]


        val icon: Drawable? = imgs.getDrawable(catOne.id - 1)
        imgs.recycle()
        //  CatTwo catOne= catTwoArrayList.get(position);
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)

        val prefs: SharedPreferences =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)

        val sb = StringBuilder()
        sb.append(catOne.id)
        //    holder.rulenumber.setTextSize(arabicFontsize);
        holder.title.text = catOne.title
        holder.title.textSize = 18f
        //  holder.id.setCompoundDrawablesWithIntrinsicBounds( icon, null, null, null);
        holder.id.setImageDrawable(icon)
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return mylist.size.toLong()
    }
    override fun getItemCount(): Int {
        return mylist.size
    }
    fun getItem(position: Int): Any {
        return mylist[position]
    }



    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    fun setmutable(userL: List<hcategoryEnt>?) {
        if (userL != null) {
            mylist=userL
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val id: ImageView
        val title: TextView
        var cardview: CardView? = null

        init {
            id = view.findViewById(R.id.imgview)
            title = view.findViewById(R.id.content)
            view.setOnClickListener(this)
            id.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}