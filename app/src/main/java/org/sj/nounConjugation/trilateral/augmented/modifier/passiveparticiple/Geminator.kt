package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple

import org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.geminator.Geminator1
import org.sj.nounConjugation.trilateral.augmented.modifier.geminator.Geminator2
import org.sj.nounConjugation.trilateral.augmented.modifier.geminator.Geminator3
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
class Geminator {
    private val modifiers: MutableList<IAugmentedTrilateralModifier> = LinkedList()

    init {
        modifiers.add(Geminator1())
        modifiers.add(Geminator2())
        modifiers.add(Geminator3())
    }

    fun apply(conjResult: MazeedConjugationResult) {
        val iter: Iterator<IAugmentedTrilateralModifier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next()
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}