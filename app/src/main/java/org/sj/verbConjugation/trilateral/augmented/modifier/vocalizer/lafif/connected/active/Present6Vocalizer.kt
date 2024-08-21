package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present6Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يُ", "ى")) // EX: (يَتَداوَى، يتقوَّى)
        substitutions.add(SuffixSubstitution("يَ", "ى")) // EX: (لن يَتَداوَى، لن يتقوَّى)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يَتَدَاوَ، لم يتقوَّ)
        substitutions.add(InfixSubstitution("يِي", "يْ")) // EX: (أنتِ تتداوَيْنَ، تتقوَّيْنَ)
        substitutions.add(InfixSubstitution("يُو", "وْ")) // EX: (أنتم تتداوَوْنَ، تتقوَّوْنَ)
        substitutions.add(InfixSubstitution("يُن", "وُن")) // EX: (أنتم تَتَداوَوُنَّ، تتقوَّوُنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return root!!.c2 == 'و' && root!!.c3 == 'ي' && (kov == 27 || kov == 28) && (formulaNo == 7 || formulaNo == 8)
    }
}