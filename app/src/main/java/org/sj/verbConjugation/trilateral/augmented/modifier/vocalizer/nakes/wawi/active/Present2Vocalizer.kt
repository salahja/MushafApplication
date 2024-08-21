package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وُ", "ى")) // EX: (هو يَتَسامَى، يَتَزَكَّى)
        substitutions.add(SuffixSubstitution("وَ", "ى")) // EX: (لن يَتَسامَى، يَتَزَكَّى)
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (لم يَتَسامَ، يَتَزَكَّ)
        substitutions.add(InfixSubstitution("وَ", "يَ")) // EX: (أنتما تتسامَيان، تتزكَّيان)
        substitutions.add(InfixSubstitution("وِي", "يْ")) // EX: (أنتِ تتسامَيْنَ، تتزَكَّيْنَ)
        substitutions.add(InfixSubstitution("وِن", "يِن")) // EX: (أنتِ تتسامَيِنَّ، تتزَكَّيِنَّ)
        substitutions.add(InfixSubstitution("وُو", "وْ")) // EX: (أنتم تتسامَوْنَ، تتزَكَّوْنَ)
        substitutions.add(InfixSubstitution("وْن", "يْن")) // EX: (أنتن تتسامَيْنَ، تتزَكَّيْنَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            21, 22, 23 -> when (formulaNo) {
                7, 8 -> return true
            }
        }
        return false
    }
}