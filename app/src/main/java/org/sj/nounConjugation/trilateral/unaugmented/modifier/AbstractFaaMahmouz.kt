package org.sj.nounConjugation.trilateral.unaugmented.modifier

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier

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
abstract class AbstractFaaMahmouz : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        when (kov) {
            3, 4, 5, 15, 18, 21, 24, 27 -> return true
        }
        return false
    }
}