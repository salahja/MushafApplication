package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "ِيُ",
                "ِي"
            )
        ) // EX: (يُوصِي، يوالِي، يَتَّقِي، يستوفِي)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يُوصِ، يوالِ، يَتَّقِ، يستوفِ)
        substitutions.add(
            InfixSubstitution(
                "يِن",
                "ن"
            )
        ) // EX: (أنتِ تُوصِنَّ، توالِنَّ، تَتَّقِنَّ، تستوفِنَّ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "ي"
            )
        ) // EX: (أنتِ تُوصِينَ، تُوالِينَ، تَتَّقِينَ، تَستوفِينَ)
        substitutions.add(
            InfixSubstitution(
                "يْن",
                "ين"
            )
        ) // EX: (أنتن تُوصِينَ، تُوالِينَ، تَتَّقِينَ، تَستوفِينَ)
        substitutions.add(
            InfixSubstitution(
                "ِيُو",
                "ُو"
            )
        ) // EX: (أنتم تُوصُونَ، تُوالُونَ، تَتَّقُونَ، تَستوفُونَ)
        substitutions.add(
            InfixSubstitution(
                "ِيُن",
                "ُن"
            )
        ) // EX: (أنتم تُوصُنَّ، تُوالُنَّ، تَتَّقُنَّ، تَستوفُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            29 -> {
                when (formulaNo) {
                    5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 5, 9 -> return true
                }
            }

            30 -> when (formulaNo) {
                1, 3, 5, 9 -> return true
            }
        }
        return false
    }
}