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
class ImperativeVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ُوْ", "ُ")) // EX: (اغْزُ، اسرُ)
        substitutions.add(InfixSubstitution("ُوِ", "ِ")) // EX: (أنتِ اغزي، اسري )
        substitutions.add(InfixSubstitution("ُوُو", "ُو")) // EX: (أنتم اغزوا، اسروا )
        substitutions.add(InfixSubstitution("ُوْن", "ُون")) // EX: (أنتن اغزون، اسرون)
        substitutions.add(InfixSubstitution("ُوُن", "ُن")) // EX: (أنتم اغْزُنَّ )
        substitutions.add(SuffixSubstitution("َوْ", "َ")) // EX: (ازْهَ، ارضَ)
        substitutions.add(InfixSubstitution("َوِي", "َيْ")) // EX: (أنتِ ازهَيْ، ارضَيْ )
        substitutions.add(InfixSubstitution("َوِن", "َيِن")) // EX: (أنتِ ازهَيِنَّ، ارضيِنَّ)
        substitutions.add(
            InfixSubstitution(
                "َوْن",
                "َيْن"
            )
        ) // EX: (أنتن ازهَيْنَ، ازْهَيْنَانِّ، ارضَيْنَ)
        substitutions.add(InfixSubstitution("َوُو", "َوْ")) // EX: (أنتم ازهَوْا، ارضَوْا)
        substitutions.add(InfixSubstitution("َوَا", "َيَا")) // EX: (أنتما ارضيا، ازْهَيَا، )
        substitutions.add(InfixSubstitution("َوَن", "َيَن")) // EX: (أنتَ ارضيَنَّ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return ((kov == 21 || kov == 22 || kov == 23 && noc == 1 || kov == 21 || kov == 23 && noc == 5 || (kov == 22 || kov == 23) && noc == 3 || kov == 23) && noc == 4)
    }
}