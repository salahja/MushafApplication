package com.example.mushafconsolidated.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.Page
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidatedimport.Config
import com.example.utility.CorpusUtilityorig.Companion.getSpannable
import java.text.MessageFormat


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
//public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListenerOnLong {
class PageMushaAudioAdapter(
    private val fullQuranPages: ArrayList<Page>,
    val context: Context,
    listener: OnItemClickListenerOnLong?,
    surah_id: Long,
    ismakki: Int,
    header: ArrayList<String>,
) :
    RecyclerView.Adapter<PageMushaAudioAdapter.ItemViewAdapter>() //implements OnItemClickListenerOnLong {
{
    val surah_id: Long
    private val header: ArrayList<String>
    private val isMakkiMadani: Int

    // --Commented out by Inspection (25/08/23, 7:28 pm):public TextView arabic, rootword;
    private val arabicfontSize: Int
    private val translationfontsize: Int
    val mItemClickListener: OnItemClickListenerOnLong?

    // --Commented out by Inspection (25/08/23, 7:28 pm):private CorpusAyahWord ayahWord;
    init {
        mItemClickListener = listener
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab)
        //   this.header = header;
        arabicfontSize = sharedPreferences.getInt("pref_font_arabic_key", 18)
        translationfontsize = sharedPreferences.getInt("pref_font_englsh_key", 18)
        this.surah_id = surah_id
        isMakkiMadani = ismakki
        this.header = header
    }

    // --Commented out by Inspection START (25/08/23, 7:28 pm):
    //    public void addContext(Context context) {
    //        this.context = context;
    //
    //    }
    // --Commented out by Inspection STOP (25/08/23, 7:28 pm)
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return fullQuranPages.size + 1

        //     return  quran.size();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        view = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.surah_header, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mushaf_row_ayah_word, parent, false)
        }
        return ItemViewAdapter(view, viewType)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val isNightmode = sharedPreferences.getString("themepref", "dark")
        val islamicfont = "AlQalam.ttf"
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "quranicfontregular.ttf")
        val custom_font = Typeface.createFromAsset(
            context.assets,
            islamicfont
        )
        val FONTS_LOCATION_PATH = "font/dejavusans.ttf"
        val defaultfont = sharedPreferences.getBoolean("default_font", true)
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
            val tauba = chapterno.toInt()
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
            var headercolor: Int
            if (isNightmode == "dark" || isNightmode == "blue" || isNightmode == "green") {
                holder.ivLocationmakki!!.setColorFilter(Color.CYAN)
                holder.ivSurahIcon!!.setColorFilter(Color.CYAN)
                holder.ivLocationmadani!!.setColorFilter(Color.CYAN)
            } else {
                holder.ivLocationmakki!!.setColorFilter(Color.BLACK)
                holder.ivSurahIcon!!.setColorFilter(Color.BLACK)
                holder.ivLocationmadani!!.setColorFilter(Color.BLACK)
            }
        } else {
            val page = fullQuranPages[position - 1]
            //  String s=      passage.get(position);
            val pageNum = page.pageNum.toString()
            holder.pagenum!!.text = pageNum
            var aya: String
            val builder = StringBuilder()
            for (ayahItem in page.ayahItemsquran!!) {
                aya = ayahItem!!.qurantext
                builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ", aya, ayahItem.ayah))
            }
            holder.quran_textView.setText(
                getSpannable(builder.toString()),
                TextView.BufferType.SPANNABLE
            )
            holder.quran_textView.typeface = custom_font
            if (!defaultfont) {
                holder.quran_textView.textSize = arabicfontSize.toFloat()
            }
        }
    }

    // --Commented out by Inspection START (25/08/23, 7:28 pm):
    //    public void displayAyats(boolean showrootkey, PageMushaAudioAdapter.ItemViewAdapter holder, int position, SharedPreferences sharedPreferences, Typeface custom_font, boolean showErab, boolean showWordColor, boolean showTransliteration, boolean showJalalayn, boolean showTranslation, boolean showWordByword, String whichtranslation, boolean showKathir) {
    //        //   holder.flowwbw.setBackgroundColor(R.style.Theme_DarkBlue);
    //        QuranEntity entity = null;
    //
    //        //   String wbw = sharedPreferences.getString("wordByWord", String.valueOf(Context.MODE_PRIVATE));
    //
    //
    //
    //
    //    /*    if(entity.getPassage_no()!=0){
    //            StringBuilder builder = new StringBuilder();
    //            builder.append(entity.getQurantext());
    //            builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ {2}", aya, entity.getAyah(),R.drawable.ruku_new));
    //            //     builder.append("۩");
    //            holder.quran_textView.setText(entity.getQurantext());
    //            holder.quran_textView.setTextSize(arabicfontSize);
    //            holder.quran_textView.setTypeface(custom_font);
    //
    //          //  holder.sajdaverse.setImageResource(R.drawable.ruku_new);
    //
    //        }*/
    //
    //     /*   if(entity.getHas_prostration()==1) {
    //
    //            StringBuilder builders = new StringBuilder();
    //            builder.append(entity.getQurantext());
    //            builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ۩", aya, entity.getAyah()));
    //       //     builder.append("۩");
    //            holder.quran_textView.setText(builder.toString());
    //            holder.quran_textView.setTextSize(arabicfontSize);
    //            holder.quran_textView.setTypeface(custom_font);
    //        }else{
    //            StringBuilder builders = new StringBuilder();
    //            builder.append(entity.getQurantext());
    //            builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ", aya, entity.getAyah()));
    //
    //            holder.quran_textView.setText(builder.toString());
    //            holder.quran_textView.setTextSize(arabicfontSize);
    //            holder.quran_textView.setTypeface(custom_font);
    //        }
    //*/
    //
    //        System.out.println("Position" + position);
    //
    //        if (showTranslation) {
    //            if (whichtranslation.equals("en_arberry")) {
    //                if (entity != null) {
    //                    holder.translate_textView.setText(entity.getEn_arberry());
    //                }
    //                holder.translate_textViewnote.setText(R.string.arberry);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setVisibility(View.VISIBLE);
    //                holder.translate_textViewnote.setVisibility(View.VISIBLE);
    //            }
    //            if (whichtranslation.equals("en_sahih")) {
    //                if (entity != null) {
    //                    holder.translate_textView.setText(entity.getTranslation());
    //                }
    //                holder.translate_textViewnote.setText(R.string.ensahih);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setVisibility(View.VISIBLE);
    //                holder.translate_textViewnote.setVisibility(View.VISIBLE);
    //            }
    //            if (whichtranslation.equals("en_jalalayn")) {
    //                if (entity != null) {
    //                    holder.translate_textView.setText(entity.getEn_jalalayn());
    //                }
    //                holder.translate_textViewnote.setText(R.string.enjalalayn);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setVisibility(View.VISIBLE);
    //                holder.translate_textViewnote.setVisibility(View.VISIBLE);
    //            }
    //            if (whichtranslation.equals("ur_jalalayn")) {
    //                if (entity != null) {
    //                    holder.translate_textView.setText(entity.getUr_jalalayn());
    //                }
    //                holder.translate_textViewnote.setText(R.string.enjalalayn);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setVisibility(View.VISIBLE);
    //                holder.translate_textViewnote.setVisibility(View.VISIBLE);
    //            }
    //            if (whichtranslation.equals("ur_junagarhi")) {
    //                if (entity != null) {
    //                    holder.translate_textView.setText(entity.getUr_junagarhi());
    //                }
    //                holder.translate_textViewnote.setText(R.string.urjunagadi);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setTextSize(translationfontsize);
    //                holder.translate_textView.setVisibility(View.VISIBLE);
    //                holder.translate_textViewnote.setVisibility(View.VISIBLE);
    //            }
    //            holder.translate_textView.setTextSize(translationfontsize);
    //            holder.translate_textView.setTextSize(translationfontsize);
    //            holder.translate_textView.setVisibility(View.VISIBLE);
    //            holder.translate_textViewnote.setVisibility(View.VISIBLE);
    //
    //        }
    //
    //    }
    // --Commented out by Inspection STOP (25/08/23, 7:28 pm)
    inner class ItemViewAdapter internal constructor(view: View, viewType: Int) :
        RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        var pagenum: TextView? = null
        var ivBismillah: ImageView? = null
        var tvSura: TextView? = null
        var tvRukus: TextView? = null
        var tvVerses: TextView? = null
        lateinit var quran_textView: TextView
        var ivSurahIcon: ImageView? = null
        var ivLocationmakki: ImageView? = null
        var ivLocationmadani: ImageView? = null

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
                pagenum = view.findViewById(R.id.pagenum)


                //    verse_idTextView = view.findViewById(R.id.verse_id_textView);


                //     erab_textView.setTextIsSelectable(true);
                quran_textView = view.findViewById(R.id.quran_textView)
                view.setOnClickListener(this)
                view.setOnLongClickListener(this)
                quran_textView.setOnClickListener(this)
                quran_textView.tag = "verse"
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
        // --Commented out by Inspection (25/08/23, 7:28 pm):BroadcastReceiver broadcastReceiver;
        // --Commented out by Inspection (25/08/23, 7:28 pm):private final boolean isFABOpen = false;
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}