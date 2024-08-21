package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive

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
        substitutions.add(
            SuffixSubstitution(
                "َيُ",
                "َى"
            )
        ) // EX: (يُذْوَى، يُقَوَّى، يُداوَى، يُنْزَوَى، يُحْتَوَى، يُتَدَاوَى، يُتَقَوَّى، يُسْتَهْوَى)
        substitutions.add(
            SuffixSubstitution(
                "يَ",
                "ى"
            )
        ) // EX: (لن يُذْوَى، يُقَوَّى، يُداوَى، يُنْزَوَى، يُحْتَوَى، يُتَدَاوَى، يُتَقَوَّى، يُسْتَهْوَى)
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (لم يُذْوَ، يُقَوَّ، يُداوَ، يُنْزَوَ، يُحْتَوَ، يُتَدَاوَ، يُتَقَوَّ، يُسْتَهْوَ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "يْ"
            )
        ) // EX: (أنتِ تُذْوَيْنَ، تُقَوَّيْنَ، تداوَيْنَ، تنْزَوَيْنَ، تحتوين، تتداوين، تتقوين، تستهوين)
        substitutions.add(
            InfixSubstitution(
                "يُو",
                "وْ"
            )
        ) // EX: (أنتم تُذْوَوْنَ، تُقَوَّوْنَ، تداوَوْنَ، تنْزَوَوْنَ، تحتوَوْنَ، تتداوَوْنَ، تتقوَّوْن، تُسْتَهْوَوْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُنّ",
                "وُنّ"
            )
        ) // EX: (أنتم تُذْوَوُنَّ، تُقَوَّوُنَّ، تداوَوُنَّ، تنْزَوَوُنَّ، تحتوَوُنَّ، تتداوَوُنَّ، تتقوَّوُنَّ، تُسْتَهْوَوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == 'و' && root!!.c3 == 'ي') {
            when (kov) {
                27 -> {
                    when (formulaNo) {
                        1, 2, 5, 7, 8, 9 -> return true
                    }
                    when (formulaNo) {
                        1, 2, 3, 4, 5, 7, 8, 9 -> return true
                    }
                }

                28 -> when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
            }
        }
        return false
    }
}