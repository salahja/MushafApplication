package org.sj.nounConjugation.trilateral.augmented.modifier.substituter

import org.sj.nounConjugation.trilateral.augmented.modifier.AbstractGenericSubstituter
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
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
class GenericSubstituter5 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("زْت", "زْد")) // EX: (ازدِراد، )
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ز' && super.isApplied(mazeedConjugationResult)
    }
}