package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْ", "ي")) // EX: (أنا سُمِّيتُ، تُزُكِّيتُ)
        substitutions.add(InfixSubstitution("وَ", "يَ")) // EX: (هو سُمِّيَ، تُزُكِّيَ)
        substitutions.add(InfixSubstitution("ِّوُ", "ُّ")) // EX: (هم سُمُّوا، تُزُكُّوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 22 && formulaNo == 8 || kov == 23 || kov == 21 && (formulaNo == 2 || formulaNo == 8)
    }
}