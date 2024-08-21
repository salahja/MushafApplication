package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "َيُ",
                "َا"
            )
        ) // EX: (يُحْيَا، يحيَّا، يحايَا، يزديَا، يتحَايَا، يتحيَّا، يُستحيَا)
        substitutions.add(
            SuffixSubstitution(
                "َيَ",
                "َا"
            )
        ) // EX: (لن يُحْيَا، يحيَّا، يحايَا، يزديَا، يتحَايَا، يتحيَّا، يُستحيَا)
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (لم يُحْيَ، يحيَّ، يحايَ، يزديَ، يتحَايَ، يتحيَّ، يُستحيَ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "يْ"
            )
        ) // EX: (أنتِ تُحْيَيْنَ، تُحَيَّيْنَ، تحايَيْنَ، تزديَيْنَ، تتحايَيْنَ، تتحيَّيْنَ، تستحيَيْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُو",
                "وْ"
            )
        ) // EX: (أنتم تُحْيَوْنَ، تُحَيَّوْنَ، تُحايَوْنَ، تزدَيَوْنَ، تتحايَوْنَ، تتحيَّوْنَ، تستحيَوْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُن",
                "وُن"
            )
        ) // EX: (أنتم تُحْيَوُنَّ، تُحَيَّوُنَّ، تحايَوُنَّ تزديَوُنَّ، تُتَحَايَوُنَّ، تُتَحَيَّوُنَّ، تستحيَوُنَّ)
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