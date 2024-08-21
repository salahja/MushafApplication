package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.AbstractGenericSubstituter
import java.util.LinkedList

class GenericSubstituter5 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("زْت", "زْد")) // EX: (ازْدُرِدَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ز' && super.isApplied(mazeedConjugationResult)
    }
}