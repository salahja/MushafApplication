package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class PastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يَ", "ى")) // EX: (أوصى)
        substitutions.add(InfixSubstitution("يُوا", "وْا")) // EX: (أوْصَوْا)
        substitutions.add(InfixSubstitution("يَت", "ت")) // EX: (هي أوصَتْ)
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