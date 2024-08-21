package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot

class InfixSubstitution(segment: String, result: String) : Substitution(segment, result) {
    override fun apply(word: String, root: TrilateralRoot): String? {
        return if (word.indexOf(segment) != -1) word.replace(segment.toRegex(), result) else null
    }
}