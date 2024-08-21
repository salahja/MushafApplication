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
abstract class AbstractEinMahmouz : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        when (kov) {
            6, 9, 13, 22, 25, 29 -> return true
        }
        return false
    }
}