package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("َيَC3ُ", "َاC3ُ")) // EX: (هو يُنهال، يُكتال)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيَC3ِ",
                "َاC3ِ"
            )
        ) // EX: (أنتِ تُنهالين، تُكتالين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيَC3َ",
                "َاC3َ"
            )
        ) // EX: (أنتما تُنهالان، تُكتالان)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيَC3ْ",
                "َC3ْ"
            )
        ) // EX: (هنّ يُنْهَلْنَ، يُكْتَلْنَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 20 && formulaNo == 4 || kov == 18 || kov == 20) && formulaNo == 5
    }
}