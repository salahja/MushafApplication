package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active

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
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (يُرَقِّي، يَجْأوِّي)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يُرَقِّ، يَجْأوِّ)
        substitutions.add(InfixSubstitution("يِن", "ن")) // EX: (أنتِ تُرَقِّنَّ، تَجْأوِّنَّ)
        substitutions.add(InfixSubstitution("يِي", "ي")) // EX: (أنتِ تُرَقِّينَ، تَجْأوِّينَ)
        substitutions.add(InfixSubstitution("يْن", "ين")) // EX: (أنتن تُرَقِّينَ، تَجْأوِّينَ)
        substitutions.add(InfixSubstitution("ِّيُو", "ُّو")) // EX: (أنتم تُرَقُّونَ، تَجْأوُّونَ)
        substitutions.add(InfixSubstitution("ِّيُن", "ُّن")) // EX: (أنتم تُرَقُّنَّ، تَجْأوُّنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 26 || kov == 24) && formulaNo == 2 || kov == 25 && (formulaNo == 2 || formulaNo == 11)
    }
}