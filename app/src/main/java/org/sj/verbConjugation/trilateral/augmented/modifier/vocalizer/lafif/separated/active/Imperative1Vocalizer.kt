package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (أوْصِ، والِ، اتَّقِ، استوفِ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "ي"
            )
        ) // EX: (أنتِ أوْصِي، والِي، اتَّقِي، استوفِي)
        substitutions.add(
            InfixSubstitution(
                "يِن",
                "ن"
            )
        ) // EX: (أنتِ أوْصِنَّ، والِنَّ، اتَّقِنَّ، استوْفِنَّ)
        substitutions.add(
            InfixSubstitution(
                "يْن",
                "ين"
            )
        ) // EX: (أنتن أوْصِينَ، والِينَ، اتقِينَ، استَوْفِينَ)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (أنتم أوْصُوا، والُوا، اتقُوا، استوفُوا)
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