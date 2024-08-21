package com.example.mushafconsolidated.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication

class NounVerbOccuranceListAdapter(
// private   HashMap<String, List<SpannableStringBuilder>> expandableListDetail;
    private val context: Context,
    private val expandableListTitle: List<String>,
    expandNounVerses: LinkedHashMap<String, ArrayList<SpannableString>>,
    expandVerbVerses: LinkedHashMap<String, ArrayList<SpannableString>>,
    expandVerbTitles: List<String>,
) : BaseExpandableListAdapter() {
    private val expandVerbTitles: List<String>
    private val expandVerbVerses: LinkedHashMap<String, ArrayList<SpannableString>>
    private var expandNounVerses = LinkedHashMap<String, ArrayList<SpannableString>>()

    init {
        this.expandNounVerses = expandNounVerses
        this.expandVerbTitles = expandVerbTitles
        this.expandVerbVerses = expandVerbVerses
    }

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return expandNounVerses[expandableListTitle[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        //  SpannableString expandedListText = (SpannableString) getChild(listPosition, expandedListPosition);
        //  var convertView: View?=convertView
        var convertView: View? = null
        //    var convertView = convertView
        val child = getChild(listPosition, expandedListPosition)
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_grammar_item, null)
        }
        val mequran =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.assets, "Taha.ttf")
        //  Typeface mequran = Typeface.createFromAsset(DarkThemeApplication.getContext().getAssets(), quranfont);
        val expandedListTextView =
            convertView!!.findViewById<View>(R.id.expandedListItem) as TextView
        val expandedListTextViewlane = convertView
            .findViewById<View>(R.id.expandedListItemverb) as TextView
        val contains = false
        // expandedListTextView.setText(HtmlCompat.fromHtml(String.valueOf(child), 0));
        //  expandedListTextView.setText((CharSequence) child);
        // expandedListTextView.setText(HtmlCompat.fromHtml(String.valueOf(child), 0));
        //  expandedListTextView.setText((CharSequence) child);
        expandedListTextViewlane.text = child as CharSequence
        //  expandedListTextView.setText(HtmlCompat.fromHtml(String.valueOf(child), 0));
        //  expandedListTextView.setText(HtmlCompat.fromHtml(String.valueOf(child), 0));
        expandedListTextViewlane.typeface = mequran
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandNounVerses[expandableListTitle[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup,
    ): View {
        var convertView: View? = null
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById<View>(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        val preferences = prefs.getString("theme", "dark")
        if (preferences == "dark" || preferences == "blue" || preferences == "green") {
            listTitleTextView.setTextColor(Color.CYAN)
        } else {
            listTitleTextView.setTextColor(
                ContextCompat.getColor(
                    QuranGrammarApplication.context!!,
                    R.color.burntamber
                )
            )
        }
        listTitleTextView.textSize = 18f
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}