package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.active

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
class Past1Vocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (رمى، أتى، سعى، نأى، أبى )
        substitutions.add(
            InfixSubstitution(
                "َيُوا",
                "َوْا"
            )
        ) // EX: (رَمَوْا، أتَوْا، سَعَوْا، نأوْا، أبَوْا)
        substitutions.add(
            InfixSubstitution(
                "َيَت",
                "َت"
            )
        ) // EX: (رَمَتْ، رَمَتَا، أتَتْ، أتَتَا، سَعَتْ، سَعَتَا، نَأتْ، نأتَا، أبَتْ، أبَتَا)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return kov == 24 || kov == 25 || kov == 26 && (noc == 2 || noc == 3)
    }
}