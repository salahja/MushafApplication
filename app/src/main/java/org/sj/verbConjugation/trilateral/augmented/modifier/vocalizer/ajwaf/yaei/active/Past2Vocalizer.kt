package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("َيَC3ْ", "َC3ْ")) // EX: (انْهَلْتُ، اكتلتُ)
        substitutions.add(ExpressionInfixSubstitution("َيَC3ّ", "َC3ّ")) // EX: (اختَتّ)
        substitutions.add(ExpressionInfixSubstitution("َيَC3َ", "َاC3َ")) // EX: (انْهالَ، اكتال)
        substitutions.add(ExpressionInfixSubstitution("َيَC3ُ", "َاC3ُ")) // EX: (انهالوا، اكتالوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 20 && formulaNo == 4 || kov == 20 || kov == 18 && formulaNo == 5
    }
}