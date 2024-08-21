package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa

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
class SpecialImperativeMahmouz2() : SubstitutionsApplier(),
    IUnaugmentedTrilateralModifier {
    

  

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        return root!!.c1 == 'ء' && root!!.c2 == 'ك' && root!!.c3 == 'ل' && root!!.conjugation == "1"
    }

    /**
     * override this method to return the custom list
     *
     * @param words List
     * @param root!!  TrilateralRoot
     */
    override fun apply(words: MutableList<Any>, root: TrilateralRoot?) {
        words.set(2, "كُلْ")
        words.set(3, "كُلِي")
        words.set(4, "كُلاَ")
        words.set(5, "كُلُوا")
        words.set(6, "كُلْنَ")
    }

    override val substitutions: List<*>
        get() =  substitutions
}
