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
class Imperative2Vocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionSuffixSubstitution("اوْC2ِيْ", "C2ِ")) // EX: (قِ، لِ)
        substitutions.add(ExpressionInfixSubstitution("اوْC2ِيِ", "C2ِ")) // EX: (أنتِ قِي، لِي)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اوْC2ِيْ",
                "C2ِي"
            )
        ) // EX: (أنتن قِينَ، لِينَ)
        substitutions.add(ExpressionInfixSubstitution("اوْC2ِيُ", "C2ُ")) // EX: (أنتم قُوا، لُوا)
        substitutions.add(ExpressionInfixSubstitution("اوْC2ِيَ", "C2ِيَ")) // EX: (أنتما قِيا)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return ((kov == 29 || kov == 30) && noc == 2 || kov == 30) && noc == 6
    }
}