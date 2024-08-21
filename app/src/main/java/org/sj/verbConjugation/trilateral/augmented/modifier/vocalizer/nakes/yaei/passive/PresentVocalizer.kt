package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.passive

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
        ) // EX: (يُهْدَى، يُرَقَّى، يُجارَى، يُنثنَى، يُكتفى، يُتناسى، يُترقَّى، يُستغنى، يُعرورى، يُجْأوَّى)
        substitutions.add(
            SuffixSubstitution(
                "يَ",
                "ى"
            )
        ) // EX: (لن يُهْدَى، يُرَقَّى، يُجارَى، يُنثنَى، يُكتفى، يُتناسى، يُترقَّى، يُستغنى، يُعرورى، يُجْأوَّى)
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (لم يُهْدَ، يُرَقَّ، يُجارَ، يُنثنَ، يُكتف، يُتناس، يُترقَّ، يُستغن، يُعرور، يُجْأوَّ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "يْ"
            )
        ) // EX: (أنتِ تُهْدَيْنَ، تُرَقَّيْنَ، تُجارَيْنَ، تُنثنَيْنَ، تُكتفيْنَ، تُتناسَيْنَ، تُترقَّيْنَ، تُستغنَيْنَ، تُعرورَيْنَ، تُجْأوَّيْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُو",
                "وْ"
            )
        ) // EX: (أنتم تُهْدَوْنَ، تُرَقَّوْنَ، تُجارَوْنَ، تُنثنَوْنَ، تُكتفوْنَ، تُتناسَوْنَ، تُترقَّوْنَ، تُستغنَوْنَ، تُعرورَوْنَ، تُجْأوَّوْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُنَّ",
                "وُنَّ"
            )
        ) // EX: (أنتن تُهْدَوُنَّ، تُرَقَّوُنَّ، تُجارَوُنَّ، تُنثنَوُنَّ، تُكتفوُنَّ، تُتناسَوُنَّ، تُترقَّوُنَّ، تُستغنَوُنَّ، تُعرورَوُنَّ، تُجْأوَّوُنَّ)
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