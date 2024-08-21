package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (تحايَ، تداوَ، تَحَيَّ، تَقَوَّ)
        substitutions.add(
            InfixSubstitution(
                "يِي",
                "يْ"
            )
        ) // EX: (أنتِ تَحايَيْ، تَداوَيْ، تَحَيَّيْ، تَقَوَّيْ)
        substitutions.add(
            InfixSubstitution(
                "يُو",
                "وْ"
            )
        ) // EX: (أنتم تَحايَوْا، تَدَاوَوْا، تَحَيَّوْا، تَقَوَّوْا)
        substitutions.add(
            InfixSubstitution(
                "يُن",
                "وُن"
            )
        ) // EX: (أنتم تَحايَوُنَّ، تداوَوُنَّ، تَحَيَّوُنَّ، تَقَوَّوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return (root!!.c2 == 'و' || root!!.c2 == 'ي') && root!!.c3 == 'ي' && (kov == 27 || kov == 28) && (formulaNo == 7 || formulaNo == 8)
    }
}