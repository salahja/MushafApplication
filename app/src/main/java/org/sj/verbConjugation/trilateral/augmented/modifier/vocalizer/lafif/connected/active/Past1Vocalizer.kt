package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "َيَ",
                "َا"
            )
        ) // EX: (أحْيَا، حَيَّا، حايا، ازدَيَا، تحايا، تحَيَّا، استحيا)
        substitutions.add(
            InfixSubstitution(
                "يُو",
                "وْ"
            )
        ) // EX: (أحْيَوْا، حَيَّوْا حايَوْا، ، ازدَيَوْا، تحايَوْا، تَحَيَّوْا، استَحْيَوْا)
        substitutions.add(
            InfixSubstitution(
                "يَت",
                "ت"
            )
        ) // EX: (أحْيَتْ، حيَّت، حايَتْ، ، ازدَيَتْ،  تحايتْ، تَحَيَّتْ، استحيَتْ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == root!!.c3 && root!!.c3 == 'ي' && kov == 28) {
            when (formulaNo) {
                1, 2, 3, 5, 7, 8, 9 -> return true
            }
        }
        return false
    }
}