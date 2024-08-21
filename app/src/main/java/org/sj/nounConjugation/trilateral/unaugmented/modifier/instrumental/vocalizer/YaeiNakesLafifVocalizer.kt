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
class YaeiNakesLafifVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (هذا المِسرى )
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (رأيت المِسرى)
        substitutions.add(SuffixSubstitution("َيِ", "َى")) // EX: (كالمِسرى )
        substitutions.add(InfixSubstitution("َيٌ", "ًى")) // EX: (هذا مِسرًى )
        substitutions.add(InfixSubstitution("َيًا", "ًى")) // EX: (رأيت مِسرًى)
        substitutions.add(InfixSubstitution("َيٍ", "ًى")) // EX: (كَمِسرًى )
        substitutions.add(InfixSubstitution("ايًا", "اءً")) // EX: (مِسراءً)
        substitutions.add(InfixSubstitution("َيَة", "َاة")) // EX: (مِسراة )
        substitutions.add(InfixSubstitution("َيَت", "َات")) // EX: (مِسراتان )
        substitutions.add(InfixSubstitution("اي", "اء")) // EX: (مِسراء )
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        if (conjugationResult.root!!.c3 != 'ي') return false
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