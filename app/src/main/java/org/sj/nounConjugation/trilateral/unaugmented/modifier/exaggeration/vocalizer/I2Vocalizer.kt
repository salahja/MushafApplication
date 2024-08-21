package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer

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
class I2Vocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِيٌ", "ٍ")) // EX: (هذا مَضٍ، عَوٍ، وَقٍ)
        substitutions.add(InfixSubstitution("ِيٍ", "ٍ")) // EX: (مررتُ على مَضٍ)
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (هذا المَضِي، )
        substitutions.add(SuffixSubstitution("ِيَ", "ِيَ")) // EX: (رأيتُ المَضِيَ، )
        substitutions.add(SuffixSubstitution("ِيِ", "ِي")) // EX: (مررتُ على المَضِي ، )
        substitutions.add(InfixSubstitution("ِيُ", "ُ")) // EX: (مَضُونَ، )
        substitutions.add(InfixSubstitution("ِيِ", "ِ")) // EX: (مَضِينَ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        if (nounFormula != "فَعِل") {
            return false
        }
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
            30 -> when (noc) {
                2, 4, 6 -> return true
            }
        }
        return false
    }
}