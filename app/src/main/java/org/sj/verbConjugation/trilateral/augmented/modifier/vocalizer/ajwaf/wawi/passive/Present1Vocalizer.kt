package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْوَC3ُ", "َاC3ُ")) // EX: (هو يُقام، يُستدار)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3ِ",
                "َاC3ِ"
            )
        ) // EX: (أنتِ تُقَامِينَ، تُستدارين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3َ",
                "َاC3َ"
            )
        ) // EX: (أنتما تُقَامان، تُستداران)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3ْ",
                "َC3ْ"
            )
        ) // EX: (هنّ يُقَمْنَ، يُستَدَرْنَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (((kov == 16 || kov == 17) && formulaNo == 1 || kov == 15 || kov == 16 || kov == 17 && formulaNo == 9))
    }
}