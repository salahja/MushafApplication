import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import org.sj.conjugator.fragments.SarfSagheer
import org.sj.verbConjugation.FaelMafool

class NewRootWordDisplayAdapter : RecyclerView.Adapter<NewRootWordDisplayAdapter.ItemViewAdapter> {
    var mItemClickListener: OnItemClickListener? = null
    private val alaheader = "اِسْم الآلَة"
    private val zarfheader = "اِسْم الْظَرفْ"
    private var verblist: ListView? = null
    var arabicFontsize: Int? = null
    private var rootcolor = 0
    private var weaknesscolor = 0
    private var wazancolor = 0
    private var isSarfSagheerMazeed = false
    private var context: Context

    private var corpusexpand: ArrayList<QuranCorpusWbw>? = null
    private var isSarfSagheerThulahi = false
    private var isverbconjugation = false
    private var particples = false

    private var isnoun = false

    private var isverb = false
    private var isviewtype = 2
    private lateinit var worddetails: HashMap<String, SpannableStringBuilder?>
    private lateinit var vbdetail: HashMap<String, String?>
    private lateinit var ismfaelmafool: ArrayList<ArrayList<*>>
    private lateinit var spannable: SpannableStringBuilder
    private lateinit var worddictorary: ArrayList<lughat>
    private lateinit var wazannumberslist: ArrayList<String>

    private lateinit var sagheer: SarfSagheer
    private var mafoolbihi: ArrayList<MafoolBihi>? = null
    private var tameez: ArrayList<TameezEnt>? = null
    private lateinit var haliaSentence: ArrayList<HalEnt>

    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();
    //new  private lateinit var sarfsagheer: ArrayList<SarfSagheer>
    private var mutlaq: ArrayList<MafoolMutlaqEnt>? = null

    private lateinit var sharedPreferences: SharedPreferences
    private var sarfsagheer: java.util.ArrayList<SarfSagheer>? = null
    private var liajlihi: ArrayList<LiajlihiEnt>? = null

    constructor(
        context: Context,
        haliaSentence: ArrayList<HalEnt>?,
        tameez: ArrayList<TameezEnt>?,
        mafoolbihi: ArrayList<MafoolBihi>?,
        mutlaqword: ArrayList<MafoolMutlaqEnt>?,
        liajlihiEntArrayList: ArrayList<LiajlihiEnt>?,
        verb: Boolean,
        wazannumberslist: ArrayList<String>?,
        spannable: SpannableStringBuilder?,
        noun: Boolean,
        ismfaelmafool: ArrayList<ArrayList<*>>?,
        participles: Boolean,
        isverbconjugaton: Boolean,
        corpusSurahWord: ArrayList<QuranCorpusWbw>?,
        wordbdetail: HashMap<String, SpannableStringBuilder?>?,
        vbdetail: HashMap<String, String?>?,
        mazeedSarfSagheer: Boolean,
        thulathiSarfSagheer: Boolean,
        sarfSagheerList: ArrayList<SarfSagheer>?,
    ) {
        this.context = context
        this.haliaSentence = haliaSentence!!
        this.tameez = tameez
        this.mafoolbihi = mafoolbihi
        mutlaq = mutlaqword
        liajlihi = liajlihiEntArrayList
        isverb = verb
        this.wazannumberslist = wazannumberslist!!
        this.spannable = spannable!!
        isnoun = noun
        if (ismfaelmafool != null) {
            this.ismfaelmafool = ismfaelmafool
        }
        particples = participles
        isverbconjugation = isverbconjugaton
        corpusexpand = corpusSurahWord
        worddetails = wordbdetail!!
        this.vbdetail = vbdetail!!
        isSarfSagheerMazeed = mazeedSarfSagheer
        isSarfSagheerThulahi = thulathiSarfSagheer
        sarfsagheer = sarfSagheerList
    }

