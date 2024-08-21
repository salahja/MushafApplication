package com.refactoring



import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.text.Html
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.format.DateFormat
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.Constant
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.SurahSummary
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.mushafconsolidated.fragments.WordMorphologyDetails

import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.model.CorpusAyahWord
import com.example.mushafconsolidated.model.CorpusWbwWord
import com.example.mushafconsolidatedimport.Config

import com.example.mushafconsolidatedimport.ParticleColorScheme
import com.example.utility.AnimationUtility
import com.example.utility.CorpusUtilityorig
import com.example.utility.FlowLayout
import com.example.utility.QuranGrammarApplication
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.tooltip.Tooltip
import sj.hisnul.fragments.NamesDetail
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
//public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListenerOnLong {
class FlowAyahWordAdapter(
    isaudio: Boolean,

    passage: LinkedHashMap<Int, ArrayList<CorpusWbwWord>>,
    private val mutlaqent: List<MafoolMutlaqEnt?>?,
    private val tameezEnts: List<TameezEnt?>?,
    private val badalErabNotesEnt: List<BadalErabNotesEnt?>?,
    private val liajlihient: List<LiajlihiEnt?>?,
    private val jumlahaliya: List<HalEnt?>?,
    private val mafoolBihis: List<MafoolBihi?>?,
    private val header: ArrayList<String?>,
    private val allofQuran: List<QuranEntity?>?,
    private val ayahWordArrayList: ArrayList<CorpusAyahWord>?,
    var context: Context,
    val surah_id: Long,
    private val SurahName: String,
    private val isMakkiMadani: Int,
    listener: OnItemClickListenerOnLong?
) : RecyclerView.Adapter<FlowAyahWordAdapter.ItemViewAdapter>() //implements OnItemClickListenerOnLong {
{
    private var defaultfont: Boolean = false
    private var isFABOpen = false
    private var issentence: Boolean = false
    lateinit var rootword: TextView;

    //MaterialTextView arabic;
    lateinit var arabicChipview: Chip
    lateinit var  arabicTv: MaterialTextView
    var arabicfontSize: Int = 0
    var translationfontsize: Int = 0
    lateinit var  mItemClickListener: OnItemClickListenerOnLong
    private lateinit var  isNightmode: String
    private var headercolor = 0

    private lateinit var  colorwordfont: Typeface
    private lateinit var  ayahWord: CorpusAyahWord
    private val isaudio: Boolean

    init {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab)
        issentence = sharedPreferences.getBoolean("grammarsentence", false)
        arabicfontSize = sharedPreferences.getInt("pref_font_arabic_key", 18)
        translationfontsize = sharedPreferences.getInt("pref_font_englsh_key", 18)
        defaultfont = sharedPreferences.getBoolean("default_font", true)
        if (listener != null) {
            mItemClickListener = listener
        }
        this.isaudio = isaudio
    }

    fun addContext(context: Context) {
        this.context = context
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return ayahWordArrayList!!.size + 1
        //     return  quran.size();
    }

    override fun getItemId(position: Int): Long {
        val ayahWord = ayahWordArrayList!![position]
        var itemId: Long = 0
        for (word in ayahWord.word!!) {
            itemId = word.verseId.toLong()
        }
        return itemId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        view = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.surah_header, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.row_ayah_word, parent, false)
            //   view = LayoutInflater.from(parent.context!!).inflate(R.layout.item_viewer_aya_cardview, parent, false);
        }
        return ItemViewAdapter(view, viewType)
    }

    fun getItem(position: Int): Any {
        return ayahWordArrayList!![0].word!![position]
        //    return allofQuran.get(position);
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        isNightmode = sharedPreferences.getString("themepref", "dark").toString()
        //  String arabic_font_selection = sharedPreferences.getString("Arabic_Font_Selection", String.valueOf(MODE_PRIVATE));
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "quranicfontregular.ttf")
        val custom_font = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )
        val FONTS_LOCATION_PATH = "fonts/DejaVuSans.ttf"
        colorwordfont = Typeface.createFromAsset(
            QuranGrammarApplication.context!!.assets,
            FONTS_LOCATION_PATH
        )
        val showrootkey = sharedPreferences.getBoolean("showrootkey", true)
        val showErab = sharedPreferences.getBoolean("showErabKey", true)
        val showWordColor = sharedPreferences.getBoolean("colortag", true)
        val showTransliteration = sharedPreferences.getBoolean("showtransliterationKey", true)
        val showJalalayn = sharedPreferences.getBoolean("showEnglishJalalayn", true)
        val showTranslation = sharedPreferences.getBoolean("showTranslationKey", true)
        val showWordByword = sharedPreferences.getBoolean("wordByWord", false)
        val showKathir = sharedPreferences.getBoolean("showKathir", false)
        //bg_black
        if (position % 2 == 1) {
            when (isNightmode) {
                "brown" -> holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.bg_brown
                    )
                )

                "dark" -> holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.odd_item_bg_black
                    )
                )

                "blue" -> holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.bg_dark_blue
                    )
                )
            }
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            when (isNightmode) {
                "brown" -> holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.odd_item_bg_brown
                    )
                )

                "dark" -> holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.bg_black
                    )
                )

                "blue" -> holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.bg_dark_blue
                    )
                )
            }
        }
        val whichtranslation = sharedPreferences.getString("selecttranslation", "en_sahih")
        if (getItemViewType(position) == 0) {
            val imgs = context.resources.obtainTypedArray(R.array.sura_imgs)

            // You have to set your header items values with the help of model class and you can modify as per your needs
            holder.tvRukus!!.text = String.format("Ruku's :%s", header[0])
            holder.tvVerses!!.text = String.format("Aya's :%s", header[1])
            holder.tvSura!!.text = header[3]
            val chapterno = header[2]
            val tauba = chapterno!!.toInt()
            if (tauba == 9) {
                holder.ivBismillah!!.visibility = View.GONE
            }
            if (isMakkiMadani == 1) {
                holder.ivLocationmakki!!.visibility = View.VISIBLE
                holder.ivLocationmadani!!.visibility = View.GONE
            } else {
                holder.ivLocationmadani!!.visibility = View.VISIBLE
                holder.ivLocationmakki!!.visibility = View.GONE
            }
            val drawable = imgs.getDrawable(chapterno.toInt() - 1)
            imgs.recycle()
            holder.ivSurahIcon!!.setImageDrawable(drawable)
            if (isNightmode == "dark" || isNightmode == "blue" || isNightmode == "green") {
                headercolor = Color.YELLOW
                holder.ivLocationmakki!!.setColorFilter(Color.CYAN)
                holder.ivSurahIcon!!.setColorFilter(Color.CYAN)
                holder.ivLocationmadani!!.setColorFilter(Color.CYAN)
            } else {
                headercolor = Color.BLUE
                holder.ivLocationmakki!!.setColorFilter(Color.BLACK)
                holder.ivSurahIcon!!.setColorFilter(Color.BLACK)
                holder.ivLocationmadani!!.setColorFilter(Color.BLACK)
            }
        } else {
            displayAyats(
                showrootkey,
                holder,
                position - 1,
                sharedPreferences,
                custom_font,
                showErab,
                showWordColor,
                showTransliteration,
                showJalalayn,
                showTranslation,
                showWordByword,
                whichtranslation,
                showKathir
            )
        }
    }

    private fun displayAyats(
        showrootkey: Boolean,
        holder: ItemViewAdapter,
        position: Int,
        sharedPreferences: SharedPreferences,
        custom_font: Typeface,
        showErab: Boolean,
        showWordColor: Boolean,
        showTransliteration: Boolean,
        showJalalayn: Boolean,
        showTranslation: Boolean,
        showWordByword: Boolean,
        whichtranslation: String?,
        showKathir: Boolean
    ) {
        //   holder.flowwbw.setBackgroundColor(R.style.Theme_DarkBlue);
        var entity: QuranEntity? = null
        val wbw = sharedPreferences.getString("wbw", "en")

        //   String wbw = sharedPreferences.getString("wordByWord", String.valueOf(Context.MODE_PRIVATE));
        try {
            entity = allofQuran!![position]
        } catch (e: IndexOutOfBoundsException) {
            println("check")
        }
        var mafoolBihi: MafoolBihi? = null
        try {
            mafoolBihi = mafoolBihis!![position]
        } catch (e: IndexOutOfBoundsException) {
            println(e.message)
        }
        ayahWord = ayahWordArrayList!![position]
        entity?.let { storepreferences(it) }
        val quranverses = ayahWordArrayList[position].spannableverse
        //   if (!isaudio){
        holder.quran_textView!!.text = quranverses
        //   holder.quran_textView.setTextSize(arabicfontSize);
        holder.quran_textView!!.typeface = custom_font
        holder.base_cardview!!.visibility = View.GONE
        //  }
        val mf = SpannableStringBuilder ()
        val halsb = StringBuilder ()
        val tameezsb = StringBuilder ()
        val badalsb = StringBuilder ()
        val ajlihisb = StringBuilder ()
        val mutlaqsb = StringBuilder ()
        holder.mafoolbihi!!.visibility = View.GONE
        holder.base_cardview!!.visibility = View.GONE
        val spanhash = CorpusUtilityorig.stringForegroundColorSpanMap
        var mfcharSequence: CharSequence?
        if (mafoolBihi != null) {
            assert(entity != null)
            if (mafoolBihi.ayah == entity!!.ayah) {
                setUpMafoolbihistring(mf)
            }
        }
        //   finalstring.setSpan(FORESTGREEN,indexOfbihi, HAL.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (mafoolBihis != null) {
            for (ent in mafoolBihis) {
                assert(entity != null)
                if (ent!!.ayah == entity!!.ayah) {
                    val b = ent.objectpronoun == null
                    if (!b) {
                        val mafoolbihiverb = SpannableStringBuilder()
                        var objectpronoun = SpannableStringBuilder()
                        mafoolbihiverb.append(ent.word).append(" ")
                        objectpronoun = SpannableStringBuilder .valueOf(ent.objectpronoun)
                        //   objectpronoun.append("(").append("مفعول به").append(")");
                        val colorSpan = spanhash["V"]
                        val proncolospan = spanhash["PRON"]
                        assert(colorSpan != null)
                        mafoolbihiverb.setSpan(
                            ForegroundColorSpan(colorSpan!!.foregroundColor),
                            0,
                            mafoolbihiverb.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        assert(proncolospan != null)
                        objectpronoun.setSpan(
                            ForegroundColorSpan(proncolospan!!.foregroundColor),
                            0,
                            objectpronoun.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        mfcharSequence = TextUtils.concat(mafoolbihiverb, " ", objectpronoun)
                        mf.append(mfcharSequence).append("\n")
                    } else {
                        val mafoolbihiverb = SpannableStringBuilder ()
                        mafoolbihiverb.append(ent.word).append(" ")
                        //      mafoolbihiverb.append("(").append("مفعول به").append(")");
                        mafoolbihiverb.setSpan(
                            spanhash["N"],
                            0,
                            mafoolbihiverb.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        mfcharSequence = TextUtils.concat(mafoolbihiverb)
                        mf.append(mfcharSequence).append("\n")
                    }
                    //    mf.append(ent.getWord().trim()).append("\n");
                }
            }
        }
        //   if(!jumlahaliya.isEmpty()) {
        if (jumlahaliya != null) {
            for (ent in jumlahaliya) {
                if (entity != null && ent!!.ayah == entity.ayah) {
                    halsb.append(ent!!.text.trim { it <= ' ' }).append("\n")
                }
            }
        }
        if (tameezEnts != null) {
            for (ent in tameezEnts) {
                if (entity != null && ent!!.ayah == entity!!.ayah) {
                    tameezsb.append(ent.word.trim { it <= ' ' }).append("\n")
                }
            }
        }
        if (liajlihient != null) {
            for (ent in liajlihient) {
                if (entity != null && ent!!.ayah == entity.ayah) {
                    ajlihisb.append(ent!!.word.trim { it <= ' ' }).append("\n")
                }
            }
        }
        if (badalErabNotesEnt != null) {
            for (ent in badalErabNotesEnt) {
                if (entity != null && ent!!.ayah == entity.ayah) {
                    badalsb.append(ent.text.trim { it <= ' ' }).append("\n")
                }
            }
        }
        if (mutlaqent != null) {
            for (ent in mutlaqent) {
                if (entity != null && ent!!.ayah == entity.ayah) {
                    mutlaqsb.append(ent.word.trim { it <= ' ' }).append("\n")
                }
            }
        }
        //CharSequence charSequence = TextUtils.concat(harfspannble, " ", khabarofversespannable, " ", harfismspannable);
        var halspan = SpannableStringBuilder ()
        var tameezspan = SpannableStringBuilder ()
        var badalspan = SpannableStringBuilder ()
        var mutlaqspan = SpannableStringBuilder ()
        var ajlihispan = SpannableStringBuilder ()
        if (!halsb.toString().isEmpty()) {
            halsb.insert(0, Constant.HALHEADER)
            halspan = SpannableStringBuilder .valueOf(SpannableString.valueOf(halsb.toString()))
            val indexOfbihi1 = halspan.toString().indexOf(Constant.HAL)
            halspan.setSpan(
                ForegroundColorSpan(headercolor),
                indexOfbihi1,
                Constant.HAL.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            halspan.setSpan(
                RelativeSizeSpan(1f),
                indexOfbihi1,
                Constant.HAL.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            halspan.setSpan(
                Typeface.DEFAULT_BOLD,
                indexOfbihi1,
                Constant.HAL.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        if (!tameezsb.toString().isEmpty()) {
            tameezsb.insert(0, Constant.TAMEEZHEADER)
            tameezspan =
                SpannableStringBuilder .valueOf(SpannableString.valueOf(tameezsb.toString()))
            val indexOfbihi2 = tameezspan.toString().indexOf(Constant.TAMEEZ)
            tameezspan.setSpan(
                ForegroundColorSpan(headercolor),
                indexOfbihi2,
                Constant.TAMEEZ.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tameezspan.setSpan(
                RelativeSizeSpan(1f),
                indexOfbihi2,
                Constant.TAMEEZ.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tameezspan.setSpan(
                Typeface.DEFAULT_BOLD,
                indexOfbihi2,
                Constant.TAMEEZ.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        if (!ajlihisb.toString().isEmpty()) {
            ajlihisb.insert(0, Constant.AJLIHIHEADER)
            ajlihispan =
                SpannableStringBuilder .valueOf(SpannableString.valueOf(ajlihisb.toString()))
            val indexOfbihi3 = ajlihispan.toString().indexOf(Constant.AJLIHI)
            ajlihispan.setSpan(
                ForegroundColorSpan(headercolor),
                indexOfbihi3,
                Constant.AJLIHI.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            ajlihispan.setSpan(
                RelativeSizeSpan(1f),
                indexOfbihi3,
                Constant.AJLIHI.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            ajlihispan.setSpan(
                Typeface.DEFAULT_BOLD,
                indexOfbihi3,
                Constant.AJLIHI.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        if (!badalsb.toString().isEmpty()) {
            badalsb.insert(0, Constant.BADALHEADER)
            badalspan = SpannableStringBuilder .valueOf(SpannableString.valueOf(badalsb.toString()))
            val indexOfbihi4 = badalspan.toString().indexOf(Constant.BADAL)
            badalspan.setSpan(
                ForegroundColorSpan(headercolor),
                indexOfbihi4,
                Constant.BADAL.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            badalspan.setSpan(
                RelativeSizeSpan(1f),
                indexOfbihi4,
                Constant.BADAL.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            badalspan.setSpan(
                Typeface.DEFAULT_BOLD,
                indexOfbihi4,
                Constant.BADAL.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        if (!mutlaqsb.toString().isEmpty()) {
            mutlaqsb.insert(0, Constant.MUTLAQHEADER)
            mutlaqspan =
                SpannableStringBuilder .valueOf(SpannableString.valueOf(mutlaqsb.toString()))
            val indexOfbihi5 = mutlaqspan.toString().indexOf(Constant.MUTLAQ)
            mutlaqspan.setSpan(
                ForegroundColorSpan(headercolor),
                indexOfbihi5,
                Constant.MUTLAQ.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            mutlaqspan.setSpan(
                RelativeSizeSpan(1f),
                indexOfbihi5,
                Constant.MUTLAQ.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            mutlaqspan.setSpan(
                Typeface.DEFAULT_BOLD,
                indexOfbihi5,
                Constant.MUTLAQ.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        val charSequence = TextUtils.concat(
            mf, " ", halspan, " ", tameezspan, " ", ajlihispan, " ", badalspan, " ", mutlaqspan
        )
        if (charSequence.toString().length > 15) {
            holder.mafoolbihi!!.text = charSequence
            holder.mafoolbihi!!.gravity = Gravity.CENTER
            holder.base_cardview!!.visibility = View.VISIBLE
            holder.mafoolbihi!!.textSize = (arabicfontSize - 5).toFloat()
            holder.mafoolbihi!!.typeface = custom_font
        } else {
            holder.erabnotescardView!!.visibility = View.GONE
            holder.mafoolbihi!!.visibility = View.GONE
        }
        setChapterInfo(holder, ayahWord)
        //  setAdapterposition(position);
        wordBywordWithTranslation(
            showrootkey,
            holder,
            custom_font,
            showWordColor,
            wbw,
            ayahWord,
            showWordByword
        )
        if (showTransliteration) {
            if (entity != null) {
                holder.quran_transliteration!!.text =
                    Html.fromHtml(entity.en_transliteration, Html.FROM_HTML_MODE_LEGACY)
            }
            holder.quran_transliteration!!.visibility = View.VISIBLE
        }
        if (showJalalayn) {
            //   holder.quran_jalalaynnote.setText(enjalalayn.getAuthor_name());
            if (entity != null) {
                holder.quran_jalalayn!!.text = entity.en_jalalayn
            }
            //
            holder.quran_jalalayn!!.visibility = View.VISIBLE
            holder.quran_jalalaynnote!!.visibility = View.VISIBLE
        }
        if (showTranslation) {
            if (whichtranslation == "en_arberry") {
                if (entity != null) {
                    holder.translate_textView!!.text = entity.en_arberry
                }
                holder.translate_textViewnote!!.setText(R.string.arberry)
                holder.translate_textView!!.visibility = View.VISIBLE
                holder.translate_textViewnote!!.visibility = View.VISIBLE
            }
            if (whichtranslation == "en_sahih") {
                if (entity != null) {
                    holder.translate_textView!!.text = entity.translation
                }
                holder.translate_textViewnote!!.setText(R.string.ensahih)
                holder.translate_textView!!.visibility = View.VISIBLE
                holder.translate_textViewnote!!.visibility = View.VISIBLE
            }
            if (whichtranslation == "en_jalalayn") {
                if (entity != null) {
                    holder.translate_textView!!.text = entity.en_jalalayn
                }
                holder.translate_textViewnote!!.setText(R.string.enjalalayn)
                holder.translate_textView!!.visibility = View.VISIBLE
                holder.translate_textViewnote!!.visibility = View.VISIBLE
            }
            if (whichtranslation == "ur_jalalayn") {
                if (entity != null) {
                    holder.translate_textView!!.text = entity.ur_jalalayn
                }
                holder.translate_textViewnote!!.setText(R.string.enjalalayn)
                holder.translate_textView!!.visibility = View.VISIBLE
                holder.translate_textViewnote!!.visibility = View.VISIBLE
            }
            if (whichtranslation == "ur_junagarhi") {
                if (entity != null) {
                    holder.translate_textView!!.text = entity.ur_junagarhi
                }
                holder.translate_textViewnote!!.setText(R.string.urjunagadi)
                holder.translate_textView!!.visibility = View.VISIBLE
                holder.translate_textViewnote!!.visibility = View.VISIBLE
            }
            //
            holder.translate_textView!!.visibility = View.VISIBLE
            holder.translate_textViewnote!!.visibility = View.VISIBLE
        }
        if (showErab) {
            holder.erabexpand!!.visibility = View.VISIBLE
            if (entity != null) {
                holder.erab_textView!!.text = entity.erabspnabble
            }
            //
            holder.erab_textView!!.typeface = custom_font
            //     holder.erab_textView.setVisibility(View.VISIBLE);
            holder.erab_textViewnote!!.visibility = View.VISIBLE
        } else {
            holder.erabexpand!!.visibility = View.GONE
        }
        setTextSizes(holder)
    }

    private fun setTextSizes(holder: ItemViewAdapter) {
        if (!defaultfont) {
            holder.quran_transliteration!!.textSize = translationfontsize.toFloat()
            holder.erab_textView!!.textSize = translationfontsize.toFloat()
            holder.translate_textView!!.textSize = translationfontsize.toFloat()
            holder.translate_textViewnote!!.textSize = translationfontsize.toFloat()
            holder.quran_jalalayn!!.textSize = translationfontsize.toFloat()
        }
    }

    private fun setUpMafoolbihistring(mf: SpannableStringBuilder ) {
        val mfspan: SpannableStringBuilder
        mf.append(Constant.BIHIHEADER)
        mfspan = SpannableStringBuilder .valueOf(SpannableString.valueOf(mf.toString()))
        val indexOfbihi = mfspan.toString().indexOf(Constant.BIHI)
        mfspan.setSpan(
            ForegroundColorSpan(headercolor),
            indexOfbihi,
            Constant.BIHI.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        mfspan.setSpan(
            RelativeSizeSpan(1f),
            indexOfbihi,
            Constant.BIHI.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        mfspan.setSpan(
            Typeface.DEFAULT_BOLD,
            indexOfbihi,
            Constant.BIHI.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        mfspan.append("\n")
    }

    private fun wordBywordWithTranslation(
        showrootkey: Boolean,
        holder: ItemViewAdapter,
        custom_font: Typeface,
        showWordColor: Boolean,
        wbw: String?,
        ayahWord: CorpusAyahWord?,
        showWbwTranslation: Boolean
    ) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        holder.flow_word_by_word!!.removeAllViews()
        for (word in ayahWord!!.word!!) {
            @SuppressLint("InflateParams") val view = inflater.inflate(R.layout.word_by_word, null)
            arabicChipview = view.findViewById(R.id.word_arabic_chipview)
            arabicTv = view.findViewById(R.id.word_arabic_textView)
            rootword = view.findViewById(R.id.root_word)
            //   wordno = view.findViewById(R.id.wordno);
            val translation = view.findViewById<TextView>(R.id.word_trans_textView)
            var spannedroot: SpannableString? = null
            val sb = StringBuilder ()
            sb.append(word.wordno)
            if (showrootkey) {
                spannedroot = if (!word.rootword!!.isEmpty()) {
                    getSpannedRoots(word)
                } else {
                    SpannableString.valueOf(word.rootword)
                }
                rootword.setText(spannedroot)
                //
                rootword.setVisibility(View.VISIBLE)
            }
            if (showWordColor) {
                var spannedword: SpannableString
                //        word.getRootword();
                spannedword = getSpannedWords(word)
                //   arabic.setText(fixArabic(String.valueOf(spannedword)));
                if (showWbwTranslation) {
                    arabicTv.setText(spannedword)
                    arabicTv.setVisibility(View.VISIBLE)
                } else {
                    arabicChipview.setText(spannedword)
                    arabicChipview.setVisibility(View.VISIBLE)
                }
            } else {
                arabicChipview.setText(word.wordsAr)
            }
            rootword.setText(spannedroot)
            rootword.setTextSize(arabicfontSize.toFloat())
            arabicTv.setTypeface(colorwordfont)
            arabicChipview.setTypeface(colorwordfont)
            if (showWbwTranslation) {
                when (wbw) {
                    "en" -> {
                        translation.text = word.translateEn
                        translation.paintFlags = translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                    }

                    "bn" -> {
                        translation.text = word.translateBn
                        translation.paintFlags = translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                    }

                    "in" -> {
                        translation.text = word.translateIndo
                        translation.paintFlags = translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                    }

                    "ur" -> {
                        translation.text = word.translationUrdu
                        translation.paintFlags = translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                    }
                }
                //  translation.setTextColor(ContextCompat.getColor(context,R.color.neutral2));
            }
            if (!defaultfont) {
                rootword.setTextSize(arabicfontSize.toFloat())
                arabicChipview.setTextSize(arabicfontSize.toFloat())
                translation.textSize = arabicfontSize.toFloat()
            }
            holder.flow_word_by_word!!.addView(view)
            view.isLongClickable = true
            if (!showWbwTranslation) {
                arabicChipview.setOnLongClickListener(View.OnLongClickListener { v ->
                    val utils = Utils(QuranGrammarApplication.context!!)
                    val verbCorpusRootWords =
                        utils.getQuranRoot(word.surahId, word.verseId, word.wordno)
                    if (verbCorpusRootWords!!.size > 0 && verbCorpusRootWords[0]!!.tag == "V") {
                        //    vbdetail = ams.getVerbDetails();
                        print("check")
                    }
                    val corpusNounWord =
                        utils.getQuranNouns(word.surahId, word.verseId, word.wordno)
                    val verbCorpusRootWord =
                        utils.getQuranRoot(word.surahId, word.verseId, word.wordno)
                    val qm = WordMorphologyDetails(word, corpusNounWord!!, verbCorpusRootWord!!)
                    val workBreakDown = qm.workBreakDown
                    var color =
                        ContextCompat.getColor(context, R.color.background_color_light_brown)
                    when (isNightmode) {
                        "dark", "blue", "green" -> color = ContextCompat.getColor(
                            context, R.color.background_color
                        )

                        "brown" -> color = ContextCompat.getColor(context, R.color.neutral0)
                        "light" ->                                 //  case "white":
                            color = ContextCompat.getColor(
                                context,
                                R.color.background_color_light_brown
                            )
                    }
                    val builder: Tooltip.Builder = Tooltip.Builder(
                        v!!, R.style.ayah_translation
                    )
                        .setCancelable(true)
                        .setDismissOnClick(false)
                        .setCornerRadius(20f)
                        .setGravity(Gravity.TOP)
                        .setArrowEnabled(true)
                        .setBackgroundColor(color)
                    //    .setText(workBreakDown)
                    builder.show()
                    true
                })
                arabicChipview.setOnClickListener(View.OnClickListener {
                    val dialog = Dialog(context)
                    dialog.setTitle(word.wordsAr)
                    val dataBundle = Bundle()
                    dataBundle.putInt(Constant.SURAH_ID, word.surahId)
                    dataBundle.putInt(Constant.AYAHNUMBER, Math.toIntExact(word.verseId.toLong()))
                    dataBundle.putInt(Constant.WORDNUMBER, Math.toIntExact(word.wordno.toLong()))
                    dataBundle.putString(Constant.SURAH_ARABIC_NAME, SurahName)
                    LoadItemList(dataBundle, word)
                })
            } else {
                view.setOnLongClickListener { v: View? ->
                    val utils = Utils(QuranGrammarApplication.context!!)
                    val verbCorpusRootWords =
                        utils.getQuranRoot(word.surahId, word.verseId, word.wordno)
                    if (verbCorpusRootWords!!.size > 0 && verbCorpusRootWords[0]!!.tag == "V") {
                        //    vbdetail = ams.getVerbDetails();
                        print("check")
                    }
                    val corpusNounWord =
                        utils.getQuranNouns(word.surahId, word.verseId, word.wordno)
                    val verbCorpusRootWord =
                        utils.getQuranRoot(word.surahId, word.verseId, word.wordno)
                    val qm = WordMorphologyDetails(word, corpusNounWord!!, verbCorpusRootWord!!)
                    val workBreakDown = qm.workBreakDown
                    var color =
                        ContextCompat.getColor(context, R.color.background_color_light_brown)
                    when (isNightmode) {
                        "dark", "blue", "green" -> color =
                            ContextCompat.getColor(context, R.color.background_color)

                        "brown" -> color = ContextCompat.getColor(context, R.color.neutral0)
                        "light" ->                             //  case "white":
                            color = ContextCompat.getColor(
                                context,
                                R.color.background_color_light_brown
                            )
                    }


                    val builder: Tooltip.Builder = Tooltip.Builder(
                        v!!, R.style.ayah_translation
                    )
                        .setCancelable(true)
                        .setDismissOnClick(false)
                        .setCornerRadius(20f)
                        .setGravity(Gravity.TOP)
                        .setArrowEnabled(true)
                        .setBackgroundColor(color)

                    builder.show()


                    true
                }
                view.setOnClickListener { //pause player when word details is clicked
                    /*     if (context instanceof WordbywordMushafAct) {
                                    ((WordbywordMushafAct)context).pauseplay();
                                }*/
                    val dialog = Dialog(context)
                    dialog.setTitle(word.wordsAr)
                    val dataBundle = Bundle()
                    dataBundle.putInt(Constant.SURAH_ID, word.surahId)
                    dataBundle.putInt(Constant.AYAHNUMBER, Math.toIntExact(word.verseId.toLong()))
                    dataBundle.putInt(Constant.WORDNUMBER, Math.toIntExact(word.wordno.toLong()))
                    dataBundle.putString(Constant.SURAH_ARABIC_NAME, SurahName)
                    LoadItemList(dataBundle, word)
                }
            }
        }
        holder.flow_word_by_word!!.visibility = View.VISIBLE
    }

    private fun LoadItemList(dataBundle: Bundle, word: CorpusWbwWord) {
        if (issentence) {
            /* val item = SentenceAnalysisBottomSheet()
             item.arguments = dataBundle
             val data = arrayOf(
                 word.surahId.toString(),
                 word.verseId.toString(),
                 word.translateEn,
                 word.wordno.toString()
             )
             SentenceAnalysisBottomSheet.newInstance(data).show(
                 (context as AppCompatActivity).supportFragmentManager,
                 SentenceAnalysisBottomSheet.TAG
             )*/
        } else {

            val item = WordAnalysisBottomSheet()
            item.arguments = dataBundle
            val data = arrayOf(
                word.surahId.toString(),
                word.verseId.toString(),
                word.translateEn,
                word.wordno.toString(),
                SurahName
            )
            WordAnalysisBottomSheet.newInstance(data).show(
                (context as AppCompatActivity).supportFragmentManager,
                WordAnalysisBottomSheet.TAG
            )
        }
    }

    private fun getSpannedRoots(corpus: CorpusWbwWord): SpannableString {
        val b = corpus.verseId == 20 && (corpus.wordno == 2 || corpus.wordno == 9)
        if (b) {
            println("check")
        }
        return CorpusUtilityorig.ColorizeRootword(
            corpus.tagone!!,corpus.tagtwo!!,corpus.tagthree!!,corpus.tagfour!!,corpus.tagfive!!,
            corpus.rootword!!
        )!!
    }

    private fun getSpannedWords(corpus: CorpusWbwWord): SpannableString {
        val b = corpus.verseId == 20 && (corpus.wordno == 2 || corpus.wordno == 9)
        if (b) {
            println("check")
        }
        return CorpusUtilityorig.NewSetWordSpan(
            corpus.tagone,corpus.tagtwo,corpus.tagthree,corpus.tagfour,corpus.tagfive,
            corpus.araone,corpus.aratwo,corpus.arathree,corpus.arafour,corpus.arafive
        )
    }

    private fun storepreferences(entity: QuranEntity) {
        val pref = context.getSharedPreferences("lastread", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(Constant.CHAPTER, entity.surah)
        editor.putInt(Constant.AYAH_ID, entity.ayah)
        editor.putString(Constant.SURAH_ARABIC_NAME, SurahName)
        editor.apply()
        //  editor.commit();
    }

    private fun setChapterInfo(holder: ItemViewAdapter, verse: CorpusAyahWord?) {
        val surahInfo = java.lang.StringBuilder ()
        //        surahInfo.append(surahName+".");
        surahInfo.append(verse!!.word!![0].surahId).append(".")
        surahInfo.append(verse.word!![0].verseId).append("-")
        surahInfo.append(SurahName)
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val isNightmode = sharedPreferences.getString("theme", "dark")
        if (isMakkiMadani == 1) {
            holder.surah_info!!.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_makkah_48,
                0,
                0,
                0
            )
        } else {
            holder.surah_info!!.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_madinah_48,
                0,
                0,
                0
            )
        }
        if (isNightmode == "dark" || isNightmode == "blue" || isNightmode == "green") {
//TextViewCompat.setCompoundDrawableTintList()
            holder.surah_info!!.compoundDrawableTintList = ColorStateList.valueOf(Color.CYAN)
        } else {
            holder.surah_info!!.compoundDrawableTintList = ColorStateList.valueOf(Color.BLUE)
        }
        holder.surah_info!!.text = surahInfo
        holder.surah_info!!.textSize = 16f
        //  holder.surah_info.setTextColor(ContextCompat.getColor(context,R.color.colorOnPrimary));
    }

    inner class ItemViewAdapter @SuppressLint("CutPasteId") internal constructor(
        view: View,
        viewType: Int
    ) : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        lateinit var  quran_jalalayn: TextView
        //  lateinit var  kathir_translation: TextView
        lateinit var  quran_transliteration: TextView
        lateinit var  translate_textView: TextView

        //   public TextView erab_textView;
        lateinit var  erab_textView: TextView
        lateinit var  surah_info: TextView
        lateinit var  mafoolbihi: TextView
        lateinit var  bismilla: TextView
        lateinit var  erab_notes: TextView
        lateinit var  quran_textView: MaterialTextView
        lateinit var  quran_transliterationnote: TextView
        lateinit var  quran_jalalaynnote: TextView
        lateinit var  erab_textViewnote: TextView
        lateinit var  translate_textViewnote: TextView
        lateinit var  bookmark: ImageView
        lateinit var  jumpto: ImageView
        lateinit var  makkimadaniicon: ImageView
        lateinit var  ivSummary: ImageView
        lateinit var  expandImageButton: ImageView
        lateinit var  ivBismillah: ImageView
        lateinit var  erabexpand: ImageView
        lateinit var  erab_notes_expand: ImageView
        lateinit var  tvSura: TextView
        lateinit var  tvRukus: TextView
        lateinit var  tvVerses: TextView
        lateinit var  ivSurahIcon: ImageView
        lateinit var  ivLocationmakki: ImageView
        lateinit var  ivLocationmadani: ImageView
        lateinit var  ivhelp: ImageView
        lateinit var  ivoverflow: ImageView
        lateinit var  ivoverflow2: ImageView
        lateinit var  arrowforward: ImageView
        lateinit var  arrowback: ImageView

        //  public   com.nex3z.flowlayout.FlowLayout  flow_word_by_word;
        lateinit var  flow_word_by_word: FlowLayout

        //   RelativeLayout colllayout;
        lateinit var  erabnotescardView: CardView
        lateinit var  mafoolatarow: ImageView
        lateinit var  hiddenGroup: Group
        lateinit var  card_group: Group
        lateinit var  base_cardview: MaterialCardView
        lateinit var  tafsir: FloatingActionButton
        lateinit var  jumptofb: FloatingActionButton
        lateinit var  bookmarfb: FloatingActionButton
        lateinit var  fabmenu: FloatingActionButton
        lateinit var  helpfb: FloatingActionButton
        lateinit var  summbaryfb: FloatingActionButton
        lateinit var  sharescreenfb: FloatingActionButton
        lateinit var  collectionfb: FloatingActionButton

        init {
            view.tag = this
            itemView.setOnClickListener(this)
            if (viewType == 0) {
                ivLocationmakki = view.findViewById(R.id.ivLocationmakki)
                ivLocationmadani = view.findViewById(R.id.ivLocationmadani)
                ivSurahIcon = view.findViewById(R.id.ivSurahIcon)
                tvVerses = view.findViewById(R.id.tvVerses)
                tvRukus = view.findViewById(R.id.tvRukus)
                tvSura = view.findViewById(R.id.tvSura)
                ivBismillah = view.findViewById(R.id.ivBismillah)
            } else {
                //     kathir_note = view.findViewById(R.id.kathir_note);
                //   kathir_translation = view.findViewById(R.id.katheer_textview)
                ivSummary = view.findViewById(R.id.ivSumarry)
                ivSummary.setTag("summary")
                ivSummary.setOnClickListener(this)
                jumpto = view.findViewById(R.id.jumpto)
                ivhelp = view.findViewById(R.id.ivHelp)
                ivoverflow = view.findViewById(R.id.ivActionOverflow)
                ivhelp.setOnClickListener(this)
                ivoverflow.setOnClickListener(this)
                ivoverflow.setTag("overflow_img")
                ivoverflow2 = view.findViewById(R.id.ivActionOverflowtwo)
                ivoverflow2.setOnClickListener(this)
                ivoverflow2.setTag("overflowbottom")
                //   makkimadaniicon = view.findViewById(R.id.makkimadaniicon)
                //    jumpto = view.findViewById(R.id.jumpto);
                //   bismilla = view.findViewById(R.id.bismillah)
                quran_transliterationnote = view.findViewById(R.id.quran_transliterationnote)
                quran_jalalaynnote = view.findViewById(R.id.quran_jalalaynnote)
                translate_textViewnote = view.findViewById(R.id.translate_textViewnote)
                erab_textViewnote = view.findViewById(R.id.erab_textViewnote)
                quran_transliteration = view.findViewById(R.id.quran_transliteration)
                quran_jalalayn = view.findViewById(R.id.quran_jalalayn)
                surah_info = view.findViewById(R.id.chaptername)
                //    verse_idTextView = view.findViewById(R.id.verse_id_textView);
                flow_word_by_word = view.findViewById(R.id.flow_word_by_word)
                translate_textView = view.findViewById(R.id.translate_textView)
                erab_textView = view.findViewById(R.id.erab_textView)
                //     erab_textView.setTextIsSelectable(true);
                quran_textView = view.findViewById(R.id.quran_textView)
                erab_notes = view.findViewById(R.id.erab_notes)
                //     bookmark = view.findViewById(R.id.bookmarkView);
                erabexpand = view.findViewById(R.id.erabexpand)
                erab_notes_expand = view.findViewById(R.id.erab_img)
//                expandImageButton = view.findViewById(R.id.expandImageButton)
                quran_textView.setOnClickListener(this)
                quran_textView.setTag("qurantext")
                erab_notes_expand.setOnClickListener(this)
                erab_notes_expand.setTag("erab_notes")
                erabnotescardView = view.findViewById(R.id.base_cardview)
                mafoolatarow = view.findViewById(R.id.show)
                hiddenGroup = view.findViewById(R.id.card_group)
                mafoolatarow.setOnClickListener(this)
                mafoolbihi = view.findViewById(R.id.directobject)
                base_cardview = view.findViewById(R.id.base_cardview)
                collectionfb = view.findViewById(R.id.collectionfb)
                collectionfb.setOnClickListener(this)
                collectionfb.setTag("collection")
                fabmenu = view.findViewById(R.id.expandfabs)
                tafsir = view.findViewById(R.id.tafsirfb)
                jumptofb = view.findViewById(R.id.jumptofb)
                bookmarfb = view.findViewById(R.id.bookmarfb)
                summbaryfb = view.findViewById(R.id.summbaryfb)
                helpfb = view.findViewById(R.id.helpfb)
                sharescreenfb = view.findViewById(R.id.sharescreenfb)
                sharescreenfb.setOnClickListener(this)
                fabmenu.setOnClickListener(this)
                tafsir.setOnClickListener(this)
                jumptofb.setOnClickListener(this)
                bookmarfb.setOnClickListener(this)
                summbaryfb.setOnClickListener(this)
                helpfb.setOnClickListener(this)
                fabmenu.setTag("fabmenu")
                tafsir.setTag("tafsir")
                jumptofb.setTag("jumptofb")
                bookmarfb.setTag("bookmarfb")
                helpfb.setTag("help")
                summbaryfb.setTag("summaryfb")
                sharescreenfb.setTag("sharefb")
                view.setOnClickListener(this)
                view.setOnLongClickListener(this)
                val shared = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    QuranGrammarApplication.context!!
                )
                val colortag = shared.getBoolean("colortag", true)
                fabmenu.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        if (!isFABOpen) {
                            showFABMenu()
                        } else {
                            closeFABMenu()
                            //     HideFabMenu();
                        }
                    }

                    private fun showFABMenu() {
                        isFABOpen = true
                        fabmenu.animate().rotationBy(180f)
                        if (!isaudio) {
                            tafsir.setVisibility(View.VISIBLE)
                            tafsir.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_55)
                            )
                            tafsir.animate().rotationBy(360f)
                            tafsir.animate().duration = 1500
                            jumptofb.setVisibility(View.VISIBLE)
                            jumptofb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_105)
                            )
                            jumptofb.animate().rotationBy(360f)
                            bookmarfb.setVisibility(View.VISIBLE)
                            bookmarfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_155)
                            )
                            bookmarfb.animate().rotationBy(360f)
                            bookmarfb.animate().duration = 600
                            summbaryfb.setVisibility(View.VISIBLE)
                            summbaryfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_205)
                            )
                            summbaryfb.animate().rotationBy(360f)
                            helpfb.setVisibility(View.VISIBLE)
                            helpfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_255)
                            )
                            helpfb.animate().rotationBy(360f)
                            sharescreenfb.setVisibility(View.VISIBLE)
                            sharescreenfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_305)
                            )
                            sharescreenfb.animate().rotationBy(360f)
                            sharescreenfb.animate().duration = 500
                            collectionfb.setVisibility(View.VISIBLE)
                            collectionfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_405)
                            )
                            collectionfb.animate().rotationBy(360f)
                            collectionfb.animate().duration = 500
                        } else {
                            tafsir.setVisibility(View.VISIBLE)
                            tafsir.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_55)
                            )
                            tafsir.animate().rotationBy(360f)
                            tafsir.animate().duration = 1500
                            jumptofb.setVisibility(View.VISIBLE)
                            jumptofb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.audio_105)
                            )
                            jumptofb.animate().rotationBy(360f)
                            bookmarfb.setVisibility(View.VISIBLE)
                            bookmarfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.audio_155)
                            )
                            bookmarfb.animate().rotationBy(360f)
                            bookmarfb.animate().duration = 600
                            summbaryfb.setVisibility(View.VISIBLE)
                            summbaryfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.audio_205)
                            )
                            summbaryfb.animate().rotationBy(360f)
                            helpfb.setVisibility(View.VISIBLE)
                            helpfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.audio_255)
                            )
                            helpfb.animate().rotationBy(360f)
                            sharescreenfb.setVisibility(View.VISIBLE)
                            sharescreenfb.animate().translationX(
                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.audio_305)
                            )
                            sharescreenfb.animate().rotationBy(360f)
                            sharescreenfb.animate().duration = 500
                        }
                        tafsir.setOnClickListener(View.OnClickListener { view12: View? ->
                            /*    closeFABMenu()

                                val readingintent =  Intent(context, TafsirFullscreenActivity::class.java)

                                val chapter_no = ayahWord!!.word!![0].surahId

                                val verse = ayahWordArrayList!![position - 1].word!![0].verseId

                                readingintent.putExtra(Constant.SURAH_ID, chapter_no)
                                readingintent.putExtra(Constant.AYAH_ID, verse)
                                readingintent.putExtra(Constant.SURAH_ARABIC_NAME, SurahName)
                                context.startActivity(readingintent)*/
                        })
                        summbaryfb.setOnClickListener(View.OnClickListener { v: View? ->
                            closeFABMenu()
                            //  HideFabMenu();
                            val chapter_no = ayahWord!!.word!![0].surahId
                            //   int verse = ayahWord.getWord().get(0).getVerseId();
                            val verse = ayahWordArrayList!![position - 1].word!![0].verseId
                            val dataBundle = Bundle()
                            dataBundle.putInt(Constant.SURAH_ID, chapter_no)
                            val item = SurahSummary()
                            item.arguments = dataBundle
                            SurahSummary.newInstance(chapter_no).show(
                                (context as AppCompatActivity).supportFragmentManager,
                                NamesDetail.TAG
                            )
                        })
                        helpfb.setOnClickListener(View.OnClickListener { v: View? ->
                            closeFABMenu()
                            ParticleColorScheme.newInstance().show(
                                (context as AppCompatActivity).supportFragmentManager,
                                WordAnalysisBottomSheet.TAG
                            )
                        })
                        sharescreenfb.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View) {
                                closeFABMenu()
                                //HideFabMenu();
                                takeScreenShot((context as AppCompatActivity).window.decorView)
                            }

                            private fun takeScreenShot(view: View) {
                                val date = Date()
                                val format = DateFormat.format("MM-dd-yyyy_hh:mm:ss", date)
                                try {
                                    val mainDir = File(
                                        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                                        "FilShare"
                                    )
                                    if (!mainDir.exists()) {
                                        val mkdir = mainDir.mkdir()
                                    }
                                    val path = "$mainDir/Mushafapplication-$format.jpeg"
                                    //    File zipfile = new File(getExternalFilesDir(null).getAbsolutePath() + getString(R.string.app_folder_path) + File.separator + DATABASEZIP);
                                    view.isDrawingCacheEnabled = true
                                    val color = Color.RED
                                    val bitmap = getBitmapFromView(view, color)
                                    val imageFile = File(path)
                                    val fileOutputStream = FileOutputStream(imageFile)
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream)
                                    fileOutputStream.flush()
                                    fileOutputStream.close()
                                    shareScreenShot(imageFile)
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }

                            fun getBitmapFromView(view: View, defaultColor: Int): Bitmap {
                                val bitmap = Bitmap.createBitmap(
                                    view.width, view.height, Bitmap.Config.ARGB_8888
                                )
                                val canvas = Canvas(bitmap)
                                canvas.drawColor(defaultColor)
                                view.draw(canvas)
                                return bitmap
                            }

                            private fun shareScreenShot(imageFile: File) {
                                val uri = FileProvider.getUriForFile(
                                    context,
                                    QuranGrammarApplication.context!!.packageName + ".provider",
                                    imageFile
                                )
                                val intent = Intent()
                                intent.action = Intent.ACTION_SEND
                                intent.type = "image/*"
                                intent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Download Application from Instagram"
                                )
                                intent.putExtra(Intent.EXTRA_STREAM, uri)
                                val resInfoList = context.packageManager.queryIntentActivities(
                                    intent,
                                    PackageManager.MATCH_DEFAULT_ONLY
                                )
                                for (resolveInfo in resInfoList) {
                                    val packageName = resolveInfo.activityInfo.packageName
                                    context.grantUriPermission(
                                        packageName,
                                        uri,
                                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    )
                                }
                                //  startActivity(Intent.createChooser(intent, "Share PDF using.."));
                                try {
                                    context.startActivity(
                                        Intent.createChooser(
                                            intent,
                                            "Share With"
                                        )
                                    )
                                } catch (e: ActivityNotFoundException) {
                                    Toast.makeText(context, "No App Available", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        })
                    }

                    private fun bookMarkSelected(position: Int) {
                        val chapter_no = ayahWord!!.word!![0].surahId
                        //   int verse = ayahWord.getWord().get(0).getVerseId();
                        val verse = ayahWordArrayList!![getPosition() - 1].word!![0].verseId
                        val en = BookMarks()
                        en.chapterno = chapter_no.toString()
                        en.verseno = verse.toString()
                        en.surahname = SurahName
                        val utils = Utils(QuranGrammarApplication.context!!)
                        //     Utils utils = new Utils(ReadingSurahPartActivity.this);
                        utils.insertBookMark(en)
                        val snackbar = Snackbar
                            .make(view, "BookMark Created", Snackbar.LENGTH_LONG)
                        snackbar.setActionTextColor(Color.BLUE)
                        snackbar.setTextColor(Color.CYAN)
                        snackbar.setBackgroundTint(Color.BLACK)
                        snackbar.show()
                    }

                    private fun closeFABMenu() {
                        isFABOpen = false
                        fabmenu.animate().rotationBy(-180f)
                        tafsir.animate().translationX(0f)
                        tafsir.animate().rotationBy(0f)
                        jumptofb.animate().translationX(0f)
                        bookmarfb.animate().translationX(0f)
                        bookmarfb.animate().rotationBy(360f)
                        summbaryfb.animate().translationX(0f)
                        helpfb.animate().translationX(0f)
                        sharescreenfb.animate().translationX(0f)
                        sharescreenfb.animate().rotationBy(360f)
                        collectionfb.animate().translationX(0f)
                        collectionfb.animate().rotationBy(360f)
                    }
                })
                mafoolatarow.setOnClickListener(View.OnClickListener { view1: View? ->
                    TransitionManager.beginDelayedTransition(erabnotescardView, AutoTransition())
                    if (hiddenGroup.getVisibility() == View.VISIBLE) {
                        hiddenGroup.setVisibility(View.GONE)
                        mafoolatarow.setImageResource(android.R.drawable.arrow_down_float)
                    } else {
                        //     colllayout.setLayoutParams(params);
                        hiddenGroup.setVisibility(View.VISIBLE)
                        mafoolatarow.setImageResource(android.R.drawable.arrow_up_float)
                    }
                })
                erabexpand.setOnClickListener(View.OnClickListener { view1: View? ->
                    if (erab_textView.getVisibility() == View.GONE) {
                        erab_textView.setVisibility(View.VISIBLE)
                        //  AnimationUtility.slide_down(context, erabexpand);
                        AnimationUtility.AnimateArrow(90.0f, erabexpand)
                    } else {
                        erab_textView.setVisibility(View.GONE)
                        AnimationUtility.AnimateArrow(0.0f, erabexpand)
                        //   Fader.slide_down(context,expandImageButton);
                    }
                })
                flow_word_by_word.setOnClickListener(View.OnClickListener { view1: View? ->
                    if (translate_textView.getVisibility() == View.GONE) translate_textView.setVisibility(
                        View.VISIBLE
                    ) else translate_textView.setVisibility(View.VISIBLE)
                })
                translate_textView.setOnClickListener(View.OnClickListener { view1: View? ->
                    if (translate_textView.getVisibility() == View.VISIBLE) translate_textView.setVisibility(
                        View.GONE
                    ) else translate_textView.setVisibility(View.VISIBLE)
                })
                erab_textView.setOnClickListener(View.OnClickListener { view1: View? ->
                    if (erab_textView.getVisibility() == View.VISIBLE) erab_textView.setVisibility(
                        View.GONE
                    ) else erab_textView.setVisibility(View.VISIBLE)
                })
            }
        }

        override fun onClick(v: View) {
            mItemClickListener?.onItemClick(v, layoutPosition)
        }

        override fun onLongClick(v: View): Boolean {
            //   mItemClickListener.onItemLongClick(getAdapterPosition(), v);
            mItemClickListener!!.onItemLongClick(bindingAdapterPosition, v)
            return true
        }
    } //View.OnClickListener, View.OnLongClickListener

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}