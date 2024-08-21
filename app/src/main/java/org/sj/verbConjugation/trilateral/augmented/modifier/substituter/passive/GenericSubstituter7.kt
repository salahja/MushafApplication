package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.AbstractGenericSubstituter
import java.util.LinkedList

class GenericSubstituter7 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ضْت", "ضْط")) // EX: (اضْطُلِعَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ض' && super.isApplied(mazeedConjugationResult)
    }
}