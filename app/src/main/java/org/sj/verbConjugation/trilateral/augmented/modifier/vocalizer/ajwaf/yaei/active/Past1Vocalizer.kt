package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْيَC3ْ", "َC3ْ")) // EX: (أبَدْتُ، استقلتُ)
        substitutions.add(ExpressionInfixSubstitution("ْيَC3ّ", "َC3ّ")) // EX: (أبَتُّ)
        substitutions.add(ExpressionInfixSubstitution("ْيَC3َ", "َاC3َ")) // EX: (أبادَ، استقال)
        substitutions.add(ExpressionInfixSubstitution("ْيَC3ُ", "َاC3ُ")) // EX: (أبادُوا، استقالوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (((((kov == 19) || (kov == 20)) && (formulaNo == 1)) || (kov == 18) || (kov == 19) || ((kov == 20) && (formulaNo == 9))))
    }
}