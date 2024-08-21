package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present5Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (يُقَوِّي)
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (لم يُقَوِّ)
        substitutions.add(InfixSubstitution("ِّيِ", "ِّ")) // EX: (أنتِ تقوِّينَ)
        substitutions.add(InfixSubstitution("ِّيُ", "ُّ")) // EX: (أنتم تُقَوُّونَ)
        substitutions.add(InfixSubstitution("ِّيْ", "ِّي")) // EX: (أنتن تُقَوِّينَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root =
            mazeedConjugationResult.root!!
        return root!!.c2 == 'و' && root!!.c3 == 'ي' && (kov == 28 || kov == 27) && formulaNo == 2
    }
}