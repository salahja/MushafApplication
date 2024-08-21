package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ُ",
                "َاC3ُ"
            )
        ) // EX: (هو يُبادُ، يُستقال)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ِ",
                "َاC3ِ"
            )
        ) // EX: (أنتِ تُبادِين، تُستقالين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3َ",
                "َاC3َ"
            )
        ) // EX: (أنتما تُبادان، تُستقالان)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ْ",
                "َC3ْ"
            )
        ) // EX: (هنّ يُبَدْنَ، يُستَقَلْنَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (((kov == 18) || (kov == 19) || ((kov == 20) && (formulaNo == 1)) || (kov == 18) || (kov == 19) || ((kov == 20) && (formulaNo == 9))))
    }
}