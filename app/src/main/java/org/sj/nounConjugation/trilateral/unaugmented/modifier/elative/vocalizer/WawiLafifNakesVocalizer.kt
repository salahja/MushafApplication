package org.sj.nounConjugation.trilateral.unaugmented.modifier.elative.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
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
class WawiLafifNakesVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َوُ", "َى")) // EX: (هذا الأعلى، )
        substitutions.add(SuffixSubstitution("َوَ", "َى")) // EX: (رأيتُ الأعلى، )
        substitutions.add(SuffixSubstitution("َوِ", "َى")) // EX: (مررتُ على الأعلى، )
        substitutions.add(InfixSubstitution("َوَ", "َيَ")) // EX: (الأعليان)
        substitutions.add(InfixSubstitution("َوُو", "َوْ")) // EX: (الأعلَوْن)
        substitutions.add(InfixSubstitution("َوِي", "َيْ")) // EX: (الأعلَيْن)
        substitutions.add(InfixSubstitution("ْوَى", "ْيَا")) // EX: (العليا)
        substitutions.add(InfixSubstitution("ْوَي", "ْيَي")) // EX: (عُلْيَيَان)
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        if (kov != 23) {
            return false
        }
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (noc) {
            1, 3, 4, 5 -> return true
        }
        return false
    }
}