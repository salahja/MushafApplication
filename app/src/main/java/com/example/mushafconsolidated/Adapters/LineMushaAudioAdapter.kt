package com.example.mushafconsolidated.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.AYAH_ID
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.model.CorpusWbwWord
import com.example.mushafconsolidatedimport.Config
import java.text.MessageFormat
import java.text.NumberFormat
import java.util.Locale


class LineMushaAudioAdapter(
    var context: Context,
    private val allofQuran: List<QuranEntity?>?,
    listener: OnItemClickListenerOnLong?,
    surahName: String,
    ismakki: Int,
    header: ArrayList<String>,
) :
    RecyclerView.Adapter<LineMushaAudioAdapter.ItemViewAdapter>() //implements OnItemClickListenerOnLong {
{
    private val defaultfont: Boolean
    private val header: ArrayList<String>
    private val SurahName: String
    private val isMakkiMadani: Int

    // --Commented out by Inspection (25/08/23, 7:54 pm):public TextView arabic, rootword;
    private val arabicfontSize: Int
    private val translationfontsize: Int
    val mItemClickListener: OnItemClickListenerOnLong?
    private var isNightmode: String? = null

    init {
        mItemClickListener = listener
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab)
        //   this.header = header;
        arabicfontSize = sharedPreferences.getInt("pref_font_arabic_key", 18)
        translationfontsize = sharedPreferences.getInt("pref_font_englsh_key", 18)
        defaultfont = sharedPreferences.getBoolean("default_font", true)
        SurahName = surahName
        isMakkiMadani = ismakki
        this.header = header
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
        return allofQuran!!.size
        //     return  quran.size();
    }

    override fun getItemId(position: Int): Long {
        return allofQuran!![position]!!.ayah.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        view = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.surah_header, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_row_mushaf_row_ayah_word, parent, false)
        }
        return ItemViewAdapter(view, viewType)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        isNightmode = sharedPreferences.getString("themepref", "dark")
        //  String arabic_font_selection = sharedPreferences.getString("Arabic_Font_Selection", String.valueOf(MODE_PRIVATE));
        val islamicfont = "AlQalam.ttf"
        val custom_font = Typeface.createFromAsset(
            context.assets,
            islamicfont
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
            holder.tvRukus.text = String.format("Ruku's :%s", header[0])
            holder.tvVerses.text = String.format("Aya's :%s", header[1])
            holder.tvSura.text = header[3]
            val chapterno = header[2]
            val tauba = chapterno.toInt()
            if (tauba == 9) {
                holder.ivBismillah.visibility = View.GONE
            }
            if (isMakkiMadani == 1) {
                holder.ivLocationmakki.visibility = View.VISIBLE
                holder.ivLocationmadani.visibility = View.GONE
            } else {
                holder.ivLocationmadani.visibility = View.VISIBLE
                holder.ivLocationmakki.visibility = View.GONE
            }
            val drawable = imgs.getDrawable(chapterno.toInt() - 1)
            imgs.recycle()
            holder.ivSurahIcon.setImageDrawable(drawable)
            if (isNightmode == "dark" || isNightmode == "blue" || isNightmode == "green") {
                holder.ivLocationmakki.setColorFilter(Color.CYAN)
                holder.ivSurahIcon.setColorFilter(Color.CYAN)
                holder.ivLocationmadani.setColorFilter(Color.CYAN)
            } else {
                holder.ivLocationmakki.setColorFilter(Color.BLACK)
                holder.ivSurahIcon.setColorFilter(Color.BLACK)
                holder.ivLocationmadani.setColorFilter(Color.BLACK)
            }
        } else {
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
    }

    private fun displayAyats(
        showrootkey: Boolean,
        holder: ItemViewAdapter,
        position: Int,
        sharedPreferences: SharedPreferences?,
        custom_font: Typeface?,
        showErab: Boolean,
        showWordColor: Boolean,
        showTransliteration: Boolean,
        showJalalayn: Boolean,
        showTranslation: Boolean,
        showWordByword: Boolean,
        whichtranslation: String?,
        showKathir: Boolean,
    ) {
        //   holder.flowwbw.setBackgroundColor(R.style.Theme_DarkBlue);
        var entity: QuranEntity? = null


        //   String wbw = sharedPreferences.getString("wordByWord", String.valueOf(Context.MODE_PRIVATE));
        try {
            entity = allofQuran?.get(position - 1)
        } catch (e: IndexOutOfBoundsException) {
            println("check")
        }
        val aya = ""
        entity?.let { storepreferences(it) }
        val lp = holder.quran_textView.layoutParams
        if (entity!!.passage_no != 0) {
            val builder = StringBuilder()
            builder.append(entity.qurantext)
            var s = ""
            builder.append(
                MessageFormat.format(
                    "{0} ﴿ {1} ﴾ {2}",
                    aya,
                    entity.ayah,
                    R.drawable.ruku_new
                )
            )
            if (entity.passage_no > 0) {
                val words = CorpusWbwWord()
                val nf = NumberFormat.getInstance(Locale.forLanguageTag("AR"))
                s = "\uFD3F" + "\u0639" + "\uFD3E"
            }
            //     builder.append("۩");
            holder.quran_textView.text = entity.qurantext + s
            holder.quran_textView.textSize = arabicfontSize.toFloat()
            holder.quran_textView.typeface = custom_font
            if (showTranslation) {
                if (whichtranslation == "en_arberry") {
                    if (entity != null) {
                        holder.translate_textView.text = entity.en_arberry
                    }
                    holder.translate_textViewnote.setText(R.string.arberry)
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.visibility = View.VISIBLE
                    holder.translate_textViewnote.visibility = View.VISIBLE
                }
                if (whichtranslation == "en_sahih") {
                    if (entity != null) {
                        holder.translate_textView.text = entity.translation
                    }
                    holder.translate_textViewnote.setText(R.string.ensahih)
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.visibility = View.VISIBLE
                    holder.translate_textViewnote.visibility = View.VISIBLE
                }
                if (whichtranslation == "en_jalalayn") {
                    if (entity != null) {
                        holder.translate_textView.text = entity.en_jalalayn
                    }
                    holder.translate_textViewnote.setText(R.string.enjalalayn)
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.visibility = View.VISIBLE
                    holder.translate_textViewnote.visibility = View.VISIBLE
                }
                if (whichtranslation == "ur_jalalayn") {
                    if (entity != null) {
                        holder.translate_textView.text = entity.ur_jalalayn
                    }
                    holder.translate_textViewnote.setText(R.string.enjalalayn)
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.visibility = View.VISIBLE
                    holder.translate_textViewnote.visibility = View.VISIBLE
                }
                if (whichtranslation == "ur_junagarhi") {
                    if (entity != null) {
                        holder.translate_textView.text = entity.ur_junagarhi
                    }
                    holder.translate_textViewnote.setText(R.string.urjunagadi)
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.textSize = translationfontsize.toFloat()
                    holder.translate_textView.visibility = View.VISIBLE
                    holder.translate_textViewnote.visibility = View.VISIBLE
                }
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }

            //  holder.sajdaverse.setImageResource(R.drawable.ruku_new);
        }
        if (entity.passage_no > 0) {
            holder.rukuview.visibility = View.VISIBLE
        }
        if (entity.has_prostration == 1) {
            val builder = StringBuilder()
            builder.append(entity.qurantext)
            builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ۩", aya, entity.ayah))
            //     builder.append("۩");
            holder.quran_textView.text = builder.toString()
        } else {
            val builder = StringBuilder()
            builder.append(entity.qurantext)
            builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ", aya, entity.ayah))
            holder.quran_textView.text = builder.toString()
        }
        if (!defaultfont) {
            holder.quran_textView.textSize = arabicfontSize.toFloat()
        }
        holder.quran_textView.typeface = custom_font

        /*     if(entity.getPassage_no()!=0){
           holder.sajdaverse.setVisibility(View.VISIBLE);
       }else {
            holder.sajdaverse.setVisibility(View.GONE);
        }
*/println("Position$position")
        if (showTranslation) {
            if (whichtranslation == "en_arberry") {
                if (entity != null) {
                    holder.translate_textView.text = entity.en_arberry
                }
                holder.translate_textViewnote.setText(R.string.arberry)
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "en_sahih") {
                if (entity != null) {
                    holder.translate_textView.text = entity.translation
                }
                holder.translate_textViewnote.setText(R.string.ensahih)
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "en_jalalayn") {
                if (entity != null) {
                    holder.translate_textView.text = entity.en_jalalayn
                }
                holder.translate_textViewnote.setText(R.string.enjalalayn)
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "ur_jalalayn") {
                if (entity != null) {
                    holder.translate_textView.text = entity.ur_jalalayn
                }
                holder.translate_textViewnote.setText(R.string.enjalalayn)
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            if (whichtranslation == "ur_junagarhi") {
                if (entity != null) {
                    holder.translate_textView.text = entity.ur_junagarhi
                }
                holder.translate_textViewnote.setText(R.string.urjunagadi)
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.textSize = translationfontsize.toFloat()
                holder.translate_textView.visibility = View.VISIBLE
                holder.translate_textViewnote.visibility = View.VISIBLE
            }
            holder.translate_textView.textSize = translationfontsize.toFloat()
            holder.translate_textView.textSize = translationfontsize.toFloat()
            holder.translate_textView.visibility = View.VISIBLE
            holder.translate_textViewnote.visibility = View.VISIBLE
        }
        //  setTextSizes(holder);
    }

    private fun storepreferences(entity: QuranEntity) {
        val pref = context.getSharedPreferences("lastread", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(SURAH_ID, entity.surah)
        editor.putInt(AYAH_ID, entity.ayah)
        editor.putString(SURAH_ARABIC_NAME, SurahName)
        editor.apply()
        editor.commit();
    }

    inner class ItemViewAdapter internal constructor(view: View, viewType: Int) :
        RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        lateinit var quran_jalalayn: TextView
        lateinit var kathir_translation: TextView
        lateinit var quran_transliteration: TextView
        lateinit var translate_textView: TextView


        lateinit var surah_info: TextView
        lateinit var mafoolbihi: TextView
        lateinit var translate_textViewnote: TextView
        lateinit var bookmark: ImageView
        lateinit var jumpto: ImageView
        lateinit var makkimadaniicon: ImageView

        lateinit var ivBismillah: ImageView
        lateinit var tvSura: TextView
        lateinit var tvRukus: TextView
        lateinit var tvVerses: TextView
        lateinit var quran_textView: AppCompatTextView
        lateinit var rukuview: View
        private lateinit var sajdaverse: ImageView
        lateinit var ivSurahIcon: ImageView
        lateinit var ivLocationmakki: ImageView
        lateinit var ivLocationmadani: ImageView

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
                rukuview = view.findViewById(R.id.rukuview)
                sajdaverse = view.findViewById(R.id.sajda)
                // makkimadaniicon = view.findViewById(R.id.makkimadaniicon)
                translate_textViewnote = view.findViewById(R.id.translate_textViewnote)
                //    surah_info = view.findViewById(R.id.chaptername)
                //    verse_idTextView = view.findViewById(R.id.verse_id_textView);
                translate_textView = view.findViewById(R.id.translate_textView)


                quran_textView = view.findViewById(R.id.quran_textView)
                view.setOnClickListener(this)
                view.setOnLongClickListener(this)
                quran_textView.setOnClickListener(this)
                quran_textView.setOnLongClickListener(this)
                quran_textView.tag = "verse"
                val shared =
                    androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
                val colortag = shared.getBoolean("colortag", true)
            }
        }

        override fun onClick(v: View) {
            mItemClickListener?.onItemClick(v, layoutPosition)
        }

        override fun onLongClick(v: View): Boolean {
            //   mItemClickListener.onItemLongClick(getAdapterPosition(), v);
            mItemClickListener?.onItemLongClick(bindingAdapterPosition, v)
            return true
        }
    } //View.OnClickListener, View.OnLongClickListener

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}