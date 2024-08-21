package com.example.quranroots

import android.text.SpannableString
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.RootVerbDetails
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.fragments.QuranMorphologyDetails
import com.example.utility.CorpusUtilityorig.Companion.NewSetWordSpan
import com.google.android.material.button.MaterialButton
import org.sj.conjugator.interfaces.OnItemClickListener

/**

 * TODO: Replace the implementation with code for your data type.
 */
class VerbDetailsRecAdapter(private val mValues: ArrayList<RootVerbDetails>) :
    RecyclerView.Adapter<VerbDetailsRecAdapter.ViewHolder>() {

    private var mItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.verbdetails, parent, false)
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lughat = mValues[position]
        val sb = StringBuilder()
        val spannableString = NewSetWordSpan(
            lughat.tagone, lughat.tagtwo, lughat.tagthree, lughat.tagfour, lughat.tagfive,
            lughat.araone!!, lughat.aratwo!!, lughat.arathree!!, lughat.arafour!!, lughat.arafive!!
        )
        //  sb.append(lughat.getSurah()).append("   ").append(lughat.getNamearabic()).append(lughat.getAyah()).append(" ").append(lughat.getArabic());
        sb.append(lughat.ayah).append("  ").append(lughat.namearabic).append("   ")
            .append(lughat.surah).append(" ").append(lughat.en)
        val sbs = SpannableString(sb)
        val charSequence = TextUtils.concat(spannableString, sb)

        holder.arabicsurahname.text = charSequence
        holder.arabicsurahname.text = lughat.namearabic
        var sa = StringBuilder()
        sa.append(lughat.surah).append(":").append(lughat.ayah).append(":").append(lughat.wordno)
        holder.verbsaw.text = sa
        holder.arabicword.text = spannableString
        holder.wordmeaning.text = lughat.en
        holder.tensevoicegendernumbermood.text =
            QuranMorphologyDetails.getGenderNumberdetails(lughat.gendernumber)
        sa = StringBuilder()
        sa.append(lughat.tense).append(":").append(lughat.voice).append(":")
            .append(lughat.mood_kananumbers)
        holder.tensevoice.text = sa
        if (lughat.form == "I") {
            if (lughat.thulathibab!!.length > 1) {
                val s = lughat.thulathibab!!.substring(0, 1)
                holder.wazan.text = QuranMorphologyDetails.getThulathiName(s)
            } else {
                holder.wazan.text = QuranMorphologyDetails.getThulathiName(lughat.thulathibab)
            }


            //   QuranMorphologyDetails.getThulathiName(lughat.getThulathibab());
        } else {
            holder.wazan.text = QuranMorphologyDetails.getFormName(lughat.form)
        }


        //     holder.mIdView.setText(mValues.get(position).id);
        //    holder.mContentView.setText(mValues.get(position).content);
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        // public final ImageView id;
        private val conjugate: MaterialButton
        var cardview: CardView? = null
        var arabicsurahname: TextView
        var verbsaw: TextView
        var arabicword: TextView
        var wazan: TextView
        var tensevoicegendernumbermood: TextView
        var tensevoice: TextView
        var wordmeaning: TextView

        init {
            view.tag = this
            itemView.setOnClickListener(this)
            //  id = view.findViewById(R.id.imgview);
            arabicsurahname = view.findViewById(R.id.arabicsurahname)
            arabicsurahname.tag = "root"
            arabicsurahname.setOnClickListener(this)
            conjugate = view.findViewById(R.id.conjugate)
            wordmeaning = view.findViewById(R.id.wordmeaning)
            arabicsurahname = view.findViewById(R.id.arabicsurahname)
            verbsaw = view.findViewById(R.id.verbsaw)
            arabicword = view.findViewById(R.id.arabicword)
            wazan = view.findViewById(R.id.wazan)
            tensevoicegendernumbermood = view.findViewById(R.id.tensevoicegendernumbermood)
            tensevoice = view.findViewById(R.id.tensevoice)
            conjugate.tag = "conjugate"
            conjugate.setOnClickListener(this)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}