package org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.generic.ActivePastGeminator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.generic.ActivePresentGeminator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.generic.ImperativeGeminator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.generic.PassivePastGeminator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.generic.PassivePresentGeminator
import org.sj.verbConjugation.util.SystemConstants

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class GenericGeminator : IUnaugmentedTrilateralModifier {
    private val geminators: MutableMap<String, SubstitutionsApplier> = HashMap()

    init {
        //خمس أنواع للادغام للمعلوم والمبني لمجهول في الماضي والمضارع والأمر
        geminators[SystemConstants.PAST_TENSE + "true"] =
            ActivePastGeminator()
        geminators[SystemConstants.PRESENT_TENSE + "true"] =
            ActivePresentGeminator()
        val imperativeGeminator = ImperativeGeminator()
        geminators[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            imperativeGeminator
        geminators[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            imperativeGeminator
        geminators[SystemConstants.PAST_TENSE + "false"] =
            PassivePastGeminator()
        geminators[SystemConstants.PRESENT_TENSE + "false"] =
            PassivePresentGeminator()
    }

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 2) && ((noc == 1) || (noc == 2) || (noc == 3) || (noc == 4) || (noc == 5) || ((kov == 3) && ((noc == 1) || (noc == 2))) || ((kov == 8) && (noc == 4)) || ((kov == 12) && ((noc == 2) || (noc == 4))))
    }

    fun apply(tense: String, active: Boolean, conjResult: ConjugationResult) {
        val geminator = geminators[tense + active]
        geminator!!.apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    }
}