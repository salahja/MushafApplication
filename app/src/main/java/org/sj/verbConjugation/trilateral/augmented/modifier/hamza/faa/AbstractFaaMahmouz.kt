package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier

abstract class AbstractFaaMahmouz : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            3 -> {
                when (formulaNo) {
                    2, 3, 5, 7, 8, 9 -> return true
                }
                return formulaNo == 5
            }

            4 -> return formulaNo == 5
            5 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
            }

            15 -> {
                when (formulaNo) {
                    2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
            }

            18, 27 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
            }

            21, 24 -> when (formulaNo) {
                1, 2, 3, 5, 7, 8, 9 -> return true
            }
        }
        return false
    }
}