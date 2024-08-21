package com.example.mushafconsolidatedimport


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import com.example.mushafconsolidated.R
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
class VerbFormsDialogFrag : BottomSheetDialogFragment() {
    private var form: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //  return inflater.inflate(R.layout.verb_forms, container, false);
        val view: View = inflater.inflate(R.layout.webview, container, false)
        val bundle = this.arguments
        val stringArray = bundle!!.getStringArray(ARG_OPTIONS_DATA)
        form = stringArray!![0]
        val wv = view.findViewById<View>(R.id.webview) as WebView

        var formstr: String? = "Form"
        formstr = if (!form!!.contains("Form")) {
            "$formstr $form"
        } else {
            form
        }
        val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
        val list = mainViewModel.getGramarRulesbyHarf(formstr!!).value

        if (list != null) {
            if (list.isNotEmpty()) {
                wv.loadDataWithBaseURL(null, list[0].detailsrules, "text/html", "utf-8", null)
            }
        }
        return view
    }

    companion object {
        //   public class VerbFormsDialogFrag extends Fragment {
        private const val ARG_OPTIONS_DATA = "item_count"
        fun newInstance(data: Array<String?>?): VerbFormsDialogFrag {
            val fragment = VerbFormsDialogFrag()
            val args = Bundle()
            args.putStringArray(ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}