    constructor(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewRootWordDisplayAdapter.ItemViewAdapter {
        val view: View
        if (isverbconjugation) {
            isviewtype = 1
            view = LayoutInflater.from(parent.context!!)
                .inflate(R.layout.qaris_view_word_details, parent, false)
        } else if (particples) {
            isviewtype = 2
            view = LayoutInflater.from(parent.context!!)
                .inflate(R.layout.wordbottomsheetismfaelmafoolsktraditional, parent, false)
            //    view = LayoutInflater.from(parent.context!!).inflate(R.layout.qaris_view_word_details, parent, false);
        } else {
            isviewtype = 1
            view = LayoutInflater.from(parent.context!!)
                .inflate(R.layout.qaris_view_word_details, parent, false)
        }
        return ItemViewAdapter(view, viewType)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: NewRootWordDisplayAdapter.ItemViewAdapter,
        position: Int,
    ) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val theme: String? = sharedPreferences.getString("themePref", "dark")
        val width: String? = sharedPreferences.getString("width", "compactWidth")
        arabicFontsize = if ((width == "mediumWidth") || (width == "expandedWidth")) {
            sharedPreferences.getInt("pref_font_arabic_key", 20)
        } else {
            18
        }
        /*      if (theme.equals("dark") || theme.equals("blue")  ||theme.equals("green")) {
            //    holder.darkThemeBacground.setBackground(context.getResources().getDrawable(R.drawable.activatedbackgroundblack));
            holder.darkThemeBacground.setCardBackgroundColor(context.getResources().getColor(R.color.odd_item_bg_black));

        } else if (theme.equals("blue")) {
            holder.darkThemeBacground.setCardBackgroundColor(context.getResources().getColor(R.color.background_color_light_darkBlue));

        }*/if (!particples && !isnoun && !isverb) {
            holder.nounoccurancebtn.visibility = View.GONE
            //  holder.verbOccurancebtn.setVisibility(GONE);
            holder.verbconjugationbtn.visibility = View.GONE
            holder.verbdetails.visibility = View.GONE
            holder.noundetails.visibility = View.GONE
        }
        if (particples || isverb) {
            holder.verbconjugationbtn.visibility = View.VISIBLE
            if (worddetails["formnumber"] != null) {
                holder.mazeedmeaning.text = worddetails["formnumber"]
                holder.mazeedmeaning.visibility = View.VISIBLE
                holder.mazeedmeaning.textSize = arabicFontsize!!.toFloat()
            }
            verblist = ListView(context)
            if (wazannumberslist.size == 1) {
                holder.rdone.text = wazannumberslist[position]
                holder.rdone.visibility = View.VISIBLE
                holder.rdone.isChecked = true
                holder.rdone.textSize = arabicFontsize!!.toFloat()
            }
            if (wazannumberslist.size == 2) {
                holder.rdone.text = wazannumberslist[position]
                holder.rdone.visibility = View.VISIBLE
                holder.rdone.isChecked = true
                holder.rdtwo.text = wazannumberslist[position + 1]
                holder.rdtwo.visibility = View.VISIBLE
                holder.rdtwo.textSize = arabicFontsize!!.toFloat()
                holder.rdone.textSize = arabicFontsize!!.toFloat()
            }
            if (wazannumberslist.size == 3) {
                holder.rdone.text = wazannumberslist[position]
                holder.rdone.visibility = View.VISIBLE
                holder.rdone.isChecked = true
                holder.rdtwo.text = wazannumberslist[position + 1]
                holder.rdtwo.visibility = View.VISIBLE
                holder.rdthree.text = wazannumberslist[position + 2]
                holder.rdthree.visibility = View.VISIBLE
                holder.rdthree.textSize = arabicFontsize!!.toFloat()
                holder.rdone.textSize = arabicFontsize!!.toFloat()
                holder.rdtwo.textSize = arabicFontsize!!.toFloat()
            }
            if (wazannumberslist.size == 4) {
                holder.rdone.text = wazannumberslist[position]
                holder.rdone.visibility = View.VISIBLE
                holder.rdone.isChecked = true
                holder.rdtwo.text = wazannumberslist[position + 1]
                holder.rdtwo.visibility = View.VISIBLE
                holder.rdfour.text = wazannumberslist[position + 3]
                holder.rdfour.visibility = View.VISIBLE
                holder.rdfour.textSize = arabicFontsize!!.toFloat()
                holder.rdthree.textSize = arabicFontsize!!.toFloat()
                holder.rdone.textSize = arabicFontsize!!.toFloat()
                holder.rdtwo.textSize = arabicFontsize!!.toFloat()
                holder.rdfour.textSize = arabicFontsize!!.toFloat()
            }
        }
        val mequran: Typeface = Typeface.createFromAsset(context.assets, quranFont)

        if ((theme == "dark") || (theme == "blue") || (theme == "green")) {
            rootcolor = Constant.BYELLOW
            weaknesscolor = Constant.BCYAN
            wazancolor = Constant.BCYAN
        } else {
            rootcolor = Constant.WBURNTUMBER
            weaknesscolor = Constant.KASHMIRIGREEN
            wazancolor = Constant.WMIDNIHTBLUE
        }
        holder.quranverseShart.ellipsize = TextUtils.TruncateAt.MARQUEE
        holder.spannableverse.ellipsize = TextUtils.TruncateAt.MARQUEE
        //   holder.verblist.setText(Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY));
        if (null != spannable) {
            val spans: Array<Any> = spannable.getSpans(0, spannable.length, Any::class.java)
            if (spans.isNotEmpty()) {
                holder.spannableverse.text = spannable
                holder.spannableverse.typeface = mequran
                holder.spannableverse.textSize = arabicFontsize!!.toFloat()
            } else if (spannable != null) {
                holder.spannableverse.text = spannable
                holder.spannableverse.typeface = mequran
                holder.spannableverse.textSize = arabicFontsize!!.toFloat()
            }
        }
        val ismfaelmafoolarray: ArrayList<*> = ArrayList<Any?>()
        if (isSarfSagheerMazeed || isSarfSagheerThulahi) {
            sagheer = sarfsagheer!![position]
            holder.mazeedmeaning.text = vbdetail["mazeed"]
            holder.mazeedmeaning.text = vbdetail["formnumber"]
            //    holder.mazeedmeaning.setText(Html.fromHtml(vbdetail.get("mazeed")));
            holder.mazeedmeaning.visibility = View.VISIBLE
            holder.mazeedmeaning.textSize = arabicFontsize!!.toFloat()
        }
        if (isnoun && !particples) {
            holder.verbconjugationbtn.visibility = View.GONE
        }
        holder.translationView.text = worddetails["translation"]


        //  String replace = word.toString().replace("\n", "<br/>").replace("\\n", "<br/>");
        holder.translationView.textSize = arabicFontsize!!.toFloat()
        //    holder.wordView.chipBackgroundColor = getColorStateList


        //    holder.wordView.chipBackgroundColor = getColorStateList
        val mafoolbihiverb = SpannableStringBuilder()
        var objectpronoun: SpannableStringBuilder? = SpannableStringBuilder()
        val tameezwordspan = SpannableStringBuilder()
        val ajlihiwordspan = SpannableStringBuilder()
        val mutlaqwordspan = SpannableStringBuilder()


        if (tameez!!.isNotEmpty()) {
            tameezwordspan.append("(").append("تمييز").append(")")
            tameezwordspan.append(tameez!![0].word)
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            tameezwordspan.setSpan(
                spanhash["N"],
                0,
                tameezwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.tameeztv?.text = tameezwordspan
            holder.tameeztv?.visibility = View.VISIBLE
        }
        if (worddetails["liajlihi"] != null) {
            ajlihiwordspan.append("(").append("مفعول لأجله").append(")")
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            ajlihiwordspan.append(liajlihi!![0].word)
            ajlihiwordspan.setSpan(
                spanhash["N"],
                0,
                ajlihiwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.liajlihitv!!.text = ajlihiwordspan
            holder.liajlihitv!!.visibility = View.VISIBLE
        }
        if (worddetails["mutlaqword"] != null) {
            mutlaqwordspan.append("(").append("مفعول المطلق").append(")")
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            mutlaqwordspan.append(mutlaq!![0].word)
            mutlaqwordspan.setSpan(
                spanhash["N"],
                0,
                mutlaqwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.mutlaqtv!!.text = mutlaqwordspan
            holder.mutlaqtv!!.visibility = View.VISIBLE
        }
        var charSequence: CharSequence? = ""
        if (worddetails["mafoolbihi"] != null) {
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            //   mafoolbihiverb.append(mafoolbihi.get(0).getWord());
            val b: Boolean = mafoolbihi!![0].objectpronoun == null
            if (!b) {
                mafoolbihiverb.append(mafoolbihi!![0].word)
                objectpronoun =
                    SpannableStringBuilder.valueOf(mafoolbihi!![0].objectpronoun)
                objectpronoun.append("(").append("مفعول به").append(")")
                mafoolbihiverb.setSpan(
                    spanhash["V"],
                    0,
                    mafoolbihiverb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                objectpronoun.setSpan(
                    spanhash["PRON"], 0, objectpronoun.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                charSequence = TextUtils.concat(mafoolbihiverb, " ", objectpronoun)
            } else {
                mafoolbihiverb.append(mafoolbihi!![0].word)
                mafoolbihiverb.append("(").append("مفعول به").append(")")
                mafoolbihiverb.setSpan(
                    spanhash["N"],
                    0,
                    mafoolbihiverb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                charSequence = TextUtils.concat(mafoolbihiverb)
            }
        }
        if (haliaSentence.isNotEmpty()) {
            holder.haliaSentence!!.text = haliaSentence[0].text
            holder.haliaSentence!!.visibility = View.VISIBLE
            holder.haliaSentence!!.textSize = arabicFontsize!!.toFloat()
            holder.haliaSentence!!.typeface = mequran
            holder.haliaSentence!!.ellipsize = TextUtils.TruncateAt.MARQUEE
        }
        if (mafoolbihiverb.isNotEmpty()) {
            holder.mafoolat!!.text = charSequence
            holder.mafoolat!!.textSize = arabicFontsize!!.toFloat()
            holder.mafoolat!!.visibility = View.VISIBLE
            holder.mafoolat!!.typeface = mequran
            holder.mafoolat!!.ellipsize = TextUtils.TruncateAt.MARQUEE
        }
        if (mafoolbihiverb.isNotEmpty()) {
            holder.mafoolat!!.text = charSequence
            holder.mafoolat!!.textSize = arabicFontsize!!.toFloat()
            holder.mafoolat!!.visibility = View.VISIBLE
            holder.mafoolat!!.typeface = mequran
            holder.mafoolat!!.ellipsize = TextUtils.TruncateAt.MARQUEE
        }
        val word: SpannableStringBuilder? = worddetails["word"]
        holder.wordView.text = word


        //    holder.wordView.setText(worddetails.get("word"));

        val pron: StringBuilder = StringBuilder()
        holder.lemma.text = vbdetail["lemma"]
        //   holder.wordView.setTextSize(arabicFontsize);
        //   holder.lemma.setTextSize(arabicFontsize);
        worddetails["noun"]
        worddetails["PRON"]
        try {

        } catch (e: NullPointerException) {
            println("root word not found")
        }
        if (worddetails["noun"] != null) {
            holder.noundetails.visibility = View.VISIBLE
            holder.noundetails.text = worddetails["noun"]
            //     holder.noundetails.setTextSize(arabicFontsize);
        }
        if (worddetails["PRON"] != null) {
            holder.pronoundetails.visibility = View.VISIBLE
            pron.append("Pronoun:")
            pron.append(worddetails["PRON"])
            holder.pronoundetails.text = pron.toString()
            //  holder.pronoundetails.setTextSize(arabicFontsize);
        }
        val vb: StringBuilder = StringBuilder()
        vb.append("V-")
        if (vbdetail["thulathi"] != null) {
            vb.append(vbdetail["thulathi"])
        }
        if (vbdetail["png"] != null) {
            vb.append(vbdetail["png"])
        }
        if (vbdetail["tense"] != null) {
            vb.append(vbdetail["tense"])
        }
        if (vbdetail["voice"] != null) {
            vb.append(vbdetail["voice"])
        }
        if (vbdetail["mood"] != null) {
            vb.append(vbdetail["mood"])
        }
        if (vbdetail["verbmood"] != null) {
            holder.moodrules?.visibility = View.VISIBLE
            holder.moodrules?.text = vbdetail["verbmood"]
            //  holder.moodrules.setTextSize(arabicFontsize);
        }
        if (vb.length > 2) {
            holder.verbdetails.visibility = View.VISIBLE
            holder.verbdetails.text = vb.toString()
            //  holder.verbdetails.setTextSize(arabicFontsize);
        }
        holder.referenceView.text =
            corpusexpand?.get(0)!!.corpus.surah.toString() + ":" + corpusexpand!![0].corpus
                .ayah + ":" + corpusexpand!![0].corpus.wordno
        val worddetail: SpannableStringBuilder? = worddetails["worddetails"]
        //  holder.wdetailstv.setText(worddetail, TextView.BufferType.SPANNABLE);
        holder.wdetailstv.text = worddetail
        //   holder.referenceView.setTextSize(arabicFontsize);
        holder.wdetailstv.textSize = 16f
        if (worddetails["lemma"] != null || worddetails["lemma"]!!.isNotEmpty()) {
            holder.lemma.visibility = View.VISIBLE
            holder.lemma.text = NewRootWordDisplayAdapter.LEMMA + worddetails["lemma"]
            //        holder.lemma.setTextSize(arabicFontsize);
        }
        if (worddetails["root"] != null) {
            holder.rootView.text = NewRootWordDisplayAdapter.ROOTWORDSTRING + worddetails["root"]
            //    holder.rootView.setTextSize(arabicFontsize);
        }
        if (vbdetail["root"] != null) {
            holder.rootView.text = NewRootWordDisplayAdapter.ROOTWORDSTRING + vbdetail["root"]
            //    holder.rootView.setTextSize(arabicFontsize);
        }
        if (isSarfSagheerMazeed || isSarfSagheerThulahi) {
            val zarf: StringBuilder = StringBuilder()
            val ismala: StringBuilder = StringBuilder()
            holder.mamaroof?.text = sagheer.madhi
            holder.mumaroof?.text = sagheer.mudharay
            holder.ismfail?.text = sagheer.ismfael
            holder.mamajhool?.text = sagheer.madhimajhool
            holder.mumajhool?.text = sagheer.mudharaymajhool
            holder.ismmafool?.text = sagheer.ismmafool
            holder.amr?.text = sagheer.amrone
            holder.nahiamr?.text = sagheer.nahiamrone
            holder.ismzarfheader?.text = zarfheader
            holder.ismalaheader?.text = alaheader
            ismala.append(sagheer.ismalaone as CharSequence?).append(", ").append(
                sagheer.ismalatwo
            ).append(", ").append(
                sagheer.ismalathree
            )
            zarf.append(sagheer.zarfone as CharSequence?).append(", ")
                .append(sagheer.zarftwo).append(", ").append(
                    sagheer.zarfthree
                )
            holder.ismzarf?.text = zarf
            holder.ismala?.text = ismala
            holder.weaknessname?.text = sagheer.weakness
            holder.rootword?.text = sagheer.verbroot
            holder.babdetails?.text = sagheer.wazanname
        }
        fontSizeSelection(holder)
        Fonttypeface(holder)
        //   VerbHeader(holder);
        if (particples) {
            val ismfael = ismfaelmafool[0][0]
            val ismmafool = ismfaelmafool[0][1]
            setTypeFace(holder)
            ismFael(ismfael as FaelMafool, holder)
            ismFaelFem(ismfael as FaelMafool, holder)
            ismMafool(ismmafool as FaelMafool, holder)
            ismMafoolFem(ismmafool as FaelMafool, holder)
            gcase(holder)
            ismfaelmafoolnumbers(holder)
            fontSIzeSelection(holder)
            val array: Array<String>
            val language: String? = sharedPreferences.getString("lan", "en")
            array = if ((language == "en")) {
                QuranGrammarApplication.context!!.resources
                    .getStringArray(R.array.enismfaelmafoolheadings)
            } else {
                QuranGrammarApplication.context!!.resources
                    .getStringArray(R.array.arismfaelmafoolheadings)
            }
            holder.apmas?.text = array[0]
            holder.apfem?.text = array[1]
            holder.ppmas?.text = array[2]
            holder.ppfem?.text = array[3]
            holder.apmas?.textSize = arabicFontsize!!.toFloat()
            holder.apfem?.textSize = arabicFontsize!!.toFloat()
            holder.apfem?.gravity = View.TEXT_ALIGNMENT_CENTER
            holder.ppmas?.textSize = arabicFontsize!!.toFloat()
            holder.ppfem?.textSize = arabicFontsize!!.toFloat()
        }
    }


    override fun getItemCount(): Int {
        return corpusexpand!!.size
    }

    private fun gcase(holder: NewRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        //  String theme = sharedPreferences.getString("theme", 1);
        val language: String? = sharedPreferences.getString("lang", "en")
        //     String language = SharedPref.getLanguage();
        val isTraditional: Boolean = true
        val array: Array<String>
        array = if ((language == "en")) QuranGrammarApplication.context!!.resources
            .getStringArray(R.array.encase) else {
            QuranGrammarApplication.context!!.resources.getStringArray(R.array.arcase)
        }
        holder.nom?.text = array[0]
        holder.acc?.text = array[1]
        holder.gen?.text = array[2]
        holder.nom1?.text = array[0]
        holder.acc1?.text = array[1]
        holder.gen1?.text = array[2]
        holder.nom2?.text = array[0]
        holder.acc2?.text = array[1]
        holder.gen2?.text = array[2]
        holder.nom3?.text = array[0]
        holder.acc3?.text = array[1]
        holder.gen3?.text = array[2]
    }

    private fun ismfaelmafoolnumbers(holder: NewRootWordDisplayAdapter.ItemViewAdapter) {
        val language: String? = sharedPreferences.getString("lan", "en")
        val isTraditional: Boolean = true
        val array: Array<String>
        array = if ((language == "en")) context.resources.getStringArray(R.array.ennumbers) else {
            context.resources.getStringArray(R.array.arnumbers)
        }
        holder.sin1?.text = array[0]
        holder.dual1?.text = array[1]
        holder.plu1?.text = array[2]
        holder.sin2?.text = array[0]
        holder.dual2?.text = array[1]
        holder.plu2?.text = array[2]
        holder.sin3?.text = array[0]
        holder.dual3?.text = array[1]
        holder.plu3?.text = array[2]
        holder.sin4?.text = array[0]
        holder.dual4?.text = array[1]
        holder.plu4?.text = array[2]
    }

    private fun ismFael(
        ismfael: FaelMafool,
        holder: ItemViewAdapter,
    ) {

        val iisone = ismfael.nomsinM!!.replace("[", "").replace("]", "");//[0].toString() //isone);
        iisone.replace("[\"\\[ \\]]", "")
        val iistwo = ismfael.nomdualM//[2].toString() //istwo);
        val iisthree = ismfael.nomplurarM//[4].toString() //isthree);
        val iisfour = ismfael.accsinM//[6].toString() //isfour);
        val iisfive = ismfael.accdualM//[8].toString() //isfive);
        val iissix = ismfael.accplurarlM//[10].toString() //issix);
        val iisseven = ismfael.gensinM//[12].toString() //isseven);
        val iiseight = ismfael.gendualM//[14].toString() //iseight);
        val iisnine = ismfael.genplurarM//[16].toString() //isnine);


        fontSIzeSelection(holder)
        setTypeFace(holder)
        holder.isone?.text = iisone
        holder.istwo?.text = iistwo
        holder.isthree?.text = iisthree
        holder.isfour?.text = iisfour
        holder.isfive?.text = iisfive
        holder.issix?.text = iissix
        holder.isseven?.text = iisseven
        holder.iseight?.text = iiseight
        holder.isnine?.text = iisnine
    }

    private fun ismFaelFem(
        ismfael: FaelMafool,
        holder: ItemViewAdapter,
    ) {
        val iisone = ismfael.nomsinF//[0].toString() //isone);
        val iistwo = ismfael.nomdualF//[2].toString() //istwo);
        val iisthree = ismfael.nomplurarF//[4].toString() //isthree);
        val iisfour = ismfael.accsinF//[6].toString() //isfour);
        val iisfive = ismfael.accdualF//[8].toString() //isfive);
        val iissix = ismfael.accplurarlF//[10].toString() //issix);
        val iisseven = ismfael.gensinF//[12].toString() //isseven);
        val iiseight = ismfael.gendualF//[14].toString() //iseight);
        val iisnine =
            ismfael.genplurarF!!.replace("[", "").replace("]", "")//[16].toString() //isnine);


        fontSIzeSelection(holder)
        setTypeFace(holder)
        holder.ismfemone?.text = iisone
        holder.ismfemtwo?.text = iistwo
        holder.ismfemthree?.text = iisthree
        holder.ismfemfour?.text = iisfour
        holder.ismfemfive?.text = iisfive
        holder.ismfemsix?.text = iissix
        holder.ismfemseven?.text = iisseven
        holder.ismfemeight?.text = iiseight
        holder.ismfemnine?.text = iisnine
    }

    private fun ismMafoolFem(ismmafoolFem: FaelMafool, holder: ItemViewAdapter) {

        val smafone = ismmafoolFem.nomsinF//[2].toString() //istwo);
        val smaftwo = ismmafoolFem.nomdualF
        val smafthree = ismmafoolFem.nomplurarF//[4].toString() //isthree);
        val smaffour = ismmafoolFem.accsinF//[6].toString() //isfour);
        val smaffive = ismmafoolFem.accdualF//[8].toString() //isfive);
        val smafsix = ismmafoolFem.accplurarlF//[10].toString() //issix);
        val smafseven = ismmafoolFem.gensinF//[12].toString() //isseven);
        val smafeight = ismmafoolFem.gendualF//[14].toString() //iseight);
        val smafnine =
            ismmafoolFem.genplurarF!!.replace("[", "").replace("]", "")//[16].toString() //isnine);

        fontSIzeSelection(holder)
        setTypeFace(holder)
        holder.imafoolfemone?.text = smafone
        holder.imafoolfemtwo?.text = smaftwo
        holder.imafoolfemthree?.text = smafthree
        holder.imafoolfemfour?.text = smaffour
        holder.imafoolfemfive?.text = smaffive
        holder.imafoolfemsix?.text = smafsix
        holder.imafoolfemseven?.text = smafseven
        holder.imafoolfemeight?.text = smafeight
        holder.imafoolfemnine?.text = smafnine
    }

    private fun ismMafool(
        ismmafoolMas: FaelMafool,
        holder: ItemViewAdapter,
    ) {
        val smafone =
            ismmafoolMas.nomsinM!!.replace("[", "").replace("]", "")//[2].toString() //istwo);
        val smaftwo = ismmafoolMas.nomdualM
        val smafthree = ismmafoolMas.nomplurarM//[4].toString() //isthree);
        val smaffour = ismmafoolMas.accsinM//[6].toString() //isfour);
        val smaffive = ismmafoolMas.accdualM//[8].toString() //isfive);
        val smafsix = ismmafoolMas.accplurarlM//[10].toString() //issix);
        val smafseven = ismmafoolMas.gensinM//[12].toString() //isseven);
        val smafeight = ismmafoolMas.gendualM//[14].toString() //iseight);
        val smafnine = ismmafoolMas.genplurarM//[16].toString() //isnine);

        fontSIzeSelection(holder)
        setTypeFace(holder)
        holder.imafone?.text = smafone
        holder.imaftwo?.text = smaftwo
        holder.imafthree?.text = smafthree
        holder.imaffour?.text = smaffour
        holder.imaffive?.text = smaffive
        holder.imafsix?.text = smafsix
        holder.imafseven?.text = smafseven
        holder.imafeight?.text = smafeight
        holder.imafnine?.text = smafnine
    }

    private fun setTypeFace(holder: NewRootWordDisplayAdapter.ItemViewAdapter) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        //  Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());
        //  Typeface arabicTypeface = Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), "Taha.ttf");
        val sharedPreferences: SharedPreferences =
            android.preference.PreferenceManager.getDefaultSharedPreferences(
                context
            )
        val arabic_font_selection: String? =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val arabicTypeface: Typeface = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )

        //   String s = SharedPref.arabicFontSelection();
        val isTraditional: Boolean = true
        holder.nom?.typeface = arabicTypeface // (array[0]);
        holder.acc?.typeface = arabicTypeface // (array[1]);
        holder.gen?.typeface = arabicTypeface // (array[2]);
        holder.nom1?.typeface = arabicTypeface // (array[0]);
        holder.acc1?.typeface = arabicTypeface // (array[1]);
        holder.gen1?.typeface = arabicTypeface // (array[2]);
        holder.nom2?.typeface = arabicTypeface // (array[0]);
        holder.acc2?.typeface = arabicTypeface // (array[1]);
        holder.gen2?.typeface = arabicTypeface // (array[2]);
        holder.nom3?.typeface = arabicTypeface // (array[0]);
        holder.acc3?.typeface = arabicTypeface // (array[1]);
        holder.gen3?.typeface = arabicTypeface // (array[2]);
        holder.sin1?.typeface = arabicTypeface //(array[0]);
        holder.dual1?.typeface = arabicTypeface //(array[1]);
        holder.plu1?.typeface = arabicTypeface //(array[2]);
        holder.sin2?.typeface = arabicTypeface //(array[0]);
        holder.dual2?.typeface = arabicTypeface //(array[1]);
        holder.plu2?.typeface = arabicTypeface //(array[2]);
        holder.sin3?.typeface = arabicTypeface //(array[0]);
        holder.dual3?.typeface = arabicTypeface //(array[1]);
        holder.plu3?.typeface = arabicTypeface //(array[2]);
        holder.sin4?.typeface = arabicTypeface //(array[0]);
        holder.dual4?.typeface = arabicTypeface //(array[1]);
        holder.plu4?.typeface = arabicTypeface //(array[2]);
        holder.imafone?.typeface = arabicTypeface //;//smafone);
        holder.imaftwo?.typeface = arabicTypeface //;//smaftwo);
        holder.imafthree?.typeface = arabicTypeface //;//smafthree);
        holder.imaffour?.typeface = arabicTypeface //;//smaffour);
        holder.imaffive?.typeface = arabicTypeface //;//smaffive);
        holder.imafsix?.typeface = arabicTypeface //;//smafsix);
        holder.imafseven?.typeface = arabicTypeface //;//smafseven);
        holder.imafeight?.typeface = arabicTypeface //;//smafeight);
        holder.imafnine?.typeface = arabicTypeface //;//smafnine);
        //
        holder.imafoolfemone?.typeface = arabicTypeface //;//smafone);
        holder.imafoolfemtwo?.typeface = arabicTypeface //;//smaftwo);
        holder.imafoolfemthree?.typeface = arabicTypeface //;//smafthree);
        holder.imafoolfemfour?.typeface = arabicTypeface //;//smaffour);
        holder.imafoolfemfive?.typeface = arabicTypeface //;//smaffive);
        holder.imafoolfemsix?.typeface = arabicTypeface //;//smafsix);
        holder.imafoolfemseven?.typeface = arabicTypeface //;//smafseven);
        holder.imafoolfemeight?.typeface = arabicTypeface //;//smafeight);
        holder.imafoolfemnine?.typeface = arabicTypeface //;//smafnine);
        //
        holder.ismfemone?.typeface = arabicTypeface //;//iismfemone);
        holder.ismfemtwo?.typeface = arabicTypeface //;//iismfemtwo);
        holder.ismfemthree?.typeface = arabicTypeface //;//iismfemthree);
        holder.ismfemfour?.typeface = arabicTypeface //;//iismfemfour);
        holder.ismfemfive?.typeface = arabicTypeface //;//iismfemfive);
        holder.ismfemsix?.typeface = arabicTypeface //;//iismfemsix);
        holder.ismfemseven?.typeface = arabicTypeface //;//iismfemseven);
        holder.ismfemeight?.typeface = arabicTypeface //;//iismfemeight);
        holder.ismfemnine?.typeface = arabicTypeface //;//iismfemnine);
        holder.isone?.typeface = arabicTypeface //;//iisone);
        holder.istwo?.typeface = arabicTypeface //;//iistwo);
        holder.isthree?.typeface = arabicTypeface //;//iisthree);
        holder.isfour?.typeface = arabicTypeface //;//iisfour);
        holder.isfive?.typeface = arabicTypeface //;//iisfive);
        holder.issix?.typeface = arabicTypeface //;//iissix);
        holder.isseven?.typeface = arabicTypeface //;//iisseven);
        holder.iseight?.typeface = arabicTypeface //;//iiseight);
        holder.isnine?.typeface = arabicTypeface //;//iisnine);
    }

    private fun fontSIzeSelection(holder: NewRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val arabicFontsize: Int = sharedPreferences.getInt("pref_font_arabic_key", 20)
        val isTraditional: Boolean = true
        holder.nom?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.acc?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.gen?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.nom1?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.acc1?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.gen1?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.nom2?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.acc2?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.gen2?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.nom3?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.acc3?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.gen3?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.sin1?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.dual1?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.plu1?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.sin2?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.dual2?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.plu2?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.sin3?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.dual3?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.plu3?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.sin4?.textSize = arabicFontsize.toFloat() //(array[0]);
        holder.dual4?.textSize = arabicFontsize.toFloat() //(array[1]);
        holder.plu4?.textSize = arabicFontsize.toFloat() //(array[2]);
        holder.imafone?.textSize = arabicFontsize.toFloat() //smafone);
        holder.imaftwo?.textSize = arabicFontsize.toFloat() //smaftwo);
        holder.imafthree?.textSize = arabicFontsize.toFloat() //smafthree);
        holder.imaffour?.textSize = arabicFontsize.toFloat() //smaffour);
        holder.imaffive?.textSize = arabicFontsize.toFloat() //smaffive);
        holder.imafsix?.textSize = arabicFontsize.toFloat() //smafsix);
        holder.imafseven?.textSize = arabicFontsize.toFloat() //smafseven);
        holder.imafeight?.textSize = arabicFontsize.toFloat() //smafeight);
        holder.imafnine?.textSize = arabicFontsize.toFloat() //smafnine);
        //
        holder.imafoolfemone?.textSize = arabicFontsize.toFloat() //smafone);
        holder.imafoolfemtwo?.textSize = arabicFontsize.toFloat() //smaftwo);
        holder.imafoolfemthree?.textSize = arabicFontsize.toFloat() //smafthree);
        holder.imafoolfemfour?.textSize = arabicFontsize.toFloat() //smaffour);
        holder.imafoolfemfive?.textSize = arabicFontsize.toFloat() //smaffive);
        holder.imafoolfemsix?.textSize = arabicFontsize.toFloat() //smafsix);
        holder.imafoolfemseven?.textSize = arabicFontsize.toFloat() //smafseven);
        holder.imafoolfemeight?.textSize = arabicFontsize.toFloat() //smafeight);
        holder.imafoolfemnine?.textSize = arabicFontsize.toFloat() //smafnine);
        //
        holder.ismfemone?.textSize = arabicFontsize.toFloat() //iismfemone);
        holder.ismfemtwo?.textSize = arabicFontsize.toFloat() //iismfemtwo);
        holder.ismfemthree?.textSize = arabicFontsize.toFloat() //iismfemthree);
        holder.ismfemfour?.textSize = arabicFontsize.toFloat() //iismfemfour);
        holder.ismfemfive?.textSize = arabicFontsize.toFloat() //iismfemfive);
        holder.ismfemsix?.textSize = arabicFontsize.toFloat() //iismfemsix);
        holder.ismfemseven?.textSize = arabicFontsize.toFloat() //iismfemseven);
        holder.ismfemeight?.textSize = arabicFontsize.toFloat() //iismfemeight);
        holder.ismfemnine?.textSize = arabicFontsize.toFloat() //iismfemnine);
        holder.isone?.textSize = arabicFontsize.toFloat() //iisone);
        holder.istwo?.textSize = arabicFontsize.toFloat() //iistwo);
        holder.isthree?.textSize = arabicFontsize.toFloat() //iisthree);
        holder.isfour?.textSize = arabicFontsize.toFloat() //iisfour);
        holder.isfive?.textSize = arabicFontsize.toFloat() //iisfive);
        holder.issix?.textSize = arabicFontsize.toFloat() //iissix);
        holder.isseven?.textSize = arabicFontsize.toFloat() //iisseven);
        holder.iseight?.textSize = arabicFontsize.toFloat() //iiseight);
        holder.isnine?.textSize = arabicFontsize.toFloat() //iisnine);
    }

    private fun Fonttypeface(holder: NewRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val typeface: Typeface = Typeface.createFromAsset(
            context.assets, quranFont
        )
        if (isverbconjugation) {
            holder.mamaroof?.typeface = typeface
            holder.mumaroof?.typeface = typeface
            holder.masdaro?.typeface = typeface
            holder.masdart?.typeface = typeface
            holder.ismfail?.typeface = typeface
            holder.mamajhool?.typeface = typeface
            holder.mumajhool?.typeface = typeface
            holder.ismmafool?.typeface = typeface
            holder.amr?.typeface = typeface
            holder.nahiamr?.typeface = typeface
            holder.babdetails?.typeface = typeface
            holder.babdetails!!.setTextColor(wazancolor)
            holder.weaknesstype?.typeface = typeface
            holder.weaknesstype!!.setTextColor(weaknesscolor)
            holder.weaknessname?.typeface = typeface
            holder.weaknessname!!.setTextColor(rootcolor)
        }
    }

    private fun fontSizeSelection(holder: NewRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val fontsize: Int = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (isverbconjugation) {
            holder.mamaroof?.textSize = fontsize.toFloat()
            holder.mumaroof?.textSize = fontsize.toFloat()
            holder.masdaro?.textSize = fontsize.toFloat()
            holder.masdart?.textSize = fontsize.toFloat()
            holder.ismfail?.textSize = fontsize.toFloat()
            holder.mamajhool?.textSize = fontsize.toFloat()
            holder.mumajhool?.textSize = fontsize.toFloat()
            holder.ismmafool?.textSize = fontsize.toFloat()
            holder.amr?.textSize = fontsize.toFloat()
            holder.nahiamr?.textSize = fontsize.toFloat()
            holder.babdetails?.textSize = fontsize.toFloat()
            holder.babdetails!!.setTextColor(Color.YELLOW)
            holder.weaknesstype?.textSize = fontsize.toFloat()
            holder.weaknesstype!!.setTextColor(Color.BLUE)
            holder.weaknessname?.textSize = fontsize.toFloat()
            holder.weaknessname!!.setTextColor(Color.GREEN)
        }
    }


    fun setRootWordsAndMeanings(
        haliaSentence: ArrayList<HalEnt>?,
        tameez: ArrayList<TameezEnt>?,
        mafoolbihi: ArrayList<MafoolBihi>?,
        mutlaq: ArrayList<MafoolMutlaqEnt>?,
        liajlihi: ArrayList<LiajlihiEnt>?,
        verb: Boolean,
        wazannumberslist: ArrayList<String>?,
        spannableStringBuilder: SpannableStringBuilder?,
        noun: Boolean,
        ismfaelmafool: ArrayList<ArrayList<*>>?,
        participles: Boolean,
        isverbconjugation: Boolean,
        corpusSurahWord: ArrayList<QuranCorpusWbw>?,
        wordbdetail: HashMap<String, SpannableStringBuilder?>?,
        vbdetail: HashMap<String, String?>?,
        isSarfSagheer: Boolean,
        isSarfSagheerThulahi: Boolean,
        sarfsagheer: ArrayList<SarfSagheer>?,
        context: Context,
    ) {
        this.haliaSentence = haliaSentence!!
        this.tameez = tameez
        this.mafoolbihi = mafoolbihi
        isverb = verb
        this.wazannumberslist = wazannumberslist!!
        spannable = spannableStringBuilder!!
        isnoun = noun
        this.ismfaelmafool = ismfaelmafool!!
        particples = participles
        this.isverbconjugation = isverbconjugation
        corpusexpand = corpusSurahWord
        worddetails = wordbdetail!!
        this.vbdetail = vbdetail!!
        isSarfSagheerMazeed = isSarfSagheer
        this.sarfsagheer = sarfsagheer
        this.isSarfSagheerThulahi = isSarfSagheerThulahi
        this.mutlaq = mutlaq
        this.liajlihi = liajlihi
        this.context = context
    }

    inner class ItemViewAdapter(view: View, viewType: Int) :
        RecyclerView.ViewHolder(view), View.OnClickListener // current clickListerner
    {
        val amr: TextView?
        val nahiamr: TextView?
        val ismfail: TextView?
        val mumaroof: TextView?
        val mamaroof: TextView?
        val ismala: TextView?
        val ismmafool: TextView?
        val mumajhool: TextView?
        val mamajhool: TextView?
        val ismzarf: TextView?

        //ISMFAEL
        val isone: TextView?
        val istwo: TextView?
        val isthree: TextView?
        val isfour: TextView?
        val isfive: TextView?
        val issix: TextView?
        val isseven: TextView?
        val iseight: TextView?
        val isnine: TextView?
        val ismfemone: TextView?
        val ismfemtwo: TextView?
        val ismfemthree: TextView?
        val ismfemfour: TextView?
        val ismfemfive: TextView?
        val ismfemsix: TextView?
        val ismfemseven: TextView?
        val ismfemeight: TextView?
        val ismfemnine: TextView?
        val imafone: TextView?
        val imaftwo: TextView?
        val imafthree: TextView?
        val imaffour: TextView?
        val imaffive: TextView?
        val imafsix: TextView?
        val imafseven: TextView?
        val imafeight: TextView?
        val imafnine: TextView?
        val imafoolfemone: TextView?
        val imafoolfemtwo: TextView?
        val imafoolfemthree: TextView?
        val imafoolfemfour: TextView?
        val imafoolfemfive: TextView?
        val imafoolfemsix: TextView?
        val imafoolfemseven: TextView?
        val imafoolfemeight: TextView?
        val imafoolfemnine: TextView?
        private val mifalone: TextView?
        private val mifaltwo: TextView?
        private val mifalthree: TextView?
        private val mifalfour: TextView?
        private val mifalfive: TextView?
        private val mifalsix: TextView?
        private val mifalseven: TextView?
        private val mifaleight: TextView?
        private val mifalnine: TextView?
        private val mifalatunone: TextView?
        private val mifalatuntwo: TextView?
        private val mifalatunthree: TextView?
        private val mifalatunfour: TextView?
        private val mifalatunfive: TextView?
        private val mifalatunsix: TextView?
        private val mifalatunseven: TextView?
        private val mifalatuneight: TextView?
        private val mifalatunnine: TextView?
        private val mifaalone: TextView?
        private val mifaaltwo: TextView?
        private val mifaalthree: TextView?
        private val mifaalfour: TextView?
        private val mifaalfive: TextView?
        private val mifaalsix: TextView?
        private val mifaalseven: TextView?
        private val mifaaleight: TextView?
        private val mifaalnine: TextView?
        private val mafalunone: TextView?
        private val mafaluntwo: TextView?
        private val mafalunthree: TextView?
        private val mafalunfour: TextView?
        private val mafalunfive: TextView?
        private val mafalunsix: TextView?
        private val mafalunseven: TextView?
        private val mafaluneight: TextView?
        private val mafalunnine: TextView?
        val sin1: TextView?
        val dual1: TextView?
        val plu1: TextView?
        val sin2: TextView?
        val dual2: TextView?
        val plu2: TextView?
        val sin3: TextView?
        val dual3: TextView?
        val plu3: TextView?
        val sin4: TextView?
        val dual4: TextView?
        val plu4: TextView?
        val nom: TextView?
        val acc: TextView?
        val gen: TextView?
        val nom1: TextView?
        val acc1: TextView?
        val gen1: TextView?
        val nom2: TextView?
        val acc2: TextView?
        val gen2: TextView?
        val nom3: TextView?
        val acc3: TextView?
        val gen3: TextView?
        private var wordDictionary: TextView? = null
        var moodrules: TextView? = null

        //  val triroot: Chip
        // // val paradigm: Chip
        //  val rootdetails: Chip
        //val verb: Chip
        val referenceView: TextView
        val wdetailstv: TextView
        val lemma: TextView
        val verbdetails: TextView
        val noundetails: TextView
        val pronoundetails: TextView
        val translationView: TextView
        val mazeedmeaning: TextView
        val rootView: TextView
        val quranverseShart: TextView
        val spannableverse: TextView
        var rootword: TextView? = null
        var wazan: TextView? = null
        var ismzarfheader: TextView? = null
        var ismalaheader: TextView? = null
        var masdaro: TextView? = null
        var masdart: TextView? = null
        var babdetails: TextView? = null
        var weaknessname: TextView? = null
        var weaknesstype: TextView? = null
        var mafoolat: TextView? = null
        var liajlihitv: TextView? = null
        var mutlaqtv: TextView? = null
        val sheet: View
        val wordView: Chip
        var haliaSentence: TextView? = null
        private val darkThemeBacground: MaterialCardView

        //  ListView list;
        val radioGroup: RadioGroup
        val rdone: RadioButton
        val rdtwo: RadioButton
        val rdthree: RadioButton
        val rdfour: RadioButton
        val dismissview: ImageView
        val apmas: TextView?
        val apfem: TextView?
        val ppmas: TextView?
        val ppfem: TextView?
        val verbconjugationbtn: TextView
        val nounoccurancebtn: TextView
        val tameeztv: TextView?

        //val expandable: ConstraintLayout
        private var ifverborpart: MaterialCardView? = null

        init {

            ifverborpart = view.findViewById(R.id.ifverborpart)
            moodrules = itemView.findViewById(R.id.moodrules)
            mazeedmeaning = itemView.findViewById(R.id.mazeedmeaning)
            darkThemeBacground = itemView.findViewById(R.id.jumptoverse)
            rdone = view.findViewById(R.id.rdone)
            rdtwo = view.findViewById(R.id.rdtwo)
            rdthree = view.findViewById(R.id.rdthree)
            rdfour = view.findViewById(R.id.rdfour)
            radioGroup = view.findViewById(R.id.rdgroup)

            wordDictionary = view.findViewById(R.id.wordDictionary)
            spannableverse = view.findViewById(R.id.spannableverse)
            quranverseShart = view.findViewById(R.id.quranverseShart)
            verbconjugationbtn = view.findViewById(R.id.verbconjugationbtn)
            //   verbOccurancebtn = view.findViewById(R.id.verboccurance);
            nounoccurancebtn = view.findViewById(R.id.wordoccurance)
            rootword = view.findViewById(R.id.weaknesstype)
            ismzarfheader = view.findViewById(R.id.ismzarfheader)
            pronoundetails = view.findViewById(R.id.pronoundetails)
            noundetails = view.findViewById(R.id.noundetails)
            sheet = view.findViewById(R.id.sheet)
            wdetailstv = view.findViewById(R.id.wordDetails)
            lemma = view.findViewById(R.id.lemma)
            verbdetails = view.findViewById(R.id.verbdetails)
            dismissview = view.findViewById(R.id.dismissView)
            referenceView = view.findViewById(R.id.referenceView)
            liajlihitv = view.findViewById(R.id.liajlihi)
            mutlaqtv = view.findViewById(R.id.mutlaq)
            tameeztv = view.findViewById(R.id.tameez)
            mafoolat = view.findViewById(R.id.mafoolat)
            haliaSentence = view.findViewById(R.id.haliya)
            wordView = view.findViewById(R.id.wordView)
            translationView = view.findViewById(R.id.translationView)
            rootView = view.findViewById(R.id.rootView)
            ismalaheader = view.findViewById(R.id.ismalaheader)
            ismala = view.findViewById(R.id.ismaalatable)
            wazan = view.findViewById(R.id.wazan)
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
            ismzarf = view.findViewById(R.id.zarftable)
            babdetails = view.findViewById(R.id.babno)
            weaknesstype = view.findViewById(R.id.weaknesstype)
            weaknessname = view.findViewById(R.id.weknessname)
            spannableverse.setOnClickListener(this)
            wordView.setOnClickListener(this)
            if (isverbconjugation || particples) {
                ifverborpart!!.visibility = View.VISIBLE
                verbconjugationbtn.setOnClickListener(this)
                //     verbOccurancebtn.setOnClickListener(this);
                nounoccurancebtn.setOnClickListener(this)
                mazeedmeaning.setOnClickListener(this)
            } else if (isnoun) {
                //  verbOccurancebtn.setEnabled(false);
                verbconjugationbtn.setOnClickListener(this)
                //          verbOccurancebtn.setOnClickListener(this);
                nounoccurancebtn.setOnClickListener(this)
            }
            sin4 = view.findViewById(R.id.singular4)
            dual4 = view.findViewById(R.id.dual4)
            plu4 = view.findViewById(R.id.plural4)
            //    }
            nom = view.findViewById(R.id.nominative)
            acc = view.findViewById(R.id.accusative)
            gen = view.findViewById(R.id.genitive)
            nom1 = view.findViewById(R.id.nominative1)
            acc1 = view.findViewById(R.id.accusative1)
            gen1 = view.findViewById(R.id.genitive1)
            nom2 = view.findViewById(R.id.nominative2)
            acc2 = view.findViewById(R.id.accusative2)
            gen2 = view.findViewById(R.id.genitive2)
            nom3 = view.findViewById(R.id.nominative3)
            acc3 = view.findViewById(R.id.accusative3)
            gen3 = view.findViewById(R.id.genitive3)
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
            ismfemone = view.findViewById(R.id.ismfemone)
            if (particples) {
                ismfemone.setText(R.string.faelmazi)
            }
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
            mifalone = view.findViewById(R.id.mifalone)
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
        }

        override fun onClick(v: View) {
            mItemClickListener?.onItemClick(v, layoutPosition)
        }
    }

    companion object {
        private const val TAG = "VerseDisplayAdapter"
        private const val ROOTWORDSTRING = "Root Word:-"
        private const val LEMMA = "Lemma/Derivative-"
    }
}

