package database

import ImageItem
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import database.entity.AllahNames
import org.sj.conjugator.interfaces.OnItemClickListener
import java.util.Locale

class GridAdapter(
// --Commented out by Inspection START (28/08/23, 6:54 pm):
    //        //    private final Integer arabicTextColor;
    var context: Context, names: List<AllahNames>, data: ArrayList<ImageItem>,
) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>(), Filterable {
    // --Commented out by Inspection (28/08/23, 6:54 pm):private final CharSequence mSearchText = "";
    private val names: List<AllahNames>
    private var namesfilter: List<AllahNames>
    var data: ArrayList<ImageItem>

    // --Commented out by Inspection (28/08/23, 6:54 pm):int rootcolor, weaknesscolor, wazancolor;
    // --Commented out by Inspection (28/08/23, 6:54 pm):private final Context context;
    // --Commented out by Inspection (28/08/23, 6:53 pm):int bookmarkpostion;
    var mItemClickListener: OnItemClickListener? = null

    // --Commented out by Inspection STOP (28/08/23, 6:54 pm)
    // --Commented out by Inspection (28/08/23, 6:54 pm):private boolean mazeedregular;
    // --Commented out by Inspection (28/08/23, 6:53 pm):private int bookChapterno;
    // --Commented out by Inspection (28/08/23, 6:54 pm):private int bookVerseno;
    // --Commented out by Inspection (28/08/23, 6:53 pm):private Integer ayahNumber;
    // --Commented out by Inspection (28/08/23, 6:54 pm):private String urdu_font_selection;
    // --Commented out by Inspection (28/08/23, 6:54 pm):private int quran_arabic_font;
    // --Commented out by Inspection (28/08/23, 6:54 pm):private int urdu_font_size;
    // --Commented out by Inspection (28/08/23, 6:53 pm):private String arabic_font_selection;
    init {
        this.names = names
        namesfilter = names
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nameimages, parent, false)
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val prefs =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val fontCategory = sharedPreferences.getString("arabic_font_category", "me_quran.ttf")
        val prefArabicFontTypeface =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), fontCategory)
        val fonts = prefs.getString("Arabic_Font_Size", "25")
        val arabicfontSize = sharedPreferences.getInt("pref_font_arabic_key", 18)
        val translationfontsize = sharedPreferences.getInt("pref_font_englsh_key", 18)
        //  String arabic_font_category = prefs.getString("arabic_font_category", "kitab.tff");
        val isNightmode = sharedPreferences.getString("themepref", "dark")
        val item = names[position]
        val mequran =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), "Taha.ttf")
        val arabicFontsize = Integer.valueOf(fonts)
        val imageItem: ImageItem = data[position]
        holder.imageView.setImageBitmap(imageItem.image)
        names[position]
        holder.name.text = item.trans
        holder.meaning.text = item.meaning
        //  holder.name.setTypeface(prefArabicFontTypeface);
        // holder.name.setTextSize(arabicfontSize);
        holder.meaning.setTypeface(mequran)
        holder.meaning.textSize = translationfontsize.toFloat()
    }

    fun getItem(position: Int): Any {
        return namesfilter[position]
    }

    override fun getItemCount(): Int {
        return namesfilter.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                namesfilter = if (charString.isEmpty()) {
                    names
                } else {
                    val filteredList: MutableList<AllahNames> = ArrayList()
                    for (details in names) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (details.meaning.lowercase(Locale.getDefault()).contains(
                                charString.lowercase(
                                    Locale.getDefault()
                                )
                            )
                        ) {
                            filteredList.add(details)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = namesfilter
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                namesfilter = filterResults.values as ArrayList<AllahNames>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        var imageView: ImageView
        var name: TextView
        var meaning: TextView

        init {
            imageView = view.findViewById(R.id.imageView)
            name = view.findViewById(R.id.Names)
            meaning = view.findViewById(R.id.meaning)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}