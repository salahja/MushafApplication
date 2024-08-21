package org.sj.verbConjugation.trilateral.augmented.modifier.pre.vocalizer

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class SeparatedLafifPassviePastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ُوْ", "ُو")) // EX: (أنا أُوصِيتُ)
        substitutions.add(InfixSubstitution("ُيْ", "ُو")) // EX: (أنا أُودِيتُ، )
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            29 -> {
                when (formulaNo) {
                    5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 5, 9 -> return true
                }
            }

            30 -> when (formulaNo) {
                1, 3, 5, 9 -> return true
            }
        }
        return false
    }
}