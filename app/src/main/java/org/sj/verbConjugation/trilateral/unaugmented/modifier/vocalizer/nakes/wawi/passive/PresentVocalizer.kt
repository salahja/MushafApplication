package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.passive

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
        substitutions.add(SuffixSubstitution("َوُ", "َى")) // EX: (يُغْزَى، يُزهى، يُرضَى، يُسْرَى)
        substitutions.add(SuffixSubstitution("َوَ", "َى")) // EX: (لن يُغزى )
        substitutions.add(SuffixSubstitution("َوْ", "َ")) // EX: (لم يُغْزَ)
        substitutions.add(InfixSubstitution("َوَا", "َيَا")) // EX: (يُغزيان )
        substitutions.add(InfixSubstitution("َوِي", "َيْ")) // EX: (أنتِ تُغْزَيْنَ )
        substitutions.add(InfixSubstitution("َوَن", "َيَن")) // EX: (هو يُغْزَيَنَّ، يُزْهَيَنَّ)
        substitutions.add(InfixSubstitution("َوِن", "َيِن")) // EX: (أنتِ تُغْزَيِنَّ)
        substitutions.add(InfixSubstitution("َوْن", "َيْن")) // EX: (أنتن تُغْزَيْنَ)
        substitutions.add(InfixSubstitution("َوُو", "َوْ")) // EX: (أنتم تُغْزَوْنَ )
    }




    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (((kov == 21 || kov == 22 || kov == 23 && noc == 1 || kov == 21 || kov == 23) && noc == 5 || (kov == 22 || kov == 23) && noc == 3 || kov == 23) && noc == 4)
    }


}