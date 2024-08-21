package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration

import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.AVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.B1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.B2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.C1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.C2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.C3Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.I1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.I2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer.JVocalizer
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
        modifiers.add(AVocalizer())
        modifiers.add(B1Vocalizer())
        modifiers.add(B2Vocalizer())
        modifiers.add(C1Vocalizer())
        modifiers.add(C2Vocalizer())
        modifiers.add(C3Vocalizer())
        modifiers.add(I1Vocalizer())
        modifiers.add(I2Vocalizer())
        modifiers.add(JVocalizer())
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