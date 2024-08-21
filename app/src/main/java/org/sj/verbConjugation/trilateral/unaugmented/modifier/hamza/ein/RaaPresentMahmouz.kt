package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.TrilateralRoot
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

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
class RaaPresentMahmouz : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
     override val substitutions: MutableList<InfixSubstitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("ْءَ", "َ")) // EX: (يَرَى)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root: TrilateralRoot? = conjugationResult.root!!
        return root!!.c1 == 'ر' && root!!.c2 == 'ء' && root!!.c3 == 'ي'
    }
}