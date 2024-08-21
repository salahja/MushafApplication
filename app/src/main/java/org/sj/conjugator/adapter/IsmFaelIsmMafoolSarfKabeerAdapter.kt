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
import org.sj.verbConjugation.FaelMafool

class IsmFaelIsmMafoolSarfKabeerAdapter(
    lists: ArrayList<ArrayList<*>>,
    private val context: Context,
    private var newsarf: Boolean
                                       ) :
    RecyclerView.Adapter<IsmFaelIsmMafoolSarfKabeerAdapter.ViewHolder>() {
    private var sharedPreferences: SharedPreferences? = null
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null
    private var sarfSagheer = lists
    private var arabicTypeface: Typeface? = null
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

        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view: View = if (isTraditional) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.newismalasktraditional, parent, false)
        } else {
            //   view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ismfaelmafoolcolumkabeer, parent, false);
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ismfaelmafoolcolumkabeer, parent, false)
        }
        //  View view = LayoutInflater.from(parent.context!!).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  final List sarf = sarfSagheer.get(position);
        val newsarf = true

        val ismfael = sarfSagheer[0][0]
        val ismmafool = sarfSagheer[0][1]
       // val ismfael=faelmafoolMas
      //  val ismmafool=faelmafoolFem
        if (newsarf) {
            SetTypeFace(holder)
            IsmFael(ismfael as FaelMafool,holder, 0)
            IsmFaelFem(ismfael as FaelMafool,holder, 0)
            IsmMafool(ismmafool as FaelMafool, holder, 1)
            IsmMafoolFem(ismmafool as  FaelMafool, holder, 1)
        }/* else {
            SetTypeFace(holder)
            IsmFael(ismfael, holder, 6)
            IsmFaelFem(holder, 6)
            IsmMafool(holder, 7)
            IsmMafoolFem(holder, 7)
        }*/
        gcase(holder)
        ismfaelmafoolnumbers(holder)
        //      FontSIzeSelection(holder);
        val array: Array<String>
        val sf = SharedPref(context)
        val language = sharedPreferences!!.getString("lan", "en")
        array = if (language == "en") {
            context.resources.getStringArray(R.array.enismfaelmafoolheadings)
        } else {
            context.resources.getStringArray(R.array.arismfaelmafoolheadings)
        }
        holder.apmas.text = array[0]
        holder.apfem.text = array[1]
        holder.ppmas.text = array[2]
        holder.ppfem.text = array[3]
        //    IsmAlamifalmifalatun(holder,8);
        //  IsmAlaMifaal(holder,9);
        // Zarf(holder,10);
    }

    private fun gcase(holder: ViewHolder) {
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
            holder.nom3!!.text = array[0]
            holder.acc3!!.text = array[1]
            holder.gen3!!.text = array[2]
        } else {
            holder.nom.text = array[0]
            holder.acc.text = array[1]
            holder.gen.text = array[2]
        }
    }

    private fun ismfaelmafoolnumbers(holder: ViewHolder) {
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
            holder.sin4!!.text = array[0]
            holder.dual4!!.text = array[1]
            holder.plu4!!.text = array[2]
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

    private fun IsmFael(ismfael: FaelMafool, holder: ViewHolder, position: Int) {

        val iisone =  ismfael.nomsinM!!.replace("[", "").replace("]", "");//[0].toString() //isone);
        iisone.replace("[\"\\[ \\]]", "")
        val iistwo =  ismfael.nomdualM//[2].toString() //istwo);
        val iisthree =  ismfael.nomplurarM//[4].toString() //isthree);
        val iisfour =  ismfael.accsinM//[6].toString() //isfour);
        val iisfive =  ismfael.accdualM//[8].toString() //isfive);
        val iissix =  ismfael.accplurarlM//[10].toString() //issix);
        val iisseven =  ismfael.gensinM//[12].toString() //isseven);
        val iiseight =  ismfael.gendualM//[14].toString() //iseight);
        val iisnine =  ismfael.genplurarM//[16].toString() //isnine);
        //    FontSIzeSelection(holder);
        SetTypeFace(holder)
        holder.isone.text = iisone
        holder.istwo.text = iistwo
        holder.isthree.text = iisthree
        holder.isfour.text = iisfour
        holder.isfive.text = iisfive
        holder.issix.text = iissix
        holder.isseven.text = iisseven
        holder.iseight.text = iiseight
        holder.isnine.text = iisnine
    }

    private fun IsmFaelFem(ismfael : FaelMafool, holder: ViewHolder, position: Int) {

        val iisone =  ismfael.nomsinF//[0].toString() //isone);
        val iistwo =  ismfael.nomdualF//[2].toString() //istwo);
        val iisthree =  ismfael.nomplurarF//[4].toString() //isthree);
        val iisfour =  ismfael.accsinF//[6].toString() //isfour);
        val iisfive =  ismfael.accdualF//[8].toString() //isfive);
        val iissix =  ismfael.accplurarlF//[10].toString() //issix);
        val iisseven =  ismfael.gensinF//[12].toString() //isseven);
        val iiseight =  ismfael.gendualF//[14].toString() //iseight);
        val iisnine =  ismfael.genplurarF!!.replace("[", "").replace("]", "")//[16].toString() //isnine);


        //     FontSIzeSelection(holder);
        SetTypeFace(holder)
        holder.ismfemone.text = iisone
        holder.ismfemtwo.text = iistwo
        holder.ismfemthree.text = iisthree
        holder.ismfemfour.text = iisfour
        holder.ismfemfive.text = iisfive
        holder.ismfemsix.text = iissix
        holder.ismfemseven.text = iisseven
        holder.ismfemeight.text = iiseight
        holder.ismfemnine.text = iisnine
    }

    private fun IsmMafoolFem(ismmafoolFem: FaelMafool, holder: ViewHolder, position: Int) {

        val smafone =  ismmafoolFem.nomsinF//[2].toString() //istwo);
        val smaftwo=ismmafoolFem.nomdualF
        val smafthree =  ismmafoolFem.nomplurarF//[4].toString() //isthree);
        val smaffour =  ismmafoolFem.accsinF//[6].toString() //isfour);
        val smaffive =  ismmafoolFem.accdualF//[8].toString() //isfive);
        val smafsix =  ismmafoolFem.accplurarlF//[10].toString() //issix);
        val smafseven =  ismmafoolFem.gensinF//[12].toString() //isseven);
        val smafeight =  ismmafoolFem.gendualF//[14].toString() //iseight);
        val smafnine =  ismmafoolFem.genplurarF!!.replace("[", "").replace("]", "")//[16].toString() //isnine);

        //     FontSIzeSelection(holder);
        SetTypeFace(holder)
        holder.imafoolfemone.text = smafone
        holder.imafoolfemtwo.text = smaftwo
        holder.imafoolfemthree.text = smafthree
        holder.imafoolfemfour.text = smaffour
        holder.imafoolfemfive.text = smaffive
        holder.imafoolfemsix.text = smafsix
        holder.imafoolfemseven.text = smafseven
        holder.imafoolfemeight.text = smafeight
        holder.imafoolfemnine.text = smafnine
    }

    private fun IsmMafool(ismmafoolMas: FaelMafool, holder: ViewHolder, position: Int) {



        val smafone =  ismmafoolMas.nomsinM!!.replace("[", "").replace("]", "")//[2].toString() //istwo);
        val smaftwo=ismmafoolMas.nomdualM
        val smafthree =  ismmafoolMas.nomplurarM//[4].toString() //isthree);
        val smaffour =  ismmafoolMas.accsinM//[6].toString() //isfour);
        val smaffive =  ismmafoolMas.accdualM//[8].toString() //isfive);
        val smafsix =  ismmafoolMas.accplurarlM//[10].toString() //issix);
        val smafseven =  ismmafoolMas.gensinM//[12].toString() //isseven);
        val smafeight =  ismmafoolMas.gendualM//[14].toString() //iseight);
        val smafnine =  ismmafoolMas.genplurarM//[16].toString() //isnine);



        //     FontSIzeSelection(holder);
        SetTypeFace(holder)
        holder.imafone.text = smafone
        holder.imaftwo.text = smaftwo
        holder.imafthree.text = smafthree
        holder.imaffour.text = smaffour
        holder.imaffive.text = smaffive
        holder.imafsix.text = smafsix
        holder.imafseven.text = smafseven
        holder.imafeight.text = smafeight
        holder.imafnine.text = smafnine
    }

    private fun SetTypeFace(holder: ViewHolder) {
        //    final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        //    arabicTypeface = Typeface.createFromAsset(context.getAssets(), sharedPref.arabicFontSelection());
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        //  String theme = sharedPreferences.getString("theme", 1);
        val indictive = sharedPreferences.getString("Arabic_Font_Selection", "kitab.ttf")
        arabicTypeface = Typeface.createFromAsset(context.assets, indictive)
        //   String s = SharedPref.arabicFontSelection();
        if (isTraditional) {
            holder.nom.typeface = arabicTypeface // (array[0]);
            holder.acc.typeface = arabicTypeface // (array[1]);
            holder.gen.typeface = arabicTypeface // (array[2]);
            holder.nom1!!.typeface = arabicTypeface // (array[0]);
            holder.acc1!!.typeface = arabicTypeface // (array[1]);
            holder.gen1!!.typeface = arabicTypeface // (array[2]);
            holder.nom2!!.typeface = arabicTypeface // (array[0]);
            holder.acc2!!.typeface = arabicTypeface // (array[1]);
            holder.gen2!!.typeface = arabicTypeface // (array[2]);
            holder.nom3!!.typeface = arabicTypeface // (array[0]);
            holder.acc3!!.typeface = arabicTypeface // (array[1]);
            holder.gen3!!.typeface = arabicTypeface // (array[2]);
            holder.sin1.typeface = arabicTypeface //(array[0]);
            holder.dual1.typeface = arabicTypeface //(array[1]);
            holder.plu1.typeface = arabicTypeface //(array[2]);
            holder.sin2.typeface = arabicTypeface //(array[0]);
            holder.dual2.typeface = arabicTypeface //(array[1]);
            holder.plu2.typeface = arabicTypeface //(array[2]);
            holder.sin3.typeface = arabicTypeface //(array[0]);
            holder.dual3.typeface = arabicTypeface //(array[1]);
            holder.plu3.typeface = arabicTypeface //(array[2]);
            holder.sin4!!.typeface = arabicTypeface //(array[0]);
            holder.dual4!!.typeface = arabicTypeface //(array[1]);
            holder.plu4!!.typeface = arabicTypeface //(array[2]);
            FontSIzeSelection(holder)
        } else {
            holder.nom.typeface = arabicTypeface //(array[0]);
            holder.acc.typeface = arabicTypeface //(array[1]);
            holder.gen.typeface = arabicTypeface //(array[2]);
            holder.sin1.typeface = arabicTypeface // (array[0]);
            holder.dual1.typeface = arabicTypeface // (array[1]);
            holder.plu1.typeface = arabicTypeface // (array[2]);
            holder.sin2.typeface = arabicTypeface // (array[0]);
            holder.dual2.typeface = arabicTypeface // (array[1]);
            holder.plu2.typeface = arabicTypeface // (array[2]);
            holder.sin3.typeface = arabicTypeface // (array[0]);
            holder.dual2.typeface = arabicTypeface // (array[1]);
            holder.dual3.typeface = arabicTypeface // (array[2]);
            FontSIzeSelection(holder)
        }
        holder.imafone.typeface = arabicTypeface //;//smafone);
        holder.imaftwo.typeface = arabicTypeface //;//smaftwo);
        holder.imafthree.typeface = arabicTypeface //;//smafthree);
        holder.imaffour.typeface = arabicTypeface //;//smaffour);
        holder.imaffive.typeface = arabicTypeface //;//smaffive);
        holder.imafsix.typeface = arabicTypeface //;//smafsix);
        holder.imafseven.typeface = arabicTypeface //;//smafseven);
        holder.imafeight.typeface = arabicTypeface //;//smafeight);
        holder.imafnine.typeface = arabicTypeface //;//smafnine);
        //
        holder.imafoolfemone.typeface = arabicTypeface //;//smafone);
        holder.imafoolfemtwo.typeface = arabicTypeface //;//smaftwo);
        holder.imafoolfemthree.typeface = arabicTypeface //;//smafthree);
        holder.imafoolfemfour.typeface = arabicTypeface //;//smaffour);
        holder.imafoolfemfive.typeface = arabicTypeface //;//smaffive);
        holder.imafoolfemsix.typeface = arabicTypeface //;//smafsix);
        holder.imafoolfemseven.typeface = arabicTypeface //;//smafseven);
        holder.imafoolfemeight.typeface = arabicTypeface //;//smafeight);
        holder.imafoolfemnine.typeface = arabicTypeface //;//smafnine);
        //
        holder.ismfemone.typeface = arabicTypeface //;//iismfemone);
        holder.ismfemtwo.typeface = arabicTypeface //;//iismfemtwo);
        holder.ismfemthree.typeface = arabicTypeface //;//iismfemthree);
        holder.ismfemfour.typeface = arabicTypeface //;//iismfemfour);
        holder.ismfemfive.typeface = arabicTypeface //;//iismfemfive);
        holder.ismfemsix.typeface = arabicTypeface //;//iismfemsix);
        holder.ismfemseven.typeface = arabicTypeface //;//iismfemseven);
        holder.ismfemeight.typeface = arabicTypeface //;//iismfemeight);
        holder.ismfemnine.typeface = arabicTypeface //;//iismfemnine);
        holder.isone.typeface = arabicTypeface //;//iisone);
        holder.istwo.typeface = arabicTypeface //;//iistwo);
        holder.isthree.typeface = arabicTypeface //;//iisthree);
        holder.isfour.typeface = arabicTypeface //;//iisfour);
        holder.isfive.typeface = arabicTypeface //;//iisfive);
        holder.issix.typeface = arabicTypeface //;//iissix);
        holder.isseven.typeface = arabicTypeface //;//iisseven);
        holder.iseight.typeface = arabicTypeface //;//iiseight);
        holder.isnine.typeface = arabicTypeface //;//iisnine);
        FontSIzeSelection(holder)
    }

    private fun FontSIzeSelection(holder: ViewHolder) {
        val defaultfont = sharedPreferences!!.getBoolean("default_font", true)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        val arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        val width = sharedPreferences.getString("width", "compactWidth")
        if (!defaultfont) {
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
                    holder.nom3!!.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.acc3!!.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.gen3!!.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin1.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual1.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu1.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin2.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu2.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin3.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual3.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu3.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin4!!.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual4!!.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu4!!.textSize = arabicFontsize.toFloat() //(array[2]);
                } else {
                    if (!defaultfont) {
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
                }
            }
            if (!defaultfont) {
                holder.imafone.textSize = arabicFontsize.toFloat() //smafone);
                holder.imaftwo.textSize = arabicFontsize.toFloat() //smaftwo);
                holder.imafthree.textSize = arabicFontsize.toFloat() //smafthree);
                holder.imaffour.textSize = arabicFontsize.toFloat() //smaffour);
                holder.imaffive.textSize = arabicFontsize.toFloat() //smaffive);
                holder.imafsix.textSize = arabicFontsize.toFloat() //smafsix);
                holder.imafseven.textSize = arabicFontsize.toFloat() //smafseven);
                holder.imafeight.textSize = arabicFontsize.toFloat() //smafeight);
                holder.imafnine.textSize = arabicFontsize.toFloat() //smafnine);
                //
                holder.imafoolfemone.textSize = arabicFontsize.toFloat() //smafone);
                holder.imafoolfemtwo.textSize = arabicFontsize.toFloat() //smaftwo);
                holder.imafoolfemthree.textSize = arabicFontsize.toFloat() //smafthree);
                holder.imafoolfemfour.textSize = arabicFontsize.toFloat() //smaffour);
                holder.imafoolfemfive.textSize = arabicFontsize.toFloat() //smaffive);
                holder.imafoolfemsix.textSize = arabicFontsize.toFloat() //smafsix);
                holder.imafoolfemseven.textSize = arabicFontsize.toFloat() //smafseven);
                holder.imafoolfemeight.textSize = arabicFontsize.toFloat() //smafeight);
                holder.imafoolfemnine.textSize = arabicFontsize.toFloat() //smafnine);
                //
                holder.ismfemone.textSize = arabicFontsize.toFloat() //iismfemone);
                holder.ismfemtwo.textSize = arabicFontsize.toFloat() //iismfemtwo);
                holder.ismfemthree.textSize = arabicFontsize.toFloat() //iismfemthree);
                holder.ismfemfour.textSize = arabicFontsize.toFloat() //iismfemfour);
                holder.ismfemfive.textSize = arabicFontsize.toFloat() //iismfemfive);
                holder.ismfemsix.textSize = arabicFontsize.toFloat() //iismfemsix);
                holder.ismfemseven.textSize = arabicFontsize.toFloat() //iismfemseven);
                holder.ismfemeight.textSize = arabicFontsize.toFloat() //iismfemeight);
                holder.ismfemnine.textSize = arabicFontsize.toFloat() //iismfemnine);
                holder.isone.textSize = arabicFontsize.toFloat() //iisone);
                holder.istwo.textSize = arabicFontsize.toFloat() //iistwo);
                holder.isthree.textSize = arabicFontsize.toFloat() //iisthree);
                holder.isfour.textSize = arabicFontsize.toFloat() //iisfour);
                holder.isfive.textSize = arabicFontsize.toFloat() //iisfive);
                holder.issix.textSize = arabicFontsize.toFloat() //iissix);
                holder.isseven.textSize = arabicFontsize.toFloat() //iisseven);
                holder.iseight.textSize = arabicFontsize.toFloat() //iiseight);
                holder.isnine.textSize = arabicFontsize.toFloat() //iisnine);
            }
        }
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer.size.toLong()
    }

    fun getItem(position: Int): Any {
        return sarfSagheer[position]
    }

    override fun getItemCount(): Int {
        //   return sarfSagheer.size();
        return 1
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        //  public final TextView ayah_number;
     /*   var madhihua: TextView?=null
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
*/
        // imafmnine
        val isone: TextView
        val istwo: TextView
        val isthree: TextView
        val isfour: TextView
        val isfive: TextView
        val issix: TextView
        val isseven: TextView
        val iseight: TextView
        val isnine: TextView
        val ismfemone: TextView
        val ismfemtwo: TextView
        val ismfemthree: TextView
        val ismfemfour: TextView
        val ismfemfive: TextView
        val ismfemsix: TextView
        val ismfemseven: TextView
        val ismfemeight: TextView
        val ismfemnine: TextView
        val imafone: TextView
        val imaftwo: TextView
        val imafthree: TextView
        val imaffour: TextView
        val imaffive: TextView
        val imafsix: TextView
        val imafseven: TextView
        val imafeight: TextView
        val imafnine: TextView
        val imafoolfemone: TextView
        val imafoolfemtwo: TextView
        val imafoolfemthree: TextView
        val imafoolfemfour: TextView
        val imafoolfemfive: TextView
        val imafoolfemsix: TextView
        val imafoolfemseven: TextView
        val imafoolfemeight: TextView
        val imafoolfemnine: TextView
    /*    val mifalone: TextView
        val mifaltwo: TextView
        val mifalthree: TextView
        val mifalfour: TextView
        val mifalfive: TextView
        val mifalsix: TextView
        val mifalseven: TextView
        val mifaleight: TextView
        val mifalnine: TextView
        val mifalatunone: TextView
        val mifalatuntwo: TextView
        val mifalatunthree: TextView
        val mifalatunfour: TextView
        val mifalatunfive: TextView
        val mifalatunsix: TextView
        val mifalatunseven: TextView
        val mifalatuneight: TextView
        val mifalatunnine: TextView
        val mifaalone: TextView
        val mifaaltwo: TextView
        val mifaalthree: TextView
        val mifaalfour: TextView
        val mifaalfive: TextView
        val mifaalsix: TextView
        val mifaalseven: TextView
        val mifaaleight: TextView
        val mifaalnine: TextView
        val mafalunone: TextView
        val mafaluntwo: TextView
        val mafalunthree: TextView
        val mafalunfour: TextView
        val mafalunfive: TextView
        val mafalunsix: TextView
        val mafalunseven: TextView
        val mafaluneight: TextView
        val mafalunnine: TextView
        val mifalunone: TextView
        val mifaluntwo: TextView
        val mifalunthree: TextView
        val mifalunfour: TextView
        val mifalunfive: TextView
        val mifalunsix: TextView
        val mifalunseven: TextView
        val mifaluneight: TextView
        val mifalunnine: TextView*/
        val sin1: TextView
        val dual1: TextView
        val plu1: TextView
        val sin2: TextView
        val dual2: TextView
        val plu2: TextView
        val sin3: TextView
        val dual3: TextView
        val plu3: TextView
        var sin4: TextView?=null
        var dual4: TextView?=null
        var plu4: TextView?=null
        val nom: TextView
        val acc: TextView
        val gen: TextView
        var nom1: TextView?=null
        var acc1: TextView?=null
        var gen1: TextView?=null
        var nom2: TextView?=null
        var acc2: TextView?=null
        var gen2: TextView?=null
        var nom3: TextView?=null
        var acc3: TextView?=null
        var gen3: TextView?=null
        var apmas: TextView
        var apfem: TextView
        var ppmas: TextView
        var ppfem: TextView

        init {
            if(isTraditional){
            sin4 = view.findViewById(R.id.singular4)
            dual4 = view.findViewById(R.id.dual4)
            plu4 = view.findViewById(R.id.plural4)

                nom1 = view.findViewById(R.id.nominative1)
                acc1 = view.findViewById(R.id.accusative1)
                gen1 = view.findViewById(R.id.genitive1)
                nom2 = view.findViewById(R.id.nominative2)
                acc2 = view.findViewById(R.id.accusative2)
                gen2 = view.findViewById(R.id.genitive2)
                nom3 = view.findViewById(R.id.nominative3)
                acc3 = view.findViewById(R.id.accusative3)
                gen3 = view.findViewById(R.id.genitive3)
             }
            nom = view.findViewById(R.id.nominative)
            acc = view.findViewById(R.id.accusative)
            gen = view.findViewById(R.id.genitive)

            sin1 = view.findViewById(R.id.singular1)
            dual1 = view.findViewById(R.id.dual1)
            plu1 = view.findViewById(R.id.plural1)
            sin2 = view.findViewById(R.id.singular2)
            dual2 = view.findViewById(R.id.dual2)
            plu2 = view.findViewById(R.id.plural2)
            sin3 = view.findViewById(R.id.singular3)
            dual3 = view.findViewById(R.id.dual3)
            plu3 = view.findViewById(R.id.plural3)
            apmas = view.findViewById(R.id.apmas)
            apfem = view.findViewById(R.id.apfem)
            ppmas = view.findViewById(R.id.ppmas)
            ppfem = view.findViewById(R.id.ppfem)
/*            madhihua = view.findViewById(R.id.madhihua)
            madhihuma = view.findViewById(R.id.madhihuma)
            madhihum = view.findViewById(R.id.madihum)
            madhihia = view.findViewById(R.id.madhihia)
            madhihumaf = view.findViewById(R.id.madhihumaf)
            madhihunna = view.findViewById(R.id.madihunna)
            madhianta = view.findViewById(R.id.madhianta)
            madhiantuma = view.findViewById(R.id.madhiantuma)
            madhiantum = view.findViewById(R.id.madhiantum)
            madhianti = view.findViewById(R.id.madhianti)
            madhiantunna = view.findViewById(R.id.madhiantumaf)
            madhiantumaf = view.findViewById(R.id.madhiantunna)
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
            muznahnu = view.findViewById(R.id.muznahnu)*/
            //
/*            madimajhua = view.findViewById(R.id.madimajhua)
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
            nahiamrantunna = view.findViewById(R.id.nahiamrantunna)*/
            //ismfael masculine
            ismfemone = view.findViewById(R.id.ismfemone)
            //  tv.setText(R.string.faelmazi); // 2
            ismfemone.setText(R.string.faelmazi)
            ismfemtwo = view.findViewById(R.id.ismfemtwo)
            ismfemthree = view.findViewById(R.id.ismfemthree)
            ismfemfour = view.findViewById(R.id.ismfemfour)
            ismfemfive = view.findViewById(R.id.ismfemfive)
            ismfemsix = view.findViewById(R.id.ismfemsix)
            ismfemseven = view.findViewById(R.id.ismfemseven)
            ismfemeight = view.findViewById(R.id.ismfemeight)
            ismfemnine = view.findViewById(R.id.ismfemnine)
            //
            isone = view.findViewById(R.id.isone)
            istwo = view.findViewById(R.id.istwo)
            isthree = view.findViewById(R.id.isthree)
            isfour = view.findViewById(R.id.isfour)
            isfive = view.findViewById(R.id.isfive)
            issix = view.findViewById(R.id.issix)
            isseven = view.findViewById(R.id.isseven)
            iseight = view.findViewById(R.id.iseight)
            isnine = view.findViewById(R.id.isnine)
            //ismmafoolmasculine
            imafone = view.findViewById(R.id.imafone)
            imaftwo = view.findViewById(R.id.imaftwo)
            imafthree = view.findViewById(R.id.imafthree)
            imaffour = view.findViewById(R.id.imaffour)
            imaffive = view.findViewById(R.id.imaffive)
            imafsix = view.findViewById(R.id.imafsix)
            imafseven = view.findViewById(R.id.imafseven)
            imafeight = view.findViewById(R.id.imafeight)
            imafnine = view.findViewById(R.id.imafnine)
            //ismmafoolfeb
            imafoolfemone = view.findViewById(R.id.imafoolfemone)
            imafoolfemtwo = view.findViewById(R.id.imafoolfemtwo)
            imafoolfemthree = view.findViewById(R.id.imafoolfemthree)
            imafoolfemfour = view.findViewById(R.id.imafoolfemfour)
            imafoolfemfive = view.findViewById(R.id.imafoolfemfive)
            imafoolfemsix = view.findViewById(R.id.imafoolfemsix)
            imafoolfemseven = view.findViewById(R.id.imafoolfemseven)
            imafoolfemeight = view.findViewById(R.id.imafoolfemeight)
            imafoolfemnine = view.findViewById(R.id.imafoolfemnine)
           /* mifalone = view.findViewById(R.id.mifalone)
            mifaltwo = view.findViewById(R.id.mifaltwo)
            mifalthree = view.findViewById(R.id.mifalthree)
            mifalfour = view.findViewById(R.id.mifalfour)
            mifalfive = view.findViewById(R.id.mifalfive)
            mifalsix = view.findViewById(R.id.mifalsix)
            mifalseven = view.findViewById(R.id.mifalseven)
            mifaleight = view.findViewById(R.id.mifaleight)
            mifalnine = view.findViewById(R.id.mifalnine)
            mifalatunone = view.findViewById(R.id.mifalatunone)
            mifalatuntwo = view.findViewById(R.id.mifalatuntwo)
            mifalatunthree = view.findViewById(R.id.mifalatunthree)
            mifalatunfour = view.findViewById(R.id.mifalatunfour)
            mifalatunfive = view.findViewById(R.id.mifalatunfive)
            mifalatunsix = view.findViewById(R.id.mifalatunsix)
            mifalatunseven = view.findViewById(R.id.mifalatunseven)
            mifalatuneight = view.findViewById(R.id.mifalatuneight)
            mifalatunnine = view.findViewById(R.id.mifalatunnine)
            mifaalone = view.findViewById(R.id.mifaalone)
            mifaaltwo = view.findViewById(R.id.mifaaltwo)
            mifaalthree = view.findViewById(R.id.mifaalthree)
            mifaalfour = view.findViewById(R.id.mifaalfour)
            mifaalfive = view.findViewById(R.id.mifaalfive)
            mifaalsix = view.findViewById(R.id.mifaalsix)
            mifaalseven = view.findViewById(R.id.mifaalseven)
            mifaaleight = view.findViewById(R.id.mifaaleight)
            mifaalnine = view.findViewById(R.id.mifaalnine)
            mafalunone = view.findViewById(R.id.mafalunone)
            mafaluntwo = view.findViewById(R.id.mafaluntwo)
            mafalunthree = view.findViewById(R.id.mafalunthree)
            mafalunfour = view.findViewById(R.id.mafalunfour)
            mafalunfive = view.findViewById(R.id.mafalunfive)
            mafalunsix = view.findViewById(R.id.mafalunsix)
            mafalunseven = view.findViewById(R.id.mafalunseven)
            mafaluneight = view.findViewById(R.id.mafaluneight)
            mafalunnine = view.findViewById(R.id.mafalunnine)
            mifalunone = view.findViewById(R.id.mifalunone)
            mifaluntwo = view.findViewById(R.id.mifaluntwo)
            mifalunthree = view.findViewById(R.id.mifalunthree)
            mifalunfour = view.findViewById(R.id.mifalunfour)
            mifalunfive = view.findViewById(R.id.mifalunfive)
            mifalunsix = view.findViewById(R.id.mifalunsix)
            mifalunseven = view.findViewById(R.id.mifalunseven)
            mifaluneight = view.findViewById(R.id.mifaluneight)
            mifalunnine = view.findViewById(R.id.mifalunnine)*/
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