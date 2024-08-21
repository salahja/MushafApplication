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
class ActivePresent1Vocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionSuffixSubstitution("َيْC2ِيُ", "َC2ِي")) // EX: (يَدِي)
        substitutions.add(ExpressionSuffixSubstitution("َيْC2ِيْ", "َC2ِ")) // EX: (لم يَدِ)
        substitutions.add(ExpressionInfixSubstitution("َيْC2ِيِن", "َC2ِن")) // EX: (أنتِ تَدِنَّ)
        substitutions.add(ExpressionInfixSubstitution("َيْC2ِيِ", "َC2ِ")) // EX: (أنتِ تَدِينَ)
        substitutions.add(ExpressionInfixSubstitution("َيْC2ِيْ", "َC2ِي")) // EX: (أنتن تَدِينَ)
        substitutions.add(ExpressionInfixSubstitution("َيْC2ِيُ", "َC2ُ")) // EX: (أنتم تَدُونَ)
        substitutions.add(ExpressionInfixSubstitution("َيْC2ِيَ", "َC2ِيَ")) // EX: (أنتما تَدِيان)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return conjugationResult.root!!.c1 == 'ي' && kov == 30 && noc == 2
    }
}