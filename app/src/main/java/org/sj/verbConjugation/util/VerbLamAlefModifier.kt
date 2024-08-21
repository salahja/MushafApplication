package org.sj.verbConjugation.util

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import java.util.LinkedList

class VerbLamAlefModifier private constructor() : SubstitutionsApplier() {
    override var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("لَا", "لا")) // EX: (قالا)
        substitutions.add(InfixSubstitution("لَّا", "لاَّ")) // EX: (انْشَلاَّ)
        substitutions.add(InfixSubstitution("لَأ", "لأ")) // EX: (مَلأَ، مَلأْتُ)
        substitutions.add(InfixSubstitution("لًا", "لاً")) // EX: (حملاً)
    }

    fun apply(conjResult: ConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, null)
    }

    fun apply(conjResult: MazeedConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, null)
    }
    // public void apply(org.sj.verb.quadriliteral.ConjugationResult conjResult) {
    // apply(conjResult.getFinalResult(), null);
    //}
/*
    fun getSubstitutions(): List<*> {
        return substitutions
    }
*/

    companion object {
        val instance = VerbLamAlefModifier()
    }
}