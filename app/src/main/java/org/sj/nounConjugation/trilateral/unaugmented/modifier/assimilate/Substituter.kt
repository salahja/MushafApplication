package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate

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
class Substituter : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("اءَا", "اوَا")) // EX: (عَمْياوان)
        substitutions.add(InfixSubstitution("اءَي", "اوَي")) // EX: (عَمْياوين)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        return conjugationResult.nounFormula == "فَعْلان"
    }
}