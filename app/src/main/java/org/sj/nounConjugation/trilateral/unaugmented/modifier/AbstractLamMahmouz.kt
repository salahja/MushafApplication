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
abstract class AbstractLamMahmouz : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        when (kov) {
            4, 7, 10, 16, 19 -> return true
        }
        return false
    }
}