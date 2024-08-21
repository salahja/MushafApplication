package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active

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
                "ْيِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (هو يُبِيدُ، يستقيل)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3ِ",
                "ِيC3ِ"
            )
        ) // EX: (أنتِ تُبِيدِين، تَستقيلين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3َ",
                "ِيC3َ"
            )
        ) // EX: (أنتما تُبِيدان، تستقيلان)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3ْ",
                "ِC3ْ"
            )
        ) // EX: (هنّ يُبِدْنَ، يَستقلن)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 18 || kov == 19 || kov == 20 && formulaNo == 1 || kov == 18 || kov == 19 || kov == 20 && formulaNo == 9
    }
}