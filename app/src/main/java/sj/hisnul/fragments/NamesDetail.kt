package sj.hisnul.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sj.hisnul.newepository.NewDuaModel


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * FontQuranListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class NamesDetail : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.namesadapter, container, false)
        val bundle: Bundle? = arguments
        val html: String = ("<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "@font-face {\n" +
                "    font-family: IndoPak;\n" +
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
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
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
        val item_count: Int = bundle!!.getInt("item_count")

        val viewmodel: NewDuaModel by viewModels()
        viewmodel.AllahNames(item_count).observe(this){


            val title: WebView = view.findViewById<View>(R.id.title) as WebView
            val concat: String = html + it!![0].title + it[0]
                .summary+ (it[0].details) + close
            title.loadDataWithBaseURL(null, concat, "text/html", "utf-8", null)
        }


        //    title.loadDataWithBaseURL(null,     namesDetails.get(1).getSummary().toString(), "text/html", "utf-8", null);
        return view
        //   return inflater.inflate(R.layout.quranFontselection, container, false);
    } /*

     */

    companion object {
        const val TAG: String = "SURAH"

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(data: Int): NamesDetail {
            val fragment = NamesDetail()
            val args: Bundle = Bundle()
            args.putInt(ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}