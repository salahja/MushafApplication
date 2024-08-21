package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza.EinMahmouz
import org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza.FaaMahmouz
import org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza.LamMahmouz
import org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza.RaaEinMahmouz
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
class Mahmouz {
    private val modifiers: MutableList<TrilateralNounSubstitutionApplier> = LinkedList()

    init {
        modifiers.add(RaaEinMahmouz())
        modifiers.add(EinMahmouz())
        modifiers.add(FaaMahmouz())
        modifiers.add(LamMahmouz())
    }

    fun apply(conjResult: MazeedConjugationResult) {
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
