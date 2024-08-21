package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active

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
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (يَتَناسَى، يَتَرَقَّى)
        substitutions.add(SuffixSubstitution("يَ", "ى")) // EX: (لن يَتَناسَى، يَتَرَقَّى)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يَتَناسَ، يَتَرَقَّ)
        substitutions.add(InfixSubstitution("يِي", "يْ")) // EX: (أنتِ تَتَناسَيْنَ، تَتَرَقَّيْنَ)
        substitutions.add(InfixSubstitution("يُو", "وْ")) // EX: (أنتم تَتَناسَوْنَ، تَتَرَقَّوْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُن",
                "وُن"
            )
        ) // EX: (أنتم تَتَناسَوُنَّ، تَتَرَقَّوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 26 || kov == 24 || kov == 25 && (formulaNo == 7 || formulaNo == 8)
    }
}