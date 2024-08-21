package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative6Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (تَساوَ، تَسَوَّ)
        substitutions.add(InfixSubstitution("وِي", "يْ")) // EX: (أنتِ تَساوَيْ، تَسَوَّيْ)
        substitutions.add(InfixSubstitution("وَا", "يَا")) // EX: (أنتما تَساوَيا، تَسَوَّيا)
        substitutions.add(InfixSubstitution("وُو", "وْ")) // EX: (أنتم تَساوَوْا، تَسَوَّوْا)
        substitutions.add(InfixSubstitution("وْن", "يْن")) // EX: (أنتن تَساوَيْنَ، تَسَوَّيْنَ)
        substitutions.add(InfixSubstitution("وَن", "يَن")) // EX: (أنتَ تَساوَيَنَّ، تَسَوَّيَنَّ)
        substitutions.add(InfixSubstitution("وِن", "يِن")) // EX: (أنتِ تَساوَيِنَّ، تَسَوَّيِنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return root!!.c2 == root!!.c3 && root!!.c3 == 'و' && kov == 28 && (formulaNo == 7 || formulaNo == 8)
    }
}