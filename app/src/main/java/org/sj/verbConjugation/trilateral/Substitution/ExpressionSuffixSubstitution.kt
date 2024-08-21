package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot

class ExpressionSuffixSubstitution(segment: String, result: String) :
    Substitution(segment, result) {
    override fun apply(word: String, root: TrilateralRoot): String? {
        var wordSegment = segment.replace("C1".toRegex(), root.c1.toString() + "")
        wordSegment = wordSegment.replace("C2".toRegex(), root.c2.toString() + "")
        wordSegment = wordSegment.replace("C3".toRegex(), root.c3.toString() + "")
        if (!word.endsWith(wordSegment)) return null
        var replacedResult = result.replace("C1".toRegex(), root.c1.toString() + "")
        replacedResult = replacedResult.replace("C2".toRegex(), root.c2.toString() + "")
        replacedResult = replacedResult.replace("C3".toRegex(), root.c3.toString() + "")
        return word.replace(wordSegment.toRegex(), replacedResult)
    }



}