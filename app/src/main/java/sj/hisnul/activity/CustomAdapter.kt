package sj.hisnul.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mushafconsolidated.R
import java.util.Locale

class CustomAdapter(
    private val context: Context,
    private val parentItemArrayList: ArrayList<ParentItem>,
) : BaseExpandableListAdapter() {
    private val originalList: ArrayList<ParentItem> = ArrayList()
    private var childItemArrayList: ArrayList<ChildItem>? = null

    init {
        originalList.addAll(parentItemArrayList)
    }


    override fun getGroupCount(): Int {
        return parentItemArrayList.size
    }

    override fun getChildrenCount(i: Int): Int {
        childItemArrayList = parentItemArrayList[i].childList
        return childItemArrayList!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return parentItemArrayList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        childItemArrayList = parentItemArrayList[groupPosition].childList
        return childItemArrayList!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        view: View?,
        parent: ViewGroup?,
    ): View {
        var view = view
        val parentItemInfo = getGroup(groupPosition) as ParentItem
        if (view == null) {
            val inflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //  view = inflater.inflate(R.layout.group_items,null);
            view = inflater.inflate(R.layout.nlist_group, null)
        }
        val tv_parentItem = view!!.findViewById<View>(R.id.tv_parentItem) as TextView
        val iv_groupIndicator = view.findViewById<View>(R.id.iv_groupIndicator) as ImageView
        tv_parentItem.text = parentItemInfo.name
        if (isExpanded) {
            iv_groupIndicator.setImageResource(R.drawable.baseline_expand_24)
        } else {
            iv_groupIndicator.setImageResource(R.drawable.baseline_close_24)
        }
        iv_groupIndicator.isSelected = isExpanded
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view: View?,
        viewGroup: ViewGroup?,
    ): View {
        var view = view
        val childItemInfo = getChild(groupPosition, childPosition) as ChildItem
        getGroup(groupPosition)
        if (view == null) {
            val layoutInflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //    view = layoutInflater.inflate(R.layout.child_items,null);
            view = layoutInflater.inflate(R.layout.list_item, null)
        }
        val tv_childItem = view!!.findViewById<View>(R.id.tv_childItem) as TextView
        tv_childItem.text = childItemInfo.name
        return view
    }

    override fun isChildSelectable(i: Int, i1: Int): Boolean {
        return true
    }

    fun filterData(query: String) {
        var query = query
        query = query.lowercase(Locale.getDefault())
        parentItemArrayList.clear()
        if (query.isEmpty()) {
            parentItemArrayList.addAll(originalList)
        } else {
            for (parentItem in originalList) {
                val childList = parentItem.childList
                val newlist = ArrayList<ChildItem>()
                for (childRow in childList) {
                    if (childRow.name.lowercase(Locale.getDefault()).contains(query)) {
                        newlist.add(childRow)
                    }
                }
                if (newlist.size > 0) {
                    val nParentRow = ParentItem(parentItem.name, newlist)
                    parentItemArrayList.add(nParentRow)
                }
            }
        } // end else
        notifyDataSetChanged()
    }
}