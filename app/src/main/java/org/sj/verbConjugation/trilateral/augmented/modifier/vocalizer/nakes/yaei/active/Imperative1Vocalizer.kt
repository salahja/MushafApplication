package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active

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
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (أهْدِ، جَارِ، انْثَنِ، اكْتَفِ، اسْتَغْنِِِِِِِِِ، اعْرَوْرِ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "ي"
            )
        ) // EX: (أنتِ أهْدِي، جَارِي، انْثَنِي، اكْتَفِي، استغْنِي، اعْرَوْرِي)
        substitutions.add(
            InfixSubstitution(
                "يِن",
                "ن"
            )
        ) // EX: (أنتِ أهْدِنَّ، جَارِنَّ، انْثَنِنَّ، اكْتَفِنَّ، استغْنِنَّ، اعْرَوْرِنَّ)
        substitutions.add(
            InfixSubstitution(
                "يْن",
                "ين"
            )
        ) // EX: (أنتن أهْدِينَ، جَارِينَ، انْثَنِينَ، اكْتَفِينَ، استغنِينَ، اعْرَوْرِي)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (أنتم أهْدُوا، جَارُوا، انْثَنُوا، اكْتَفُوا، استغنُوا، اعْرَوْرُوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            26 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 5, 9 -> return true
                }
            }

            25 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 5, 9 -> return true
                }
            }

            24 -> when (formulaNo) {
                1, 3, 5, 9 -> return true
            }
        }
        return false
    }
}