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
class YaeiLafifNakesVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (هذا الأهدى، )
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (رأيتُ الأهدى، )
        substitutions.add(SuffixSubstitution("َيِ", "َى")) // EX: (مررتُ على الأهدى، )
        substitutions.add(InfixSubstitution("َيُو", "َوْ")) // EX: (الأهدَوْنَ)
        substitutions.add(InfixSubstitution("َيِي", "َيْ")) // EX: (الأهدَيْنَ)
        substitutions.add(InfixSubstitution("ْيَى", "ْيَا")) // EX: (الهديا)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            26 -> {
                when (noc) {
                    2, 3, 4 -> return true
                }
                return noc == 2 || noc == 4
            }

            28 -> return noc == 2 || noc == 4
        }
        return false
    }
}