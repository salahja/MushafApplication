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
class WawiPassivePresentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionSuffixSubstitution("ْC2َيُ", "C2َى")) // EX: (يُوقَى)
        substitutions.add(ExpressionSuffixSubstitution("ْC2َيَ", "C2َى")) // EX: (لن يُوقَى)
        substitutions.add(ExpressionSuffixSubstitution("ْC2َيْ", "C2َ")) // EX: (لم يُوقَ)
        substitutions.add(ExpressionInfixSubstitution("ْC2َيْ", "C2َيْ")) // EX: (أنتن تُوقينَ)
        substitutions.add(ExpressionInfixSubstitution("ْC2َيَ", "C2َيَ")) // EX: (أنتما تُوقَيَان)
        substitutions.add(ExpressionInfixSubstitution("ْC2َيِي", "C2َيْ")) // EX: (أنتِ تُوقَيْنَ)
        substitutions.add(ExpressionInfixSubstitution("ْC2َيِن", "C2َيِن")) // EX: (أنتِ تُوقَيِنَّ)
        substitutions.add(ExpressionInfixSubstitution("ْC2َيُو", "C2َوْ")) // EX: (أنتم تُوقَوْنَ)
        substitutions.add(ExpressionInfixSubstitution("ْC2َيُن", "C2َوُن")) // EX: (أنتم تُوقَوُنَّ)
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        if (conjugationResult.root!!.c1 != 'و') {
            return false
        }
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 29 || kov == 30) && noc == 2 || kov == 30 && (noc == 6 || noc == 4)
    }
}