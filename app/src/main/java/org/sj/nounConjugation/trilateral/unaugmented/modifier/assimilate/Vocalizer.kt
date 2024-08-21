package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate

import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer.Vocalizer1
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer.Vocalizer2
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer.Vocalizer31
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer.Vocalizer32
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer.Vocalizer41
import org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer.Vocalizer42
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
class Vocalizer {
    private val modifiers: MutableList<IUnaugmentedTrilateralNounModificationApplier> = LinkedList()

    init {
        modifiers.add(Vocalizer1())
        modifiers.add(Vocalizer2())
        modifiers.add(Vocalizer31())
        modifiers.add(Vocalizer32())
        modifiers.add(Vocalizer41())
        modifiers.add(Vocalizer42())
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