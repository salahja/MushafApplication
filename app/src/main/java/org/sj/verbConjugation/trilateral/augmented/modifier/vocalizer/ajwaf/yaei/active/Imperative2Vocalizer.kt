package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active

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
                "َيِC3ُ",
                "َاC3ُ"
            )
        ) // EX: (انتم انهالوا، اكتالوا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيِC3ِ",
                "َاC3ِ"
            )
        ) // EX: (أنتِ انهالي، اكتالي)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيِC3َ",
                "َاC3َ"
            )
        ) // EX: (أنتما انهالا، اكتالا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيِC3ْ",
                "َC3ْ"
            )
        ) // EX: (أنتن انْهَلْنَ، اكْتَلْنَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 20 && formulaNo == 4 || kov == 20 || kov == 18 && formulaNo == 5
    }
}