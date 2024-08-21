package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.ExpressionSuffixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
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
class YaeiPassivePresentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionSuffixSubstitution("يْC2َيُ", "وC2َى")) // EX: (يُودَى)
        substitutions.add(ExpressionSuffixSubstitution("يْC2َيَ", "وC2َى")) // EX: (لن يُودَى)
        substitutions.add(ExpressionInfixSubstitution("يْC2َيَ", "وC2َيَ")) // EX: (يُودَيانِ)
        substitutions.add(ExpressionSuffixSubstitution("يْC2َيْ", "وC2َ")) // EX: (لم يُودَ)
        substitutions.add(ExpressionInfixSubstitution("يْC2َيْ", "وC2َيْ")) // EX: (يُودَيْنَانِّ)
        substitutions.add(ExpressionInfixSubstitution("يْC2َيِي", "وC2َيْ")) // EX: (أنتِ تُودَيْنَ)
        substitutions.add(ExpressionInfixSubstitution("يْC2َيُو", "وC2َوْ")) // EX: (أنتم تُودَوْنَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "يْC2َيُن",
                "وC2َوُن"
            )
        ) // EX: (أنتم تُودَوُنَّ)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        if (conjugationResult.root!!.c1 != 'ي') {
            return false
        }
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 29 || kov == 30) && noc == 2 || kov == 30 && (noc == 6 || noc == 4)
    }
}