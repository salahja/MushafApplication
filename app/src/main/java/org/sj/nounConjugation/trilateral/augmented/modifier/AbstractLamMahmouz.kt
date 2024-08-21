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
abstract class AbstractLamMahmouz : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        when (kov) {
            4, 7, 10, 16, 19 -> return true
        }
        return false
    }
}