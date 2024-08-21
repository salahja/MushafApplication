package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.vocalizer

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
class Mithal1Vocalizer : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
      override val substitutions: MutableList<InfixSubstitution?> = LinkedList<InfixSubstitution?>()

    init {
        substitutions.add(InfixSubstitution("ُوْ", "ُو")) // EX: (مُوجِبٌ، )
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
      return  (formulaNo == 1) &&  kov==9 || kov==10||kov==11



    }


}
