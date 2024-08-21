package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وُ", "ي")) // EX: (هو يُسَمِّي)
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (لم يُسَمِّ)
        substitutions.add(InfixSubstitution("ِّوُ", "ُّ")) // EX: (أنتم تُسَمُّونَ)
        substitutions.add(InfixSubstitution("وْن", "ين")) // EX: (أنتن تُسَمِّينَ)
        substitutions.add(InfixSubstitution("وَ", "يَ")) // EX: (لن يُسَمِّيَ)
        substitutions.add(InfixSubstitution("وِ", "")) // EX: (أنتِ تُسَمِّينَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 21 || kov == 23) && formulaNo == 2
    }
}