package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.pre.vocalizer.SeparatedLafifActivePresentVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.pre.vocalizer.SeparatedLafifPassivePresentVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.pre.vocalizer.SeparatedLafifPassviePastVocalizer
import org.sj.verbConjugation.util.SystemConstants

class PreVocalizer {
    private val separatedLafifActivePresentVocalizer = SeparatedLafifActivePresentVocalizer()
    private val separatedLafifPassivePresentVocalizer = SeparatedLafifPassivePresentVocalizer()
    private val separatedLafifPassivePastVocalizer = SeparatedLafifPassviePastVocalizer()
    fun apply(tense: String, active: Boolean, conjResult: MazeedConjugationResult) {
        if (active) {
            if (tense == SystemConstants.PRESENT_TENSE && separatedLafifActivePresentVocalizer.isApplied(
                    conjResult
                )
            ) separatedLafifActivePresentVocalizer.apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
        } else {
            if (tense == SystemConstants.PRESENT_TENSE && separatedLafifPassivePresentVocalizer.isApplied(
                    conjResult
                )
            ) separatedLafifPassivePresentVocalizer.apply(
                conjResult.finalResult as MutableList<Any>,
                conjResult.root!!
            ) else if (tense == SystemConstants.PAST_TENSE && separatedLafifPassivePastVocalizer.isApplied(
                    conjResult
                )
            ) separatedLafifPassivePastVocalizer.apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
        }
    }
}