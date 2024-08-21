package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class Ajwaf2Vocalizer : TrilateralNounSubstitutionApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ْيَ", "َا")) // EX: (مُبادٌ، مُستَقَالٌ)
        substitutions.add(InfixSubstitution("َيَ", "َا")) // EX: (مُنْهالٌ، مُكْتالٌ)
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            18 -> {
                when (formulaNo) {
                    1, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 9 -> return true
                }
                when (formulaNo) {
                    1, 4, 5, 9 -> return true
                }
            }

            19 -> {
                when (formulaNo) {
                    1, 9 -> return true
                }
                when (formulaNo) {
                    1, 4, 5, 9 -> return true
                }
            }

            20 -> when (formulaNo) {
                1, 4, 5, 9 -> return true
            }
        }
        return false
    }


}