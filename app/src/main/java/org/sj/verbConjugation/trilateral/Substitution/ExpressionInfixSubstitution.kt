package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot

class ExpressionInfixSubstitution(segment: String, result: String) : Substitution(segment, result) {
    override fun apply(word: String, root: TrilateralRoot): String? {
        if (word.length > 0) {
            ////System.out.printf(word);
        }
        ////System.out.println(segment+","+result);
        var wordSegment = segment.replace("C1".toRegex(), root.c1.toString() + "")
        wordSegment = wordSegment.replace("C2".toRegex(), root.c2.toString() + "")
        wordSegment = wordSegment.replace("C3".toRegex(), root.c3.toString() + "")
        ////System.out.println(wordSegment+","+result);
        if (word.indexOf(wordSegment) == -1) return null
        var replacedResult = result.replace("C1".toRegex(), root.c1.toString() + "")
        replacedResult = replacedResult.replace("C2".toRegex(), root.c2.toString() + "")
        replacedResult = replacedResult.replace("C3".toRegex(), root.c3.toString() + "")
        return word.replace(wordSegment.toRegex(), replacedResult)
    }
}