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
class Ajwaf1Vocalizer : TrilateralNounSubstitutionApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ْوَ", "َا")) // EX: (مُقامٌ، مُسْتَدارٌ)
        substitutions.add(InfixSubstitution("َوَ", "َا")) // EX: (مُنْقادٌ، مُقْتادٌ)
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            15 -> {
                when (formulaNo) {
                    4, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 4, 5, 9 -> return true
                }
            }

            16 -> {
                when (formulaNo) {
                    1, 5, 9 -> return true
                }
                when (formulaNo) {
                    1, 4, 5, 9 -> return true
                }
            }

            17 -> when (formulaNo) {
                1, 4, 5, 9 -> return true
            }
        }
        return false
    }

   
}