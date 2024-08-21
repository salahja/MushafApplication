package org.sj.nounConjugation.trilateral.unaugmented.modifier.elative

import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.elative.hamza.EinMahmouz
import org.sj.nounConjugation.trilateral.unaugmented.modifier.elative.hamza.LamMahmouz
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
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
class Mahmouz {
    private val modifiers: MutableList<IUnaugmentedTrilateralNounModificationApplier> = LinkedList()

    init {
        modifiers.add(EinMahmouz())
        modifiers.add(LamMahmouz())
    }

    fun apply(conjResult: ConjugationResult) {
        val iter: Iterator<IUnaugmentedTrilateralNounModificationApplier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next()
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}