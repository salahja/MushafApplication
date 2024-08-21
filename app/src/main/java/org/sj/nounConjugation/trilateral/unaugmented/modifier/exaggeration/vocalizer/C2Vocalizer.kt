package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class C2Vocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("او", "اء")) // EX: (مِعطاء )
        substitutions.add(InfixSubstitution("اي", "اء")) // EX: (مِجْناء، مِعْواء )
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        if (nounFormula != "مِفْعَال") {
            return false
        }
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            23 -> {
                when (noc) {
                    1, 3, 5 -> return true
                }
                when (noc) {
                    2, 3, 4 -> return true
                }
                return noc == 2 || noc == 4
            }

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