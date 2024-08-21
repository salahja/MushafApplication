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
class ImperativeVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيْ", "ِ")) // EX: (أنتَ ارمِ)
        substitutions.add(InfixSubstitution("ِيِ", "ِ")) // EX: (أنتِ ارمي)
        substitutions.add(InfixSubstitution("ِيْ", "ِي")) // EX: (انتن ارمين)
        substitutions.add(InfixSubstitution("ِيُ", "ُ")) // EX: (أنتم ارموا)
        substitutions.add(SuffixSubstitution("َيْ", "َ")) // EX: (اسعَ، اخشَ)
        substitutions.add(InfixSubstitution("َيِي", "َيْ")) // EX: (أنتِ اسعَيْ، اخشي )
        substitutions.add(InfixSubstitution("َيُو", "َوْ")) // EX: (أنتم اسعَوْا، اخشَوْا )
        substitutions.add(InfixSubstitution("َيُن", "َوُن")) // EX: (أنتم اسعَوُنَّ، اخشَوُنَّ )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return ((kov == 24 || kov == 26) && noc == 2 || kov == 24 || kov == 25 || kov == 26 && (noc == 3 || noc == 4))
    }
}