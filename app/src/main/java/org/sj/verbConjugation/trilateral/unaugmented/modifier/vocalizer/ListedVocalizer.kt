package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:كان  من  ضمنها  هذا  الجذر  أو  لا في حالات الاعلال يتم فحص قائمة من الجذور اذا
 *
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
abstract class ListedVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    /**
     * isApplied
     *
     * @param conjugationResult ConjugationResult
     * @return boolean
     * @todo Implement this
     * org.sj.verb.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
     * method
     */
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root
        //فحص الباب التصريفي أولاً
        if (root.conjugation != noc.toString() + "") return false
        val iter = appliedRoots.iterator()
        while (iter.hasNext()) {
            val appliedRoot = iter.next()
            val c1 = appliedRoot[0]
            val c2 = appliedRoot[1]
            val c3 = appliedRoot[2]
            if (c1 == root!!.c1 && c2 == root.c2 && root.c3 == c3) return true
        }
        return false
    }

    protected abstract val appliedRoots: List<String>
    protected abstract val noc: Int
}