package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
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
class PresentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (يُرْمَى، يسعى، يخشى )
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (لن يُرْمَى، يسعى، يخشى )
        substitutions.add(SuffixSubstitution("َيْ", "َ")) // EX: (لم يُرمَ، يسعَ، يخشَ)
        substitutions.add(
            InfixSubstitution(
                "َيِي",
                "َيْ"
            )
        ) // EX: (أنتِ تُرمَيْنَ، تسعَيْنَ، تخشين )
        substitutions.add(
            InfixSubstitution(
                "َيُو",
                "َوْ"
            )
        ) // EX: (أنتم تُرمَوْنَ، تسعَوْنَ، تخشون )
        substitutions.add(
            InfixSubstitution(
                "َيُن",
                "َوُن"
            )
        ) // EX: (أنتم تُرمَوُنَّ، تُسعَوُنَّ، تُخشوُنَّ )
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 24 || kov == 26) && noc == 2 || kov == 24 || kov == 25 || kov == 26 && (noc == 3 || noc == 4)
    }
}