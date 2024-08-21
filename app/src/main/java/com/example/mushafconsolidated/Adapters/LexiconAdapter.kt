package com.example.mushafconsolidated.Adaptersimport

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.QuranGrammarApplication


class LexiconAdapter(
    private val lanesdictionary: ArrayList<String>,
    private val context: Context?,
    var language: String
) : RecyclerView.Adapter<LexiconAdapter.ItemViewAdapter>() {
    var mItemClickListener: OnItemClickListener? = null
    var isSarfSagheerMazeed: Boolean = false

    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();
    private val sarfsagheer: ArrayList<ArrayList<*>>? = null
    public override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LexiconAdapter.ItemViewAdapter {
        val view: View
        view = LayoutInflater.from(parent.context!!)
            .inflate(R.layout.dictionary_layout, parent, false)
        return ItemViewAdapter(view)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onBindViewHolder(holder: LexiconAdapter.ItemViewAdapter, position: Int) {
        //  Typeface typeface = Typeface.createFromAsset(context.getAssets(), quranfont);
        //   Typeface mequran = Typeface.createFromAsset(DarkThemeApplication.context!!.getAssets(), "Roboto.ttf");
        val mequran: Typeface =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.assets, "Taha.ttf")
        var lanes: String = lanesdictionary[position]
        holder.wordDictionary.requestFocus()
        holder.wordDictionary.settings.lightTouchEnabled = true
        holder.wordDictionary.settings.builtInZoomControls = true
        holder.wordDictionary.settings.builtInZoomControls = true
        holder.wordDictionary.settings.setSupportZoom(true)
        val webSettings: WebSettings = holder.wordDictionary.settings
        webSettings.javaScriptEnabled = true
        //  mTextView.setInitialScale(1);
        //   mTextView.getSettings().setLoadWithOverviewMode(true);
        //    mTextView.getSettings().setUseWideViewPort(true);
        //  mTextView.getSettings().setBuiltInZoomControls(true);
        val data: StringBuilder = StringBuilder()
        if (((language == "imperative") || (language == "genetivenoun") || (language == "accusativenoun") || (language == "nominativenoun") || (language == "accusative") || (language == "preposition") || (language == "conditonal") || (language == "relative") || (language == "dem") || (language == "Jussive") || (language == "Subjunctive"))) {
            // webView.loadDataWithBaseURL(htmlData, "text/html", "utf-8", null);
            holder.wordDictionary.loadDataWithBaseURL(
                "file:///android_asset/",
                lanes,
                "text/html",
                "utf-8",
                null
            )
            holder.wordDictionary.settings.builtInZoomControls = true
        } else if ((language == "lanes")) {
            //   wv.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);
            data.append("<!DOCTYPE html><html lang=\"en\"><head>")
            data.append("<LINK href=\"lexicon.css\" type=\"text/css\" rel=\"stylesheet\"/></head>")
                .append(lanes)
            //    holder. wordDictionary.loadDataWithBaseURL("file:///android_asset/", data .toString(), "text/html", "utf-8", null);
            //    holder.wordDictionary.loadDataWithBaseURL(null, lanes , "text/html", "utf-8", null);
            lanes = "<link rel=\"stylesheet\" type=\"text/css\" href=\"lexicon.css\" />$lanes"
            // lets assume we have /assets/style.css file

            // holder.wordDictionary.loadDataWithBaseURL("file:///android_asset/", data.toString(), "text/html", "UTF-8", null);
            holder.wordDictionary.settings.builtInZoomControls = true
            holder.wordDictionary.loadDataWithBaseURL(
                "file:///android_asset/",
                lanes,
                "text/html",
                "utf-8",
                null
            )
        } else if ((language == "hans")) {
            //   data .append("<HTML><HEAD><LINK href=\"entry.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
            //  data .append("</body></HTML>");
            //  holder. wordDictionary.loadDataWithBaseURL("file:///android_asset/", data .toString(), "text/html", "utf-8", null);
            holder.wordDictionary.loadDataWithBaseURL(
                null, lanes //  data .append("</body></HTML>");
                //  holder. wordDictionary.loadDataWithBaseURL("file:///android_asset/", data .toString(), "text/html", "utf-8", null);
                , "text/html", "utf-8", null
            )
            holder.wordDictionary.settings.builtInZoomControls = true
        }
    }

    public override fun getItemId(position: Int): Long {
        return lanesdictionary[position].length.toLong()
    }

    public override fun getItemCount(): Int {
        return lanesdictionary.size
    }

    fun getItem(position: Int): Any {
        return lanesdictionary[position]
    }

    inner class ItemViewAdapter constructor(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        private val wordDictionaryUrdu: TextView
        var meaning: TextView? = null
        private val rootwowrd: TextView
        var arabicword: TextView? = null
        private var referenceView: TextView? = null
        var dismissview: ImageView? = null
        var wordDictionary: WebView
        var i: Int = ContextCompat.getColor(context!!, R.color.kashmirigreen)

        init {
            rootwowrd = view.findViewById(R.id.rootward)
            arabicword = view.findViewById(R.id.arabicword)
            wordDictionary = view.findViewById(R.id.wordDictionary)
            wordDictionaryUrdu = view.findViewById(R.id.wordDictionaryUrdu)
            meaning = view.findViewById(R.id.meaning)
            referenceView = view.findViewById(R.id.referenceView)
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}