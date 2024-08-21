package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (يُحَيِّي)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يُحَيِّ)
        substitutions.add(InfixSubstitution("ِّيِ", "ِّ")) // EX: (أنتِ تُحَيِّينَ)
        substitutions.add(InfixSubstitution("ِّيُ", "ُّ")) // EX: (أنتم تُحَيُّونَ)
        substitutions.add(InfixSubstitution("ِّيْ", "ِّي")) // EX: (أنتن تُحَيِّينَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return root!!.c2 == root!!.c3 && root!!.c3 == 'ي' && kov == 28 && formulaNo == 2
    }
}