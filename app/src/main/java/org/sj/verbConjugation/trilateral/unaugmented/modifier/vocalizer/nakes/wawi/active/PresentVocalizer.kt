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
class PresentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ُوُ", "ُو")) // EX: (يغزو، يَمْؤُو، يأسو، يسرو، يأمو )
        substitutions.add(
            SuffixSubstitution(
                "ُوْ",
                "ُ"
            )
        ) // EX: (لم يَغْزُ، لم يَمْءُ، يَأْسُ، يَسْرُ، يأمُ)
        substitutions.add(
            InfixSubstitution(
                "ُوِ",
                "ِ"
            )
        ) // EX: (أنتِ تغزين، تأمين، تأسين، تَسرِين، تَغْزِنَّ)
        substitutions.add(
            InfixSubstitution(
                "ُوْن",
                "ُون"
            )
        ) // EX: (أنتن تغزون، تأمون، تأسون، تسرون، تأمون)
        substitutions.add(
            InfixSubstitution(
                "ُوُو",
                "ُو"
            )
        ) // EX: (أنتم تغزون، تأمون، تأسون، تسرون، تأمون )
        substitutions.add(InfixSubstitution("ُوُن", "ُن")) // EX: (أنتم تَغْزُنَّ )
        substitutions.add(SuffixSubstitution("َوُ", "َى")) // EX: (يرضى، يزهى، يجأى )
        substitutions.add(SuffixSubstitution("َوَ", "َى")) // EX: (لن يرضى، لن يزهى، لن يجأى )
        substitutions.add(SuffixSubstitution("َوْ", "َ")) // EX: (لم يرضَ، لم يَزْهَ، لم يَجْأَ)
        substitutions.add(
            InfixSubstitution(
                "َوَا",
                "َيَا"
            )
        ) // EX: (ترضيان، تَزْهَيَان، لن تزهيا، لم تزهيا، تجأيان )
        substitutions.add(
            InfixSubstitution(
                "َوِي",
                "َيْ"
            )
        ) // EX: (أنتِ ترضين، تَزْهَيْنَ، لن تَزْهَيْ، لم تَزْهَيْ، تَجْأَيْنَ )
        substitutions.add(InfixSubstitution("َوِن", "َيِن")) // EX: (أنتِ ترضينَّ، تَزْهَيِنَّ،)
        substitutions.add(
            InfixSubstitution(
                "َوْن",
                "َيْن"
            )
        ) // EX: (أنتن ترضَيْن، تَزْهَيْنَ، لن تزهَين، لم تزهين، تجأين)
        substitutions.add(
            InfixSubstitution(
                "َوُو",
                "َوْ"
            )
        ) // EX: (أنتم ترضَوْنَ، تَزْهَوْنَ، لن تزهَوْا، لم تزهَوْا، تجأون )
        substitutions.add(InfixSubstitution("َوَن", "َيَن")) // EX: (أنتَ ترضيَنَّ، )
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
   //     return ((((kov == 21 || kov == 22 || kov == 23 && noc == 1 || kov == 21 || kov == 23) && noc == 5 || kov == 22 || kov == 23) && noc == 3 || kov == 23 && noc == 4))

        return (kov == 21 || kov == 22 || kov == 23) && noc == 1 || (kov == 21 || kov == 23) && noc == 5 || (kov == 22 || kov == 23) && noc == 3 || kov == 23 && noc == 4
    }
}