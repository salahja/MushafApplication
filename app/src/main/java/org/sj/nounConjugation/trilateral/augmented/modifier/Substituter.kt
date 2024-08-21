package org.sj.nounConjugation.trilateral.augmented.modifier

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter1
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter2
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter3
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter4
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter5
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter6
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter7
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.GenericSubstituter8
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.SpecialSubstituter1
import org.sj.nounConjugation.trilateral.augmented.modifier.substituter.SpecialSubstituter2
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: ����� �������
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
class Substituter {
    private val modifiers: MutableList<IAugmentedTrilateralModifier> = LinkedList()

    init {
        modifiers.add(GenericSubstituter1())
        modifiers.add(GenericSubstituter2())
        modifiers.add(GenericSubstituter3())
        modifiers.add(GenericSubstituter4())
        modifiers.add(GenericSubstituter5())
        modifiers.add(GenericSubstituter6())
        modifiers.add(GenericSubstituter7())
        modifiers.add(GenericSubstituter8())
        modifiers.add(SpecialSubstituter1())
        modifiers.add(SpecialSubstituter2())
    }

    fun apply(conjResult: MazeedConjugationResult) {
        val iter: Iterator<IAugmentedTrilateralModifier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next()
            if (modifier.isApplied(conjResult)) {
                (modifier as TrilateralNounSubstitutionApplier).apply(
                    conjResult.finalResult as MutableList<Any>,
                    conjResult.root!!
                )
                break
            }
        }
    }
}
