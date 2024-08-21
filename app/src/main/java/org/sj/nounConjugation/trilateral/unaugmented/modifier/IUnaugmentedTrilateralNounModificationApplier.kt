package org.sj.nounConjugation.trilateral.unaugmented.modifier

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
interface IUnaugmentedTrilateralNounModificationApplier {
    fun isApplied(conjugationResult: ConjugationResult): Boolean
}