package com.example.mushafconsolidated.fragments

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

class GrammarFragmentsListAdapter(
    private val context: Context, private val expandableListTitle: List<String>,
    private val expandableListDetail: HashMap<String, List<SpannableString>>
) : BaseExpandableListAdapter() {
    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return expandableListDetail[expandableListTitle[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView: View? = null
        val expandedListText =
            getChild(listPosition, expandedListPosition)
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_grammar_item, parent, false)
        }
        val mequran =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.assets, "Roboto.ttf")
        val expandedListTextView =
            convertView?.findViewById<View>(R.id.expandedListItem) as TextView
        expandedListTextView.text = expandedListText as CharSequence?
        //    expandedListTextView.setTypeface(mequran);
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetail[expandableListTitle[listPosition]]!!.size
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
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView: View? = null
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, parent, false)
        }
        val listTitleTextView = convertView
            ?.findViewById<View>(R.id.listTitle) as TextView
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        val preferences = prefs.getString("theme", "dark")
        val dark = preferences == "dark" || preferences == "blue" || preferences == "green"
        if (dark) {
            listTitleTextView.setTextColor(Color.CYAN)
        } else {
            listTitleTextView.setTextColor(
                ContextCompat.getColor(
                    QuranGrammarApplication.context!!,
                    R.color.burntamber
                )
            )
        }
        listTitleTextView.setTypeface(null, Typeface.BOLD)
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