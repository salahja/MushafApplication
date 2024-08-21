package com.example.mushafconsolidated.Adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Environment
import android.text.Html
import android.text.SpannableString
import android.text.format.DateFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.Constant.AYAHNUMBER
import com.example.Constant.AYAH_ID
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.Constant.WORDNUMBER
import com.example.mushafconsolidated.Activity.TafsirFullscreenActivity
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.SurahSummary
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.mushafconsolidated.fragments.refWordMorphologyDetails
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.model.NewQuranCorpusWbw
import com.example.mushafconsolidatedimport.Config
import com.example.mushafconsolidatedimport.ParticleColorScheme
import com.example.utility.AnimationUtility
import com.example.utility.CorpusUtilityorig
import com.example.utility.FlowLayout
import com.example.utility.QuranGrammarApplication
import com.google.android.material.card.MaterialCardView
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
class NewTopicFlowAyahWordAdapter(
    private val ayahWordArrayList: ArrayList<LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>>,
    listener: OnItemClickListenerOnLong?,

    arrayofquran: ArrayList<ArrayList<QuranEntity>>,
    surahArrays: Array<String>
) :
    RecyclerView.Adapter<NewTopicFlowAyahWordAdapter.ItemViewAdapter>() {
    private val arabicfontSize: Int
    private val translationfontsize: Int
    val mItemClickListener: OnItemClickListenerOnLong?
    private val issentence: Boolean
    var context: Context? = null
    lateinit var arabic: TextView
    lateinit var rootword: TextView
    private lateinit var isNightmode: String
    private lateinit var SurahName: String
    private lateinit var arrayofquran: ArrayList<ArrayList<QuranEntity>>
    private val isMakkiMadani = 0
    lateinit var ayahWord: NewQuranCorpusWbw
    private lateinit var quranverses: SpannableString
    private var defaultfont: Boolean = false
    private lateinit var surahArrays: Array<String>

    init {
        mItemClickListener = listener
        //  mItemClickListener = listener;
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab)
        issentence = sharedPreferences.getBoolean("grammarsentence", false)
        arabicfontSize = sharedPreferences.getInt("pref_font_arabic_key", 18)
        translationfontsize = sharedPreferences.getInt("pref_font_englsh_key", 18)
        defaultfont = sharedPreferences.getBoolean("default_font", true)

        this.arrayofquran = arrayofquran
        this.surahArrays = surahArrays
    }

    fun addContext(context: Context?) {
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
        return ayahWordArrayList.size
        //     return  quran.size();
    }

    override fun getItemId(position: Int): Long {
        val ayahWord = ayahWordArrayList[position]
        var itemId: Long = 0
        return ayahWord[0]!![0].corpus!!.ayah.toLong()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.topic_row_ayah_word, parent, false)
        //   view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewer_aya_cardview, parent, false);
        return ItemViewAdapter(view, viewType)
    }

    // --Commented out by Inspection START (23/08/23, 6:07 am):
    //    public Object getItem(int position) {
    //        return ayahWordArrayList.get(0).getWord().get(position);
    //        //    return allofQuran.get(position);
    //    }
    // --Commented out by Inspection STOP (23/08/23, 6:07 am)
    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context!!
        )
        isNightmode = sharedPreferences.getString("themepref", "dark")!!
        //grammatically colred word default font
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "quranicfontregular.ttf")
        val custom_font = Typeface.createFromAsset(
            context!!.assets,
            arabic_font_selection
        )
        val showrootkey = sharedPreferences.getBoolean("showrootkey", true)
        val showErab = sharedPreferences.getBoolean("showErabKey", true)
        val showWordColor = sharedPreferences.getBoolean("colortag", true)
        val showTransliteration = sharedPreferences.getBoolean("showtransliterationKey", true)
        val showJalalayn = sharedPreferences.getBoolean("showEnglishJalalayn", true)
        val showTranslation = sharedPreferences.getBoolean("showTranslationKey", true)
        val showWordByword = sharedPreferences.getBoolean("wordByWord", false)
        val showKathir = sharedPreferences.getBoolean("showKathir", false)
        val whichtranslation = sharedPreferences.getString("selecttranslation", "en_sahih")
        displayAyats(
            showrootkey,
            holder,
            position,
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
        val wbw = sharedPreferences.getString("wbw", "en")
        var entity: QuranEntity? = null

        //   String wbw = sharedPreferences.getString("wordByWord", String.valueOf(Context.MODE_PRIVATE));
        try {
            entity = arrayofquran[position][0]
        } catch (e: IndexOutOfBoundsException) {
            println("check")
        }




        ayahWord = ayahWordArrayList[position][0]!![0]

        try {
            quranverses = ayahWordArrayList[position][0]!![0].spannableverse!!
        } catch (e: IndexOutOfBoundsException) {
        }
        assert(ayahWord != null)
        //  holder.header.text = ayahWord.topictitle
        holder.quran_textView.text = quranverses
        holder.quran_textView.textSize = 16f
        holder.quran_textView.typeface = custom_font
        holder.base_cardview.visibility = View.GONE
        holder.mafoolbihi.visibility = View.GONE
        holder.base_cardview.visibility = View.GONE
        setChapterInfo(holder, ayahWord)
        //  setAdapterposition(position);
        wordBywordWithTranslation(
            showrootkey,
            holder,
            showWordColor,
            wbw,
            ayahWord,
            showWordByword,
            position
        )
        if (showTransliteration) {
            if (entity != null) {
                holder.quran_transliteration.text =
                    Html.fromHtml(entity.en_transliteration, Html.FROM_HTML_MODE_LEGACY)
            }
            holder.quran_transliteration.visibility = View.VISIBLE
        }
        if (showJalalayn) {
            holder.quran_jalalaynnote.text = "Jalalayn";
            if (entity != null) {
                holder.quran_jalalayn.text = entity.en_jalalayn
            }
            //
            holder.quran_jalalayn.visibility = View.VISIBLE
            holder.quran_jalalaynnote.visibility = View.VISIBLE
        }
        if (showTranslation) {
            if (whichtranslation == "en_arberry") {
                if (entity != null) {
                    holder.translate_textView.text = entity.en_arberry
                }
                holder.translate_textViewnote.setText(R.string.arberry)
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "en_sahih") {
                if (entity != null) {
                    holder.translate_textView.text = entity.translation
                }
                holder.translate_textViewnote.setText(R.string.ensahih)
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "en_jalalayn") {
                if (entity != null) {
                    holder.translate_textView.text = entity.en_jalalayn
                }
                holder.translate_textViewnote.setText(R.string.enjalalayn)
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "ur_jalalayn") {
                if (entity != null) {
                    holder.translate_textView.text = entity.ur_jalalayn
                }
                holder.translate_textViewnote.setText(R.string.enjalalayn)
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "ur_junagarhi") {
                if (entity != null) {
                    holder.translate_textView.text = entity.ur_junagarhi
                }
                holder.translate_textViewnote.setText(R.string.urjunagadi)
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            //
            holder.translate_textView.visibility = View.VISIBLE
            holder.translate_textViewnote.visibility = View.VISIBLE
        }
        if (showErab) {
            holder.erabexpand.visibility = View.VISIBLE
            if (entity != null) {
                if (entity.erabspnabble.isNullOrBlank()) {
                    holder.erab_textView.text = entity.ar_irab_two


                } else {
                    holder.erab_textView.text = entity.erabspnabble
                }
            }
            //
            holder.erab_textView.typeface = custom_font
            //     holder.erab_textView.setVisibility(View.VISIBLE);
            holder.erab_textViewnote.visibility = View.VISIBLE
        } else {
            holder.erabexpand.visibility = View.GONE
        }
        setTextSizes(holder)
    }

    private fun setTextSizes(holder: ItemViewAdapter) {
        if (!defaultfont) {
            holder.quran_transliteration.textSize = translationfontsize.toFloat()
            holder.erab_textView.textSize = translationfontsize.toFloat()
            holder.translate_textView.textSize = translationfontsize.toFloat()
            holder.translate_textViewnote.textSize = translationfontsize.toFloat()
            holder.quran_jalalayn.textSize = translationfontsize.toFloat()
        }
    }

    private fun wordBywordWithTranslation(
        showrootkey: Boolean,
        holder: ItemViewAdapter,
        showWordColor: Boolean,
        wbw: String?,
        ayahWord: NewQuranCorpusWbw?,
        showWbwTranslation: Boolean,
        position: Int
    ) {
        val FONTS_LOCATION_PATH = "fonts/DejaVuSans.ttf"
        val colorwordfont = Typeface.createFromAsset(
            (QuranGrammarApplication.instance)?.assets,
            FONTS_LOCATION_PATH
        )
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        holder.flow_word_by_word.removeAllViews()
        val wordarray = ayahWordArrayList[position][0]
        if (wordarray != null) {
            for (word in wordarray) {
                @SuppressLint("InflateParams") val view =
                    inflater.inflate(R.layout.word_by_word, null)
                arabic = view.findViewById(R.id.word_arabic_textView)
                rootword = view.findViewById(R.id.root_word)
                //   wordno = view.findViewById(R.id.wordno);
                val translation = view.findViewById<TextView>(R.id.word_trans_textView)
                var spannedroot: SpannableString? = null
                val sb = StringBuilder()

                val rootwords =
                    word.corpus!!.rootaraone + word.corpus!!.rootaratwo + word.corpus!!.rootarathree + word.corpus!!.rootarafour + word.corpus!!.rootarafive
                val araword =
                    word.corpus!!.araone + word.corpus!!.aratwo + word.corpus!!.arathree + word.corpus!!.arafour + word.corpus!!.arafive

                sb.append(word.corpus!!.wordno)
                if (showrootkey) {
                    spannedroot = if (rootwords.isNotEmpty()) {
                        getSpannedRoots(word, rootwords)
                    } else {
                        SpannableString.valueOf(rootwords)
                    }
                    rootword.text = spannedroot
                    //
                    rootword.visibility = View.VISIBLE
                }



                if (showWordColor) {
                    var spannedword: SpannableString
                    // word.getRootword();
                    spannedword = getSpannedWords(word)
                    arabic.text = spannedword
                } else {
                    arabic.text = araword
                }
                rootword.text = spannedroot
                rootword.textSize = arabicfontSize.toFloat()
                //  arabic.setTextSize(18);
                arabic.textSize = arabicfontSize.toFloat()
                arabic.typeface = colorwordfont
                if (showWbwTranslation) {
                    when (wbw) {
                        "en" -> {
                            translation.text = word.wbw!!.en
                            translation.paintFlags =
                                translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                        }

                        "bn" -> {
                            translation.text = word.wbw!!.bn
                            translation.paintFlags =
                                translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                        }

                        "in" -> {
                            translation.text = word.wbw!!.`in`
                            translation.paintFlags =
                                translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                        }

                        "ur" -> {
                            translation.text = word.wbw!!.ur
                            translation.paintFlags =
                                translation.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                        }
                    }
                    //  translation.setTextColor(context.getResources().getColor(R.color.neutral2));
                }
                //    translation.setTextSize(forntSize + 4);
                translation.textSize = translationfontsize.toFloat()
                holder.flow_word_by_word.addView(view)
                view.isLongClickable = true
                view.setOnLongClickListener { v: View? ->
                    val utils = Utils(context)
                    val verbCorpusRootWords: List<VerbCorpus> =
                        utils.getQuranRoot(
                            word.corpus!!.surah,
                            word.corpus!!.ayah,
                            word.corpus!!.wordno
                        ) as List<VerbCorpus>
                    if (verbCorpusRootWords.isNotEmpty() && verbCorpusRootWords[0].tag == "V") {
                        //    vbdetail = ams.getVerbDetails();
                        print("check")
                    }
                    val corpusNounWord: ArrayList<NounCorpus?> =
                        utils.getQuranNouns(
                            word.corpus!!.surah,
                            word.corpus!!.ayah,
                            word.corpus!!.wordno
                        ) as ArrayList<NounCorpus?>
                    val verbCorpusRootWord: List<VerbCorpus?> =
                        utils.getQuranRoot(
                            word.corpus!!.surah,
                            word.corpus!!.ayah,
                            word.corpus!!.wordno
                        ) as List<VerbCorpus>


                    val qm =
                        refWordMorphologyDetails(word.corpus!!, corpusNounWord, verbCorpusRootWord)
                    val workBreakDown = qm.workBreakDown
                    var color =
                        context!!.resources.getColor(R.color.background_color_light_brown)
                    when (isNightmode) {
                        "dark", "blue", "green" -> color =
                            ContextCompat.getColor(context!!, R.color.background_color)

                        "brown", "light" -> color =
                            ContextCompat.getColor(context!!, R.color.neutral0)

                        "white" -> color =
                            ContextCompat.getColor(context!!, R.color.background_color_light_brown)
                    }
                    val builder =
                        Tooltip.Builder(v!!, R.style.ayah_translation)
                            .setCancelable(true)
                            .setDismissOnClick(false)
                            .setCornerRadius(20f)
                            .setGravity(Gravity.TOP)
                            .setArrowEnabled(true)
                            .setBackgroundColor(color)
                            .setText(workBreakDown)
                    builder.show()
                    true
                }
                view.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val dialog = Dialog(context!!)
                        dialog.setTitle(word.corpus!!.araone + word.corpus!!.aratwo + word.corpus!!.arathree + word.corpus!!.arafour + word.corpus!!.arafive)

                        val dataBundle = Bundle()
                        dataBundle.putInt(SURAH_ID, word.corpus!!.surah)
                        dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.corpus!!.ayah.toLong()))
                        dataBundle.putInt(
                            WORDNUMBER,
                            Math.toIntExact(word.corpus!!.wordno.toLong())
                        )
                        dataBundle.putString(SURAH_ARABIC_NAME, SurahName)//TODO
                        LoadItemList(dataBundle)
                    }

                    private fun LoadItemList(dataBundle: Bundle) {
                        if (issentence) {
                            /*       val item = SentenceAnalysisBottomSheet()
                            item.setArguments(dataBundle)
                            val data = arrayOf(
                                word.surahId.toString(),
                                word.corpus!!.ayah.toString(),
                                word.translateEn,
                                word.corpus!!.wordno.toString()
                            )
                            SentenceAnalysisBottomSheet.newInstance(data).show(
                                (context as AppCompatActivity?)!!.supportFragmentManager,
                                SentenceAnalysisBottomSheet.TAG
                            )*/
                        } else {
                            val item = WordAnalysisBottomSheet()
                            item.arguments = dataBundle
                            val data = arrayOf<String?>(
                                word.corpus!!.surah.toString(),
                                word.corpus!!.ayah.toString(),
                                word.wbw!!.en,
                                word.corpus!!.wordno.toString(),
                                "SurahName" //TODO
                            )
                            WordAnalysisBottomSheet.newInstance(data).show(
                                (context as AppCompatActivity?)!!.supportFragmentManager,
                                WordAnalysisBottomSheet.TAG
                            )
                        }
                    }
                })
            }
        }
        holder.flow_word_by_word.visibility = View.VISIBLE
    }

    private fun getSpannedRoots(tag: NewQuranCorpusWbw, rootwords: String): SpannableString? {

        return CorpusUtilityorig.ColorizeRootword(
            tag.corpus!!.tagone!!,
            tag.corpus!!.tagtwo!!,
            tag.corpus!!.tagthree!!,
            tag.corpus!!.tagfour!!,
            tag.corpus!!.tagfive!!,
            rootwords
        )
    }

    private fun getSpannedWords(tag: NewQuranCorpusWbw): SpannableString {


        return CorpusUtilityorig.NewSetWordSpan(
            tag.corpus!!.tagone,
            tag.corpus!!.tagtwo,
            tag.corpus!!.tagthree,
            tag.corpus!!.tagfour,
            tag.corpus!!.tagfive,
            tag.corpus!!.araone!!,
            tag.corpus!!.aratwo!!,
            tag.corpus!!.arathree!!,
            tag.corpus!!.arafour!!,
            tag.corpus!!.arafive!!
        )
    }

    private fun setChapterInfo(holder: ItemViewAdapter, verse: NewQuranCorpusWbw?) {
        val surahInfo = StringBuilder()
        //        surahInfo.append(surahName+".");
        val surahname = surahArrays[verse!!.corpus!!.surah - 1]
        SurahName = surahname
        surahInfo.append(surahname).append(" ").append("Ayah").append(" ")
            .append(verse.corpus!!.ayah)
        //  surahInfo.append(verse!!.corpus!!.surah).append(".")
        //  surahInfo.append(verse.corpus!!.ayah).append("-").append(surahname)

        //  surahInfo.append(SurahName);
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context!!
        )
        val isNightmode = sharedPreferences.getString("themepref", "dark")
        val imgs: TypedArray =
            QuranGrammarApplication.context!!.resources.obtainTypedArray(R.array.sura_imgs)
        val drawable = imgs.getDrawable(verse.corpus!!.surah.toString().toInt() - 1)
        imgs.recycle()
        holder.surahicon.setImageDrawable(drawable)
        if (isNightmode == "dark" || isNightmode == "blue") {
            holder.surahicon.setColorFilter(Color.CYAN)
        }
        if (isMakkiMadani == 1) {
            holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_makkah_48,
                0,
                0,
                0
            )
        } else {
            holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_madinah_48,
                0,
                0,
                0
            )
        }
        if (isNightmode == "dark" || isNightmode == "blue" || isNightmode == "green") {
//TextViewCompat.setCompoundDrawableTintList()
            holder.surah_info.compoundDrawableTintList = ColorStateList.valueOf(Color.CYAN)
        } else {
            holder.surah_info.compoundDrawableTintList = ColorStateList.valueOf(Color.BLUE)
            //   holder.surah_info.setBackgroundColor(getContext().getResources().getColor(R.color.burntamber));
            //   imageViewIcon.setColorFilter(getContext().getResources().getColor(R.color.blue));
        }
        holder.surah_info.text = surahInfo
        holder.surah_info.textSize = arabicfontSize.toFloat()
        //  holder.surah_info.setTextColor(context.getResources().getColor(R.color.colorOnPrimary));
    }

    inner class ItemViewAdapter @SuppressLint("CutPasteId") internal constructor(
        view: View,
        viewType: Int
    ) :
        RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        // --Commented out by Inspection (23/08/23, 6:10 am):public final TextView quran_jalalayn;
        // --Commented out by Inspection (23/08/23, 6:10 am):public final TextView kathir_translation;
        val quran_transliteration: TextView
        val translate_textView: TextView
        val erab_textView: TextView
        val surah_info: TextView
        val mafoolbihi: TextView
        val quran_textView: MaterialTextView

        // --Commented out by Inspection (23/08/23, 6:09 am):public final TextView erab_notes;
        //      public final TextView quran_transliterationnote;
        // public final TextView quran_jalalaynnote;
        val erab_textViewnote: TextView
        val quran_jalalayn: TextView
        val quran_jalalaynnote: TextView
        val header: TextView
        val translate_textViewnote: TextView

        //val makkimadaniicon: ImageView
        //val expandImageButton: ImageView
        val erabexpand: ImageView
        private val erab_notes_expand: ImageView
        private val colorize: SwitchCompat
        val flow_word_by_word: FlowLayout

        //   RelativeLayout colllayout;
        private val erabnotescardView: CardView
        private val mafoolatarow: ImageView
        val surahicon: ImageView
        private val ivoverflow: ImageView
        private val hiddenGroup: Group
        val base_cardview: MaterialCardView
        val tafsir: FloatingActionButton
        private val jumptofb: FloatingActionButton
        val bookmarfb: FloatingActionButton
        val fabmenu: FloatingActionButton
        val helpfb: FloatingActionButton
        val summbaryfb: FloatingActionButton
        val sharescreenfb: FloatingActionButton

        init {
            view.tag = this
            header = view.findViewById(R.id.headers)
            itemView.setOnClickListener(this)
            surahicon = view.findViewById(R.id.surahicon)
            //   kathir_translation = view.findViewById(R.id.katheer_textview);
            colorize = view.findViewById(R.id.colorized)
            ivoverflow = view.findViewById(R.id.ivActionOverflow)
            ivoverflow.setOnClickListener(this)
            ivoverflow.tag = "overflow_img"
            //  ivhelp.setOnClickListener(this);
            //    makkimadaniicon = view.findViewById(R.id.makkimadaniicon)

            // quran_transliterationnote = view.findViewById(R.id.quran_transliterationnote);
            //    quran_jalalaynnote = view.findViewById(R.id.quran_jalalaynnote);
            translate_textViewnote = view.findViewById(R.id.translate_textViewnote)
            erab_textViewnote = view.findViewById(R.id.erab_textViewnote)
            quran_transliteration = view.findViewById(R.id.quran_transliteration)
            //  quran_jalalayn = view.findViewById(R.id.quran_jalalayn);
            surah_info = view.findViewById(R.id.chaptername)
            //    verse_idTextView = view.findViewById(R.id.verse_id_textView);
            flow_word_by_word = view.findViewById(R.id.flow_word_by_word)
            translate_textView = view.findViewById(R.id.translate_textView)
            erab_textView = view.findViewById(R.id.erab_textView)
            //     erab_textView.setTextIsSelectable(true);
            quran_textView = view.findViewById(R.id.quran_textView)
            //     erab_notes = view.findViewById(R.id.erab_notes);
            erabexpand = view.findViewById(R.id.erabexpand)
            quran_jalalayn = view.findViewById(R.id.quran_jalalayn)
            erab_notes_expand = view.findViewById(R.id.erab_img)
            quran_jalalaynnote = view.findViewById(R.id.quran_jalalaynnote)
            //   expandImageButton = view.findViewById(R.id.expandImageButton)
            quran_textView.setOnClickListener(this)
            quran_textView.tag = "qurantext"
            erab_notes_expand.setOnClickListener(this)
            erab_notes_expand.tag = "erab_notes"
            erabnotescardView = view.findViewById(R.id.base_cardview)
            mafoolatarow = view.findViewById(R.id.show)
            hiddenGroup = view.findViewById(R.id.card_group)
            mafoolatarow.setOnClickListener(this)
            mafoolbihi = view.findViewById(R.id.directobject)
            base_cardview = view.findViewById(R.id.base_cardview)
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
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
            val shared =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
            val colortag = shared.getBoolean("colortag", true)
            fabmenu.setOnClickListener(object : View.OnClickListener {
                private var isFABOpen = false
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

                    tafsir.visibility = View.VISIBLE
                    tafsir.animate().translationX(
                        -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_55)
                    )
                    tafsir.animate().rotationBy(360f)
                    tafsir.animate().duration = 1500


                    /*
                                            jumptofb.visibility = View.VISIBLE
                                            jumptofb.animate().translationX(
                                                -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_105)
                                                                           )
                                            jumptofb.animate().rotationBy(360f)*/
                    bookmarfb.visibility = View.VISIBLE


                    bookmarfb.animate().translationX(
                        -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_105)
                    )




                    bookmarfb.animate().rotationBy(360f)
                    bookmarfb.animate().duration = 600
                    summbaryfb.visibility = View.VISIBLE
                    summbaryfb.animate().translationX(
                        -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_155)
                    )
                    summbaryfb.animate().rotationBy(360f)
                    helpfb.visibility = View.VISIBLE
                    helpfb.animate().translationX(
                        -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_205)
                    )
                    helpfb.animate().rotationBy(360f)
                    sharescreenfb.visibility = View.VISIBLE
                    sharescreenfb.animate().translationX(
                        -QuranGrammarApplication.instance!!.resources.getDimension(R.dimen.standard_255)
                    )
                    sharescreenfb.animate().rotationBy(360f)
                    sharescreenfb.animate().duration = 500


                    tafsir.setOnClickListener {
                        closeFABMenu()
                        //HideFabMenu();
                        val readingintent = Intent(
                            context as AppCompatActivity?,
                            TafsirFullscreenActivity::class.java
                        )
                        //  flowAyahWordAdapter.getItem(position);
                        val chapter_no = ayahWord.corpus!!.surah
                        //   int verse = ayahWord.getWord().get(0).getcorpus!!.ayah();
                        val verse = ayahWord.corpus!!.ayah
                        //   String name = getSurahArabicName();
                        readingintent.putExtra(SURAH_ID, chapter_no)
                        readingintent.putExtra(AYAH_ID, verse)
                        readingintent.putExtra(SURAH_ARABIC_NAME, SurahName)
                        (context as AppCompatActivity?)!!.startActivity(readingintent)
                    }
                    summbaryfb.setOnClickListener {
                        closeFABMenu()
                        //  HideFabMenu();
                        val chapter_no = ayahWord.corpus!!.surah
                        //   int verse = ayahWord.getWord().get(0).getVerseId();
                        val verse = ayahWord.corpus!!.ayah
                        val dataBundle = Bundle()
                        dataBundle.putInt(SURAH_ID, chapter_no)
                        val item = SurahSummary()
                        item.arguments = dataBundle
                        //  FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                        //   transactions.show(item);
                        SurahSummary.newInstance(chapter_no).show(
                            (context as AppCompatActivity?)!!.supportFragmentManager,
                            NamesDetail.TAG
                        )
                    }
                    helpfb.setOnClickListener { v: View? ->
                        closeFABMenu()
                        ParticleColorScheme.newInstance().show(
                            (context as AppCompatActivity).supportFragmentManager,
                            WordAnalysisBottomSheet.TAG
                        )
                    }
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
                                    context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
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
                                context!!,
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
                            val resInfoList = context!!.packageManager.queryIntentActivities(
                                intent,
                                PackageManager.MATCH_DEFAULT_ONLY
                            )
                            for (resolveInfo in resInfoList) {
                                val packageName = resolveInfo.activityInfo.packageName
                                context!!.grantUriPermission(
                                    packageName,
                                    uri,
                                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                                )
                            }
                            //  startActivity(Intent.createChooser(intent, "Share PDF using.."));
                            try {
                                context!!.startActivity(
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

                private fun closeFABMenu() {
                    isFABOpen = false
                    fabmenu.animate().rotationBy(-180f)
                    tafsir.animate().translationX(0f)
                    tafsir.animate().rotationBy(0f)

                    //   jumptofb.animate().translationX(0);
                    bookmarfb.animate().translationX(0f)
                    bookmarfb.animate().rotationBy(360f)
                    summbaryfb.animate().translationX(0f)
                    helpfb.animate().translationX(0f)
                    sharescreenfb.animate().translationX(0f)
                    sharescreenfb.animate().rotationBy(360f)
                }

                private fun bookMarkSelected(position: Int) {
                    //  position = flowAyahWordAdapter.getAdapterposition();

                    //  flowAyahWordAdapter.getItem(position);
                    val chapter_no = ayahWord.corpus!!.surah
                    //   int verse = ayahWord.getWord().get(0).getVerseId();
                    val verse = ayahWord.corpus!!.ayah
                    val en = BookMarks()
                    en.chapterno = chapter_no.toString()
                    en.verseno = verse.toString()
                    en.surahname = "SurahName"
                    val utils = Utils(context)
                    //     Utils utils = new Utils(ReadingSurahPartActivity.this);
                    utils.insertBookMark(en)
                    val snackbar = Snackbar
                        .make(view, "BookMark Created", Snackbar.LENGTH_LONG)
                    snackbar.setActionTextColor(Color.BLUE)
                    snackbar.setTextColor(Color.CYAN)
                    snackbar.setBackgroundTint(Color.BLACK)
                    snackbar.show()
                }

            })
            mafoolatarow.setOnClickListener { view1: View? ->
                TransitionManager.beginDelayedTransition(
                    erabnotescardView,
                    AutoTransition()
                )
                if (hiddenGroup.visibility == View.VISIBLE) {
                    hiddenGroup.visibility = View.GONE
                    mafoolatarow.setImageResource(android.R.drawable.arrow_down_float)
                } else {
                    //     colllayout.setLayoutParams(params);
                    hiddenGroup.visibility = View.VISIBLE
                    mafoolatarow.setImageResource(android.R.drawable.arrow_up_float)
                }
            }
            erabexpand.setOnClickListener { view1: View? ->
                if (erab_textView.visibility == View.GONE) {
                    erab_textView.visibility = View.VISIBLE
                    //  AnimationUtility.slide_down(context, erabexpand);
                    AnimationUtility.AnimateArrow(90.0f, erabexpand)
                } else {
                    erab_textView.visibility = View.GONE
                    AnimationUtility.AnimateArrow(0.0f, erabexpand)
                    //   Fader.slide_down(context,expandImageButton);
                }
            }
            flow_word_by_word.setOnClickListener { view1: View? ->
                if (translate_textView.visibility == View.GONE) translate_textView.visibility =
                    View.VISIBLE else translate_textView.visibility = View.VISIBLE
            }
            translate_textView.setOnClickListener { view1: View? ->
                if (translate_textView.visibility == View.VISIBLE) translate_textView.visibility =
                    View.GONE else translate_textView.visibility = View.VISIBLE
            }
            erab_textView.setOnClickListener { view1: View? ->
                if (erab_textView.visibility == View.VISIBLE) erab_textView.visibility =
                    View.GONE else erab_textView.visibility = View.VISIBLE
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
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}