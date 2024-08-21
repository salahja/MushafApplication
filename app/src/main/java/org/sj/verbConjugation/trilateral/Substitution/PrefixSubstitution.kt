package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot

class PrefixSubstitution(segment: String, result: String) : Substitution(segment, result) {
    override fun apply(word: String, root: TrilateralRoot): String? {
        return if (word.startsWith(segment)) word.replaceFirst(segment.toRegex(), result) else null
    }
}