package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active

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
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (يُوَصِّي)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يُوَصِّ)
        substitutions.add(InfixSubstitution("يِن", "ن")) // EX: (أنتِ تُوَصِّنَّ)
        substitutions.add(InfixSubstitution("يِي", "ي")) // EX: (أنتِ تُوَصِّينَ)
        substitutions.add(InfixSubstitution("يْن", "ين")) // EX: (أنتن تُوَصِّينَ)
        substitutions.add(InfixSubstitution("ِّيُو", "ُّو")) // EX: (أنتم تُوَصُّونَ)
        substitutions.add(InfixSubstitution("ِّيُن", "ُّن")) // EX: (أنتم تُوَصُّنَّ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 30 && formulaNo == 2
    }
}