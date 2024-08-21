package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.geminator

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
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
class Geminator1 : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
      override var substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْC3ِC3",
                "ِC3ّ"
            )
        ) // EX: (مُحِبٌّ، مُسْتَحِبٌّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "C3ِC3",
                "C3ّ"
            )
        ) // EX: (مُحاجٌّ، مُنْقَضٌّ، مُشْتَدٌّ، مُتَصامٌّ)
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            1, 17, 20 -> return formulaNo == 6 || formulaNo == 12
            6 -> return formulaNo == 6
            11 -> return formulaNo == 12
            2 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 7, 9 -> return true
                }
                when (formulaNo) {
                    3, 5, 7, 9 -> return true
                }
                return formulaNo == 3 || formulaNo == 7
            }

            3 -> {
                when (formulaNo) {
                    3, 5, 7, 9 -> return true
                }
                return formulaNo == 3 || formulaNo == 7
            }

            8 -> return formulaNo == 3 || formulaNo == 7
        }
        return false
    }


}
