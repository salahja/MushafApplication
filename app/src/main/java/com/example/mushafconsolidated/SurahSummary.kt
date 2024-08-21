package com.example.mushafconsolidated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * FontQuranListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class SurahSummary : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null

    // private ColorSchemeAdapter colorSchemeAdapter;
    var textView: TextView? = null
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.namesadapter, container, false)
        val bundle: Bundle? = arguments
        val webView: WebView = view.findViewById<View>(R.id.title) as WebView
        //  WebSettingsCompat.setForceDark(webView.settings, FORCE_DARK_ON);
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(
                webView.settings,
                WebSettingsCompat.FORCE_DARK_ON
            )
        }
        val html: String = ("<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "@font-face {\n" +
                "    font-family: IndoPak,arial;\n" +
                "    src: url(\"file:///android_asset/me_quran.ttf\")\n" +
                "}\n" +
                " body {\n" +
                "\n" +
                "  margin: 5%;\n" +
                "\n" +
                "}\n" +
                "h3   {color: blue;}\n" +
                "\n" +
                "div { \n" +
                "\n" +
                "  text-align: center;\n" +
                "  font-size: 20px;\n" +
                "}\n" +
                "h1,h2,h2 { \n" +
                "\n" +
                "  text-align: center;\n" +
                "  font-size: 25px;\n" +
                "}\n" +
                "\n" +
                ".ayah{color:#330000;" +
                "font-size:25px;" +
                "font-family:IndoPak,arial;}" +
                ".arabic-text{\n" +
                "  font-family: IndoPak, arial;\n" +
                "  \n" +
                "  border: 2px solid black;\n" +
                "  margin: 20px;\n" +
                "  padding: 20px;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>")
        val close: String = "</body>\n" +
                "</html>"
        assert(bundle != null)
        val item_count: Int = bundle!!.getInt("item_count")
        val utils: Utils = Utils(activity)
        val viewmodel: QuranVIewModel by viewModels()
        viewmodel.getSurahSummary(item_count).observe(this) {
            //  val surahSummary: ArrayList<surahsummary> = utils.getSurahSummary(item_count) as ArrayList<surahsummary>
            //   String  ayah = getVersesDetails(item_count, surahSummary);
            var sum: String = it[0].summary
            sum = sum.replace("God".toRegex(), "Allah(SWT)")
            var odiv: String = "<div>"
            val cdiv: String = "</div>"
            sum = sum.replace("\\.".toRegex(), "<br>")
            odiv = odiv + sum + cdiv
            //   String concat = html.concat(odiv).concat(ayah.toString()).concat(close);
            val concat: String = html + odiv + close
            webView.loadDataWithBaseURL(null, concat, "text/html", "utf-8", null)
        }

        return view
    }

    companion object {
        const val TAG: String = "SURAH"

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(data: Int): SurahSummary {
            val fragment: SurahSummary = SurahSummary()
            val args: Bundle = Bundle()
            args.putInt(SurahSummary.ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}