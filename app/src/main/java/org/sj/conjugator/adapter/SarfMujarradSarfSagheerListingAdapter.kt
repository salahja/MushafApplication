package org.sj.conjugator.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.google.android.material.textview.MaterialTextView
import org.sj.conjugator.fragments.SarfSagheer
import org.sj.conjugator.interfaces.OnItemClickListener

class SarfMujarradSarfSagheerListingAdapter(
    private var sarfSagheer: ArrayList<SarfSagheer>,
    private val context: Context
) : RecyclerView.Adapter<SarfMujarradSarfSagheerListingAdapter.ViewHolder>() {
    private var rootcolor = 0
    private var weaknesscolor = 0
    private var wazancolor = 0
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null
    private var alaheader = "اِسْم الآلَة"
    private var zarfheader = "اِسْم الْظَرفْ"
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //      View view = LayoutInflater.from(parent.context!!).inflate(R.layout.sarfkabeercolumn, parent, false);

        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sarfsagheer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  final List sarf = sarfSagheer.get(position);
//        final String[] array = (String[]) sarfSagheer.get(position).toArray();
//        SharedPreferences shared = getDefaultSharedPreferences(context);
//        String preferences = shared.getString("theme", "dark");
        val shared = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val preferences = shared.getString("theme", "dark")
        // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!);
        if (preferences == "dark" || preferences == "blue" || preferences == "green") {
            rootcolor = Color.CYAN
            weaknesscolor = Color.YELLOW
            wazancolor = Color.CYAN
        } else {
            rootcolor = Color.RED
            weaknesscolor = Color.BLACK
            wazancolor = Color.RED
        }
        val zarf = StringBuilder()
        val ismala = StringBuilder()
        val amr = StringBuilder()
        val nahiamr = StringBuilder()
        val sagheer = sarfSagheer[position]
        val prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val mequran = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )
        if (sarfSagheer[0].verbtype == "mazeed") {
            holder.form.visibility = View.VISIBLE
            holder.form.text = sarfSagheer[0].wazanname
        } else {
            holder.form.visibility = View.GONE
        }
        val length = sarfSagheer.size
        val arabicFontsize = prefs.getInt("arabicFontSizeEntryArray", 20)
        holder.mamaroof.text = sagheer.madhi
        holder.mumaroof.text = sagheer.mudharay
        holder.ismfail.text = sagheer.ismfael
        holder.mamajhool.text = sagheer.madhimajhool
        holder.mumajhool.text = sagheer.mudharaymajhool
        holder.ismmafool.text = sagheer.ismmafool
        holder.amr.text = sagheer.amrone
        holder.nahiamr.text = sagheer.nahiamrone
        holder.ismzarfheader.text = zarfheader
        holder.ismalaheader.text = alaheader




        zarf.append(sagheer.ismalaone as CharSequence?).append(", ").append(sagheer.ismalatwo)
            .append(", ").append(sagheer.ismalathree)
        ismala.append(sagheer.zarfone as CharSequence?).append(", ").append(sagheer.zarftwo)
            .append(", ").append(sagheer.zarfthree)
        holder.ismzarf.text = zarf
        holder.ismala.text = ismala
        holder.weaknessname.text = sagheer.weakness
        holder.rootword.text = sagheer.verbroot
        holder.babname.text = sagheer.wazanname
        holder.ismalaheader.textSize = arabicFontsize.toFloat()
        holder.ismzarfheader.textSize = arabicFontsize.toFloat()
        holder.mamaroof.textSize = arabicFontsize.toFloat()
        holder.mumaroof.textSize = arabicFontsize.toFloat()
        holder.masdaro.textSize = arabicFontsize.toFloat()
        holder.masdart.textSize = arabicFontsize.toFloat()
        holder.ismfail.textSize = arabicFontsize.toFloat()
        holder.mamajhool.textSize = arabicFontsize.toFloat()
        holder.mumajhool.textSize = arabicFontsize.toFloat()
        holder.ismmafool.textSize = arabicFontsize.toFloat()
        holder.amr.textSize = arabicFontsize.toFloat()
        holder.nahiamr.textSize = arabicFontsize.toFloat()
        holder.babname.textSize = arabicFontsize.toFloat()
        holder.rootword.textSize = arabicFontsize.toFloat()
        holder.ismzarf.textSize = arabicFontsize.toFloat()
        holder.ismala.textSize = arabicFontsize.toFloat()
        holder.weaknessname.textSize = arabicFontsize.toFloat()
        holder.wazan.textSize = arabicFontsize.toFloat()
        holder.mamaroof.typeface = mequran
        holder.mumaroof.typeface = mequran
        //   holder.masdaro.setTypeface(mequran);
        // holder.masdart.setTypeface(mequran);
        holder.ismfail.typeface = mequran
        holder.mamajhool.typeface = mequran
        holder.mumajhool.typeface = mequran
        holder.ismmafool.typeface = mequran
        holder.amr.typeface = mequran
        holder.nahiamr.typeface = mequran
        holder.babname.typeface = mequran
        //  holder.babname.setTextColor(Color.YELLOW);
        holder.rootword.typeface = mequran
        //  holder.rootword.setTextColor(Color.BLUE);
        holder.ismzarf.typeface = mequran
        holder.ismala.typeface = mequran
        holder.weaknessname.typeface = mequran
        //  holder.weaknessname.setTextColor(Color.GREEN);
        holder.babname.setTextColor(wazancolor)
        holder.rootword.setTextColor(rootcolor)
        holder.weaknessname.setTextColor(weaknesscolor)
        //     holder.masdaro.setText((CharSequence) toArray.get(12));
        //     holder.masdart.setText((CharSequence) toArray.get(12));
        //     TextView textView = (TextView) findViewById(R.id.textView);
        //     Spannable spanDark = new SpannableString(textView.getText());
        //     span.setSpan(new RelativeSizeSpan(0.8f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //     textView.setText(span);
        //     ismfail,masdaro,mumaroof,mamaroof,ismmafool,masdart,mumajhool,mamajhool,ismzarf,ismala;
        //  holder.ismfail.setText(o.);
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer.size.toLong()
    }

    fun getItem(position: Int): Any {
        return sarfSagheer[position]
    }

    override fun getItemCount(): Int {
        return sarfSagheer.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val amr: MaterialTextView
        val nahiamr: MaterialTextView
        val ismfail: MaterialTextView
        val mumaroof: MaterialTextView
        val mamaroof: MaterialTextView
        val ismmafool: MaterialTextView
        val mumajhool: MaterialTextView
        val mamajhool: MaterialTextView
        val ismzarf: MaterialTextView
        val ismala: MaterialTextView
        var form: MaterialTextView
        private val babno: TextView
        val ismalaheader: TextView
        val ismzarfheader: TextView
        val masdart: TextView
        val masdaro: TextView
        val babname: TextView
        val rootword: TextView
        val weaknessname: TextView
        val wazan: TextView

        init {
            //    itemView.setTag(this);
            //     itemView.setOnClickListener(onItemClickListener);
            form = view.findViewById(R.id.form)
            babno = view.findViewById(R.id.babno)
            babno.setOnClickListener(this)
            form.setOnClickListener(this)
            form.tag = "form"
            ismfail = view.findViewById(R.id.ismfail)
            masdaro = view.findViewById(R.id.masdar)
            mumaroof = view.findViewById(R.id.mumaroof)
            mamaroof = view.findViewById(R.id.mamaroof)
            ismmafool = view.findViewById(R.id.ismmafool)
            masdart = view.findViewById(R.id.masdar2)
            mumajhool = view.findViewById(R.id.mumajhool)
            mamajhool = view.findViewById(R.id.mamajhool)
            amr = view.findViewById(R.id.amr)
            nahiamr = view.findViewById(R.id.nahiamr)
            ismala = view.findViewById(R.id.ismaalatable)
            ismzarf = view.findViewById(R.id.zarftable)
            babname = view.findViewById(R.id.babno)
            rootword = view.findViewById(R.id.rootword)
            weaknessname = view.findViewById(R.id.weknessname)
            ismzarfheader = view.findViewById(R.id.ismzarfheader)
            ismalaheader = view.findViewById(R.id.ismalaheader)
            wazan = view.findViewById(R.id.wazan)
            //     verify = view.findViewById(R.id.conjugateall);
            mumajhool.tooltipText = "Click for Verb Conjugation"
            view.setOnClickListener(this)
            ismfail.setOnClickListener(this) //R.id.ismfail);
            mumaroof.setOnClickListener(this) //R.id.mumaroof);
            mamaroof.setOnClickListener(this) //R.id.mamaroof);
            ismmafool.setOnClickListener(this) //R.id.ismmafool);
            mumajhool.setOnClickListener(this) //R.id.mumajhool);
            mamajhool.setOnClickListener(this) //R.id.mamajhool);
            amr.setOnClickListener(this) //R.id.amr);
            nahiamr.setOnClickListener(this) //R.id.nahiamr);
            ismala.setOnClickListener(this) //R.id.ismaalatable);
            ismzarf.setOnClickListener(this) //R.id.zarftable);
            rootword.setOnClickListener(this) //R.id.weaknesstype);
            babno.setOnClickListener { }
            //      verify.setOnClickListener(this);
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}