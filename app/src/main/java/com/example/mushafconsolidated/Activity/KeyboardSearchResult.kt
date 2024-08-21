package com.example.mushafconsolidated.Activity

import android.os.Bundle
import android.text.SpannableString

class KeyboardSearchResult : WordOccuranceAct() {
    private val expandlexicon = LinkedHashMap<String, ArrayList<SpannableString>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        executeDictionary()
    }

    private fun executeDictionary() {
        //  ArrayList<lanelexicon> lanesDifinition = utils.getLanesDifinition(root);
        //  ArrayList<SpannableString> lanesdifinition ;
        val list: ArrayList<SpannableString> = ArrayList()
        val lanessb = StringBuilder()
        //  for (lanelexicon lanes : lanesDifinition) {
        //  <p style="margin-left:200px; margin-right:50px;">
        //    list.add("<p style=\"margin-left:200px; margin-right:50px;\">");
        //  list.add("<p style=\"margin-left:200px; margin-right:50px;\">");
        //  list.add(lanes.getDefinition() );
        list.add(SpannableString.valueOf(""))
        //   }
        expandlexicon["lanes Lexicon"] = list
        expandlexicon["Hans"] = list
        val expandLexconTitle: List<String>
        expandLexconTitle = ArrayList(expandlexicon.keys)
        expandNounTitles = ArrayList<String>(expandNounVerses.keys)
        expandVerbTitles = ArrayList<String>(expandVerbVerses.keys)
        expandNounVerses.putAll(expandlexicon)
        expandNounVerses.putAll(expandVerbVerses)
        expandNounTitles.addAll(expandLexconTitle)
        expandNounTitles.addAll(expandVerbTitles)
    }
}