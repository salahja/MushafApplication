package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "يَ",
                "ى"
            )
        ) // EX: (أذْوَى، قَوَّى، داوى، انزوى، احتوى،تداوى، تقوَّى استهوى)
        substitutions.add(
            InfixSubstitution(
                "يُو",
                "وْ"
            )
        ) // EX: (أذوَوْا، قَوَّوْا داوَوْا، انزَوَوْا، احتَوَوْا، تداوَوْا، تَقَوَّوْا، استهوَوْا)
        substitutions.add(
            InfixSubstitution(
                "يَت",
                "ت"
            )
        ) // EX: (أذوَتْ، قَوَّت داوَت، انزوتْ، احتوتْ، تداوتْ، تقوَّتْ، استهوتْ)
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