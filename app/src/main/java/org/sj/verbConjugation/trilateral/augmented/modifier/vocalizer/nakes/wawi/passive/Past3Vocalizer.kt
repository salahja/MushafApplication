package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْ", "ي")) // EX: (أنا ارعُوِيتُ)
        substitutions.add(InfixSubstitution("وَّ", "وِيَ")) // EX: (هو ارْعُوِيَ)
        substitutions.add(InfixSubstitution("وُّ", "وُ")) // EX: (هم ارْعُوُوا)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 23 && formulaNo == 6
    }
}