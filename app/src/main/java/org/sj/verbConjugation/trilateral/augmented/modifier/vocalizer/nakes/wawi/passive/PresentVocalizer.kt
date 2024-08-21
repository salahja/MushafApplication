package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive

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
                "وُ",
                "ى"
            )
        ) // EX: (يُدْنَى، يُسَمَّى، يحابَى، يرتضى، يرعوَى، يُتسامى، يُتزكَّى، يسترضى، يحلولى)
        substitutions.add(
            SuffixSubstitution(
                "وَ",
                "ى"
            )
        ) // EX: (لن يُدْنَى، يُسَمَّى، يحابَى، يرتضى، يُتسامى، يُتزكَّى، يسترضى، يحلولى)
        substitutions.add(
            SuffixSubstitution(
                "وْ",
                ""
            )
        ) // EX: (لم يُدْنَ، يُسَمَّ، يحابَ، يرتضَ، يرعوَ، يُتسامَ، يُتزكَّ، يسترضَ، يحلولَ)
        substitutions.add(
            InfixSubstitution(
                "وِي",
                "يْ"
            )
        ) // EX: (أنتِ تُدْنَيْنَ، تُسَمَّيْن، تحابَين، ترتضَين، تُتسامَين، تُتزكَّين، تسترضَين، تحلولَين)
        substitutions.add(
            InfixSubstitution(
                "وَا",
                "يَا"
            )
        ) // EX: (أنتما تُدْنَيان، تُسَمَّيان، تحابَيان، ترتضَيان، تُتسامَيان، تُتزكَّيان، تسترضَيان، تحلولَيان)
        substitutions.add(
            InfixSubstitution(
                "وُو",
                "وْ"
            )
        ) // EX: (أنتم تُدْنَوْن، تُسَمَّوْن، تحابَوْن، ترتضَوْن، تُتسامَوْن، تُتزكَّوْن، تسترضَوْن، تحلولَوْن)
        substitutions.add(
            InfixSubstitution(
                "وْن",
                "يْن"
            )
        ) // EX: (أنتن تُدْنَيْنَ، تُسَمَّيْن، تحابَين، ترتضَين، تُتسامَين، تُتزكَّين، تسترضَين، تحلولَين)
        substitutions.add(
            InfixSubstitution(
                "وَن",
                "يَن"
            )
        ) // EX: (هو يُدْنَيَنَّ، يُسَمَّيَنَّ، يحابَيَنَّ، يرتضَيَنَّ، يُتسامَيَنَّ، يُتزكَّيَنَّ، يسترضَيَنَّ، يحلولَيَنَّ)
        substitutions.add(
            InfixSubstitution(
                "وِن",
                "يِن"
            )
        ) // EX: (أنتِ تُدْنَيِنَّ، تُسَمَّيِنَّ، تحابَيِنَّ، ترتضَيِنَّ، تُتسامَيِنَّ، تُتزكَّيِنَّ، تسترضَيِنَّ، تحلولَيِنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            21 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> return true
                }
            }

            22 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> return true
                }
            }

            23 -> when (formulaNo) {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> return true
            }
        }
        return false
    }
}