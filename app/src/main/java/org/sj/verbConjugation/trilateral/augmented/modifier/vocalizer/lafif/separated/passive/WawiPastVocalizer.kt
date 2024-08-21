package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class WawiPastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ِيْ",
                "ِي"
            )
        ) // EX: (أنا أُوصِيتُ، وُفِّيتُ، وُولِيتُ، اتُّقِيتُ، تُوُوريتُ، تُوُلِّيتُ، استُوفِيتُ)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (هم أُوصُوا، وُولُوا، اتُّقُوا، تُوُورُوا، استُوفُوا)
        substitutions.add(InfixSubstitution("ِّيُ", "ُّ")) // EX: (هم وُفُّوا، تُوُلُّوا)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        if (mazeedConjugationResult.root!!.c1 != 'و') return false
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            29 -> {
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
            }

            30 -> when (formulaNo) {
                1, 2, 3, 5, 7, 8, 9 -> return true
            }
        }
        return false
    }
}