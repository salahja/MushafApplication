package org.sj.verbConjugation.trilateral.augmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class TStartedGeminator : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("تْت", "تّ")) // EX: (اتَّبَعَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return mazeedConjugationResult.root!!.c1 == 'ت' && (kov == 1 || kov == 6) && formulaNo == 5
    }

    fun apply(tense: String?, active: Boolean, conjResult: MazeedConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    }
}