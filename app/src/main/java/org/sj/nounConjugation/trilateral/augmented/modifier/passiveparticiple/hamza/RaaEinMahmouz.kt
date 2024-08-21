package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.hamza

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
class RaaEinMahmouz : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ْءً", "ً")) // EX: (مُرًى، )
        substitutions.add(InfixSubstitution("ْءَ", "َ")) // EX: (مُرَيانِ، )
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val root = mazeedConjugationResult.root!!
        return root!!.c1 == 'ر' && root!!.c2 == 'ء' && root!!.c3 == 'ي' && mazeedConjugationResult.formulaNo == 1
    }
}