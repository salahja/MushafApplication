package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (تَوارَ، تَوَلَّ)
        substitutions.add(InfixSubstitution("يِي", "يْ")) // EX: (أنتِ تَوارَيْ، تَوَلَّيْ)
        substitutions.add(InfixSubstitution("يُو", "وْ")) // EX: (أنتم تَوَارَوْا، تَوَلَّوْا)
        substitutions.add(InfixSubstitution("يُن", "وُن")) // EX: (أنتم تَوَارَوُنَّ، تَوَلَّوُنَّ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 29 && formulaNo == 7 || kov == 30 && (formulaNo == 7 || formulaNo == 8)
    }
}