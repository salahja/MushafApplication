package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer

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
class Vocalizer31 : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِيو", "ِيّ")) // EX: (بهيّ، سويّ، )
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
      return   ((kov == 23) && ((noc == 4) || (noc == 5))) || ((kov == 28) && (noc == 4)) && nounFormula == "فَعِيل" && conjugationResult.root!!.c3 == 'و'
      //  return nounFormula == "فَعِيل" && conjugationResult.root!!.c3 == 'و' && (kov == 23 && (noc == 4 || noc == 5) || kov) == 28 && noc == 4
    }
}