package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.AbstractGenericSubstituter
import java.util.LinkedList

class GenericSubstituter4 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ذْت", "ذْد")) // EX: (اذْدُكِرَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ذ' && super.isApplied(mazeedConjugationResult)
    }
}