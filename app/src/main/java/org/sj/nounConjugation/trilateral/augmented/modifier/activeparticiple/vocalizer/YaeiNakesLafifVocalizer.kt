package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class YaeiNakesLafifVocalizer : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
      override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (هذا المُهْدِي، )
        substitutions.add(SuffixSubstitution("ِيَ", "ِيَ")) // EX: (رأيتُ المُهْدِيَ، )
        substitutions.add(SuffixSubstitution("ِيِ", "ِي")) // EX: (مررتُ على المُهْدِي ، )
        substitutions.add(InfixSubstitution("ِيٌ", "ٍ")) // EX: (هذا مُهْدٍ)
        substitutions.add(InfixSubstitution("ِيٍ", "ٍ")) // EX: (مررتُ على مُهْدٍ)
        substitutions.add(InfixSubstitution("ِيُ", "ُ")) // EX: (مُهْدُونَ، )
        substitutions.add(InfixSubstitution("ِيِ", "ِ")) // EX: (مُهْدِينَ، )
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        if (mazeedConjugationResult.root!!.c3 != 'ي') return false
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            24, 30 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            25 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            26 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            27 -> {
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            28 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            29 -> when (formulaNo) {
                5, 7, 9 -> return true
            }
        }
        return false
    }


}
