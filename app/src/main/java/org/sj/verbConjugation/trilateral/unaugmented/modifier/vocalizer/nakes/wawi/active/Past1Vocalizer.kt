package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active

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
        substitutions.add(SuffixSubstitution("َوَ", "َا")) // EX: (غزا، أسا، عثا)
        substitutions.add(InfixSubstitution("َوُوا", "َوْا")) // EX: (غزَوْا، أسوا، عَثَوْا)
        substitutions.add(
            InfixSubstitution(
                "َوَت",
                "َت"
            )
        ) // EX: (غَزَتْ، غَزَتَا، أسَتْ، أسَتَا، عَثَتْ، عَثَتَا)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 21 || kov == 23) && (noc == 1 || noc == 3)
    }
}