package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (سَمِّ)
        substitutions.add(InfixSubstitution("ِّوُ", "ُّ")) // EX: (أنتم سَمُّوا)
        substitutions.add(InfixSubstitution("وْن", "ين")) // EX: (أنتن سَمِّينَ)
        substitutions.add(InfixSubstitution("وَ", "يَ")) // EX: (أنتما سَمِّيَا)
        substitutions.add(InfixSubstitution("وِ", "")) // EX: (أنتِ سَمِّي)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 21 || kov == 23) && formulaNo == 2
    }
}