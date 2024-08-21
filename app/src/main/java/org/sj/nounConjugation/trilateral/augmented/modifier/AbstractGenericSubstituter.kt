package org.sj.nounConjugation.trilateral.augmented.modifier

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier

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
abstract class AbstractGenericSubstituter : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        if (formulaNo != 5) {
            return false
        }
        when (kov) {
            1, 2, 6, 7, 16, 17, 20, 23, 26, 28 -> return true
        }
        return false
    }
}