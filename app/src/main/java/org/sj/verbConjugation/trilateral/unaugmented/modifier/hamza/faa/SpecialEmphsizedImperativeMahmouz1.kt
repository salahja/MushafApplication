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
class SpecialEmphsizedImperativeMahmouz1 : SubstitutionsApplier(),
    IUnaugmentedTrilateralModifier {
  

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        return root!!.c1 == 'ء' && root!!.c2 == 'خ' && root!!.c3 == 'ذ' && root!!.conjugation == "1"
    }

    /**
     * override this method to return the custom list
     *
     * @param words List
     * @param root!!  TrilateralRoot
     */
    override fun apply(words: MutableList<Any>, root: TrilateralRoot?) {
        words.set(2, "خُذَنَّ")
        words.set(3, "خُذِنَّ")
        words.set(4, "خُذَانِّ")
        words.set(5, "خُذُنَّ")
        words.set(6, "خُذْنَانِّ")
    }

    override val substitutions: List<*>
        get() = TODO("Not yet implemented")


}
