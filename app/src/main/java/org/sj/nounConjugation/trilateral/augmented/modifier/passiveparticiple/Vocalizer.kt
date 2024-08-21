package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.vocalizer.PreSeparatedLafifVocalizer
import org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer.Ajwaf1Vocalizer
import org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer.Ajwaf2Vocalizer
import org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer.Mithal1Vocalizer
import org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer.Mithal2Vocalizer
import org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer.WawiNakesLafifVocalizer
import org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer.YaeiNakesLafifVocalizer
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
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
    private val preSeparatedLafifVocalizer = PreSeparatedLafifVocalizer()

    init {
        modifiers.add(Mithal1Vocalizer())
        modifiers.add(Mithal2Vocalizer())
        modifiers.add(Ajwaf1Vocalizer())
        modifiers.add(Ajwaf2Vocalizer())
        modifiers.add(WawiNakesLafifVocalizer())
        modifiers.add(YaeiNakesLafifVocalizer())
    }

    fun apply(conjResult: MazeedConjugationResult) {
        if (preSeparatedLafifVocalizer.isApplied(conjResult)) preSeparatedLafifVocalizer.apply(
            conjResult.finalResult as MutableList<Any>,
            conjResult.root!!
        )
        val iter: Iterator<TrilateralNounSubstitutionApplier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next() as IAugmentedTrilateralModifier
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}