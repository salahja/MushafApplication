package org.sj.conjugator.adapter

import android.content.Context
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import org.sj.conjugator.utilities.SharedPref
import org.sj.verbConjugation.AmrNahiAmr
import org.sj.verbConjugation.MadhiMudharay

class VerbSarfKabeerAdapter(
    private var sarfSagheer: ArrayList<ArrayList<*>>,
    private val context: Context
) :
    RecyclerView.Adapter<VerbSarfKabeerAdapter.ViewHolder>() {
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null
    private var arabicTypeface: Typeface? = null
    private var defaultfont = false
    private var aBoolean = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(
                QuranGrammarApplication.context
            )
        defaultfont = prefs.getBoolean("default_font", true)
        aBoolean = prefs.getBoolean("sarfkabeer_format_verb", true)
        val view: View = if (aBoolean) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.newverbsarfkabeertraditional, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.verbscolumnkabeer, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        arabicTypeface = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )
        val madhimudhary = sarfSagheer[0]
        val faelmafool = sarfSagheer[1]
        val amrandnahi = sarfSagheer[2]

        val madhi=madhimudhary[0]
        val madhimajhool=madhimudhary[1]
        val mudharaymaroof=madhimudhary[2]
        val mudharaymajhool=madhimudhary[3]

        val ismfael=faelmafool[0]
        val ismmafool=faelmafool[1]
        val amr=amrandnahi[0]
        val nahiamr=amrandnahi[1]




        this.MadhiMaroof(madhi as MadhiMudharay,holder, 0)
        MudhariMaroof(mudharaymaroof as MadhiMudharay,holder, 2)
        MadhiMajhool(madhimajhool as MadhiMudharay,holder, 1)
        MudhariMajhool(mudharaymajhool as MadhiMudharay,holder, 3)
      Amar(amrandnahi[0] as AmrNahiAmr,holder, 4)
   AmarNahi(amrandnahi[1] as AmrNahiAmr,holder, 5)
        if (!aBoolean) {
            pronouns(holder)
        }
    }

    private fun pronouns(holder: ViewHolder) {
        val array = context.resources.getStringArray(R.array.arabicpronouns)
        holder.huaid!!.text = array[0]
        holder.humamid!!.text = array[1]
        holder.humid!!.text = array[2]
        holder.hiaid!!.text = array[3]
        holder.humafid!!.text = array[4]
        holder.hunnaid!!.text = array[5]
        holder.antaid!!.text = array[6]
        holder.antumamid!!.text = array[7]
        holder.antumid!!.text = array[8]
        holder.antiid!!.text = array[9]
        holder.antumafid!!.text = array[10]
        holder.antunnaid!!.text = array[11]
        holder.anaid!!.text = array[12]
        holder.nahnuid!!.text = array[13]
        holder.huaid.typeface = arabicTypeface //(array[0]);
        holder.humamid.typeface = arabicTypeface //(array[1]);
        holder.humid.typeface = arabicTypeface //(array[2]);
        holder.hiaid.typeface = arabicTypeface //(array[3]);
        holder.humafid.typeface = arabicTypeface //(array[4]);
        holder.hunnaid.typeface = arabicTypeface //(array[5]);
        holder.antaid.typeface = arabicTypeface //(array[6]);
        holder.antumamid.typeface = arabicTypeface //(array[7]);
        holder.antumid.typeface = arabicTypeface //(array[8]);
        holder.antiid.typeface = arabicTypeface //(array[9]);
        holder.antumafid.typeface = arabicTypeface //(array[10]);
        holder.antunnaid.typeface = arabicTypeface //(array[11]);
        holder.anaid.typeface = arabicTypeface //(array[12]);
        holder.nahnuid.typeface = arabicTypeface //(array[13]);
    }

    private fun AmarNahi(amr: AmrNahiAmr, holder: ViewHolder, position: Int) {
        val anta: String
        val antuma: String
        val antum: String
        val anti: String
        val antumaf: String
        val antunna: String
        val sf = SharedPref(
            context
        )
        val isTraditional = SharedPref.GetSarfKabeerVerb()
        val arraypronouns = context.resources.getStringArray(R.array.arabicpronouns)
        var sb: StringBuilder
        if (isTraditional) {
            sb = StringBuilder()
            sb.append(arraypronouns[6])
            sb.append("-")
            sb.append(amr.anta)
            anta = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(amr.antuma)
            antuma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[8])
            sb.append("-")
            sb.append(amr.antum)
            antum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[9])
            sb.append("-")
            sb.append(amr.anti)
            anti = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(amr.antumaf)
            antumaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[11])
            sb.append("-")
            sb.append(amr.antunna)
            antunna = sb.toString()
        } else {
            anta = amr.anta.toString()//[0].toString()
            antuma = amr.antuma.toString()//[2].toString()
            antum = amr.antum.toString()//[3].toString()
            anti = amr.anti.toString()//[1].toString()
            antumaf = amr.antumaf.toString()//[2].toString()
            antunna = amr.antunna.toString()//[4].toString()
        }
        holder.nahiamranta.typeface = arabicTypeface
        holder.nahiamrantuma.typeface = arabicTypeface
        holder.nahiamrantum.typeface = arabicTypeface
        holder.nahiamranti.typeface = arabicTypeface
        holder.nahiamrantumaf.typeface = arabicTypeface
        holder.nahiamrantunna.typeface = arabicTypeface
        holder.nahiamranta.text = anta
        holder.nahiamrantuma.text = antuma
        holder.nahiamrantum.text = antum
        holder.nahiamranti.text = anti
        holder.nahiamrantumaf.text = antumaf
        holder.nahiamrantunna.text = antunna
    }

    private fun Amar(amr: AmrNahiAmr, holder: ViewHolder, position: Int) {
        val anta: String
        val antuma: String
        val antum: String
        val anti: String
        val antumaf: String
        val antunna: String
        val sf = SharedPref(
            context
        )
        val isTraditional = SharedPref.GetSarfKabeerVerb()
        val arraypronouns = context.resources.getStringArray(R.array.arabicpronouns)
        var sb: StringBuilder
        if (isTraditional) {
            sb = StringBuilder()
            sb.append(arraypronouns[6])
            sb.append("-")
            sb.append(amr.anta)
            anta = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(amr.antuma)
            antuma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[8])
            sb.append("-")
            sb.append(amr.antum)
            antum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[9])
            sb.append("-")
            sb.append(amr.anti)
            anti = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(amr.antumaf)
            antumaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[11])
            sb.append("-")
            sb.append(amr.antunna)
            antunna = sb.toString()
        } else {
            anta = amr.anta.toString()//[0].toString()
            antuma = amr.antuma.toString()//[2].toString()
            antum = amr.antum.toString()//[3].toString()
            anti = amr.anti.toString()//[1].toString()
            antumaf = amr.antumaf.toString()//[2].toString()
            antunna = amr.antunna.toString()//[4].toString()
        }
        holder.amranta.typeface = arabicTypeface
        holder.amrantuma.typeface = arabicTypeface
        holder.amrantum.typeface = arabicTypeface
        holder.amranti.typeface = arabicTypeface
        holder.amrantumaf.typeface = arabicTypeface
        holder.amrantunna.typeface = arabicTypeface
        holder.amranta.text = anta
        holder.amrantuma.text = antuma
        holder.amrantum.text = antum
        holder.amranti.text = anti
        holder.amrantumaf.text = antumaf
        holder.amrantunna.text = antunna
    }

    private fun MudhariMajhool(mudharaymajhool: MadhiMudharay, holder: ViewHolder, position: Int) {
        val hua: String
        val huma: String
        val hum: String
        val hia: String
        val humaf: String
        val hunna: String
        val anta: String
        val antuma: String
        val antum: String
        val anti: String
        val antumaf: String
        val antunna: String
        val ana: String
        val nahnu: String
        val sf = SharedPref(
            context
        )
        val isTraditional = SharedPref.GetSarfKabeerVerb()
        val arraypronouns = context.resources.getStringArray(R.array.arabicpronouns)
        var sb: StringBuilder
        if (isTraditional) {
            sb = StringBuilder()
            sb.append(arraypronouns[0])
            sb.append("-")
            sb.append(mudharaymajhool.hua.toString().replace("[", "").replace("]", ""))//[0].toString())
            hua = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[1])
            sb.append("-")
            sb.append(mudharaymajhool.huma.toString())//[1].toString())
            huma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[2])
            sb.append("-")
            sb.append(mudharaymajhool.hum.toString())//[2].toString())
            hum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[3])
            sb.append("-")
            sb.append(mudharaymajhool.hia.toString())//[3].toString())
            hia = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[4])
            sb.append("-")
            sb.append(mudharaymajhool.humaf.toString())//[4].toString())
            humaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[5])
            sb.append("-")
            sb.append(mudharaymajhool.hunna.toString())//[5].toString())
            hunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[6])
            sb.append("-")
            sb.append(mudharaymajhool.anta.toString())//[6].toString())
            anta = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(mudharaymajhool.antuma.toString())//[7].toString())
            antuma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[8])
            sb.append("-")
            sb.append(mudharaymajhool.antum.toString())//[8].toString())
            antum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[9])
            sb.append("-")
            sb.append(mudharaymajhool.anti.toString())//[9].toString())
            anti = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(mudharaymajhool.antumaf.toString())//[7].toString())
            antumaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[11])
            sb.append("-")
            sb.append(mudharaymajhool.antunna.toString())//[10].toString())
            antunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[12])
            sb.append("-")
            sb.append(mudharaymajhool.ana.toString())//[11].toString())
            ana = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[13])
            sb.append("-")
            sb.append(mudharaymajhool.nahnu.toString().replace("[", "").replace("]", ""))//[12].toString())
            nahnu = sb.toString()
        } else {
            hua = mudharaymajhool.hua.toString().replace("[", "").replace("]", "")
            huma =mudharaymajhool.huma.toString()
            hum = mudharaymajhool.hum.toString()
            hia =mudharaymajhool.hia.toString()//[position][3].toString()
            humaf =mudharaymajhool.humaf.toString()//[position][4].toString()
            hunna =mudharaymajhool.hunna.toString()//[position][5].toString()
            anta =mudharaymajhool.anta.toString()//[position][6].toString()
            antuma =mudharaymajhool.antuma.toString()//[position][7].toString()
            antum =mudharaymajhool.antum.toString()//[position][8].toString()
            anti =mudharaymajhool.anti.toString()//[position][9].toString()
            antumaf =mudharaymajhool.antumaf.toString()//[position][7].toString()
            antunna =mudharaymajhool.antunna.toString()//[position][10].toString()
            ana =mudharaymajhool.ana.toString()//[position][11].toString()
            nahnu =mudharaymajhool.nahnu.toString().replace("[", "").replace("]", "")//[position][12].toString()
        }
        //     FontSIzeSelection(holder);
