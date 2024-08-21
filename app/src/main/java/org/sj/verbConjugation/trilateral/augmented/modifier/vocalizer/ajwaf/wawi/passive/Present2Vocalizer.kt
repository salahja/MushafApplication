package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوَC3ُ",
                "َاC3ُ"
            )
        ) // EX: (هو يُنْقادُ، يُقْتاد)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوَC3ِ",
                "َاC3ِ"
            )
        ) // EX: (أنتِ تُنْقادِينَ، تُقْتادِينَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوَC3َ",
                "َاC3َ"
            )
        ) // EX: (أنتما تُنْقَادَانِ، تُقْتادَانِ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوَC3ْ",
                "َC3ْ"
            )
        ) // EX: (هنّ يُنْقَدْنَ، يُقْتَدْنَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 15 || kov == 17) && formulaNo == 4 || kov == 15 || kov == 16 || kov == 17 && formulaNo == 5
    }
}