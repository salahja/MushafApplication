package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class PastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْيِC3ْ", "ِC3ْ")) // EX: (أبِدْتُ، استُقلتُ،)
        substitutions.add(ExpressionInfixSubstitution("ْيِC3ّ", "ِC3ّ")) // EX: (أُبِتُّ)
        substitutions.add(ExpressionInfixSubstitution("ْيِC3َ", "ِيC3َ")) // EX: (أبِيدَ، استُقِيل،)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (أبِيدُوا، استُقِيلوا،)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُيِC3ْ",
                "ِC3ْ"
            )
        ) // EX: (انْهِلْتُ، اكتِلْتُ)
        substitutions.add(ExpressionInfixSubstitution("ُيِC3ّ", "ِC3ّ")) // EX: (اختِتُّ)
        substitutions.add(ExpressionInfixSubstitution("ُيِC3َ", "ِيC3َ")) // EX: (انْهِيلَ، اكتِيل)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُيِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (انهِيلوا، اكتِيلوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return ((kov == 19 || kov == 20) && formulaNo == 1 || kov == 20 && formulaNo == 4 || kov == 20 || kov == 18) && formulaNo == 5 || kov == 18 || kov == 19 || kov == 20 && formulaNo == 9
    }
}