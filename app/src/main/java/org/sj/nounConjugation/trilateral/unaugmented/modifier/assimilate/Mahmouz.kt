package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.hamza.EinMahmouz
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.hamza.FaaMahmouz
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.hamza.LamMahmouz
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
    private val modifiers: MutableList<TrilateralNounSubstitutionApplier> = LinkedList()

    init {
        modifiers.add(FaaMahmouz())
        modifiers.add(EinMahmouz())
        modifiers.add(LamMahmouz())
    }

    fun apply(conjResult: ConjugationResult) {
        val iter: Iterator<TrilateralNounSubstitutionApplier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next() as IUnaugmentedTrilateralNounModificationApplier
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}