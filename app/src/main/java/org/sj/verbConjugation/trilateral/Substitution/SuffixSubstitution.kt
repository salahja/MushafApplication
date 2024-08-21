package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot

class SuffixSubstitution(segment: String, result: String) : Substitution(segment, result) {
    override fun apply(word: String, root: TrilateralRoot): String? {
        if (word.endsWith(segment)) {
            val changeIndex = word.lastIndexOf(segment)
            //String replacement = word.substring(changeIndex, word.length());
            //replacement.replaceAll(segment, result);
            return word.substring(0, changeIndex) + result
        }
        return null
    }
}