package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class PastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "يْ",
                "ي"
            )
        ) // EX: (أنا أُهْدِيتُ، رُقِّيتُ، جُوريتُ، انثُنيت، اكتُفِيتُ، تُنُوسِيتُ، استُغْنِيتُ، اعْرُوُرِيتُ، اجؤُوِّيتُ)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (هم أُهْدُوا، جُورُوا، انثُنُوا، اكتُفُوا، تُنوسُوا، استُغْنُوا، اعْرُورُوا)
        substitutions.add(InfixSubstitution("ِّيُ", "ُّ")) // EX: (هم رُقُّوا، تُرُقُّوا، اجؤُوُّوا)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            24 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
            }

            25 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
            }

            26 -> when (formulaNo) {
                1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
            }
        }
        return false
    }
}