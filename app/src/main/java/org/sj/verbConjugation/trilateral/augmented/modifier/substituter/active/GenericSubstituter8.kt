package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.AbstractGenericSubstituter
import java.util.LinkedList

class GenericSubstituter8 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ظْتَ",
                "ظْطَ"
            )
        ) // EX: (اظْطَلَمَ، يَظْطَلِمُ، اظْطَلِمْ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ظ' && super.isApplied(mazeedConjugationResult)
    }
}