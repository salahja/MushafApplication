package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected

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
class PassivePresentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يَيُ", "يَا")) // EX: (يُحْيَا)
        substitutions.add(SuffixSubstitution("يَيَ", "يَا")) // EX: (لن يُحْيَا)
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (يُشْوَى)
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (لن يُشْوَى)
        substitutions.add(SuffixSubstitution("َيْ", "َ")) // EX: (لم يُشْوَ)
        substitutions.add(InfixSubstitution("َيِي", "َيْ")) // EX: (أنتِ تُشْوَيْنَ)
        substitutions.add(InfixSubstitution("َيُو", "َوْ")) // EX: (أنتم تُشْوَوْنَ)
        substitutions.add(InfixSubstitution("َيُن", "َوُن")) // EX: (أنتم تُشْوَوُنَّ)
        substitutions.add(SuffixSubstitution("َوُ", "َى")) // EX: (يُسْوَى)
        substitutions.add(SuffixSubstitution("َوَ", "َى")) // EX: (لن يُسْوَى)
        substitutions.add(SuffixSubstitution("َوْ", "َ")) // EX: (لم يُسْوَ)
        substitutions.add(InfixSubstitution("َوِي", "َيْ")) // EX: (أنتِ تُسْوَيْنَ)
        substitutions.add(InfixSubstitution("َوِن", "َيِن")) // EX: (أنتِ تُسْوَيِنَّ)
        substitutions.add(InfixSubstitution("َوَن", "َيَن")) // EX: (أنتَ تُسْوَيَنَّ)
        substitutions.add(InfixSubstitution("َوُو", "َوْ")) // EX: (أنتم تُسْوَوْنَ)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 27 || kov == 28) && noc == 2 || kov == 28 && noc == 4
    }
}