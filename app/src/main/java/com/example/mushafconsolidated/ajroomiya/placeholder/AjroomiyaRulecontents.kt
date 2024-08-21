package com.example.mushafconsolidated.ajroomiya.placeholder

import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.Utils
import com.example.utility.QuranGrammarApplication


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object AjroomiyaRulecontents {
    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<GrammarRules?> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    //   public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();
    val ITEM_MAP: MutableMap<String, GrammarRules?> = HashMap()
    private const val COUNT = 25

    /*

        static {
            // Add some sample items.
            for (int i = 1; i <= COUNT; i++) {
                addItem(createPlaceholderItem(i));
            }
        }
     */
    init {
        val util = Utils(QuranGrammarApplication.context!!)
        val rules: ArrayList<GrammarRules?>? = util.grammarRules as ArrayList<GrammarRules?>?
        for (s in rules!!) {
            addItems(s)
        }
    }

    private fun addItems(item: GrammarRules?) {
        ITEMS.add(item)
        ITEM_MAP[item!!.id.toString()] = item
    }

    /**
     * A placeholder item representing a piece of content.
     */
    class PlaceholderItem(val id: String, val content: String, val details: String) {
        override fun toString(): String {
            return content
        }
    }
}