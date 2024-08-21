package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

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
        substitutions.add(SuffixSubstitution("يْ", "")) // EX: (حَيِّ، قَوِّ)
        substitutions.add(InfixSubstitution("ِّيِ", "ِّ")) // EX: (أنتِ حَيِّي، قَوِّي)
        substitutions.add(InfixSubstitution("ِّيُ", "ُّ")) // EX: (أنتم حَيُّوا، قَوُّوا)
        substitutions.add(InfixSubstitution("ِّيْ", "ِّي")) // EX: (أنتن حَيِّينَ، قَوِّينَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return (root!!.c2 == 'و' || root!!.c2 == 'ي') && root!!.c3 == 'ي' && (kov == 27 || kov == 28) && formulaNo == 2
    }
}