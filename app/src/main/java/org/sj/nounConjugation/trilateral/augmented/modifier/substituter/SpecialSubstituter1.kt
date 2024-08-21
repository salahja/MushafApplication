package org.sj.nounConjugation.trilateral.augmented.modifier.substituter

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
class SpecialSubstituter1 : TrilateralNounSubstitutionApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْت", "تّ")) // EX: (اتِّصال، اتِّقاء، اتِّأء)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        var b = (mazeedConjugationResult.root!!.c1 == 'و') && (formulaNo == 5)
        val b1 = (kov == 9) || (kov == 10) || (kov == 11) || (kov == 29) || (kov == 30)
     return   b==b1

      //  return mazeedConjugationResult.root!!.c1 == 'و' && formulaNo == 5 && (kov == 9 || kov == 10 || kov == 11 || kov == 29 || kov) == 30
    }
}