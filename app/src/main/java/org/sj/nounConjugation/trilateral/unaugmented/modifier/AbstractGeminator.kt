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
abstract class AbstractGeminator : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            2 -> {
                when (noc) {
                    1, 2, 3, 4, 5 -> return true
                }
                return noc == 2 || noc == 1
            }

            3 -> return noc == 2 || noc == 1
            8 -> return noc == 4
            12 -> return noc == 2 || noc == 4
        }
        return false
    }
}