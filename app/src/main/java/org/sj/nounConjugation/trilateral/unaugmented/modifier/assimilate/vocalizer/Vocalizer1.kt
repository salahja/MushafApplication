package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
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
class Vocalizer1 : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<SuffixSubstitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (هذا الأعمى، )
        substitutions.add(SuffixSubstitution("ِيَ", "َى")) // EX: (رأيتُ الأعمى ، )
        substitutions.add(SuffixSubstitution("ِيِ", "َى")) // EX: (مررتُ على الأعمى ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return nounFormula == "أفعل" && (kov == 25 || kov == 26) && noc == 4
    }
}