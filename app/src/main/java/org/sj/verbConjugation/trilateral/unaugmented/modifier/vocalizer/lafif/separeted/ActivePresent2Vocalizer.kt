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
class ActivePresent2Vocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionSuffixSubstitution("َوْC2ِيُ", "َC2ِي")) // EX: (يَقِي، يَلِي)
        substitutions.add(ExpressionSuffixSubstitution("َوْC2ِيْ", "َC2ِ")) // EX: (لم يَقِ، يَلِ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوْC2ِيِن",
                "َC2ِن"
            )
        ) // EX: (أنتِ تَقِنَّ، تَلِنَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوْC2ِيِ",
                "َC2ِ"
            )
        ) // EX: (أنتِ تَقِينَ، تَلِينَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوْC2ِيْ",
                "َC2ِي"
            )
        ) // EX: (أنتن تَقِينَ، تَلِينَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َوْC2ِيُ",
                "َC2ُ"
            )
        ) // EX: (أنتم تَقُون، تَقُنَّ، تَلُون، تَلُنَّ)
        substitutions.add(ExpressionInfixSubstitution("َوْC2ِيَ", "َC2ِيَ")) // EX: (أنتما تقيان)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 29 || kov == 30) && noc == 2 || kov == 30 && noc == 6
    }
}