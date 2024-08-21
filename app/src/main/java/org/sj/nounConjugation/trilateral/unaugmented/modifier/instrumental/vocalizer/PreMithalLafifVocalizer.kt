package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.vocalizer

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
class PreMithalLafifVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("مِوْ", "مِي")) // EX: (مِيسَم، مِيسمة، مِيصال، مِيفاء)
        substitutions.add(InfixSubstitution("مِيْ", "مِي")) // EX: (مِيقَن، مِيقنة، مِيقان، مِيداء)
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            8 -> return noc == 4
            9 -> return noc == 2
            10 -> {
                when (noc) {
                    3, 4, 5 -> return true
                }
                when (noc) {
                    2, 3, 4, 5, 6 -> return true
                }
                return noc == 2 || noc == 4
            }

            11 -> {
                when (noc) {
                    2, 3, 4, 5, 6 -> return true
                }
                return noc == 2 || noc == 4
            }

            12 -> return noc == 2 || noc == 4
            13 -> return noc == 4 || noc == 6
            14 -> {
                when (noc) {
                    1, 2, 3, 4, 5, 6 -> return true
                }
                return noc == 2
            }

            29 -> return noc == 2
            30 -> when (noc) {
                2, 4, 6 -> return true
            }
        }
        return false
    }
}