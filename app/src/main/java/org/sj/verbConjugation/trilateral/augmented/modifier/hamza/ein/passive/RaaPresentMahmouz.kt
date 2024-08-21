package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.TrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier

class RaaPresentMahmouz : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("ْءَ", "َ")) // EX: (يُرَى)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val root: TrilateralRoot? = mazeedConjugationResult.root!!
        return mazeedConjugationResult.formulaNo == 1 && root!!.c1 == 'ر' && root!!.c2 == 'ء' && root!!.c3 == 'ي'
    }
}