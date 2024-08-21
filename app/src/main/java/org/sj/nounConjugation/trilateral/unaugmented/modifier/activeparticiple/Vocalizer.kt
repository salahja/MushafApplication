package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple

import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf1Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf2Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf3WawiListedVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf3WawiVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf3YaeiListedVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf3YaeiVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.Ajwaf4Vocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.WawiLafifNakesVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer.YaeiLafifNakesVocalizer
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
    private val modifiers: MutableList<SubstitutionsApplier> = LinkedList()

    init {
        modifiers.add(WawiLafifNakesVocalizer())
        modifiers.add(YaeiLafifNakesVocalizer())
        modifiers.add(Ajwaf1Vocalizer())
        modifiers.add(Ajwaf2Vocalizer())
        modifiers.add(Ajwaf3WawiVocalizer())
        modifiers.add(Ajwaf3WawiListedVocalizer())
        modifiers.add(Ajwaf3YaeiVocalizer())
        modifiers.add(Ajwaf3YaeiListedVocalizer())
        modifiers.add(Ajwaf4Vocalizer())
    }

    fun apply(conjResult: ConjugationResult) {
        val iter: Iterator<SubstitutionsApplier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next() as IUnaugmentedTrilateralNounModificationApplier
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}