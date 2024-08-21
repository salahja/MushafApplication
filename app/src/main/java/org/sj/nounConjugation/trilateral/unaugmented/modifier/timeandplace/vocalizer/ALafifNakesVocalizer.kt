package org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer

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
class ALafifNakesVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("يَيٌ", "يَا")) // EX: (هذا المَحيا )
        substitutions.add(InfixSubstitution("يَيً", "يَا")) // EX: (رأيتُ المَحيا )
        substitutions.add(InfixSubstitution("يَيٍ", "يَا")) // EX: (بالمَحيا )
        substitutions.add(InfixSubstitution("َوَ", "َيَ")) // EX: (مَغْزِيان )
        substitutions.add(InfixSubstitution("َيَ", "َيَ")) // EX: (مَمْشَيان )
        substitutions.add(InfixSubstitution("َو", "َى")) // EX: (مَغْزَى )
        substitutions.add(InfixSubstitution("َي", "َى")) // EX: (مَمْشَى )
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        if (nounFormula != "مَفْعَل") return false
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            21 -> return noc == 1 || noc == 5
            22 -> return noc == 1 || noc == 3
            23 -> {
                when (noc) {
                    1, 3, 4, 5 -> return true
                }
                when (noc) {
                    2, 3, 4 -> return true
                }
                return noc == 3 || noc == 4
            }

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