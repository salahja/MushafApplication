package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يُ", "ا")) // EX: (يَتَحَايَا، يَتَحَيَّا)
        substitutions.add(SuffixSubstitution("َيَ", "َا")) // EX: (لن يَتَحَايَا، لن يتَحَيَّا)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يَتَحَايَ، لم يَتَحَيَّ)
        substitutions.add(InfixSubstitution("يِي", "يْ")) // EX: (أنتِ تَتَحَايَيْنَ، تَتَحَيَّيْنَ)
        substitutions.add(InfixSubstitution("يُو", "وْ")) // EX: (أنتم تَتَحايَوْنَ، تَتَحَيَّوْنَ)
        substitutions.add(
            InfixSubstitution(
                "يُن",
                "وُن"
            )
        ) // EX: (أنتم تَتَحايَوُنَّ، تَتَحَيَّوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return root!!.c2 == root!!.c3 && root!!.c3 == 'ي' && kov == 28 && (formulaNo == 7 || formulaNo == 8)
    }
}