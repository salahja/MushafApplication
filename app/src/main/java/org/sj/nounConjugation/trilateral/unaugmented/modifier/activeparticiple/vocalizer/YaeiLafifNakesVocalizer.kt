package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer

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
        substitutions.add(InfixSubstitution("ِيٌ", "ٍ")) // EX: (هذا رامٍ)
        substitutions.add(InfixSubstitution("ِيٍ", "ٍ")) // EX: (مررتُ على رامٍ)
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (هذا الرامي، رامي السهم، )
        substitutions.add(SuffixSubstitution("ِيَ", "ِيَ")) // EX: (رأيتُ الراميَ، راميَ السهم، )
        substitutions.add(SuffixSubstitution("ِيِ", "ِي")) // EX: (مررتُ على الرامي، رامي السهم ، )
        substitutions.add(InfixSubstitution("ِيُ", "ُ")) // EX: (رامُونَ، )
        substitutions.add(InfixSubstitution("ِيِ", "ِ")) // EX: (رامِينَ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            24, 26 -> {
                when (noc) {
                    2, 3, 4 -> return true
                }
                return noc == 3 || noc == 4
            }

            25 -> return noc == 3 || noc == 4
            27, 29 -> return noc == 2
            28 -> return noc == 2 || noc == 4
            30 -> when (noc) {
                2, 4, 6 -> return true
            }
        }
        return false
    }
}