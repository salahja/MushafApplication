package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier

abstract class AbstractEinMahmouz : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            6 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            9 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            13 -> {
                when (formulaNo) {
                    1, 2, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            22 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            25 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            29 -> when (formulaNo) {
                5, 7, 9 -> return true
            }
        }
        return false
    }
}