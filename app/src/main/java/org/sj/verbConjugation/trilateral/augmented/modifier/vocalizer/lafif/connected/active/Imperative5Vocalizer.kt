package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative5Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (سَوِّ، احْوَوِّ)
        substitutions.add(InfixSubstitution("ِّوِ", "ِّ")) // EX: (أنتِ سَوِّي، احْوَوِّي)
        substitutions.add(InfixSubstitution("ِّوَ", "ِّيَ")) // EX: (أنتما سَوِّيا، احْوَوِّيا)
        substitutions.add(InfixSubstitution("ِّوُ", "ُّ")) // EX: (أنتم سَوُّوا، احْوَوُّوا)
        substitutions.add(InfixSubstitution("ِّوْ", "ِّي")) // EX: (أنتن سَوِّينَ، احْوَوِّينَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return root!!.c2 == root!!.c3 && root!!.c3 == 'و' && kov == 28 && (formulaNo == 2 || formulaNo == 11)
    }
}