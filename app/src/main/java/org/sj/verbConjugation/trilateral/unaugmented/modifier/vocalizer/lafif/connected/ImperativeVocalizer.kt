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
class ImperativeVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيْ", "ِ")) // EX: (اشْوِ)
        substitutions.add(InfixSubstitution("ِيِن", "ِن")) // EX: (أنتِ اشْوِنَّ)
        substitutions.add(InfixSubstitution("ِيِ", "ِ")) // EX: (أنتِ اشْوِي)
        substitutions.add(InfixSubstitution("ِيْ", "ِي")) // EX: (أنتن اشْوِينَ)
        substitutions.add(InfixSubstitution("ِيُ", "ُ")) // EX: (أنتم اشْوُوا)
        substitutions.add(SuffixSubstitution("َيْ", "َ")) // EX: (أنتَ اقْوَ، احْيَ)
        substitutions.add(InfixSubstitution("َيِي", "َيْ")) // EX: (أنتِ اقوَيْ، احْيَيْ)
        substitutions.add(InfixSubstitution("َيُو", "َوْ")) // EX: (أنتم اقْوَوْا، احْيَوْا)
        substitutions.add(InfixSubstitution("َيُن", "َوُن")) // EX: (أنتم اقْوَوُنَّ، احيَوُنَّ)
        substitutions.add(SuffixSubstitution("َوْ", "َ")) // EX: (اسْوَ)
        substitutions.add(InfixSubstitution("َوِي", "َيْ")) // EX: (أنتِ اسْوَيْ)
        substitutions.add(InfixSubstitution("َوَن", "َيَن")) // EX: (أنتَ اسْوَيَنَّ)
        substitutions.add(InfixSubstitution("َوْن", "َيْن")) // EX: (أنتن اسْوَيْنَ)
        substitutions.add(InfixSubstitution("َوِن", "َيِن")) // EX: (أنتِ اسْوَيِنَّ)
        substitutions.add(InfixSubstitution("َوُو", "َوْ")) // EX: (أنتم اسْوَوْا)
        substitutions.add(InfixSubstitution("َوَ", "َيَ")) // EX: (أنتما اسْويا)
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (kov == 27 || kov == 28) && noc == 2 || kov == 28 && noc == 4
    }
}