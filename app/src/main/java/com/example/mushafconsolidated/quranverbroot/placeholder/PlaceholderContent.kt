package com.example.mushafconsolidated.quranverbroot.placeholder

import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.utility.QuranGrammarApplication

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {
    private var searchs: String? = null

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<ItemHolder> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, ItemHolder> = HashMap()
    var root = ArrayList<String>()
    private const val COUNT = 25

    init {
        // Add some sample items.
        for (i in 0..27) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: ItemHolder) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createPlaceholderItem(position: Int): ItemHolder {
        val array: Array<String> = QuranGrammarApplication.context!!.resources
            .getStringArray(R.array.arabicletters)
        // return new PlaceholderItem(String.valueOf(position), "Item " + position, makeDetails(position));
        return ItemHolder(
            position.toString(),
            array[position], makeDetailso(position, array[position], ArrayList()), ArrayList()
        )
    }

    private fun makeDetails(position: Int): ArrayList<String> {

        //    StringBuilder builder = new StringBuilder();
        //   builder.append("Details about Item: ").append(position);
        val details = ArrayList<String>()
        for (i in 0 until position) {
            details.add("details about item")
        }
        //  return builder.toString();
        return details
    }

    private fun makeDetailso(
        position: Int,
        s: String,
        strings: ArrayList<String>
    ): ArrayList<String> {
        val builder = StringBuilder()
        val indexOf = s.indexOf("ا")
        var fstr = ""
        if (indexOf != -1) {
            fstr = s.replace("ا", "ء")
        } else {
            fstr = s
        }
        searchs = "$fstr%";
        //%
        val util = Utils(QuranGrammarApplication.context!!)
        //  val letter: ArrayList<qurandictionary> = util.getByfirstletter(searchs) as ArrayList<qurandictionary>
        val letter: ArrayList<VerbCorpus> =
            util.getQuranVerbsByfirstletter(searchs!!) as ArrayList<VerbCorpus>
        for (verb in letter) {
            strings.add(verb.root_a!!)
            //    builder.append(qurandictionary.getRootarabic()).append("\n");
        }


        //   ArrayList<qurandictionary> letter = Utils.getRootwordsbyLetter("غ");
        builder.append("Details about Item: ").append(position)

        return strings
    }

    /**
     * A placeholder item representing a piece of content.
     */
    class ItemHolder(
        val id: String,
        val content: String,
        val details: ArrayList<String>,
        rootsArrayList: ArrayList<String>
    ) {
        private var rootsArrayList = ArrayList<String>()

        init {
            this.rootsArrayList = rootsArrayList
        }

        override fun toString(): String {
            return content
        }
    }

    class roots private constructor(var rootword: String)
}