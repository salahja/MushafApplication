package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (تَسامَ، تَزَكَّ)
        substitutions.add(InfixSubstitution("وِي", "يْ")) // EX: (أنتِ تسامَيْ، تزَكَّيْ)
        substitutions.add(InfixSubstitution("وِن", "يِن")) // EX: (أنتِ تسامَيِنَّ، تزَكَّيِنَّ)
        substitutions.add(InfixSubstitution("وُو", "وْ")) // EX: (أنتم تسامَوْا، تزَكَّوْا)
        substitutions.add(InfixSubstitution("وْن", "يْن")) // EX: (أنتن تسامَيْنَ، تزَكَّيْنَ)
        substitutions.add(InfixSubstitution("وَ", "يَ")) // EX: (أنتما تسامَيا، تزكَّيا)
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