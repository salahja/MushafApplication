package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.vocalizer

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
class WawiNakesLafifVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َوُ", "َى")) // EX: (هذا المِغْزَى )
        substitutions.add(SuffixSubstitution("َوَ", "َى")) // EX: (رأيت المِغْزَى )
        substitutions.add(SuffixSubstitution("َوِ", "َى")) // EX: (كالمِغْزَى )
        substitutions.add(InfixSubstitution("َوٌ", "ًى")) // EX: (هذا مِغْزًى )
        substitutions.add(InfixSubstitution("َوًا", "ًى")) // EX: (رأيت مِغْزًى )
        substitutions.add(InfixSubstitution("َوٍ", "ًى")) // EX: (كَمِغْزًى )
        substitutions.add(InfixSubstitution("اوًا", "اءً")) // EX: (مِغْزاءً)
        substitutions.add(InfixSubstitution("َوَة", "َاة")) // EX: (مِغْزاة )
        substitutions.add(InfixSubstitution("َوَت", "َات")) // EX: (مِغْزاتان )
        substitutions.add(InfixSubstitution("َوَ", "َيَ")) // EX: (مِغْزَيان )
        substitutions.add(InfixSubstitution("او", "اء")) // EX: (مِغْزاء )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        if (conjugationResult.root!!.c3 != 'و') return false
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