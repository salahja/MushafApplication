package org.sj.verbConjugation.trilateral.augmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic.ActivePastGeminator
import org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic.ActivePresentGeminator
import org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic.ImperativeGeminator
import org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic.PassivePastGeminator
import org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic.PassivePresentGeminator
import org.sj.verbConjugation.util.SystemConstants

class MazeedGenericGeminator : IAugmentedTrilateralModifier {
    private val geminators: MutableMap<String, SubstitutionsApplier> = HashMap()

    init {
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

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (formulaNo) {
            6 -> {
                when (kov) {
                    1, 6, 17, 20 -> return true
                }
                return false
            }

            12 -> {
                when (kov) {
                    1, 11, 17, 20 -> return true
                }
                return false
            }

            1, 4 -> return kov == 2
            3, 7 -> return kov == 2 || kov == 3 || kov == 8
            5, 9 -> return kov == 2 || kov == 3
        }
        return false
    }

    fun apply(tense: String, active: Boolean, conjResult: MazeedConjugationResult) {
        val geminator = geminators[tense + active]
        geminator!!.apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    }
}