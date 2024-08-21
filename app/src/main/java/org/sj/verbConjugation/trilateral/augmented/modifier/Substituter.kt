package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter1
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter2
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter3
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter4
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter5
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter6
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter7
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.GenericSubstituter8
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.SpecialSubstituter1
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active.SpecialSubstituter2
import java.util.LinkedList

class Substituter {
    private val activeList: MutableList<SubstitutionsApplier> = LinkedList()
    private val passiveList: MutableList<SubstitutionsApplier> = LinkedList()

    init {
        activeList.add(GenericSubstituter1())
        activeList.add(GenericSubstituter2())
        activeList.add(GenericSubstituter3())
        activeList.add(GenericSubstituter4())
        activeList.add(GenericSubstituter5())
        activeList.add(GenericSubstituter6())
        activeList.add(GenericSubstituter7())
        activeList.add(GenericSubstituter8())
        activeList.add(SpecialSubstituter1())
        activeList.add(SpecialSubstituter2())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter1())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter2())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter3())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter4())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter5())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter6())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter7())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.GenericSubstituter8())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.SpecialSubstituter1())
        passiveList.add(org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive.SpecialSubstituter2())
    }

    fun apply(tense: String?, active: Boolean, conjResult: MazeedConjugationResult) {
        var modifiers: List<SubstitutionsApplier>? = null
        modifiers = if (!active) {
            passiveList
        } else {
            activeList
        }
        val iter = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next() as IAugmentedTrilateralModifier
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}