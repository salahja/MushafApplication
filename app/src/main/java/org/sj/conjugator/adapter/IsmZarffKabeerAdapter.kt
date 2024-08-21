package org.sj.conjugator.adapter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import org.sj.conjugator.utilities.SharedPref
import org.sj.verbConjugation.IsmZarfMafalatun
import org.sj.verbConjugation.IsmZarfMafalun
import org.sj.verbConjugation.IsmZarfMafilun

class IsmZarffKabeerAdapter(lists: ArrayList<ArrayList<*>>, private val context: Context) :
    RecyclerView.Adapter<IsmZarffKabeerAdapter.ViewHolder>() {
    private var sharedPreferences: SharedPreferences? = null
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null
    private var sarfSagheer = lists
    private var isTraditional = false

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        isTraditional = SharedPref.SarfKabeerOthers()
        //      View view = LayoutInflater.from(parent.context!!).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view: View = if (isTraditional) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ismzarfkbtraditional, parent, false)
        } else {
            //  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zarfcolumnkabeer , parent, false);
            LayoutInflater.from(parent.context).inflate(R.layout.ismzarfcolumnkabeer, parent, false)
        }
        return ViewHolder(view)
    }

    /*
    public void onBindViewHolder(ViewHolder holder, int position) {
        //  final List sarf = sarfSagheer.get(position);
//        final String[] array = (String[]) sarfSagheer.get(position).toArray();
        //   ArrayList list = sarfSagheer.get(position);
        //    position++;
        MadhiMaroof(holder, 0);
        MudhariMaroof(holder, 1);
        MadhiMajhool(holder, 2);
        MudhariMajhool(holder, 3);
        Amar(holder, 4);

    }
*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  final List sarf = sarfSagheer.get(position);
//        final String[] array = (String[]) sarfSagheer.get(position).toArray();
        //   ArrayList list = sarfSagheer.get(position);
        //    position++;

        val mafil =sarfSagheer[0][0]
        val mafalatun =sarfSagheer[1][0]
        val mafalun = sarfSagheer[2][0]

        Zarfmafalatun(mafalatun as IsmZarfMafalatun,holder, 2)
        Zarfmafil(mafil as IsmZarfMafilun,holder, 1)
            Zarfmafal(mafalun as IsmZarfMafalun,holder, 0)


            ismzarfnumbers(holder)
            gcase(holder)
            FontSIzeSelection(holder)
            SetTypeface(holder)

    }

    private fun gcase(holder: ViewHolder) {
        val sf = SharedPref(context)
        val language = sharedPreferences!!.getString("lan", "en")
        val array: Array<String> =
            if (language == "en") context.resources.getStringArray(R.array.encase) else {
            context.resources.getStringArray(R.array.arcase)
        }
        if (isTraditional) {
            holder.nom.text = array[0]
            holder.acc.text = array[1]
            holder.gen.text = array[2]
            holder.nom1!!.text = array[0]
            holder.acc1!!.text = array[1]
            holder.gen1!!.text = array[2]
            holder.nom2!!.text = array[0]
            holder.acc2!!.text = array[1]
            holder.gen2!!.text = array[2]
        } else {
            holder.nom.text = array[0]
            holder.acc.text = array[1]
            holder.gen.text = array[2]
        }
    }

    private fun ismzarfnumbers(holder: ViewHolder) {
        val sf = SharedPref(context)
        val language = sharedPreferences!!.getString("lan", "en")
        val array: Array<String> =
            if (language == "en") context.resources.getStringArray(R.array.ennumbers) else {
            context.resources.getStringArray(R.array.arnumbers)
        }
        if (isTraditional) {
            holder.sin1.text = array[0]
            holder.dual1.text = array[1]
            holder.plu1.text = array[2]
            holder.sin2.text = array[0]
            holder.dual2.text = array[1]
            holder.plu2.text = array[2]
            holder.sin3.text = array[0]
            holder.dual3.text = array[1]
            holder.plu3.text = array[2]
        } else {
            holder.sin1.text = array[0]
            holder.dual1.text = array[1]
            holder.plu1.text = array[2]
            holder.sin2.text = array[0]
            holder.dual2.text = array[1]
            holder.plu2.text = array[2]
            holder.sin3.text = array[0]
            holder.dual3.text = array[1]
            holder.plu3.text = array[2]
        }
    }

    private fun Zarfmafal(mafil: IsmZarfMafalun, holder: ViewHolder, position: Int) {
        val mafalunone =  mafil.nomsinMafalun
        val mafaluntwo = mafil.nomdualMafalun
        val mafalunthree = "" //String smafalunthree);
        val mafalunfour = mafil.accsinMafalun
        val mafalunfive =  mafil.accdualMafalun
        val mafalunsix = "" //String smafalunsix);
        val mafalunseven =  mafil.gensinMafalun
        val mafaluneight =  mafil.gendualMafalun
        val mafalunnine = "" //mafalunnine);
        holder.mafalunone.text = mafalunone
        holder.mafaluntwo.text = mafaluntwo
        holder.mafalunthree.text = mafalunthree
        holder.mafalunfour.text = mafalunfour
        holder.mafalunfive.text = mafalunfive
        holder.mafalunsix.text = mafalunsix
        holder.mafalunseven.text = mafalunseven
        holder.mafaluneight.text = mafaluneight
        holder.mafalunnine.text = mafalunnine
    }

    private fun Zarfmafil(mafil: IsmZarfMafilun, holder: ViewHolder, position: Int) {
        val zarfmafalunone =mafil.nomsinMafilun  //[0].toString() //String zarfmafalunone);
        val zarfmafaluntwo =mafil.nomdualMafilun  //[1].toString() //String zarfmafaluntwo);
        val zarfmafalunthree = "-" //String zarfmafalunthree);
        val zarfmafalunfour =mafil.accsinMafilun //[2].toString() //String zarfmafalunfour);
        val zarfmafalunfive =mafil. accdualMafilun //[3].toString() //String zarfmafalunfive);
        val zarfmafalunsix = "-" //String zarfmafalunsix);
        val zarfmafalunseven =mafil.gensinMafilun  //[4].toString() //String zarfmafalunseven);
        val zarfmafaluneight =mafil.gendualMafilun  //[5].toString() //String zarfmafaluneight);
        val zarfmafalunnine = "-" //mifalatunnine);
        holder.zarfmafalunone.text = zarfmafalunone
        holder.zarfmafaluntwo.text = zarfmafaluntwo
        holder.zarfmafalunthree.text = zarfmafalunthree
        holder.zarfmafalunfour.text = zarfmafalunfour
        holder.zarfmafalunfive.text = zarfmafalunfive
        holder.zarfmafalunsix.text = zarfmafalunsix
        holder.zarfmafalunseven.text = zarfmafalunseven
        holder.zarfmafaluneight.text = zarfmafaluneight
        holder.zarfmafalunnine.text = zarfmafalunnine
    }

    private fun Zarfmafalatun(mafalun: IsmZarfMafalatun, holder: ViewHolder, position: Int) {
        val zarffemone =mafalun.nomsinMafalatun  //[0].toString() //String zarffemone);
        val zarffemtwo =mafalun.nomdualMafalatun //[1].toString() //String zarffemtwo);
        val zarffemthree = "-" //String zarffemthree);
        val zarffemfour =mafalun.accsinMafalatun  //[2].toString() //String zarffemfour);
        val zarffemfive =mafalun.accdualMafalatun  //[3].toString() //String zarffemfive);
        val zarffemsix = "-" //String zarffemsix);
        val zarffemseven =mafalun.gensinMafalatun  //[4].toString() //String zarffemseven);
        val zarffemeight =mafalun.gendualMafalatun //[5].toString() //String zarffemeight);
        val zarffemnine = "-" //mifalatunnine);
        holder.zarffemone.text = zarffemone
        holder.zarffemtwo.text = zarffemtwo
        holder.zarffemthree.text = zarffemthree
        holder.zarffemfour.text = zarffemfour
        holder.zarffemfive.text = zarffemfive
        holder.zarffemsix.text = zarffemsix
        holder.zarffemseven.text = zarffemseven
        holder.zarffemeight.text = zarffemeight
        holder.zarffemnine.text = zarffemnine
    }

    private fun FontSIzeSelection(holder: ViewHolder) {
        val defaultfont = sharedPreferences!!.getBoolean("default_font", true)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        val width = sharedPreferences.getString("width", "compactWidth")
        val arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (width == "mediumWidth" || width == "expandedWidth") {
            if (isTraditional) {
                holder.nom.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.acc.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.gen.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.nom1!!.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.acc1!!.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.gen1!!.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.nom2!!.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.acc2!!.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.gen2!!.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.sin1.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.dual1.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.plu1.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.sin2.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.plu2.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.sin3.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.dual3.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.plu3.textSize = arabicFontsize.toFloat() //(array[2]);
            } else {
                holder.nom.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.acc.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.gen.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.sin1.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.dual1.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.plu1.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.sin2.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.plu2.textSize = arabicFontsize.toFloat() //(array[2]);
                holder.sin3.textSize = arabicFontsize.toFloat() //(array[0]);
                holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                holder.dual3.textSize = arabicFontsize.toFloat() //(array[2]);
            }
            holder.mafalunone.textSize = arabicFontsize.toFloat() //;//(mafalunone);
            holder.mafaluntwo.textSize = arabicFontsize.toFloat() //;//(mafaluntwo);
            holder.mafalunthree.textSize = arabicFontsize.toFloat() //;//(mafalunthree);
            holder.mafalunfour.textSize = arabicFontsize.toFloat() //;//(mafalunfour);
            holder.mafalunfive.textSize = arabicFontsize.toFloat() //;//(mafalunfive);
            holder.mafalunsix.textSize = arabicFontsize.toFloat() //;//(mafalunsix);
            holder.mafalunseven.textSize = arabicFontsize.toFloat() //;//(mafalunseven);
            holder.mafaluneight.textSize = arabicFontsize.toFloat() //;//(mafaluneight);
            holder.mafalunnine.textSize = arabicFontsize.toFloat() //;//(mafalunnine);
            holder.zarfmafalunone.textSize = arabicFontsize.toFloat() //;//(zarfmafalunone);
            holder.zarfmafaluntwo.textSize = arabicFontsize.toFloat() //;//(zarfmafaluntwo);
            holder.zarfmafalunthree.textSize = arabicFontsize.toFloat() //;//(zarfmafalunthree);
            holder.zarfmafalunfour.textSize = arabicFontsize.toFloat() //;//(zarfmafalunfour);
            holder.zarfmafalunfive.textSize = arabicFontsize.toFloat() //;//(zarfmafalunfive);
            holder.zarfmafalunsix.textSize = arabicFontsize.toFloat() //;//(zarfmafalunsix);
            holder.zarfmafalunseven.textSize = arabicFontsize.toFloat() //;//(zarfmafalunseven);
            holder.zarfmafaluneight.textSize = arabicFontsize.toFloat() //;//(zarfmafaluneight);
            holder.zarfmafalunnine.textSize = arabicFontsize.toFloat() //;//(zarfmafalunnine);
            holder.zarffemone.textSize = arabicFontsize.toFloat() //;//(zarffemone);
            holder.zarffemtwo.textSize = arabicFontsize.toFloat() //;//(zarffemtwo);
            holder.zarffemthree.textSize = arabicFontsize.toFloat() //;//(zarffemthree);
            holder.zarffemfour.textSize = arabicFontsize.toFloat() //;//(zarffemfour);
            holder.zarffemfive.textSize = arabicFontsize.toFloat() //;//(zarffemfive);
            holder.zarffemsix.textSize = arabicFontsize.toFloat() //;//(zarffemsix);
            holder.zarffemseven.textSize = arabicFontsize.toFloat() //;//(zarffemseven);
            holder.zarffemeight.textSize = arabicFontsize.toFloat() //;//(zarffemeight);
            holder.zarffemnine.textSize = arabicFontsize.toFloat() //;//(zarfmafalunnine);
        }
    }

    private fun SetTypeface(holder: ViewHolder) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        //  String theme = sharedPreferences.getString("theme", 1);
        val indictive = sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val arabicTypeface = Typeface.createFromAsset(context.assets, indictive)
        holder.nom.setTypeface(arabicTypeface, Typeface.BOLD) //(array[0]);
        holder.nom.setTypeface(arabicTypeface, Typeface.BOLD)
        //(array[1]);
        holder.nom.setTypeface(arabicTypeface, Typeface.BOLD)
        //(array[2]);
        holder.sin1.setTypeface(arabicTypeface, Typeface.BOLD) // (array[0]);
        holder.dual1.setTypeface(arabicTypeface, Typeface.BOLD) // (array[1]);
        holder.plu1.setTypeface(arabicTypeface, Typeface.BOLD) // (array[2]);
        holder.sin2.setTypeface(arabicTypeface, Typeface.BOLD) // (array[0]);
        holder.dual2.setTypeface(arabicTypeface, Typeface.BOLD) // (array[1]);
        holder.plu2.setTypeface(arabicTypeface, Typeface.BOLD) // (array[2]);
        holder.sin3.setTypeface(arabicTypeface, Typeface.BOLD) // (array[0]);
        holder.dual2.setTypeface(arabicTypeface, Typeface.BOLD) // (array[1]);
        holder.dual3.setTypeface(arabicTypeface, Typeface.BOLD) // (array[2]);
        holder.mafalunone.typeface = arabicTypeface //(mafalunone);
        holder.mafaluntwo.typeface = arabicTypeface //(mafaluntwo);
        holder.mafalunthree.typeface = arabicTypeface //(mafalunthree);
        holder.mafalunfour.typeface = arabicTypeface //(mafalunfour);
        holder.mafalunfive.typeface = arabicTypeface //(mafalunfive);
        holder.mafalunsix.typeface = arabicTypeface //(mafalunsix);
        holder.mafalunseven.typeface = arabicTypeface //(mafalunseven);
        holder.mafaluneight.typeface = arabicTypeface //(mafaluneight);
        holder.mafalunnine.typeface = arabicTypeface //(mafalunnine);
        holder.zarfmafalunone.typeface = arabicTypeface //(zarfmafalunone);
        holder.zarfmafaluntwo.typeface = arabicTypeface //(zarfmafaluntwo);
        holder.zarfmafalunthree.typeface = arabicTypeface //(zarfmafalunthree);
        holder.zarfmafalunfour.typeface = arabicTypeface //(zarfmafalunfour);
        holder.zarfmafalunfive.typeface = arabicTypeface //(zarfmafalunfive);
        holder.zarfmafalunsix.typeface = arabicTypeface //(zarfmafalunsix);
        holder.zarfmafalunseven.typeface = arabicTypeface //(zarfmafalunseven);
        holder.zarfmafaluneight.typeface = arabicTypeface //(zarfmafaluneight);
        holder.zarfmafalunnine.typeface = arabicTypeface //(zarfmafalunnine);
        holder.zarffemone.typeface = arabicTypeface //(zarffemone);
        holder.zarffemtwo.typeface = arabicTypeface //(zarffemtwo);
        holder.zarffemthree.typeface = arabicTypeface //(zarffemthree);
        holder.zarffemfour.typeface = arabicTypeface //(zarffemfour);
        holder.zarffemfive.typeface = arabicTypeface //(zarffemfive);
        holder.zarffemsix.typeface = arabicTypeface //(zarffemsix);
        holder.zarffemseven.typeface = arabicTypeface //(zarffemseven);
        holder.zarffemeight.typeface = arabicTypeface //(zarffemeight);
        holder.zarffemnine.typeface = arabicTypeface //(zarfmafalunnine);
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer.size.toLong()
    }

    fun getItem(position: Int): Any {
        return sarfSagheer[position]
    }

    override fun getItemCount(): Int {
        //  return sarfSagheer.size();
        return 1
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        //  public final TextView ayah_number;
        val mafalunone: TextView
        val mafaluntwo: TextView
        val mafalunthree: TextView
        val mafalunfour: TextView
        val mafalunfive: TextView
        val mafalunsix: TextView
        val mafalunseven: TextView
        val mafaluneight: TextView
        val mafalunnine: TextView
        val zarfmafalunone: TextView
        val zarfmafaluntwo: TextView
        val zarfmafalunthree: TextView
        val zarfmafalunfour: TextView
        val zarfmafalunfive: TextView
        val zarfmafalunsix: TextView
        val zarfmafalunseven: TextView
        val zarfmafaluneight: TextView
        val zarfmafalunnine: TextView
        val zarffemone: TextView
        val zarffemtwo: TextView
        val zarffemthree: TextView
        val zarffemfour: TextView
        val zarffemfive: TextView
        val zarffemsix: TextView
        val zarffemseven: TextView
        val zarffemeight: TextView
        val zarffemnine: TextView
        val sin1: TextView
        val dual1: TextView
        val plu1: TextView
        val sin2: TextView
        val dual2: TextView
        val plu2: TextView
        val sin3: TextView
        val dual3: TextView
        val plu3: TextView
        val nom: TextView
        val acc: TextView
        val gen: TextView
        val nom1: TextView?
        val acc1: TextView?
        val gen1: TextView?
        val nom2: TextView?
        val acc2: TextView?
        val gen2: TextView?

        init {
            nom = view.findViewById(R.id.nominative)
            acc = view.findViewById(R.id.accusative)
            gen = view.findViewById(R.id.genitive)
            nom1 = view.findViewById(R.id.nominative1)
            acc1 = view.findViewById(R.id.accusative1)
            gen1 = view.findViewById(R.id.genitive1)
            nom2 = view.findViewById(R.id.nominative2)
            acc2 = view.findViewById(R.id.accusative2)
            gen2 = view.findViewById(R.id.genitive2)
            sin1 = view.findViewById(R.id.singular1)
            dual1 = view.findViewById(R.id.dual1)
            plu1 = view.findViewById(R.id.plural1)
            sin2 = view.findViewById(R.id.singular2)
            dual2 = view.findViewById(R.id.dual2)
            plu2 = view.findViewById(R.id.plural2)
            sin3 = view.findViewById(R.id.singular3)
            dual3 = view.findViewById(R.id.dual3)
            plu3 = view.findViewById(R.id.plural3)
            mafalunone = view.findViewById(R.id.mafalunone)
            mafaluntwo = view.findViewById(R.id.mafaluntwo)
            mafalunthree = view.findViewById(R.id.mafalunthree)
            mafalunfour = view.findViewById(R.id.mafalunfour)
            mafalunfive = view.findViewById(R.id.mafalunfive)
            mafalunsix = view.findViewById(R.id.mafalunsix)
            mafalunseven = view.findViewById(R.id.mafalunseven)
            mafaluneight = view.findViewById(R.id.mafaluneight)
            mafalunnine = view.findViewById(R.id.mafalunnine)
            zarffemone = view.findViewById(R.id.zarffemone)
            zarffemtwo = view.findViewById(R.id.zarffemtwo)
            zarffemthree = view.findViewById(R.id.zarffemthree)
            zarffemfour = view.findViewById(R.id.zarffemfour)
            zarffemfive = view.findViewById(R.id.zarffemfive)
            zarffemsix = view.findViewById(R.id.zarffemsix)
            zarffemseven = view.findViewById(R.id.zarffemseven)
            zarffemeight = view.findViewById(R.id.zarffemeight)
            zarffemnine = view.findViewById(R.id.zarffemnine)
            zarfmafalunone = view.findViewById(R.id.zarfmafalunone)
            zarfmafaluntwo = view.findViewById(R.id.zarfmafaluntwo)
            zarfmafalunthree = view.findViewById(R.id.zarfmafalunthree)
            zarfmafalunfour = view.findViewById(R.id.zarfmafalunfour)
            zarfmafalunfive = view.findViewById(R.id.zarfmafalunfive)
            zarfmafalunsix = view.findViewById(R.id.zarfmafalunsix)
            zarfmafalunseven = view.findViewById(R.id.zarfmafalunseven)
            zarfmafaluneight = view.findViewById(R.id.zarfmafaluneight)
            zarfmafalunnine = view.findViewById(R.id.zarfmafalunnine)
            view.setOnClickListener(this) // current clickListerner
            view.setOnClickListener(this) // current clickListerner
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}