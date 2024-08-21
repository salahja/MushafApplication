package org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.vocalizer.Ajwaf1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.vocalizer.Ajwaf2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.vocalizer.WawiLafifNakes1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.vocalizer.WawiLafifNakes2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.vocalizer.YaeiLafifNakesVocalizer
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
        modifiers.add(WawiLafifNakes1Vocalizer())
        modifiers.add(WawiLafifNakes2Vocalizer())
        modifiers.add(YaeiLafifNakesVocalizer())
        modifiers.add(Ajwaf1Vocalizer())
        modifiers.add(Ajwaf2Vocalizer())
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