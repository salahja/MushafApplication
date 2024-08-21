package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class SpecialSubstituter2 : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("يْتَ", "تَّ")) // EX: (اتَّسَرَ، يَتَّسِرُ، اتَّسِرْ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return mazeedConjugationResult.root!!.c1 == 'ي' && formulaNo == 5 && (kov == 13 || kov == 14)
    }
}