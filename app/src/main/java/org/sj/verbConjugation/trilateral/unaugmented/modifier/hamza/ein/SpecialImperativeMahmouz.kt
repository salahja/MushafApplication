package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein

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
class SpecialImperativeMahmouz : SubstitutionsApplier(),
    IUnaugmentedTrilateralModifier {
    



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        return root!!.c1 == 'س' && root!!.c2 == 'ء' && root!!.c3 == 'ل'
    }

    /**
     * override this method to return the custom list
     *
     * @param words List
     * @param root!!  TrilateralRoot
     */
    override fun apply(words: MutableList<Any>, root: TrilateralRoot?) {
        words.set(2, "سَلْ/اسْأَلْ")
        words.set(3, "سَلِي/اسْأَلِي")
        words.set(4, "سَلاَ/اسْأَلاَ")
        words.set(5, "سَلُوا/اسْأَلُوا")
        words.set(6, "سَلْنَ/اسْأَلْنَ")
    }
    override val substitutions: List<*>
        get() =  substitutions

}
