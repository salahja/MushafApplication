package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier

abstract class AbstractLamMahmouz : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            4 -> return formulaNo == 5
            7 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 7, 8, 9 -> return true
                }
            }

            10, 16 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 7, 8, 9 -> return true
                }
            }

            19 -> when (formulaNo) {
                1, 2, 3, 7, 8, 9 -> return true
            }
        }
        return false
    }
}