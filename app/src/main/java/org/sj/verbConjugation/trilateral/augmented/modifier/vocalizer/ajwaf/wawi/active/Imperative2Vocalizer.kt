package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوِC3ُ",
                "َاC3ُ"
            )
        ) // EX: (أنتم انقادوا، اقتادوا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوِC3ِ",
                "َاC3ِ"
            )
        ) // EX: (أنتِ انقادي، اقتادي)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوِC3َ",
                "َاC3َ"
            )
        ) // EX: (أنتما انقادا، اقتادا)
        substitutions.add(ExpressionInfixSubstitution("َوِC3ْ", "َC3ْ")) // EX: (أنتن انقدن، اقتدن)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 15 || kov == 17) && formulaNo == 4 || kov == 15 || kov == 16 || kov == 17 && formulaNo == 5
    }
}