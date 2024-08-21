package com.example.mushafconsolidated.Activity.placeholder


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
object AjroomiyaNotesContent {
    /**
     * An array of sample (placeholder) items.
     */
    private val ITEMS: MutableList<GrammarRules> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    //   public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();
    private val ITEM_MAP: MutableMap<String?, GrammarRules?> = HashMap()
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
        val util = Utils(QuranGrammarApplication.context)
        val rules: List<GrammarRules?>? =
            util.grammarRules
        if (rules != null) {
            for (s in rules) {
                addItems(s!!)
            }
        }
    }

    private fun addItems(item: GrammarRules) {
        ITEMS.add(item)
        ITEM_MAP[item.id.toString()] =
            item
    }

    /*
        private static void addItem(PlaceholderItem item) {
            ITEMS.add(item);
            ITEM_MAP.put(item.id, item);
        }

     */
    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(position.toString(), "Item $position", makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val sb = StringBuilder()
            .append("Details about Item: ").append(position)
        for (i in 0 until position) {
            sb.append("\nMore details information here.")
        }
        return sb.toString()
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