package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class PastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْوِC3ْ", "ِC3ْ")) // EX: (أُقِمْتُ، استدرتُ،)
        substitutions.add(ExpressionInfixSubstitution("ْوِC3ّ", "ِC3ّ")) // EX: (أُمِتُّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوِC3َ",
                "ِيC3َ"
            )
        ) // EX: (أُقِيمَ، استُدِير،)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (أُقِيمُوا، استُدِيروا،)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُوِC3ْ",
                "ِC3ْ"
            )
        ) // EX: (انْقِدْتُ، اقْتِدْتُ)
        substitutions.add(ExpressionInfixSubstitution("ُوِC3ّ", "ِC3ّ")) // EX: (اختِتُّ)
        substitutions.add(ExpressionInfixSubstitution("ُوِC3َ", "ِيC3َ")) // EX: (انقِيدَ، اقْتِيدَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُوِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (انقِيدُوا، اقتِيدُوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (((((((kov == 16) || (kov == 17)) && (formulaNo == 1)) || (kov == 15) || ((kov == 17) && (formulaNo == 4)) || (kov == 15) || (kov == 16) || (kov == 17)) && (formulaNo == 5)) || (kov == 15) || (kov == 16) || ((kov == 17) && (formulaNo == 9))))
    }
}