package org.sj.verbConjugation.trilateral.augmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class NEndedGeminator : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("نْن", "نّ")) // EX: (نحن سَكَّنَّا، هنَّ سَكَّنَّ)
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        if (mazeedConjugationResult.root!!.c3 != 'ن') return false
        when (formulaNo) {
            1, 2, 3, 4, 5, 7, 8, 9, 10, 11 -> when (kov) {
                1, 2, 3, 5, 6, 11, 14, 15, 17, 18, 20 -> return true
            }
        }
        return false
    }

    fun apply(tense: String?, active: Boolean, conjResult: MazeedConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    }

   
}