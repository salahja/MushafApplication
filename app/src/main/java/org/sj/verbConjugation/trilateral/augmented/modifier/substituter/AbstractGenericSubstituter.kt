package org.sj.verbConjugation.trilateral.augmented.modifier.substituter

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier

abstract class AbstractGenericSubstituter : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        if (formulaNo != 5) return false
        val kovCond = false
        when (kov) {
            1, 2, 6, 7, 16, 17, 20, 23, 26, 28 -> return true
        }
        return false
    }
}