package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
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
class WawiPassivePresentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ُوْ", "ُو")) // EX: (يُوعَدُ، يُوهَبُ، يُورَثُ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return ((((((kov == 9) && (noc == 2)) || (kov == 10)) && ((noc == 3) || (noc == 4) || (noc == 5))) || ((kov == 11) && ((noc == 2) || (noc == 3) || (noc == 4) || (noc == 5)))))
    }
}