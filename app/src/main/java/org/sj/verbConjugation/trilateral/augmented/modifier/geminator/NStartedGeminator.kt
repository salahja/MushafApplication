package org.sj.verbConjugation.trilateral.augmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class NStartedGeminator : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("نْن", "نّ")) // EX: (انَّمَسَ)
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return mazeedConjugationResult.root!!.c1 == 'ن' && kov == 1 && formulaNo == 4
    }

    fun apply(tense: String?, active: Boolean, conjResult: MazeedConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    }


}