package com.example.mushafconsolidatedimport

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication.Companion.context

import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * Particle color scheme
 *
 * @constructor Create empty Particle color scheme
 */
class ParticleColorScheme : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null
    private var textView: TextView? = null
    private var colorSchemeAdapter: ColorSchemeAdapter? = null

    /**
     * Set on item click listener
     *
     * @param mItemClickListener
     */
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.colorschemelayout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  recyclerView.setLayoutManager(new LinearLayoutManager(context!!));
        val mLayoutManager: GridLayoutManager = GridLayoutManager(activity, 2)
        // parentRecyclerView = view.findViewById(R.id.juzRecyclerView);
        val recyclerView: RecyclerView = view.findViewById(R.id.colorrecview)
        recyclerView.layoutManager = mLayoutManager
        val spanhash = CorpusUtilityorig.stringForegroundColorSpanMap
        textView = view.findViewById(R.id.Colortv)
        val particle: MutableList<String> = ArrayList()


        particle?.add("PN = \"Proper Noun(اسم علم)\",")
        particle?.add("ADJ = \"Adjective(صفة)")
        particle?.add("V =Verb(فعل)")
        particle?.add("N =Noun")
        particle?.add(" PRON = Pronouns(ضمير)")
        particle?.add(" REL =  Relative Pronoun(اسم موصول)")
        particle?.add("T =  Time Adverb(ظرف زمان)")
        particle?.add("  LOC =  Location Adverb(ظرف مكان)")

        particle?.add("ACC  	Accusative particle(حرف نصب)")
        particle?.add("AMD  	Amendment particle(حرف استدراك)	")
        particle?.add("ANS  	Answer particle	(حرف جواب)")
        particle?.add("AVR  	Aversion particle	(حرف ردع)")


        particle?.add("CAUS  Particle of cause	(حرف سببية)")
        particle?.add("CERT  Particle of certainty	(حرف تحقيق)")
        particle?.add("CIRC  Circumstantial particle	(حرف حال)")
        particle?.add("COM  	Comitative particle	(واو المعية)")
        particle?.add("COND  Conditional particle(حرف شرط)")
        particle?.add("CONJ  Coordinating conjunction(حرف عطف)")
        particle?.add("DEM = Demonstrative Pronoun(اسم اشارة)")
        particle?.add("DET  determiner()")

        particle?.add("EMPH  Emphatic lām prefix(لام التوكيد) ")
        particle?.add("EQ  	Equalization particle(حرف تسوية)")
        particle?.add("EXH  	Exhortation particle(حرف تحضيض)")
        particle?.add("EXL  	Explanation particle(حرف تفصيل)")
        particle?.add("EXP  	Exceptive particle	(أداة استثناء)")
        particle?.add("FUT  	Future particle	(حرف استقبال)")
        particle?.add("INL  	Quranic initials(	(حروف مقطعة	")
        particle?.add("INC  	Inceptive particle	(حرف ابتداء)")
        particle?.add("INT  	Particle of interpretation(حرف تفسير)")
        particle?.add("INTG  Interogative particle	(حرف استفهام)")

        particle?.add("PREV  Preventive particle	(حرف كاف)")
        particle?.add ("P  Prepositions(حرف جر)")


        particle?.add("PRO  	Prohibition particle(حرف نهي)")
        particle?.add("REM  	Resumption particle	(حرف استئنافية)")
        particle?.add("RES  	Restriction particle(أداة حصر)")
        particle?.add("RET  	Retraction particle	(حرف اضراب)")
        particle?.add("RSLT  Result particle(حرف واقع في جواب الشرط)")



        particle?.add("NEG  	Negative particle(حرف نفي)")
        particle?.add("SUB  	Subordinating conjunction(حرف مصدري)")
        particle?.add("SUP  	Supplemental particle	(حرف زائد)")
        particle?.add("SUR  	Surprise particle	(حرف فجاءة)")
        particle?.add("VOC  	Vocative particle	(حرف نداء)")
        particle?.add("IMPV  Imperative lāmprefix(لام الامر)")
        particle?.add("PRP  Purpose lāmprefix(لام التعليل)")





        val details: ArrayList<String> = ArrayList()
        val sample: String = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
        colorSchemeAdapter = ColorSchemeAdapter(spanhash, particle)
        recyclerView.adapter = colorSchemeAdapter
        colorSchemeAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                //      int checkedRadioButtonId = textView.getCheckedRadioButtonId();
                //Toast.makeText(context!!, "", Toast.LENGTH_SHORT).show();
            }
        })
    }

    private class ViewHolder(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.coloradapter, parent, false)),
        View.OnClickListener {

        val text: TextView = itemView.findViewById(R.id.Colortv)

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }


    }

    private class ColorSchemeAdapter(
        spanhash: Map<String?, ForegroundColorSpan>,
        private var particle: MutableList<String>
    ) :
        RecyclerView.Adapter<ViewHolder>() {
        private var mItemCount: String? = null
        private var spanhash: Map<String?, ForegroundColorSpan>? = spanhash
        private var mItemClickListener: OnItemClickListener? = null
        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            val sample = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
            val mequran = Typeface.createFromAsset(context!!.assets, "me_quran.ttf")
            val qalam = Typeface.createFromAsset(context!!.assets, "AlQalam.ttf")
            val s = particle!![position]
            val split = s?.split("\\s".toRegex())?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
            val foregroundColorSpan = spanhash!![split?.get(0)]
            val sp = SpannableString(s)


            sp.setSpan(foregroundColorSpan, 0, s!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.text.text = sp
            holder.text.textSize = 20f
        }


        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ParticleColorScheme.ViewHolder {
            return ParticleColorScheme.ViewHolder(
                LayoutInflater.from(
                    parent.context
                ), parent
            )
        }

        override fun getItemCount(): Int {
            return particle!!.size
        }

        /**
         * Set on item click listener
         *
         * @param mItemClickListener
         */
        fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
            this.mItemClickListener = mItemClickListener
        }
    }

    companion object {
        // TODO: Customize parameters
        fun newInstance(): ParticleColorScheme {
            return ParticleColorScheme()
        }
    }
}