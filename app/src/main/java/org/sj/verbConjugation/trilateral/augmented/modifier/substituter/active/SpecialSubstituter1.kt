package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class SpecialSubstituter1 : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْتَ", "تَّ")) // EX: (اتَّصَلَ، يَتَّصِلُ، اتَّصِلْ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return mazeedConjugationResult.root!!.c1 == 'و' && formulaNo == 5 && (kov == 9 || kov == 10 || kov == 11 || kov == 29 || kov == 30)
    }
}