package org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer.ACAjwaf1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer.ACAjwaf2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer.ALafifNakesVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer.BAjwafVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer.CLafifNakesVocalizer
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
    private val modifiers: MutableList<TrilateralNounSubstitutionApplier> = LinkedList()

    init {
        modifiers.add(ACAjwaf1Vocalizer())
        modifiers.add(ACAjwaf2Vocalizer())
        modifiers.add(BAjwafVocalizer())
        modifiers.add(ALafifNakesVocalizer())
        modifiers.add(CLafifNakesVocalizer())
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