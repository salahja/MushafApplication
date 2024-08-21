package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active

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
        ) // EX: (يُهْدِي، يُجارِي، يَنْثَنِي، يَكْتَفِي، يَسْتَغْنِي، يَعْرَوْرِي)
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (لم يُهْدِ، يُجارِ، يَنْثَنِ، يَكْتَفِ، يَسْتَغْنِ، يَعْرَوْرِ)
        substitutions.add(
            InfixSubstitution(
                "يِن",
                "ن"
            )
        ) // EX: (أنتِ تُهْدِنَّ، تُجارِنَّ، تَنْثَنِنَّ، تَكْتَفِنَّ، تَسْتَغْنِنَّ، تَعْرَوْرِنَّ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "ي"
            )
        ) // EX: (أنتِ تُهْدِينَ، تُجارِينَ، تَنْثَنِينَ، تَكْتَفِينَ، تَسْتَغْنِينَ، تَعْرَوْرِينَ)
        substitutions.add(
            InfixSubstitution(
                "يْن",
                "ين"
            )
        ) // EX: (أنتن تُهْدِينَ، تُجارِينَ، تَنْثَنِينَ، تَكْتَفِينَ، تَسْتَغْنِينَ، تَعْرَوْرِينَ)
        substitutions.add(
            InfixSubstitution(
                "ِيُو",
                "ُو"
            )
        ) // EX: (أنتم تُهْدُونَ، تُجارُونَ، تَنْثَنُونَ، تَكْتَفُونَ، تَسْتَغْنُونَ، تَعْرَوْرُونَ)
        substitutions.add(
            InfixSubstitution(
                "ِيُن",
                "ُن"
            )
        ) // EX: (أنتم تُهْدُنَّ، تُجارُنَّ، تَنْثَنُنَّ، تَكْتَفُنَّ، تَسْتَغْنُنَّ، تَعْرَوْرُنَّ)
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