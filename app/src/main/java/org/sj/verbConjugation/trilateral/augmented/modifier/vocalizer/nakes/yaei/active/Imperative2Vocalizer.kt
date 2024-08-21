package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active

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
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (تَناسَ، تَرَقَّ)
        substitutions.add(InfixSubstitution("يِي", "يْ")) // EX: (أنتِ تَناسَيْ، تَرَقَّيْ)
        substitutions.add(InfixSubstitution("يُو", "وْ")) // EX: (أنتم تَناسَوْا، تَرَقَّوْا)
        substitutions.add(InfixSubstitution("يُن", "وُن")) // EX: (أنتم تَناسَوُنَّ، تَرَقَّوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return kov == 26 || kov == 24 || kov == 25 && (formulaNo == 7 || formulaNo == 8)
    }
}