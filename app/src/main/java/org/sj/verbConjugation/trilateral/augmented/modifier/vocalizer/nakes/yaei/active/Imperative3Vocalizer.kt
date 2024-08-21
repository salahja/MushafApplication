package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active

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
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (رَقِّ، اجْأوِّ)
        substitutions.add(InfixSubstitution("يِي", "ي")) // EX: (أنتِ رَقِّي، اجْأوِّي)
        substitutions.add(InfixSubstitution("يِن", "ن")) // EX: (أنتِ رَقِّنَّ، اجْأوِّنَّ)
        substitutions.add(InfixSubstitution("يْن", "ين")) // EX: (أنتن رَقِّينَ، اجْأوِّينَ)
        substitutions.add(InfixSubstitution("ِّيُ", "ُّ")) // EX: (أنتم رَقُّوا، اجْأوُّوا)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 26 || kov == 24) && formulaNo == 2 || kov == 25 && (formulaNo == 2 || formulaNo == 11)
    }
}