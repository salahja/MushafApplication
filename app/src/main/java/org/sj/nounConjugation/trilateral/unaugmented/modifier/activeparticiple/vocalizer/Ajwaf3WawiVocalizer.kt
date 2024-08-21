package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import java.util.LinkedList

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
class Ajwaf3WawiVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("اوِ", "ائِ")) // EX: (قائِمٌ)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return kov == 17 && (noc == 1 || noc == 5)
    }
}