//ismfaile
        //   SharedPref.arabicFontSelection();
        holder.muzmajhua.typeface = arabicTypeface
        holder.muzmajhuma.typeface = arabicTypeface
        holder.muzmajhum.typeface = arabicTypeface
        holder.muzmajhia.typeface = arabicTypeface
        holder.muzmajhumaf.typeface = arabicTypeface
        holder.muzmajhunna.typeface = arabicTypeface
        holder.muzmajanta.typeface = arabicTypeface
        holder.muzmajantuma.typeface = arabicTypeface
        holder.muzmajantum.typeface = arabicTypeface
        holder.muzmajanti.typeface = arabicTypeface
        holder.muzmajantumaf.typeface = arabicTypeface
        holder.muzmajantunna.typeface = arabicTypeface
        holder.muzmajana.typeface = arabicTypeface
        holder.muzmajnahnu.typeface = arabicTypeface
        holder.muzmajhua.text = hua
        holder.muzmajhuma.text = huma
        holder.muzmajhum.text = hum
        holder.muzmajhia.text = hia
        holder.muzmajhumaf.text = humaf
        holder.muzmajhunna.text = hunna
        holder.muzmajanta.text = anta
        holder.muzmajantuma.text = antuma
        holder.muzmajantum.text = antum
        holder.muzmajanti.text = anti
        holder.muzmajantumaf.text = antumaf
        holder.muzmajantunna.text = antunna
        holder.muzmajana.text = ana
        //
        holder.muzmajnahnu.text = nahnu
        FontSIzeSelection(holder)
    }

    private fun FontSIzeSelection(holder: ViewHolder) {
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )
        val width = sharedPreferences.getString("width", "compactWidth")
        val arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (!defaultfont) {
            if (width == "mediumWidth" || width == "expandedWidth") {
                //   final Integer arabicFontsize = SharedPref.arabicFontsize();
                holder.muzhua.textSize = arabicFontsize.toFloat()
                holder.muzhuma.textSize = arabicFontsize.toFloat()
                holder.muzhum.textSize = arabicFontsize.toFloat()
                holder.muzhia.textSize = arabicFontsize.toFloat()
                holder.muzhumaf.textSize = arabicFontsize.toFloat()
                holder.muzhunna.textSize = arabicFontsize.toFloat()
                holder.muzanta.textSize = arabicFontsize.toFloat()
                holder.muzantuma.textSize = arabicFontsize.toFloat()
                holder.muzantum.textSize = arabicFontsize.toFloat()
                holder.muzanti.textSize = arabicFontsize.toFloat()
                holder.muzantumaf.textSize = arabicFontsize.toFloat()
                holder.muzantunna.textSize = arabicFontsize.toFloat()
                holder.muzana.textSize = arabicFontsize.toFloat()
                holder.muznahnu.textSize = arabicFontsize.toFloat()
                holder.muzmajhua.textSize = arabicFontsize.toFloat()
                holder.muzmajhuma.textSize = arabicFontsize.toFloat()
                holder.muzmajhum.textSize = arabicFontsize.toFloat()
                holder.muzmajhia.textSize = arabicFontsize.toFloat()
                holder.muzmajhumaf.textSize = arabicFontsize.toFloat()
                holder.muzmajhunna.textSize = arabicFontsize.toFloat()
                holder.muzmajanta.textSize = arabicFontsize.toFloat()
                holder.muzmajantuma.textSize = arabicFontsize.toFloat()
                holder.muzmajantum.textSize = arabicFontsize.toFloat()
                holder.muzmajanti.textSize = arabicFontsize.toFloat()
                holder.muzmajantumaf.textSize = arabicFontsize.toFloat()
                holder.muzmajantunna.textSize = arabicFontsize.toFloat()
                holder.muzmajana.textSize = arabicFontsize.toFloat()
                holder.muzmajnahnu.textSize = arabicFontsize.toFloat()
                holder.madhihua.textSize = arabicFontsize.toFloat()
                holder.madhihuma.textSize = arabicFontsize.toFloat()
                holder.madhihum.textSize = arabicFontsize.toFloat()
                holder.madhihia.textSize = arabicFontsize.toFloat()
                holder.madhihumaf.textSize = arabicFontsize.toFloat()
                holder.madhihunna.textSize = arabicFontsize.toFloat()
                holder.madhianta.textSize = arabicFontsize.toFloat()
                holder.madhiantuma.textSize = arabicFontsize.toFloat()
                holder.madhiantum.textSize = arabicFontsize.toFloat()
                holder.madhianti.textSize = arabicFontsize.toFloat()
                holder.madhiantunna.textSize = arabicFontsize.toFloat()
                holder.madhiantumaf.textSize = arabicFontsize.toFloat()
                holder.madhiana.textSize = arabicFontsize.toFloat()
                holder.madhinahnu.textSize = arabicFontsize.toFloat()
                holder.madimajhua.textSize = arabicFontsize.toFloat()
                holder.madimajhuma.textSize = arabicFontsize.toFloat()
                holder.madimajhum.textSize = arabicFontsize.toFloat()
                holder.madimajhia.textSize = arabicFontsize.toFloat()
                holder.madimajhumaf.textSize = arabicFontsize.toFloat()
                holder.madimajhunna.textSize = arabicFontsize.toFloat()
                holder.madimajanta.textSize = arabicFontsize.toFloat()
                holder.madimajantuma.textSize = arabicFontsize.toFloat()
                holder.madimajantum.textSize = arabicFontsize.toFloat()
                holder.madimajanti.textSize = arabicFontsize.toFloat()
                holder.madimajantumaf.textSize = arabicFontsize.toFloat()
                holder.madimajantunna.textSize = arabicFontsize.toFloat()
                holder.madimajana.textSize = arabicFontsize.toFloat()
                holder.madimajnahnu.textSize = arabicFontsize.toFloat()
                holder.amranta.textSize = arabicFontsize.toFloat()
                holder.amrantuma.textSize = arabicFontsize.toFloat()
                holder.amrantum.textSize = arabicFontsize.toFloat()
                holder.amranti.textSize = arabicFontsize.toFloat()
                holder.amrantumaf.textSize = arabicFontsize.toFloat()
                holder.amrantunna.textSize = arabicFontsize.toFloat()
                holder.nahiamranta.textSize = arabicFontsize.toFloat()
                holder.nahiamrantuma.textSize = arabicFontsize.toFloat()
                holder.nahiamrantum.textSize = arabicFontsize.toFloat()
                holder.nahiamranti.textSize = arabicFontsize.toFloat()
                holder.nahiamrantumaf.textSize = arabicFontsize.toFloat()
                holder.nahiamrantunna.textSize = arabicFontsize.toFloat()
                if (!aBoolean) {
                    holder.huaid!!.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.humamid!!.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.humid!!.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.hiaid!!.textSize = arabicFontsize.toFloat() //(array[3]);
                    holder.humafid!!.textSize = arabicFontsize.toFloat() //(array[4]);
                    holder.hunnaid!!.textSize = arabicFontsize.toFloat() //(array[5]);
                    holder.antaid!!.textSize = arabicFontsize.toFloat() //(array[6]);
                    holder.antumamid!!.textSize = arabicFontsize.toFloat() //(array[7]);
                    holder.antumid!!.textSize = arabicFontsize.toFloat() //(array[8]);
                    holder.antiid!!.textSize = arabicFontsize.toFloat() //(array[9]);
                    holder.antumafid!!.textSize = arabicFontsize.toFloat() //(array[10]);
                    holder.antunnaid!!.textSize = arabicFontsize.toFloat() //(array[11]);
                    holder.anaid!!.textSize = arabicFontsize.toFloat() //(array[12]);
                    holder.nahnuid!!.textSize = arabicFontsize.toFloat() //(array[13]);
                }
            }
        }
    }

    private fun MadhiMajhool(madhimajhool : MadhiMudharay,   holder: ViewHolder, position: Int) {
        val hua: String
        val huma: String
        val hum: String
        val hia: String
        val humaf: String
        val hunna: String
        val anta: String
        val antuma: String
        val antum: String
        val anti: String
        val antumaf: String
        val antunna: String
        val ana: String
        val nahnu: String
        val sf = SharedPref(
            context
        )
        val isTraditional = SharedPref.GetSarfKabeerVerb()
        val arraypronouns = context.resources.getStringArray(R.array.arabicpronouns)
        var sb: StringBuilder
        if (isTraditional) {
            sb = StringBuilder()
            sb.append(arraypronouns[0])
            sb.append("-")
            sb.append(madhimajhool.hua.toString().replace("[", "").replace("]", ""))//[0].toString())
            hua = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[1])
            sb.append("-")
            sb.append(madhimajhool.huma.toString())//[1].toString())
            huma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[2])
            sb.append("-")
            sb.append(madhimajhool.hum.toString())//[2].toString())
            hum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[3])
            sb.append("-")
            sb.append(madhimajhool.hia.toString())//[3].toString())
            hia = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[4])
            sb.append("-")
            sb.append(madhimajhool.humaf.toString())//[4].toString())
            humaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[5])
            sb.append("-")
            sb.append(madhimajhool.hunna.toString())//[5].toString())
            hunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[6])
            sb.append("-")
            sb.append(madhimajhool.anta.toString())//[6].toString())
            anta = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(madhimajhool.antuma.toString())//[7].toString())
            antuma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[8])
            sb.append("-")
            sb.append(madhimajhool.antum.toString())//[8].toString())
            antum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[9])
            sb.append("-")
            sb.append(madhimajhool.anti.toString())//[9].toString())
            anti = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(madhimajhool.antumaf.toString())//[7].toString())
            antumaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[11])
            sb.append("-")
            sb.append(madhimajhool.antunna.toString())//[10].toString())
            antunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[12])
            sb.append("-")
            sb.append(madhimajhool.ana.toString())//[11].toString())
            ana = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[13])
            sb.append("-")
            sb.append(madhimajhool.nahnu.toString().replace("[", "").replace("]", ""))//[12].toString())
            nahnu = sb.toString()
        } else {
            hua = madhimajhool.hua.toString().replace("[", "").replace("]", "")
            huma =madhimajhool.huma.toString()
            hum = madhimajhool.hum.toString()
            hia =madhimajhool.hia.toString()//[position][3].toString()
            humaf =madhimajhool.humaf.toString()//[position][4].toString()
            hunna =madhimajhool.hunna.toString()//[position][5].toString()
            anta =madhimajhool.anta.toString()//[position][6].toString()
            antuma =madhimajhool.antuma.toString()//[position][7].toString()
            antum =madhimajhool.antum.toString()//[position][8].toString()
            anti =madhimajhool.anti.toString()//[position][9].toString()
            antumaf =madhimajhool.antumaf.toString()//[position][7].toString()
            antunna =madhimajhool.antunna.toString()//[position][10].toString()
            ana =madhimajhool.ana.toString()//[position][11].toString()
            nahnu =madhimajhool.nahnu.toString().replace("[", "").replace("]", "")//[position][12].toString()
        }
        holder.madimajhua.typeface = arabicTypeface
        holder.madimajhuma.typeface = arabicTypeface
        holder.madimajhum.typeface = arabicTypeface
        holder.madimajhia.typeface = arabicTypeface
        holder.madimajhumaf.typeface = arabicTypeface
        holder.madimajhunna.typeface = arabicTypeface
        holder.madimajanta.typeface = arabicTypeface
        holder.madimajantuma.typeface = arabicTypeface
        holder.madimajantum.typeface = arabicTypeface
        holder.madimajanti.typeface = arabicTypeface
        holder.madimajantumaf.typeface = arabicTypeface
        holder.madimajantunna.typeface = arabicTypeface
        holder.madimajana.typeface = arabicTypeface
        holder.madimajnahnu.typeface = arabicTypeface
        holder.madimajhua.text = hua
        holder.madimajhuma.text = huma
        holder.madimajhum.text = hum
        holder.madimajhia.text = hia
        holder.madimajhumaf.text = humaf
        holder.madimajhunna.text = hunna
        holder.madimajanta.text = anta
        holder.madimajantuma.text = antuma
        holder.madimajantum.text = antum
        holder.madimajanti.text = anti
        holder.madimajantumaf.text = antumaf
        holder.madimajantunna.text = antunna
        holder.madimajana.text = ana
        holder.madimajnahnu.text = nahnu
    }

    private fun MudhariMaroof(mudharaymaroof: MadhiMudharay, holder: ViewHolder, position: Int) {
        val hua: String
        val huma: String
        val hum: String
        val hia: String
        val humaf: String
        val hunna: String
        val anta: String
        val antuma: String
        val antum: String
        val anti: String
        val antumaf: String
        val antunna: String
        val ana: String
        val nahnu: String
        val sf = SharedPref(
            context
        )
        val isTraditional = SharedPref.GetSarfKabeerVerb()
        val arraypronouns = context.resources.getStringArray(R.array.arabicpronouns)
        var sb: StringBuilder
        if (isTraditional) {
            sb = StringBuilder()
            sb.append(arraypronouns[0])
            sb.append("-")
            sb.append(mudharaymaroof.hua.toString().replace("[", "").replace("]", ""))//[0].toString())
            hua = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[1])
            sb.append("-")
            sb.append(mudharaymaroof.huma.toString())//[1].toString())
            huma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[2])
            sb.append("-")
            sb.append(mudharaymaroof.hum.toString())//[2].toString())
            hum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[3])
            sb.append("-")
            sb.append(mudharaymaroof.hia.toString())//[3].toString())
            hia = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[4])
            sb.append("-")
            sb.append(mudharaymaroof.humaf.toString())//[4].toString())
            humaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[5])
            sb.append("-")
            sb.append(mudharaymaroof.hunna.toString())//[5].toString())
            hunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[6])
            sb.append("-")
            sb.append(mudharaymaroof.anta.toString())//[6].toString())
            anta = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(mudharaymaroof.antuma.toString())//[7].toString())
            antuma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[8])
            sb.append("-")
            sb.append(mudharaymaroof.antum.toString())//[8].toString())
            antum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[9])
            sb.append("-")
            sb.append(mudharaymaroof.anti.toString())//[9].toString())
            anti = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(mudharaymaroof.antumaf.toString())//[7].toString())
            antumaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[11])
            sb.append("-")
            sb.append(mudharaymaroof.antunna.toString())//[10].toString())
            antunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[12])
            sb.append("-")
            sb.append(mudharaymaroof.ana.toString())//[11].toString())
            ana = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[13])
            sb.append("-")
            sb.append(mudharaymaroof.nahnu.toString().replace("[", "").replace("]", ""))//[12].toString())
            nahnu = sb.toString()
        } else {
            hua = mudharaymaroof.hua.toString().replace("[", "").replace("]", "")
            huma =mudharaymaroof.huma.toString()
            hum = mudharaymaroof.hum.toString()
            hia =mudharaymaroof.hia.toString()//[position][3].toString()
            humaf =mudharaymaroof.humaf.toString()//[position][4].toString()
            hunna =mudharaymaroof.hunna.toString()//[position][5].toString()
            anta =mudharaymaroof.anta.toString()//[position][6].toString()
            antuma =mudharaymaroof.antuma.toString()//[position][7].toString()
            antum =mudharaymaroof.antum.toString()//[position][8].toString()
            anti =mudharaymaroof.anti.toString()//[position][9].toString()
            antumaf =mudharaymaroof.antumaf.toString()//[position][7].toString()
            antunna =mudharaymaroof.antunna.toString()//[position][10].toString()
            ana =mudharaymaroof.ana.toString()//[position][11].toString()
            nahnu =mudharaymaroof.nahnu.toString().replace("[", "").replace("]", "")//[position][12].toString()
        }
        holder.muzhua.typeface = arabicTypeface
        holder.muzhuma.typeface = arabicTypeface
        holder.muzhum.typeface = arabicTypeface
        holder.muzhia.typeface = arabicTypeface
        holder.muzhumaf.typeface = arabicTypeface
        holder.muzhunna.typeface = arabicTypeface
        holder.muzanta.typeface = arabicTypeface
        holder.muzantuma.typeface = arabicTypeface
        holder.muzantum.typeface = arabicTypeface
        holder.muzanti.typeface = arabicTypeface
        holder.muzantumaf.typeface = arabicTypeface
        holder.muzantunna.typeface = arabicTypeface
        holder.muzana.typeface = arabicTypeface
        holder.muznahnu.typeface = arabicTypeface
        holder.muzhua.text = hua
        holder.muzhuma.text = huma
        holder.muzhum.text = hum
        holder.muzhia.text = hia
        holder.muzhumaf.text = humaf
        holder.muzhunna.text = hunna
        holder.muzanta.text = anta
        holder.muzantuma.text = antuma
        holder.muzantum.text = antum
        holder.muzanti.text = anti
        holder.muzantumaf.text = antumaf
        holder.muzantunna.text = antunna
        holder.muzana.text = ana
        holder.muznahnu.text = nahnu
    }

    private fun MadhiMaroof(madhi: MadhiMudharay, holder: ViewHolder, position: Int) {
        val hua: String
        val huma: String
        val hum: String
        val hia: String
        val humaf: String
        val hunna: String
        val anta: String
        val antuma: String
        val antum: String
        val anti: String
        val antumaf: String
        val antunna: String
        val ana: String
        val nahnu: String
        val sf = SharedPref(
            context
        )
        val isTraditional = SharedPref.GetSarfKabeerVerb()
        val arraypronouns = context.resources.getStringArray(R.array.arabicpronouns)
        var sb: StringBuilder
        if (isTraditional) {
            sb = StringBuilder()
            sb.append(arraypronouns[0])
            sb.append("-")
            sb.append(madhi.hua.toString().replace("[", "").replace("]", ""))//[0].toString())
            hua = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[1])
            sb.append("-")
            sb.append(madhi.huma.toString())//[1].toString())
            huma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[2])
            sb.append("-")
            sb.append(madhi.hum.toString())//[2].toString())
            hum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[3])
            sb.append("-")
            sb.append(madhi.hia.toString())//[3].toString())
            hia = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[4])
            sb.append("-")
            sb.append(madhi.humaf.toString())//[4].toString())
            humaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[5])
            sb.append("-")
            sb.append(madhi.hunna.toString())//[5].toString())
            hunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[6])
            sb.append("-")
            sb.append(madhi.anta.toString())//[6].toString())
            anta = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(madhi.antuma.toString())//[7].toString())
            antuma = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[8])
            sb.append("-")
            sb.append(madhi.antum.toString())//[8].toString())
            antum = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[9])
            sb.append("-")
            sb.append(madhi.anti.toString())//[9].toString())
            anti = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[7])
            sb.append("-")
            sb.append(madhi.antumaf.toString())//[7].toString())
            antumaf = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[11])
            sb.append("-")
            sb.append(madhi.antunna.toString())//[10].toString())
            antunna = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[12])
            sb.append("-")
            sb.append(madhi.ana.toString())//[11].toString())
            ana = sb.toString()
            sb = StringBuilder()
            sb.append(arraypronouns[13])
            sb.append("-")
            sb.append(madhi.nahnu.toString().replace("[", "").replace("]", ""))//[12].toString())
            nahnu = sb.toString()
        } else {
            hua = madhi.hua.toString().replace("[", "").replace("]", "")
            huma =madhi.huma.toString()
            hum = madhi.hum.toString()
            hia =madhi.hia.toString()//[position][3].toString()
            humaf =madhi.humaf.toString()//[position][4].toString()
            hunna =madhi.hunna.toString()//[position][5].toString()
            anta =madhi.anta.toString()//[position][6].toString()
            antuma =madhi.antuma.toString()//[position][7].toString()
            antum =madhi.antum.toString()//[position][8].toString()
            anti =madhi.anti.toString()//[position][9].toString()
            antumaf =madhi.antumaf.toString()//[position][7].toString()
            antunna =madhi.antunna.toString()//[position][10].toString()
            ana =madhi.ana.toString()//[position][11].toString()
            nahnu =madhi.nahnu.toString().replace("[", "").replace("]", "")//[position][12].toString()
        }
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val language = sharedPreferences.getString("lan", "en")
        val arrayheadings: Array<String> =
            if (language == "en") context.resources.getStringArray(R.array.enverbheadings) else {
                context.resources.getStringArray(R.array.arverbheadings)
            }
        //    holder.pronouns.setText(array[0]);
        holder.pastactive.text = arrayheadings[1]
        holder.presentactive.text = arrayheadings[2]
        holder.pastpassive.text = arrayheadings[3]
        holder.presentpassive.text = arrayheadings[4]
        holder.command.text = arrayheadings[5]
        holder.negcommand.text = arrayheadings[6]
        holder.madhihua.typeface = arabicTypeface
        //        holder.madhihuma.setTypeface(arabicTypeface);
        holder.madhihum.typeface = arabicTypeface
        holder.madhihia.typeface = arabicTypeface
        holder.madhihumaf.typeface = arabicTypeface
        holder.madhihunna.typeface = arabicTypeface
        holder.madhianta.typeface = arabicTypeface
        holder.madhiantuma.typeface = arabicTypeface
        holder.madhiantum.typeface = arabicTypeface
        holder.madhianti.typeface = arabicTypeface
        holder.madhiantunna.typeface = arabicTypeface
        holder.madhiantumaf.typeface = arabicTypeface
        holder.madhiana.typeface = arabicTypeface
        holder.madhinahnu.typeface = arabicTypeface
        holder.madhihua.text = hua
        holder.madhihua.text = hua
        holder.madhihuma.text = huma
        holder.madhihum.text = hum
        holder.madhihia.text = hia
        holder.madhihumaf.text = humaf
        holder.madhihunna.text = hunna
        holder.madhianta.text = anta
        holder.madhiantuma.text = antuma
        holder.madhiantum.text = antum
        holder.madhianti.text = anti
        holder.madhiantumaf.text = antumaf
        holder.madhiantunna.text = antunna
        holder.madhiana.text = ana
        holder.madhinahnu.text = nahnu
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer.size.toLong()
    }

    fun getItem(position: Int): Any {
        return sarfSagheer[position]
    }

    override fun getItemCount(): Int {
        //    return sarfSagheer.size();
        return 1
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        //  public final TextView ayah_number;
        val madhihua: TextView
        val madhihuma: TextView
        val madhihum: TextView
        val madhihia: TextView
        val madhihumaf: TextView
        val madhihunna: TextView
        val madhianta: TextView
        val madhiantuma: TextView
        val madhiantum: TextView
        val madhianti: TextView
        val madhiantumaf: TextView
        val madhiantunna: TextView
        val madhiana: TextView
        val madhinahnu: TextView
        val muzhua: TextView
        val muzhuma: TextView
        val muzhum: TextView
        val muzhia: TextView
        val muzhumaf: TextView
        val muzhunna: TextView
        val muzanta: TextView
        val muzantuma: TextView
        val muzantum: TextView
        val muzanti: TextView
        val muzantumaf: TextView
        val muzantunna: TextView
        val muzana: TextView
        val muznahnu: TextView
        val madimajhua: TextView
        val madimajhuma: TextView
        val madimajhum: TextView
        val madimajhia: TextView
        val madimajhumaf: TextView
        val madimajhunna: TextView
        val madimajanta: TextView
        val madimajantuma: TextView
        val madimajantum: TextView
        val madimajanti: TextView
        val madimajantumaf: TextView
        val madimajantunna: TextView
        val madimajana: TextView
        val madimajnahnu: TextView
        val muzmajhua: TextView
        val muzmajhuma: TextView
        val muzmajhum: TextView
        val muzmajhia: TextView
        val muzmajhumaf: TextView
        val muzmajhunna: TextView
        val muzmajanta: TextView
        val muzmajantuma: TextView
        val muzmajantum: TextView
        val muzmajanti: TextView
        val muzmajantumaf: TextView
        val muzmajantunna: TextView
        val muzmajana: TextView
        val muzmajnahnu: TextView
        val amranta: TextView
        val amrantuma: TextView
        val amrantum: TextView
        val amranti: TextView
        val amrantumaf: TextView
        val amrantunna: TextView
        val nahiamranta: TextView
        val nahiamrantuma: TextView
        val nahiamrantum: TextView
        val nahiamranti: TextView
        val nahiamrantumaf: TextView
        val nahiamrantunna: TextView
        val huaid: TextView?
        val humamid: TextView?
        val humid: TextView?
        val hiaid: TextView?
        val humafid: TextView?
        val hunnaid: TextView?
        val antaid: TextView?
        val antumamid: TextView?
        val antumid: TextView?
        val antiid: TextView?
        val antumafid: TextView?
        val antunnaid: TextView?
        val anaid: TextView?
        val nahnuid: TextView?
       // val pronouns: TextView
        val pastactive: TextView
        val presentactive: TextView
        val pastpassive: TextView
        val presentpassive: TextView
        val command: TextView
        val negcommand: TextView

        init {
          //  pronouns = view.findViewById(R.id.pronouns)
            pastactive = view.findViewById(R.id.pastactive)
            presentactive = view.findViewById(R.id.presentactive)
            pastpassive = view.findViewById(R.id.pastpassive)
            presentpassive = view.findViewById(R.id.presentpassive)
            command = view.findViewById(R.id.command)
            negcommand = view.findViewById(R.id.negcommand)
            huaid = view.findViewById(R.id.huaid)
            humamid = view.findViewById(R.id.humamid)
            humid = view.findViewById(R.id.humid)
            hiaid = view.findViewById(R.id.hiaid)
            humafid = view.findViewById(R.id.humafid)
            hunnaid = view.findViewById(R.id.hunnaid)
            antaid = view.findViewById(R.id.antaid)
            antumamid = view.findViewById(R.id.antumamid)
            antumid = view.findViewById(R.id.antumid)
            antiid = view.findViewById(R.id.antiid)
            antumafid = view.findViewById(R.id.antumafid)
            antunnaid = view.findViewById(R.id.antunnaid)
            anaid = view.findViewById(R.id.anaid)
            nahnuid = view.findViewById(R.id.nahnuid)
            madhihua = view.findViewById(R.id.madhihua)
            madhihuma = view.findViewById(R.id.madhihuma)
            madhihum = view.findViewById(R.id.madihum)
            madhihia = view.findViewById(R.id.madhihia)
            madhihumaf = view.findViewById(R.id.madhihumaf)
            madhihunna = view.findViewById(R.id.madihunna)
            madhianta = view.findViewById(R.id.madhianta)
            madhiantuma = view.findViewById(R.id.madhiantuma)
            madhiantum = view.findViewById(R.id.madhiantum)
            madhianti = view.findViewById(R.id.madhianti)
            madhiantumaf = view.findViewById(R.id.madhiantumaf)
            madhiantunna = view.findViewById(R.id.madhiantunna)
            madhiana = view.findViewById(R.id.madhiana)
            madhinahnu = view.findViewById(R.id.madhinahnu)
            muzhua = view.findViewById(R.id.muzhua)
            muzhuma = view.findViewById(R.id.muzhuma)
            muzhum = view.findViewById(R.id.muzhum)
            muzhia = view.findViewById(R.id.muzhia)
            muzhumaf = view.findViewById(R.id.muzhumaf)
            muzhunna = view.findViewById(R.id.muzhunna)
            muzanta = view.findViewById(R.id.muzanta)
            muzantuma = view.findViewById(R.id.muzantuma)
            muzantum = view.findViewById(R.id.muzantum)
            muzanti = view.findViewById(R.id.muzanti)
            muzantumaf = view.findViewById(R.id.muzantumaf)
            muzantunna = view.findViewById(R.id.muzantunna)
            muzana = view.findViewById(R.id.muzana)
            muznahnu = view.findViewById(R.id.muznahnu)
            //
            madimajhua = view.findViewById(R.id.madimajhua)
            madimajhuma = view.findViewById(R.id.madimajhuma)
            madimajhum = view.findViewById(R.id.madimajhum)
            madimajhia = view.findViewById(R.id.madimajhia)
            madimajhumaf = view.findViewById(R.id.madimajhumaf)
            madimajhunna = view.findViewById(R.id.madimajhunna)
            madimajanta = view.findViewById(R.id.madimajanta)
            madimajantuma = view.findViewById(R.id.madimajantuma)
            madimajantum = view.findViewById(R.id.madimajantum)
            madimajanti = view.findViewById(R.id.madimajanti)
            madimajantumaf = view.findViewById(R.id.madimajantumaf)
            madimajantunna = view.findViewById(R.id.madimajantunna)
            madimajana = view.findViewById(R.id.madimajana)
            madimajnahnu = view.findViewById(R.id.madimajnahnu)
            ///muzmajhool
            muzmajhua = view.findViewById(R.id.muzmajhua)
            muzmajhuma = view.findViewById(R.id.muzmajhuma)
            muzmajhum = view.findViewById(R.id.muzmajhum)
            muzmajhia = view.findViewById(R.id.muzmajhia)
            muzmajhumaf = view.findViewById(R.id.muzmajhumaf)
            muzmajhunna = view.findViewById(R.id.muzmajhunna)
            muzmajanta = view.findViewById(R.id.muzmajanta)
            muzmajantuma = view.findViewById(R.id.muzmajantuma)
            muzmajantum = view.findViewById(R.id.muzmajantum)
            muzmajanti = view.findViewById(R.id.muzmajanti)
            muzmajantumaf = view.findViewById(R.id.muzmajantumaf)
            muzmajantunna = view.findViewById(R.id.muzmajantunna)
            muzmajana = view.findViewById(R.id.muzmajana)
            muzmajnahnu = view.findViewById(R.id.muzmajnahnu)
            //
            amranta = view.findViewById(R.id.amranta)
            amrantuma = view.findViewById(R.id.amrantuma)
            amrantum = view.findViewById(R.id.amrantum)
            amranti = view.findViewById(R.id.amranti)
            amrantumaf = view.findViewById(R.id.amrantumaf)
            amrantunna = view.findViewById(R.id.amrantunna)
            nahiamranta = view.findViewById(R.id.nahiamranta)
            nahiamrantuma = view.findViewById(R.id.nahiamrantuma)
            nahiamrantum = view.findViewById(R.id.nahiamrantum)
            nahiamranti = view.findViewById(R.id.nahiamranti)
            nahiamrantumaf = view.findViewById(R.id.nahiamrantumaf)
            nahiamrantunna = view.findViewById(R.id.nahiamrantunna)
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