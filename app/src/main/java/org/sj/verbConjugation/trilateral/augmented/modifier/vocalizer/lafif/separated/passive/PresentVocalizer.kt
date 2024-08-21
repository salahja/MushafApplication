package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class PresentVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "َيُ",
                "َى"
            )
        ) // EX: (يوصَى، يُوَصَّى، يُوالَى، يُتَّقَى، يُتَوارَى، يُتَوَلَّى، يُسْتَوْفَى)
        substitutions.add(
            SuffixSubstitution(
                "َيَ",
                "َى"
            )
        ) // EX: (لن يُوصَى، يُوَصَّى، يُوالَى، يُتَّقَى، يُتَوارَى، يُتَوَلَّى، يُسْتَوْفَى)
        substitutions.add(
            SuffixSubstitution(
                "َيْ",
                "َ"
            )
        ) // EX: (لم يُوصَ، يُوَصَّ، يُوالَ، يُتَّقَ، يُتَوارَ، يُتَوَلَّ، يُسْتَوْفَ)
        substitutions.add(
            InfixSubstitution(
                "َيِي",
                "َيْ"
            )
        ) // EX: (أنتِ تُوصَيْنَ، تُوَصَّيْنَ، تُوالَين، تُتَّقَين، تُتَوارَين، تُتَوَلَّين، تُسْتَوْفَين)
        substitutions.add(
            InfixSubstitution(
                "َيُو",
                "َوْ"
            )
        ) // EX: (أنتم تُوصَوْنَ، تُوَصَّوْنَ، تُوالَون، تُتَّقَون، تُتَوارَوْن، تُتَوَلَّوْن، تُسْتَوْفَون)
        substitutions.add(
            InfixSubstitution(
                "َيُن",
                "َوُن"
            )
        ) // EX: (أنتم تُوصَوُنَّ، تُوَصَّوُنَّ، تُوالَوُنَّ، تُتَّقَوُنَّ، تُتَوارَوُنَّ، تُتَوَلَّوُنَّ، تُسْتَوْفَوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
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